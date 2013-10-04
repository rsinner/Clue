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
	public void loadConfigFiles() throws BadConfigFormatException, FileNotFoundException {
		loadLegendFile();
		loadLayoutFile();
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
	public void loadLayoutFile() throws FileNotFoundException, BadConfigFormatException {
		String layout = getLayoutFile();
		FileReader layRead = new FileReader(layout);
		Scanner layIn = new Scanner(layRead);
		String line;
		int row = 0;
		int prevCol = -1;
		while (layIn.hasNextLine()) {
			int col = 0;
			line = layIn.nextLine();
			for (int i =  0; i < line.length(); i++) {
				char c = line.charAt(i);
				if (c == ',')
					continue;
				String s = line.charAt(i)+"";
				if (i+1 < line.length() && line.charAt(i+1) != ',') {
					if (line.charAt(i+1) != 'U' || line.charAt(i+1) != 'R' || line.charAt(i+1) != 'D' || line.charAt(i+1) != 'L')
						throw new BadConfigFormatException("Error in the layout file.");
					s += line.charAt(i+1);
					i++;
				}
				cells.add(createBoardCell(row,col,s));
				col++;
			}
			if (prevCol >= 0 && prevCol != col)
				throw new BadConfigFormatException("Error in the layout file.");
			prevCol = col;
			row++;
		}
		numRows = row+1;
		numColumns = prevCol+1;
	}
	public BoardCell createBoardCell(int row, int col, String s) {
		if (s.charAt(0) == 'W')
			return new WalkwayCell(row, col);
		return new RoomCell(row, col, s);
	}
	public int calcIndex(int row, int column) {
		return numColumns*row + column;
	}
	public RoomCell getRoomCellAt(int row, int col) {
		int index = calcIndex(row, col);
		if (cells.get(index) instanceof WalkwayCell)
			return null;
		return (RoomCell) cells.get(index);
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
	public BoardCell getCellAt(int i) {
		return cells.get(i);
	}
}
