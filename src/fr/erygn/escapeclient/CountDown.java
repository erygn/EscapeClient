package fr.erygn.escapeclient;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Timer;

public class CountDown {
    private final JLabel label = new JLabel("...");
    private final JPanel mainLabel = new JPanel();
    private final JPanel mainConfig = new JPanel();
    private final JTextField countSelect = new JTextField(10);
    private final JButton button = new JButton("Click me");
    private final Timer timer;
    private int count = 3;

    public CountDown() {
        timer = new Timer(1000, e -> {
            if (count > 0) {
                label.setText(String.valueOf(count--));
            } else {
                ((Timer) (e.getSource())).stop();
                count = 3;
                button.setEnabled(true);
            }
        });
        timer.setInitialDelay(0);

        button.addActionListener(e -> {
        	count = Integer.parseInt(countSelect.getText());
            if (countSelect.getText() != null && count > 0) {
            	timer.start();
                button.setEnabled(false);
            }
        });

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setLayout(new BorderLayout());
        mainLabel.add(label, BorderLayout.CENTER);
        frame.add(mainLabel, BorderLayout.CENTER);
        
        mainConfig.add(countSelect);
        mainConfig.add(button);
        frame.add(mainConfig, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CountDown();
    }
}
