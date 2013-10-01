package clueGame;

import java.util.ArrayList;
import java.util.Map;

public class Board {
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	
	public void loadCinfigFiles() {
		
	}
	public String getLegendFile() {
		return " ";
	}
	public String getLayoutFile() {
		return " ";
	}
	
	public static int calcIndex(int row, int column) {
		int i = 0;
		//i = index[row][column];
		return i;
	}
	
	public int getRoomCellAt(int row, int col) {
		int RoomCell = 0;
		return RoomCell;
	}
	public ArrayList<BoardCell> getCells() {
		return cells;
	}
	public Map<Character, String> getRooms() {
		return rooms;
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}

}
