package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import model.world.Champion;

public class AvailableChampions extends JPanel implements ActionListener, MouseListener {
	private ChooseTeamPanel chooseTeamPanel;
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private int count = 1;
	private JPanel grid;
	private JPanel selected;
	private JButton add;
	private JLabel selectedInfo;
	private JLabel selectedPic;
	private Champion selectedChamp;
	private JLabel team1;
	private JLabel team2;
	private JLabel c1;
	private JLabel c2;
	private JLabel c3;
	private JLabel c4;
	private JLabel c5;
	private JLabel c6;

	public AvailableChampions(MainFrame frame, ChooseTeamPanel chooseTeamPanel) {
		this.chooseTeamPanel = chooseTeamPanel;

		selectedChamp = Game.getAvailableChampions().get(0);

		setBounds(160, 140, 1200, 640);
		setLayout(null);
		setOpaque(false);
		setVisible(true);

		// GRID

		grid = new JPanel();
		grid.setLayout(new GridLayout(3, 5, 5, 5));
		grid.setBounds(0, 0, 690, 500);
		grid.setVisible(true);
		grid.setOpaque(false);

		for (Champion c : Game.getAvailableChampions()) {

			JButton b = new JButton();
			setCard(b, c);
			b.setSize(50, 100);
			b.setForeground(Color.WHITE);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.setFocusPainted(false);
			b.setOpaque(false);
			b.addActionListener(this);
			buttons.add(b);
			grid.add(b);
		}

		// selected champ

		selected = new JPanel();
		selected.setLayout(null);
		selected.setBounds(700, 0, 500, 500);
		selected.setVisible(true);
		selected.setOpaque(false);

		selectedInfo = new JLabel();
		selectedInfo.setText(("<html>" + selectedChamp.getName() + "<br/>" + "HP: " + selectedChamp.getCurrentHP()
				+ "<br/>" + "Mana: " + selectedChamp.getMana() + "<br/>" + "Speed: " + selectedChamp.getSpeed()
				+ "<br/>" + "Max Action pts: " + selectedChamp.getMaxActionPointsPerTurn() + "<br/>" + "Attack damage: "
				+ selectedChamp.getAttackDamage() + "<br/>" + "Attack range: " + selectedChamp.getAttackRange()
				+ "<br/>" + "<br/>" + "Ability 1: " + selectedChamp.getAbilities().get(0).getName() + "<br/>" + "<br/>"
				+ "Ability 2: " + selectedChamp.getAbilities().get(1).getName() + "<br/>" + "<br/>" + "Ability 3: "
				+ selectedChamp.getAbilities().get(2).getName() + "<br/>" + "<br/>" + "</html>"));
		selectedInfo.setBounds(40, 0, 200, 430);
		selectedInfo.setForeground(Color.white);
		selectedInfo.setOpaque(false);
		selectedInfo.setFont(new Font("Arial", Font.BOLD, 20));

		selectedPic = new JLabel();
		selectedPic.setIcon(new ImageIcon("resources/caLarge.png"));
		selectedPic.setBounds(240, 0, 300, 430);
		selectedPic.setForeground(Color.white);
		selectedPic.setOpaque(false);
		selectedPic.setFont(new Font("Arial", Font.BOLD, 20));

		add = new JButton();
		add.setName("add");
		add.setIcon(new ImageIcon("resources/addb.png"));
		add.setBounds(170, 440, 155, 55);
		add.setOpaque(false);
		add.setContentAreaFilled(false);
		add.setBorderPainted(false);
		add.setFocusPainted(false);
		add.addActionListener(this);
		add.addMouseListener(this);

		selected.add(add);
		selected.add(selectedInfo);
		selected.add(selectedPic);

		// team 1
		team1 = new JLabel();
		team1.setLayout(new FlowLayout(FlowLayout.LEFT));
		team1.setBounds(160, 560, 250, 70);
		team1.setVisible(true);
		team1.setOpaque(false);

		c1 = new JLabel();
		c1.setVisible(true);
		c1.setOpaque(false);

		c2 = new JLabel();
		c2.setVisible(true);
		c2.setOpaque(false);

		c3 = new JLabel();
		c3.setVisible(true);
		c3.setOpaque(false);

		team1.add(c1);
		team1.add(c2);
		team1.add(c3);

		// team 2
		team2 = new JLabel();
		team2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		team2.setBounds(750, 560, 250, 70);
		team2.setVisible(true);
		team2.setOpaque(false);

		c4 = new JLabel();
		c4.setVisible(true);
		c4.setOpaque(false);

		c5 = new JLabel();
		c5.setVisible(true);
		c5.setOpaque(false);

		c6 = new JLabel();
		c6.setVisible(true);
		c6.setOpaque(false);

		team2.add(c4);
		team2.add(c5);
		team2.add(c6);

		add(grid);
		add(selected);
		add(team1);
		add(team2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Champion c : Game.getAvailableChampions()) {
			if (c.getName().equals(((Component) e.getSource()).getName())) {
				selectedChamp = c;
			}
		}
		if (!(((Component) e.getSource()).getName()).equals("add")) {
			selectedPic.setIcon(getPic());
			selectedInfo.setText(("<html>" + selectedChamp.getName() + "<br/>" + "HP: " + selectedChamp.getCurrentHP()
					+ "<br/>" + "Mana: " + selectedChamp.getMana() + "<br/>" + "Speed: " + selectedChamp.getSpeed()
					+ "<br/>" + "Max Action pts: " + selectedChamp.getMaxActionPointsPerTurn() + "<br/>"
					+ "Attack damage: " + selectedChamp.getAttackDamage() + "<br/>" + "Attack range: "
					+ selectedChamp.getAttackRange() + "<br/>" + "<br/>" + "Ability 1: "
					+ selectedChamp.getAbilities().get(0).getName() + "<br/>" + "<br/>" + "Ability 2: "
					+ selectedChamp.getAbilities().get(1).getName() + "<br/>" + "<br/>" + "Ability 3: "
					+ selectedChamp.getAbilities().get(2).getName() + "<br/>" + "<br/>" + "</html>"));
			add.setEnabled(true);
		} else {
			if (count == 1 || count == 3 || count == 5) {
				FormPanel.p1.getTeam().add(selectedChamp);
				if (count == 1) {
					c1.setIcon(getSmallPic(selectedChamp));
					chooseTeamPanel.getTitle().setText(FormPanel.p2.getName() + ", please choose your first champion");
				} else if (count == 3) {
					c2.setIcon(getSmallPic(selectedChamp));
					chooseTeamPanel.getTitle().setText(FormPanel.p2.getName() + ", please choose your second champion");
				} else if (count == 5) {
					c3.setIcon(getSmallPic(selectedChamp));
					chooseTeamPanel.getTitle().setText(FormPanel.p2.getName() + ", please choose your third champion");
				}
				count++;
				add.setEnabled(false);
			} else if (count == 2 || count == 4 || count == 6) {
				FormPanel.p2.getTeam().add(selectedChamp);
				if (count == 2) {
					c4.setIcon(getSmallPic(selectedChamp));
					chooseTeamPanel.getTitle().setText(FormPanel.p1.getName() + ", please choose your second champion");
				} else if (count == 4) {
					c5.setIcon(getSmallPic(selectedChamp));
					chooseTeamPanel.getTitle().setText(FormPanel.p1.getName() + ", please choose your third champion");
				} else if (count == 6) {
					c6.setIcon(getSmallPic(selectedChamp));
					chooseTeamPanel.getTitle().setText("Press Next to choose your leaders");
				}
				count++;
				add.setEnabled(false);
			}
			if (count == 7) {
				chooseTeamPanel.add(chooseTeamPanel.getNextButton());
				chooseTeamPanel.validate();
				chooseTeamPanel.repaint();
			} else {
				for (JButton b : buttons) {
					if (b.getName().equals(selectedChamp.getName())) {
						b.setEnabled(false);
					}
				}
			}
			cardSound();
		}

	}

