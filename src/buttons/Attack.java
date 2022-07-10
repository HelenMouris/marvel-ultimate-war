package buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.Control;

public class Attack extends CustomButton {
	private Control control;

	public Attack(Control control) {
		this.control = control;
		
		setIcon(new ImageIcon("resources/attackb.png"));
		setSize(180,70);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setActionCommand("attack");
		addActionListener(control);
		addMouseListener(this);
	}

	public void mouseEntered(MouseEvent e) {
		setIcon(new ImageIcon("resources/attackbHover.png"));
		
	}

	public void mouseExited(MouseEvent e) {
		setIcon(new ImageIcon("resources/attackb.png"));
	}
	
	
}
