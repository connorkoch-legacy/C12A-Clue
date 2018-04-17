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

public class ClueGameGUI extends JFrame{
	private static DetectiveNotesGUI dialog;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Clue");
		frame.setSize(800, 800);
		// adds lower panel
		LowerPanelGUI lowerPanelGUI= new LowerPanelGUI();
		frame.add(lowerPanelGUI, BorderLayout.SOUTH);
		// adds board part
		BoardGUI boardPart = new BoardGUI();
		frame.add(boardPart, BorderLayout.CENTER);
		//adds right panel
		RightPanelGUI rightPanel = new RightPanelGUI();
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
	}
}

