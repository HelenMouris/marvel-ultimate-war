package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import controller.Control;
import engine.Game;
import model.world.Champion;

public class ChooseLeaderPanel extends JPanel implements ActionListener, MouseListener {
	private MainFrame frame;
	private JLabel title;
	private JLabel player1;
	private JLabel player2;
	private JButton startGame;
	private JPanel team1;
	private JPanel team2;
	private Game game;
	private Control control;

	public ChooseLeaderPanel(MainFrame frame) {
		super();
		this.frame = frame;
		setLayout(null);
		setOpaque(false);

		title = new JLabel("Please choose your leaders");
		title.setBounds(460, 45, 850, 70);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial", Font.BOLD, 45));

		player1 = new JLabel(FormPanel.p1.getName());
		player1.setBounds(700, 160, frame.getWidth(), 50);
		player1.setForeground(Color.WHITE);
		player1.setFont(new Font("Arial", Font.BOLD, 30));

		team1 = new JPanel();
		team1.setLayout(new GridLayout(1, 3, 15, 100));
		team1.setBounds(550, 220, 400, 150);
		team1.setVisible(true);
		team1.setOpaque(false);
		for (Champion c : FormPanel.p1.getTeam()) {
			JButton b = new JButton();
			AvailableChampions.setCard(b, c);
			b.setSize(160, 200);
			b.setForeground(Color.WHITE);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.setFocusPainted(false);
			b.setOpaque(false);
			b.addActionListener(this);
			b.setName(c.getName());
			team1.add(b);
		}

		player2 = new JLabel(FormPanel.p2.getName());
		player2.setBounds(700, 430, frame.getWidth(), 50);
		player2.setForeground(Color.WHITE);
		player2.setFont(new Font("Arial", Font.BOLD, 30));

		team2 = new JPanel();
		team2.setLayout(new GridLayout(1, 3, 15, 100));
		team2.setBounds(550, 490, 400, 150);
		team2.setVisible(true);
		team2.setOpaque(false);
		for (Champion c : FormPanel.p2.getTeam()) {
			JButton b = new JButton();
			AvailableChampions.setCard(b, c);
			b.setSize(160, 200);
			b.setForeground(Color.WHITE);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.setFocusPainted(false);
			b.setOpaque(false);
			b.addActionListener(this);
			b.setName(c.getName());
			team2.add(b);
		}

		startGame = new JButton();
		startGame.setIcon(new ImageIcon("resources/startGameb.png"));
		startGame.setBounds(630, 695, 230, 60);
		startGame.setFont(new Font("Arial", Font.BOLD, 30));
		startGame.setOpaque(false);
		startGame.setContentAreaFilled(false);
		startGame.setBorderPainted(false);
		startGame.setFocusPainted(false);
		startGame.setForeground(Color.white);
		startGame.addActionListener(this);
		startGame.addMouseListener(this);

		add(title);
		add(player1);
		add(team1);
		add(player2);
		add(team2);
		add(startGame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startGame) {

			game = new Game(FormPanel.p1, FormPanel.p2);
			control = new Control(frame, game);

			frame.setBack(new BackgroundPanel(new ImageIcon("resources/background.jpg").getImage()
					.getScaledInstance(1500, 825, Image.SCALE_DEFAULT)));
			frame.setContentPane(frame.getBack());
			frame.getContentPane().setLayout(new CardLayout());
			frame.getContentPane().add(new GamePanel(frame, game, control));
			((CardLayout) frame.getContentPane().getLayout()).next(frame.getContentPane());
		} else {
			for (Champion c : FormPanel.p1.getTeam()) {
				if (c.getName() == ((Component) e.getSource()).getName()) {
					FormPanel.p1.setLeader(c);
					cardSound();
				}
			}
			for (Champion c : FormPanel.p2.getTeam()) {
				if (c.getName() == ((Component) e.getSource()).getName()) {
					FormPanel.p2.setLeader(c);
					cardSound();
				}
			}
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
		startGame.setIcon(new ImageIcon("resources/startGamebHover.png"));
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
		startGame.setIcon(new ImageIcon("resources/startGameb.png"));
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
