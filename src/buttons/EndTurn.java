package buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.Control;

public class EndTurn extends CustomButton {
	private Control control;

	public EndTurn(Control control) {
		this.control = control;
		
		setIcon(new ImageIcon("resources/endTurnb.png"));
		setSize(180,70);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setActionCommand("endTurn");
		addActionListener(control);
		addMouseListener(this);
	}

	public void mouseEntered(MouseEvent e) {
		setIcon(new ImageIcon("resources/endTurnbHover.png"));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setIcon(new ImageIcon("resources/endTurnb.png"));
	}
	
	
}
