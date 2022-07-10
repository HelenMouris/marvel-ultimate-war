package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Control;
import engine.Game;
import model.world.Champion;

public class Player1Panel extends JPanel {
	private Game game;
	private JLabel header;
	private JLabel title;
	public static Team1Members team1;

	public Player1Panel(MainFrame frame, Game game, Control control) {
		super();
		this.game = game;
		setLayout(null);
		setSize(500, 600);
		setVisible(true);
		setOpaque(false);

		header = new JLabel();
		header.setBounds(0, 0, 500, 50);
		header.setVisible(true);
		header.setOpaque(false);

		title = new JLabel(game.getFirstPlayer().getName());
		title.setForeground(Color.RED);
		title.setFont(new Font("Sans Serif", Font.BOLD, 50));
		title.setBounds(0, 50, 500, 80);
		title.setVisible(true);
		title.setOpaque(false);
		title.setIcon(new ImageIcon("resources/lbNUsed.png"));
		title.setHorizontalTextPosition(JLabel.LEFT);
		title.setIconTextGap(20);

		team1 = new Team1Members(game, frame);
		team1.setBounds(0, 115, 500, 485);
		team1.setVisible(true);
		team1.setOpaque(false);

		add(header);
		add(title);
		add(team1);
	}

	public void updateTilte() {
		if (game.isFirstLeaderAbilityUsed()) {
			title.setIcon(new ImageIcon("resources/lbUsed.png"));
		} else {
			title.setIcon(new ImageIcon("resources/lbNUsed.png"));
		}
	}

}
