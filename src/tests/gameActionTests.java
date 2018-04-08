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
		board.setConfigFiles("Data/OurClueBoardCSV.csv", "Data/ClueRooms.txt", "Data/CTest_CluePlayers.txt", "Data/CTest_ClueCards.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}
	
	//Test Select a Target
	
	@Test
	public void testTargetRandomSelection(){
		 ComputerPlayer player = new ComputerPlayer();
		 // Pick a location with no rooms in target, just three targets
		 board.calcTargets(17, 10, 1);
		 boolean left = false;
		 boolean right = false;
		 boolean up = false;
		 // Run the test a large number of times
		 for (int i=0; i<100; i++) {
		 BoardCell selected = player.pickLocation(board.getTargets());
		 if (selected == board.getCellAt(17, 9))
		 left = true;
		 else if (selected == board.getCellAt(17, 11))
		 right = true;
		 else if (selected == board.getCellAt(16, 10))
		 up = true;
		 else
		 fail("Invalid target selected");
		 }
		 // Ensure each target was selected at least once
		 assertTrue(left);
		 assertTrue(right);
		 assertTrue(up);
	}
	
	@Test
	public void testAlwaysGoesToRoom(){
		ComputerPlayer player = new ComputerPlayer();
		board.calcTargets(4, 2, 1);
		boolean up = false;
		boolean right = false;
		boolean down = false;
		for(int i = 0; i < 100; i++){
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(3, 2))
				 up = true;
				 else if (selected == board.getCellAt(4, 3))
				 right = true;
				 else if (selected == board.getCellAt(5, 2))
				 down = true;
				 else
				 fail("Invalid target selected");
				 }
		// only door should have been visited
			assertTrue(up);
			assertFalse(right);
			assertFalse(down);
			
		}
		
		
		
	
	
	@Test
	public void testPreviouslyLeftRoomRandomSelection(){
		ComputerPlayer player = new ComputerPlayer();
		board.calcTargets(4, 2, 1);
		player.setPrevRoom(board.getCellAt(3, 2)); // marks door as previously visited
		boolean up = false;
		boolean right = false;
		boolean down = false;
		for(int i = 0; i < 100; i++){
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(3, 2))
				 up = true;
				 else if (selected == board.getCellAt(4, 3))
				 right = true;
				 else if (selected == board.getCellAt(5, 2))
				 down = true;
				 else
				 fail("Invalid target selected");
				 }
		// all should be visited
			assertTrue(up);
			assertTrue(right);
			assertTrue(down);
	}
	
	//Accusation tests
	
	@Test
	public void testCorrectAccusation(){
		ArrayList<Card> envelope = new ArrayList<Card>();
		envelope = board.getEnvelope();
		Card[] cards = board.getCards();
		Card personGuess = null;
		Card weaponGuess = null;
		Card roomGuess = null;
		for(Card c: envelope){
			if(c.getCardType() == CardType.PERSON){
				personGuess = c;
			}else if(c.getCardType() == CardType.WEAPON){
				weaponGuess = c;
			}else if(c.getCardType() == CardType.ROOM){
				roomGuess = c;
			}
		}
		assertTrue(board.checkAccusation(personGuess, weaponGuess, roomGuess));
	}
	
	@Test
	public void testAccusationWithWrongPerson(){
		
	}
	
	@Test
	public void testAccusationWithWrongWeapon(){
		
	}
	
	@Test
	public void testAccusationWithWrongRoom(){
		
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
