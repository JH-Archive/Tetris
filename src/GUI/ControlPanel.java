package GUI;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
	// sizes and dimensions
	private int height;
	
	// score
	private int score;
	
	// tetris object
	Tetris t;
	
	// JButtons
	private JButton restartButton;
	private JButton quitButton;
	private JButton pauseButton;
	
	// JLabel
	private JLabel scoreLabel;
	
	public ControlPanel(Tetris t, int height) {
		this.t = t;
		this.height = height;
		score = 0;
		
		// create buttons and label
		restartButton = new JButton("Restart");
		quitButton = new JButton("Quit");
		pauseButton = new JButton("Pause");
		scoreLabel  = new JLabel("Score: 0");
		
		// add buttons and label to JPanel
		add(restartButton);
		add(quitButton);
		add(pauseButton);
		add(scoreLabel);
		
		// attach buttons to ActionListener classes
		restartButton.addActionListener(new restartListener());
		quitButton.addActionListener(new quitListener());
		pauseButton.addActionListener(new pauseListener());
		
		// set layout
		setLayout(new GridLayout(2, 2));
	}
	
	public void incrementScore() {
		score++;
		scoreLabel.setText("Score: " + Integer.toString(score));
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public int getScore() {
		return score;
	}
	
	private class restartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			t.restartGame();
		}
	}
	
	private class quitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	private class pauseListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			t.pauseGame();
		}
	}

}
