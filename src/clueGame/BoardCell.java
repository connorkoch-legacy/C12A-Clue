package clueGame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.sun.javafx.charts.Legend;

public class BoardCell extends JPanel {
	private int row;
	private int col;
	private char initial;
	private DoorDirection dir;
	private boolean walkway;
	private boolean room;
	private boolean doorway;


	private int rowPixel, colPixel;
	private Color color = Color.YELLOW;
	private final int BOARD_HEIGHT = 600;
	private final int BOARD_WIDTH = 600;
	private final int pieceDimensions = BOARD_HEIGHT/25;
	private boolean nameSpace;
	private int doorBuffer;
	private boolean isTarget = false;

	public void draw(Graphics g){
		Board board = new Board();
		rowPixel = pieceDimensions * row;
		colPixel = pieceDimensions * col;

		if(walkway){
			g.setColor(color);
			g.fillRect(colPixel, rowPixel, pieceDimensions, pieceDimensions);
			g.setColor(Color.black);
			g.drawRect(colPixel, rowPixel, pieceDimensions, pieceDimensions);
		}
		if(nameSpace){
			g.drawString(Board.legend.get(initial), colPixel, rowPixel);
		}
		if(doorway){
			g.setColor(Color.GRAY);
			g.fillRect(colPixel, rowPixel, pieceDimensions, pieceDimensions);
			if(dir == DoorDirection.DOWN){
				doorBuffer = pieceDimensions / 9;
				g.setColor(Color.blue);
				g.fillRect(colPixel, rowPixel + pieceDimensions - doorBuffer, pieceDimensions, doorBuffer);
			} else if(dir == DoorDirection.UP){
				doorBuffer = pieceDimensions / 9;
				g.setColor(Color.blue);
				g.fillRect(colPixel, rowPixel + doorBuffer, pieceDimensions, doorBuffer);
			}else if(dir == DoorDirection.LEFT){
				doorBuffer = pieceDimensions / 9;
				g.setColor(Color.blue);
				g.fillRect(colPixel + doorBuffer, rowPixel, doorBuffer, pieceDimensions);
			}else if(dir == DoorDirection.RIGHT){
				doorBuffer = pieceDimensions / 9;
				g.setColor(Color.blue);
				g.fillRect(colPixel + pieceDimensions - doorBuffer, rowPixel, doorBuffer, pieceDimensions);
			}
		}
		if(isTarget){
			g.setColor(Color.WHITE);
			g.fillRect(colPixel, rowPixel, pieceDimensions, pieceDimensions);
		}
	}
	//Paints the target boardcells blue
	public void drawBlue(Graphics g){
		rowPixel = pieceDimensions * row;
		colPixel = pieceDimensions * col;

	}

	public BoardCell() {
	}
	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + col + "]";
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public char getInitial() {
		return initial;
	}
	public void setInitial(char initial) {
		this.initial = initial;
	}
	public boolean isWalkway(){
		return walkway;
	}
	public boolean isRoom(){
		return room;
	}
	public boolean isDoorway(){
		return doorway;
	}
	public Object getDoorDirection() {
		return dir;
	}
	public void setDoorway(boolean doorway) {
		this.doorway = doorway;
	}
	public void setWalkway(boolean walkway) {
		this.walkway = walkway;
	}
	public void setRoom(boolean room) {
		this.room = room;
	}
	public void setDoorDirection(DoorDirection dir) {
		this.dir = dir;
	}

	public boolean isNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(boolean nameSpace) {
		this.nameSpace = nameSpace;
	}
	public boolean isTarget() {
		return isTarget;
	}
	public void setTarget(boolean isTarget) {
		this.isTarget = isTarget;
	}


}
