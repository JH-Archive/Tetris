package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		// quit game
		GameRunner.quit();
	}
}
