package buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Up extends CustomButton {
	private JButton up;

	public Up(JButton up) {
		this.up = up;
	}

	public void mouseEntered(MouseEvent e) {
		up.setIcon(new ImageIcon("resources/upbHover.png"));
		
	}

	public void mouseExited(MouseEvent e) {
		up.setIcon(new ImageIcon("resources/upb.png"));
	}
	
	
}
