package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.junit.runner.Computer;

import clueGame.Card.CardType;

public class ClueGame {
	private ArrayList<Card> deck;
	private ArrayList<ComputerPlayer> computerPlayers;
	private HumanPlayer human;
	private int currentPlayer = 0;
	private static final int NUM_CARDS = 21;
	private ArrayList<Card> solution;
	private Set<Card> seenCards;
	
	
	
	public ClueGame() {
		super();
		this.seenCards = new HashSet<Card>();
	}

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
//	public void fakeLoadCards(){
//		deck = new ArrayList<Card>();
//		for(int i = 0; i < NUM_CARDS; i++){
//			Card c1 = new Card();
//			c1.setName("TestCard" + String.valueOf(i));
//			c1.setType(Card.CardType.WEAPON);
//			deck.add(c1);
//		}
//	}
	
	public void loadCards(String fileName) {
		deck = new ArrayList<Card>();
		try {
			FileReader reader = new FileReader(fileName);
			Scanner scan = new Scanner(reader);
			while(scan.hasNextLine()) {
				String temp = scan.nextLine();
				String[] rows = temp.split(",");
				//Human player is at the bottom of the text file. If no next line, human
					Card card = new Card();
					card.setName(rows[0]);
					String type = rows[1];
					if(type.equals("PLAYER")) {
						card.setType(CardType.PERSON);
					} else if(type.equals("ROOM")) {
						card.setType(CardType.ROOM);
					} else if(type.equals("WEAPON")) {
						card.setType(CardType.WEAPON);
					}
				deck.add(card);
				}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void shuffleCards(){
		ArrayList<Card> tempDeck = (ArrayList<Card>) deck.clone();
		ArrayList<Card> shuffledDeck = new ArrayList<Card>();
		int arraySize = tempDeck.size();
		for(int i = 0; i<arraySize; i++){
			int randomIndex = generateRandomNumber(tempDeck.size());
			shuffledDeck.add(tempDeck.get(randomIndex));
			tempDeck.remove(randomIndex);
		}
		deck = (ArrayList<Card>) shuffledDeck.clone();
	}
	
	public int generateRandomNumber(int i) {
		Random generator = new Random();
		return generator.nextInt(i);
	}

	public void dealCards(){
		
		// Deal the 3 solution cards first
		dealSolution();
	
		// For every deck in the card, deal it to somebody depending on who the current player is.
		// Created an iterator so we can modify deck WHILE we're iterating through it.
		Iterator<Card> it = deck.iterator();
		while(it.hasNext()){
			switch(currentPlayer){
			case 0: 
					computerPlayers.get(0).getCards().add(it.next());
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
	
	

	public void dealSolution() {
		solution = new ArrayList<Card>();
		// Need to clone the array so that we can have 2 iterators working on the same data,
		// but in a different stream.
		ArrayList<Card>	buffer = (ArrayList<Card>) deck.clone();
		Iterator<Card> i = buffer.iterator();
		boolean haveWeapon = false;
		boolean havePerson = false;
		boolean haveRoom = false;
		while(i.hasNext()){
			
			Card temp = i.next();
			if(temp.getType()== Card.CardType.WEAPON && !haveWeapon){
				solution.add(temp);
				haveWeapon = true;
				deck.remove(temp);
			}
			else if(temp.getType()== Card.CardType.PERSON && !havePerson){
				solution.add(temp);
				havePerson = true;
				deck.remove(temp);
			}
			else if(temp.getType()== Card.CardType.ROOM && !haveRoom){
				solution.add(temp);
				haveRoom = true;
				deck.remove(temp);
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

	public boolean checkAccusation(ArrayList<Card> accusation) {
		for(Card acc : accusation){
			for(Card soln : solution){
				if(acc.getType() == soln.getType() && !acc.equals(soln)){
					return false;
				}
			}
		}
		return true;
	}

	public void addCardToSeen(Card seenCard){
		seenCards.add(seenCard);
	}
	
	// setter for testing purposes
	public void setSolution(ArrayList<Card> solution) {
		this.solution = solution;
	}
	// for testing
	public ArrayList<Card> getSolution() {
		return solution;
	}
	// for testing
	public Set<Card> getSeenCards() {
		return seenCards;
	}

	public ArrayList<Card> createSuggestion(Card weaponCard, Card personCard) {
		ArrayList<Card> suggestion = new ArrayList<Card>();
		if(currentPlayer < 5){
			ComputerPlayer cp = computerPlayers.get(currentPlayer);
			String room = cp.getCurrentRoom();
			Card roomCard = new Card();
			roomCard.setName(room);
			roomCard.setType(clueGame.Card.CardType.ROOM);
			suggestion.add(roomCard);
		}
		else{
			HumanPlayer hp = human;
			String room = human.getCurrentRoom();
			Card roomCard = new Card();
			roomCard.setName(room);
			roomCard.setType(clueGame.Card.CardType.ROOM);
			suggestion.add(roomCard);
		}
		
		suggestion.add(weaponCard);
		suggestion.add(personCard);
		
		return suggestion;
	}
	
	
	
	
}
