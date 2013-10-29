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
	public void draw(Graphics g, BoardCell c) {
		g.setColor(Color.yellow);
		g.drawRect(c.getColumn()*36, c.getRow()*36, c.getDimension(), c.getDimension());
		
	}

	
	

}