package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel{
	private JButton next, accuse;
	
	public ButtonsPanel() {
		
		next = new JButton();
		accuse = new JButton();
		next.setText("Next Player");
		accuse.setText("Make an accusation");
		add(next);
		add(accuse);
	}

	public JButton getNext() {
		return next;
	}
}
