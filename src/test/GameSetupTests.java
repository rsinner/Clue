package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.smartcardio.Card;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import clueGame.ClueGame;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;

public class GameSetupTests {
	
	private static ClueGame cg;
	
	@Before
	public void setup(){
		cg = new ClueGame();
		cg.loadPlayers("players.txt");
	}
	
	
//testNumPCPlayers, testPCPlayerAttributes, testStartingLocations, and testHumanPlayerAttributes are all designed to test loadPlayers.
	@Test
	public void testNumberPCPlayers() {
		ArrayList<ComputerPlayer> forTesting = cg.getComputerPlayers();
		// Make sure all 3 players are loaded into the array
		Assert.assertEquals(5, forTesting.size());
		// Make sure the correct 3 PC players are in the array
		Assert.assertEquals("Colonel Mustard", forTesting.get(0).getName());
		Assert.assertEquals("Professor Plum", forTesting.get(1).getName());
		Assert.assertEquals("Violet", forTesting.get(2).getName());
		Assert.assertEquals("Miss Scarlett", forTesting.get(3).getName());
		Assert.assertEquals("Mr. Green", forTesting.get(4).getName());
		
	}
	
	@Test
	public void testPCPlayerAttributes(){
		ArrayList<ComputerPlayer> forTesting = cg.getComputerPlayers();
		for(ComputerPlayer cp : forTesting){
			// Make sure the pc player is instantiated
			Assert.assertTrue(cp != null);
			// Make sure card list is instantiated
			Assert.assertTrue(cp.getCards() != null);
			// Make sure name is set
			Assert.assertTrue(cp.getName() != null);
		}
	}
	
	@Test
	public void testHumanPlayerAttributes(){
		HumanPlayer hp = cg.getHuman();
		// Make sure the human is instantiated
		Assert.assertTrue(hp!=null);
		// Make sure the human's cards list is instantiated
		Assert.assertTrue(hp.getCards() != null);
		// Make sure the human's name has been set
		Assert.assertTrue(hp.getName() != null);
	}
	
	@Test 
	public void testStartingLocations(){
		ArrayList<ComputerPlayer> forTesting = cg.getComputerPlayers();
		// Make sure colonel mustard's start pos is 5
		Assert.assertEquals(5, forTesting.get(0).getStartingLocation());
		// Make sure prof plum's start pos is 114
		Assert.assertEquals(114, forTesting.get(1).getStartingLocation());
		// Make sure violet's start pos is 355
		Assert.assertEquals(355, forTesting.get(2).getStartingLocation());
		// Make sure scarlett's starting pos is 13
		Assert.assertEquals(13, forTesting.get(3).getStartingLocation());
		// Make sure mr green's starting pos is 349
		Assert.assertEquals(349, forTesting.get(4).getStartingLocation());
		// Make sure human's starting position is 10
		HumanPlayer hp = cg.getHuman();
		Assert.assertEquals(10, hp.getStartingLocation());
		
	}
	
	@Test
	public void testDeckAfterDeal(){
		cg.fakeLoadCards();
		cg.dealCards();
		// After all cards have been dealt, the deck should be empty.
		ArrayList<clueGame.Card> deck = cg.getDeck();
		Assert.assertEquals(0, deck.size());
	}
	
	@Test
	public void testNumberOfPlayerCards(){
		ArrayList<ComputerPlayer> cpuPlayers = cg.getComputerPlayers();
		// Check that all computer players have 4 cards, +- 1 since 21/ 5 = 4 with 1 left over
		for(ComputerPlayer cp : cpuPlayers){
			Assert.assertEquals(4, cp.getCards().size(), 1);
		}
		// Check to make sure human has 4 +- 1 cards.
		Assert.assertEquals(4, cg.getHuman().getCards().size(), 1);
	}

}