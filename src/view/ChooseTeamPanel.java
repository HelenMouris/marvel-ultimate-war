package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import model.world.Champion;

public class ChooseTeamPanel extends JPanel implements ActionListener, MouseListener {
	private JLabel title;
	private AvailableChampions availableChampions;
	private JButton nextButton;
	private MainFrame frame;

	public ChooseTeamPanel(MainFrame frame) {
		super();
		this.frame = frame;
		setLayout(null);

		title = new JLabel(FormPanel.p1.getName() + ", please choose your first champion");
		title.setBounds(400, 45, 850, 70);
		title.setForeground(Color.white);
		title.setFont(new Font("Arial", Font.BOLD, 33));

		availableChampions = new AvailableChampions(frame, this);

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
		add(availableChampions);
		setOpaque(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.getContentPane().add(new ChooseLeaderPanel(frame));
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

	public JLabel getTitle() {
		return title;
	}

	public JButton getNextButton() {
		return nextButton;
	}

}
