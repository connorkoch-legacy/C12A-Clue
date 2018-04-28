package tests;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ClueGameGUI;
import clueGame.Player;
import clueGame.Solution;
import clueGame.HumanPlayer;
import clueGame.ComputerPlayer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;


public class gameActionTests extends JPanel {
	private static Board board;
	ClueGameGUI start = new ClueGameGUI();
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

	//Test Select a Target
	/**
	 * Tests the computerPlayers randomly select space to move
	 */
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
	
	/**
	 * Tests player will select room
	 */
	
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


	/**
	 * Tests player randomly selects space if previously visited room is an option
	 */


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

	/**
	 * Tests correct handling of correct accusation
	 */
	@Test
	public void testCorrectAccusation(){
		Solution answer = board.getTheAnswer();
		Solution sol = new Solution();
		//sets the guesses based on theAnswer
		sol.person = answer.person;
		sol.weapon = answer.weapon;
		sol.room = answer.room;


		assertTrue(board.checkAccusation(sol));
	}

	/**
	 * tests correct handling of accusation with wrong person
	 */
	
	@Test
	public void testAccusationWithWrongPerson(){
		Solution sol = new Solution();
		Solution answer = board.getTheAnswer();
		Card[] cards = board.getCards();

		//sets the guesses based on theAnswer
		sol.person = answer.person;
		sol.weapon = answer.weapon;
		sol.room = answer.room;


		// changes the person guess to be wrong
		if(sol.person == cards[13]){
			sol.person = cards[14];
		} else {
			sol.person = cards[13];
		}


		assertFalse(board.checkAccusation(sol));
	}

	/**
	 * tests correct handling of accusation with wrong weapon
	 */
	
	@Test
	public void testAccusationWithWrongWeapon(){
		Solution sol = new Solution();
		Solution answer = board.getTheAnswer();
		Card[] cards = board.getCards();

		//sets the guesses based on theAnswer
		sol.person = answer.person;
		sol.weapon = answer.weapon;
		sol.room = answer.room;


		// changes the person guess to be wrong
		if(sol.weapon == cards[20]){
			sol.weapon = cards[21];
		} else {
			sol.weapon = cards[20];
		}


		assertFalse(board.checkAccusation(sol));
	}

	/**
	 * tests correct handling of accusation with wrong room
	 */
	
	@Test
	public void testAccusationWithWrongRoom(){
		Solution sol = new Solution();
		Solution answer = board.getTheAnswer();
		Card[] cards = board.getCards();

		//sets the guesses based on theAnswer
		sol.person = answer.person;
		sol.weapon = answer.weapon;
		sol.room = answer.room;


		// changes the person guess to be wrong
		if(sol.room == cards[1]){
			sol.room = cards[2];
		} else {
			sol.room = cards[1];
		}


		assertFalse(board.checkAccusation(sol));
	}


	// Test suggestions
	
	/**
	 * Tests that current room is used in suggestion
	 */
	
	@Test
	public void testRoomMatchesCurrentLocation(){
		ComputerPlayer player = new ComputerPlayer();
		
		// initializes player location because the room is dependant on it
		player.setRow(3);
		player.setColumn(2);
		Solution suggestion = player.createSuggestion();
		// makes sure card for room player is in is contained in the suggestion

		assertTrue(suggestion.room.getCardName().equals("Attic"));

		// same test at a second loation
		// initializes player location because the room is dependant on it
		player.setRow(22);
		player.setColumn(0);
		suggestion = player.createSuggestion();
		// makes sure card for room player is in is contained in the suggestion

		assertTrue(suggestion.room.getCardName().equals("Laundry room"));

	}
	 
	/**
	 * tests that if only one weapon is unseen it is selected
	 */
	
	@Test
	public void testOneWeaponUnseen(){
		ComputerPlayer player = new ComputerPlayer();
		Card[] cards = Board.getCards();
		// adds all but last weapon
		for(int i = 17; i < cards.length - 1; i++){
			player.addSeenCards(cards[i]);
		}
		Solution suggestion = player.createSuggestion();

		assertTrue(suggestion.weapon.equals(cards[cards.length-1]));

	}

