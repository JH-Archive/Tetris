package GUI;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NextPiece extends JPanel {
	private JLabel label;
	private NextPiecePanel panel;
	
	public NextPiece(int row, int col, int square, int border, int start) {
		// create components
		label = new JLabel("Next Piece:");
		panel = new NextPiecePanel(row, col, square, border, start);
		
		// add components to panel
		add(label);
		add(panel);
		
		// set layout
		setLayout(new GridLayout(2, 1));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		panel.paintComponent(g);
	}
}
