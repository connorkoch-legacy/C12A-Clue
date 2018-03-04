package clueGame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import experiment.BoardCell;

public class Board {
	
	
	// variable used for singleton pattern
		private static Board theInstance = new Board();
		// constructor is private to ensure only one can be created
		private Board() {
			
		}
		// this method returns the only Board
		public static Board getInstance() {
			return theInstance;
		}
	
	
	private int numRows;
	private int numColumns;
	public static final int MAX_BOARD_SIZE = 50;
	public static BoardCell[][] board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	public HashMap<Character, String> legend = new HashMap<Character, String>();
	public HashMap<BoardCell, Set<BoardCell>> adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
	public HashSet<BoardCell> targets = new HashSet<BoardCell>();
	private String boardConfigFile;
	private String roomConfigFile;
	
	
	
	
	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}

	public HashSet<BoardCell> getTargets() {
		return targets;
	}

	public void setTargets(HashSet<BoardCell> targets) {
		this.targets = targets;
	}
	
	
	
	public HashMap<Character, String> getLegend() {
		return legend;
	}



	public void setConfigFiles(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}
	public clueGame.BoardCell getCellAt(int i, int j) {
		clueGame.BoardCell b = new clueGame.BoardCell();
		b.setRow(1);
		b.setCol(1);
		return b;
	}

	
	
	
}
