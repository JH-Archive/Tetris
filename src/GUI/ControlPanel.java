package GUI;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.PauseListener;
import Game.QuitListener;
import Game.RestartListener;

public class ControlPanel extends JPanel {
	// sizes and dimensions
	private int height;
	
	// tetris object
	Tetris t;
	
	// JButtons
	private JButton restartButton;
	private JButton quitButton;
	private JButton pauseButton;
	
	// listeners
	PauseListener pauser;
	RestartListener restarter;
	QuitListener quitter;
	
	public ControlPanel(PauseListener pauser, RestartListener restarter, QuitListener quitter, int height) {
		this.pauser = pauser;
		this.restarter = restarter;
		this.quitter = quitter;
		
		this.t = t;
		this.height = height;
		
		// create buttons
		restartButton = new JButton("Restart");
		quitButton = new JButton("Quit");
		pauseButton = new JButton("Pause");
		
		// add buttons to JPanel
		add(restartButton);
		add(quitButton);
		add(pauseButton);
		
		// attach buttons to ActionListener classes
		restartButton.addActionListener(restarter);
		quitButton.addActionListener(quitter);
		pauseButton.addActionListener(pauser);
		
		// set layout
		setLayout(new GridLayout(2, 2));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

}
