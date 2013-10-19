package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ClueGame {
	private ArrayList<Card> deck;
	private ArrayList<ComputerPlayer> computerPlayers;
	private HumanPlayer human;
	private int currentPlayer = 0;
	
	
	public void loadPlayers(String fileName) {
		computerPlayers = new ArrayList<ComputerPlayer>();
		try {
			FileReader reader = new FileReader(fileName);
			Scanner scan = new Scanner(reader);
			while(scan.hasNextLine()) {
				String temp = scan.nextLine();
				String[] rows = temp.split(",");
				//Human player is at the bottom of the text file. If no next line, human
				if(!scan.hasNextLine()) {
					human = new HumanPlayer();
					human.setName(rows[0]);
					human.setStartingLocation(Integer.parseInt(rows[1]));
				}
				else {
					ComputerPlayer cp = new ComputerPlayer();
					cp.setName(rows[0]);
					cp.setStartingLocation(Integer.parseInt(rows[1]));
					computerPlayers.add(cp);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void fakeLoadCards(){
		deck = new ArrayList<Card>();
		Card c1 = new Card();
		c1.setName("TestCard1");
		c1.setType(Card.CardType.WEAPON);
		deck.add(c1);
		Card c2 = new Card();
		c2.setName("TestCard2");
		c2.setType(Card.CardType.WEAPON);
		deck.add(c2);
		Card c3 = new Card();
		c3.setName("TestCard2");
		c3.setType(Card.CardType.WEAPON);
		deck.add(c3);
		Card c4 = new Card();
		c4.setName("TestCard2");
		c4.setType(Card.CardType.WEAPON);
		deck.add(c4);
		Card c5 = new Card();
		c5.setName("TestCard2");
		c5.setType(Card.CardType.WEAPON);
		deck.add(c5);
//		Card c4 = new Card();
//		c4.setName("TestCard2");
//		c4.setType(Card.CardType.WEAPON);
//		deck.add(c4);
		
		
	}
	
	public void dealCards(){
		
	}
	
	public ArrayList<ComputerPlayer> getComputerPlayers(){
		return computerPlayers;
	}
	public HumanPlayer getHuman() {
		return human;
	}
	
	public ArrayList<Card> getDeck(){
		return deck;
	}
	
	
}
