//Connor Koch and Dan Winternitz

package clueGame;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.Set;

//import experiment.BoardCell;


public class Board {
	
	private int numRows;
	private int numColumns;
	private int numDoors;
	public static final int MAX_BOARD_SIZE = 50;
	public static clueGame.BoardCell[][] board = new clueGame.BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	public HashMap<Character, String> legend = new HashMap<Character, String>();
	public HashMap<BoardCell, Set<BoardCell>> adjMtx = new HashMap<BoardCell, Set<BoardCell>>();
	public HashSet<BoardCell> targets = new HashSet<BoardCell>();
	public HashSet<BoardCell> visited = new HashSet<BoardCell>();
	private String boardConfigFile;
	private String roomConfigFile;
	
	
	// variable used for singleton pattern
		private static Board theInstance = new Board();
		// constructor is private to ensure only one can be created
		private Board() {
			for(int i = 0; i < board.length; ++i){
				for(int j = 0; j < board[i].length; ++j){
					board[i][j] = new BoardCell();
					board[i][j].setCol(i);
					board[i][j].setRow(j);
				}
			}
			calcAdjacencies();
		}
		// this method returns the only Board
		public static Board getInstance() {
			return theInstance;
		}
	
	
	
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

	public void setConfigFiles(String layOut, String csv) {
		roomConfigFile = csv;
		boardConfigFile = layOut;
		
		try {
		File file = new File(roomConfigFile);
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
		}catch(FileNotFoundException e) {
			
		}
		
		// handles "testBoardDimensions" tests
		
		File file1 = new File(boardConfigFile);
		int count = 0;
		try {
			Scanner input = new Scanner(file1);
			while(input.hasNext()){
				String temp = input.nextLine();
				String[] bar = temp.split(",");
				setNumColumns(bar.length);
				for(int i = 0; i < numColumns; i++) {
					for(int j = 0; j < bar.length; ++j) {
						board[i][count].setInitial(bar[j].charAt(0));
					}
				}
				count++;
			}
			setNumRows(count);
			input.close();
		}catch(FileNotFoundException e) {
		
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
						if(bar[j].charAt(1) == 'U') {
							bc.setDoorDirection(DoorDirection.UP);
						}
						else if(bar[j].charAt(1) == 'R') {
							bc.setDoorDirection(DoorDirection.RIGHT);
						}
						else if(bar[j].charAt(1) == 'D') {
							bc.setDoorDirection(DoorDirection.DOWN);
						} else {
							bc.setDoorDirection(DoorDirection.LEFT);
						}
						
						
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
					
					//System.out.println(bc.getDoorDirection());
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
	//////////////
	public void calcAdjacencies(){
		for(int i = 0; i < board.length; ++i){
			for(int j = 0; j < board[i].length; ++j){
				HashSet<BoardCell> adjacencies = new HashSet<BoardCell>();
				if(i-1 >= 0){
					//System.out.println("Hello");
					adjacencies.add(board[i-1][j]);
				}
				if(i+1 < board.length){
					adjacencies.add(board[i+1][j]);
				}
				if(j-1 >= 0){
					adjacencies.add(board[i][j-1]);
				}
				if(j+1 < board[i].length){
					adjacencies.add(board[i][j+1]);
				}
				
				adjMtx.put(board[i][j], adjacencies);
				//System.out.println(adjMtx.size());
			}
		}
	}
	
	public Set<BoardCell> getAdjList(int row, int col){
		calcAdjacencies();
		return adjMtx.get(board[row][col]);
	}
	
	public void calcTargets(BoardCell startCell, int pathLength){
		
		
		for (BoardCell adjacent : AdjList(startCell)) {
			if (visited.contains(adjacent)) {
				continue;
			}
			
			visited.add(adjacent);
			if (pathLength == 1) {
				targets.add(adjacent);
			}
			else {
				calcTargets(adjacent, pathLength - 1);
			}
			visited.remove(adjacent);
		}
	}
	
	
public void calcTargets(int row, int col, int pathLength){
		
		System.out.println(board[row][col]);
		
		//HashSet<BoardCell> temp = new HashSet<BoardCell>();
		//temp = AdjList(board[col][row]);
		
		for (BoardCell adjacent : AdjList(board[col][row])) {
			if (visited.contains(adjacent)) {
				continue;
			}
			
			visited.add(adjacent);
			if (pathLength == 1) {
				targets.add(adjacent);
			}
			else {
				calcTargets(adjacent, pathLength - 1);
			}
			visited.remove(adjacent);
		}
	}
	
	
	public HashSet<BoardCell> AdjList(BoardCell cell){
		/*for(BoardCell i: adjMtx.get(cell)){
			i.getRow();
		}*/
		calcAdjacencies();
		return (HashSet<BoardCell>) adjMtx.get(cell);
	}
	
	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException{
		
		// handles "testRooms" tests
		File file = new File(roomConfigFile);
			Scanner inputStream = new Scanner(file);
			while(inputStream.hasNext()){
				String data = inputStream.nextLine();
				//System.out.println(data);
				String[] foo = data.split(",\\s*");
				
				String first = foo[0];
				String second = foo[1];
				String third = foo[2];
				if(third != "Card" || third != "Other") {
					throw new BadConfigFormatException();     // throws if the third word is anything but card or other
				}
				char c = first.charAt(0);
				//System.out.println(c);
				//System.out.println(second);
				
				legend.put(c, second);
			}
			inputStream.close();
	}
	
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		// handles exception tests
		
		File file1 = new File(boardConfigFile);
		int count = 0;

			Scanner input = new Scanner(file1);
			while(input.hasNext()){
				String temp = input.nextLine();
				String[] bar = temp.split(",");
				setNumColumns(bar.length);
				for(int i = 0; i < numColumns; i++) {
					for(int j = 0; j < bar.length; ++j) {
						board[i][count].setInitial(bar[j].charAt(0));
						boolean isIn = false;
						for(char c: legend.keySet()) {
							if(bar[j].charAt(0) == c) isIn = true;
						}
						if(!isIn) throw new BadConfigFormatException(); // throws if the char is not in the legend
					}
				}
				count++;
			}
			setNumRows(count);
			input.close();
			if(numColumns != numRows) throw new BadConfigFormatException(); // throws if cols ! = rows
	}
	
	public clueGame.BoardCell getCellAt(int i, int j) {
		
		return board[i][j];
	}
	
	
	

	
	
	
}
