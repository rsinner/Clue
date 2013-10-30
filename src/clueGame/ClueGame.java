package clueGame;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;

import org.junit.runner.Computer;

import clueGame.Card.CardType;

public class ClueGame extends JFrame{
	private ArrayList<Card> deck;
	private ArrayList<Card> listOfCards;
	private ArrayList<ComputerPlayer> computerPlayers;
	private HumanPlayer human;
	private int currentPlayer = 0;
	private static final int NUM_CARDS = 21;
	private ArrayList<Card> solution;
	private Set<Card> seenCards;
	private Board board;
	
	
	
	// WE NEED A WAY TO INCREMENT CURRENT PLAYER, CONSISTENTLY, AFTER EACH TURN. Specifically for
	// createSuggestion to function.
	// I'm hoping that during the progression of programming this, we'll be forced to write a 
	// take turn function or something.
	
	public ClueGame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue Game");
		setSize(1000, 1000);
		this.seenCards = new HashSet<Card>();
		board = new Board("Clue_Layout.csv", "legend.txt");
		board.loadConfigFiles();
		add(board, BorderLayout.CENTER);
		loadPlayers("players.txt");
		board.updatePlayers(computerPlayers, human);
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
					human.setColor(rows[2]);
					human.initializePlayer();
				}
				else {
					ComputerPlayer cp = new ComputerPlayer();
					cp.setName(rows[0]);
					cp.setStartingLocation(Integer.parseInt(rows[1]));
					cp.setColor(rows[2]);
					cp.initializePlayer();
					computerPlayers.add(cp);
					
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadCards(String fileName) {
		deck = new ArrayList<Card>();
		listOfCards = new ArrayList<Card>();
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
			shuffleCards();
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
		listOfCards = (ArrayList<Card>) shuffledDeck.clone();
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
	


	public ArrayList<Card> createSuggestion(Card weaponCard, Card personCard) {
		ArrayList<Card> suggestion = new ArrayList<Card>();
		if(currentPlayer < 5){
			ComputerPlayer cp = computerPlayers.get(currentPlayer);
			String room = board.getRooms().get(cp.getCurrentRoom());
			Card roomCard = new Card();
			roomCard.setName(room);
			roomCard.setType(clueGame.Card.CardType.ROOM);
			suggestion.add(roomCard);
		}
		else{
			HumanPlayer hp = human;
			String room = board.getRooms().get(human.getCurrentRoom());
			Card roomCard = new Card();
			roomCard.setName(room);
			roomCard.setType(clueGame.Card.CardType.ROOM);
			suggestion.add(roomCard);
		}
		
		suggestion.add(weaponCard);
		suggestion.add(personCard);
		
		return suggestion;
	}

	public ArrayList<Card> cpuMakeSuggestion() {
		Card weapon = new Card();
		Card person = new Card();
		for(Card c : listOfCards){
			if(c.getType() == clueGame.Card.CardType.WEAPON){
				if(!seenCards.contains(c)){
					weapon = c;
				}
			}
			else if(c.getType() == clueGame.Card.CardType.PERSON){
				if(!seenCards.contains(c)){
					person = c;
				}
			}
		}
		return createSuggestion(weapon, person);
		
	}
	
	public Card setupDisproveSuggestion(ArrayList<Card> suggestion, Player currentPlayer) {
		//make a list of all players. remove current player. Iterate
		ArrayList<Player> allPlayers = (ArrayList<Player>) computerPlayers.clone();
		allPlayers.add(human);
		allPlayers.remove(currentPlayer);
		Card result = null;
		for(Player p : allPlayers) {
			//Only returns one card, handles in disproveSuggestion
			result = p.disproveSuggestion(suggestion);
			if(result != null)
				break;
		}
		return result;
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		for(BoardCell bc : targets){
			// If it's a room cell, check to see if it was most recently visited
			if(bc instanceof RoomCell){
				RoomCell rc = (RoomCell) bc;
				if(currentPlayer < 5){
					if(computerPlayers.get(currentPlayer).getPreviousRoom() != rc.getInitial()){
						// Not most recently visited, cpu will select this room.
						return bc;
					}
				}
			}
		}
		
		// Either there were no rooms or the room was previously visited
		// Generate a random number  to choose the cell to pick.
		int randomRoom = generateRandomNumber(targets.size());
		Object[] targArray = targets.toArray();
		return (BoardCell) targArray[randomRoom];
		//return null;
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

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	// For Testing
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	public static void main(String[] args){
		ClueGame gui = new ClueGame();
		gui.setVisible(true);
		ControlGUI controlGui = new ControlGUI();
		controlGui.setVisible(true);
		DetectiveNotesDialogue dialogue = new DetectiveNotesDialogue();
		dialogue.setVisible(true);
	}
	
}
