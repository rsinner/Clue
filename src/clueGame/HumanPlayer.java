package clueGame;

import java.util.Set;

public class HumanPlayer extends Player {

	public void makeMove(Set<BoardCell> targets, Board b) {
		b.highlightTargets(targets, true);
		
	}
}
