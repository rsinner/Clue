package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlGUI extends JPanel {
	private JTextField turn, guess, guessResult, roll;
	private JButton next, accuse;
	
	public ControlGUI() {
		setLayout(new GridLayout(2,1));
		
		JPanel firstRow = new JPanel();
		firstRow.setLayout(new BoxLayout(firstRow, BoxLayout.X_AXIS));
		turn = new JTextField();
		turn.setBorder(BorderFactory.createTitledBorder("Who's turn?"));
		guess = new JTextField();
		guess.setBorder(BorderFactory.createTitledBorder("Guess"));
		guessResult = new JTextField();
		guessResult.setBorder(BorderFactory.createTitledBorder("Guess Result"));
		roll = new JTextField();
		roll.setBorder(BorderFactory.createTitledBorder("Roll"));
		roll.setPreferredSize(new Dimension(50, roll.getPreferredSize().height));
		roll.setMaximumSize(new Dimension(50, roll.getPreferredSize().height));
		roll.setHorizontalAlignment(JTextField.CENTER);
		turn.setPreferredSize(new Dimension(150, turn.getPreferredSize().height));
		turn.setMaximumSize(new Dimension(150, turn.getPreferredSize().height));
		
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
	
	

	public JButton getNext() {
		return next;
	}

	public JButton getAccuse() {
		return accuse;
	}
	public JTextField getTurn() {
		return turn;
	}
	
	public void setNextText(String s) {
		turn.setText(s);
	}
	
	public void setRollText(int currentRoll){
		roll.setText(String.valueOf(currentRoll));
	}
	
	public void setGuessText(String s) {
		guess.setText(s);
	}
	
	public void setGuessResultText(String s){
		guessResult.setText(s);
	}
}
