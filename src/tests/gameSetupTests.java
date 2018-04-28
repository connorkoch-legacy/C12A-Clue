package tests;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ClueGameGUI;
import clueGame.Player;
import clueGame.HumanPlayer;
import clueGame.ComputerPlayer;
import java.awt.Color;
import java.util.ArrayList;

public class gameSetupTests{
	private ClueGameGUI start = new ClueGameGUI();
	private static Board board;
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		//board = Board.getInstance();
		// set the file names to use my config files
		//board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt", "Data/CTest_CluePlayers.txt", "Data/CTest_ClueCards.txt");		
		// Initialize will load BOTH config files 
		//board.initialize();
		ClueGameGUI start = new ClueGameGUI();
	}
	
	//Test that players are loaded correctly
	@Test
	public void testPlayerLoading(){
//		board = Board.getInstance();
//		board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt", "Data/CTest_CluePlayers.txt", "Data/CTest_ClueCards.txt");
//		board.initialize();
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
	
	// tests cards are loaded correctly
	@Test
	public void testCardLoading() {
		Card[] cards = board.getCards();
		assertEquals(23, cards.length);///////////////
		int personCount = 0;
		int weaponCount = 0;
		int roomCount = 0;
		for(int i = 0; i < 23; i++){
			if(cards[i].getCardType() == CardType.PERSON){
				personCount++;
			}else if(cards[i].getCardType() == CardType.WEAPON){
				weaponCount++;
			} else if (cards[i].getCardType() == CardType.ROOM){
				roomCount++;
			}
		}
		assertEquals(6, personCount);
		assertEquals(6, weaponCount);
		assertEquals(11, roomCount);
		// check existance of one of each type of card
		assertTrue(cards[1].getCardName().equals("Kitchen"));
		assertTrue(cards[13].getCardType() == CardType.PERSON);
		assertTrue(cards[21].getCardType() == CardType.WEAPON);
		assertTrue(cards[21].getCardName().equals("Rope"));
		
	}
	
	// tests envelope has correct cards in it
	@Test
	public void testEnvelope(){
		//board = Board.getInstance();
		//board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt", "Data/CTest_CluePlayers.txt", "Data/CTest_ClueCards.txt");
		//board.initialize();
		ArrayList<Card> testEnvelope = Board.getInstance().getEnvelope();
		assertEquals(3, Board.getInstance().getEnvelope().size());
		assertTrue(testEnvelope.get(0).getCardType() == CardType.ROOM);
		assertTrue(testEnvelope.get(1).getCardType() == CardType.PERSON);
		assertTrue(testEnvelope.get(2).getCardType() == CardType.WEAPON);
	}
	
	// tests the cards were dealt right
	@Test
	public void testDealingCards() {
		Player[] players = board.getPlayers();
		Card[] cards = board.getCards();
		// makes sure there are no cards dealt twice
		boolean noDuplicate = true;
		for(int i = 0; i < players.length; i++){
			for(Card c: players[i].ownedCards){
				for(int j = 0; j < players.length; j++){
					if(players[j].ownedCards.contains(c) && (i != j)){
						noDuplicate = false;
					}
				}
			}
		}
		
		assertTrue(noDuplicate);
		// makes sure all cards are dealt
		int numCardsDealt = 0;
		for(int i = 0; i < players.length; i++){
			numCardsDealt += players[i].ownedCards.size();
		}
		assertTrue(numCardsDealt == cards.length-3);
		// make sure all players have about same number of cards
		boolean itsGood = true;
		for(int i = 0; i < players.length; i++){
			for(int j = 0; j < players.length; j++){
				if(players[i].ownedCards.size() - players[j].ownedCards.size() > 1 || players[i].ownedCards.size() - players[j].ownedCards.size() < -1){
					itsGood = false;
				}
			}
		}
		assertTrue(itsGood);
		
	}
	
}