	/**
	 * tests that if only one person is unseen it is selected
	 */
	
	@Test
	public void testOnePersonUnseen(){
		ComputerPlayer player = new ComputerPlayer();
		Card[] cards = Board.getCards();
		// adds all but last weapon
		for(int i = 11; i < 16; i++){
			player.addSeenCards(cards[i]);
		}
		Solution suggestion = player.createSuggestion();

		assertTrue(suggestion.person.equals(cards[16]));
	}

	/**
	 * tests that weapon is randomly selected
	 */
	
	@Test
	public void testWeaponRandomlySelected(){
		ComputerPlayer player = new ComputerPlayer();
		Card[] cards = Board.getCards();
		Solution suggestion = player.createSuggestion();
		// adds all but last three weapons
		for(int i = 17; i < cards.length - 3; i++){
			player.addSeenCards(cards[i]);
		}
		boolean last = false;
		boolean secLast = false;
		boolean thirdLast = false;
		boolean fourthLast = false;
		for(int i = 0; i < 100; i++){
			suggestion = player.createSuggestion();
			if(suggestion.weapon.equals(cards[cards.length-1])){
				last = true;
			}else if(suggestion.weapon.equals(cards[cards.length-2])){
				secLast = true;
			}else if(suggestion.weapon.equals(cards[cards.length-3])){
				thirdLast = true;
			}else if(suggestion.weapon.equals(cards[cards.length-4])){
				fourthLast = true;
			}
		}
		assertTrue(last);
		assertTrue(secLast);
		assertTrue(thirdLast); // only last three should appear
		assertFalse(fourthLast);

	}

	/**
	 * tests that person is randomly selected
	 */
	
	@Test
	public void testPersonRandomlySelected(){
		ComputerPlayer player = new ComputerPlayer();
		Card[] cards = Board.getCards();
		Solution suggestion = player.createSuggestion();
		// adds all but last three people
		for(int i = 11; i < 14; i++){
			player.addSeenCards(cards[i]);
		}
		boolean last = false;
		boolean secLast = false;
		boolean thirdLast = false;
		boolean fourthLast = false;
		for(int i = 0; i < 100; i++){
			suggestion = player.createSuggestion();
			if(suggestion.person.equals(cards[16])){
				last = true;
			}else if(suggestion.person.equals(cards[15])){
				secLast = true;
			}else if(suggestion.person.equals(cards[14])){
				thirdLast = true;
			}else if(suggestion.person.equals(cards[13])){
				fourthLast = true;
			}
		}
		assertTrue(last);
		assertTrue(secLast);
		assertTrue(thirdLast);
		assertFalse(fourthLast); // makes seen one isnt added
	}

	// Test Disprove suggestion - ComputerPlayer
	
	/**
	 * This test makes sure that if a player has only one matching card, it is returned
	 */
	ComputerPlayer p = new ComputerPlayer();
	@Test
	public void testOneMatchingCard(){
		//Create a test hand for the player with 1 of each type of card
		Card c11 = new Card();
		c11.setCardName("Knife");
		p.addCard(c11);
		Card c22 = new Card();
		c22.setCardName("Bathroom");
		p.addCard(c22);
		Card c33 = new Card();
		c33.setCardName("Miss Vivienne Scarlet");
		p.addCard(c33);
		//Create the suggestion cards
		Card c1 = new Card();
		Card c2 = new Card();
		Card c3 = new Card();
		c1.setCardName("Knife");
		c2.setCardName("Attic");
		c3.setCardName("Plum");
		//Call the disprove function which tests if any of the three suggestion cards is matches the players hands, should return true for knife
		assertTrue(c1.equals(p.disprove(c1, c2, c3)));
	}
	
