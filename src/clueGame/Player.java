package clueGame;

import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Card> cards;
	private int startingLocation;
	private String color;
	private String previousRoom;
	private String currentRoom;

	public Player() {
		super();
		cards = new ArrayList<Card>();	
		previousRoom = currentRoom = "Walkway";
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

	
	public void setStartingLocation(int start){
		this.startingLocation = start;
	}
	
	public int getStartingLocation(){
		return startingLocation;
	}
	
	public String getPreviousRoom() {
		return previousRoom;
	}

	public void setPreviousRoom(String previousRoom) {
		this.previousRoom = previousRoom;
	}

	public String getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(String currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}
