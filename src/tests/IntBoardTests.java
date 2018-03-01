package tests;
import experiment.BoardCell;
//import static org.junit.Assert.*;
import experiment.IntBoard;
public class IntBoardTests {
	IntBoard intBoard = new IntBoard();
	BoardCell b = new BoardCell();
	b.setRow(0);
	b.setCol(0);
	
	public void setUp(){	
	}
	
	@Test
	public void adjacencyListTest(){
		intBoard.calcAdjacencies();
		intBoard.AdjList();
		
	}
	
	
}
