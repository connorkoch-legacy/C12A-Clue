//Connor Koch and Dan Winternitz

package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Board {
	
	private int numRows;
	private int numColumns;
	private int numDoors;
	public static final int MAX_BOARD_SIZE = 50;
	public static clueGame.BoardCell[][] board = new clueGame.BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	public Map<Character, String> legend = new HashMap<Character, String>();
	public Map<BoardCell, Set<BoardCell>> adjMtx = new HashMap<BoardCell, Set<BoardCell>>();
	public Set<BoardCell> targets = new HashSet<BoardCell>();
	public Set<BoardCell> visited = new HashSet<BoardCell>();
	private Card[] cards;
	private ArrayList<Card> envelope = new ArrayList<Card>();
	private Player[] players;
	private String boardConfigFile;
	private String roomConfigFile;
	private String playersConfigFile;
	private String cardsConfigFile;
	
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

	public Set<BoardCell> getTargets() {
		return targets;
	}

	public void setTargets(HashSet<BoardCell> targets) {
		this.targets = targets;
	}
	
	public Map<Character, String> getLegend() {
		return legend;
	}
	
	public int getNumDoors(){
		return numDoors;
	}

	public void setConfigFiles(String layOut, String csvRooms, String csvPlayers, String csvCards) {
		roomConfigFile = csvRooms;
		playersConfigFile = csvPlayers;
		cardsConfigFile = csvCards;
		boardConfigFile = layOut;
		
		//Room Loading
		try {
		File file = new File(roomConfigFile);
		Scanner inputStream = new Scanner(file);
		while(inputStream.hasNext()){
			String data = inputStream.nextLine();
			String[] foo = data.split(",\\s*");
			
			String first = foo[0];
			String second = foo[1];
			char c = first.charAt(0);
			
			legend.put(c, second);
		}
		inputStream.close();
		}catch(FileNotFoundException e) {
			System.out.println("File not found, please correct file name.");
		}
		
		//Players Loading
		try {
		File file = new File(playersConfigFile);
		Scanner inputStream = new Scanner(file);
		int counter = 0;
		players = new Player[6];
		while(inputStream.hasNext()){
			String data = inputStream.nextLine();
			String[] foo = data.split(",\\s*");
			
			String name = foo[0];
			String color = foo[1];
			String type = foo[2];
			int startingRow = Integer.parseInt(foo[3]);
			int startingCol = Integer.parseInt(foo[4]);
			
			if(counter == 0){
				HumanPlayer player = new HumanPlayer();
				player.setPlayerName(name);
				player.setColor(color);
				player.setRow(startingRow);
				player.setColumn(startingCol);
				players[counter] = player;
			}else {
				ComputerPlayer player = new ComputerPlayer();
				player.setPlayerName(name);
				player.setColor(color);
				player.setRow(startingRow);
				player.setColumn(startingCol);
				players[counter] = player;
			}
			counter++;
		}
		inputStream.close();
		}catch(FileNotFoundException e) {
			System.out.println("File not found, please correct file name.");
		}
		
		//Cards Loading
		try {
		File file = new File(cardsConfigFile);
		Scanner inputStream = new Scanner(file);
		int counter = 0;
		cards = new Card[23];
		while(inputStream.hasNext()){
			String data = inputStream.nextLine();
			if(counter <= 10) {
				cards[counter] = new Card();
				cards[counter].setCardName(data);
				cards[counter].setCardType(CardType.ROOM);
			} else if(counter > 10 && counter <= 16){
				cards[counter] = new Card();
				cards[counter].setCardName(data);
				cards[counter].setCardType(CardType.PERSON);
			} else{
				cards[counter] = new Card();
				cards[counter].setCardName(data);
				cards[counter].setCardType(CardType.WEAPON);
			}
			counter++;
		}
		inputStream.close();
		}catch(FileNotFoundException e) {
			System.out.println("File not found, please correct file name.");
		}
		
		
		//////////////////////////
		// deals the cards
		///////////////////////////
		
		Random r = new Random();
		int counter = 0;
		ArrayList<Card> dealingCards = new ArrayList<Card>(); //dealingCards holds current cards needed to be dealt
		for(int i = 0; i < cards.length; i++){
			dealingCards.add(cards[i]);
		}
		
		//Deals 3 cards to the envelope
		Card room = dealingCards.get(r.nextInt(11));
		dealingCards.remove(room);
		envelope.add(room);
		Card player = dealingCards.get(r.nextInt(6)+10);
		dealingCards.remove(player);
		envelope.add(player);
		Card weapon = dealingCards.get(r.nextInt(6)+15);
		dealingCards.remove(weapon);
		envelope.add(weapon);
		
		//Loops through undealt cards and deals them
		while(dealingCards.size() > 0){
			counter++;
			Player chosenP = players[(counter % players.length)];
			
			int randCard = r.nextInt(dealingCards.size());
			Card rCard = dealingCards.get(randCard);
			chosenP.addCard(rCard);
			dealingCards.remove(rCard);
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
			System.out.println("File not found, please correct file name.");
		}
	}

	public void initialize() {
		// the following populates board and sets certain attributes for each BoardCell
		File file1 = new File(boardConfigFile);
		int i = 0;
		
		try {
			Scanner input = new Scanner(file1);
			while(input.hasNext()){
				String nextRow = input.nextLine();
				String[] roomLine = nextRow.split(",");
				
				for(int j = 0; j < roomLine.length; j++){
					clueGame.BoardCell bc = new clueGame.BoardCell();
					bc.setCol(j);
 					bc.setRow(i);
 					bc.setInitial(roomLine[j].charAt(0));
 					if(roomLine[j].length() == 2 && roomLine[j].charAt(1) != 'N'){
						if(roomLine[j].charAt(1) == 'U') {
							bc.setDoorDirection(DoorDirection.UP);
						}
						else if(roomLine[j].charAt(1) == 'R') {
							bc.setDoorDirection(DoorDirection.RIGHT);
						}
						else if(roomLine[j].charAt(1) == 'D') {
							bc.setDoorDirection(DoorDirection.DOWN);
						} else {
							bc.setDoorDirection(DoorDirection.LEFT);
						}
						
						bc.setDoorway(true);
						bc.setRoom(false);
						bc.setWalkway(false);
 					} else if (roomLine[j].charAt(0) == 'W'){
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
			System.out.println("File not found, please correct file name.");
		}
	}
	
	public void calcAdjacencies(){
		for(int i = 0; i < board.length; ++i){
			for(int j = 0; j < board[i].length; ++j){
				HashSet<BoardCell> adjacencies = new HashSet<BoardCell>();
				if(board[i][j].isWalkway() == true){ // calcs adjacencies for cells that are walkways
					
					if(i-1 >= 0 && board[i-1][j].isRoom() == false){
						if(board[i-1][j].isDoorway() != true || board[i-1][j].getDoorDirection() == DoorDirection.DOWN){
							adjacencies.add(board[i-1][j]);
						}
					}
					if(i+1 < numRows && board[i+1][j].isRoom() == false){
						if(board[i+1][j].isDoorway() != true || board[i+1][j].getDoorDirection() == DoorDirection.UP){
							adjacencies.add(board[i+1][j]);
						}
					}
					if(j-1 >= 0 && board[i][j-1].isRoom() == false){
						if(board[i][j-1].isDoorway() != true || board[i][j-1].getDoorDirection() == DoorDirection.RIGHT){
							adjacencies.add(board[i][j-1]);
						}
					}
					if(j+1 < numColumns && board[i][j+1].isRoom() == false){
						if(board[i][j+1].isDoorway() != true || board[i][j+1].getDoorDirection() == DoorDirection.LEFT){
						adjacencies.add(board[i][j+1]);
						}
					}
				} else if(board[i][j].isDoorway() == true){ // puts the one cell in front of a door in the doors adjacency list
					if(board[i][j].getDoorDirection() == DoorDirection.RIGHT){
						adjacencies.add(board[i][j+1]);
					}
					if(board[i][j].getDoorDirection() == DoorDirection.LEFT){
						adjacencies.add(board[i][j-1]);
					}
					if(board[i][j].getDoorDirection() == DoorDirection.DOWN){
						adjacencies.add(board[i+1][j]);
					}
					if(board[i][j].getDoorDirection() == DoorDirection.UP){
						adjacencies.add(board[i-1][j]);
					}
				}
				// cells in a room get empty adjacency lists
				
				adjMtx.put(board[i][j], adjacencies);
			}
		}
	}
	
	public Set<BoardCell> getAdjList(int row, int col){
		calcAdjacencies();
		return adjMtx.get(board[row][col]);
	}

	public void doCalcTargets(int row, int col, int pathLength){
		for (BoardCell adjacent : AdjList(board[row][col])) {
			if (visited.contains(adjacent)) {
				continue;
			}
			
			if(adjacent.isDoorway() && pathLength>0){
				targets.add(adjacent);
			}
			
			visited.add(adjacent);
			if (pathLength == 1) {
				targets.add(adjacent);
			}
			else {
				doCalcTargets(adjacent.getRow(), adjacent.getCol(), pathLength - 1);
			}
			visited.remove(adjacent);
		}
	}
	//////////////////////
	// Tests accusation
	//////////////////////
	public boolean checkAccusation(Card person, Card weapon, Card room){
		// accusationStatus initially assumes it it correct, and will turn false if contradicted by later tests
		boolean accusationStatus = false; 
		
		
		return accusationStatus;
		
	}
	

	public void calcTargets(int i, int j, int path){
		targets.clear();
		visited.clear();
		visited.add(board[i][j]);
		doCalcTargets(i,j,path);
	}
	
	
	public HashSet<BoardCell> AdjList(BoardCell cell){
		calcAdjacencies();
		return (HashSet<BoardCell>) adjMtx.get(cell);
	}
	
	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException{
		
		// handles "testRooms" tests
		File file = new File(roomConfigFile);
		try(Scanner inputStream = new Scanner(file)){
			while(inputStream.hasNext()){
				String data = inputStream.nextLine();
				String[] lineContents = data.split(",\\s*");
	
				String first = lineContents[0];
				String second = lineContents[1];
				String third = lineContents[2];
				if(third != "Card" || third != "Other") {
					throw new BadConfigFormatException();     // throws if the third word is anything but card or other
				}
				char c = first.charAt(0);
	
				legend.put(c, second);
				}
		}
	}
	
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		// handles exception tests
		
		File file1 = new File(boardConfigFile);
		int count = 0;

			try(Scanner input = new Scanner(file1)){
				while(input.hasNext()){
					String nextLine = input.nextLine();
					String[] roomLine = nextLine.split(",");
					setNumColumns(roomLine.length);
					for(int i = 0; i < numColumns; i++) {
						for(int j = 0; j < roomLine.length; ++j) {
							board[i][count].setInitial(roomLine[j].charAt(0));
							boolean isIn = false;
							for(char c: legend.keySet()) {
								if(roomLine[j].charAt(0) == c) isIn = true;
							}
							if(!isIn) throw new BadConfigFormatException(); // throws if the char is not in the legend
						}
					}
					count++;
				}
			}
			setNumRows(count);
			if(numColumns != numRows) throw new BadConfigFormatException(); // throws if cols ! = rows
	}
	
	public clueGame.BoardCell getCellAt(int i, int j) {
		return board[i][j];
	}
	
	public void selecTAnswer(){
		
	}
	
	public Card handleSuggestion(){
		Card c = new Card();
		return c;
	}
	
	public boolean checkAccusation(Solution accusation){
		return true;
	}
	
	public Card[] getCards() {
		return cards;
	}
	
	public Player[] getPlayers() {
		return players;
	}
	
	public ArrayList<Card> getEnvelope() {
		return envelope;
	}
	
}
