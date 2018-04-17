package clueGame;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class RightPanelGUI extends JPanel{
	private String myCardPeople = "test player";
	private String myCardRoom = "test room";
	private String myCardWeapon = "test weapon";

	public RightPanelGUI(){
		add(createRightPanel());
	}

	public JPanel createRightPanel(){
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		//Create player card
		JLabel playerCard = new JLabel(myCardPeople);
		TitledBorder titledBorder = new TitledBorder("People");
		playerCard.setBorder(titledBorder);
		rightPanel.add(playerCard);
		//Create room card
		JLabel roomCard = new JLabel(myCardRoom);
		TitledBorder titledBorder2 = new TitledBorder("Rooms");
		roomCard.setBorder(titledBorder2);
		rightPanel.add(roomCard);
		//Create weapon card
		JLabel weaponCard = new JLabel(myCardWeapon);
		TitledBorder titledBorder3 = new TitledBorder("Weapons");
		weaponCard.setBorder(titledBorder3);
		rightPanel.add(weaponCard);

		return rightPanel;

	}
}
