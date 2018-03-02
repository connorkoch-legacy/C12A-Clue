package experiment;
import java.util.*;

public class IntBoard {
	
	public static final int BOARD_SIZE = 4;
	public static BoardCell[][] board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
	
	private Map<BoardCell, HashSet<BoardCell>> adjMtx = new HashMap<BoardCell, HashSet<BoardCell>>();
	HashSet<BoardCell> targets = new HashSet<BoardCell>();
	
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
	
	public HashSet<BoardCell> AdjList(BoardCell cell){
		for(BoardCell i: adjMtx.get(cell)){
			i.getRow();
		}
		return adjMtx.get(cell);
	}
	
	public void calcTargets(BoardCell startCell, int pathLength){
		targets.clear();
		HashSet<BoardCell> visited = new HashSet<BoardCell>();
		for(BoardCell adjCell: AdjList(startCell)){
			if(!visited.contains(adjCell)){
				visited.add(adjCell);
				if(pathLength == 1){
					visited.add(adjCell);
				}
				else{
					calcTargets(adjCell, pathLength-1);
				}
				for(Iterator<BoardCell> i = visited.iterator(); i.hasNext();){
					BoardCell b = i.next();
					if(b.getCol() == adjCell.getCol() && b.getRow() == adjCell.getRow()){
						i.remove();
					}
				}
			}
		}
	}
	
	public Set<BoardCell> getTargets(){
		return targets;
	}
	
	public static void main(String[] args) {
		IntBoard ib = new IntBoard();
		//ib.calcAdjacencies();
		//HashSet<BoardCell> test = new HashSet<BoardCell>();
		System.out.println(ib.AdjList(board[1][1]));
		
		
		}

	
}
