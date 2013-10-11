package clueGame;

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

import clueGame.RoomCell.DoorDirection;

public class Board {
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
			if (!(Character.isLetter(c) && line.charAt(1) == ',' && line.charAt(2) == ' ') || s.indexOf(',') >= 0) {
				logger.write("Error in the legend file.");
				throw new BadConfigFormatException("Error in the legend file.");
			}
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
				if (rooms.get(line.charAt(i)) == null) {
					logger.write("Error in the legend file.");
					throw new BadConfigFormatException("Error in the layout file.");
				}
				if (i+1 < line.length() && line.charAt(i+1) != ',') {
					s += line.charAt(i+1);
					i++;
				}
				cells.add(createBoardCell(row,col,s));
				col++;
			}
			if (prevCol >= 0 && prevCol != col) {
				logger.write("Error in the legend file.");
				throw new BadConfigFormatException("Error in the layout file.");
			}
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
	//Returns true when the cell at (row2, col2) is a valid adjacent cell to the cell at (row1, col1)
	private boolean adjacencyLogic(int row1, int col1, int row2, int col2) {
		
		int adjacentIndex = calcIndex(row2,col2);
		if(adjacentIndex >= 0 && adjacentIndex < cells.size() && row2 >= 0 && row2 < numRows && col2 >= 0 && col2 < numColumns) {
			BoardCell adjacentCell = cells.get(adjacentIndex);
			if(adjacentCell.isDoorway()) {
				RoomCell thisRoom = (RoomCell) adjacentCell;
				if(row2 + thisRoom.getDoorDirection().getRow() == row1 && col2 + thisRoom.getDoorDirection().getCol() == col1) 
					return true;
				else
					return false;
			}
			else {
				return !cells.get(adjacentIndex).isRoom();
			}
		}
		return false;
	}
	public void calcAdjacencies() {
		LinkedList<Integer> adj = new LinkedList<Integer>();
		for (int i = 0; i < numRows; ++i) {
			for (int j = 0; j < numColumns; ++j) {
				adj = getAdjList(calcIndex(i,j));
				adjLists.put(calcIndex(i, j), adj);
			}
		}
	}
	public LinkedList<Integer> getAdjList(int cell) {
		LinkedList<Integer>	adj = new LinkedList<Integer>();
		int row = calcRow(cell);
		int col = calcCol(cell);
		if(cell < 0 || cell >= cells.size())
			return adj;
		if(cells.get(cell).isDoorway()) {
			RoomCell thisCell = (RoomCell) cells.get(cell);
			adj.add(calcIndex(row + thisCell.getDoorDirection().getRow(), col + thisCell.getDoorDirection().getCol()));
			return adj;
		}
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
	public void startTargets(int row, int column, int steps) {
		visited = new boolean[numRows*numColumns];
		targets = new HashSet<BoardCell>();
		for(int i = 0; i < visited.length; i++)
			visited[i] = false;
		visited[calcIndex(row, column)] = true;
	}
	public void calcTargets(int row, int column, int steps) {
		startTargets(row, column, steps);
		calcTargets(row, column, steps, targets);
	}
	private void calcTargets(int row, int column, int steps, Set<BoardCell> targ) {
		LinkedList<Integer> adjCells = getAdjList(calcIndex(row, column));
		for(int n = 0; n < adjCells.size(); n++) {
			if(visited[adjCells.get(n)]) {
				adjCells.remove(n);
				n--;
			}
		}
		for(Integer a : adjCells) {
			visited[a] = true;
			BoardCell thisCell = cells.get(a);
			if(steps == 1 || thisCell.isDoorway()) {
				targ.add(thisCell);
			}
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
