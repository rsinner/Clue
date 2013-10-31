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
		// Set walkway color to yellow
		g.setColor(Color.yellow);
		g.fillRect(getColumn()*CELL_SIZE, getRow()*CELL_SIZE, getDimension(), getDimension());
		g.setColor(Color.BLACK);
		g.drawRect(getColumn()*CELL_SIZE,getRow()*CELL_SIZE, getDimension(),getDimension());
		
		
		// Do nothing with draw name.
		
	}




}