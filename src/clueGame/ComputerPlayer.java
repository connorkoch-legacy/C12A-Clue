package clueGame;

import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{
	private Card prevRoom; // tracks the last room player was in
	
	
	
	
	// choses the target location based on a given targets set
	public BoardCell pickLocation(Set<BoardCell> targets){
		BoardCell target = null; 
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
		return target;
	}
	
	public void makeAccusation(){
		
	}
	
	public void createSuggestion(){
		
	}

	public Card getPrevRoom() {
		return prevRoom;
	}

	public void setPrevRoom(Card prevRoom) {
		this.prevRoom = prevRoom;
	}

	
}
