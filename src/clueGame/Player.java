package clueGame;

import java.util.ArrayList;

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

	public void disproveSuggestion() {
		
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
