package clueBoard;

import java.awt.Color;
import java.awt.Graphics;

public class WalkwayCell extends BoardCell {

	public WalkwayCell() {
	
	}
	
	@Override
	public boolean isWalkway() {
		return true;
	}
	
	//Draw Walkway Cell
	@Override
	public void draw(Graphics g, Board board, int width, int height) {
		int leftCoord = this.getCellColumn()*width;
		int topCoord = this.getCellRow()*height;
		g.setColor(Color.yellow);
		g.fillRect(leftCoord, topCoord, width, height);
		g.setColor(Color.black);
		g.drawRect(leftCoord, topCoord, width, height);
		
	}
}
