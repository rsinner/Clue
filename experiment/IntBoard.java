package experiment;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private boolean[] visited;
	private static Integer[][] index;
	
	public IntBoard() {
		index = new Integer[4][4];
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				index[i][j] = count;
				count++;
			}
		}
	}
	
	public void calcAdjacencies() {
		adjMtx = new HashMap<Integer, LinkedList<Integer>>();
		
	}
	
	public void startTargets(int row, int column, int numSteps) {
		visited = new boolean[16];
		for (int x = 0; x < 16; x++)
			visited[x] = false;
		int i = calcIndex(row, column);
		visited[i] = true;
		Set<Integer> targets = getTargets(i, numSteps);
	}
	
	public Set<Integer> getTargets(int index, int numSteps) {
		HashSet set = new HashSet();
		for () {
			visited[a] = true;
			if (numSteps == 1)
				set.add(a);
			else
				getTargets(a, numSteps--);
			visited[a] = false;
		}
		return set;
	}
	
	public static LinkedList<Integer> getAdjList(int cell) {
		LinkedList list = new LinkedList();
		return list;
	}
	
	public static int calcIndex(int row, int column) {
		int i;
		i = index[row][column];
		return i;
	}
}
