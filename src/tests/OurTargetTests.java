package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class OurTargetTests {
	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 25;
	public static final int NUM_COLUMNS = 25;
	public static final int NUM_DOORS = 16;
	
	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("Data/OurClueBoardCSV.csv", "Data/ClueRooms.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}
	// The following tests targets along a walkway
	@Test
	public void testTargetsAlongWalkway() {
		// tests block (5,24) with pathLength 2
		board.calcTargets(5, 24, 2);
		Set<BoardCell> targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(5, 22)));
		assertTrue(targets.contains(board.getCellAt(7, 22)));
		assertTrue(targets.contains(board.getCellAt(6, 23)));
		assertEquals(3, targets.size());
		// tests block (14,19) with pathlength 1
		board.calcTargets(14, 19, 1);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(14, 20)));
		assertTrue(targets.contains(board.getCellAt(14, 18)));
		assertTrue(targets.contains(board.getCellAt(13, 19)));
		assertTrue(targets.contains(board.getCellAt(15, 19)));
		assertEquals(4, targets.size());
		// tests block (12,3) with pathLength 2
		board.calcTargets(12, 3, 2);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(12, 1)));
		assertTrue(targets.contains(board.getCellAt(12, 5)));
		assertTrue(targets.contains(board.getCellAt(11, 2)));
		assertTrue(targets.contains(board.getCellAt(11, 4)));
		assertTrue(targets.contains(board.getCellAt(13, 2)));
		assertTrue(targets.contains(board.getCellAt(13, 4)));
		assertEquals(6, targets.size());
		// tests block (24,8) with pathLength 3
		board.calcTargets(24, 8, 3);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(22, 9)));
		assertTrue(targets.contains(board.getCellAt(23, 10)));
		assertTrue(targets.contains(board.getCellAt(24, 9)));
		assertTrue(targets.contains(board.getCellAt(24, 11)));
		assertTrue(targets.contains(board.getCellAt(23, 8)));
		assertEquals(5, targets.size());
		
		
	}
	// The following tests targets in fornt of a door
	@Test
	public void testEnterRoom(){
		// folowing tests in front of a left-facing door
		board.calcTargets(0, 13, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(1, 13)));
		assertTrue(targets.contains(board.getCellAt(0, 12)));
		assertTrue(targets.contains(board.getCellAt(0, 14)));
		// following tests in front of an up-facing door
		board.calcTargets(0, 21, 1);
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 20)));
		assertTrue(targets.contains(board.getCellAt(0, 22)));
	}
	
	@Test
	public void testLeavingRoom(){
		// following tests leaving a right-facing door
		board.calcTargets(5, 13, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(6, 12)));
		assertTrue(targets.contains(board.getCellAt(6, 14)));
		assertTrue(targets.contains(board.getCellAt(7, 13)));
		// following tests leaving an up-facing door
		board.calcTargets(23, 14, 1);
		targets = board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(23, 13)));
		
		
	}

}
