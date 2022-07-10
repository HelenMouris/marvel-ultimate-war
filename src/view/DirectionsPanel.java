package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import buttons.Attack;
import buttons.DirectionButton;
import buttons.Down;
import buttons.Left;
import buttons.Right;
import buttons.Up;
import controller.Control;
import engine.Game;
import model.world.Direction;

public class DirectionsPanel extends JPanel {
	private DirectionButton up;
	private DirectionButton down;
	private DirectionButton left;
	private DirectionButton right;
	private JLabel upLeft;
	private JLabel upRight;
	private JLabel center;
	private JLabel downLeft;
	private JLabel downRight;
	private Up upListener;
	private Down downListener;
	private Left leftListener;
	private Right rightListener;

	public DirectionsPanel(MainFrame frame, Game game, Control control) {
		super();
		setLayout(new GridLayout(3, 3, 5, 5));
		setSize(336, 187);
		setVisible(true);
		setOpaque(false);

		up = new DirectionButton(Direction.UP, new ImageIcon("resources/upb.png"), control);
		this.upListener = new Up(up);
		up.addMouseListener(upListener);

		down = new DirectionButton(Direction.DOWN, new ImageIcon("resources/downb.png"), control);
		this.downListener = new Down(down);
		down.addMouseListener(downListener);

		left = new DirectionButton(Direction.LEFT, new ImageIcon("resources/leftb.png"), control);
		this.leftListener = new Left(left);
		left.addMouseListener(leftListener);

		right = new DirectionButton(Direction.RIGHT, new ImageIcon("resources/rightb.png"), control);
		this.rightListener = new Right(right);
		right.addMouseListener(rightListener);

		upLeft = new JLabel();
		upLeft.setSize(180, 140);
		upLeft.setVisible(true);
		upLeft.setOpaque(false);

		upRight = new JLabel();
		upRight.setSize(180, 140);
		upRight.setVisible(true);
		upRight.setOpaque(false);

		center = new JLabel();
		center.setSize(180, 140);
		center.setVisible(true);
		center.setOpaque(false);

		downLeft = new JLabel();
		downLeft.setSize(180, 140);
		downLeft.setVisible(true);
		downLeft.setOpaque(false);

		downRight = new JLabel();
		downRight.setSize(180, 140);
		downRight.setVisible(true);
		downRight.setOpaque(false);

		add(upLeft);
		add(up);
		add(upRight);
		add(left);
		add(center);
		add(right);
		add(downLeft);
		add(down);
		add(downRight);

	}

}
