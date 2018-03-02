package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

public class IntBoardTest {
	public IntBoard ib;
	@Before
	public void beforeAll(){
		ib = new IntBoard();
		
	}
	
	
	@Test
	public void testAdjacencies0_0() {
		BoardCell cell = ib.board[0][0];
		HashSet<BoardCell> testList = ib.AdjList(cell);
		assertTrue(testList.contains(ib.board[1][0]));
		assertTrue(testList.contains(ib.board[0][1]));
		assertEquals(2,testList.size());
	}

	
	@Test
	public void testAdjacencies3_3() {
		BoardCell cell = ib.board[3][3];
		HashSet<BoardCell> testList = ib.AdjList(cell);
		assertTrue(testList.contains(ib.board[3][2]));
		assertTrue(testList.contains(ib.board[2][3]));
		assertEquals(2,testList.size());
	}
	
	
	@Test
	public void testAdjacencies1_3() {
		BoardCell cell = ib.board[1][3];
		HashSet<BoardCell> testList = ib.AdjList(cell);
		assertTrue(testList.contains(ib.board[0][3]));
		assertTrue(testList.contains(ib.board[2][3]));
		assertTrue(testList.contains(ib.board[1][2]));
		assertEquals(3,testList.size());
	}
	
	
	@Test
	public void testAdjacencies3_0() {
		BoardCell cell = ib.board[3][0];
		HashSet<BoardCell> testList = ib.AdjList(cell);
		assertTrue(testList.contains(ib.board[2][0]));
		assertTrue(testList.contains(ib.board[3][1]));
		assertEquals(2,testList.size());
	}
	
	
	@Test
	public void testAdjacencies1_1() {
		BoardCell cell = ib.board[1][1];
		HashSet<BoardCell> testList = ib.AdjList(cell);
		assertTrue(testList.contains(ib.board[1][2]));
		assertTrue(testList.contains(ib.board[1][0]));
		assertTrue(testList.contains(ib.board[0][1]));
		assertTrue(testList.contains(ib.board[2][1]));
		assertEquals(4,testList.size());
	}
	
	@Test
	public void testAdjacencies2_2() {
		BoardCell cell = ib.board[2][2];
		HashSet<BoardCell> testList = ib.AdjList(cell);
		assertTrue(testList.contains(ib.board[1][2]));
		assertTrue(testList.contains(ib.board[3][2]));
		assertTrue(testList.contains(ib.board[2][1]));
		assertTrue(testList.contains(ib.board[2][3]));
		assertEquals(4,testList.size());
	}
}
