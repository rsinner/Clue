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
	private String legend;
	private String layout;
	
	public Board() {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		this.legend = getLegendFile();
		this.layout = getLayoutFile();
	}
	public Board(String layout, String legend) {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		this.legend = legend;
		this.layout = layout;
	}
	public void loadConfigFiles() {
		try {
			loadRoomConfig();
			loadBoardConfig();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			e.printStackTrace();
		}
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
	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader legRead = new FileReader(legend);
		Scanner legIn = new Scanner(legRead);
		String line;
		while (legIn.hasNextLine()) {
			line = legIn.nextLine();
			char c = line.charAt(0);
			String s = line.substring(3);
			if (!(Character.isLetter(c) && line.charAt(1) == ',' && line.charAt(2) == ' ') || s.indexOf(',') >= 0)
				throw new BadConfigFormatException("Error in the legend file."+line);
			rooms.put(c, s);
		}
	}
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
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
				if (rooms.get(line.charAt(i)) == null)
					throw new BadConfigFormatException("Error in the layout file.");
				if (i+1 < line.length() && line.charAt(i+1) != ',') {
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
		numRows = row;
		numColumns = prevCol;
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
