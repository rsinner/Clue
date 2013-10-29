package clueGame;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogisticsPanel extends JPanel {
	JTextField turn, guess, guessResult, roll;
	
	public LogisticsPanel() {
		setLayout(new GridLayout(1,4));
		turn = new JTextField();
		turn.setBorder(BorderFactory.createTitledBorder("Who's turn?"));
		guess = new JTextField();
		guess.setBorder(BorderFactory.createTitledBorder("Guess"));
		guessResult = new JTextField();
		guessResult.setBorder(BorderFactory.createTitledBorder("Guess Result"));
		roll = new JTextField();
		roll.setBorder(BorderFactory.createTitledBorder("Roll"));
		
		add(roll);
		add(turn);
		add(guess);
		add(guessResult);
	}
}
