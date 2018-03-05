package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

/**
 * Tests the IntBoard
 * @author Daniel Winternitz && Connor Koch
 *
 */

public class IntBoardTest {
	private IntBoard ib;
	@Before
	public void beforeAll(){
		ib = new IntBoard();
	}
	
	/**
	 * Tests adjacencies for piece at (0,0)
	 */
	@Test
	public void testAdjacencies0_0() {
		BoardCell cell = IntBoard.board[0][0];
		HashSet<BoardCell> testList = ib.AdjList(cell);
		assertTrue(testList.contains(IntBoard.board[1][0]));
		assertTrue(testList.contains(IntBoard.board[0][1]));
		assertEquals(2,testList.size());
	}

	/**
	 * Tests adjacencies for piece at (3,3)
	 */
	@Test
	public void testAdjacencies3_3() {
		BoardCell cell = IntBoard.board[3][3];
		HashSet<BoardCell> testList = ib.AdjList(cell);
		assertTrue(testList.contains(IntBoard.board[3][2]));
		assertTrue(testList.contains(IntBoard.board[2][3]));
		assertEquals(2,testList.size());
	}
	
	/**
	 * Tests adjacencies for piece at (1,3)
	 */
	@Test
	public void testAdjacencies1_3() {
		BoardCell cell = IntBoard.board[1][3];
		HashSet<BoardCell> testList = ib.AdjList(cell);
		assertTrue(testList.contains(IntBoard.board[0][3]));
		assertTrue(testList.contains(IntBoard.board[2][3]));
		assertTrue(testList.contains(IntBoard.board[1][2]));
		assertEquals(3,testList.size());
	}
	
	/**
	 * Tests adjacencies for piece at (3,0)
	 */
	@Test
	public void testAdjacencies3_0() {
		BoardCell cell = IntBoard.board[3][0];
		HashSet<BoardCell> testList = ib.AdjList(cell);
		assertTrue(testList.contains(IntBoard.board[2][0]));
		assertTrue(testList.contains(IntBoard.board[3][1]));
		assertEquals(2,testList.size());
	}
	
	/**
	 * Tests adjacencies for piece at (1,1)
	 */
	@Test
	public void testAdjacencies1_1() {
		BoardCell cell = IntBoard.board[1][1];
		HashSet<BoardCell> testList = ib.AdjList(cell);
		assertTrue(testList.contains(IntBoard.board[1][2]));
		assertTrue(testList.contains(IntBoard.board[1][0]));
		assertTrue(testList.contains(IntBoard.board[0][1]));
		assertTrue(testList.contains(IntBoard.board[2][1]));
		assertEquals(4,testList.size());
	}
	/**
	 * Tests adjacencies for piece at (2,2)
	 */
	@Test
	public void testAdjacencies2_2() {
		BoardCell cell = IntBoard.board[2][2];
		HashSet<BoardCell> testList = ib.AdjList(cell);
		assertTrue(testList.contains(IntBoard.board[1][2]));
		assertTrue(testList.contains(IntBoard.board[3][2]));
		assertTrue(testList.contains(IntBoard.board[2][1]));
		assertTrue(testList.contains(IntBoard.board[2][3]));
		assertEquals(4,testList.size());
	}
	// below are targets tests
	
	/**
	 * tests target list for piece (0,0)
	 */
	
