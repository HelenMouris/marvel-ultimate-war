package buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Down extends CustomButton {
	private JButton down;

	public Down(JButton down) {
		this.down = down;
	}

	public void mouseEntered(MouseEvent e) {
		down.setIcon(new ImageIcon("resources/downbHover.png"));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		down.setIcon(new ImageIcon("resources/downb.png"));
	}
	
	
}
