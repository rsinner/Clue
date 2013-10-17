package test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import experiment.IntBoard;

public class IntBoardTests {

	private IntBoard board;
	
	@Before
	public void setUp() {
		System.out.println("In @Before");
		board = new IntBoard();
		board.calcAdjacencies();
	}
	
	@Test
	public void testCalcIndex() {
		int expected = 5;
		int actual = board.calcIndex(1,1);
		Assert.assertEquals(expected,actual);
		expected = 11;
		actual = board.calcIndex(2, 3);
		Assert.assertEquals(expected,actual);
	}
	
	@Test
	public void testAdjacencyCorner() {
		LinkedList testList = board.getAdjList(0);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(1));
		//Assert.assertTrue(testList.contains(9));
		//Assert.assertTrue(testList.contains(6));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacencyEdge() {
		LinkedList testList = board.getAdjList(14);
		Assert.assertTrue(testList.contains(13));
		Assert.assertTrue(testList.contains(10));
		Assert.assertTrue(testList.contains(15));
		Assert.assertEquals(3, testList.size());
	}
	@Test
	public void testAdjacencyMiddle() {
		LinkedList testList = board.getAdjList(5);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(1));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(6));
		Assert.assertEquals(4, testList.size());
	}
	@Test
	public void testCorner() {
		board.startTargets(0, 0, 2);
		Set targets = board.getTargets(0,2);
		System.out.println(targets);
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(8));
	}
	
	@Test
	public void testTopEdge() {
		board.startTargets(0, 2, 1);
		Set targets = board.getTargets(2,1);
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(3));
	}
	
	@Test
	public void testMiddle() {
		board.startTargets(2, 2, 3);
		Set targets = board.getTargets(10,3);
		Assert.assertEquals(8, targets.size());
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(4));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(11));
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(14));
	}

	@Test
	public void testRightEdge() {
		board.startTargets(1, 3, 2);
		Set targets = board.getTargets(7,2);
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(15));
	}

	@Test
	public void testLeftEdge() {
		board.startTargets(1, 0, 2);
		Set targets = board.getTargets(4,2);
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(12));
	}

	@Test
	public void testBottomEdge() {
		board.startTargets(3, 2, 4);
		Set targets = board.getTargets(14,4);
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(4));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(11));
		Assert.assertTrue(targets.contains(12));
	}
}
