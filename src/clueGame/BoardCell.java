package clueGame;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.naming.Context;

public abstract class BoardCell {
	private int row;
	private int column;
	// Corresponds to half an inch
	private int dimension = 28;
	public static final int CELL_SIZE = 28;
	private int numColumns = 19;
	private boolean isHighlighted = false;
	
	public boolean isHighlighted() {
		return isHighlighted;
	}

	public void setHighlighted(boolean isHighlighted) {
		this.isHighlighted = isHighlighted;
	}

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
		isHighlighted = false;
	}

	public abstract void draw(Graphics g, Board board, boolean drawName);
	
	public abstract void draw(Graphics g, Board board, boolean drawName, int cellSize);
	
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
	
	public int calcIndex(int row, int column) {
		return numColumns*row + column;
	}

	
}
