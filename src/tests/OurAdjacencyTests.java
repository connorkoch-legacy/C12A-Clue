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
import clueGame.DoorDirection;

public class OurAdjacencyTests {
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
	
	//Location within room should have empty adj list
	@Test
	public void TestAdjacenciesInsideRoom(){
		//Test to see adjacency lists in rooms are empty
		Set<BoardCell> tester = board.getAdjList(1, 0);
		assertEquals(0, tester.size());
		tester = board.getAdjList(3, 12);
		assertEquals(0, tester.size());
		tester = board.getAdjList(4, 25);
		assertEquals(0, tester.size());
		
		
	}
	
	//Locations with only walkways as adjacent locations
	@Test
	public void testAdjacenciesWalkway(){
		//Test to see that walkway near room and wall only contains two adj cells
		Set<BoardCell> testList = board.getAdjList(5, 0);
		assertTrue(testList.contains(board.getCellAt(6, 0)));
		assertTrue(testList.contains(board.getCellAt(5, 1)));
		//System.out.println(testList.size());
		assertEquals(2, testList.size());
		
		//Test adj list has two cells at the corner of a room
		testList = board.getAdjList(19, 4);
		assertTrue(testList.contains(board.getCellAt(19, 3)));
		assertTrue(testList.contains(board.getCellAt(18, 4)));
		assertEquals(2, testList.size());
	}
	
	//Test 4 corners
	@Test
	public void testEdgeAdjacencies(){
		//Test top left corner has 0 adjacencies because its in room
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
		Set<BoardCell> testList = board.getAdjList(0,21);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCellAt(0, 20)));
		assertTrue(testList.contains(board.getCellAt(0, 22)));
		//tests by a downward door way
		testList = board.getAdjList(8, 6);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(8, 5)));
		assertTrue(testList.contains(board.getCellAt(8, 4)));
		assertTrue(testList.contains(board.getCellAt(7, 6)));
		assertTrue(testList.contains(board.getCellAt(9, 6)));
		// tests by a left facing doorway
		testList = board.getAdjList(21, 23);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(21, 24)));
		assertTrue(testList.contains(board.getCellAt(20, 23)));
		assertTrue(testList.contains(board.getCellAt(22, 23)));
		// tests by a right facing door
		testList = board.getAdjList(6, 13);
		assertEquals(4, testList.size());
		assertTrue(testList.contains(board.getCellAt(5, 13)));
		assertTrue(testList.contains(board.getCellAt(7, 13)));
		assertTrue(testList.contains(board.getCellAt(6, 14)));
		assertTrue(testList.contains(board.getCellAt(6, 12)));
		
	}
	
	@Test
	public void testDoorWays(){
		// tests adjacency of left facing door
		Set<BoardCell> testList = board.getAdjList(1,13);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(0, 13)));
		// tests adjacency of right facing door
		testList = board.getAdjList(5,13);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 13)));
		// tests adjacency of upward facing door
		testList = board.getAdjList(23,14);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(23, 13)));
		// tests adjacency of downward facing door
		testList = board.getAdjList(8,5);
		//assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(8, 6)));
	}
	
	@Test
	public void besideARoom(){
		// tests below a room
		Set<BoardCell> testList = board.getAdjList(0,6);
		assertEquals(2, testList.size());
		assertTrue(testList.contains(board.getCellAt(0, 7)));
		assertTrue(testList.contains(board.getCellAt(1, 6)));
		// tests beside a room
		testList = board.getAdjList(5,22);
		assertEquals(3, testList.size());
		assertTrue(testList.contains(board.getCellAt(5, 21)));
		assertTrue(testList.contains(board.getCellAt(5, 23)));
		assertTrue(testList.contains(board.getCellAt(6, 22)));
	}
	
	
	
}