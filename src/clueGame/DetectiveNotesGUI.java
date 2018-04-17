package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class DetectiveNotesGUI extends JDialog{
	
	//Constructor that deals with making the dialog box and adding the left and right jpanels
	public DetectiveNotesGUI() {
		setTitle("Detective Notes");
		setSize(600, 700);
		add(leftColumn(), BorderLayout.WEST);
		add(rightColumn(), BorderLayout.EAST);
	}
	
	//the left jpanel contains the checkboxes
	public JPanel leftColumn() {
		JPanel left = new JPanel();
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		//creates the people panel
		JPanel people = new JPanel();
		people.setLayout(new GridLayout(3,2));
		TitledBorder titledBorder1 = new TitledBorder("People");
		people.setBorder(titledBorder1);
		JCheckBox checkBox1 = new JCheckBox("Miss Vivienne Scarlet");
		people.add(checkBox1);
		JCheckBox checkBox2 = new JCheckBox("Colonel Michael Mustard");
		people.add(checkBox2);
		JCheckBox checkBox3 = new JCheckBox("Mrs. Blanche White");
		people.add(checkBox3);
		JCheckBox checkBox4 = new JCheckBox("Reverend Jonathan Green");
		people.add(checkBox4);
		JCheckBox checkBox5 = new JCheckBox("Mrs. Elizabeth Peacock");
		people.add(checkBox5);
		JCheckBox checkBox6 = new JCheckBox("Professor Peter Plum");
		people.add(checkBox6);
		left.add(people);
		//creates the rooms panel
		JPanel rooms = new JPanel();
		rooms.setLayout(new GridLayout(5,2));
		TitledBorder titledBorder2 = new TitledBorder("Rooms");
		rooms.setBorder(titledBorder2);
		JCheckBox checkBox7 = new JCheckBox("Conservatory");
		rooms.add(checkBox7);
		JCheckBox checkBox8 = new JCheckBox("Kitchen");
		rooms.add(checkBox8);
		JCheckBox checkBox9 = new JCheckBox("Ballroom");
		rooms.add(checkBox9);
		JCheckBox checkBox10 = new JCheckBox("Billiard room");
		rooms.add(checkBox10);
		JCheckBox checkBox11 = new JCheckBox("Bathroom");
		rooms.add(checkBox11);
		JCheckBox checkBox12 = new JCheckBox("Cellar");
		rooms.add(checkBox12);
		JCheckBox checkBox13 = new JCheckBox("Laundry room");
		rooms.add(checkBox13);
		JCheckBox checkBox14 = new JCheckBox("Attic");
		rooms.add(checkBox14);
		JCheckBox checkBox15 = new JCheckBox("Vault");
		rooms.add(checkBox15);
		left.add(rooms);
		//Creates weapons panel
		JPanel weapons = new JPanel();
		weapons.setLayout(new GridLayout(3,2));
		TitledBorder titledBorder3 = new TitledBorder("Weapons");
		weapons.setBorder(titledBorder3);
		JCheckBox checkBox16 = new JCheckBox("Candlestick");
		weapons.add(checkBox16);
		JCheckBox checkBox17 = new JCheckBox("Knife");
		weapons.add(checkBox17);
		JCheckBox checkBox18 = new JCheckBox("Lead Pipe");
		weapons.add(checkBox18);
		JCheckBox checkBox19 = new JCheckBox("Revolver");
		weapons.add(checkBox19);
		JCheckBox checkBox20 = new JCheckBox("Rope");
		weapons.add(checkBox20);
		JCheckBox checkBox21 = new JCheckBox("Wrench");
		weapons.add(checkBox21);
		left.add(weapons);
		return left;
	}

	//the right jpanel contains the drop down menus
	public JPanel rightColumn() {
		JComboBox<String> personGuess = new JComboBox<String>(), roomGuess = new JComboBox<String>(), weaponGuess = new JComboBox<String>();
		JPanel right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		//set the options for person box
		personGuess.addItem("Miss Vivienne Scarlet");
		personGuess.addItem("Colonel Michael Mustard");
		personGuess.addItem("Mrs. Blanche White");
		personGuess.addItem("Reverend Jonathan Green");
		personGuess.addItem("Mrs. Elizabeth Peacock");
		personGuess.addItem("Professor Peter Plum");
		right.add(personGuess);
		//set the options for rooms box
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
		//set the options for weapons box
		weaponGuess.addItem("Candlestick");
		weaponGuess.addItem("Knife");
		weaponGuess.addItem("Lead Pipe");
		weaponGuess.addItem("Revolver");
		weaponGuess.addItem("Rope");
		weaponGuess.addItem("Wrench");
		right.add(weaponGuess);
		return right;
	}

}
