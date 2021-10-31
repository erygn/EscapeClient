package fr.erygn.escapeclient.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.erygn.escapeclient.CountDown;
import fr.erygn.escapeclient.MultiCastRecepteur;

public class MainPanel extends JPanel {
	private int ecouleTps;
	public JPanel config;
	public JPanel configMultiCast;
	public JPanel mainPanel;
	public JPanel mainMulti;
	public JLabel decompte;
	private String secondes;
	public JButton tpsBtn;
	
	private CountDown countDown;
	
	private MultiCastRecepteur recepteur;
	
	public MainPanel() {
		config = new JPanel();
		configMultiCast = new JPanel();
		mainMulti = new JPanel();
		
		//Le JLabel du décompte
		decompte = new JLabel("0", SwingConstants.CENTER);
		decompte.setFont(new Font("Verdana", Font.PLAIN, 38));
		decompte.setForeground(Color.white);
			
		//Panel CountDown
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color(25, 25, 25));
		mainPanel.add(decompte, BorderLayout.CENTER);
		
		//TextField pour définir le temps de décompte
		JTextField tpsCompteur = new JTextField(5);
		JLabel tpsMsg = new JLabel("Local | Temps de décompte en minute");
		tpsBtn = new JButton("Lancer");
		
		//TextField pour définir la connexion en multicast
		JTextField ipMulti = new JTextField("127.0.0.1", 10);
		JLabel ipMultiLabel = new JLabel("Multi | IP");
		JButton ipMultiConnect = new JButton("Connexion");
		JButton ipDisconnect = new JButton("Deconnexion");
		
		ipDisconnect.addActionListener(e -> {
			recepteur.interrupt();
			recepteur.Disconnet();
		});
		
		JButton tpsInterrupt = new JButton("Stopper");
		tpsInterrupt.setEnabled(true);
		
		mainPanel.add(tpsInterrupt, BorderLayout.SOUTH);
		
		tpsInterrupt.addActionListener(e -> {
			countDown.interrupt();
			ecouleTps = 0;
			decompte.setText("0");
			tpsBtn.setEnabled(true);
			//countDown(secondes);
			mainPanel.setVisible(false);
			config.setVisible(true);
		});
		
		tpsBtn.addActionListener(e -> {
			secondes = tpsCompteur.getText();
			if (secondes != null && secondes.length() > 0) {
				countDown = new CountDown(secondes, this);
				//countDown.start();
			}
		});
		
		ipMultiConnect.addActionListener(e -> {
			String ipConnect = ipMulti.getText();
			if (ipConnect != null && ipConnect.length() > 0) {
				try {
					String nom = "Client";
					InetAddress groupeIP;
					groupeIP = InetAddress.getByName(ipConnect);
					int port = 8090; 
					recepteur = new MultiCastRecepteur(nom, groupeIP, port, this);
					config.setVisible(false);
					configMultiCast.setVisible(false);
					mainMulti.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("Impossible d'attribuer de se connecter à l'adresse multicast");
					//e1.printStackTrace();
				}
			}
		});
		
		config.add(tpsMsg);
		config.add(tpsCompteur);
		config.add(tpsBtn);
		
		configMultiCast.add(ipMultiLabel);
		configMultiCast.add(ipMulti);
		configMultiCast.add(ipMultiConnect);
		
		mainMulti.add(ipDisconnect);
		
		add(config);
		add(configMultiCast);
		add(mainPanel);
		add(mainMulti);
		mainMulti.setVisible(false);
		mainPanel.setVisible(false);
	}
}
