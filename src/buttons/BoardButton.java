package buttons;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import engine.Game;
import model.world.Champion;
import model.world.Cover;

public class BoardButton extends JButton {
	private Game game;
	private int i;
	private int j;

	public BoardButton(Game game, int i, int j ) {
		this.game = game;
		this.i = i;
		this.j = j;
		
		if (game.getBoard()[i][j]  != null) {
			if (game.getBoard()[i][j] instanceof Cover ) {
				setIcon(new ImageIcon("resources/cover.png"));
				setToolTipText("<html>"+"Current HP: " + ((Cover) game.getBoard()[i][j]).getCurrentHP() +"</html>");
			}
			else if (game.getBoard()[i][j] instanceof Champion ) {
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Captain America"))
					setIcon(new ImageIcon("resources/ca.png"));
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Deadpool"))
					setIcon(new ImageIcon("resources/deadpool.png"));
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Dr Strange"))
					setIcon(new ImageIcon("resources/drStrange.png"));
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Electro"))
					setIcon(new ImageIcon("resources/electro.png"));
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Ghost Rider"))
					setIcon(new ImageIcon("resources/ghostRider.png"));
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Hela"))
					setIcon(new ImageIcon("resources/hela.png"));
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Hulk"))
					setIcon(new ImageIcon("resources/hulk.png"));
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Iceman"))
					setIcon(new ImageIcon("resources/iceman.png"));
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Ironman"))
					setIcon(new ImageIcon("resources/ironman.png"));
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Loki"))
					setIcon(new ImageIcon("resources/loki.png"));
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Quicksilver"))
					setIcon(new ImageIcon("resources/quicksilver.png"));
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Spiderman"))
					setIcon(new ImageIcon("resources/spiderman.png"));
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Thor"))
					setIcon(new ImageIcon("resources/thor.png"));
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Venom"))
					setIcon(new ImageIcon("resources/venom.png"));
				if (( (Champion) game.getBoard()[i][j]).getName().equals("Yellow Jacket"))
					setIcon(new ImageIcon("resources/yellowJacket.png"));
				if (game.getFirstPlayer().getTeam().contains(( (Champion) game.getBoard()[i][j]))) {
					setBorder(new LineBorder(Color.RED));
				}
				if (game.getSecondPlayer().getTeam().contains(( (Champion) game.getBoard()[i][j]))) {
					setBorder(new LineBorder(Color.BLUE));
				}	
			}
		}
		
		
	}

	public Game getGame() {
		return game;
	}
	
	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

}
