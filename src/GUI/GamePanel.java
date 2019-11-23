package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Game.Location;
import Game.Piece;

public class GamePanel extends JPanel {
	// sizes and dimensions
	private int row;
	private int col;
	private int square;
	private int border;
	
	// starting positions
	private final int startX;
	private final int startY;
	
	//colors
	private Color[][] colors;
	private final Color defaultColor = Color.GRAY;
	private final Color backgroundColor = Color.BLACK;
	
	public GamePanel(int row, int col, int square, int border, int startX, int startY) {
		this.row = row;
		this.col = col;
		this.square = square;
		this.border = border;
		this.startX = startX;
		this.startY = startY;
		
		// instantiate colors and set all colors to grey at start
		colors = new Color[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				colors[i][j] = defaultColor;
			}
		}
	}
	
	public void insertPiece(Piece p) {
		Location[] orientations = p.getPiece();
		colors[startX][startY] = p.color;
		
		for (int i = 0; i < 3; i++) {
			Location cell = orientations[i];
			colors[startX+cell.dy][startY+cell.dx] = p.color; 
		}
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// always call super.paintComponent or else stuff gets wonky
		super.paintComponent(g);
		
		// draw rectangles for all spots in Game Panel
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				g.setColor(colors[j][i]);
				g.fillRect((square*i)+border, (square*j)+border, square, square);
			}
		}
		
		// draw borders between squares
		g.setColor(backgroundColor);
		g.drawLine(border+(col*square), border, border+(col*square), border+(row*square));
		for (int i = 0; i < col; i++) {
			g.drawLine(border+(i*square), border, border+(i*square), border+(row*square));
		}
		g.drawLine(border, border+(row*square), border+(col*square), border+(row*square));
		for (int j = 0; j < row; j++) {
			g.drawLine(border, border+(j*square), border+(col*square), border+(j*square));
		}
		
		// draw borders around board
		g.setColor(backgroundColor);
		g.fillRect(0, 0, (2*border)+(col*square), border);
		g.fillRect(0, 0, border, (2*border)+(row*square));
		g.fillRect(border+(col*square), 0, border, (2*border)+(row*square));
		g.fillRect(0, border+(row*square), (2*border)+(col*square), border);
	}
}
