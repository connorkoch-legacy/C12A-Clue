package clueGame;

public class BoardCell {
	private int row;
	private int col;
	private char initial;
	
	private boolean walkway;
	private boolean room;
	private boolean doorway;
	
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
		// TODO Auto-generated method stub
		return null;
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
	
	
	
	
	
}
