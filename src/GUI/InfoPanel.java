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
	 * 		-controls									JPanel
	 * 				-controlsLabel						JLabel
	 * 				-controlsText						JTextArea
	 * 		-highScore									JLabel
	 * 		-currentScore								JLabel
	 * 
	 */
	
	// score labels
	private int score;
	private int highScore;
	
	private JLabel scoreLabel;
	private JLabel highScoreLabel;
	
	private final String scoreString = "Current Score: ";
	private final String highString = "High Score: ";
	
	// labels
	private final JLabel controlsLabel = new JLabel("Controls:");
	
	// compound JPanel
	private JPanel controls;
	
	// text Areas
	private JTextArea controlsText;
	
	public InfoPanel(int highScore) {
		
		// instantiate scores at beginning
		score = 0;
		this.highScore = highScore;
		
		// create score JLabels
		scoreLabel = new JLabel(scoreString + "0");
		highScoreLabel = new JLabel(highString + Integer.toString(highScore));
		
		// create and add controls JPanel
		controls = new JPanel();
		controls.add(controlsLabel);
		
		// create, configure, and add JTextArea to controls
		controlsText = createTextArea(5, 10);
		controls.add(controlsText);
		
		// set layouts of the three JPanels
		controls.setLayout(new GridLayout(2, 1));
		
		// add sub JPanels and JLabels to InfoPanel JPanel
		add(controls);
		add(scoreLabel);
		add(highScoreLabel);
		
		setLayout(new GridLayout(3, 1));
	}
	
	private JTextArea createTextArea(int lines, int width) {
		JTextArea textArea = new JTextArea(lines, width);
		textArea.setLineWrap(true); // uses multiple words
		textArea.setWrapStyleWord(true); // breaks after a word
		return textArea;
	}
	
	public void incrementScore() {
		// increment score
		score++;
		
		// update high score if needed
		if (highScore <= score) {
			highScore = score;
			highScoreLabel.setText(highString + Integer.toString(highScore));
		}
		
		// change score label text
		scoreLabel.setText(scoreLabel + Integer.toString(score));
		
		repaint();
	}
	
	public int getHighScore() {
		return highScore;
	}

}
