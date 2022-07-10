package buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.Control;
import view.HowToPlayFrame;

public class Help extends CustomButton {

	public Help(Control control) {

		setIcon(new ImageIcon("resources/helpb.png"));
		setBounds(370, 6, 45, 45);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setActionCommand("help");
		addActionListener(control);
		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		HowToPlayFrame howtoPlay = new HowToPlayFrame();
	}

	public void mouseEntered(MouseEvent e) {
		setIcon(new ImageIcon("resources/helpbHover.png"));
		try {
			File file = new File("resources/sounds/hover.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
			return;
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
	}

	public void mouseExited(MouseEvent e) {
		setIcon(new ImageIcon("resources/helpb.png"));
	}

}
