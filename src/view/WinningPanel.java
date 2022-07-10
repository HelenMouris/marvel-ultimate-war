package view;

import java.awt.Color;
import java.awt.Font;
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

import engine.Game;
import engine.Player;

public class WinningPanel extends JPanel implements ActionListener, MouseListener {
	private Player winner;
	private MainFrame frame;
	private Game game;
	private JButton exit;

	public WinningPanel(MainFrame frame, Game game) {
		super();
		this.frame = frame;
		this.game = game;
		this.winner = game.checkGameOver();
		
		setBackground(Color.BLACK);
		setOpaque(true);
		setLayout(null);
		
		JLabel text = new JLabel();
		text.setText("<html>" + "Congratulations " + winner.getName() + "," + "You won the war!"  + "</html>");
		text.setBounds(400,30,850,70);
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Arial", Font.BOLD, 35));
		
		JLabel pic = new JLabel();
		pic.setIcon(new ImageIcon("resources/winBack.jpg"));
		pic.setBounds(350,150,850,500);
		pic.setForeground(Color.WHITE);
		pic.setFont(new Font("Arial", Font.BOLD, 35));
		
		exit = new JButton();
		exit.setIcon(new ImageIcon("resources/exitb.png"));
		exit.setBounds(630,695,230,80);
		exit.setFont(new Font("Arial", Font.BOLD, 30));
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.setFocusPainted(false);
		exit.setForeground(Color.white);
		exit.addActionListener(this);
		exit.addMouseListener(this);
		
		
		add(text);
		add(pic);
		add(exit);
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
		exit.setIcon(new ImageIcon("resources/exitbHover.png"));
		try {	File file = new File("resources/sounds/hover.wav");
		Clip clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(file));
		clip.start();
		return;
		} catch(Exception e1) {
		System.err.println(e1.getMessage());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		exit.setIcon(new ImageIcon("resources/exitb.png"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}
	
}
