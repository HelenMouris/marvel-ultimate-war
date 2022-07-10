package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import engine.Player;
import model.world.Champion;

public class Team1Members extends JPanel {
	private Game game;

	private P1Champ1 champ1;
	private P1Champ2 champ2;
	private P1Champ3 champ3;

	public Team1Members(Game game, MainFrame frame) {
		this.game = game;
		setLayout(null);

		champ1 = new P1Champ1(frame, game);
		champ1.setBounds(5, 10, 300, 155);
		champ1.setOpaque(false);
		champ1.setVisible(true);

		champ2 = new P1Champ2(frame, game);
		champ2.setBounds(5, 170, 300, 155);
		champ2.setOpaque(false);
		champ2.setVisible(true);

		champ3 = new P1Champ3(frame, game);
		champ3.setBounds(5, 330, 300, 155);
		champ3.setOpaque(false);
		champ3.setVisible(true);

		add(champ1);
		add(champ2);
		add(champ3);

	}

	public void updateTeam() {
		if (game.getFirstPlayer().getTeam().size() == 0) {
			remove(champ1);
			remove(champ2);
			remove(champ3);
		} else if (game.getFirstPlayer().getTeam().size() == 1) {
			remove(champ2);
			remove(champ3);
			champ1.updateInfo();
		} else if (game.getFirstPlayer().getTeam().size() == 2) {
			remove(champ3);
			champ1.updateInfo();
			champ2.updateInfo();
		} else if (game.getFirstPlayer().getTeam().size() == 3) {
			champ1.updateInfo();
			champ2.updateInfo();
			champ3.updateInfo();
		}

	}

}
