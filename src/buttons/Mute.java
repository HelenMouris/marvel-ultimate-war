package buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.Control;
import view.StartPanel;

public class Mute extends CustomButton {
	private boolean isMute = false;

	public Mute(Control control) {

		setIcon(new ImageIcon("resources/muteb.png"));
		setBounds(420, 6, 45, 45);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setActionCommand("mute");
		addActionListener(control);
		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {
		if (!isMute) {
			StartPanel.clip.stop();
			isMute = true;
		} else {
			try {
				File file = new File("resources/sounds/game.wav");
				StartPanel.clip = AudioSystem.getClip();
				StartPanel.clip.open(AudioSystem.getAudioInputStream(file));
				StartPanel.clip.loop(Clip.LOOP_CONTINUOUSLY);
				StartPanel.clip.start();
				isMute = false;
				return;
			} catch (Exception w) {
				System.err.println(w.getMessage());
			}
		}

	}

	public void mouseEntered(MouseEvent e) {
		setIcon(new ImageIcon("resources/mutebHover.png"));
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
		setIcon(new ImageIcon("resources/muteb.png"));
	}

}
