package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuessBoxGUI extends JDialog{
	private JButton submitButton;
	private JButton cancelButton;
	JComboBox<String> personGuess = new JComboBox<String>();
	JComboBox<String> weaponGuess = new JComboBox<String>();
	private boolean submitted;
	String roomString = new String();

	public GuessBoxGUI() {
		submitted = false;
		setTitle("Make a Guess");
		setSize(300, 200);
		add(leftColumn(), BorderLayout.WEST);
		add(rightColumn(), BorderLayout.EAST);
		
	}

	//Creates left side of guess dialog box
	public JPanel leftColumn(){
		JPanel left = new JPanel();
		left.setLayout(new GridLayout(4,1));

		JLabel yourRoom = new JLabel("Your room");
		left.add(yourRoom);
		JLabel person = new JLabel("Person");
		left.add(person);
		JLabel weapon = new JLabel("Weapon");
		left.add(weapon);
		
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new ButtonListener());
		left.add(submitButton);

		return left;
	}

	//Creates right side of guess dialog box
	public JPanel rightColumn(){
		JPanel right = new JPanel();
		right.setLayout(new GridLayout(4,1));

		//Selects the room the player is in based on the initial of the boardcell at their current location
		char roomChar = Board.getBoard()[Board.getInstance().getPlayers()[0].getRow()][Board.getInstance().getPlayers()[0].getColumn()].getInitial();
		switch(roomChar){
			case 'C':	roomString = "Conservatory";
						break;
			case 'K':	roomString = "Kitchen";
						break;
			case 'B':	roomString = "Ballroom";
						break;
			case 'R':	roomString = "Billiard room";
						break;
			case 'T':	roomString = "Bathroom";
						break;
			case 'L':	roomString = "Cellar";
						break;
			case 'D':	roomString = "Laundry room";
						break;
			case 'A':	roomString = "Attic";
						break;
			case 'V':	roomString = "Vault";
						break;
			default:	roomString = "null";
		}
		JLabel yourRoom = new JLabel(roomString);
		right.add(yourRoom);
		//set the options for person box
		personGuess.addItem("Miss Vivienne Scarlet");
		personGuess.addItem("Colonel Michael Mustard");
		personGuess.addItem("Mrs. Blanche White");
		personGuess.addItem("Reverend Jonathan Green");
		personGuess.addItem("Mrs. Elizabeth Peacock");
		personGuess.addItem("Professor Peter Plum");
		right.add(personGuess);
		//set the options for weapons box
		weaponGuess.addItem("Candlestick");
		weaponGuess.addItem("Knife");
		weaponGuess.addItem("Lead Pipe");
		weaponGuess.addItem("Revolver");
		weaponGuess.addItem("Rope");
		weaponGuess.addItem("Wrench");
		right.add(weaponGuess);
		//add the cancel button
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ButtonListener());
		right.add(cancelButton);

		return right;
	}

	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == submitButton){	//checks to see if other players can disprove the suggestion
				//matches the string from the drop down box to the appropriate card
				Card personSuggest = new Card();
				Card weaponSuggest = new Card();
				Card roomSuggest = new Card();
				for(Card c : Board.getInstance().getCards()){
					System.out.println(c.getCardName());
					if(c.getCardName() == personGuess.getSelectedItem()) personSuggest = c;
					else if(c.getCardName() == weaponGuess.getSelectedItem()) weaponSuggest = c;
					else if(c.getCardName() == roomString) roomSuggest = c;
				}
				System.out.println(personSuggest.getCardName() + " " + weaponSuggest.getCardName() + " " + roomSuggest.getCardName());
				Card disprovedCard = new Card();
				disprovedCard = Board.getInstance().handleSuggestion(Board.getInstance().getPlayers()[0], personSuggest, weaponSuggest, roomSuggest); 
				if(disprovedCard != null) System.out.println(disprovedCard.getCardName());
				
			}else if(e.getSource() == cancelButton){
				
			}
		}
	}

	public boolean isSubmitted() {
		return submitted;
	}
	
	




}

