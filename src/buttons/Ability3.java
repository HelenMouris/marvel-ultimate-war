package buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.Control;
import engine.Game;

public class Ability3 extends CustomButton {
	private Game game;

	public Ability3(Game game, Control control) {
		this.game = game;

		if (game.getCurrentChampion().getAbilities().get(2).getCurrentCooldown() == 0) {
			setIcon(new ImageIcon("resources/useb.png"));
		} else {
			setIcon(new ImageIcon("resources/usebHover.png"));
		}
		setSize(130, 40);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setActionCommand("a3");
		addActionListener(control);
		addMouseListener(this);
	}

	public void mouseEntered(MouseEvent e) {
		setIcon(new ImageIcon("resources/usebHover.png"));

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (game.getCurrentChampion().getAbilities().get(2).getCurrentCooldown() == 0) {
			setIcon(new ImageIcon("resources/useb.png"));
		} else {
			setIcon(new ImageIcon("resources/usebHover.png"));
		}

	}

}
