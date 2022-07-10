package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import buttons.Help;
import buttons.Move;
import buttons.Mute;
import controller.Control;
import engine.Game;
import model.world.Champion;

public class Player2Panel extends JPanel {
	private Game game;
	private JLabel header;
	private JLabel title;
	public static Team2Members team2;
	private JLabel name;
	private JButton mute;
	private JButton help;
	private Mute muteListener;
	private Help helpListener;

	public Player2Panel(MainFrame frame, Game game, Control control) {
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
		header.setLayout(null);

		mute = new Mute(control);
		help = new Help(control);

		header.add(help);
		header.add(mute);

		title = new JLabel();
		title.setLayout(new BorderLayout());

		name = new JLabel(game.getSecondPlayer().getName() + "  ");
		name.setForeground(Color.BLUE);
		name.setFont(new Font("Sans Serif", Font.BOLD, 50));
		name.setVisible(true);
		name.setOpaque(false);
		name.setIcon(new ImageIcon("resources/lbNUsed.png"));
		name.setHorizontalTextPosition(JLabel.RIGHT);
		name.setIconTextGap(20);

		title.setBounds(0, 50, 500, 80);
		title.setVisible(true);
		title.setOpaque(false);
		title.add(name, BorderLayout.EAST);

		team2 = new Team2Members(game, frame);
		team2.setBounds(0, 115, 500, 485);
		team2.setVisible(true);
		team2.setOpaque(false);

		add(header);
		add(title);
		add(team2);
	}

	public void updateTilte() {
		if (game.isSecondLeaderAbilityUsed()) {
			name.setIcon(new ImageIcon("resources/lbUsed.png"));
		} else {
			name.setIcon(new ImageIcon("resources/lbNUsed.png"));
		}
	}
}