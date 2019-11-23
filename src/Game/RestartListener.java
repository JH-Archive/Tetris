package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		// restart game
		GameRunner.restart();
	}
}
