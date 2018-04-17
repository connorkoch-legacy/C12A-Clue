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
		
		for(BoardCell[] b: Board.board){ // draws all board cells
			for(BoardCell c: b){
				c.draw(g);
			}
		}
		for(Player p: Board.getPlayers()){ // draws all players
			p.draw(g);
		}
		
	}
	

	public JPanel createBoardGUI() {
		JPanel boardPanel = new JPanel();
		
		return boardPanel;
	}
}
