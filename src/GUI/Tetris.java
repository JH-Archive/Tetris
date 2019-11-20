package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	private final int gpSquare = 25;
	private final int gpBorder = 10;
	
	// instance variables for Info Panel
	private final int ipWidth = 180;
	private final int ipNextDisplayHeight = 60;
	
	// instance variables for Control Panel
	private final int cpHeight = 150;
	
	public Tetris() {
		
		// set GUI parameters
		setSize(new Dimension(windowRowSize(), windowColSize()));
		setTitle("Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create and add game panel
		gp = new GamePanel(gpCol, gpRow, gpSquare, gpBorder);
		add(gp, BorderLayout.CENTER);
		
		// create and add info panel
		ip = new InfoPanel(ipWidth, ipNextDisplayHeight);
		add(ip, BorderLayout.EAST);
		
		// create and add control panel
		cp = new ControlPanel(cpHeight);
		add(cp, BorderLayout.SOUTH);
		
		// create and set menu
		menu = createMenuBar();
		setJMenuBar(menu);
		
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
	
	
	
	public static void main(String[] args) {
		Tetris gui = new Tetris();
		gui.setVisible(true);
	}
	
}
