package buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import engine.Game;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import view.CurrentChampionPanel;

public class Stone3 extends CustomButton {
	private JLabel a1;
	private CurrentChampionPanel currentPanel;
	private Game game;

	public Stone3(JLabel a1, CurrentChampionPanel currentPanel, Game game) {
		this.a1 = a1;
		this.currentPanel = currentPanel;
		this.game = game;
	}

	public void mouseEntered(MouseEvent e) {
		setAbilityInfo(this.game, a1, 2);
		a1.setIcon(new ImageIcon("resources/nth.png"));
		try {
			File file = new File("resources/sounds/glow.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
			return;
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
	}

	public void mouseExited(MouseEvent e) {
		a1.setText("");
		setPic(this.game, a1, 2);
	}

}
