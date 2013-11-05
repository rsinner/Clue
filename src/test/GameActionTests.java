package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import clueGame.BoardCell;
import clueGame.Card;
import clueGame.Card.CardType;
import clueGame.Board;
import clueGame.ClueGame;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;

public class GameActionTests {
	private ArrayList<clueGame.Card> solution;
	private ClueGame cg;
	private clueGame.Card weapon;
	private clueGame.Card person;
	private clueGame.Card room;
	private ArrayList<Card> premadeCards;

	private static final Card MUSTARD_CARD = new Card("Colonel Mustard", Card.CardType.PERSON);
	private static final Card GREEN_CARD = new Card("Mr. Green", Card.CardType.PERSON);
	private static final Card VIOLET_CARD = new Card("Violet", Card.CardType.PERSON);
	private static final Card PLUM_CARD = new Card("Plum", Card.CardType.PERSON);
	private static final Card SCARLETT_CARD = new Card("Miss Scarlett", Card.CardType.PERSON);
	private static final Card HUMAN_CARD = new Card("Human", Card.CardType.PERSON);

	private static final Card KITCHEN_CARD = new Card("Kitchen", Card.CardType.ROOM);
	private static final Card BALLROOM_CARD = new Card("Ballroom", Card.CardType.ROOM);
	private static final Card WALKWAY_CARD = new Card("Walkway", Card.CardType.ROOM);
	private static final Card CONSERVATORY_CARD = new Card("Conservatory", Card.CardType.ROOM);
	private static final Card BILLIARD_CARD = new Card("Billiard Room", Card.CardType.ROOM);
	private static final Card LIBRARY_CARD = new Card("Library", Card.CardType.ROOM);
	private static final Card STUDY_CARD = new Card("Study", Card.CardType.ROOM);
	private static final Card DINING_CARD = new Card("Dining Room", Card.CardType.ROOM);
	private static final Card LOUNGE_CARD = new Card("Lounge", Card.CardType.ROOM);
	private static final Card HALL_CARD = new Card("Hall", Card.CardType.ROOM);

	private static final Card KNIFE_CARD = new Card("Knife", Card.CardType.WEAPON);
	private static final Card CROWBAR_CARD = new Card("Crowbar", Card.CardType.WEAPON);
	private static final Card ROPE_CARD = new Card("Rope", Card.CardType.WEAPON);
	private static final Card CANDLE_CARD = new Card("Candlestick", Card.CardType.WEAPON);
	private static final Card PISTOL_CARD = new Card("Pistol", Card.CardType.WEAPON);
	private static final Card MACE_CARD = new Card("Mace", Card.CardType.WEAPON);


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

