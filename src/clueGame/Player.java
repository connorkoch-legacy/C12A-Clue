package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JPanel;

public class Player extends JPanel{
	private String playerName;
	private int row;
	private int column;
	private Color color;
	public Set<Card> ownedCards = new HashSet<Card>(); //Stores cards in hand
	private Set<Card> seenCards = new HashSet<Card>(); //Stores cards in hand
	private final int BOARD_HEIGHT = 600;
	private final int BOARD_WIDTH = 600;
	private final int pieceDimensions = BOARD_HEIGHT/25;
	private int roll;
	private Solution accusation;
	private Solution suggestion;
	//private Card lastSeenCard;

	
	/**
	 * Draws the player at their location
	 * @param g
	 */
	public void draw(Graphics g) {
		int rowPixel = pieceDimensions * row;
		int colPixel = pieceDimensions * column;
		
		g.setColor(color);
		g.fillOval(colPixel, rowPixel, pieceDimensions, pieceDimensions);
		
		g.setColor(Color.black);
		g.drawOval(colPixel, rowPixel, pieceDimensions, pieceDimensions);

	}

	public void makeMove(){
		
	}
	
	
	public void rollDie(){
		Random r = new Random();
		roll = r.nextInt(6) + 1;
		
	}
	
	public void addCard(Card c){
		ownedCards.add(c);
		addSeenCards(c);
		
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(String col) { // converts a string to the color and sets variable color to it
		
		Field field = null;
		try {
			field = Class.forName("java.awt.Color").getField(col);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			color = (Color)field.get(null);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.color = color;
	}
	
	public Color convertColor(String strColor) {
		Color color;
		try {
			// We can use reflection to convert the string to a color
			Field field = Class.forName("java.awt.Color").getField(strColor.trim());
			color = (Color)field.get(null);
		} catch (Exception e) {
			color = null; // Not defined
		}
		return color;
	}
	public Set<Card> getSeenCards() {
		return seenCards;
	}
	public void addSeenCards(Card seen) {
		seenCards.add(seen);
		//setLastSeenCard(seen);
	}

	public Set<Card> getOwnedCards() {
		return ownedCards;
	}

	public Card disprove(Card card1, Card card2, Card card3) {
		return null;

	}
	public int getRoll() {
		return roll;
	}

	public Solution getAccusation() {
		return accusation;
	}

	public void setAccusation(Solution accusation) {
		this.accusation = accusation;
	}

	

	public Solution getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(Solution suggestion) {
		this.suggestion = suggestion;
	}
	

}
