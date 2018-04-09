package clueGame;

import java.util.ArrayList;
import java.util.Random;

public class HumanPlayer extends Player{
	
	@Override
	public Card disprove(Card card1, Card card2, Card card3) {
		ArrayList<Card> matchingCards = new ArrayList<Card>();
		for(Card c: ownedCards) {
			if(c.equals(card1)) matchingCards.add(c);
			else if(c.equals(card2)) matchingCards.add(c);
			else if(c.equals(card3)) matchingCards.add(c);
		}
		//System.out.println(matchingCards.get(0).getCardName());
		Random r = new Random();
		if(matchingCards.size() == 1) {
			return matchingCards.get(0);
		} else if(matchingCards.size() == 2) {
			return matchingCards.get(r.nextInt(2));
		} else if(matchingCards.size() == 3) {
			return matchingCards.get(r.nextInt(3));
		} else return null;
	}
	
}
