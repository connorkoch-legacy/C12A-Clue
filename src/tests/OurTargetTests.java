// Daniel Winternitz and Connor Koch
package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ClueGameGUI;

public class OurTargetTests {
	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 25;
	public static final int NUM_COLUMNS = 25;
	public static final int NUM_DOORS = 16;
	
	private static Board board;
	private ClueGameGUI start = new ClueGameGUI();
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
	//	board = Board.getInstance();
		// set the file names to use my config files
		//board.setConfigFiles("Data/OurClueBoardCSV.csv", "Data/ClueRooms.txt", "Data/CTest_CluePlayers.txt", "Data/CTest_ClueCards.txt");		
		// Initialize will load BOTH config files 
		//board.initialize();
		ClueGameGUI start = new ClueGameGUI();
	}
	// The following tests targets along a walkway
	@Test
	public void testTargetsAlongWalkway() {
		// tests block (5,24) with pathLength 2
		Board board = Board.getInstance();
		board.calcTargets(13, 5, 2);
		board.calcTargets(24, 5, 2);
		Set<BoardCell> targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(22, 5)));
		assertTrue(targets.contains(board.getCellAt(24, 7)));
		assertTrue(targets.contains(board.getCellAt(23, 6)));
		assertEquals(3, targets.size());
		// tests block (14,19) with pathlength 1
		board.calcTargets(19, 14, 1);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(20, 14)));
		assertTrue(targets.contains(board.getCellAt(18, 14)));
		assertTrue(targets.contains(board.getCellAt(19, 13)));
		assertTrue(targets.contains(board.getCellAt(19, 15)));
		assertEquals(4, targets.size());
		// tests block (12,3) with pathLength 2
		board.calcTargets(3, 12, 2);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(1, 12)));
		assertTrue(targets.contains(board.getCellAt(5, 12)));
		assertTrue(targets.contains(board.getCellAt(2, 11)));
		assertTrue(targets.contains(board.getCellAt(4,11)));
		assertTrue(targets.contains(board.getCellAt(2, 13)));
		assertTrue(targets.contains(board.getCellAt(4, 13)));
		assertEquals(6, targets.size());
		// tests block (24,8) with pathLength 3
		board.calcTargets(8, 24, 3);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(9, 22)));
		assertTrue(targets.contains(board.getCellAt(10, 23)));
		assertTrue(targets.contains(board.getCellAt(9, 24)));
		assertTrue(targets.contains(board.getCellAt(11, 24)));
		assertTrue(targets.contains(board.getCellAt(8, 23)));
		assertEquals(5, targets.size());
		
		
	}
	// The following tests targets in fornt of a door
	@Test
	public void testEnterRoom(){
		// folowing tests in front of a left-facing door
		Board board = Board.getInstance();
		board.calcTargets(13, 5, 2);
		board.calcTargets(13, 0, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(13, 1)));
		assertTrue(targets.contains(board.getCellAt(12, 0)));
		assertTrue(targets.contains(board.getCellAt(14, 0)));
		// following tests in front of an up-facing door
		board.calcTargets(21, 0, 1);
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(20, 0)));
		assertTrue(targets.contains(board.getCellAt(22, 0)));
	}
	
	@Test
	public void testLeavingRoom(){
		// following tests leaving a right-facing door
		Board board = Board.getInstance();
		board.calcTargets(13, 5, 2);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(12, 6)));
		assertTrue(targets.contains(board.getCellAt(14, 6)));
		assertTrue(targets.contains(board.getCellAt(13, 7)));
		// following tests leaving an up-facing door
		board.calcTargets(14, 23, 1);
		targets = board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(13, 23)));
		
		
	}

}
