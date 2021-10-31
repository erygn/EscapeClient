package fr.erygn.escapeclient;

import java.text.DecimalFormat;

import fr.erygn.escapeclient.ui.MainPanel;

public class CountDown extends Thread {
	public MainPanel mp;
	private int ecouleTps;
	private String secondes;
	
	public CountDown(String secondes, MainPanel mp) {
		this.secondes = secondes;
		this.mp = mp;
		start();
	}

	public void run() {
		mp.configMultiCast.setVisible(false);
		mp.mainPanel.setVisible(true);
		mp.config.setVisible(false);
		mp.tpsBtn.setEnabled(false);
		ecouleTps = Integer.parseInt(secondes) * 60;
		while (ecouleTps > 0) {
			String sent = new DecimalFormat("00").format(ecouleTps / 60) + ":" + new DecimalFormat("00").format(ecouleTps % 60);
			mp.decompte.setText(sent);
			try {
				Thread.sleep(1000);
				} catch (InterruptedException f) {
					break;
			}
			//System.out.println(ecouleTps);
			ecouleTps -= 1;
		}
		mp.decompte.setText("0");
		mp.tpsBtn.setEnabled(true);
		//countDown(secondes);
		mp.mainPanel.setVisible(false);
		mp.configMultiCast.setVisible(true);
		mp.config.setVisible(true);
	}
}
