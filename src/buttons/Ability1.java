package buttons;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.Control;
import engine.Game;

public class Ability1 extends CustomButton {
	private Game game;

	public Ability1(Game game, Control control) {
		this.game = game;

		if (game.getCurrentChampion().getAbilities().get(0).getCurrentCooldown() == 0) {
			setIcon(new ImageIcon("resources/useb.png"));
		} else {
			setIcon(new ImageIcon("resources/usebHover.png"));
		}
		setSize(120, 30);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setActionCommand("a1");
		addActionListener(control);
		addMouseListener(this);

	}

	public void mouseEntered(MouseEvent e) {
		setIcon(new ImageIcon("resources/usebHover.png"));

	}

	public void mouseExited(MouseEvent e) {
		if (game.getCurrentChampion().getAbilities().get(0).getCurrentCooldown() == 0) {
			setIcon(new ImageIcon("resources/useb.png"));
		} else {
			setIcon(new ImageIcon("resources/usebHover.png"));
		}

	}

}
