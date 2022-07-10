package view;

import java.awt.CardLayout;
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
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import engine.Player;

public class FormPanel extends JPanel implements ActionListener, MouseListener {
	private JLabel title;
	private JLabel player1Label;
	private JTextField player1Field;
	private JLabel player2Label;
	private JTextField player2Field;
	private JButton nextButton;
	private MainFrame frame;
	public static Player p1;
	public static Player p2;

	public FormPanel(MainFrame frame) {
		super();
		this.frame = frame;
		setLayout(null);
		setOpaque(false);

		title = new JLabel("Please enter your names");
		title.setBounds(425, 45, 850, 70);
		title.setForeground(Color.white);
		title.setFont(new Font("Arial", Font.BOLD, 55));

		player1Label = new JLabel("Player 1");
		player1Label.setBounds(450, 200, 200, 70);
		player1Label.setForeground(Color.white);
		player1Label.setFont(new Font("Arial", Font.BOLD, 40));

		player1Field = new JTextField();
		player1Field.setBounds(450, 270, 600, 50);
		player1Field.setFont(new Font("Arial", Font.BOLD, 40));
		player1Field.setForeground(Color.RED);
		player1Field.setBorder(new LineBorder(Color.RED));

		player2Label = new JLabel("Player 2");
		player2Label.setBounds(450, 400, 200, 70);
		player2Label.setForeground(Color.white);
		player2Label.setFont(new Font("Arial", Font.BOLD, 40));

		player2Field = new JTextField();
		player2Field.setBounds(450, 470, 600, 50);
		player2Field.setFont(new Font("Arial", Font.BOLD, 40));
		player2Field.setForeground(Color.RED);
		player2Field.setBorder(new LineBorder(Color.RED));

		nextButton = new JButton();
		nextButton.setIcon(new ImageIcon("resources/nextb.png"));
		nextButton.setBounds(670, 700, 155, 55);
		nextButton.setOpaque(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setFocusPainted(false);
		nextButton.addActionListener(this);
		nextButton.addMouseListener(this);

		add(title);
		add(player1Label);
		add(player1Field);
		add(player2Label);
		add(player2Field);
		add(nextButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String player1Name = player1Field.getText();
		String player2Name = player2Field.getText();

		p1 = new Player(player1Name);
		p2 = new Player(player2Name);

		frame.getContentPane().add(new ChooseTeamPanel(frame));
		((CardLayout) frame.getContentPane().getLayout()).next(frame.getContentPane());

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
		nextButton.setIcon(new ImageIcon("resources/nextbHover.png"));
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
		nextButton.setIcon(new ImageIcon("resources/nextb.png"));
	}

}
