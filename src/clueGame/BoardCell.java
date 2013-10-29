package clueGame;

import java.awt.Graphics;

import javax.naming.Context;

public abstract class BoardCell {
	private int row;
	private int column;
	// Corresponds to half an inch
	private int dimension = 36;
	
	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int column) {
		this.column = column;
	}

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

	public abstract void draw(Graphics g, BoardCell c);

	public int getDimension() {
		return dimension;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	
}
