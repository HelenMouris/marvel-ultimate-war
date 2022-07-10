package buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Left extends CustomButton {
	private JButton left;

	public Left(JButton left) {
		this.left = left;
	}

	public void mouseEntered(MouseEvent e) {
		left.setIcon(new ImageIcon("resources/leftbHover.png"));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		left.setIcon(new ImageIcon("resources/leftb.png"));
	}
	
	
}
