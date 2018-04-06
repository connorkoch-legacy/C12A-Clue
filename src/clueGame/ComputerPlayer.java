package clueGame;

import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{
	private BoardCell prevRoom; // tracks the last room player was in
	
	
	
	
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
	
	public void makeAccusation(){
		
	}
	
	public void createSuggestion(){
		
	}

	public BoardCell getPrevRoom() {
		return prevRoom;
	}

	public void setPrevRoom(BoardCell prevRoom) {
		this.prevRoom = prevRoom;
	}

	
}
