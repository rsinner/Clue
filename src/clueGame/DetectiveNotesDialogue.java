package clueGame;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DetectiveNotesDialogue extends JDialog {
	public DetectiveNotesDialogue() {
		setTitle("Detective Notes");
		setSize(400, 600);
		setLayout(new GridLayout(3,2));
		
		JPanel people = new JPanel();
		JCheckBox scarlett = new JCheckBox("Miss Scarlett");
		JCheckBox human = new JCheckBox("Human");
		JCheckBox mustard = new JCheckBox("Colonel Mustard");
		JCheckBox green = new JCheckBox("Mr. Green");
		JCheckBox plum = new JCheckBox("Professor Plum");
		JCheckBox violet = new JCheckBox("Violet");
		people.setLayout(new GridLayout(3,2));
		people.add(scarlett);
		people.add(human);
		people.add(mustard);
		people.add(green);
		people.add(plum);
		people.add(violet);
		people.setBorder(BorderFactory.createTitledBorder("People"));
		add(people);
		
		JPanel personGuess = new JPanel();
		JComboBox drop = new JComboBox();
		drop.setEditable(false);
		drop.addItem("Miss Scarlett");
		drop.addItem("Human");
		drop.addItem("Colonel Mustard");
		drop.addItem("Mr. Green");
		drop.addItem("Professor Plum");
		drop.addItem("Violet");
		personGuess.setBorder(BorderFactory.createTitledBorder("Person Guess"));
		personGuess.add(drop);
		add(personGuess);
		
		JPanel rooms = new JPanel();
		JCheckBox conservatory = new JCheckBox("Conservatory");
		JCheckBox kitchen = new JCheckBox("Kitchen");
		JCheckBox ballroom = new JCheckBox("Ballroom");
		JCheckBox billiard = new JCheckBox("Billiard Room");
		JCheckBox library = new JCheckBox("Library");
		JCheckBox study = new JCheckBox("Study");
		JCheckBox dining = new JCheckBox("Dining Room");
		JCheckBox lounge = new JCheckBox("Lounge");
		JCheckBox hall = new JCheckBox("Hall");
		rooms.setLayout(new GridLayout(5,2));
		rooms.add(kitchen);
		rooms.add(ballroom);
		rooms.add(billiard);
		rooms.add(library);
		rooms.add(study);
		rooms.add(dining);
		rooms.add(lounge);
		rooms.add(hall);
		rooms.setBorder(BorderFactory.createTitledBorder("Rooms"));
		add(rooms);
		
		JPanel roomGuess = new JPanel();
		JComboBox drop2 = new JComboBox();
		drop2.setEditable(false);
		drop2.addItem("Conservatory");
		drop2.addItem("Kitchen");
		drop2.addItem("Ballroom");
		drop2.addItem("Billiard Room");
		drop2.addItem("Library");
		drop2.addItem("Study");
		drop2.addItem("Dining Room");
		drop2.addItem("Lounge");
		drop2.addItem("Hall");
		roomGuess.setBorder(BorderFactory.createTitledBorder("Room Guess"));
		roomGuess.add(drop2);
		add(roomGuess);
		
		JPanel weapons = new JPanel();
		JCheckBox knife = new JCheckBox("Knife");
		JCheckBox crowbar = new JCheckBox("Crowbar");
		JCheckBox candlestick = new JCheckBox("Candlestick");
		JCheckBox mace = new JCheckBox("Mace");
		JCheckBox pistol = new JCheckBox("Pistol");
		JCheckBox rope = new JCheckBox("Rope");
		weapons.setLayout(new GridLayout(3,2));
		weapons.add(knife);
		weapons.add(crowbar);
		weapons.add(candlestick);
		weapons.add(mace);
		weapons.add(pistol);
		weapons.add(rope);
		weapons.setBorder(BorderFactory.createTitledBorder("Weapons"));
		add(weapons);
		
		JPanel weaponGuess = new JPanel();
		JComboBox drop3 = new JComboBox();
		drop3.setEditable(false);
		drop3.addItem("Knife");
		drop3.addItem("Crowbar");
		drop3.addItem("Candlestick");
		drop3.addItem("Mace");
		drop3.addItem("Pistol");
		drop3.addItem("Rope");
		weaponGuess.setBorder(BorderFactory.createTitledBorder("Weapon Guess"));
		weaponGuess.add(drop3);
		add(weaponGuess);
		
	}
}
