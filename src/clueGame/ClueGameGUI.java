package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

public class ClueGameGUI extends JFrame{
	private static DetectiveNotesGUI dialog;
	private static boolean startOfGame = true;
	private static LowerPanelGUI lowerPanelGUI= new LowerPanelGUI();
	static JFrame frame = new JFrame();
	/*public static void updateBottomPanel(){
		// adds lower panel
		LowerPanelGUI lowerPanelGUI= new LowerPanelGUI();
		frame.add(lowerPanelGUI, BorderLayout.SOUTH);
		frame.setVisible(true);
	}*/
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Clue");
		frame.setSize(800, 800);

		frame.add(lowerPanelGUI, BorderLayout.SOUTH);
		// adds board part
		BoardGUI boardPart = new BoardGUI();
		frame.add(boardPart, BorderLayout.CENTER);
		//adds right panel
		RightPanelGUI rightPanel = new RightPanelGUI();
		TitledBorder cardBorder = new TitledBorder("My Cards");
		rightPanel.setBorder(cardBorder);
		frame.add(rightPanel, BorderLayout.EAST);
		//creates the menu bar
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu menu = new JMenu("File");
		
		//The following block deals with adding items to the file option in the menu bar, and also adds functionality when the items are clicked
		JMenuItem fileItem = new JMenuItem("Show Notes");
		JMenuItem fileItem2 = new JMenuItem("Exit");
		class MenuExitListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		class MenuNotesListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				dialog = new DetectiveNotesGUI();
				dialog.setVisible(true);
			}
		}
		fileItem.addActionListener(new MenuNotesListener());
		fileItem2.addActionListener(new MenuExitListener());
		
		menu.add(fileItem);
		menu.add(fileItem2);
		menuBar.add(menu);

		frame.setVisible(true);
		
		if(startOfGame){
			startOfGame = false;
			JOptionPane.showMessageDialog(frame, "You are Miss Scarlet. Press Next Player to begin.", "Welcome to Clue", JOptionPane.PLAIN_MESSAGE);
		}
		////////////////////
	
		
	}
	
	public void update(){
		lowerPanelGUI.setLabel();
	}

	public static boolean isStartOfGame() {
		return startOfGame;
	}

	public static void setStartOfGame(boolean startOfGame) {
		ClueGameGUI.startOfGame = startOfGame;
	}
	
	
	
}

