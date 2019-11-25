package GUI;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Game.GameRunner;

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
	private static int score;
	private static int highScore;
	private final static int speedMultiple = 100;
	
	private static JLabel scoreLabel;
	private static JLabel highScoreLabel;
	
	private static final String scoreString = "Current Score: ";
	private static final String highString = "High Score: ";
	
	// labels
	private final JLabel controlsLabel = new JLabel("Controls:");
	
	// compound JPanel
	private JPanel controls;
	
	// text Areas
	private JTextArea controlsText;
	
	public InfoPanel(int highScore) {
		
		// instantiate scores at beginning
		score = -1;
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
	
	public static void incrementScore() {
		// increment score
		score++;
		
		// update high score if needed
		if (highScore <= score) {
			highScore = score;
			highScoreLabel.setText(highString + Integer.toString(highScore));
		}
		
		// change score label text
		scoreLabel.setText(scoreString + Integer.toString(score));
		
		// if a multiple of 100, speed up
		if (score % speedMultiple == 0 && score > 0) {
			GameRunner.speedUp();
		}
		
		//repaint();
	}
	
	public int getHighScore() {
		return highScore;
	}
	
	public int getScore() {
		return score;
	}
}