		premadeCards = new ArrayList<Card>() {{
			add(MUSTARD_CARD);
			add(GREEN_CARD);
			add(BALLROOM_CARD);
			add(KITCHEN_CARD);
			add(CROWBAR_CARD);
			add(ROPE_CARD);
		}};


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
		cg.getComputerPlayers().get(3).updateSeen(weapon);
		// Test to make sure the data structure contains the 'seen' card.
		Assert.assertTrue(cg.getComputerPlayers().get(3).getSeen().contains(weapon));
		// Test to make sure the card is only added once
		cg.getComputerPlayers().get(3).updateSeen(weapon);
		Assert.assertEquals(1, cg.getComputerPlayers().get(3).getSeen().size());
	}

	@Test
	public void testCreateSuggestion(){
		cg.setCurrentPlayer(2);
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
		cg.setCurrentPlayer(2);
		ArrayList<clueGame.Card> sugg = cg.cpuMakeSuggestion();
		// Make check to see if suggestion is informative. 
		for(clueGame.Card c : sugg){
			for (int i = 0; i < cg.getComputerPlayers().size(); i++) {
				Assert.assertFalse(cg.getComputerPlayers().get(i).getSeen().contains(c));
			}
		}
	}

	@Test
	public void testDisproveSuggestionOneCard(){
		ComputerPlayer cp = cg.getComputerPlayers().get(0);
		cp.setCards(premadeCards);
		ArrayList<Card> sugg;
		Card result;
		//correct person is returned
		cg.setCurrentPlayer(2);
		sugg = cg.createSuggestion(CANDLE_CARD, GREEN_CARD);
		result = cg.setupDisproveSuggestion(sugg, null);
		Assert.assertEquals(GREEN_CARD, result);
		//Correct weapon is returned
		sugg = cg.createSuggestion(CROWBAR_CARD, VIOLET_CARD);
		result = cg.setupDisproveSuggestion(sugg, null);
		Assert.assertEquals(CROWBAR_CARD, result);
		//Correct room is returned
		sugg = cg.createSuggestion(CANDLE_CARD, VIOLET_CARD);
		result = cg.setupDisproveSuggestion(sugg, null);
		Assert.assertEquals(null, result);
		//Correct room is returned, since it is the only option that is the same.
		cp.setCards(new ArrayList<Card>() {{
			add(WALKWAY_CARD);
			add(CANDLE_CARD);
			add(GREEN_CARD);
		}});
		sugg = cg.createSuggestion(CROWBAR_CARD, VIOLET_CARD);
		result = cg.setupDisproveSuggestion(sugg, null);
		Assert.assertEquals(WALKWAY_CARD, result);

	}

	@Test
	public void testDisproveSuggestionTwoCards(){
		ComputerPlayer cp = cg.getComputerPlayers().get(0);
		cp.setCards(new ArrayList<Card>() {{
			add(WALKWAY_CARD);
			add(CANDLE_CARD);
			add(GREEN_CARD);
		}});

		ArrayList<Card> sugg;
		Card result;
		int candleCounter = 0, greenCounter = 0, walkwayCounter = 0;
		cg.setCurrentPlayer(2);
		sugg = cg.createSuggestion(CANDLE_CARD, GREEN_CARD);
		//make sure that each result comes up more than once so it really is random
		for(int i = 0; i<50; i++) {
			result = cg.setupDisproveSuggestion(sugg, null);
			if(result.equals(CANDLE_CARD))
				candleCounter++;
			else if(result.equals(GREEN_CARD))
				greenCounter++;
			else if(result.equals(WALKWAY_CARD))
				walkwayCounter++;
		}
		Assert.assertTrue(candleCounter>0);
		Assert.assertTrue(greenCounter>0);
		Assert.assertTrue(walkwayCounter>0);
	}

	@Test
	public void testPlayersQueriedInOrder() {
		//SOLUTION: CANDLE, GREEN, WALKWAY for testing purposes only
		ArrayList<Card> sugg;
		ComputerPlayer cp1 = cg.getComputerPlayers().get(0);
		cp1.setCards(new ArrayList<Card>() {{
			add(BILLIARD_CARD);
			add(MACE_CARD);
			add(SCARLETT_CARD);
		}});
		ComputerPlayer cp2 = cg.getComputerPlayers().get(1);
		cp2.setCards(new ArrayList<Card>() {{
			add(LIBRARY_CARD);
			add(MACE_CARD);
			add(VIOLET_CARD);
		}});
		ComputerPlayer cp3 = cg.getComputerPlayers().get(2);
		cp3.setCards(new ArrayList<Card>() {{
			add(KITCHEN_CARD);
			add(ROPE_CARD);
			add(MUSTARD_CARD);
		}});
		ComputerPlayer cp4 = cg.getComputerPlayers().get(3);
		cp4.setCards(new ArrayList<Card>() {{
			add(HUMAN_CARD);
			add(CROWBAR_CARD);
			add(DINING_CARD);
		}});
		ComputerPlayer cp5 = cg.getComputerPlayers().get(4);
		cp5.setCards(new ArrayList<Card>() {{
			add(PLUM_CARD);
			add(CONSERVATORY_CARD);
			add(BALLROOM_CARD);
		}});
		HumanPlayer h = cg.getHuman();
		h.setCards(new ArrayList<Card>() {{
			add(PISTOL_CARD);
			add(STUDY_CARD);
			add(KNIFE_CARD);
		}});
		cg.setCurrentPlayer(2);
		sugg = cg.createSuggestion(CANDLE_CARD, GREEN_CARD);
		//ok to put null to check to make sure no one returns anything
		Card result = cg.setupDisproveSuggestion(sugg, null);
		Assert.assertEquals(null, result);
		//only human could disprove
		sugg = cg.createSuggestion(PISTOL_CARD, GREEN_CARD);
		result = cg.setupDisproveSuggestion(sugg, null);
		Assert.assertEquals(PISTOL_CARD, result);
		//Make sure user cannot give result
		sugg = cg.createSuggestion(PISTOL_CARD, GREEN_CARD);
		result = cg.setupDisproveSuggestion(sugg, h);
		Assert.assertEquals(null, result);
		//more than one person can disprove
		//Mace should be returned since it is the first one that can disprove
		sugg = cg.createSuggestion(MACE_CARD, PLUM_CARD);
		result = cg.setupDisproveSuggestion(sugg, cp1);
		Assert.assertEquals(MACE_CARD, result);
		//person furthest from the user - human disproves c1
		sugg = cg.createSuggestion(PISTOL_CARD, GREEN_CARD);
		result = cg.setupDisproveSuggestion(sugg, cp1);
		Assert.assertEquals(PISTOL_CARD, result);

	}

	@Test
	public void testHumanDisprove() {
		//Make sure humans can disprove
		HumanPlayer h = cg.getHuman();
		h.setCards(premadeCards);
		cg.setCurrentPlayer(2);
		ArrayList<Card> sugg = cg.createSuggestion(CROWBAR_CARD, VIOLET_CARD);
		Card result = cg.setupDisproveSuggestion(sugg, null);
		Assert.assertEquals(CROWBAR_CARD, result);
	}

	@Test
	public void testCurrentPlayerNoCardReturn() {
		//set so player has specific card. Should return null
		ComputerPlayer cp = cg.getComputerPlayers().get(0);
		cp.setCards(new ArrayList<Card>() {{
			add(WALKWAY_CARD);
			add(CANDLE_CARD);
			add(GREEN_CARD);
		}});
		cg.setCurrentPlayer(2);
		ArrayList<Card> sugg = cg.createSuggestion(CANDLE_CARD, GREEN_CARD);
		Card result = cg.setupDisproveSuggestion(sugg, cp);
		Assert.assertTrue(result == null);

	}

	@Test
	public void testPickLocationWithRoomNotLastVisited(){
		Board board = new Board("Clue_Layout.csv", "legend.txt");
		board.loadConfigFiles();
		// The room is immediately adjacent here.
		board.calcTargets(15, 6, 1);
		Set<BoardCell> targets = board.getTargets();
		for(int i = 0; i < 100; i++){
			cg.getComputerPlayers().get(i%5).setPreviousRoom('D');
			BoardCell expected = board.getCellAt(board.calcIndex(15, 5));
			BoardCell actual = cg.getComputerPlayers().get(i%5).pickLocation(targets);
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
			System.out.println(cg.getComputerPlayers().get(currentPlayer).getPreviousRoom());
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
			BoardCell locationPicked = cg.getComputerPlayers().get(currentPlayer).pickLocation(targets);
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
		System.out.println("UP: " + chooseUp + ":: DOWN: "+chooseDown+":: ROOM: "+chooseRoom+":: RIGHT: "+chooseRight);
		Assert.assertTrue(chooseUp >= 10);
		Assert.assertTrue(chooseDown >= 10);
		Assert.assertTrue(chooseRoom == 0);
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
			BoardCell locationPicked = cg.getComputerPlayers().get(i%5).pickLocation(targets);
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
