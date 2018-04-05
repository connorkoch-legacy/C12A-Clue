package tests;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import clueGame.Board;
import clueGame.Player;
import clueGame.HumanPlayer;
import clueGame.ComputerPlayer;
import java.awt.Color;

public class gameSetupTests {
	
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
		assertEquals(6, players.length);
		//1st Player (Human Player) attribute tests
		assertEquals("Miss Vivienne Scarlet", players[0].getPlayerName());
		//assertTrue(Color.RED.equals(players[2].getColor()));
		assertTrue(players[0] instanceof HumanPlayer);
		assertEquals(6, players[0].getColumn());
		assertEquals(0, players[0].getRow());
		
		//3rd Player (Computer Player) attribute tests
		assertEquals("Mrs. Blanche White", players[2].getPlayerName());
		//assertTrue(Color.WHITE.equals(players[2].getColor()));
		assertTrue(players[2] instanceof ComputerPlayer);
		assertEquals(15, players[2].getColumn());
		assertEquals(24, players[2].getRow());
		
	}
	
	//Game Actions Tests below
	
	
}
