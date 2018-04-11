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


public class clueGUI extends JPanel{
	private String currentPlayersTurn;
	
	public clueGUI(){
		currentPlayersTurn = "Miss Vivienne Scarlet";
		setLayout(new GridLayout(2,0));
		JPanel lowerPanel = createLowerPanel();
		add(lowerPanel);
	}
	
	public JPanel createLowerPanel(){
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new GridLayout(2,2));
		lowerPanel.add(createTopSubPanel(), BorderLayout.CENTER);
		lowerPanel.add(createBottomSubPanel(), BorderLayout.CENTER);
		return lowerPanel;
	}
	
	public JPanel createTopSubPanel(){
		//Creates the upper half of the lower panel
		JPanel topSubPanel = new JPanel();
		topSubPanel.setLayout(new GridLayout(1,3));
		JPanel topWhoseTurnSubPanel = new JPanel();
		
		//Create the whose turn panel within the lower panel
		topWhoseTurnSubPanel.setLayout(new GridLayout(1,1));
		JLabel whoseTurn = new JLabel("Whose Turn? " + currentPlayersTurn);
		topWhoseTurnSubPanel.add(whoseTurn, BorderLayout.CENTER);
		topSubPanel.add(topWhoseTurnSubPanel, BorderLayout.WEST);
		
		//Create the two buttons which will go in the upper half of the lower panel
		JButton nextPlayerButton = new JButton("Next Player");
		JButton accusationButton = new JButton("Make an Accusation");
		topSubPanel.add(nextPlayerButton, BorderLayout.EAST);
		topSubPanel.add(accusationButton, BorderLayout.EAST);
		
		//Create the lower half of the lower panel
		JPanel lowerSubPanel = new JPanel();
		lowerSubPanel.setLayout(new GridLayout(1,3));
		JPanel lowerWhoseTurnSubPanel = new JPanel();
		
		return topSubPanel;
	}
	
	public JPanel createBottomSubPanel(){
		//Creates the upper half of the lower panel
		JPanel bottomSubPanel = new JPanel();
		bottomSubPanel.setLayout(new GridLayout(1,3));
		
		// makes die box
		JLabel die = new JLabel("Roll: ");
		bottomSubPanel.add(die, BorderLayout.WEST);
		TitledBorder dieTitledBorder = new TitledBorder("Die");
		die.setBorder(dieTitledBorder);
		
		// makes guess box
		JLabel guess = new JLabel("Guess: ");
		bottomSubPanel.add(guess, BorderLayout.CENTER);
		TitledBorder guessTitledBorder = new TitledBorder("Guess");
		guess.setBorder(guessTitledBorder);
		
		// makes Guess Result box
		JLabel response = new JLabel("Response: ");
		TitledBorder resultTitledBorder = new TitledBorder("Guess Result");
		response.setBorder(resultTitledBorder);
		bottomSubPanel.add(response, BorderLayout.CENTER);
		
		
		return bottomSubPanel;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Clue");
		frame.setSize(800, 800);
		clueGUI gui = new clueGUI();
		frame.add(gui, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
}
