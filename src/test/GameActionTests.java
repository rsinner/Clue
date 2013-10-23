package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import clueGame.BoardCell;
import clueGame.Card.CardType;
import clueGame.Board;
import clueGame.ClueGame;
import clueGame.ComputerPlayer;

public class GameActionTests {
	private ArrayList<clueGame.Card> solution;
	private ClueGame cg;
	private clueGame.Card weapon;
	private clueGame.Card person;
	private clueGame.Card room;


	@Before
	public void setUp(){
		// Set up a clue game to run
		cg = new ClueGame();
		// Set up a dummy solution
		solution = new ArrayList<clueGame.Card>();
		weapon = new clueGame.Card();
		weapon.setName("Pistol");
		weapon.setType(clueGame.Card.CardType.WEAPON);
		solution.add(weapon);
		person = new clueGame.Card();
		person.setName("Violet");
		person.setType(clueGame.Card.CardType.PERSON);
		solution.add(person);
		room = new clueGame.Card();
		room.setName("Ballroom");
		room.setType(clueGame.Card.CardType.ROOM);
		solution.add(room);

		cg.loadPlayers("players.txt");
		cg.loadCards("cards.txt");
	}

	@Test
	public void testGoodAccusation(){
		// Set the clue game's solution to the simple solution constructed in setup.
		cg.setSolution(solution);

		// Construct an array with the correct weapon, person, and room
		ArrayList<clueGame.Card> correctAccusation = new ArrayList<clueGame.Card>();
		correctAccusation.add(weapon);
		correctAccusation.add(person);
		correctAccusation.add(room);
		// Correct solution should yield a true return
		Assert.assertTrue(cg.checkAccusation(correctAccusation));


	}

	@Test
	public void testAccusationAllWrong(){
		cg.setSolution(solution);
		// Create incorrect weapon, person and room
		clueGame.Card weapon2 = new clueGame.Card();
		clueGame.Card person2 = new clueGame.Card();
		clueGame.Card room2 = new clueGame.Card();
		weapon2.setName("Wrong");
		weapon2.setType(clueGame.Card.CardType.WEAPON);
		person2.setName("Red");
		person2.setType(clueGame.Card.CardType.PERSON);
		room2.setName("Bowling");
		room2.setType(clueGame.Card.CardType.ROOM);
		// Construct an array with incorrect data
		ArrayList<clueGame.Card> falseAccusation = new ArrayList<clueGame.Card>();
		falseAccusation.add(weapon2);
		falseAccusation.add(person2);
		falseAccusation.add(room2);
		// Accusation is incorrect, therefore this should yield false;
		Assert.assertFalse(cg.checkAccusation(falseAccusation));		

	}

	@Test
	public void testAccusationBadRoom(){
		// Set the clue game's solution to the simple solution constructed in setup.
		cg.setSolution(solution);

		// Construct an array with the incorrect room
		ArrayList<clueGame.Card> correctAccusation = new ArrayList<clueGame.Card>();
		clueGame.Card room2 = new clueGame.Card();
		room2.setName("Bowling");
		room2.setType(clueGame.Card.CardType.ROOM);
		correctAccusation.add(weapon);
		correctAccusation.add(person);
		correctAccusation.add(room2);

		Assert.assertFalse(cg.checkAccusation(correctAccusation));


	}

	@Test
	public void testAccusationBadWeapon(){
		// Set the clue game's solution to the simple solution constructed in setup.
		cg.setSolution(solution);

		// Construct an array with incorrect weapon
		ArrayList<clueGame.Card> correctAccusation = new ArrayList<clueGame.Card>();
		clueGame.Card weapon2 = new clueGame.Card();
		weapon2.setName("Wrong");
		weapon2.setType(clueGame.Card.CardType.WEAPON);
		correctAccusation.add(weapon2);
		correctAccusation.add(person);
		correctAccusation.add(room);

		Assert.assertFalse(cg.checkAccusation(correctAccusation));


	}

	@Test
	public void testAccusationBadPerson(){
		// Set the clue game's solution to the simple solution constructed in setup.
		cg.setSolution(solution);

		// Construct an array with incorrect person
		ArrayList<clueGame.Card> correctAccusation = new ArrayList<clueGame.Card>();
		clueGame.Card person2 = new clueGame.Card();
		person2.setName("Wrong");
		person2.setType(clueGame.Card.CardType.PERSON);
		correctAccusation.add(weapon);
		correctAccusation.add(person2);
		correctAccusation.add(room);

		Assert.assertFalse(cg.checkAccusation(correctAccusation));


	}


	@Test
	public void testAddCardToSeen(){
		cg.addCardToSeen(weapon);
		// Test to make sure the data structure contains the 'seen' card.
		Assert.assertTrue(cg.getSeenCards().contains(weapon));
		// Test to make sure the card is only added once
		cg.addCardToSeen(weapon);
		Assert.assertEquals(1, cg.getSeenCards().size());
	}

