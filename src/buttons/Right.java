package buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Right extends CustomButton {
	private JButton right;

	public Right(JButton right) {
		this.right = right;
	}

	public void mouseEntered(MouseEvent e) {
		right.setIcon(new ImageIcon("resources/rightbHover.png"));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		right.setIcon(new ImageIcon("resources/rightb.png"));
	}
	
	
}
