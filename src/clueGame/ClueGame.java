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

// Annie, please remove this when you write the real function.
// Also, fix the method call to this in the tests to your proper load function,
// or load deck in the @Before method.
	public void fakeLoadCards(){
		deck = new ArrayList<Card>();
		for(int i = 0; i < 21; i++){
			Card c1 = new Card();
			c1.setName("TestCard1");
			c1.setType(Card.CardType.WEAPON);
			deck.add(c1);
		}
		
		
		
	}
	
	public void dealCards(){
		// For every deck in the card, deal it to somebody depending on who the current player is.
		Iterator<Card> it = deck.iterator();
		while(it.hasNext()){
			switch(currentPlayer){
			case 0: computerPlayers.get(0).getCards().add(it.next());
					it.remove();
					currentPlayer++;
					break;
			case 1: computerPlayers.get(1).getCards().add(it.next());
					it.remove();
					currentPlayer++;
					break;
			case 2: computerPlayers.get(2).getCards().add(it.next());
					it.remove();
					currentPlayer++;
					break;
			case 3: computerPlayers.get(3).getCards().add(it.next());
					it.remove();
					currentPlayer++;
					break;
			case 4: computerPlayers.get(4).getCards().add(it.next());
					it.remove();
					currentPlayer++;
					break;
			case 5: human.getCards().add(it.next());
					it.remove();
					currentPlayer = 0;
					break;
			}
		}
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
