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
	
	
	
	
	
	
}