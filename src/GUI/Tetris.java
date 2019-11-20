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
	
	// instance variables for Info Panel
	private final int ipBorder = 15;
	private final int ipSquare = 30;
	private final int ipRow = 4;
	private final int ipCol = 5;
	
	// instance variables for Control Panel
	private final int cpHeight = 200;
	
	// other
	boolean paused;
	boolean restart;
	
	public Tetris() {
		paused = false;
		restart = false;
		
		// set GUI parameters
		setSize(new Dimension(windowRowSize(), windowColSize()));
		setTitle("Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		
		// create and add info panel
		ip = new InfoPanel(ipRow, ipCol, ipSquare, ipBorder, ipStart());
		add(ip, BorderLayout.EAST);
		
		// create and add game panel
		gp = new GamePanel(gpRow, gpCol, gpSquare, gpBorder);
		add(gp, BorderLayout.CENTER);
		
		// create and add control panel
		cp = new ControlPanel(this, cpHeight);
		add(cp, BorderLayout.SOUTH);
		
		// create and set menu
		menu = createMenuBar();
		setJMenuBar(menu);
	}
	
	private int windowRowSize() {
		int size = 0;
		size += (gpBorder * 2);
		size += (gpSquare * gpCol);
		size += (2*ipBorder)+(ipSquare*ipCol);
		return size;
	}
	
	private int windowColSize() {
		int size = 0;
		size += (gpBorder * 2);
		size += (gpSquare * gpRow);
		size += cpHeight;
		return size;
	}
	
	private int ipStart() {
		int start = 0;
		start += (2 * gpBorder);
		start += (gpSquare * gpCol);
		return start;
	}
	
	private JMenuBar createMenuBar() {
		// create menu bar and the file menu
		JMenuBar bar = new JMenuBar();
		JMenu file = createFileMenu();
		
		// add file menu to the menu bar
		bar.add(file);
		
		// return menu bar'
		return bar;
	}
	
	private JMenu createFileMenu() {
		// create file menu, and its entry the exit button
		JMenu file = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		
		// create ActionListener class to define exit item behavior
		class ExitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		
		// attach exit item to the ExitListener class
		exit.addActionListener(new ExitListener());
		
		// add exit menu item to file menu
		file.add(exit);
		
		// return file menu
		return file;
	}
	
	public void restartActions() {
		//remove and kill every JPanel
		remove(gp);
		gp = null;
		remove(ip);
		ip = null;
		remove(cp);
		cp = null;
		
		// create and add game panel
		gp = new GamePanel(gpRow, gpCol, gpSquare, gpBorder);
		add(gp, BorderLayout.CENTER);
		
		// create and add info panel
		ip = new InfoPanel(ipRow, ipCol, ipSquare, ipBorder, ipStart());
		add(ip, BorderLayout.EAST);
		
		// create and add control panel
		cp = new ControlPanel(this, cpHeight);
		add(cp, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		Tetris gui = new Tetris();
		gui.setVisible(true);
	}
	
	public void pauseGame() {
		if (paused) paused = false;
		else paused = true;
		System.out.println("Paused");
	}
	
	public void restartGame() {
		restart = true;
		System.out.println("Restart");
	}
	
}
