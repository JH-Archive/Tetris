package GUI;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InfoPanel extends JPanel {
	
	/*
	 * 
	 * InfoPanel										JPanel
	 * 		-nextPiece									JPanel
	 * 				-nextPieceLabel						JLabel
	 * 				-nextPiecePanel						JPanel
	 * 		-controls									JPanel
	 * 				-controlsLabel						JLabel
	 * 				-controlsText						JTextArea
	 * 		-highScore									JPanel
	 * 				-highScoreLabel						JLabel
	 * 				-highScoresText						JTextArea
	 * 
	 */
	
	// sizes and dimensions
	private int row;
	private int col;
	private int square;
	private int border;
	private int start;
	
	// labels
	private final JLabel controlsLabel = new JLabel("Controls:");
	private final JLabel highScoreLabel = new JLabel("High Score:");
	
	// compound JPanels
	private NextPiece nextPanel;
	private JPanel controls;
	private JPanel highScore;
	
	// text Areas
	private JTextArea controlsText;
	private JTextArea highScoreText;
	
	// locations of NextPiecePanel
	private int nextPanelX;
	private int nextPanelY;
	
	public InfoPanel(int row, int col, int square, int border, int start) {
		this.row = row;
		this.col = col;
		this.square = square;
		this.border = border;
		this.start = start;
		
		// create and add three JPanels, and add JLabels to each
		nextPanel = new NextPiece(row, col, square, border, start);
		controls = new JPanel();
		highScore = new JPanel();
		controls.add(controlsLabel);
		highScore.add(highScoreLabel);
		
		// create, configure, and add JTextAreas to controls and highScore JPanels
		controlsText = createTextArea(5, 10);
		highScoreText = createTextArea(3, 6);
		controls.add(controlsText);
		highScore.add(highScoreText);
		
		// set layouts of the three JPanels
		GridLayout subLayout = new GridLayout(2, 1);
		controls.setLayout(subLayout);
		highScore.setLayout(subLayout);
		
		// add sub JPanels to InfoPanel JPanel
		add(nextPanel);
		add(controls);
		add(highScore);
		
		setLayout(new GridLayout(3, 1));
	}
	
	private JTextArea createTextArea(int lines, int width) {
		JTextArea textArea = new JTextArea(lines, width);
		textArea.setLineWrap(true); // uses multiple words
		textArea.setWrapStyleWord(true); // breaks after a word
		return textArea;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		nextPanel.paintComponent(g);
	}

}
