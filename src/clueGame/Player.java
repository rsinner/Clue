package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class Player {
	private String name;
	private ArrayList<Card> cards;
	private ArrayList<Card> seen;
	private int currentLocation;
	private String color;
	private char previousRoom;
	private char currentRoom;
	protected Color c;
	private static final int CIRCLE_DIMENSION = 28;
	
	public Player() {
		super();
		cards = new ArrayList<Card>();	
		seen = new ArrayList<Card>();
		previousRoom = currentRoom = 'W';
	}
	
	public void initializePlayer(){
		if(name.equals("Colonel Mustard")){
			this.c = Color.blue;
		}
		else if(name.equals("Professor Plum")){
			this.c = Color.MAGENTA;
		}
		else if(name.equals("Violet")){
			this.c = Color.pink;
		}
		else if(name.equals("Miss Scarlett")){
			this.c = Color.RED;
		}
		else if(name.equals("Mr. Green")){
			this.c = Color.green;
		}
		else if(name.equals("Human")){
			this.c = Color.black;
		}
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
	
	public void updateSeen(Card newCard) {
		if(!seen.contains(newCard))
			seen.add(newCard);
		System.out.println(getName() + "::" + seen.toString());
	}

	public ArrayList<Card> getSeen() {
		return seen;
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
	
	public void setCurrentLocation(int start){
		this.currentLocation = start;
	}
	
	public int getCurrentLocation(){
		return currentLocation;
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
	
	public void makeMove(Set<BoardCell> targets, Board b) {
		
	}
	
	public void draw(Graphics g, Board c) {
		g.setColor(this.c);
		int x = c.getCellAt(currentLocation).getRow();
		
		int y = c.getCellAt(currentLocation).getColumn();
		
		g.drawOval(y*CIRCLE_DIMENSION, x*CIRCLE_DIMENSION, CIRCLE_DIMENSION, CIRCLE_DIMENSION);
		g.fillOval(y*CIRCLE_DIMENSION, x*CIRCLE_DIMENSION, CIRCLE_DIMENSION, CIRCLE_DIMENSION);
	}
	
	public void drawArc(Graphics g, Board c) {
		g.setColor(this.c);
		int x = c.getCellAt(currentLocation).getRow();
		
		int y = c.getCellAt(currentLocation).getColumn();
		
		g.drawArc(y*CIRCLE_DIMENSION, x*CIRCLE_DIMENSION, CIRCLE_DIMENSION, CIRCLE_DIMENSION, 0, 180);
		g.fillArc(y*CIRCLE_DIMENSION, x*CIRCLE_DIMENSION, CIRCLE_DIMENSION, CIRCLE_DIMENSION, 0, 180);
	}
}
