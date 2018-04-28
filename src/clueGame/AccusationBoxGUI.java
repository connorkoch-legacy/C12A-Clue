package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * This class is used for making an accusation
 *
 */


public class AccusationBoxGUI extends JDialog{
	private JButton submitButton;
	private JButton cancelButton;
	JComboBox<String> roomGuess = new JComboBox<String>();
	JComboBox<String> personGuess = new JComboBox<String>();
	JComboBox<String> weaponGuess = new JComboBox<String>();
	String roomString = new String();

	public AccusationBoxGUI() {
		setTitle("Make an Accusation");
		setSize(300, 200);
		add(leftColumn(), BorderLayout.WEST);
		add(rightColumn(), BorderLayout.EAST);
		
	}

	//Creates left side of guess dialog box
	public JPanel leftColumn(){
		JPanel left = new JPanel();
		left.setLayout(new GridLayout(4,1));

		JLabel yourRoom = new JLabel("Room");
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
		roomGuess.addItem("Conservatory");
		roomGuess.addItem("Kitchen");
		roomGuess.addItem("Ballroom");
		roomGuess.addItem("Billiard room");
		roomGuess.addItem("Bathroom");
		roomGuess.addItem("Cellar");
		roomGuess.addItem("Laundry room");
		roomGuess.addItem("Attic");
		roomGuess.addItem("Vault");
		right.add(roomGuess);
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
					if(c.getCardName().equals(personGuess.getSelectedItem())) personSuggest = c; 
					else if(c.getCardName().equals(weaponGuess.getSelectedItem())) weaponSuggest = c;
					else if(c.getCardName().equals(roomGuess.getSelectedItem())) roomSuggest = c;
				}
				//Make a solution with the inputs from the user
				Solution theAnswer = new Solution();
				theAnswer = Board.getInstance().getTheAnswer();
				boolean correctGuess = false;
				Solution sol = new Solution(personSuggest, roomSuggest, weaponSuggest);
				correctGuess = Board.getInstance().checkAccusation(sol);
				endGame(correctGuess);	//call the endgame function to see if the accusation is correct
				setVisible(false);
			}else if(e.getSource() == cancelButton){
				setVisible(false);
			}
		}
	}
	
	//Ends the game if the accusation is correct, otherwise continues on
	public void endGame(boolean correctGuess) {
		if(correctGuess) {
			JOptionPane.showMessageDialog(this, "Correct. Congratulations, you win!", "Accusation", JOptionPane.PLAIN_MESSAGE);
			Board.getInstance().getStart().end();
		}
		else {
			JOptionPane.showMessageDialog(this, "This accusation is not correct.", "Accusation", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
}
