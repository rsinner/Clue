package clueGame;

import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Card> cards;
	private int startingLocation;

	public Player() {
		super();
		cards = new ArrayList<Card>();
		
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
	
	
	
	
}
