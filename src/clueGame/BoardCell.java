package clueGame;

import java.awt.Color;

public class BoardCell {
	private int row;
	private int col;
	private char initial;
	private DoorDirection dir;
	private boolean walkway;
	private boolean room;
	private boolean doorway;
	private int rowPixel, colPixel;
	private Color color = Color.YELLOW;
	private final int BOARD_HEIGHT = 800;
	private final int BOARD_WIDTH = 800;
	private final int pieceDimensions = BOARD_HEIGHT/25;
	
	public void draw(){
		rowPixel = pieceDimensions * row;
		colPixel = pieceDimensions * col;
		
		//drawRect(rowPixel, colPixel, pieceDimensions , pieceDimensions);
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
}
