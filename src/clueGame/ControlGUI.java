package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControlGUI extends JPanel {
	public ControlGUI() {
		
		LogisticsPanel logPanel = new LogisticsPanel();
		add(logPanel);
		ButtonsPanel buttonPanel = new ButtonsPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		
	}

}
