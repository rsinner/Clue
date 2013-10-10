package clueGame;

public class WalkwayCell extends BoardCell {

	public WalkwayCell(int row, int column) {
		super(row, column);
	}

	@Override
	public boolean isWalkway() {
		return true;
	}
	

}