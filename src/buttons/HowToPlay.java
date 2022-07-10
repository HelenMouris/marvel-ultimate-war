package buttons;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class HowToPlay extends CustomButton {
	private JButton b;

	public HowToPlay(JButton b) {
		this.b = b;
	}

	public void mouseEntered(MouseEvent e) {
		b.setFont(new Font("Arial", Font.BOLD, 50));
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
		b.setFont(new Font("Arial", Font.BOLD, 40));
	}

}
