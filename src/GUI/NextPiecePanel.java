package GUI;
import Game.Piece;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class NextPiecePanel extends JPanel {
	// sizes and dimensions
	private int row;
	private int col;
	private int square;
	private int border;
	private int start;
	
	private int xStart;
	private int yStart;
	
	// colors
	private Color[][] colors;
	private final Color defaultColor = Color.RED;
	private final Color backgroundColor = Color.BLACK;
	
	// piece object
	private Piece nextPiece;
	
	public NextPiecePanel(int row, int col, int square, int border, int start) {
		this.row = row;
		this.col = col;
		this.square = square;
		this.border = border;
		this.start = start;
		
		xStart = start + border;
		yStart = square + border;
		
		//System.out.println(Integer.toString(xStart) + " " + Integer.toString(yStart));
		
		colors = new Color[row][col];
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				colors[j][i] = defaultColor;
			}
		}
		
		generateNextPiece();
	}
	
	public Piece getNextPiece() {
		Piece nextHolder = nextPiece;
		generateNextPiece();
		return nextHolder;
	}
	
	private void generateNextPiece() {
		String orientations[] =  {"I1", "I2", "O", "T1", "T2", "T3", "T4", 
										"L1", "L2", "L3", "L4", "J1", "J2", 
										"J3", "J4", "S1", "S2", "Z1", "Z2"};
		Random rand = new Random();
		int randIndex = rand.nextInt(19);
		
		nextPiece = new Piece(orientations[randIndex]);
		
		// update colors
	}
	
	public void paintComponent(Graphics g) {
		// always call super.paintComponent
		super.paintComponent(g);
		
		// fill in rectangles
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				g.setColor(colors[j][i]);
				g.fillRect((square*i)+border+xStart, (square*j)+border+yStart, square+xStart, square+yStart);
			}
		}
		
		// draw borders between squares
		g.setColor(backgroundColor);
		g.drawLine(border+(col*square)+xStart, border+yStart, border+(col*square)+xStart, border+(row*square)+yStart);
		for (int i = 0; i < col; i++) {
			g.drawLine(border+(i*square)+xStart, border+yStart, border+(i*square)+xStart, border+(row*square)+yStart);
		}
		g.drawLine(border+xStart, border+(row*square)+yStart, border+(col*square)+xStart, border+(row*square)+yStart);
		for (int j = 0; j < row; j++) {
			g.drawLine(border+xStart, border+(j*square)+yStart, border+(col*square)+xStart, border+(j*square)+yStart);
		}
		
		// draw borders around board
		g.setColor(backgroundColor);
		g.fillRect(xStart, yStart, (2*border)+(col*square)+xStart, border+yStart);
		g.fillRect(xStart, yStart, border+xStart, (2*border)+(row*square)+yStart);
		g.fillRect(border+(col*square)+xStart, yStart, border+xStart, (2*border)+(row*square)+yStart);
		g.fillRect(xStart, border+(row*square)+yStart, (2*border)+(col*square)+xStart, border+yStart);		
	}
}
