package clueGame;

import java.awt.Color;
import java.awt.Font;
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
	public void draw(Graphics g, Board c, boolean drawName, int cellSize) {
		// Checks to see if it is a target and highlights it
		if (isHighlighted())
			g.setColor(Color.CYAN);
		else
			// Set room color to gray
			g.setColor(Color.gray);
		g.fillRect(getColumn()*cellSize, getRow()*cellSize, cellSize, cellSize);
		// Since 1 inch ~ 72 units, each is 36x36 units.
		g.drawRect(getColumn()*cellSize, (getRow())*cellSize, cellSize, cellSize);
		// If we're supposed to draw the name, draw the name.
		g.setColor(Color.WHITE);

		if(doorDirection == DoorDirection.UP){
			g.setColor(Color.BLUE);
			//g.drawRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE,getDimension(), getDimension());
			g.fillRect(getColumn()*cellSize, getRow()*cellSize, cellSize, cellSize/4);
			g.setColor(Color.gray);
			g.drawRect(getColumn()*cellSize, (getRow())*cellSize, cellSize, cellSize);
		}
		else if(doorDirection == DoorDirection.DOWN){
			g.setColor(Color.BLUE);
			//g.drawRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE,getDimension(), getDimension());
			g.fillRect(getColumn()*cellSize, (getRow()+1)*cellSize-10, cellSize, cellSize/4);
			g.setColor(Color.gray);
			g.drawRect(getColumn()*cellSize, (getRow())*cellSize, cellSize, cellSize);
		}
		else if(doorDirection == DoorDirection.LEFT){
			g.setColor(Color.BLUE);
			//g.drawRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE,getDimension(), getDimension());
			g.fillRect(getColumn()*cellSize, (getRow())*cellSize, cellSize/4, cellSize);
			g.setColor(Color.gray);
			g.drawRect(getColumn()*cellSize, (getRow())*cellSize, cellSize, cellSize);
		}
		else if(doorDirection == DoorDirection.RIGHT){
			g.setColor(Color.BLUE);
			//g.drawRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE,getDimension(), getDimension());
			g.fillRect(getColumn()*cellSize+cellSize - 10, (getRow())*cellSize, cellSize/4, cellSize);
			g.setColor(Color.gray);
			g.drawRect(getColumn()*cellSize, (getRow())*cellSize, cellSize, cellSize);
		}
		g.setColor(Color.WHITE);
		if(drawName){
			g.setFont(new Font("Felix Titling", Font.BOLD, 10));
			g.drawString(c.getRooms().get(getInitial()), getColumn()*cellSize, getRow()*cellSize+(int)(.5*cellSize));
		}
	}

	@Override
	public void draw(Graphics g, Board c, boolean drawName) {
		// Checks to see if it is a target and highlights it
		if (isHighlighted())
			g.setColor(Color.CYAN);
		else
			// Set room color to gray
			g.setColor(Color.gray);
		g.fillRect(getColumn()*CELL_SIZE, getRow()*CELL_SIZE, getDimension(), getDimension());
		// Since 1 inch ~ 72 units, each is 36x36 units.
		g.drawRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE,getDimension(), getDimension());
		// If we're supposed to draw the name, draw the name.
		g.setColor(Color.WHITE);

		if(doorDirection == DoorDirection.UP){
			g.setColor(Color.BLUE);
			//g.drawRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE,getDimension(), getDimension());
			g.fillRect(getColumn()*CELL_SIZE, getRow()*CELL_SIZE, CELL_SIZE, CELL_SIZE/4);
			g.setColor(Color.gray);
			g.drawRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE,getDimension(), getDimension());
		}
		else if(doorDirection == DoorDirection.DOWN){
			g.setColor(Color.BLUE);
			//g.drawRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE,getDimension(), getDimension());
			g.fillRect(getColumn()*CELL_SIZE, (getRow()+1)*CELL_SIZE-10, CELL_SIZE, CELL_SIZE/4);
			g.setColor(Color.gray);
			g.drawRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE,getDimension(), getDimension());
		}
		else if(doorDirection == DoorDirection.LEFT){
			g.setColor(Color.BLUE);
			//g.drawRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE,getDimension(), getDimension());
			g.fillRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE, CELL_SIZE/4, CELL_SIZE);
			g.setColor(Color.gray);
			g.drawRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE,getDimension(), getDimension());
		}
		else if(doorDirection == DoorDirection.RIGHT){
			g.setColor(Color.BLUE);
			//g.drawRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE,getDimension(), getDimension());
			g.fillRect(getColumn()*CELL_SIZE+CELL_SIZE - 10, (getRow())*CELL_SIZE, CELL_SIZE/4, CELL_SIZE);
			g.setColor(Color.gray);
			g.drawRect(getColumn()*CELL_SIZE, (getRow())*CELL_SIZE,getDimension(), getDimension());
		}
		g.setColor(Color.WHITE);
		if(drawName){
			g.setFont(new Font("Felix Titling", Font.BOLD, 10));
			g.drawString(c.getRooms().get(getInitial()), getColumn()*CELL_SIZE, getRow()*CELL_SIZE+(int)(.5*CELL_SIZE));
		}
	}

}