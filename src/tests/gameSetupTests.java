package tests;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import clueGame.Board;
import clueGame.Card;
import clueGame.Player;

public class gameSetupTests{
	
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
	
	//Test that players are loaded correctly
	@Test
	public void testPlayerLoading(){
		//Make a player array called players
		Player[] players = board.getPlayers();
		//Check correct number of players are loaded
		assertEquals(1, players.length);
		
	}
	
	// tests cards are loaded correctly
	@Test
	public void testCardLoading() {
		Card[] cards = board.getCards();
		assertEquals(23, cards.length);///////////////
		//int personCount;
		//int weaponCount;
		//int roomCount;
		
		
		
		
		
	}
	
	
	
	
}
