package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlGUI extends JPanel {
	JTextField turn, guess, guessResult, roll;
	private JButton next, accuse;
	
	public ControlGUI() {
		setLayout(new GridLayout(2,1));
		
		JPanel firstRow = new JPanel();
		firstRow.setLayout(new GridLayout(1,4));
		turn = new JTextField();
		turn.setBorder(BorderFactory.createTitledBorder("Who's turn?"));
		guess = new JTextField();
		guess.setBorder(BorderFactory.createTitledBorder("Guess"));
		guessResult = new JTextField();
		guessResult.setBorder(BorderFactory.createTitledBorder("Guess Result"));
		roll = new JTextField();
		roll.setBorder(BorderFactory.createTitledBorder("Roll"));
		
		firstRow.add(roll);
		firstRow.add(turn);
		firstRow.add(guess);
		firstRow.add(guessResult);
		add(firstRow);
		
		JPanel secondRow = new JPanel();
		next = new JButton();
		accuse = new JButton();
		next.setText("Next Player");
		accuse.setText("Make an accusation");
		secondRow.add(next);
		secondRow.add(accuse);
		
		add(secondRow);
	
		
	}

}
