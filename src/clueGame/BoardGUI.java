package clueGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class BoardGUI extends JPanel{
	public BoardGUI(){
		setBackground(Color.gray);
		
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(BoardCell[] b: Board.board){
			for(BoardCell c: b){
				c.draw(g);
			}
		}
		
		
		
		
	}
	

	public JPanel createBoardGUI() {
		JPanel boardPanel = new JPanel();
		
		return boardPanel;
	}
}
