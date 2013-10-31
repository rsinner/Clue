package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	private String name;
	private ArrayList<Card> cards;
	private int startingLocation;
	private String color;
	private char previousRoom;
	private char currentRoom;
	protected Color c;
	private static final int CIRCLE_DIMENSION = 28;

	
	public Player() {
		super();
		cards = new ArrayList<Card>();	
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
			this.c = Color.CYAN;
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
	
	public void draw(Graphics g, Board c) {
		// Set room color to gray
		g.setColor(this.c);
		int x = c.getCellAt(startingLocation).getRow();
		
		int y = c.getCellAt(startingLocation).getColumn();
		
		g.drawOval(y*CIRCLE_DIMENSION, x*CIRCLE_DIMENSION, CIRCLE_DIMENSION, CIRCLE_DIMENSION);
		g.fillOval(y*CIRCLE_DIMENSION, x*CIRCLE_DIMENSION, CIRCLE_DIMENSION, CIRCLE_DIMENSION);
		// The commented line below will fill the grid with color. Makes it hard to count cells.
		//g.fillRect(getColumn()*CELL_SIZE, getRow()*CELL_SIZE, getDimension(), getDimension());
		// Since 1 inch ~ 72 units, each is 36x36 units.
		//g.drawRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE,getDimension(), getDimension());
	}
	
}
