package controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import buttons.BoardButton;
import buttons.DirectionButton;
import engine.Game;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.AntiHero;
import model.world.Damageable;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;
import view.BackgroundPanel;
import view.BoardPanel;
import view.ButtonsPanel;
import view.CurrentChampionPanel;
import view.ErrorFrame;
import view.GamePanel;
import view.MainFrame;
import view.Player1Panel;
import view.Player2Panel;
import view.Team1Members;
import view.Team2Members;
import view.WinningPanel;

public class Control implements ActionListener {
	private GamePanel gamePanel;
	private BoardPanel boardPanel;
	private CurrentChampionPanel currentChampPanel;
	private Player1Panel player1Panel;
	private Player2Panel player2Panel;
	private MainFrame frame;
	private Game game;
	private Direction currentDirection;
	private Damageable currentTarget;
	private Team1Members team1;
	private Team2Members team2;

	public Control(MainFrame frame, Game game) {
		super();
		this.game = game;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof DirectionButton)
			currentDirection = ((DirectionButton) e.getSource()).getD();
		if (e.getSource() instanceof BoardButton)
			currentTarget = (Damageable) game.getBoard()[((BoardButton) e.getSource())
					.getI()][((BoardButton) e.getSource()).getJ()];
		if (e.getActionCommand().equals("move")) {
			try {
				if (currentDirection != null) {
					game.move(currentDirection);
					moveSound();
				} else {
					ErrorFrame error = new ErrorFrame("Please choose a direction first then press move");
				}

			} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
				ErrorFrame error = new ErrorFrame(e1.getMessage());
			} finally {
				currentDirection = null;
				currentTarget = null;
			}
		} else if (e.getActionCommand().equals("attack")) {
			try {
				if (currentDirection != null) {
					game.attack(currentDirection);
					attackSound();
				} else {
					ErrorFrame error = new ErrorFrame("Please choose a direction first then press attack");
				}
			} catch (NotEnoughResourcesException | ChampionDisarmedException e1) {
				ErrorFrame error = new ErrorFrame(e1.getMessage());
			} finally {
				currentDirection = null;
				currentTarget = null;
			}
		} else if (e.getActionCommand().equals("leaderAbility")) {
			try {
				game.useLeaderAbility();
				if (game.getCurrentChampion() instanceof Hero) {
					healSound();
				} else if (game.getCurrentChampion() instanceof Villain) {
					damageSound();
				}
				if (game.getCurrentChampion() instanceof AntiHero) {
					glowSound();
				}
			} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e1) {
				ErrorFrame error = new ErrorFrame(e1.getMessage());
			} finally {
				currentDirection = null;
				currentTarget = null;
			}
		} else if (e.getActionCommand().equals("endTurn")) {
			try {
				game.endTurn();
				hoverSound();
				// update turn order
				ButtonsPanel.turnOrder1.updatepq();
				ButtonsPanel.turnOrder1.revalidate();
				ButtonsPanel.turnOrder1.repaint();

				ButtonsPanel.turnOrder2.updatepq();
				ButtonsPanel.turnOrder2.revalidate();
				ButtonsPanel.turnOrder2.repaint();
			} catch (Exception e1) {
				ErrorFrame error = new ErrorFrame(e1.getMessage());
			} finally {
				currentDirection = null;
				currentTarget = null;
			}
		} else if (e.getActionCommand().equals("a1") || e.getActionCommand().equals("a2")
				|| e.getActionCommand().equals("a3") || e.getActionCommand().equals("a4")) {
			Ability a = game.getCurrentChampion().getAbilities().get(0);
			if (e.getActionCommand().equals("a1"))
				a = game.getCurrentChampion().getAbilities().get(0);
			else if (e.getActionCommand().equals("a2"))
				a = game.getCurrentChampion().getAbilities().get(1);
			else if (e.getActionCommand().equals("a3"))
				a = game.getCurrentChampion().getAbilities().get(2);
			else if (e.getActionCommand().equals("a4"))
				a = game.getCurrentChampion().getAbilities().get(3);
			if (a.getCastArea() == AreaOfEffect.DIRECTIONAL) {
				try {
					if (currentDirection != null) {
						game.castAbility(a, currentDirection);
						if (a instanceof HealingAbility) {
							healSound();
						} else if (a instanceof DamagingAbility) {
							damageSound();
						} else if (a instanceof CrowdControlAbility) {
							glowSound();
						}
					} else {
						ErrorFrame error = new ErrorFrame("Please choose a direction first then press use");
					}
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					ErrorFrame error = new ErrorFrame(e1.getMessage());
				} finally {
					currentDirection = null;
					currentTarget = null;
				}
			} else if (a.getCastArea() == AreaOfEffect.SINGLETARGET) {
				try {
					if (currentTarget != null) {
						game.castAbility(a, currentTarget.getLocation().x, currentTarget.getLocation().y);
						if (a instanceof HealingAbility) {
							healSound();
						} else if (a instanceof DamagingAbility) {
							damageSound();
						} else if (a instanceof CrowdControlAbility) {
							glowSound();
						}
					} else {
						ErrorFrame error = new ErrorFrame("Please select a target on the board first then press use");
					}
				} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
						| CloneNotSupportedException e1) {
					ErrorFrame error = new ErrorFrame(e1.getMessage());
				} finally {
					currentDirection = null;
					currentTarget = null;
				}
			} else {
				try {
					game.castAbility(a);
					if (a instanceof HealingAbility) {
						healSound();
					} else if (a instanceof DamagingAbility) {
						damageSound();
					} else if (a instanceof CrowdControlAbility) {
						glowSound();
					}
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					ErrorFrame error = new ErrorFrame(e1.getMessage());
				} finally {
					currentDirection = null;
					currentTarget = null;
				}
			}
		}

		// update board
		GamePanel.boardPanel.removeAll();
		for (int i = 4; i >= 0; i--)
			for (int j = 0; j < 5; j++) {
				BoardButton button = new BoardButton(game, i, j);
				button.addActionListener(this);
				button.setContentAreaFilled(false);
				button.setOpaque(false);
				GamePanel.boardPanel.add(button);
			}
		GamePanel.boardPanel.revalidate();
		GamePanel.boardPanel.repaint();

		// update Current Champion
		GamePanel.currentChampionPanel.updateInfo();
		GamePanel.currentChampionPanel.revalidate();
		GamePanel.currentChampionPanel.repaint();

		// update leader ability status
		GamePanel.player1Panel.updateTilte();
		GamePanel.player2Panel.updateTilte();

		// update players teams info
		Player1Panel.team1.updateTeam();
		Player1Panel.team1.revalidate();
		Player1Panel.team1.repaint();

		Player2Panel.team2.updateTeam();
		Player2Panel.team2.revalidate();
		Player2Panel.team2.repaint();

		if (game.checkGameOver() != null) {
			WinningPanel winningPanel = new WinningPanel(frame, game);

			frame.getContentPane().add(winningPanel);
			((CardLayout) frame.getContentPane().getLayout()).next(frame.getContentPane());

		}

	}

	public void moveSound() {
		File file = null;
		file = new File("resources/sounds/move.wav");
		Clip c = null;

		try {
			c = AudioSystem.getClip();
		} catch (Exception e) {
		}

		try {
			c.open(AudioSystem.getAudioInputStream(file));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException | NullPointerException w) {
			System.out.println(w.getMessage());
		}
		c.start();

	}

	public void attackSound() {
		File file = null;
		file = new File("resources/sounds/attack.wav");
		Clip c = null;

		try {
			c = AudioSystem.getClip();
		} catch (Exception e) {
		}

		try {
			c.open(AudioSystem.getAudioInputStream(file));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException | NullPointerException w) {
			System.out.println(w.getMessage());
		}
		c.start();

	}

	public void damageSound() {
		File file = null;
		file = new File("resources/sounds/damage.wav");
		Clip c = null;

		try {
			c = AudioSystem.getClip();
		} catch (Exception e) {
		}

		try {
			c.open(AudioSystem.getAudioInputStream(file));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException | NullPointerException w) {
			System.out.println(w.getMessage());
		}
		c.start();

	}

	public void glowSound() {
		File file = null;
		file = new File("resources/sounds/glow.wav");
		Clip c = null;

		try {
			c = AudioSystem.getClip();
		} catch (Exception e) {
		}

		try {
			c.open(AudioSystem.getAudioInputStream(file));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException | NullPointerException w) {
			System.out.println(w.getMessage());
		}
		c.start();

	}

	public void healSound() {
		File file = null;
		file = new File("resources/sounds/heal.wav");
		Clip c = null;

		try {
			c = AudioSystem.getClip();
		} catch (Exception e) {
		}

		try {
			c.open(AudioSystem.getAudioInputStream(file));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException | NullPointerException w) {
			System.out.println(w.getMessage());
		}
		c.start();

	}

	public void hoverSound() {
		File file = null;
		file = new File("resources/sounds/hover.wav");
		Clip c = null;

		try {
			c = AudioSystem.getClip();
		} catch (Exception e) {
		}

		try {
			c.open(AudioSystem.getAudioInputStream(file));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException | NullPointerException w) {
			System.out.println(w.getMessage());
		}
		c.start();

	}

}