	public ImageIcon getPic() {
		switch (selectedChamp.getName()) {
		case "Captain America":
			return new ImageIcon("resources/caLarge.png");
		case "Deadpool":
			return new ImageIcon("resources/deadpoolLarge.png");
		case "Dr Strange":
			return new ImageIcon("resources/drStrangeLarge.png");
		case "Electro":
			return new ImageIcon("resources/electroLarge.png");
		case "Ghost Rider":
			return new ImageIcon("resources/ghostriderLarge.png");
		case "Hela":
			return new ImageIcon("resources/helaLarge.png");
		case "Hulk":
			return new ImageIcon("resources/hulkLarge.png");
		case "Iceman":
			return new ImageIcon("resources/icemanLarge.png");
		case "Ironman":
			return new ImageIcon("resources/ironmanLarge.png");
		case "Loki":
			return new ImageIcon("resources/lokiLarge.png");
		case "Quicksilver":
			return new ImageIcon("resources/quicksilverLarge.png");
		case "Spiderman":
			return new ImageIcon("resources/spidermanLarge.png");
		case "Thor":
			return new ImageIcon("resources/thorLarge.png");
		case "Venom":
			return new ImageIcon("resources/venomLarge.png");
		case "Yellow Jacket":
			return new ImageIcon("resources/yellowJacketLarge.png");
		default:
			return new ImageIcon("resources/caLarge.png");
		}
	}

