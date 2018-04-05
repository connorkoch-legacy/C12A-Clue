package tests;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Player;
import clueGame.HumanPlayer;
import clueGame.ComputerPlayer;
import java.awt.Color;
import java.util.ArrayList;


public class gameActionTests {
	private static Board board;
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt", "Data/CTest_CluePlayers.txt", "Data/CTest_ClueCards.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}
	
	//Test Select a Target
	
	@Test
	public void testTargetRandomSelection(){
		 ComputerPlayer player = new ComputerPlayer();
		 // Pick a location with no rooms in target, just three targets
		 board.calcTargets(14, 0, 2);
		 boolean loc_12_0 = false;
		 boolean loc_14_2 = false;
		 boolean loc_15_1 = false;
		 // Run the test a large number of times
		 for (int i=0; i<100; i++) {
		 BoardCell selected = player.pickLocation(board.getTargets());
		 if (selected == board.getCellAt(12, 0))
		 loc_12_0 = true;
		 else if (selected == board.getCellAt(14, 2))
		 loc_14_2 = true;
		 else if (selected == board.getCellAt(15, 1))
		 loc_15_1 = true;
		 else
		 fail("Invalid target selected");
		 }
		 // Ensure each target was selected at least once
		 assertTrue(loc_12_0);
		 assertTrue(loc_14_2);
		 assertTrue(loc_15_1);
	}
	
	@Test
	public void testAlwaysGoesToRoom(){
		
	}
	
	@Test
	public void testPreviouslyLeftRoomRandomSelection(){
		
	}
	
	//Accusation tests
	
	@Test
	public void testCorrectSolution(){
		
	}
	
	@Test
	public void testSolutionWithWrongPerson(){
		
	}
	
	@Test
	public void testSolutionWithWrongWeapon(){
		
	}
	
	@Test
	public void testSolutionWithWrongRoom(){
		
	}
	
	// Test suggestions
	
	@Test
	public void testRoomMatchesCurrentLocation(){
		
	}
	
	@Test
	public void testOneWeaponUnseen(){
		
		
	}
	
	@Test
	public void testOnePersonUnseen(){
		
	}
	
	@Test
	public void testWeaponRandomlySelected(){
		
	}
	
	@Test
	public void testPersonRandomlySelected(){
		
	}
	
	// Test Disprove suggestion - ComputerPlayer
	
	@Test
	public void testOneMatchingCard(){
		
	}
	
	@Test
	public void testMoreThanOneMatchingCard(){
		
	}
	
	@Test
	public void testNoMatchingCards(){
		
	}
	
	//Test HAndle Suggestions - Board
	
	@Test
	public void testNoOneCanDisprove(){
		
	}
	
	@Test
	public void testOnlyAccusingPlayerCanDisprove(){
		
	}
	
	@Test
	public void testOnlyHumanCanDisprove(){
		
	}
	
	@Test
	public void testOnlyHumanCanDisproveButAccuser(){
		
	}
	
	@Test
	public void testTwoPlayersCanDisprove(){
		
	}
	
	@Test
	public void testHumanAndAnotherPlayerCanDisprove(){
		
	}
}
