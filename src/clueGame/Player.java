package clueGame;

import java.util.ArrayList;
import java.util.Random;

public class Player {
	private String name;
	private ArrayList<Card> cards;
	private int startingLocation;
	private String color;
	private char previousRoom;
	private char currentRoom;

	public Player() {
		super();
		cards = new ArrayList<Card>();	
		previousRoom = currentRoom = 'W';
	}
	
	public int generateRandomNumber(int i) {
		Random generator = new Random();
		return generator.nextInt(i);
	}

	public Card disproveSuggestion(ArrayList<Card> suggestion) {
		ArrayList<Card> result = new ArrayList<Card>();
			for(Card card : cards) {
				if(suggestion.contains(card))
					result.add(card);
			}
		
		if(result.size() > 0) {
				return result.get(generateRandomNumber(result.size()));
		} else return null;
	}

	// For Testing purposes
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public void setStartingLocation(int start){
		this.startingLocation = start;
	}
	
	public int getStartingLocation(){
		return startingLocation;
	}
	
	public char getPreviousRoom() {
		return previousRoom;
	}

	public void setPreviousRoom(char previousRoom) {
		this.previousRoom = previousRoom;
	}

	public char getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(char currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}
