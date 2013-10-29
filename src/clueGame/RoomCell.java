package clueGame;

import java.awt.Color;
import java.awt.Graphics;

import javax.naming.Context;

public class RoomCell extends BoardCell {
	public static enum DoorDirection {
		NONE(0,0),UP(-1,0),DOWN(1,0),LEFT(0,-1),RIGHT(0,1);

		private int x,y;

		DoorDirection(int X, int Y) {
			x = X;
			y = Y;
		}

		public int getRow() {
			return x;
		}
		public int getCol() {
			return y;
		}
	};
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

	@Override
	public void draw(Graphics g, BoardCell c) {
		// TODO Auto-generated method stub
		g.setColor(Color.gray);
		g.drawRect(c.getColumn()*36, c.getRow()*36, c.getDimension(), c.getDimension());
	}

	

}