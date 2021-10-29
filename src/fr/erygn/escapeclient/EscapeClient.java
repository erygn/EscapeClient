package fr.erygn.escapeclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EscapeClient {
	
	private int ecouleTps;
	private JPanel config;
	private JPanel mainPanel;
	private JLabel decompte;
	private String secondes;
	
	public EscapeClient() {
		System.out.println("Start");
		
		//Panel NORTH
		config = new JPanel();
				
		//Le JLabel du décompte
		decompte = new JLabel("0");
		decompte.setFont(new Font("Verdana", Font.PLAIN, 38));
		decompte.setForeground(Color.white);
			
		//Panel CountDown
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color(25, 25, 25));
		mainPanel.add(decompte, BorderLayout.CENTER);
		
		//TextField pour définir le temps de décompte
		JTextField tpsCompteur = new JTextField(10);
		JLabel tpsMsg = new JLabel("Temps de décompte en sec");
		JButton tpsBtn = new JButton("Lancer");
		
		JButton tpsInterrupt = new JButton("Stopper");
		tpsInterrupt.setEnabled(false);
		
		mainPanel.add(tpsInterrupt, BorderLayout.SOUTH);
		
		Thread countDown = new Thread(new Runnable() {
			public void run() {
				mainPanel.setVisible(true);
				config.setVisible(false);
				tpsBtn.setEnabled(false);
				ecouleTps = Integer.parseInt(secondes);
				while (ecouleTps > 0) {
					decompte.setText(Integer.toString(ecouleTps));
					try {
						Thread.sleep(1000);
						} catch (InterruptedException f) {
					}
					//System.out.println(ecouleTps);
					ecouleTps -= 1;
				}
				decompte.setText("0");
				tpsBtn.setEnabled(true);
				//countDown(secondes);
				mainPanel.setVisible(false);
				config.setVisible(true);
			}
		});
		
		tpsInterrupt.addActionListener(e -> {
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
				countDown.start();
			}
		});
		
		config.add(tpsMsg);
		config.add(tpsCompteur);
		config.add(tpsBtn);
		
		//Création de la Frame
		JFrame jF = new JFrame("EscapeClient");
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jF.setPreferredSize(new Dimension(600, 400));
		jF.pack();
		
		jF.add(config, BorderLayout.NORTH);
		jF.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setVisible(false);
		jF.setVisible(true);
	}

	public static void main(String[] args) {
		new EscapeClient();
	}
	
	public void countDown(String secondes) {
		System.out.println("Début");
		ecouleTps = Integer.parseInt(secondes);
		while (ecouleTps != 0) {
			//decompte.setText(Integer.toString(ecouleTps));
			System.out.println(ecouleTps);
			ecouleTps -= 1;
			try {
				Thread.sleep(1000);
				} catch (InterruptedException e) {
			}
		}
		System.out.println("Fin");
		
	}
}
