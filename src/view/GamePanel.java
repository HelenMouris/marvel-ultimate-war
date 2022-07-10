package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.Control;
import engine.Game;

public class GamePanel extends JPanel {
	public static CurrentChampionPanel currentChampionPanel;
	public static DirectionsPanel directionsPanel;
	public static ButtonsPanel buttonsPanel;
	public static Player1Panel player1Panel;
	public static Player2Panel player2Panel;
	public static BoardPanel boardPanel;

	public GamePanel(MainFrame frame, Game game, Control control) {
		super();
		setLayout(null);
		setOpaque(false);
		
		//upper section
		player1Panel = new Player1Panel(frame, game, control);
		player1Panel.setBounds(0, 0,500,600);
		player1Panel.setVisible(true);
		
		boardPanel = new BoardPanel(frame, game, control);
		boardPanel.setBounds(500, 5,500,595);
		boardPanel.setVisible(true);
		
		player2Panel = new Player2Panel(frame, game, control);
		player2Panel.setBounds(1000, 0,500,600);
		player2Panel.setVisible(true);
		

		//lower section
		currentChampionPanel = new CurrentChampionPanel(frame, game, control);
		currentChampionPanel.setBounds(0, 605,845,182);
		currentChampionPanel.setVisible(true);
		
		buttonsPanel = new ButtonsPanel(frame, game, control);
		buttonsPanel.setBounds(850, 600,350,187);
		buttonsPanel.setVisible(true);
		
		directionsPanel = new DirectionsPanel(frame, game, control);
		directionsPanel.setBounds(1240, 600,220,187);
		directionsPanel.setVisible(true);
		
		add(player1Panel);
		add(player2Panel);
		add(boardPanel);
		add(currentChampionPanel);
		add(buttonsPanel);
		add(directionsPanel);
			
	}
	
}
