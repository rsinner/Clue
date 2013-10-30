package clueGame;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JPanel;

import clueGame.RoomCell.DoorDirection;

public class Board extends JPanel {
	public ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	private Map<Integer, LinkedList<Integer>> adjLists;
	private Set<BoardCell> targets;
	private boolean visited[];
	private int numRows;
	private int numColumns;
	private String legend;
	private String layout;
	private PrintWriter logger;

	// Board constructor that sets up the ArrayList, HashMap, and
	// gets the legend and layout files through user input
	public Board() {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		this.legend = getLegendFile();
		this.layout = getLayoutFile();
		adjLists = new HashMap<Integer, LinkedList<Integer>>();
		try {
			logger = new PrintWriter("errorLog.txt");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		boolean firstCell = true;
		BoardCell previous = null;
		ArrayList<Character> roomsPainted = new ArrayList<Character>();
		for(int i = 0; i < cells.size(); i ++){
			if(cells.get(i) instanceof RoomCell){
				RoomCell r = (RoomCell) cells.get(i);
				if(!roomsPainted.contains(r.getInitial())){
					r.draw(g, this, true);
					roomsPainted.add(r.getInitial());
				}
				else{
					cells.get(i).draw(g, this, false);
				}
			}
			else{
				cells.get(i).draw(g, this, false);
			}
			
		}
	}
	
	// Board constructor that sets up the ArrayList, HashMap, and
	// sets the legend and layout file to what was input into
	// the constructor
	public Board(String layout, String legend) {
		cells = new ArrayList<BoardCell>();
		rooms = new HashMap<Character, String>();
		this.legend = legend;
		this.layout = layout;
		adjLists = new HashMap<Integer, LinkedList<Integer>>();
		try {
			logger = new PrintWriter("errorLog.txt");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Loads the layout and legend files by calling other methods
	public void loadConfigFiles() {
		try {
			loadRoomConfig();
			loadBoardConfig();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Prompts the user to input the name of the legend file (*.txt)
	public String getLegendFile() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please insert the legend file: ");
		String legend = scan.nextLine();
		return legend;
	}
	
	// Prompts the user to input the name of the layout file (*.csv)
	public String getLayoutFile() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please insert the layout file: ");
		String layout = scan.nextLine();
		return layout;
	}
	
	// Opens and traverses the legend file
	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader legRead = new FileReader(legend);
		Scanner legIn = new Scanner(legRead);
		String line;
		// While the file has another line...
		while (legIn.hasNextLine()) {
			line = legIn.nextLine();
			char c = line.charAt(0);
			String s = line.substring(3);
			// Checks to make sure that the legend file is correctly formatted
			if (!(Character.isLetter(c) && line.charAt(1) == ',' && line.charAt(2) == ' ') || s.indexOf(',') >= 0) {
				logger.write("Error in the legend file.");
				throw new BadConfigFormatException("Error in the legend file.");
			}
			// Puts the character of the room and the name of the room in the Map containing all the rooms
			rooms.put(c, s);
		}
	}

	// Opens and traverses the layout file
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader layRead = new FileReader(layout);
		Scanner layIn = new Scanner(layRead);
		String line;
		int row = 0;
		int prevCol = -1;
		while (layIn.hasNextLine()) {
			int col = 0;
			line = layIn.nextLine();
			// Goes through each line of the file character by character
			for (int i =  0; i < line.length(); i++) {
				char c = line.charAt(i);
				// Passes over commas
				if (c == ',')
					continue;
				String s = line.charAt(i)+"";
				// Throws an error if there is not a room at the next character
				if (rooms.get(line.charAt(i)) == null) {
					logger.write("Error in the legend file.");
					throw new BadConfigFormatException("Error in the layout file.");
				}
				// Accounts for multiple characters (doorways)
				if (i+1 < line.length() && line.charAt(i+1) != ',') {
					s += line.charAt(i+1);
					i++;
				}
				// creates a new boardcell at that row and col with the string s that contains
				// either a room character, a walkway character, or a doorway
				BoardCell temp = createBoardCell(row,col,s);
				temp.setRow(row);
				temp.setCol(col);
				cells.add(temp);
//				cells.add(createBoardCell(row,col,s));
//				cells.get(calcIndex(row, col)).setRow(row);
//				cells.get(calcIndex(row, col)).setCol(col);
				col++;
			}
			// Throws an error if the columns are not all the same size
			if (prevCol >= 0 && prevCol != col) {
				logger.write("Error in the legend file.");
				throw new BadConfigFormatException("Error in the layout file.");
			}
			prevCol = col;
			row++;
		}
		// Sets the number of rows and columns
		numRows = row;
		numColumns = prevCol;
	}
	
	// Creates a boardcell at the row and col
	public BoardCell createBoardCell(int row, int col, String s) {
		// Returns a WalkwayCell if s is a 'W'
		if (s.charAt(0) == 'W')
			return new WalkwayCell(row, col);
		// Otherwise it returns a room cell
		return new RoomCell(row, col, s);
	}
	
	// Returns the index at a certain row and column
	public int calcIndex(int row, int column) {
		return numColumns*row + column;
	}
	
	public RoomCell getRoomCellAt(int row, int col) {
		int index = calcIndex(row, col);
		// if the roomCell at that row and column is a walkway, it will return null
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
	
	//Returns true when the cell at (row2, col2) is a valid adjacent cell to the cell at (row1, col1)
	private boolean adjacencyLogic(int row1, int col1, int row2, int col2) {
		// Calculates the index of the adjacent cell
		int adjacentIndex = calcIndex(row2,col2);
		// Checks to make sure that the adjacent cell is within the boundaries of the board
		if(adjacentIndex >= 0 && adjacentIndex < cells.size() && row2 >= 0 && row2 < numRows && col2 >= 0 && col2 < numColumns) {
			BoardCell adjacentCell = cells.get(adjacentIndex);
			// Checks to see if the adjacent cell is a doorway
			if(adjacentCell.isDoorway()) {
				RoomCell thisRoom = (RoomCell) adjacentCell;
				// Checks to see if the direction of the door at the adjacent cell is a valid
				// direction to be entered from by the cell
				if(row2 + thisRoom.getDoorDirection().getRow() == row1 && col2 + thisRoom.getDoorDirection().getCol() == col1) 
					return true;
				else
					return false;
			}
			// If it is not a doorway, it returns true if the adjacent cell is not a room (is a walkway)
			else {
				return !cells.get(adjacentIndex).isRoom();
			}
		}
		return false;
	}
	
	// Finds all the adjacencies for the board and adds them to a Map
	public void calcAdjacencies() {
		LinkedList<Integer> adj = new LinkedList<Integer>();
		for (int i = 0; i < numRows; ++i) {
			for (int j = 0; j < numColumns; ++j) {
				adj = getAdjList(calcIndex(i,j));
				adjLists.put(calcIndex(i, j), adj);
			}
		}
	}
	
	// Gets the adjacency list for a certain cell
	public LinkedList<Integer> getAdjList(int cell) {
		LinkedList<Integer>	adj = new LinkedList<Integer>();
		int row = calcRow(cell);
		int col = calcCol(cell);
		// Checks to make sure the cell is within the board boundaries
		if(cell < 0 || cell >= cells.size())
			return adj;
		// if the cell is a doorway it adds the cell that is the only way out of the room 
		if(cells.get(cell).isDoorway()) {
			RoomCell thisCell = (RoomCell) cells.get(cell);
			adj.add(calcIndex(row + thisCell.getDoorDirection().getRow(), col + thisCell.getDoorDirection().getCol()));
			return adj;
		}
		// If the cell is not a room, then it calls adjacencyLogic and finds the adjacent cells
		else if(!cells.get(cell).isRoom()) {
			if(adjacencyLogic(row, col, row, col + 1))
				adj.add(calcIndex(row, col + 1));
			if(adjacencyLogic(row, col, row, col - 1))
				adj.add(calcIndex(row, col - 1));
			if(adjacencyLogic(row, col, row + 1, col))
				adj.add(calcIndex(row + 1, col));
			if(adjacencyLogic(row, col, row - 1, col))
				adj.add(calcIndex(row - 1, col));
		}
		return adj;
	}
	
	// Sets up the visited array and the targets Set in preparation to find the targets
	public void startTargets(int row, int column, int steps) {
		visited = new boolean[numRows*numColumns];
		targets = new HashSet<BoardCell>();
		for(int i = 0; i < visited.length; i++)
			visited[i] = false;
		visited[calcIndex(row, column)] = true;
	}
	
	// Calls startTargets to set everything up, then calls a different calcTargets (see below)
	public void calcTargets(int row, int column, int steps) {
		startTargets(row, column, steps);
		calcTargets(row, column, steps, targets);
	}
	
	// Finds all the targets from a certain cell with a certain number of steps
	private void calcTargets(int row, int column, int steps, Set<BoardCell> targ) {
		LinkedList<Integer> adjCells = getAdjList(calcIndex(row, column));
		// Goes through all of the possible adjacent cells
		for(int n = 0; n < adjCells.size(); n++) {
			// If the cell has already been visited, then it removes it from the LinkedList
			if(visited[adjCells.get(n)]) {
				adjCells.remove(n);
				n--;
			}
		}
		// For every integer in adjCells, it finds all possible targets
		for(Integer a : adjCells) {
			visited[a] = true;
			BoardCell thisCell = cells.get(a);
			// If the number of steps is down to one, or the cell is a doorway,
			// it adds the cell to the Set of targets
			if(steps == 1 || thisCell.isDoorway()) {
				targ.add(thisCell);
			}
			// Otherwise, it calls calcTargets again with number of steps decremented by 1
			else {
				calcTargets(calcRow(a), calcCol(a), steps - 1, targ);
			}
			visited[a] = false;
		}
	}
	
	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	public int calcRow(int cell) {
		return cell / numColumns;
	}
	
	public int calcCol(int cell) {
		return cell % numColumns;
	}
}
