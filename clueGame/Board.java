package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import clueGame.RoomCell.DoorDirection;

public class Board {
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	
	public Board(String file) {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		
	}
	
	public void loadConfigFiles() throws FileNotFoundException {
		
		//numRows = layIn.
	}
	public String getLegendFile() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please insert the legend file: ");
		String legend = scan.nextLine();
		return legend;
	}
	public String getLayoutFile() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please insert the layout file: ");
		String layout = scan.nextLine();
		return layout;
	}
	public void loadLegendFile() throws FileNotFoundException, BadConfigFormatException {
		String legend = getLegendFile();
		FileReader legRead = new FileReader(legend);
		Scanner legIn = new Scanner(legRead);
		String line;
		while (legIn.hasNextLine()) {
			line = legIn.nextLine();
			char c = line.charAt(0);
			String s = line.substring(3);
			if (!(Character.isLetter(c) && line.charAt(1) == ',' && line.charAt(2) == ' ') || s.indexOf(',') >= 0)
				throw new BadConfigFormatException("Error in the legend file.");
			rooms.put(c, s);
		}
	}
	public void loadLayoutFile() throws FileNotFoundException {
		String layout = getLayoutFile();
		FileReader layRead = new FileReader(layout);
		Scanner layIn = new Scanner(layRead);
		String line;
		int row = 0;
		while (layIn.hasNextLine()) {
			line = layIn.nextLine();
			for (int col =  0; col < line.length(); col++) {
				char c = line.charAt(col);
				if (c == ',')
					continue;
				String s = line.charAt(col)+"";
			}
		}
	}
	public static int calcIndex(int row, int column) {
		int i = 0;
		return i;
	}
	
	public RoomCell getRoomCellAt(int row, int col) {
		RoomCell whichCell = new RoomCell();
		return whichCell;
	}
	
	public ArrayList<BoardCell> getCells() {
		return cells;
	}
	public Map<Character, String> getRooms() {
		HashMap<Character, String> rooms = new HashMap<Character, String>();
		return rooms;
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	public BoardCell getCellAt(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
