package clueBoard;

import java.util.Set;

//Naomi and Brandon
public class HumanPlayer extends Player{

	public HumanPlayer(String name, java.awt.Point location, java.awt.Color color) {
		super(name, location, color);
	}
	
	public HumanPlayer() {
		super();
	}
	
	public void updateSeen(Card seen){
		this.seen.add(seen);
	}

	@Override
	void makeMove(Set<BoardCell> targets, Board board) {
		board.setHumanTurn(true);
		board.repaint();
		
	}
}
