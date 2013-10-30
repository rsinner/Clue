package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class ControlGUI extends JFrame {
	public ControlGUI() {
		setSize(new Dimension(800, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LogisticsPanel logPanel = new LogisticsPanel();
		add(logPanel);
		ButtonsPanel buttonPanel = new ButtonsPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		
	}

}
