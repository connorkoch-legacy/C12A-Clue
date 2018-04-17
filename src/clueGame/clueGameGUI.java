package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class clueGameGUI extends JFrame{
	clueGameGUI(){
		
		//lowerPanelGUI.setPreferredSize(new Dimension(100, 100));
		
	}
	
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
		frame.setVisible(true);
	}
}
