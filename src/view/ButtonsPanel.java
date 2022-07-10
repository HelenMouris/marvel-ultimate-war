package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import buttons.Attack;
import buttons.EndTurn;
import buttons.LeaderAbility;
import buttons.Move;
import controller.Control;
import engine.Game;
import model.world.Champion;
import model.world.Condition;

public class ButtonsPanel extends JPanel {
	private JButton move;
	private JButton attack;
	private JButton leaderAbility;
	private JButton endTurn;
	public static Pq1 turnOrder1;
	public static Pq2 turnOrder2;
	
	public ButtonsPanel(MainFrame frame, Game game, Control control) {
		super();
		setLayout(new GridLayout(3,2,5,5));
		setSize(350,187);
		setVisible(true);
		setOpaque(false);
		
		move = new Move(control);
		attack = new Attack(control);
		endTurn = new EndTurn(control);
		leaderAbility = new LeaderAbility(control);
		
		turnOrder1 = new Pq1(game, frame);
		turnOrder2 = new Pq2(game, frame);
		
		
		add(move);
		add(attack);
		add(endTurn);
		add(leaderAbility);
		add(turnOrder1);
		add(turnOrder2);
	}	
	
}