	/**
	 * Tests that a random card is returned if a player has more than one matching card
	 */
	@Test
	public void testMoreThanOneMatchingCard(){
		//Create a test hand for the player with 1 of each type of card
		Card c11 = new Card();
		c11.setCardName("Knife");
		p.addCard(c11);
		Card c22 = new Card();
		c22.setCardName("Bathroom");
		p.addCard(c22);
		Card c33 = new Card();
		c33.setCardName("Miss Vivienne Scarlet");
		p.addCard(c33);
		//Create the suggestion cards
		Card c1 = new Card();
		Card c2 = new Card();
		Card c3 = new Card();
		c1.setCardName("Knife");
		c2.setCardName("Bathroom");
		c3.setCardName("Plum");
		//Call the disprove function mutliple times to test that both knife and bathroom were selected at least once
		boolean card1 = false;
		boolean card2 = false;
		for(int i = 0; i < 100; ++i) {
			Card testC = p.disprove(c1, c2, c3);
			if(c1.equals(testC)) card1 = true;
			else if(c2.equals(testC)) card2 = true;
		}
		assertTrue(card1);
		assertTrue(card2);
	}
	
	/**
	 * Tests that if a suggestions can't be disproved, null is returned
	 */
	@Test
	public void testNoMatchingCards(){
		//Create a test hand for the player with 1 of each type of card
		Card c11 = new Card();
		c11.setCardName("Knife");
		p.addCard(c11);
		Card c22 = new Card();
		c22.setCardName("Bathroom");
		p.addCard(c22);
		Card c33 = new Card();
		c33.setCardName("Miss Vivienne Scarlet");
		p.addCard(c33);
		//Create the suggestion cards
		Card c1 = new Card();
		Card c2 = new Card();
		Card c3 = new Card();
		c1.setCardName("Globe");
		c2.setCardName("Attic");
		c3.setCardName("Plum");
		//Check that when no cards are equal, null is returned from disprove()
		assertEquals(null, p.disprove(c1, c2, c3));
	}

	//Test HAndle Suggestions - Board
	
	/**
	 * Suggestion that no one can disprove returns null
	 */
	@Test
	public void testNoOneCanDisprove(){
		//The stuff below is for the handle suggestions tests which require a simulated deal and less players
		HumanPlayer testPlayer1 = new HumanPlayer();
		ComputerPlayer testPlayer2 = new ComputerPlayer();
		ComputerPlayer testPlayer3 = new ComputerPlayer();
		Card c1 = new Card();
		c1.setCardName("Knife");
		testPlayer1.addCard(c1);
		testPlayer2.addCard(c1);
		testPlayer3.addCard(c1);
		Card c2 = new Card();
		c2.setCardName("Attic");
		testPlayer1.addCard(c2);
		testPlayer2.addCard(c2);
		testPlayer3.addCard(c2);
		Card c3 = new Card();
		c3.setCardName("Peter Plum");
		testPlayer1.addCard(c3);
		testPlayer2.addCard(c3);
		testPlayer3.addCard(c3);
		Player[] testPlayers = new Player[3];
		testPlayers[0] = testPlayer1;
		testPlayers[1] = testPlayer2;
		testPlayers[2] = testPlayer3;
		Card c4 = new Card();
		c4.setCardName("Broom");
		Card c5 = new Card();
		c5.setCardName("Conservatory");
		Card c6 = new Card();
		c6.setCardName("Donny");
		assertEquals(null, board.handleSuggestion(testPlayer2, c4, c5, c6));
	}
	
