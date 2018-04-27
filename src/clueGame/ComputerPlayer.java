package clueGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{
	private BoardCell prevRoom; // tracks the last room player was in
	private boolean inRoom = false;
	
	@Override
	public void makeMove(){
		// clears out Guess and Result fields, doesnt really effect players move
		Solution sol = new Solution();
		Card card = new Card();
		card.setCardName(" ");
		sol.person = card;
		sol.weapon = card;
		sol.room = card;
		setSuggestion(sol);
		Board.getInstance().setRevCard("No knew Clue");
		/////////////////
		
		
		for(BoardCell[] b: Board.getInstance().board){ // makes sure no cells are marked as targets
			for(BoardCell c: b){
				c.setTarget(false);
			}
		}
		
		boolean continueTurn = true; // used to skip rest of trun if bad accusation is made
		// if only three cards unseen, makes accusation
		if((Board.getInstance().getCards().length - getSeenCards().size()) == 3){
			continueTurn = makeAccusation();
		}
		
		
		if(continueTurn){ // if false means failed accusation was made, and ends turn
			BoardCell newSpot = new BoardCell(); // moves the player to a cell in its target list
			rollDie();
			Board.getInstance().calcTargets(getRow(), getColumn(), getRoll());
			newSpot = pickLocation(Board.getInstance().getTargets());
			setRow(newSpot.getRow());
			setColumn(newSpot.getCol());

			if(Board.getInstance().getCellAt(getRow(), getColumn()).isDoorway()){
				inRoom = true; // checks if in room and sets inRoom bool accordingly
			} else {
				inRoom = false;
			}

			if(inRoom){ // if in room makes a suggestion adds revealed card to seen cards
				Solution suggestion = new Solution();
				suggestion = createSuggestion();
				setSuggestion(suggestion); // sets variable in parent to local suggestion
				Card revCard = new Card();
				revCard = Board.getInstance().handleSuggestion(Board.getInstance().getPlayers()[Board.getInstance().getCurrentPlayerIterator()],
						suggestion.person, suggestion.room, suggestion.weapon);
				addSeenCards(revCard); // adds the revealed card to the seen cards set


			}
		}
	}
	
	/**
	 * Returns the suggestion based on what the computerPlayer has seen and the players location
	 * @return Solution
	 */
	
	public Solution createSuggestion(){
		Solution suggestion = new Solution();
		
		
		Random r = new Random();
		
		// puts room based on current location
		String roomName = Board.legend.get(Board.getCellAt(getRow(), getColumn()).getInitial());
		//Loops through cards and searched for the card that the current player is in and suggests it
		for(Card c : Board.getInstance().getCards()){
			if(roomName.equals(c.getCardName())) suggestion.room = c;
		}
		// chooses weapon
		ArrayList<Card> unseenWeapons = new ArrayList<Card>();
		for(Card c: Board.getCards()){ // for everycard, adds to array if weapon and not seen
			if(c.getCardType() == CardType.WEAPON && !getSeenCards().contains(c)){
				unseenWeapons.add(c);
			}
		}
		// randomly selects weapon from array of unseen weapons
		int randomIndex = r.nextInt(unseenWeapons.size());
		suggestion.weapon = unseenWeapons.get(randomIndex);
		
		
		// chooses person
		ArrayList<Card> unseenPerson = new ArrayList<Card>();
		for(Card c: Board.getCards()){ // for everycard, adds to array if weapon and not seen
			if(c.getCardType() == CardType.PERSON && !getSeenCards().contains(c)){
				unseenPerson.add(c);
			}
		}
		randomIndex = r.nextInt(unseenPerson.size());
		suggestion.person = unseenPerson.get(randomIndex);
		
		return suggestion;
	}
	
	// chooses the target location based on a given targets set
	public BoardCell pickLocation(Set<BoardCell> targets){
		BoardCell target = null; 
		// selects target randomly and stores in target variable
		Random r = new Random();
		int targetIndex = r.nextInt(targets.size());
		int indexCheck = 0;
		for(BoardCell bc: targets){
			if(indexCheck == targetIndex){
				target = bc;
				break;
			}
			indexCheck++;
		}
		// overrides previous target selection if set contains a door that hasnt been previously visited
		for(BoardCell b: targets){
			if(b.isDoorway() && b != prevRoom){
				target = b;
			}
		}
		
		return target;
	}
	
	public boolean makeAccusation(){
		Solution accusation = new Solution();
		for(Card c: Board.getInstance().getCards()){
			if(!getSeenCards().contains(c)){
				if(c.getCardType() == CardType.PERSON){
					accusation.person = c;
				} else if(c.getCardType() == CardType.WEAPON){
					accusation.weapon = c;
				} else if(c.getCardType() == CardType.ROOM){
					accusation.room = c;
				}	
			}
		}
		setAccusation(accusation); // sets accusation variable in Plater.java to local variable
		
		
		// checks accusation and sets boolean result accordingly
		boolean result = Board.getInstance().checkAccusation(accusation);
//		if(result){
//			System.out.println(getPlayerName() + " won the game");
//		}else{
//			System.out.println(getPlayerName() + " was wrong");
//		}
		String accResult = getPlayerName() + " " + "was CORRECT and WON the game!!";;
		if(!result){
			accResult = getPlayerName() + " was WRONG.";
		}
		// alerts human of accusation and status
		Board.getInstance().getStart().displayAccusation(getPlayerName() + " accused " + getAccusation().person.getCardName() 
				+ " with " + getAccusation().weapon.getCardName() + " in the " + getAccusation().room.getCardName()
				+ "\n" + accResult);
		return result;
	}

	public BoardCell getPrevRoom() {
		return prevRoom;
	}

	public void setPrevRoom(BoardCell prevRoom) {
		this.prevRoom = prevRoom;
	}

	//Checks the suggestion to see if the player has any matching cards
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
