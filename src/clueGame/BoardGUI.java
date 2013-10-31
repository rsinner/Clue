package clueGame;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

//ClueGame GUI that:
//(5) Displays the grid of rooms, including room names
//(5) Includes an indication of door direction
//(5) Displays the players in their appropriate starting locations with correct colors
//(4) Includes a file menu that includes exit and show notes
//(4) Has normal JFrame functionality (set visible, set default close, has a title)

public class BoardGUI extends JFrame {
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem close;
	
	public BoardGUI() {
		setSize(new Dimension(600, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue Game");
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menuBar.add(menu);
		add(menuBar);
		
	}
	

}
