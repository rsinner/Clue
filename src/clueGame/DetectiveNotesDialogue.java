package clueGame;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DetectiveNotesDialogue extends JDialog {
	private ArrayList<String> playerNames;
	private ArrayList<String> roomNames;
	private ArrayList<String> weaponNames;
	
	public DetectiveNotesDialogue(ArrayList<String> pNames, ArrayList<String> rNames, ArrayList<String> wNames) {
		
		setTitle("Detective Notes");
		setSize(400, 600);
		setLayout(new GridLayout(3,2));
		
		JPanel people = new JPanel();
		people.setLayout(new GridLayout(3,2));
		for(String s : pNames)
			people.add(new JCheckBox(s));
		people.setBorder(BorderFactory.createTitledBorder("People"));
		add(people);
		
		JPanel personGuess = new JPanel();
		JComboBox drop = new JComboBox();
		drop.setEditable(false);
		for(String s : pNames)
			drop.addItem(s);
		personGuess.setBorder(BorderFactory.createTitledBorder("Person Guess"));
		personGuess.add(drop);
		add(personGuess);
		
		JPanel rooms = new JPanel();
		rooms.setLayout(new GridLayout(5,2));
		for(String s : rNames)
			rooms.add(new JCheckBox(s));
		rooms.setBorder(BorderFactory.createTitledBorder("Rooms"));
		add(rooms);
		
		JPanel roomGuess = new JPanel();
		JComboBox drop2 = new JComboBox();
		drop2.setEditable(false);
		for(String s : rNames)
			drop2.addItem(s);
		roomGuess.setBorder(BorderFactory.createTitledBorder("Room Guess"));
		roomGuess.add(drop2);
		add(roomGuess);
		
		JPanel weapons = new JPanel();
		weapons.setLayout(new GridLayout(3,2));
		for(String s : wNames)
			weapons.add(new JCheckBox(s));
		weapons.setBorder(BorderFactory.createTitledBorder("Weapons"));
		add(weapons);
		
		JPanel weaponGuess = new JPanel();
		JComboBox drop3 = new JComboBox();
		drop3.setEditable(false);
		for(String s : wNames)
			drop3.addItem(s);
		weaponGuess.setBorder(BorderFactory.createTitledBorder("Weapon Guess"));
		weaponGuess.add(drop3);
		add(weaponGuess);
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
