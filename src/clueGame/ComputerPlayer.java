package clueGame;

import java.util.ArrayList;
import java.util.Set;

public class ComputerPlayer extends Player {
	public BoardCell pickLocation(Set<BoardCell> targets) {
		for(BoardCell bc : targets){
			if (bc.isDoorway()) {
				// If it's a room cell, check to see if it was most recently visited
				if(bc.equals(getPreviousRoom())){
					continue;
				} else {
					RoomCell rc = (RoomCell) bc;
					setPreviousRoom(rc.getInitial());
					return bc;
				}
			}
		}

		// Either there were no rooms or the room was previously visited
		// Generate a random number  to choose the cell to pick.
		int randomRoom = generateRandomNumber(targets.size());
		Object[] targArray = targets.toArray();
		return (BoardCell) targArray[randomRoom];
		//return null;
	}
	
	public void makeMove(Set<BoardCell> targets) {
		BoardCell move = pickLocation(targets);
		int row = move.getColumn();
		int column = move.getRow();
		int location = move.calcIndex(row, column);
		setStartingLocation(location);
		
	}
}