	@Test
	public void testCreateSuggestion(){
		ArrayList<clueGame.Card> sugg = cg.createSuggestion(weapon, person);
		// Make sure the suggestion has 3 cards
		Assert.assertEquals(3, sugg.size());

		// Make sure there's a room, person, and weapon. Thus also testing for uniqueness.
		clueGame.Card  temp = sugg.get(0);
		Assert.assertTrue(temp.getType() == clueGame.Card.CardType.ROOM);
		temp = sugg.get(1);
		Assert.assertTrue(temp.getType() == clueGame.Card.CardType.WEAPON);
		temp = sugg.get(2);
		Assert.assertTrue(temp.getType() == clueGame.Card.CardType.PERSON);
	}

	@Test
	public void testGoodSuggestion(){
		//cg.updateSeenCards();
		
		// Make a suggestion
		ArrayList<clueGame.Card> sugg = cg.cpuMakeSuggestion();
		// Make check to see if suggestion is informative. 
		for(clueGame.Card c : sugg){
			Assert.assertFalse(cg.getSeenCards().contains(c));
		}
	}
	
	@Test
	public void testPickLocationWithRoomNotLastVisited(){
		Board board = new Board("Clue_Layout.csv", "legend.txt");
		board.loadConfigFiles();
		// The room is immediately adjacent here.
		board.calcTargets(15, 6, 1);
		Set<BoardCell> targets = board.getTargets();
		for(int i = 0; i < 100; i++){
			BoardCell expected = board.getCellAt(board.calcIndex(15, 5));
			BoardCell actual = cg.pickLocation(targets);
			Assert.assertEquals(expected, actual);
		}
		
	}
	
	@Test
	public void testPickLocationWithRoomLastVisited(){
		Board board = new Board("Clue_Layout.csv", "legend.txt");
		board.loadConfigFiles();
		// Room has been previously visited, choice should be random
		int currentPlayer =cg.getCurrentPlayer();
		if(currentPlayer<5){
			cg.getComputerPlayers().get(currentPlayer).setPreviousRoom('A');
		}
		else{
			cg.getHuman().setPreviousRoom('A');
		}
		
		board.calcTargets(15, 6, 1);
		Set<BoardCell> targets = board.getTargets();
		int chooseUp = 0;
		int chooseDown = 0;
		int chooseRoom = 0;
		int chooseRight = 0;
		
		// Call pickLocation on this same set of targets 100 times
		// Check to see that each is being chosen a sufficient number of times.
		for(int i = 0; i < 100; i++){
			BoardCell locationPicked = cg.pickLocation(targets);
			// Count the number of times each cell is chosen
			if(locationPicked.equals(board.getCellAt(board.calcIndex(14, 6)))){
				chooseUp++;
			}
			else if(locationPicked.equals(board.getCellAt(board.calcIndex(16, 6)))){
				chooseDown++;
			}
			else if(locationPicked.equals(board.getCellAt(board.calcIndex(15, 5)))){
				chooseRoom++;
			}
			else if(locationPicked.equals(board.getCellAt(board.calcIndex(15, 7)))){
				chooseRight++;
			}
		}
		// If each cell has been chosen 10 or more times, that's sufficiently random.
		Assert.assertTrue(chooseUp >= 10);
		Assert.assertTrue(chooseDown >= 10);
		Assert.assertTrue(chooseRoom >= 10);
		Assert.assertTrue(chooseRight >= 10);
	}
	
	@Test
	public void testPickLocationNoRoom(){
		Board board = new Board("Clue_Layout.csv", "legend.txt");
		board.loadConfigFiles();
		// Walkway cell surrounded by walkways, and a the closet. Now selection should be random
		board.calcTargets(10, 10, 1);
		Set<BoardCell> targets = board.getTargets();
		int chooseDown = 0;
		int chooseLeft = 0;
		int chooseRight = 0;
		
		// Call pickLocation on this same set of targets 100 times
		// Check to see that each is being chosen a sufficient number of times.
		for(int i = 0; i < 100; i++){
			BoardCell locationPicked = cg.pickLocation(targets);
			// Count the number of times each cell is chosen
			if(locationPicked.equals(board.getCellAt(board.calcIndex(11, 10)))){
				chooseDown++;
			}
			else if(locationPicked.equals(board.getCellAt(board.calcIndex(10, 9)))){
				chooseLeft++;
			}
			else if(locationPicked.equals(board.getCellAt(board.calcIndex(10, 11)))){
				chooseRight++;
			}
		}
		// If each cell has been chosen 10 or more times, that's sufficiently random.
		Assert.assertTrue(chooseDown >= 10);
		Assert.assertTrue(chooseLeft >= 10);
		Assert.assertTrue(chooseRight >= 10);
		
	}
}
