package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private int row;
	private int col;
	private int square;
	private int border;
	
	private Color[][] colors;
	
	public GamePanel(int row, int col, int square, int border) {
		this.row = row;
		this.col = col;
		this.square = square;
		this.border = border;
		
		// instantiate colors and set all colors to light grey at start
		colors = new Color[row][col];
		for (Color[] colorRow : colors) {
			for (Color color : colorRow) {
				color = Color.LIGHT_GRAY;
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// always call super.paintComponent or else stuff gets wonky
		super.paintComponent(g);
		
		// draw rectangles for all spots in Game Panel
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				g.setColor(colors[i][j]);
				g.fillRect((square*i)+border, (square*j)+border, square, square);
			}
		}
	}
}