	/**
	 * Tests that a player who is accusing cannot disprove his own suggestion
	 */
	@Test
	public void testOnlyAccusingPlayerCanDisprove(){
		//The stuff below is for the handle suggestions tests which require a simulated deal and less players
		HumanPlayer testPlayer1 = new HumanPlayer();
		ComputerPlayer testPlayer2 = new ComputerPlayer();
		ComputerPlayer testPlayer3 = new ComputerPlayer();
		Card c1 = new Card();
		c1.setCardName("Knife");
		Card testC1 = new Card();
		testC1.setCardName("Chalice");		//This card will allow us to test that when player 3 suggests the chalice, disprove will return null because the accuser cannot disprove himself
		testPlayer3.addCard(testC1);
		testPlayer2.addCard(c1);
		testPlayer1.addCard(c1);
		Card c2 = new Card();
		c2.setCardName("Attic");
		testPlayer1.addCard(c2);
		testPlayer2.addCard(c2);
		testPlayer3.addCard(c2);
		Card c3 = new Card();
		c3.setCardName("Peter Plum");
		testPlayer1.addCard(c3);
		testPlayer2.addCard(c3);
		testPlayer3.addCard(c3);
		Player[] testPlayers = new Player[3];
		testPlayers[0] = testPlayer1;
		testPlayers[1] = testPlayer2;
		testPlayers[2] = testPlayer3;
		Card c4 = new Card();
		c4.setCardName("Broom");
		Card c5 = new Card();
		c5.setCardName("Conservatory");
		Card c6 = new Card();
		c6.setCardName("Donny");
		assertEquals(null, board.handleSuggestion(testPlayer3, testC1, c5, c6));
	}
	
	/**
	 * Tests that a human player correctly disproves if he is the only one who can
	 */
	@Test
	public void testOnlyHumanCanDisprove(){
		//The stuff below is for the handle suggestions tests which require a simulated deal and less players
		board = Board.getInstance();
		board.setConfigFiles("Data/OurClueBoardCSV.csv", "Data/ClueRooms.txt", "Data/CTest_CluePlayers.txt", "Data/CTest_ClueCards.txt");		
		//Initialize will load BOTH config files 
		board.initialize();
		HumanPlayer testPlayer1 = new HumanPlayer();
		ComputerPlayer testPlayer2 = new ComputerPlayer();
		ComputerPlayer testPlayer3 = new ComputerPlayer();
		Card c1 = new Card();
		c1.setCardName("Knife");
		Card testC1 = new Card();
		testC1.setCardName("Chalice");		//This card will allow us to test that when player 3 suggests the chalice, disprove will return null because the accuser cannot disprove himself
		testPlayer1.addCard(testC1);
		testPlayer2.addCard(c1);
		testPlayer3.addCard(c1);
		Card c2 = new Card();
		c2.setCardName("Attic");
		testPlayer1.addCard(c2);
		testPlayer2.addCard(c2);
		testPlayer3.addCard(c2);
		Card c3 = new Card();
		c3.setCardName("Peter Plum");
		testPlayer1.addCard(c3);
		testPlayer2.addCard(c3);
		testPlayer3.addCard(c3);
		Player[] testPlayers = new Player[3];
		testPlayers[0] = testPlayer1;
		testPlayers[1] = testPlayer2;
		testPlayers[2] = testPlayer3;
		Card c4 = new Card();
		c4.setCardName("Broom");
		Card c5 = new Card();
		c5.setCardName("Conservatory");
		Card c6 = new Card();
		c6.setCardName("Donny");

	}
	
	/**
	 * Tests that a human player who accuses cannot disprove his own suggestion
	 */
	@Test
	public void testOnlyHumanCanDisproveButAccuser(){
		//The stuff below is for the handle suggestions tests which require a simulated deal and less players
		HumanPlayer testPlayer1 = new HumanPlayer();
		ComputerPlayer testPlayer2 = new ComputerPlayer();
		ComputerPlayer testPlayer3 = new ComputerPlayer();
		Card c1 = new Card();
		c1.setCardName("Knife");
		Card testC1 = new Card();
		testC1.setCardName("Chalice");		//This card will allow us to test that when player 1 (the Human) suggests the chalice, disprove will return null because the accuser cannot disprove himself
		testPlayer1.addCard(testC1);
		testPlayer2.addCard(c1);
		testPlayer3.addCard(c1);
		Card c2 = new Card();
		c2.setCardName("Attic");
		testPlayer1.addCard(c2);
		testPlayer2.addCard(c2);
		testPlayer3.addCard(c2);
		Card c3 = new Card();
		c3.setCardName("Peter Plum");
		testPlayer1.addCard(c3);
		testPlayer2.addCard(c3);
		testPlayer3.addCard(c3);
		Player[] testPlayers = new Player[3];
		testPlayers[0] = testPlayer1;
		testPlayers[1] = testPlayer2;
		testPlayers[2] = testPlayer3;
		Card c4 = new Card();
		c4.setCardName("Broom");
		Card c5 = new Card();
		c5.setCardName("Conservatory");
		Card c6 = new Card();
		c6.setCardName("Donny");
		assertEquals(null, board.handleSuggestion(testPlayer1, testC1, c5, c6));
	}
	