	public static ImageIcon getSmallPic(Champion c) {
		switch (c.getName()) {
		case "Captain America":
			return new ImageIcon("resources/cap.png");
		case "Deadpool":
			return new ImageIcon("resources/deadpoolp.png");
		case "Dr Strange":
			return new ImageIcon("resources/drStrangep.png");
		case "Electro":
			return new ImageIcon("resources/electrop.png");
		case "Ghost Rider":
			return new ImageIcon("resources/ghostRiderp.png");
		case "Hela":
			return new ImageIcon("resources/helap.png");
		case "Hulk":
			return new ImageIcon("resources/hulkp.png");
		case "Iceman":
			return new ImageIcon("resources/icemanp.png");
		case "Ironman":
			return new ImageIcon("resources/ironmanp.png");
		case "Loki":
			return new ImageIcon("resources/lokip.png");
		case "Quicksilver":
			return new ImageIcon("resources/quicksilverp.png");
		case "Spiderman":
			return new ImageIcon("resources/spidermanp.png");
		case "Thor":
			return new ImageIcon("resources/thorp.png");
		case "Venom":
			return new ImageIcon("resources/venomp.png");
		case "Yellow Jacket":
			return new ImageIcon("resources/yellowJacketp.png");
		default:
			return new ImageIcon("resources/cap.png");
		}
	}

	public static void setCard(JButton b, Champion c) {
		switch (c.getName()) {
		case "Captain America":
			b.setName("Captain America");
			b.setIcon(new ImageIcon("resources/caCard.png"));
			break;
		case "Deadpool":
			b.setName("Deadpool");
			b.setIcon(new ImageIcon("resources/deadpoolCard.png"));
			break;
		case "Dr Strange":
			b.setName("Dr Strange");
			b.setIcon(new ImageIcon("resources/drStrangeCard.png"));
			break;
		case "Electro":
			b.setName("Electro");
			b.setIcon(new ImageIcon("resources/electroCard.png"));
			break;
		case "Ghost Rider":
			b.setName("Ghost Rider");
			b.setIcon(new ImageIcon("resources/ghostRiderCard.png"));
			break;
		case "Hela":
			b.setName("Hela");
			b.setIcon(new ImageIcon("resources/helaCard.png"));
			break;
		case "Hulk":
			b.setName("Hulk");
			b.setIcon(new ImageIcon("resources/hulkCard.png"));
			break;
		case "Iceman":
			b.setName("Iceman");
			b.setIcon(new ImageIcon("resources/icemanCard.png"));
			break;
		case "Ironman":
			b.setName("Ironman");
			b.setIcon(new ImageIcon("resources/ironmanCard.png"));
			break;
		case "Loki":
			b.setName("Loki");
			b.setIcon(new ImageIcon("resources/lokiCard.png"));
			break;
		case "Quicksilver":
			b.setName("Quicksilver");
			b.setIcon(new ImageIcon("resources/quicksilverCard.png"));
			break;
		case "Spiderman":
			b.setName("Spiderman");
			b.setIcon(new ImageIcon("resources/spidermanCard.png"));
			break;
		case "Thor":
			b.setName("Thor");
			b.setIcon(new ImageIcon("resources/thorCard.png"));
			break;
		case "Venom":
			b.setName("Venom");
			b.setIcon(new ImageIcon("resources/venomCard.png"));
			break;
		case "Yellow Jacket":
			b.setName("Yellow Jacket");
			b.setIcon(new ImageIcon("resources/yellowJacketCard.png"));
			break;

		}
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
		add.setIcon(new ImageIcon("resources/addbHover.png"));
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
		add.setIcon(new ImageIcon("resources/addb.png"));
	}

	public void cardSound() {
		File file = null;
		file = new File("resources/sounds/card.wav");
		Clip c = null;

		try {
			c = AudioSystem.getClip();
		} catch (Exception e) {
		}

		try {
			c.open(AudioSystem.getAudioInputStream(file));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException | NullPointerException w) {
			System.out.println(w.getMessage());
		}
		c.start();

	}

}
