// Daniel Winternitz and Connor Koch
package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ClueGameGUI;
import clueGame.DoorDirection;

public class OurAdjacencyTests {
	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 25;
	public static final int NUM_COLUMNS = 25;
	public static final int NUM_DOORS = 16;
	
	private static Board board;
	private ClueGameGUI start = new ClueGameGUI();
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		//board = Board.getInstance();
		// set the file names to use my config files
		//board.setConfigFiles("Data/OurClueBoardCSV.csv", "Data/ClueRooms.txt", "Data/CTest_CluePlayers.txt", "Data/CTest_ClueCards.txt");		
		// Initialize will load BOTH config files 
		//board.initialize();
		ClueGameGUI start = new ClueGameGUI();
	}
	
	//Location within room should have empty adj list
	@Test
	public void TestAdjacenciesInsideRoom(){
		//Test to see adjacency lists in rooms are empty
		Board board = Board.getInstance();
		Set<BoardCell> tester = board.getAdjList(1, 0);
		assertEquals(0, tester.size());
		tester = board.getAdjList(12, 3);
		assertEquals(0, tester.size());
		tester = board.getAdjList(4, 23);
		assertEquals(0, tester.size());
		
		
	}
	
	//Locations with only walkways as adjacent locations
	@Test
	public void testAdjacenciesWalkway(){
		//Test to see that walkway near room and wall only contains two adj cells
		Board board = Board.getInstance();
		Set<BoardCell> testList = board.getAdjList(0, 5);
		assertTrue(testList.contains(board.getCellAt(0, 6)));
		assertTrue(testList.contains(board.getCellAt(1, 5)));
		//System.out.println(testList.size());
		assertEquals(2, testList.size());
		
		//Test adj list has two cells at the corner of a room
		testList = board.getAdjList(19, 4);
		assertTrue(testList.contains(board.getCellAt(19, 3)));
		assertTrue(testList.contains(board.getCellAt(19, 5)));
		assertTrue(testList.contains(board.getCellAt(18, 4)));
		assertEquals(3, testList.size());
		testList = board.getAdjList(4, 19);
		assertTrue(testList.contains(board.getCellAt(3, 19)));
		assertTrue(testList.contains(board.getCellAt(4, 18)));
		assertEquals(2, testList.size());
	}
	
	//Test 4 corners
	@Test
	public void testEdgeAdjacencies(){
		//Test top left corner has 0 adjacencies because its in room
		Board board = Board.getInstance();
		Set<BoardCell> testList = board.getAdjList(0, 0);
		assertEquals(0, testList.size());
		
		//Test top right corner has 0 adjacencies because its in room
		testList = board.getAdjList(24, 0);
		assertEquals(0, testList.size());
		
		//Test bottom left corner has 0 adjacencies because its in room
		testList = board.getAdjList(0, 24);
		assertEquals(0, testList.size());
		
		//Test bottom right corner has 0 adjacencies because its in room
		testList = board.getAdjList(24, 24);
		assertEquals(0, testList.size());
	}
	
	@Test
	public void testBesideADoor(){
		// tests by an upward doorway
		Board board = Board.getInstance();
		Set<BoardCell> testList = board.getAdjList(21,0);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCellAt(20, 0)));
		assertTrue(testList.contains(board.getCellAt(22, 0)));
		//tests by a downward door way
		testList = board.getAdjList(6, 8);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(5, 8)));
		assertTrue(testList.contains(board.getCellAt(7, 8)));
		assertTrue(testList.contains(board.getCellAt(6, 7)));
		assertTrue(testList.contains(board.getCellAt(6, 9)));
		// tests by a left facing doorway
		testList = board.getAdjList(23, 21);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(23, 20)));
		assertTrue(testList.contains(board.getCellAt(23, 22)));
		assertTrue(testList.contains(board.getCellAt(24, 21)));
		// tests by a right facing door
		testList = board.getAdjList(13, 6);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(13, 5)));
		assertTrue(testList.contains(board.getCellAt(13, 7)));
		assertTrue(testList.contains(board.getCellAt(14, 6)));
		assertTrue(testList.contains(board.getCellAt(12, 6)));
		
	}
	
	@Test
	public void testDoorWays(){
		// tests adjacency of left facing door
		Board board = Board.getInstance();
		Set<BoardCell> testList = board.getAdjList(13,1);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(13, 0)));
		// tests adjacency of right facing door
		testList = board.getAdjList(13,5);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(13, 6)));
		// tests adjacency of upward facing door
		testList = board.getAdjList(14,23);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(13, 23)));
		// tests adjacency of downward facing door
		testList = board.getAdjList(5,8);
		//assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 8)));
	}
	
	@Test
	public void besideARoom(){
		// tests below a room
		Board board = Board.getInstance();
		Set<BoardCell> testList = board.getAdjList(6,0);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCellAt(7, 0)));
		assertTrue(testList.contains(board.getCellAt(6, 1)));
		// tests beside a room
		testList = board.getAdjList(22,5);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(21, 5)));
		assertTrue(testList.contains(board.getCellAt(23, 5)));
		assertTrue(testList.contains(board.getCellAt(22, 6)));
	}
	
	
	
}