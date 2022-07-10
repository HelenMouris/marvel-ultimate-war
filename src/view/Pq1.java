package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import model.world.Champion;
import model.world.Condition;

public class Pq1 extends JPanel {
	private Game game;
	private MainFrame frame;
	private JLabel c1;
	private JLabel c2;
	private JLabel c3;

	public Pq1(Game game, MainFrame frame) {
		this.game = game;
		this.frame = frame;

		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		setVisible(true);
		setOpaque(false);

		c1 = new JLabel();
		c1.setVisible(true);
		c1.setOpaque(false);

		c2 = new JLabel();
		c2.setVisible(true);
		c2.setOpaque(false);

		c3 = new JLabel();
		c3.setVisible(true);
		c3.setOpaque(false);

		ArrayList<Champion> temp = new ArrayList<Champion>();
		Champion c = (Champion) game.getTurnOrder().remove();
		temp.add(c);
		ImageIcon i = AvailableChampions.getSmallPic(c);
		c1.setIcon(i);

		c = (Champion) game.getTurnOrder().remove();
		temp.add(c);
		i = AvailableChampions.getSmallPic(c);
		c2.setIcon(i);

		c = (Champion) game.getTurnOrder().remove();
		temp.add(c);
		i = AvailableChampions.getSmallPic(c);
		c3.setIcon(i);

		add(c1);
		add(c2);
		add(c3);

		for (Champion t : temp) {
			game.getTurnOrder().insert(t);
		}
	}

	public void updatepq() {
		if (game.getTurnOrder().size() == 0) {
			return;
		} else if (game.getTurnOrder().size() == 1) {
			ArrayList<Champion> temp = new ArrayList<Champion>();
			Champion c = (Champion) game.getTurnOrder().remove();
			temp.add(c);
			ImageIcon i = AvailableChampions.getSmallPic(c);
			c1.setIcon(i);

			add(c1);
			remove(c2);
			remove(c3);

			for (Champion t : temp) {
				game.getTurnOrder().insert(t);
			}

		} else if (game.getTurnOrder().size() == 2) {
			ArrayList<Champion> temp = new ArrayList<Champion>();
			Champion c = (Champion) game.getTurnOrder().remove();
			temp.add(c);
			ImageIcon i = AvailableChampions.getSmallPic(c);
			c1.setIcon(i);

			c = (Champion) game.getTurnOrder().remove();
			temp.add(c);
			i = AvailableChampions.getSmallPic(c);
			c2.setIcon(i);

			add(c1);
			add(c2);
			remove(c3);

			for (Champion t : temp) {
				game.getTurnOrder().insert(t);
			}

		} else if (game.getTurnOrder().size() >= 3) {
			ArrayList<Champion> temp = new ArrayList<Champion>();
			Champion c = (Champion) game.getTurnOrder().remove();
			temp.add(c);
			ImageIcon i = AvailableChampions.getSmallPic(c);
			c1.setIcon(i);

			c = (Champion) game.getTurnOrder().remove();
			temp.add(c);
			i = AvailableChampions.getSmallPic(c);
			c2.setIcon(i);

			c = (Champion) game.getTurnOrder().remove();
			temp.add(c);
			i = AvailableChampions.getSmallPic(c);
			c3.setIcon(i);

			add(c1);
			add(c2);
			add(c3);
			for (Champion t : temp) {
				game.getTurnOrder().insert(t);
			}
		}
	}
}
