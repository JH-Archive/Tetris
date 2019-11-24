package GUI;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.GameRunner;
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
	private JButton testRotate;
	private JButton testLeft;
	private JButton testRight;
	private JButton testDrop;
	
	// listeners
	PauseListener pauser;
	RestartListener restarter;
	QuitListener quitter;
	
	// test mode
	private final boolean test;
	
	public ControlPanel(PauseListener pauser, RestartListener restarter, QuitListener quitter, 
						int height, boolean test) {
		this.pauser = pauser;
		this.restarter = restarter;
		this.quitter = quitter;
		
		this.t = t;
		this.height = height;
		this.test = test;
		
		// create buttons
		restartButton = new JButton("Restart");
		quitButton = new JButton("Quit");
		pauseButton = new JButton("Pause");
		
		// add buttons to JPanel
		add(restartButton);
		add(quitButton);
		add(pauseButton);
		
		if (test) {
			testListener testListen = new testListener();
			
			// rotate control
			testRotate = new JButton("Test Rotate");
			testRotate.addActionListener(testListen);
			add(testRotate);
			
			// left control
			testLeft = new JButton("Test Left");
			testLeft.addActionListener(testListen);
			add(testLeft);
						
			// right control
			testRight = new JButton("Test Right");
			testRight.addActionListener(testListen);
			add(testRight);
			
			// drop control
			testDrop = new JButton("Test Drop");
			testDrop.addActionListener(testListen);
			add(testDrop);
		}
		
		// attach buttons to ActionListener classes
		restartButton.addActionListener(restarter);
		quitButton.addActionListener(quitter);
		pauseButton.addActionListener(pauser);
		
		// set layout
		if (test) setLayout(new GridLayout(3, 3));
		else setLayout(new GridLayout(2, 2));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	private class testListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == testRotate) GameRunner.action(1);
			else if (e.getSource() == testLeft) GameRunner.action(2);
			else if (e.getSource() == testRight) GameRunner.action(3);
			else if (e.getSource() == testDrop) GameRunner.action(4);
		}
	}

}
