package clueGame;

public class RoomCell extends BoardCell {
	public static enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE};
	private DoorDirection doorDirection;
	private char roomInitial;

	public RoomCell(int row, int col, String s) {
		super(row,col);
		roomInitial = s.charAt(0);	
		if (s.length() == 2) {
			if (s.charAt(1) == 'U')
				doorDirection = DoorDirection.UP;
			else if (s.charAt(1) == 'D')
				doorDirection = DoorDirection.DOWN;
			else if (s.charAt(1) == 'R')
				doorDirection = DoorDirection.RIGHT;
			else if (s.charAt(1) == 'L')
				doorDirection = DoorDirection.LEFT;
			else
				doorDirection = DoorDirection.NONE;
		}
		else
			doorDirection = DoorDirection.NONE;
	}
	
	@Override
	public boolean isRoom() {
		return true;
	}
	
	@Override
	public boolean isDoorway() {
		if (doorDirection == DoorDirection.NONE)
			return false;
		else
			return true;
	}

	public char getInitial() {
		return roomInitial;
	}

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

}