//Connor Koch and Dan Winternitz

package clueGame;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


//import experiment.BoardCell;

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
	private int numDoors;
	public static final int MAX_BOARD_SIZE = 50;
	public static clueGame.BoardCell[][] board = new clueGame.BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
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
	
	
	public int getNumDoors(){
		return numDoors;
	}



	public void setConfigFiles(String layOut, String csv)  {
		roomConfigFile = csv;
		boardConfigFile = layOut;
		
		
		// handles "testRooms" tests
		File file = new File(csv);
		try {
			Scanner inputStream = new Scanner(file);
			while(inputStream.hasNext()){
				String data = inputStream.nextLine();
				//System.out.println(data);
				String[] foo = data.split(",\\s*");
				
				String first = foo[0];
				String second = foo[1];
				char c = first.charAt(0);
				//System.out.println(c);
				//System.out.println(second);
				
				legend.put(c, second);
			}
			inputStream.close();
				
			}catch (FileNotFoundException e){
				e.printStackTrace();
		}
		
		
		
		
		// handles "testBoardDimensions" tests
		
		File file1 = new File(layOut);
		int count = 0;
		
		try {
			Scanner input = new Scanner(file1);
			while(input.hasNext()){
				count++;
				String temp = input.nextLine();
				String[] bar = temp.split(",");
				setNumColumns(bar.length);
			}
			setNumRows(count);
			input.close();
				
			}catch (FileNotFoundException e){
				e.printStackTrace();
		}
		
	}
	
	
	
	
	

	public void initialize() {
		// the following populates board and sets certain attributes for each BoardCell
		
		
		File file1 = new File(boardConfigFile);
		int i = 0;
		
		try {
			Scanner input = new Scanner(file1);
			while(input.hasNext()){
				String temp = input.nextLine();
				String[] bar = temp.split(",");
				
				for(int j = 0; j < bar.length; j++){
					clueGame.BoardCell bc = new clueGame.BoardCell();
					bc.setCol(j);
					bc.setRow(i);
					bc.setInitial(bar[j].charAt(0));
					if(bar[j].length() == 2 && bar[j].charAt(1) != 'N'){
						
						
						
						
						bc.setDoorway(true);
						bc.setRoom(false);
						bc.setWalkway(false);
					} else if (bar[j].charAt(0) == 'W'){
						bc.setWalkway(true);
						bc.setRoom(false);
						bc.setDoorway(false);
					} else {
						bc.setRoom(true);
						bc.setWalkway(false);
						bc.setDoorway(false);
					}
					
		
					board[i][j] = bc;
				}
	
				i++;
			}
			
			input.close();
				
			}catch (FileNotFoundException e){
				e.printStackTrace();
		}
		System.out.println(getNumDoors());
	
		
	}
	
	
	
	
	public clueGame.BoardCell getCellAt(int i, int j) {
		
		return board[i][j];
	}

	
	
	
}
