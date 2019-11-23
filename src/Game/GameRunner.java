package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.Tetris;

public class GameRunner {
	
	private final int timing = 1000; //milliseconds
	private static boolean paused;
	
	private static PauseListener pauser;
	private static RestartListener restarter;
	private static QuitListener quitter;
	
	private static Tetris board;
	
	public static void main(String[] args) {
		
		// control state
		paused = false;
		
		pauser = new PauseListener();
		restarter = new RestartListener();
		quitter = new QuitListener();
		
		board = new Tetris(pauser, restarter, quitter, 0);
		board.setVisible(true);
		
		board.insertNextPiece();
	}
	
	public static void pause() {
		if (paused) paused = false;
		else paused = true;
		System.out.println("Really paused");
	}
	
	public static void restart() {
		
		//** Ask if they are sure
		
		board.setVisible(false);
		paused = true;
		
		int highScore = board.getHighScore();
		
		board = null;
		board = new Tetris(pauser, restarter, quitter, highScore);
		
		board.setVisible(true);
		paused = false;
		
		board.insertNextPiece();
	}	
	
	public static void quit() {
		
		//** Ask if they are sure
		
		System.out.println("Quitting...");
		System.exit(0);
	}
}
