package clueGame;

public class RoomCell extends BoardCell {
	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE};
	private DoorDirection doorDirection;
	private char roomInitial;

	public RoomCell(int row, int col, String s) {
		super(row,col);
		roomInitial = s.charAt(0);	
		if (s.length() == 2) {
			if (s.charAt(1) == 'U')
				doorDirection = DoorDirection.UP;
			if (s.charAt(1) == 'D')
				doorDirection = DoorDirection.DOWN;
			if (s.charAt(1) == 'R')
				doorDirection = DoorDirection.RIGHT;
			if (s.charAt(1) == 'L')
				doorDirection = DoorDirection.LEFT;
		}
	}
	
	@Override
	public boolean isRoom() {
		return true;
	}

	public char getInitial() {
		return roomInitial;
	}

}