	@Test
	public void testTargets_0_0_3(){
		BoardCell cell = IntBoard.board[0][0];
		ib.prepCalcTargets(IntBoard.board[0][0]);
		//ib.targets.clear();
		//ib.visited.clear();
		//ib.visited.add(ib.board[0][0]);
		ib.calcTargets(cell, 3);
		HashSet<BoardCell> targets = ib.getTargets();
		assertTrue(targets.contains(IntBoard.board[3][0]));
		assertTrue(targets.contains(IntBoard.board[1][2]));
		assertTrue(targets.contains(IntBoard.board[0][1]));
		assertTrue(targets.contains(IntBoard.board[2][1]));
		assertTrue(targets.contains(IntBoard.board[0][3]));
		assertTrue(targets.contains(IntBoard.board[1][0]));
		assertEquals(6,targets.size());
		
	}
	/**
	 * tests target list for piece (0,0)
	 */
	@Test
	public void testTargets_0_0_2(){
		BoardCell cell = IntBoard.board[0][0];
		ib.prepCalcTargets(IntBoard.board[0][0]);
		//ib.targets.clear();
		//ib.visited.clear();
		//ib.visited.add(ib.board[0][0]);
		ib.calcTargets(cell, 2);
		HashSet<BoardCell> targets = ib.getTargets();
		assertTrue(targets.contains(IntBoard.board[2][0]));
		assertTrue(targets.contains(IntBoard.board[0][2]));
		assertTrue(targets.contains(IntBoard.board[1][1]));
		assertEquals(3,targets.size());
		
	}
	/**
	 * tests target list for piece (3,3)
	 */
	@Test
	public void testTargets_3_3_3(){
		BoardCell cell = IntBoard.board[3][3];
		ib.prepCalcTargets(IntBoard.board[3][3]);
		//ib.targets.clear();
		//ib.visited.clear();
		//ib.visited.add(ib.board[3][3]);
		ib.calcTargets(cell, 3);
		HashSet<BoardCell> targets = ib.getTargets();
		assertTrue(targets.contains(IntBoard.board[3][0]));
		assertTrue(targets.contains(IntBoard.board[2][1]));
		assertTrue(targets.contains(IntBoard.board[3][2]));
		assertTrue(targets.contains(IntBoard.board[1][2]));
		assertTrue(targets.contains(IntBoard.board[0][3]));
		assertTrue(targets.contains(IntBoard.board[2][3]));
		assertEquals(6,targets.size());
		
	}
	/**
	 * tests target list for piece (3,3)
	 */
	@Test
	public void testTargets_3_3_2(){
		BoardCell cell = IntBoard.board[3][3];
		ib.prepCalcTargets(IntBoard.board[3][3]);
		//ib.targets.clear();
		//ib.visited.clear();
		//ib.visited.add(ib.board[3][3]);
		ib.calcTargets(cell, 2);
		HashSet<BoardCell> targets = ib.getTargets();
		assertTrue(targets.contains(IntBoard.board[2][2]));
		assertTrue(targets.contains(IntBoard.board[3][1]));
		assertTrue(targets.contains(IntBoard.board[1][3]));
		assertEquals(3,targets.size());
		
	}
	
	/**
	 * tests target list for piece (1,1)
	 */
	
	@Test
	public void testTargets_1_1_2(){
		BoardCell cell = IntBoard.board[1][1];
		ib.prepCalcTargets(IntBoard.board[1][1]);
		//ib.targets.clear();
		//ib.visited.clear();
		//ib.visited.add(ib.board[1][1]);
		ib.calcTargets(cell, 2);
		HashSet<BoardCell> targets = ib.getTargets();
		assertTrue(targets.contains(IntBoard.board[0][0]));
		assertTrue(targets.contains(IntBoard.board[0][2]));
		assertTrue(targets.contains(IntBoard.board[2][0]));
		assertTrue(targets.contains(IntBoard.board[1][3]));
		assertTrue(targets.contains(IntBoard.board[3][1]));
		assertTrue(targets.contains(IntBoard.board[2][2]));
		assertEquals(6,targets.size());
		
	}
	
	/**
	 * tests target list for piece (1,1)
	 */
	
	@Test
	public void testTargets_1_1_1(){
		BoardCell cell = IntBoard.board[1][1];
		ib.prepCalcTargets(IntBoard.board[1][1]);
		//ib.targets.clear();
		//ib.visited.clear();
		//ib.visited.add(ib.board[1][1]);
		ib.calcTargets(cell, 1);
		HashSet<BoardCell> targets = ib.getTargets();
		assertTrue(targets.contains(IntBoard.board[1][0]));
		assertTrue(targets.contains(IntBoard.board[0][1]));
		assertTrue(targets.contains(IntBoard.board[2][1]));
		assertTrue(targets.contains(IntBoard.board[1][2]));
		assertEquals(4,targets.size());
		
	}
}
