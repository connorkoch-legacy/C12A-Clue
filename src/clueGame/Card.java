package clueGame;

public class Card {
	private String cardName;
	private CardType cardType;
	
	public boolean equals(Card card){
		//If the passed in card has the same name, cards are equal
		if(cardName == card.getCardName()) return true;
		return false;
	}
	
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	
}
