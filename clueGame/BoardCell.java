package clueGame;

public abstract class BoardCell {
	private int row;
	private int column;
	
	public boolean isWalkway() {
		return false;
	}
	
	public boolean isRoom() {
		return false;
	}
	
	public boolean isDoorway() {
		return false;
	}

	public BoardCell(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

}
