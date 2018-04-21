package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class LowerPanelGUI extends JPanel{
	private String currentPlayersTurn;
	
	public LowerPanelGUI(){
		currentPlayersTurn = "Miss Vivienne Scarlet";
		add(createLowerPanel());
	}
	
	public JPanel createLowerPanel(){
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new GridLayout(2,0));
		lowerPanel.add(createTopSubPanel());
		lowerPanel.add(createBottomSubPanel());
		return lowerPanel;
	}
	
	public JPanel createTopSubPanel(){
		//Creates the upper half of the lower panel
		JPanel topSubPanel = new JPanel();
		topSubPanel.setLayout(new GridLayout(1,3));
		JPanel topWhoseTurnSubPanel = new JPanel();
		
		//Create the whose turn panel within the lower panel
		topWhoseTurnSubPanel.setLayout(new GridLayout(1,1));
		JLabel whoseTurn = new JLabel("Whose Turn? " + Board.getPlayers()[0].getName());
		topWhoseTurnSubPanel.add(whoseTurn);
		topSubPanel.add(topWhoseTurnSubPanel);
		
		//Create the two buttons which will go in the upper half of the lower panel
		JButton nextPlayerButton = new JButton("Next Player");
		JButton accusationButton = new JButton("Make an Accusation");
		topSubPanel.add(nextPlayerButton);
		topSubPanel.add(accusationButton);
		
		//Create the lower half of the lower panel
		JPanel lowerSubPanel = new JPanel();
		lowerSubPanel.setLayout(new GridLayout(1,3));
		
		return topSubPanel;
	}
	
	public JPanel createBottomSubPanel(){
		//Creates the upper half of the lower panel
		JPanel bottomSubPanel = new JPanel();
		bottomSubPanel.setLayout(new GridLayout(1,3));
		
		// makes die box
		JLabel die = new JLabel("Roll: " + Board.getPlayers()[0].getRoll());
		bottomSubPanel.add(die);
		TitledBorder dieTitledBorder = new TitledBorder("Die");
		die.setBorder(dieTitledBorder);
		
		// makes guess box
		JLabel guess = new JLabel("Guess: ");
		bottomSubPanel.add(guess);
		TitledBorder guessTitledBorder = new TitledBorder("Guess");
		guess.setBorder(guessTitledBorder);
		
		// makes Guess Result box
		JLabel response = new JLabel("Response: ");
		TitledBorder resultTitledBorder = new TitledBorder("Guess Result");
		response.setBorder(resultTitledBorder);
		bottomSubPanel.add(response);
		
		
		return bottomSubPanel;
	}

}
