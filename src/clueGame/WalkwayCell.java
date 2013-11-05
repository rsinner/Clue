package clueGame;

import java.awt.Color;
import java.awt.Graphics;

import javax.naming.Context;

public class WalkwayCell extends BoardCell {

	public WalkwayCell(int row, int column) {
		super(row, column);
	}

	@Override
	public boolean isWalkway() {
		return true;
	}

	@Override
	public void draw(Graphics g, Board c, boolean drawName) {
		// Checks to see if it is a target and highlights it
		if (isHighlighted())
			g.setColor(Color.CYAN);
		else
			// Set walkway color to yellow
			g.setColor(Color.yellow);
		g.fillRect(getColumn()*CELL_SIZE, getRow()*CELL_SIZE, getDimension(), getDimension());
		g.setColor(Color.BLACK);
		g.drawRect(getColumn()*CELL_SIZE,getRow()*CELL_SIZE, getDimension(),getDimension());


		// Do nothing with draw name.

	}

	@Override
	public void draw(Graphics g, Board c, boolean drawName, int cellSize) {
		// Checks to see if it is a target and highlights it
		if (isHighlighted())
			g.setColor(Color.CYAN);
		else
			// Set walkway color to yellow
			g.setColor(Color.yellow);
		g.fillRect(getColumn()*cellSize, getRow()*cellSize, cellSize, cellSize);
		g.setColor(Color.BLACK);
		g.drawRect(getColumn()*cellSize,getRow()*cellSize, cellSize, cellSize);


		// Do nothing with draw name.

	}


}