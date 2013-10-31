package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControlGUI extends JPanel {
	public ControlGUI() {
		setLayout(new GridLayout(1,6));
		LogisticsPanel logPanel = new LogisticsPanel();
		add(logPanel);
		ButtonsPanel buttonPanel = new ButtonsPanel();
		add(buttonPanel);
		
	}

}
