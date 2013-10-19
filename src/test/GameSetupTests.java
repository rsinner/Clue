package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	
	
//testNumPCPlayers, testPCPlayerAttributes, and testHumanPlayerAttributes are all designed to test loadPlayers.
	@Test
	public void testNumberPCPlayers() {
		ArrayList<ComputerPlayer> forTesting = cg.getComputerPlayers();
		// Make sure all 3 players are loaded into the array
		Assert.assertEquals(3, forTesting.size());
		// Make sure the correct 3 PC players are in the array
		Assert.assertEquals("Colonel Mustard", forTesting.get(0).getName());
		Assert.assertEquals("Professor Plum", forTesting.get(1).getName());
		Assert.assertEquals("Violet", forTesting.get(2).getName());
		
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
		// Make sure prof plum's start pos is 10
		Assert.assertEquals(90, forTesting.get(1).getStartingLocation());
		// Make sure violet's start pos is 313
		Assert.assertEquals(313, forTesting.get(2).getStartingLocation());
		// Make sure human's starting position is 10
		HumanPlayer hp = cg.getHuman();
		Assert.assertEquals(10, hp.getStartingLocation());
		
	}

}
