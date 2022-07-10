package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import engine.Game;
import model.effects.Effect;
import model.world.Champion;
import model.world.Hero;
import model.world.Villain;

public class P1Champ3 extends ChampDetails {
	private Game game;

	public P1Champ3(MainFrame frame, Game game) {
		super(2, "first", game);
		this.game = game;

		setLayout(null);

		getPic().setBounds(5, 5, 140, 145);
		getPic().setIcon(getImage(game.getFirstPlayer().getTeam().get(2)));
		getPic().addMouseListener(this);

		getHp().setBounds(145, 5, 150, 20);

		getEffects().setBounds(145, 5, 145, 145);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		getPic().setIcon(new ImageIcon("empty.png"));
		getPic().setText(getChampDetails(2, game, "first"));
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

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		getPic().setIcon(getImage(game.getFirstPlayer().getTeam().get(2)));
		getPic().setText("");
	}

	public void updateInfo() {
		getPic().setIcon(getImage(game.getFirstPlayer().getTeam().get(2)));
		getHp().setValue(game.getFirstPlayer().getTeam().get(2).getCurrentHP());
		getEffects().setText(getEffectsText(2, game, "first"));
	}

}