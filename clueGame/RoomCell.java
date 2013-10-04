package clueGame;

public class RoomCell extends BoardCell {
	public enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE};
	private DoorDirection doorDirection;
	private char roomInitial;

	public RoomCell() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isRoom() {
		return true;
	}

	public Object getInitial() {
		// TODO Auto-generated method stub
		return null;
	}

}