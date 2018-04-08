package clueGame;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player {
	private String playerName;
	private int row;
	private int column;
	private Color color;
	public Set<Card> ownedCards = new HashSet<Card>(); //Stores cards in hand
	private Set<Card> seenCards = new HashSet<Card>(); //Stores cards in hand
	
	public void addCard(Card c){
		ownedCards.add(c);
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
	public void setColor(String color) {
		this.color = convertColor(color);
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
	}


	
}
