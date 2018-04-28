// ours?
package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ClueGameGUI;
import clueGame.DoorDirection;

public class FileInitTests {
	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 25;
	public static final int NUM_COLUMNS = 25;
	public static final int NUM_DOORS = 16;
	
	private static Board board;
	private ClueGameGUI start = new ClueGameGUI();
	@BeforeClass
	public static void setUp() {
		ClueGameGUI start = new ClueGameGUI();
		// Board is singleton, get the only instance
		//board = Board.getInstance();
		// set the file names to use my config files
		//board.setConfigFiles("Data/OurClueBoardCSV.csv", "Data/ClueRooms.txt", "Data/CTest_CluePlayers.txt", "Data/CTest_ClueCards.txt");		
		// Initialize will load BOTH config files 
		//board.initialize();
		//ClueGameGUI start = new ClueGameGUI();
	}
	
	@Test
	public void testRooms() {
		// Get the map of initial => room 
		Board board = Board.getInstance();
		Map<Character, String> legend = board.getLegend();
		// Ensure we read the correct number of rooms
		assertEquals(LEGEND_SIZE, legend.size());
		
		assertEquals("Conservatory", legend.get('C'));
		assertEquals("Kitchen", legend.get('K'));
		assertEquals("Bathroom", legend.get('T'));
		assertEquals("Cellar", legend.get('L'));
		assertEquals("Laundry room", legend.get('D'));
		assertEquals("Attic", legend.get('A'));
		assertEquals("Vault", legend.get('V'));
		assertEquals("Ballroom", legend.get('B'));
		assertEquals("Billiard room", legend.get('R'));
		assertEquals("Walkway", legend.get('W'));
		assertEquals("Closet", legend.get('X'));

	}
	
	@Test
	public void testBoardDimensions() {
		// Ensure we have the proper number of rows and columns
		Board board = Board.getInstance();
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());		
	}
	
	@Test
	public void testDoorDirections(){
		//these tests make sure that the given cells are doors, and that these doors open in a direction that matches our board layout
		BoardCell cell = board.getCellAt(3, 2);
		System.out.println(cell.getDoorDirection());
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.DOWN, cell.getDoorDirection());
		cell = board.getCellAt(13, 1);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.LEFT, cell.getDoorDirection());
		cell = board.getCellAt(13, 5);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.RIGHT, cell.getDoorDirection());
		cell = board.getCellAt(22, 0);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.UP, cell.getDoorDirection());
		
		cell = board.getCellAt(1, 1);
		assertFalse(cell.isDoorway());
		cell = board.getCellAt(2, 2);
		assertFalse(cell.isDoorway());
		
	}
	
	@Test
	public void testNumDoors(){
		//esnsure the number of doors read = 16
		int numDoors = 0;
		Board board = Board.getInstance();
		for (int row=0; row<board.getNumRows(); row++)
			for (int col=0; col<board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		assertEquals(NUM_DOORS, numDoors);
	}
	
	@Test
	public void testCharInitial(){
		//tests that the given cell has initials matching our board layout
		BoardCell cell = board.getCellAt(1, 1);
		assertEquals('A', cell.getInitial());
		cell = board.getCellAt(8, 1);
		assertEquals('W', cell.getInitial());
		cell = board.getCellAt(11, 1);
		assertEquals('V', cell.getInitial());
	}
	
	
	
	
	
	
	
}
