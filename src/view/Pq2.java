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

public class Pq2 extends JPanel {
	private Game game;
	private MainFrame frame;
	private JLabel c4;
	private JLabel c5;
	private JLabel c6;

	public Pq2(Game game, MainFrame frame) {
		this.game = game;
		this.frame = frame;

		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		setVisible(true);
		setOpaque(false);

		c4 = new JLabel();
		c4.setVisible(true);
		c4.setOpaque(false);

		c5 = new JLabel();
		c5.setVisible(true);
		c5.setOpaque(false);

		c6 = new JLabel();
		c6.setVisible(true);
		c6.setOpaque(false);

		ArrayList<Champion> temp = new ArrayList<Champion>();
		Champion c = (Champion) game.getTurnOrder().remove();
		temp.add(c);
		c = (Champion) game.getTurnOrder().remove();
		temp.add(c);
		c = (Champion) game.getTurnOrder().remove();
		temp.add(c);
		c = (Champion) game.getTurnOrder().remove();
		temp.add(c);
		ImageIcon i = AvailableChampions.getSmallPic(c);
		c4.setIcon(i);

		c = (Champion) game.getTurnOrder().remove();
		temp.add(c);
		i = AvailableChampions.getSmallPic(c);
		c5.setIcon(i);

		c = (Champion) game.getTurnOrder().remove();
		temp.add(c);
		i = AvailableChampions.getSmallPic(c);
		c6.setIcon(i);

		add(c4);
		add(c5);
		add(c6);

		for (Champion t : temp) {
			game.getTurnOrder().insert(t);
		}
	}

	public void updatepq() {
		ArrayList<Champion> temp = new ArrayList<Champion>();
		Champion c;

		if (game.getTurnOrder().size() > 3) {
			c = (Champion) game.getTurnOrder().remove();
			temp.add(c);
			c = (Champion) game.getTurnOrder().remove();
			temp.add(c);
			c = (Champion) game.getTurnOrder().remove();
			temp.add(c);
			if (game.getTurnOrder().size() == 1) {
				c = (Champion) game.getTurnOrder().remove();
				temp.add(c);
				ImageIcon i = AvailableChampions.getSmallPic(c);
				c4.setIcon(i);

				add(c4);
				remove(c5);
				remove(c6);

				for (Champion t : temp) {
					game.getTurnOrder().insert(t);
				}

			} else if (game.getTurnOrder().size() == 2) {
				c = (Champion) game.getTurnOrder().remove();
				temp.add(c);
				ImageIcon i = AvailableChampions.getSmallPic(c);
				c4.setIcon(i);

				c = (Champion) game.getTurnOrder().remove();
				temp.add(c);
				i = AvailableChampions.getSmallPic(c);
				c5.setIcon(i);

				add(c4);
				add(c5);
				remove(c6);

				for (Champion t : temp) {
					game.getTurnOrder().insert(t);
				}

			} else if (game.getTurnOrder().size() == 3) {
				c = (Champion) game.getTurnOrder().remove();
				temp.add(c);
				ImageIcon i = AvailableChampions.getSmallPic(c);
				c4.setIcon(i);

				c = (Champion) game.getTurnOrder().remove();
				temp.add(c);
				i = AvailableChampions.getSmallPic(c);
				c5.setIcon(i);

				c = (Champion) game.getTurnOrder().remove();
				temp.add(c);
				i = AvailableChampions.getSmallPic(c);
				c6.setIcon(i);

				add(c4);
				add(c5);
				add(c6);
				for (Champion t : temp) {
					game.getTurnOrder().insert(t);
				}
			}
		} else {
			remove(c4);
			remove(c5);
			remove(c6);
		}

	}
}
