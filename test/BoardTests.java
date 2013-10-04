package test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.RoomCell;

public class BoardTests {
	private static Board board;
	public static final int NUM_ROOMS = 10;
	public static final int NUM_ROWS = 18;
	public static final int NUM_COLUMNS = 19;
	
	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		board = new Board("file.txt");
		board.loadConfigFiles();
	}
	@Test
	public void testRooms() {
		Map<Character, String> rooms = board.getRooms();
		assertEquals("Indoor Pool", rooms.get('P'));
		assertEquals("Video Game Room", rooms.get('V'));
		assertEquals("Lounge", rooms.get('L'));
		assertEquals("Closet", rooms.get('X'));
	}
	
	@Test
	public void testBoardDimensions() {
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());		
	}
	
	@Test
	public void FourDoorDirections() {
		// Test one each RIGHT/LEFT/UP/DOWN
		RoomCell room = board.getRoomCellAt(5, 2);
		assertTrue(room.isDoorway());
		room = board.getRoomCellAt(10, 11);
		assertFalse(room.isDoorway());	

	}
	
	@Test
	public void testNumberOfDoorways() 
	{
		int numDoors = 0;
		int totalCells = board.getNumColumns() * board.getNumRows();
		Assert.assertEquals(342, totalCells);
		for (int i=0; i<totalCells; i++)
		{
			BoardCell cell = board.getCellAt(i);
			if (cell.isDoorway())
				numDoors++;
		}
		Assert.assertEquals(10, numDoors);
	}
	
	@Test
	public void testCalcIndex() {
		assertEquals(0, board.calcIndex(0, 0));
		assertEquals(NUM_COLUMNS-2, board.calcIndex(0, NUM_COLUMNS-2));
		assertEquals(341, board.calcIndex(NUM_ROWS-1, NUM_COLUMNS-1));
	}
	
	@Test
	public void testRoomInitials() {
		assertEquals('P', board.getRoomCellAt(0, 0).getInitial());
		assertEquals('B', board.getRoomCellAt(2, 16).getInitial());
		assertEquals('H', board.getRoomCellAt(2, 11).getInitial());
		assertEquals('A', board.getRoomCellAt(14, 2).getInitial());
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testBadRooms() throws BadConfigFormatException, FileNotFoundException {
		Board b = new Board("file.txt");
		b.getLegendFile();
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoom() throws BadConfigFormatException, FileNotFoundException {
		Board b = new Board("file.txt");
		b.getLayoutFile();
	}
}