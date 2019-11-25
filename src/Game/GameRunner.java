package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import GUI.Tetris;

public class GameRunner {
	
	// test controls
	private final static boolean testButton = false;
	private final static boolean testTime = false;
	
	private static int currentTiming; //milliseconds
	private static final int timing = 500;
	private static Timer time;
	private static final double speedMultiplier = 0.95;
	
	private static boolean paused;
	private static boolean dropped;
	private static boolean gameEnd;
	
	private static PauseListener pauser;
	private static RestartListener restarter;
	private static QuitListener quitter;
	
	private static Tetris board;
	
	public static void main(String[] args) {
		
		// instantiate currentTiming
		currentTiming = timing;
		
		// control state
		paused = false;
		dropped = false;
		gameEnd = false;
		
		pauser = new PauseListener();
		restarter = new RestartListener();
		quitter = new QuitListener();
		
		board = new Tetris(pauser, restarter, quitter, 0, testButton);
		
		board.setVisible(true);
		
		board.insertNextPiece();
		
		time = new Timer(currentTiming, new timeRunner());
		time.start();
	}
	
	public static void pause() {
		if (paused) {
			paused = false;
			time.restart();
		}
		else {
			paused = true;
			time.stop();
		}
	}
	
	public static void restart() {
		
		currentTiming = timing;
		
		board.setVisible(false);
		pause();
		
		int highScore = board.getHighScore();
		
		board = null;
		board = new Tetris(pauser, restarter, quitter, highScore, testButton);
		
		board.setVisible(true);
		paused = true;
		pause();
		
		board.insertNextPiece();
	}	
	
	public static void quit() {
		
		paused = false;
		pause();
		
		if (!gameEnd) {
			// ask if they want to continue
			int ready = JOptionPane.showConfirmDialog(board,  "Are you sure you want to quit?");
			if (ready == JOptionPane.NO_OPTION || ready == JOptionPane.CANCEL_OPTION) {
				pause();
				return;
			}
		}
		
		System.out.println("Quitting...");
		System.exit(0);
	}
	
	public static void action(int command) {
		System.out.println("Testing");
		
		if (command == 1) board.rotate();
		else if (command == 2) board.moveLeft();
		else if (command == 3) board.moveRight();
		//else if (command == 4) drop();
		else if (command == 4) board.movePieceDown();
	}
	
	private static void drop() {
		dropped = true;
	}
	
	private static class timeRunner implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!dropped) {
				boolean throwAway = board.movePieceDown();
			}
			else {
				for (int i = 0; i < board.getRows(); i++) {
					boolean moreLeft = board.movePieceDown();
					if (!moreLeft) break;
				}
				dropped = false;
			}
		}
	}
	
	public static void speedUp() {
		double timingDouble = (double) currentTiming;
		timingDouble *= speedMultiplier;
		currentTiming = (int) timingDouble;
		time = new Timer(currentTiming, new timeRunner());
		time.start();
	}
	
	public static void gameOver() {
		pause();
		gameEnd = true;
		board.gameOver();
	}
}
