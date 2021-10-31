package fr.erygn.escapeclient;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;

import fr.erygn.escapeclient.ui.MainPanel;

public class MultiCastRecepteur extends Thread {
	private String nomPartie;
	private InetAddress ipAdress;
	private int port;
	private MulticastSocket socketReception;
	private SocketAddress socketAddr;
	private boolean isRunning;
	
	private MainPanel mp;
	
	public MultiCastRecepteur(String nom, InetAddress addr, int port, MainPanel mp) throws IOException {
		this.nomPartie = nom;
		this.ipAdress = addr;
		this.port = port;
		this.mp = mp;
		this.isRunning = true;
		socketAddr = new InetSocketAddress(addr, port);
		socketReception = new MulticastSocket(socketAddr);
		start();
	}
	
	public void run() {
		System.out.println("Lancement du thread");
		DatagramPacket message;
		byte[] contenuMessage;
		String texte;
		ByteArrayInputStream lect;
		
		while(isRunning == true) {
			contenuMessage = new byte[1024];
			message = new DatagramPacket(contenuMessage, contenuMessage.length);
			try {
				socketReception.receive(message);
				texte = (new DataInputStream(new ByteArrayInputStream(contenuMessage))).readUTF();
				//if (!texte.startsWith(nomPartie)) continue;
				System.out.println(texte);
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		System.out.println("Fin thread");
	}
	
	public void interrupt() {
		super.interrupt();
		socketReception.close();
		socketReception.disconnect();
	}
	
	public void Disconnet() {
		isRunning = false;
		mp.mainMulti.setVisible(false);
		mp.config.setVisible(true);
		mp.configMultiCast.setVisible(true);
	}
}
