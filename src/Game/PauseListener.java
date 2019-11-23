package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseListener implements ActionListener {
	private GameRunner theGame;
	
	public void actionPerformed(ActionEvent e) {
		// pause game
		GameRunner.pause();
	}
}