	/**
	 * Tests that the player closest on the left to the suggesting player is the one who disproves
	 */
	@Test
	public void testTwoPlayersCanDisprove(){
		//The stuff below is for the handle suggestions tests which require a simulated deal and less players
		HumanPlayer testPlayer1 = new HumanPlayer();
		ComputerPlayer testPlayer2 = new ComputerPlayer();
		ComputerPlayer testPlayer3 = new ComputerPlayer();
		Card c1 = new Card();
		c1.setCardName("Knife");
		testPlayer1.addCard(c1);
		testPlayer2.addCard(c1);
		testPlayer3.addCard(c1);
		Card c2 = new Card();
		c2.setCardName("Attic");
		testPlayer1.addCard(c2);
		testPlayer2.addCard(c2);
		testPlayer3.addCard(c2);
		Card c3 = new Card();
		c3.setCardName("Peter Plum");
		testPlayer1.addCard(c3);
		testPlayer2.addCard(c3);
		testPlayer3.addCard(c3);
		Player[] testPlayers = new Player[3];
		testPlayers[0] = testPlayer1;
		testPlayers[1] = testPlayer2;
		testPlayers[2] = testPlayer3;
		Card c4 = new Card();
		c4.setCardName("Broom");
		Card c5 = new Card();
		c5.setCardName("Conservatory");
		Card c6 = new Card();
		c6.setCardName("Donny");
		//The below lines test that, for a suggestion that more than one player can disprove, the index of the player that returns a revealed card is the person closest to the suggesting player
		board.handleSuggestion(testPlayer1, c1, c5, c6);
	}
	
	/**
	 * Tests that the human player closest on the left to the suggesting player is the one who disproves
	 */
	@Test
	public void testHumanAndAnotherPlayerCanDisprove(){
		//The stuff below is for the handle suggestions tests which require a simulated deal and less players
		board = Board.getInstance();
		HumanPlayer testPlayer1 = new HumanPlayer();
		ComputerPlayer testPlayer2 = new ComputerPlayer();
		ComputerPlayer testPlayer3 = new ComputerPlayer();
		Card c1 = new Card();
		c1.setCardName("Knife");
		testPlayer1.addCard(c1);
		testPlayer2.addCard(c1);
		testPlayer3.addCard(c1);
		Card c2 = new Card();
		c2.setCardName("Attic");
		testPlayer1.addCard(c2);
		testPlayer2.addCard(c2);
		testPlayer3.addCard(c2);
		Card c3 = new Card();
		c3.setCardName("Peter Plum");
		testPlayer1.addCard(c3);
		testPlayer2.addCard(c3);
		testPlayer3.addCard(c3);
		Player[] testPlayers = new Player[3];
		testPlayers[0] = testPlayer1;
		testPlayers[1] = testPlayer2;
		testPlayers[2] = testPlayer3;
		Card c4 = new Card();
		c4.setCardName("Broom");
		Card c5 = new Card();
		c5.setCardName("Conservatory");
		Card c6 = new Card();
		c6.setCardName("Donny");
		//Tests the same thing as the test before, but a computer and a human can disprove the suggesting player, and the computer comes before the human so its index should be returned
		board.handleSuggestion(testPlayer2, c1, c5, c6);

	}
}
