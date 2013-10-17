package test;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class AdjTests {
	private static Board board;
	@BeforeClass
	public static void setUp() {
		board = new Board("Clue_Layout.csv", "legend.txt");
		board.loadConfigFiles();
		board.calcAdjacencies();

	}

	@Test
	public void testAdjInRooms()
	{
		LinkedList<Integer> roomtest = board.getAdjList(board.calcIndex(13, 14));
		System.out.println(roomtest);
		Assert.assertEquals(0, roomtest.size());
		roomtest = board.getAdjList(board.calcIndex(10, 15));
		Assert.assertEquals(0, roomtest.size());
		roomtest = board.getAdjList(board.calcIndex(12, 1));
		Assert.assertEquals(0, roomtest.size());
		roomtest = board.getAdjList(board.calcIndex(8, 1));
		Assert.assertEquals(0, roomtest.size());
		roomtest = board.getAdjList(board.calcIndex(4, 3));
		Assert.assertEquals(0, roomtest.size());
	}

	@Test
	public void testAdjExit()
	{
		//Going Down
		LinkedList<Integer> testexit = board.getAdjList(board.calcIndex(4, 2));
		Assert.assertEquals(1, testexit.size());
		Assert.assertTrue(testexit.contains(board.calcIndex(5, 2)));
		//Going Left
		testexit = board.getAdjList(board.calcIndex(9, 14));
		Assert.assertEquals(1, testexit.size());
		Assert.assertTrue(testexit.contains(board.calcIndex(9, 13)));
		//Going Right
		testexit = board.getAdjList(board.calcIndex(7, 3));
		Assert.assertEquals(1, testexit.size());
		Assert.assertTrue(testexit.contains(board.calcIndex(7, 4)));
		//Going Up
		testexit = board.getAdjList(board.calcIndex(13, 10));
		Assert.assertEquals(1, testexit.size());
		Assert.assertTrue(testexit.contains(board.calcIndex(12, 10)));
	}

	@Test
	public void testAdjDoorways()
	{
		//Doorway Right
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(15, 6));
		Assert.assertTrue(testList.contains(board.calcIndex(15, 5)));
		Assert.assertTrue(testList.contains(board.calcIndex(15, 7)));
		Assert.assertTrue(testList.contains(board.calcIndex(14, 6)));
		Assert.assertTrue(testList.contains(board.calcIndex(16, 6)));
		Assert.assertEquals(4, testList.size());
		//Doorway Left
		testList = board.getAdjList(board.calcIndex(4, 14));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 15)));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 13)));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 14)));
		Assert.assertEquals(3, testList.size());
		//Doorway Down
		testList = board.getAdjList(board.calcIndex(12, 15));
		Assert.assertTrue(testList.contains(board.calcIndex(12, 16)));
		Assert.assertTrue(testList.contains(board.calcIndex(12, 14)));
		Assert.assertTrue(testList.contains(board.calcIndex(11, 15)));
		Assert.assertTrue(testList.contains(board.calcIndex(13, 15)));
		Assert.assertEquals(4, testList.size());
		//Doorway Up
		testList = board.getAdjList(board.calcIndex(4, 11));
		Assert.assertTrue(testList.contains(board.calcIndex(3, 11)));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 11)));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 10)));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 12)));
		Assert.assertEquals(4, testList.size());
	}

	@Test
	public void testAdjWalkways()
	{
		// 4 walkways
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(11, 7));
		Assert.assertTrue(testList.contains(board.calcIndex(10, 7)));
		Assert.assertTrue(testList.contains(board.calcIndex(12, 7)));
		Assert.assertTrue(testList.contains(board.calcIndex(11, 6)));
		Assert.assertTrue(testList.contains(board.calcIndex(11, 8)));
		Assert.assertEquals(4, testList.size());

		// 2 walkways (left and right)
		testList = board.getAdjList(board.calcIndex(5, 16));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 17)));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 15)));
		Assert.assertEquals(2, testList.size());

		// 2 walkways (up and down)
		testList = board.getAdjList(board.calcIndex(15, 13));
		Assert.assertTrue(testList.contains(board.calcIndex(14, 13)));
		Assert.assertTrue(testList.contains(board.calcIndex(16, 13)));
		Assert.assertEquals(2, testList.size());

		// 2 walkways on bottom edge by room
		testList = board.getAdjList(board.calcIndex(17, 6));
		Assert.assertTrue(testList.contains(board.calcIndex(17, 7)));
		Assert.assertTrue(testList.contains(board.calcIndex(16, 6)));
		Assert.assertEquals(2, testList.size());

		// 2 walkways on right edge by room
		testList = board.getAdjList(board.calcIndex(12, 18));
		Assert.assertTrue(testList.contains(board.calcIndex(11, 18)));
		Assert.assertTrue(testList.contains(board.calcIndex(12, 17)));
		Assert.assertEquals(2, testList.size());

		// 1 walkway on top edge by room
		testList = board.getAdjList(board.calcIndex(0, 14));
		Assert.assertTrue(testList.contains(board.calcIndex(0, 13)));
		Assert.assertEquals(1, testList.size());

		// 2 walkways on left edge by room
		testList = board.getAdjList(board.calcIndex(10, 0));
		Assert.assertTrue(testList.contains(board.calcIndex(11, 0)));
		Assert.assertTrue(testList.contains(board.calcIndex(9, 0)));			
		Assert.assertEquals(2, testList.size());

		// by door
		testList = board.getAdjList(board.calcIndex(12, 9));
		Assert.assertTrue(testList.contains(board.calcIndex(12, 10)));
		Assert.assertTrue(testList.contains(board.calcIndex(12, 8)));
		Assert.assertTrue(testList.contains(board.calcIndex(11, 9)));
		Assert.assertEquals(3, testList.size());
	}

	@Test
	public void testTargetsOneStep() {
		board.calcTargets(7, 5, 1);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 5))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 5))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 4))));
	}

	@Test
	public void testTargetsThreeSteps() {
		board.calcTargets(11, 12, 3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(13, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 12))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 13))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 10))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 12))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 9))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 11))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 13))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 15))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 10))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 12))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 14))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 11))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 13))));
	}

	@Test
	public void testTargetsFiveSteps() {
		board.calcTargets(4, 8, 5);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(15, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(1, 10))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(2, 5))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(3, 10))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 5))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 9))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 11))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 13))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 4))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 10))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 12))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 5))));
	}

	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(14, 7, 2);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(5, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 8))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 7))));
	}

	@Test 
	public void testTargetsIntoRoom() {
		board.calcTargets(2, 5, 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(0, 5))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(1, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 5))));
	}

	@Test
	public void testRoomExit() {
		board.calcTargets(12, 3, 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 3))));
		board.calcTargets(12, 3, 2);
		targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 4))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 2))));
	}

}