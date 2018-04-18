package clueGame;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class RightPanelGUI extends JPanel{
	private ArrayList<String> personCards = new ArrayList<String>();
	private ArrayList<String> weaponCards = new ArrayList<String>();
	private ArrayList<String> roomCards = new ArrayList<String>();

	public RightPanelGUI(){
		add(createRightPanel());
	}
	/**
	 * Creates the right panel, that displays cards in the users hand
	 * @author Daniel Winternitz and Connor Koch
	 */
	public JPanel createRightPanel(){
		Board testBoard = new Board();
		Player humanPlayer = Board.getPlayers()[0];
		Set<Card> ownedCards = new HashSet<Card>();
		ownedCards = humanPlayer.getOwnedCards();
		for(Card c: ownedCards){
			if(c.getCardType() == CardType.PERSON){
				personCards.add(c.getCardName());
			}else if(c.getCardType() == CardType.WEAPON){
				weaponCards.add(c.getCardName());
			}else if(c.getCardType() == CardType.ROOM){
				roomCards.add(c.getCardName());
			}
		}
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		//Create player card
		JPanel personPanel = new JPanel();
		personPanel.setLayout(new BoxLayout(personPanel, BoxLayout.Y_AXIS));
		for(String s: personCards){
			JLabel playerCard = new JLabel(s);
			
			personPanel.add(playerCard);
		}
		JPanel borderPanel1 = new JPanel();
		TitledBorder titledBorderRoom1 = new TitledBorder("People");
		borderPanel1.add(personPanel);
		borderPanel1.setBorder(titledBorderRoom1);
		rightPanel.add(borderPanel1);
		//Create room card
		JPanel roomPanel = new JPanel();
		roomPanel.setLayout(new BoxLayout(roomPanel, BoxLayout.Y_AXIS));
		for(String s: roomCards){
			JLabel roomCard = new JLabel(s);
			
			roomPanel.add(roomCard);
		}
		JPanel borderPanel2 = new JPanel();
		TitledBorder titledBorderRoom = new TitledBorder("Rooms");
		borderPanel2.add(roomPanel);
		borderPanel2.setBorder(titledBorderRoom);
		rightPanel.add(borderPanel2);
		//Create weapon card
		JPanel weaponPanel = new JPanel();
		weaponPanel.setLayout(new BoxLayout(weaponPanel, BoxLayout.Y_AXIS));
		for(String s: weaponCards){
			JLabel weaponCard = new JLabel(s);
			
			weaponPanel.add(weaponCard);
		}
		JPanel borderPanel3 = new JPanel();
		TitledBorder titledBorderRoom3 = new TitledBorder("Weapons");
		borderPanel3.add(roomPanel);
		borderPanel3.setBorder(titledBorderRoom3);
		rightPanel.add(borderPanel3);
		return rightPanel;

	}
}
