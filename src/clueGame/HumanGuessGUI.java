package clueGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HumanGuessGUI extends JDialog {
	private JButton submit, cancel;
	private JPanel rooms;
	private JTextField room;
	private JComboBox dropRoom, dropPerson, dropWeapon;
	private Board b;
	private ClueGame cg;
	
	public HumanGuessGUI(Board b, ClueGame cg,  ArrayList<String> rNames, ArrayList<String> pNames, ArrayList<String> wNames) {
		super(cg, "Make a Guess", true);
		this.b = b;
		this.cg = cg;
		
		setLayout(new GridLayout(4,1));
		setSize(250, 250);
		
		rooms = new JPanel();
		JPanel people = new JPanel();
		JPanel weapons = new JPanel();

		dropPerson = new JComboBox();
		dropPerson.setEditable(false);
		for(String s : pNames)
			dropPerson.addItem(s);

		dropWeapon = new JComboBox();
		dropWeapon.setEditable(false);
		for(String s : wNames)
			dropWeapon.addItem(s);

		dropRoom = new JComboBox();
		dropRoom.setEditable(false);
		for(String s : rNames)
			dropRoom.addItem(s);

		JPanel buttons = new JPanel();
		
		submit = new JButton();
		submit.setText("Submit");
		cancel = new JButton();
		cancel.setText("Cancel");
		
		buttons.add(submit);
		buttons.add(cancel);

		people.setLayout(new GridLayout(1,2));
		JTextField person = new JTextField();
		person.setSize(50,50);
		person.setEditable(false);
		person.setText("Person");
		people.add(person);
		people.add(dropPerson);

		weapons.setLayout(new GridLayout(1,2));
		JTextField weapon = new JTextField();
		weapon.setSize(50,50);
		weapon.setEditable(false);
		weapon.setText("Weapon");
		weapons.add(weapon);
		weapons.add(dropWeapon);

		add(rooms);
		add(people);
		add(weapons);
		add(buttons);

	}

	public void setVisible(boolean bool) {
		if (bool) {
			rooms.removeAll();
			rooms.setLayout(new GridLayout(1,2));
			JTextField yourRoom = new JTextField();
			yourRoom.setSize(50,50);
			yourRoom.setEditable(false);
			yourRoom.setText("Your Room");
			rooms.add(yourRoom);

			RoomCell current = b.getRoomCellAt(b.calcRow(cg.getHuman().getCurrentLocation()),b.calcCol(cg.getHuman().getCurrentLocation())); 
			if (current instanceof RoomCell){
				room = new JTextField();
				room.setSize(100,50);
				room.setEditable(false);
				char roomInitial = current.getInitial();
				String roomName = b.getRooms().get(roomInitial);
				room.setText(roomName);
				rooms.add(room);
			} else {
				rooms.add(dropRoom);
			}
			rooms.repaint();
			rooms.revalidate();
		}
		cg.repaint();
		cg.revalidate();
		super.setVisible(bool);
		
	}

	public JButton getSubmit() {
		return submit;
	}

	public JButton getCancel() {
		return cancel;
	}
	
	public String getRoom() {
		if(rooms.getComponentCount() > 1 && rooms.getComponent(1) == room) {
			return room.getText();
		} else {
			return dropRoom.getSelectedItem().toString();
		}
	}
	
	public String getPerson() {
		return dropPerson.getSelectedItem().toString();
	}
	
	public String getWeapon() {
		return dropWeapon.getSelectedItem().toString();
	}
}
