package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Game.Direction;
import Game.GameRunner;
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
	
	// current positions
	private int currentX;
	private int currentY;
	
	//colors
	private Color[][] colors;
	private final Color defaultColor = Color.GRAY;
	private final Color backgroundColor = Color.BLACK;
	
	// current piece
	private Piece piece;
	
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
		
		piece = p;
		currentX = startY;
		currentY = startX;
		
		Location[] orientations = piece.getPiece();
		colors[startY][startX] = piece.color;
		
		for (int i = 0; i < 3; i++) {
			Location cell = orientations[i];
			colors[startY+cell.dx][startX+cell.dy] = piece.color; 
		}
		
		InfoPanel.incrementScore();
		
		repaint();
	}
	
	public boolean movePiece(Direction d) {
		
		if (d == Direction.DOWN) {
			if (moveIsLegal(d)) {
				// reset colors
				colors[currentX][currentY] = defaultColor;
				int x, y;
				for (int i = 0; i < 3; i++) {
					x = currentX + piece.getPiece()[i].dx;
					y = currentY + piece.getPiece()[i].dy;
					
					colors[x][y] = defaultColor;
				}
				
				// increment current x
				currentX++;
				
				// set new colors
				colors[currentX][currentY] = piece.color;
				for (int i = 0; i < 3; i++) {
					x = currentX + piece.getPiece()[i].dx;
					y = currentY + piece.getPiece()[i].dy;
					
					colors[x][y] = piece.color;
				}
				
				// repaint
				repaint();
				return true;
			}
			else {
				// reset colors
				colors[currentX][currentY] = defaultColor;
				int x, y;
				for (int i = 0; i < 3; i++) {
					x = currentX + piece.getPiece()[i].dx;
					y = currentY + piece.getPiece()[i].dy;
					
					colors[x][y] = defaultColor;
				}
				
				// change color slightly to promote collisions between the same piece types
				int newRed = piece.color.getRed();
				if (newRed + 1 < 256) newRed++;
				int newGreen = piece.color.getGreen();
				if (newGreen + 1 < 256) newGreen++;
				int newBlue = piece.color.getBlue();
				if (newBlue + 1 < 256) newBlue++;
				Color newColor = new Color(newRed, newGreen, newBlue);
				colors[currentX][currentY] = newColor;
				for (int i = 0; i < 3; i++) {
					x = currentX + piece.getPiece()[i].dx;
					y = currentY + piece.getPiece()[i].dy;
					
					colors[x][y] = newColor;
				}
				
				// remove filled rows
				filledRowRemover();
				
				// check if game over
				if (gameOverChecker()) {
					GameRunner.gameOver();
				}
				
				// repaint
				repaint();
				
				return false;
			}
		}
		else if (d == Direction.LEFT) {
			if (moveIsLegal(d)) {
				// reset colors
				colors[currentX][currentY] = defaultColor;
				int x, y;
				for (int i = 0; i < 3; i++) {
					x = currentX + piece.getPiece()[i].dx;
					y = currentY + piece.getPiece()[i].dy;
					
					colors[x][y] = defaultColor;
				}
				
				// increment current y
				currentY--;
				
				// set new colors
				colors[currentX][currentY] = piece.color;
				for (int i = 0; i < 3; i++) {
					x = currentX + piece.getPiece()[i].dx;
					y = currentY + piece.getPiece()[i].dy;
					
					colors[x][y] = piece.color;
				}
				
				// repaint
				repaint();
				return true;
			}
		}
		else if (d == Direction.RIGHT) {
			if (moveIsLegal(d)) {
				// reset colors
				colors[currentX][currentY] = defaultColor;
				int x, y;
				for (int i = 0; i < 3; i++) {
					x = currentX + piece.getPiece()[i].dx;
					y = currentY + piece.getPiece()[i].dy;
					
					colors[x][y] = defaultColor;
				}
				
				// increment current y
				currentY++;
				
				// set new colors
				colors[currentX][currentY] = piece.color;
				for (int i = 0; i < 3; i++) {
					x = currentX + piece.getPiece()[i].dx;
					y = currentY + piece.getPiece()[i].dy;
					
					colors[x][y] = piece.color;
				}
				
				// repaint
				repaint();
				return true;
			}
		}
		return false;
	}
	
	private boolean gameOverChecker() {
		for (int i = 0; i < col; i++) {
			if (colors[0][i] != defaultColor) return true;
		}
		return false;
	}
	
	private void filledRowRemover() {
		// for each row
		for (int i = 0; i < row; i++) {
			boolean filled = true;
			
			// for each column
			for (int j = 0; j < col; j++) {
				if (colors[i][j] == defaultColor) filled = false;
			}
			
			if (filled) {
				
				// set each row to be the row above it
				// starting at current row, and going to second row
				for (int k = i; k > 0; k--) {
					for (int l = 0; l < col; l++) {
						colors[k][l] = colors[k-1][l];
					}
				}
				
				// set row 0 to be defaultColor
				for (int l = 0; l < col; l++) {
					colors[0][l] = defaultColor;
				}
			}
		}
	}
	
	private boolean moveIsLegal(Direction d) {
		boolean legal = true;
		
		if (d == Direction.DOWN) {
			if (currentX + 1 >= row) legal = false;
			else if ((colors[currentX + 1][currentY] != defaultColor) && (colors[currentX + 1][currentY] != piece.color)) legal = false;
			
			int x, y;
			for (int i = 0; i < 3; i++) {
				x = currentX + piece.getPiece()[i].dx;
				
				if (x + 1 >= row) legal = false;
				else if ((colors[x + 1][currentY] != defaultColor) && (colors[x + 1][currentY] != piece.color)) legal = false;
			}
		}
		else if (d == Direction.LEFT) {
			if (currentY == 0) legal = false;
			else if ((colors[currentX][currentY-1] != defaultColor) && (colors[currentX][currentY-1] != piece.color)) legal = false;
			
			int x, y;
			for (int i = 0; i < 3; i++) {
				y = currentY + piece.getPiece()[i].dy;
				
				if (y-1 < 0) legal = false;
				else if ((colors[currentX][y - 1] != defaultColor) && (colors[currentX][y - 1] != piece.color)) legal = false;
			}
		}
		else if (d == Direction.RIGHT) {
			if (currentY + 1 == col) legal = false;
			else if ((colors[currentX][currentY+1] != defaultColor) && (colors[currentX][currentY+1] != piece.color)) legal = false;
			
			int x, y;
			for (int i = 0; i < 3; i++) {
				y = currentY + piece.getPiece()[i].dy;
				
				if (y+1 >= col) legal = false;
				else if ((colors[currentX][y + 1] != defaultColor) && (colors[currentX][y + 1] != piece.color)) legal = false;
			}
		}
		
		return legal;
	}
	
	public void rotate() {
		if (rotationIsLegal()) {
			// reset colors
			colors[currentX][currentY] = defaultColor;
			int x, y;
			for (int i = 0; i < 3; i++) {
				x = currentX + piece.getPiece()[i].dx;
				y = currentY + piece.getPiece()[i].dy;
				
				colors[x][y] = defaultColor;
			}
			
			// rotate piece
			Piece rotated = piece.rotatedVersion();
			piece = rotated;
			
			// set new colors
			colors[currentX][currentY] = piece.color;
			for (int i = 0; i < 3; i++) {
				x = currentX + piece.getPiece()[i].dx;
				y = currentY + piece.getPiece()[i].dy;
				
				colors[x][y] = piece.color;
			}
			
			// repaint
			repaint();
		}
	}
	
	private boolean rotationIsLegal() {
		Piece rotatedPiece = piece.rotatedVersion();
		Location[] rotated = rotatedPiece.getPiece();
		
		int x, y;
		for (int i = 0; i < 3; i++) {
			x = currentX + rotated[i].dx;
			y = currentY + rotated[i].dy;
			
			if (x < 0 || x >= row) return false;
			if (y < 0 || y >= col) return false;
			if ((colors[x][y] != defaultColor) && (colors[x][y] != piece.color)) return false;
		}
		
		return true;
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
