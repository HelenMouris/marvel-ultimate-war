package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import buttons.HowToPlay;

public class StartPanel extends JPanel implements ActionListener, MouseListener {
	private JButton startButton;
	private JButton howToPlay;
	private MainFrame frame;
	public static Clip clip;

	public StartPanel(MainFrame frame) {
		super();
		this.frame = frame;
		setLayout(null);
		startGameMusic();

		startButton = new JButton("START");
		startButton.setBounds(620, 550, 230, 70);
		startButton.setFont(new Font("Arial", Font.BOLD, 40));
		startButton.setOpaque(false);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.setFocusPainted(false);
		startButton.setForeground(Color.white);
		startButton.addActionListener(this);
		startButton.addMouseListener(this);

		howToPlay = new JButton("How to Play");
		howToPlay.setBounds(580, 620, 320, 70);
		howToPlay.setFont(new Font("Arial", Font.BOLD, 40));
		howToPlay.setOpaque(false);
		howToPlay.setContentAreaFilled(false);
		howToPlay.setBorderPainted(false);
		howToPlay.setFocusPainted(false);
		howToPlay.setForeground(Color.white);
		howToPlay.addActionListener(this);

		HowToPlay l = new HowToPlay(howToPlay);
		howToPlay.addMouseListener(l);

		add(startButton);
		add(howToPlay);

		setOpaque(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("START")) {
			frame.dispose();
			frame.setBack(new BackgroundPanel(new ImageIcon("resources/formBack.jpg").getImage().getScaledInstance(1500,
					825, Image.SCALE_DEFAULT)));
			frame.setContentPane(frame.getBack());
			frame.getContentPane().setLayout(new CardLayout());
			frame.getContentPane().add(new FormPanel(this.frame));
			frame.setVisible(true);
		} else if (e.getActionCommand().equals("How to Play")) {
			HowToPlayFrame howtoPlay = new HowToPlayFrame();
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
		startButton.setFont(new Font("Arial", Font.BOLD, 50));
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
		startButton.setFont(new Font("Arial", Font.BOLD, 40));
	}

	public static void startGameMusic() {

		try {
			File file = new File("resources/sounds/game.wav");
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
			return;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
