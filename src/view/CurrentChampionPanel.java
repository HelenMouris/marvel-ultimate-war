package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import buttons.Ability1;
import buttons.Ability2;
import buttons.Ability3;
import buttons.Ability4;
import buttons.Right;
import buttons.Stone1;
import buttons.Stone2;
import buttons.Stone3;
import buttons.Stone4;
import controller.Control;
import engine.Game;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Hero;
import model.world.Villain;

public class CurrentChampionPanel extends JPanel implements MouseListener {
	private Game game;
	private Control control;
	private JLabel pic;
	private JLabel info;
	private JLabel a1;
	private JLabel a2;
	private JLabel a3;
	private JLabel a4 = new JLabel();
	private JLabel a1Name;
	private JLabel a2Name;
	private JLabel a3Name;
	private JLabel a4Name;
	private JLabel a1Info;
	private JLabel a2Info;
	private JLabel a3Info;
	private JLabel a4Info;
	private Stone1 stone1Listener;
	private Stone2 stone2Listener;
	private Stone3 stone3Listener;
	private Stone4 stone4Listener;
	private JButton use1;
	private JButton use2;
	private JButton use3;
	private JButton use4;

	public CurrentChampionPanel(MainFrame frame, Game game, Control control) {
		super();
		this.game = game;
		this.control = control;
		setLayout(new GridLayout(1, 6, 5, 0));
		setSize(800, 187);
		setVisible(true);
		setOpaque(false);
		setBorder(BorderFactory.createTitledBorder(null, "Current Champion", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Arial", Font.BOLD, 15), Color.WHITE));

		pic = new JLabel(getImage(game.getCurrentChampion()));
		pic.setBackground(Color.YELLOW);
		pic.setOpaque(false);
		pic.setVisible(true);

		info = new JLabel();
		info.setText(getChampText());
		info.setForeground(Color.WHITE);
		info.setFont(new Font("Arial", Font.PLAIN, 15));
		info.setOpaque(false);
		info.setVisible(true);
		info.addMouseListener(this);

		// ABILITY 1

		a1 = new JLabel();
		a1.setLayout(new BorderLayout());
		a1.setOpaque(false);
		a1.setVisible(true);

		a1Name = new JLabel();
		a1Name.setText(game.getCurrentChampion().getAbilities().get(0).getName());
		a1Name.setForeground(Color.WHITE);
		a1Name.setFont(new Font("Arial", Font.BOLD, 13));
		a1Name.setOpaque(false);
		a1Name.setVisible(true);
		a1Name.setHorizontalAlignment(JLabel.CENTER);
		a1.add(a1Name, BorderLayout.NORTH);

		a1Info = new JLabel();
		setPic(a1Info, 0);
		a1Info.setForeground(Color.WHITE);
		a1Info.setFont(new Font("Arial", Font.BOLD, 8));
		a1Info.setOpaque(false);
		a1Info.setVisible(true);
		a1Info.setHorizontalAlignment(JLabel.CENTER);
		a1Info.setHorizontalTextPosition(JLabel.CENTER);

		stone1Listener = new Stone1(a1Info, this, game);
		a1Info.addMouseListener(stone1Listener);

		a1.add(a1Info, BorderLayout.CENTER);

		use1 = new Ability1(game, control);
		a1.add(use1, BorderLayout.SOUTH);

		// ABILITY 2

		a2 = new JLabel();
		a2.setLayout(new BorderLayout());
		a2.setOpaque(false);
		a2.setVisible(true);

		a2Name = new JLabel();
		a2Name.setText(game.getCurrentChampion().getAbilities().get(1).getName());
		a2Name.setForeground(Color.WHITE);
		a2Name.setFont(new Font("Arial", Font.BOLD, 13));
		a2Name.setOpaque(false);
		a2Name.setVisible(true);
		a2Name.setHorizontalAlignment(JLabel.CENTER);
		a2.add(a2Name, BorderLayout.NORTH);

		a2Info = new JLabel();
		setPic(a2Info, 1);
		a2Info.setForeground(Color.WHITE);
		a2Info.setFont(new Font("Arial", Font.BOLD, 8));
		a2Info.setOpaque(false);
		a2Info.setVisible(true);
		a2Info.setHorizontalAlignment(JLabel.CENTER);
		a2Info.setHorizontalTextPosition(JLabel.CENTER);

		stone2Listener = new Stone2(a2Info, this, game);
		a2Info.addMouseListener(stone2Listener);

		a2.add(a2Info, BorderLayout.CENTER);

		use2 = new Ability2(game, control);
		a2.add(use2, BorderLayout.SOUTH);

		// ABILITY 3

		a3 = new JLabel();
		a3.setLayout(new BorderLayout());
		a3.setOpaque(false);
		a3.setVisible(true);

		a3Name = new JLabel();
		a3Name.setText(game.getCurrentChampion().getAbilities().get(2).getName());
		a3Name.setForeground(Color.WHITE);
		a3Name.setFont(new Font("Arial", Font.BOLD, 13));
		a3Name.setOpaque(false);
		a3Name.setVisible(true);
		a3Name.setHorizontalAlignment(JLabel.CENTER);
		a3.add(a3Name, BorderLayout.NORTH);

		a3Info = new JLabel();
		setPic(a3Info, 2);
		a3Info.setForeground(Color.WHITE);
		a3Info.setFont(new Font("Arial", Font.BOLD, 8));
		a3Info.setOpaque(false);
		a3Info.setVisible(true);
		a3Info.setHorizontalAlignment(JLabel.CENTER);
		a3Info.setHorizontalTextPosition(JLabel.CENTER);

		stone3Listener = new Stone3(a3Info, this, game);
		a3Info.addMouseListener(stone3Listener);

		a3.add(a3Info, BorderLayout.CENTER);

		use3 = new Ability3(game, control);
		a3.add(use3, BorderLayout.SOUTH);

		add(pic);
		add(info);
		add(a1);
		add(a2);
		add(a3);

	}

	public void updateInfo() {
		pic.setIcon(getImage(game.getCurrentChampion()));
		info.setText(getChampText());
		a1Name.setText(game.getCurrentChampion().getAbilities().get(0).getName());
		a2Name.setText(game.getCurrentChampion().getAbilities().get(1).getName());
		a3Name.setText(game.getCurrentChampion().getAbilities().get(2).getName());
		setPic(a1Info, 0);
		setPic(a2Info, 1);
		setPic(a3Info, 2);
		// if punch
		if (game.getCurrentChampion().getAbilities().size() > 3) {
			a4.setLayout(new BorderLayout());
			a4.setOpaque(false);
			a4.setVisible(true);

			a4Name = new JLabel();
			a4Name.setText(game.getCurrentChampion().getAbilities().get(3).getName());
			a4Name.setForeground(Color.WHITE);
			a4Name.setFont(new Font("Arial", Font.BOLD, 13));
			a4Name.setOpaque(false);
			a4Name.setVisible(true);
			a4Name.setHorizontalAlignment(JLabel.CENTER);
			a4.add(a4Name, BorderLayout.NORTH);

			a4Info = new JLabel();
			setPic(a4Info, 3);
			a4Info.setForeground(Color.WHITE);
			a4Info.setFont(new Font("Arial", Font.BOLD, 8));
			a4Info.setOpaque(false);
			a4Info.setVisible(true);

			a4Info.setHorizontalAlignment(JLabel.CENTER);
			a4Info.setHorizontalTextPosition(JLabel.CENTER);

			stone4Listener = new Stone4(a4Info, this, game);
			a4Info.addMouseListener(stone4Listener);

			a4.add(a4Info, BorderLayout.CENTER);

			use4 = new Ability4(game, control);
			a4.add(use4, BorderLayout.SOUTH);

			add(a4);
		} else {
			remove(a4);
		}
		if (game.getCurrentChampion().getAbilities().get(0).getCurrentCooldown() == 0) {
			use1.setIcon(new ImageIcon("resources/useb.png"));
		} else {
			use1.setIcon(new ImageIcon("resources/usebHover.png"));
		}
		if (game.getCurrentChampion().getAbilities().get(1).getCurrentCooldown() == 0) {
			use2.setIcon(new ImageIcon("resources/useb.png"));
		} else {
			use2.setIcon(new ImageIcon("resources/usebHover.png"));
		}
		if (game.getCurrentChampion().getAbilities().get(2).getCurrentCooldown() == 0) {
			use3.setIcon(new ImageIcon("resources/useb.png"));
		} else {
			use3.setIcon(new ImageIcon("resources/usebHover.png"));
		}
	}

	public void setAbilityInfo(JLabel a, int i) {
		String result = "<html>" + game.getCurrentChampion().getAbilities().get(i).getCastArea() + "<br/>"
				+ "Cast Range: " + game.getCurrentChampion().getAbilities().get(i).getCastRange() + "<br/>" + "Mana: "
				+ game.getCurrentChampion().getAbilities().get(i).getManaCost() + "<br/>" + "Action pts: "
				+ game.getCurrentChampion().getAbilities().get(i).getRequiredActionPoints() + "<br/>"
				+ "Base Cool Down: " + game.getCurrentChampion().getAbilities().get(i).getBaseCooldown() + "<br/>"
				+ "Current Cool Down: " + game.getCurrentChampion().getAbilities().get(i).getCurrentCooldown()
				+ "<br/>";
		if (game.getCurrentChampion().getAbilities().get(i) instanceof HealingAbility) {
			result += "Heal Amount: "
					+ ((HealingAbility) game.getCurrentChampion().getAbilities().get(i)).getHealAmount() + "<br/>"
					+ "</html>";
		} else if (game.getCurrentChampion().getAbilities().get(i) instanceof DamagingAbility) {
			result += "Damage Amount: "
					+ ((DamagingAbility) game.getCurrentChampion().getAbilities().get(i)).getDamageAmount() + "<br/>"
					+ "</html>";
		} else if (game.getCurrentChampion().getAbilities().get(i) instanceof CrowdControlAbility) {
			result += "Effect: "
					+ ((CrowdControlAbility) game.getCurrentChampion().getAbilities().get(i)).getEffect().getName()
					+ "<br/>" + "Duration: "
					+ ((CrowdControlAbility) game.getCurrentChampion().getAbilities().get(i)).getEffect().getDuration()
					+ "<br/>" + "</html>";
		}
		a.setText(result);
	}

	public void setPic(JLabel a, int i) {
		if (game.getCurrentChampion().getAbilities().get(i) instanceof HealingAbility) {
			a.setIcon(new ImageIcon("resources/healingStone.png"));
		} else if (game.getCurrentChampion().getAbilities().get(i) instanceof DamagingAbility) {
			a.setIcon(new ImageIcon("resources/damagingStone.png"));
		} else if (game.getCurrentChampion().getAbilities().get(i) instanceof CrowdControlAbility) {
			a.setIcon(new ImageIcon("resources/ccStone.png"));
		}
	}

	private ImageIcon getImage(Champion c) {
		if (c.getName().equals("Captain America"))
			return new ImageIcon("resources/ca.png");
		if (c.getName().equals("Deadpool"))
			return (new ImageIcon("resources/deadpool.png"));
		if (c.getName().equals("Dr Strange"))
			return (new ImageIcon("resources/drStrange.png"));
		if (c.getName().equals("Electro"))
			return (new ImageIcon("resources/electro.png"));
		if (c.getName().equals("Ghost Rider"))
			return (new ImageIcon("resources/ghostRider.png"));
		if (c.getName().equals("Hela"))
			return (new ImageIcon("resources/hela.png"));
		if (c.getName().equals("Hulk"))
			return (new ImageIcon("resources/hulk.png"));
		if (c.getName().equals("Iceman"))
			return (new ImageIcon("resources/iceman.png"));
		if (c.getName().equals("Ironman"))
			return (new ImageIcon("resources/ironman.png"));
		if (c.getName().equals("Loki"))
			return (new ImageIcon("resources/loki.png"));
		if (c.getName().equals("Quicksilver"))
			return (new ImageIcon("resources/quicksilver.png"));
		if (c.getName().equals("Spiderman"))
			return (new ImageIcon("resources/spiderman.png"));
		if (c.getName().equals("Thor"))
			return (new ImageIcon("resources/thor.png"));
		if (c.getName().equals("Venom"))
			return (new ImageIcon("resources/venom.png"));
		if (c.getName().equals("Yellow Jacket"))
			return (new ImageIcon("resources/yellowJacket.png"));
		return (new ImageIcon("resources/nothing.png"));
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
		info.setText(getEffects());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		info.setText(getChampText());
	}

	public String getEffects() {
		if (game.getCurrentChampion().getAppliedEffects().size() != 0) {
			String s = "<html>";
			for (Effect e : game.getCurrentChampion().getAppliedEffects()) {
				s = s + e.getName() + ": " + e.getDuration() + "<br/>";
			}
			s = s + "</html>";
			return s;
		} else {
			return "No effects";
		}

	}

	public String getChampText() {
		String result = "";
		if (game.getCurrentChampion() instanceof Hero) {
			result += "<html>" + game.getCurrentChampion().getName() + "<br/>" + "Type: " + "Hero" + "<br/>";
		} else if (game.getCurrentChampion() instanceof Villain) {
			result += "<html>" + game.getCurrentChampion().getName() + "<br/>" + "Type: " + "Villain" + "<br/>";
		} else {
			result += "<html>" + game.getCurrentChampion().getName() + "<br/>" + "Type: " + "AntiHero" + "<br/>";
		}
		return result + "HP: " + game.getCurrentChampion().getCurrentHP() + "<br/>" + "Mana: "
				+ game.getCurrentChampion().getMana() + "<br/>" + "Speed: " + game.getCurrentChampion().getSpeed()
				+ "<br/>" + "Current Action pts: " + game.getCurrentChampion().getCurrentActionPoints() + "<br/>"
				+ "Attack damage: " + game.getCurrentChampion().getAttackDamage() + "<br/>" + "Attack range: "
				+ game.getCurrentChampion().getAttackRange() + "<br/>" + "</html>";
	}

}
