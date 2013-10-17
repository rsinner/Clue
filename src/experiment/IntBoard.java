package experiment;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private boolean[] visited;
	private final static int size = 4;
	private final static int total = 16;
	private static Integer[][] index;

	public IntBoard() {
		index = new Integer[size][size];
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				index[i][j] = count;
				count++;
			}
		}
	}

	public void calcAdjacencies() {
		adjMtx = new HashMap<Integer, LinkedList<Integer>>();
		LinkedList<Integer> adj = new LinkedList<Integer>();
		for (int i = 0; i < total; i++) {
			adj = getAdjList(i);
			adjMtx.put(i, adj);
		}

	}

	public void startTargets(int row, int column, int numSteps) {
		visited = new boolean[total];
		for (int x = 0; x < total; x++)
			visited[x] = false;
		int i = calcIndex(row, column);
		visited[i] = true;
	}

	public Set<Integer> getTargets(int cell, int numSteps) {
		HashSet<Integer> set = new HashSet<Integer>();
		LinkedList<Integer> adjCell = getAdjList(cell);
		for (int n = 0; n < adjCell.size(); n++) {
			if (visited[adjCell.get(n)]) {
				adjCell.remove(n);
				n--;
			}
		}
		for (Integer a : adjCell) {
			visited[a] = true;
			if (numSteps == 1)
				set.add(a);
			else 
				set.addAll(getTargets(a, numSteps-1));
			visited[a] = false;				
		}
		return set;
	}

	public static LinkedList<Integer> getAdjList(int cell) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		int row = calcRow(cell);
		int col = calcCol(cell);
		if (row+1 < size){
			if (index[row+1][col] >= 0)
				list.add(index[row+1][col]);
		}
		if (row-1 >= 0){
			if (index[row-1][col] >= 0)
				list.add(index[row-1][col]);
		}
		if (col+1 < size){
			if (index[row][col+1] >= 0)
				list.add(index[row][col+1]);
		}
		if (col-1 >= 0){
			if (index[row][col-1] >= 0)
				list.add(index[row][col-1]);
		}
		return list;
	}

	public static int calcIndex(int row, int column) {
		int i;
		i = index[row][column];
		return i;
	}
	
	//Helper functions for finding Adjacencies
	public static int calcRow(int cell) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (index[i][j] == cell) {
					return i;
				}
			}
		}
		return 0;
	}
	public static int calcCol(int cell) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (index[i][j] == cell) {
					return j;
				}
			}
		}
		return 0;
	}
	
}
