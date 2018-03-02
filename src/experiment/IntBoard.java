package experiment;
import java.util.*;

/**
 * Creates a game board and calculates relationships between different pieces aon the board
 * @author Daniel Winternitz && Connor Koch
 *
 */

public class IntBoard {
	
	public static final int BOARD_SIZE = 4;
	public static BoardCell[][] board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
	
	
	public HashSet<BoardCell> visited = new HashSet<BoardCell>();
	
	private Map<BoardCell, HashSet<BoardCell>> adjMtx = new HashMap<BoardCell, HashSet<BoardCell>>();
	public HashSet<BoardCell> targets = new HashSet<BoardCell>();
	
	public IntBoard(){
		for(int i = 0; i < board.length; ++i){
			for(int j = 0; j < board[i].length; ++j){
				board[i][j] = new BoardCell();
				board[i][j].setCol(i);
				board[i][j].setRow(j);
			}
		}
		calcAdjacencies();
	}
	/**
	 * Calculates agacent pieces and stores the in a map
	 */
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
	/**
	 * returns set of adjacent pieces
	 * @param cell
	 * @return
	 */
	public HashSet<BoardCell> AdjList(BoardCell cell){
		for(BoardCell i: adjMtx.get(cell)){
			i.getRow();
		}
		return adjMtx.get(cell);
	}
	/**
	 * Calculates targets base don a given space and move distance
	 * @param startCell
	 * @param pathLength
	 */
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
	/**
	 * must be called before calling calcTargets
	 * @param startCell
	 */
	public void prepCalcTargets(BoardCell startCell){
		targets.clear();
		visited.clear();
		visited.add(startCell);
		
	}
	/**
	 * returns targets set
	 * @return
	 */
	public HashSet<BoardCell> getTargets(){
		return targets;
	}
	
	public static void main(String[] args) {
		IntBoard ib = new IntBoard();
		//ib.calcAdjacencies();
		//HashSet<BoardCell> test = new HashSet<BoardCell>();
		System.out.println(ib.AdjList(board[1][1]));
		
		
		}

	
}
