package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import buttons.BoardButton;
import controller.Control;
import engine.Game;

public class BoardPanel extends JPanel {
	private Game game;
	private MainFrame frame;
	private Control control;

	public BoardPanel(MainFrame frame, Game game, Control control) {
		super();
		this.frame = frame;
		this.game = game;
		this.control = control;
		setLayout(new GridLayout(5,5,5,5));
		setVisible(true);
		setOpaque(false);
		
		for(int i=4;i>=0;i--)
			for(int j=0;j<5;j++) {
				BoardButton button = new BoardButton(game, i,j);
				button.addActionListener(control);
				button.setContentAreaFilled(false);
				button.setOpaque(false);
				add(button);
			}
	}
}
