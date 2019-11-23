package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Game.*;

public class Tetris extends JFrame {
	
	// The game runner is what runs the game actions themselves
	GameRunner master;
	
	// JPanel instance variables
	private GamePanel gp; // creates the actual tetris game visuals
	private InfoPanel ip; // creates the side bar showing info
	private ControlPanel cp; // creates the bottom panel giving user options
	
	// menu instance variable
	private JMenuBar menu;
	
	// instance variables for Game Panel
	private final int gpCol = 10;
	private final int gpRow = 20;
	private final int gpSquare = 30;
	private final int gpBorder = 15;
	private final int startX = 4;
	private final int startY = 0;
	
	// instance variables for Info Panel
	private final int ipWidth = 180;
	
	// instance variables for Control Panel
	private final int cpHeight = 200;		
	
	// instance variables for Game Control
	private PauseListener pauser;
	private RestartListener restarter;
	private QuitListener quitter;
	
	// pieces
	private Piece currentPiece;
	private Piece nextPiece;
	
	public Tetris(PauseListener pauser, RestartListener restarter, QuitListener quitter, int highScore) {
		
		this.pauser = pauser;
		this.restarter = restarter;
		this.quitter = quitter;
		
		// set GUI parameters
		setSize(new Dimension(windowRowSize(), windowColSize()));
		setTitle("Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create and add info panel
		ip = new InfoPanel(highScore);
		add(ip, BorderLayout.EAST);
		
		// create and add game panel
		gp = new GamePanel(gpRow, gpCol, gpSquare, gpBorder, startX, startY);
		add(gp, BorderLayout.CENTER);
		
		// create and add control panel
		cp = new ControlPanel(pauser, restarter, quitter, cpHeight);
		add(cp, BorderLayout.SOUTH);
		
		// create and set menu
		menu = createMenuBar();
		setJMenuBar(menu);
		
		// create original random piece to be first
		nextPiece = new Piece();
	}
	
	private int windowRowSize() {
		int size = 0;
		size += (gpBorder * 2);
		size += (gpSquare * gpCol);
		size += ipWidth;
		return size;
	}
	
	private int windowColSize() {
		int size = 0;
		size += (gpBorder * 2);
		size += (gpSquare * gpRow);
		size += cpHeight;
		return size;
	}
	
	private JMenuBar createMenuBar() {
		// create menu bar and its menu's
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = createFileMenu();
		JMenu colorMenu = createColorMenu();
		
		// add menu's to menu bar
		bar.add(fileMenu);
		bar.add(colorMenu);
		
		// return menu bar
		return bar;
	}
	
	private JMenu createFileMenu() {
		// create file menu
		JMenu fileMenu = new JMenu("File");
		
		// create and add pause option to menu
		JMenuItem pause = new JMenuItem("Pause");
		pause.addActionListener(pauser);
		fileMenu.add(pause);
		
		// create and add restart option to menu
		JMenuItem restart = new JMenuItem("Restart");
		restart.addActionListener(restarter);
		fileMenu.add(restart);
		
		// create and add quit option to menu
		JMenuItem exit = new JMenuItem("Quit");
		exit.addActionListener(quitter);
		fileMenu.add(exit);
		
		// return file menu
		return fileMenu;
	}
	
	private JMenu createColorMenu() {
		// create color menu
		JMenu colorMenu = new JMenu("Colors");
		
		// add in development button that does nothing
		JMenuItem inDev = new JMenuItem("In development");
		inDev.addActionListener(null);
		colorMenu.add(inDev);
		
		// return color menu
		return colorMenu;
	}
	
	public int getHighScore() {
		return ip.getHighScore();
	}
	
	public void insertNextPiece() {
		currentPiece = nextPiece;
		nextPiece = new Piece();
		gp.insertPiece(currentPiece);
	}
	
}
