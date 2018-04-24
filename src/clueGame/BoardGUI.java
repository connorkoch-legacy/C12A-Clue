package clueGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javafx.util.Pair;

public class BoardGUI extends JPanel implements MouseListener{
	private boolean hasMoved;
	private static BoardGUI theInstance = new BoardGUI();
	
	public BoardGUI(){
		hasMoved = true;
		setBackground(Color.gray);
		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(BoardCell[] b: Board.getInstance().board){ // draws all board cells
			for(BoardCell c: b){
				c.draw(g);
			}
		}
		for(Player p: Board.getInstance().getPlayers()){ // draws all players
			p.draw(g);
		}
		repaint();
	}
	
	public JPanel createBoardGUI() {
		JPanel boardPanel = new JPanel();
		repaint();
		return boardPanel;
	}
	
	//Checks to see if the Mouse is clicked in the region of a target cell
	@Override
	public void mouseClicked(MouseEvent e) {
		boolean wrongClick = false;
		Point point = e.getPoint();
		for(BoardCell b : Board.getInstance().getTargets()){
			Rectangle imageBounds = new Rectangle(b.getColPixel(), b.getRowPixel(), b.getPieceDimensions(), b.getPieceDimensions());
			if(imageBounds.contains(point)){
				Board.getInstance().setHumanTurnEnded(true); // used to track if humans turn has finished
				Board.getInstance().getPlayers()[0].setRow(b.getRow());	//Updates the position of player when a cell in the target list is clicked
				Board.getInstance().getPlayers()[0].setColumn(b.getCol());
				wrongClick = false;
				for(BoardCell b2 : Board.getInstance().getTargets()){	//Goes through target list and makes sure they arent colored white anymore
					b2.setTarget(false);
				}
				repaint();
				//Board.getInstance().doMove();
				break;
			}
			wrongClick = true;
		}
		if(wrongClick) {
			wrongClick = false;
			JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "Invalid move. Please try again.", "", JOptionPane.PLAIN_MESSAGE);
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean isHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	public static BoardGUI getTheInstance() {
		return theInstance;
	}
	
	
}
