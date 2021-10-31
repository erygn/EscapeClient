package fr.erygn.escapeclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.net.InetAddress;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.erygn.escapeclient.ui.MainPanel;

public class EscapeClient {
	
	private MainPanel config;
	
	public EscapeClient() {
		config = new MainPanel();
		
		//Création de la Frame
		JFrame jF = new JFrame("EscapeClient");
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jF.setPreferredSize(new Dimension(600, 400));
		//config.setPreferredSize(new Dimension(600, 400));
		jF.pack();
		jF.add(config, BorderLayout.CENTER);
		jF.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		new EscapeClient();
	}
	
}
