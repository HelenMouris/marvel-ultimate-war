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

public class ChampDetails extends JPanel implements MouseListener {
	private JLabel pic;
	private JLabel effects;
	private JProgressBar hp;

	public ChampDetails(int i, String type, Game game) {
		setLayout(null);

		pic = new JLabel();
		pic.setText("");
		pic.setForeground(Color.WHITE);
		pic.setFont(new Font("Arial", Font.BOLD, 13));
		pic.setOpaque(false);
		pic.setVisible(true);
		pic.addMouseListener(this);

		effects = new JLabel();
		effects.setForeground(Color.WHITE);
		effects.setOpaque(false);
		effects.setVisible(true);

		if (type.equals("first")) {
			hp = new JProgressBar(0, game.getFirstPlayer().getTeam().get(i).getMaxHP());
			hp.setValue(game.getFirstPlayer().getTeam().get(i).getMaxHP());
		} else {
			hp = new JProgressBar(0, game.getSecondPlayer().getTeam().get(i).getMaxHP());
			hp.setValue(game.getSecondPlayer().getTeam().get(i).getMaxHP());
		}
		hp.setStringPainted(true);
		hp.setFont(new Font("Arial", Font.BOLD, 15));
		hp.setForeground(Color.RED);
		hp.setOpaque(false);

		add(pic);
		add(hp);
		add(effects);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public String getChampDetails(int i, Game game, String type) {
		String result = "<html>";
		if (type.equals("first")) {
			if (game.getFirstPlayer().getLeader() == game.getFirstPlayer().getTeam().get(i))
				result = result + "LEADER" + "<br/>";
			return result + "Type: " + getType(i, game, type) + "<br/>" + "HP: "
					+ game.getFirstPlayer().getTeam().get(i).getCurrentHP() + "<br/>" + "Mana: "
					+ game.getFirstPlayer().getTeam().get(i).getMana() + "<br/>" + "Speed: "
					+ game.getFirstPlayer().getTeam().get(i).getSpeed() + "<br/>" + "Max Action pts: "
					+ game.getFirstPlayer().getTeam().get(i).getMaxActionPointsPerTurn() + "<br/>" + "Attack damage: "
					+ game.getFirstPlayer().getTeam().get(i).getAttackDamage() + "<br/>" + "Attack range: "
					+ game.getFirstPlayer().getTeam().get(i).getAttackRange() + "<br/>" + "</html>";
		} else {
			if (game.getSecondPlayer().getLeader() == game.getSecondPlayer().getTeam().get(i))
				result = result + "LEADER" + "<br/>";
			return result + "Type: " + getType(i, game, type) + "<br/>" + "HP: "
					+ game.getSecondPlayer().getTeam().get(i).getCurrentHP() + "<br/>" + "Mana: "
					+ game.getSecondPlayer().getTeam().get(i).getMana() + "<br/>" + "Speed: "
					+ game.getSecondPlayer().getTeam().get(i).getSpeed() + "<br/>" + "Max Action pts: "
					+ game.getSecondPlayer().getTeam().get(i).getMaxActionPointsPerTurn() + "<br/>" + "Attack damage: "
					+ game.getSecondPlayer().getTeam().get(i).getAttackDamage() + "<br/>" + "Attack range: "
					+ game.getSecondPlayer().getTeam().get(i).getAttackRange() + "<br/>" + "</html>";

		}
	}

	public String getEffectsText(int i, Game game, String type) {
		String s = "<html>";
		if (type.equals("first")) {
			for (Effect e : game.getFirstPlayer().getTeam().get(i).getAppliedEffects()) {
				s = s + e.getName() + ": " + e.getDuration() + "<br/>";
			}
		} else {
			for (Effect e : game.getSecondPlayer().getTeam().get(i).getAppliedEffects()) {
				s = s + e.getName() + ": " + e.getDuration() + "<br/>";
			}
		}
		s = s + "</html>";
		return s;
	}

	public String getType(int i, Game game, String type) {
		if (type.equals("first")) {
			if (game.getFirstPlayer().getTeam().get(i) instanceof Hero) {
				return "Hero";
			} else if (game.getFirstPlayer().getTeam().get(i) instanceof Villain) {
				return "Villain";
			} else {
				return "AntiHero";
			}
		} else {
			if (game.getSecondPlayer().getTeam().get(i) instanceof Hero) {
				return "Hero";
			} else if (game.getSecondPlayer().getTeam().get(i) instanceof Villain) {
				return "Villain";
			} else {
				return "AntiHero";
			}
		}
	}

	public ImageIcon getImage(Champion c) {
		if (c.getName().equals("Captain America"))
			return new ImageIcon("resources/caCard.png");
		if (c.getName().equals("Deadpool"))
			return (new ImageIcon("resources/deadpoolCard.png"));
		if (c.getName().equals("Dr Strange"))
			return (new ImageIcon("resources/drStrangeCard.png"));
		if (c.getName().equals("Electro"))
			return (new ImageIcon("resources/electroCard.png"));
		if (c.getName().equals("Ghost Rider"))
			return (new ImageIcon("resources/ghostRiderCard.png"));
		if (c.getName().equals("Hela"))
			return (new ImageIcon("resources/helaCard.png"));
		if (c.getName().equals("Hulk"))
			return (new ImageIcon("resources/hulkCard.png"));
		if (c.getName().equals("Iceman"))
			return (new ImageIcon("resources/icemanCard.png"));
		if (c.getName().equals("Ironman"))
			return (new ImageIcon("resources/ironmanCard.png"));
		if (c.getName().equals("Loki"))
			return (new ImageIcon("resources/lokiCard.png"));
		if (c.getName().equals("Quicksilver"))
			return (new ImageIcon("resources/quicksilverCard.png"));
		if (c.getName().equals("Spiderman"))
			return (new ImageIcon("resources/spidermanCard.png"));
		if (c.getName().equals("Thor"))
			return (new ImageIcon("resources/thorCard.png"));
		if (c.getName().equals("Venom"))
			return (new ImageIcon("resources/venomCard.png"));
		if (c.getName().equals("Yellow Jacket"))
			return (new ImageIcon("resources/yellowJacketCard.png"));
		return (new ImageIcon("resources/nothing.png"));
	}

	public JLabel getPic() {
		return pic;
	}

	public JLabel getEffects() {
		return effects;
	}

	public JProgressBar getHp() {
		return hp;
	}

}
