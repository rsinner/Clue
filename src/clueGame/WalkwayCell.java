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
	public void draw(Graphics g, Board c) {
		g.setColor(Color.yellow);
		g.drawRect(getColumn()*36,getRow()*36, getDimension(),getDimension());
		
	}

	
	

}