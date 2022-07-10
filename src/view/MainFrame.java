package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import engine.Game;

public class MainFrame extends JFrame {
	private BackgroundPanel back;
	private StartPanel start;

	public MainFrame() {
		setSize(1500, 825);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Marvel Ultimate War");

		ImageIcon image = new ImageIcon("resources/logo.png");
		setIconImage(image.getImage());
		start = new StartPanel(this);
		back = new BackgroundPanel(new ImageIcon("resources/back1.jpg").getImage().getScaledInstance(1500, 825, Image.SCALE_DEFAULT));
		setContentPane(back);
		getContentPane().setLayout(new CardLayout());
		getContentPane().add(start);

		setVisible(true);
	}

	public static void main(String[] args) {
		startMusic();
		Intro1GIF intro = new Intro1GIF();
		Intro2GIF intro2 = new Intro2GIF();
		MainFrame main = new MainFrame();
		intro.setVisible(false);
		intro2.setVisible(false);
		try {
			Game.loadAbilities("Abilities.csv");
		} catch (IOException e) {
			System.out.println("file not found");
		}
		try {
			Game.loadChampions("Champions.csv");
		} catch (IOException e) {
			System.out.println("file not found");
		}

	}

	public BackgroundPanel getBack() {
		return back;
	}

	public void setBack(BackgroundPanel back) {
		this.back = back;
	}

	public StartPanel getStart() {
		return start;
	}

	private static void startMusic() {

		try {
			File file = new File("resources/sounds/intro.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
			return;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
