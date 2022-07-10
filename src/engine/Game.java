package engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
import model.effects.Disarm;
import model.effects.Dodge;
import model.effects.Effect;
import model.effects.EffectType;
import model.effects.Embrace;
import model.effects.PowerUp;
import model.effects.Root;
import model.effects.Shield;
import model.effects.Shock;
import model.effects.Silence;
import model.effects.SpeedUp;
import model.effects.Stun;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Condition;
import model.world.Cover;
import model.world.Damageable;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

public class Game {
	private static ArrayList<Champion> availableChampions = new ArrayList<Champion>();
	private static ArrayList<Ability> availableAbilities = new ArrayList<Ability>();
	private Player firstPlayer;
	private Player secondPlayer;
	private Object[][] board;
	private PriorityQueue turnOrder;
	private boolean firstLeaderAbilityUsed;
	private boolean secondLeaderAbilityUsed;
	private final static int BOARDWIDTH = 5;
	private final static int BOARDHEIGHT = 5;

	public Game(Player first, Player second) {
		firstPlayer = first;
		secondPlayer = second;

		board = new Object[BOARDWIDTH][BOARDHEIGHT];
		turnOrder = new PriorityQueue(6);
		placeChampions();
		placeCovers();
		prepareChampionTurns();
	}

	public static void loadAbilities(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Ability a = null;
			AreaOfEffect ar = null;
			switch (content[5]) {
			case "SINGLETARGET":
				ar = AreaOfEffect.SINGLETARGET;
				break;
			case "TEAMTARGET":
				ar = AreaOfEffect.TEAMTARGET;
				break;
			case "SURROUND":
				ar = AreaOfEffect.SURROUND;
				break;
			case "DIRECTIONAL":
				ar = AreaOfEffect.DIRECTIONAL;
				break;
			case "SELFTARGET":
				ar = AreaOfEffect.SELFTARGET;
				break;

			}
			Effect e = null;
			if (content[0].equals("CC")) {
				switch (content[7]) {
				case "Disarm":
					e = new Disarm(Integer.parseInt(content[8]));
					break;
				case "Dodge":
					e = new Dodge(Integer.parseInt(content[8]));
					break;
				case "Embrace":
					e = new Embrace(Integer.parseInt(content[8]));
					break;
				case "PowerUp":
					e = new PowerUp(Integer.parseInt(content[8]));
					break;
				case "Root":
					e = new Root(Integer.parseInt(content[8]));
					break;
				case "Shield":
					e = new Shield(Integer.parseInt(content[8]));
					break;
				case "Shock":
					e = new Shock(Integer.parseInt(content[8]));
					break;
				case "Silence":
					e = new Silence(Integer.parseInt(content[8]));
					break;
				case "SpeedUp":
					e = new SpeedUp(Integer.parseInt(content[8]));
					break;
				case "Stun":
					e = new Stun(Integer.parseInt(content[8]));
					break;
				}
			}
			switch (content[0]) {
			case "CC":
				a = new CrowdControlAbility(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3]), ar, Integer.parseInt(content[6]), e);
				break;
			case "DMG":
				a = new DamagingAbility(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3]), ar, Integer.parseInt(content[6]), Integer.parseInt(content[7]));
				break;
			case "HEL":
				a = new HealingAbility(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3]), ar, Integer.parseInt(content[6]), Integer.parseInt(content[7]));
				break;
			}
			availableAbilities.add(a);
			line = br.readLine();
		}
		br.close();
	}

	public static void loadChampions(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Champion c = null;
			switch (content[0]) {
			case "A":
				c = new AntiHero(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[3]),
						Integer.parseInt(content[4]), Integer.parseInt(content[5]), Integer.parseInt(content[6]),
						Integer.parseInt(content[7]));
				break;

			case "H":
				c = new Hero(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[3]),
						Integer.parseInt(content[4]), Integer.parseInt(content[5]), Integer.parseInt(content[6]),
						Integer.parseInt(content[7]));
				break;
			case "V":
				c = new Villain(content[1], Integer.parseInt(content[2]), Integer.parseInt(content[3]),
						Integer.parseInt(content[4]), Integer.parseInt(content[5]), Integer.parseInt(content[6]),
						Integer.parseInt(content[7]));
				break;
			}

			c.getAbilities().add(findAbilityByName(content[8]));
			c.getAbilities().add(findAbilityByName(content[9]));
			c.getAbilities().add(findAbilityByName(content[10]));
			availableChampions.add(c);
			line = br.readLine();
		}
		br.close();
	}

	private static Ability findAbilityByName(String name) {
		for (Ability a : availableAbilities) {
			if (a.getName().equals(name))
				return a;
		}
		return null;
	}

	public void placeCovers() {
		int i = 0;
		while (i < 5) {
			int x = ((int) (Math.random() * (BOARDWIDTH - 2))) + 1;
			int y = (int) (Math.random() * BOARDHEIGHT);

			if (board[x][y] == null) {
				board[x][y] = new Cover(x, y);
				i++;
			}
		}

	}

	public void placeChampions() {
		int i = 1;
		for (Champion c : firstPlayer.getTeam()) {
			board[0][i] = c;
			c.setLocation(new Point(0, i));
			i++;
		}
		i = 1;
		for (Champion c : secondPlayer.getTeam()) {
			board[BOARDHEIGHT - 1][i] = c;
			c.setLocation(new Point(BOARDHEIGHT - 1, i));
			i++;
		}

	}

	public static ArrayList<Champion> getAvailableChampions() {
		return availableChampions;
	}

	public static ArrayList<Ability> getAvailableAbilities() {
		return availableAbilities;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public Object[][] getBoard() {
		return board;
	}

	public PriorityQueue getTurnOrder() {
		return turnOrder;
	}

	public boolean isFirstLeaderAbilityUsed() {
		return firstLeaderAbilityUsed;
	}

	public boolean isSecondLeaderAbilityUsed() {
		return secondLeaderAbilityUsed;
	}

	public static int getBoardwidth() {
		return BOARDWIDTH;
	}

	public static int getBoardheight() {
		return BOARDHEIGHT;
	}

	public Champion getCurrentChampion() {
		return (Champion) turnOrder.peekMin();
	}

	public Player checkGameOver() {
		if (firstPlayer.getTeam().isEmpty()) {
			return secondPlayer;
		}
		if (secondPlayer.getTeam().isEmpty()) {
			return firstPlayer;
		}
		return null;
	}
	
	public void move(Direction d) throws NotEnoughResourcesException, UnallowedMovementException {
		if (getCurrentChampion().getCurrentActionPoints() < 1) {
			throw new NotEnoughResourcesException("Not enough Action points available");
		}
		if (getCurrentChampion().getCondition() == Condition.ROOTED) {
			throw new UnallowedMovementException("Champion is Rooted");
		}
		int currentX = getCurrentChampion().getLocation().x;
		int currentY = getCurrentChampion().getLocation().y;
		int newX = currentX;
		int newY = currentY;
		if (d == Direction.UP) {
			newX++;
		} else if (d == Direction.DOWN) {
			newX--;
		} else if (d == Direction.LEFT) {
			newY--;
		} else if (d == Direction.RIGHT) {
			newY++;
		}

		if (newX < 0 || newX > BOARDHEIGHT-1 || newY < 0 || newY > BOARDWIDTH-1) {
			throw new UnallowedMovementException("Unallowed Movement");
		}
		if (board[newX][newY] != null) {
			throw new UnallowedMovementException("Unallowed Movement");
		}
		board[newX][newY] = getCurrentChampion();
		board[currentX][currentY] = null;
		getCurrentChampion().setLocation(new Point(newX, newY));
		getCurrentChampion().setCurrentActionPoints((int) (getCurrentChampion().getCurrentActionPoints() - 1));

	}

	public void attack(Direction d) throws NotEnoughResourcesException, ChampionDisarmedException {
		for (Effect e : getCurrentChampion().getAppliedEffects()) {
			if (e.getName().equals("Disarm")) {
				throw new ChampionDisarmedException("Champion is disarmed");
			}
		}
		if (getCurrentChampion().getCurrentActionPoints() < 2) {
			throw new NotEnoughResourcesException("Not enough Action points available");
		}
		
		Object target = null;
		boolean outOfRange = false;
		int x = getCurrentChampion().getLocation().x;
		int y = getCurrentChampion().getLocation().y;
		while (true) {
			if (d == Direction.UP) {
				x++;
			} else if (d == Direction.DOWN) {
				x--;
			} else if (d == Direction.LEFT) {
				y--;
			} else if (d == Direction.RIGHT) {
				y++;
			}
			if (Math.abs(getCurrentChampion().getLocation().x - x) > getCurrentChampion().getAttackRange()
					|| Math.abs(getCurrentChampion().getLocation().y - y) > getCurrentChampion().getAttackRange()) {
				outOfRange = true;
				break;
			}
			if (x < 0 || x > BOARDHEIGHT-1 || y < 0 || y > BOARDWIDTH-1) {
				outOfRange = true;
				break;
			} else if (board[x][y] != null) {
				if (board[x][y] instanceof Cover) {
					target = board[x][y];
					break;
				} else if (board[x][y] instanceof Champion) {
					if (!isFriend((Champion) board[x][y])) {
						target = board[x][y];
						break;
					}
				}
			}
		}
		if (!outOfRange) {
			if (target instanceof Cover) {
				((Cover) target).setCurrentHP(((Cover) target).getCurrentHP() - getCurrentChampion().getAttackDamage());
				if (((Cover) target).getCurrentHP() == 0) {
					board[x][y] = null;
				}
			}
			if (target instanceof Champion) {
				boolean isDodge = false;
				boolean isShield = false;
				if (!isFriend((Champion) target)) {
					ArrayList<Effect> effectsToRemove = new ArrayList<Effect>();
					for (Effect e : ((Champion) target).getAppliedEffects()) {
						if (e instanceof Dodge) {
							isDodge = true;
						} else if (e instanceof Shield) {
							isShield = true;
							effectsToRemove.add(e);
							break;
						}
					}
					for (Effect e : effectsToRemove) {
						((Champion) target).getAppliedEffects().remove(e);
						e.remove((Champion) target);
					}
					if (isDodge && !isShield) {
						int random = (int) Math.round(Math.random());
						if (random == 1) {
							applyAttack((Champion) target);
						}
					} else if (!isDodge && !isShield) {
						applyAttack((Champion) target);
					}
					// check if dead
					if (((Champion) target).getCurrentHP() == 0) {
						((Champion) target).setCondition(Condition.KNOCKEDOUT);
						board[x][y] = null;
					}
				}
			}
		}
		getCurrentChampion().setCurrentActionPoints((int) (getCurrentChampion().getCurrentActionPoints() - 2));
		removeDead();
	}

	public void castAbility(Ability a) throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException {
		if (getCurrentChampion().getCurrentActionPoints() < a.getRequiredActionPoints()) {
			throw new NotEnoughResourcesException("Not enough Action points available");
		}
		if (getCurrentChampion().getMana() < a.getManaCost()) {
			throw new NotEnoughResourcesException("Not enough Mana available");
		}
		if (a.getCurrentCooldown() != 0) {
			throw new AbilityUseException("Must cool down before using the ability again");
		}
		for (Effect e: getCurrentChampion().getAppliedEffects()) {
			if (e instanceof Silence) {
				throw new AbilityUseException("Champion is silenced");
			}
		}
		// getting valid targets
		ArrayList<Damageable> targets = new ArrayList<Damageable>();
		if (a.getCastArea() == AreaOfEffect.SELFTARGET) {
			targets.add(getCurrentChampion());
		} else if (a.getCastArea() == AreaOfEffect.TEAMTARGET) {
			if (a instanceof HealingAbility) {
				for (Champion c : getCurrentPlayer().getTeam()) {
					if (checkRange(c,a)) {
						targets.add(c);
					}
				}
			} else if (a instanceof DamagingAbility) {
				for (Champion c : getOpponentPlayer().getTeam()) {
					if (checkRange(c,a)) {
						if (!checkEffect(c)) {
							targets.add(c);
						}
					}
				}
			} else if (a instanceof CrowdControlAbility) {
				if (((CrowdControlAbility) a).getEffect().getType() == EffectType.BUFF) {
					for (Champion c : getCurrentPlayer().getTeam()) {
						if (checkRange(c,a)) {
							targets.add(c);
						}
					}
				} else {
					for (Champion c : getOpponentPlayer().getTeam()) {
						if (checkRange(c,a)) {
							targets.add(c);
						}
					}
				}
			}
		} else if (a.getCastArea() == AreaOfEffect.SURROUND) {
			int x = getCurrentChampion().getLocation().x;
			int y = getCurrentChampion().getLocation().y;
			
			if ((x + 1) < BOARDHEIGHT) {
				if ((y - 1) >= 0) {
					checkCell(x + 1, y - 1, targets, a);
				}
				if ((y + 1) < BOARDWIDTH) {
					checkCell(x + 1, y + 1, targets, a);
				}
				checkCell(x + 1, y, targets, a);
			}
			if ((x - 1) >= 0) {
				if ((y - 1) >= 0) {
					checkCell(x - 1, y - 1, targets, a);
				}
				if ((y + 1) < BOARDWIDTH) {
					checkCell(x - 1, y + 1, targets, a);
				}
				checkCell(x - 1, y, targets, a);
			}
			if ((y - 1) >= 0) {
				checkCell(x, y - 1, targets, a);
			}
			if ((y + 1) < BOARDWIDTH) {
				checkCell(x, y + 1, targets, a);
			}
		}
		
		a.execute(targets);
		removeCovers(targets);
		removeDead();
		getCurrentChampion().setMana(getCurrentChampion().getMana() - a.getManaCost());
		getCurrentChampion().setCurrentActionPoints(getCurrentChampion().getCurrentActionPoints() - a.getRequiredActionPoints());
		a.setCurrentCooldown(a.getBaseCooldown());
	}

	public void castAbility(Ability a, Direction d) throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException {
		if (getCurrentChampion().getCurrentActionPoints() < a.getRequiredActionPoints()) {
			throw new NotEnoughResourcesException("Not enough Action points available");
		}
		if (getCurrentChampion().getMana() < a.getManaCost()) {
			throw new NotEnoughResourcesException("Not enough Mana available");
		}
		if (a.getCurrentCooldown() != 0) {
			throw new AbilityUseException("Must cool down before using the ability again");
		}
		for (Effect e: getCurrentChampion().getAppliedEffects()) {
			if (e instanceof Silence) {
				throw new AbilityUseException("Champion is silenced");
			}
		}
		
		ArrayList<Damageable> targets = new ArrayList<Damageable>();
		boolean outOfRange = false;
		int x = getCurrentChampion().getLocation().x;
		int y = getCurrentChampion().getLocation().y;
		while (!outOfRange) {
			if (d == Direction.UP) {
				x++;
			} else if (d == Direction.DOWN) {
				x--;
			} else if (d == Direction.LEFT) {
				y--;
			} else if (d == Direction.RIGHT) {
				y++;
			}
			if (x < 0 || x > BOARDHEIGHT-1 || y < 0 || y > BOARDWIDTH-1 ) {
				outOfRange = true;
				break;
			} else if (board[x][y] != null) {
				if (a instanceof HealingAbility) {
					if (board[x][y] instanceof Champion && isFriend((Champion) board[x][y])) {
						if (checkRange((Champion) board[x][y],a)) {
							targets.add((Champion) board[x][y]);
						}
					}
				} else if (a instanceof DamagingAbility) {
					if (board[x][y] instanceof Cover) {
						if (checkRange((Cover) board[x][y],a)) {
							targets.add((Cover) board[x][y]);
						}
					}
					if (board[x][y] instanceof Champion && !isFriend((Champion) board[x][y])) {
						if (checkRange((Champion) board[x][y],a)) {
							if (!checkEffect((Champion) board[x][y])) {
								targets.add((Champion) board[x][y]);
							}
						}
					}
				} else if (a instanceof CrowdControlAbility) {
					if (((CrowdControlAbility) a).getEffect().getType() == EffectType.BUFF) {
						if (board[x][y] instanceof Champion && isFriend((Champion) board[x][y])) {
							if (checkRange((Champion) board[x][y],a)) {
								targets.add((Champion) board[x][y]);
							}
						}
					} else {
						if (board[x][y] instanceof Champion && !isFriend((Champion) board[x][y])) {
							if (checkRange((Champion) board[x][y],a)) {
								targets.add((Champion) board[x][y]);
							}
						}
					}
				}
			}
		}

		a.execute(targets);
		removeCovers(targets);
		removeDead();
		getCurrentChampion().setMana(getCurrentChampion().getMana() - a.getManaCost());
		getCurrentChampion().setCurrentActionPoints(getCurrentChampion().getCurrentActionPoints() - a.getRequiredActionPoints());
		a.setCurrentCooldown(a.getBaseCooldown());
	}

	public void castAbility(Ability a, int x, int y) throws NotEnoughResourcesException, AbilityUseException, InvalidTargetException, CloneNotSupportedException {
		if (getCurrentChampion().getCurrentActionPoints() < a.getRequiredActionPoints()) {
			throw new NotEnoughResourcesException("Not enough Action points available");
		}
		if (getCurrentChampion().getMana() < a.getManaCost()) {
			throw new NotEnoughResourcesException("Not enough Mana available");
		}
		if (a.getCurrentCooldown() != 0) {
			throw new AbilityUseException("Must cool down before using the ability again");
		}
		for (Effect e: getCurrentChampion().getAppliedEffects()) {
			if (e instanceof Silence) {
				throw new AbilityUseException("Champion is silenced");
			}
		}
		if (board[x][y] == null) {
			throw new InvalidTargetException("Can't use ability on an empty cell");
		}
		if (!checkRange((Damageable) board[x][y], a)) {
			throw new AbilityUseException("target not is cast range");
		}
		if (a instanceof HealingAbility || a instanceof CrowdControlAbility) {
			if (board[x][y] instanceof Cover) {
				throw new InvalidTargetException("Can't use this ability on a cover");
			}
		}
		if (a instanceof HealingAbility && board[x][y] instanceof Champion) {
			if (!isFriend((Champion) board[x][y])) {
				throw new InvalidTargetException("Can't use this ability on an enemy");
			}
		}
		if (a instanceof DamagingAbility && board[x][y] instanceof Champion) {
			if (isFriend((Champion) board[x][y])) {
				throw new InvalidTargetException("Can't use this ability on a friend");
			}
		}
		if (a instanceof CrowdControlAbility && board[x][y] instanceof Champion) {
			if (((CrowdControlAbility) a).getEffect().getType() == EffectType.BUFF && !isFriend((Champion) board[x][y])) {
				throw new InvalidTargetException("Can't use this ability on an enemy");
			}
			if (((CrowdControlAbility) a).getEffect().getType() == EffectType.DEBUFF && isFriend((Champion) board[x][y])) {
				throw new InvalidTargetException("Can't use this ability on a friend");
			}
		}
		ArrayList<Damageable> targets = new ArrayList<Damageable>();
		if (board[x][y] instanceof Cover) {
			targets.add((Cover) board[x][y]);
		} else if (board[x][y] instanceof Champion) {
			if (a instanceof DamagingAbility) {
				if (!checkEffect((Champion) board[x][y])) {
					targets.add((Champion) board[x][y]);
				}
			} else {
				targets.add((Champion) board[x][y]);
			}
			
		}
		
		a.execute(targets);
		removeCovers(targets);
		removeDead();
		getCurrentChampion().setMana(getCurrentChampion().getMana() - a.getManaCost());
		getCurrentChampion().setCurrentActionPoints(getCurrentChampion().getCurrentActionPoints() - a.getRequiredActionPoints());
		a.setCurrentCooldown(a.getBaseCooldown());
	}

	public void useLeaderAbility() throws LeaderAbilityAlreadyUsedException, LeaderNotCurrentException {
		if (getCurrentChampion() != getCurrentPlayer().getLeader()) {
			throw new LeaderNotCurrentException("Current champion is not a leader");
		}
		if (getCurrentPlayer() == firstPlayer) {
			if (firstLeaderAbilityUsed)
				throw new LeaderAbilityAlreadyUsedException("Leader ability is already used");
		} else {
			if (secondLeaderAbilityUsed)
				throw new LeaderAbilityAlreadyUsedException("Leader ability is already used");
		}
		ArrayList<Champion> targets = new ArrayList<Champion>();
		if (getCurrentChampion() instanceof Hero) {
			targets = getCurrentPlayer().getTeam();
		} else if (getCurrentChampion() instanceof Villain) {
			targets = getOpponentPlayer().getTeam();
		} else {
			for (Champion c : firstPlayer.getTeam()) {
				if (c != firstPlayer.getLeader())
					targets.add(c);
			}
			for (Champion c : secondPlayer.getTeam()) {
				if (c != secondPlayer.getLeader())
					targets.add(c);
			}
		}
		getCurrentChampion().useLeaderAbility(targets);
		for (Champion c : targets) {
			if (c.getCondition() == Condition.KNOCKEDOUT || c.getCurrentHP() == 0) {
				board[c.getLocation().x][c.getLocation().y] = null;
			}
		}
		if (getCurrentPlayer() == firstPlayer) {
			firstLeaderAbilityUsed = true;
		} else {
			secondLeaderAbilityUsed = true;
		}
		removeDead();
	}

	public void endTurn() {
		turnOrder.remove();
		if (turnOrder.isEmpty()) {
			prepareChampionTurns();
			updateTimers();
			getCurrentChampion().setCurrentActionPoints(getCurrentChampion().getMaxActionPointsPerTurn());
		} else if (getCurrentChampion().getCondition() == Condition.INACTIVE) {
			updateTimers();
			getCurrentChampion().setCurrentActionPoints(getCurrentChampion().getMaxActionPointsPerTurn());
			endTurn();
		} else if (getCurrentChampion().getCondition() == Condition.KNOCKEDOUT) {
			endTurn();
		} else {
			updateTimers();
			getCurrentChampion().setCurrentActionPoints(getCurrentChampion().getMaxActionPointsPerTurn());
		}
	}

	private void prepareChampionTurns() {
		for (Champion c : firstPlayer.getTeam()) {
			if (c.getCondition() != Condition.KNOCKEDOUT) {
				turnOrder.insert(c);
			}
		}
		for (Champion c : secondPlayer.getTeam()) {
			if (c.getCondition() != Condition.KNOCKEDOUT) {
				turnOrder.insert(c);
			}
		}
	}

	// helpers

	public Player getCurrentPlayer() {
		for (Champion c : firstPlayer.getTeam()) {
			if (c == getCurrentChampion()) {
				return firstPlayer;
			}
		}
		return secondPlayer;
	}

	public Player getOpponentPlayer() {
		for (Champion c : firstPlayer.getTeam()) {
			if (c == getCurrentChampion()) {
				return secondPlayer;
			}
		}
		return firstPlayer;
	}

	public boolean isFriend(Champion c) {
		for (Champion t : getCurrentPlayer().getTeam()) {
			if (t == c) {
				return true;
			}
		}
		return false;
	}

	public void applyAttack(Champion target) {
		int damage;
		if ((target instanceof Hero && getCurrentChampion() instanceof Villain)
				|| (target instanceof Villain && getCurrentChampion() instanceof Hero)
				|| (target instanceof Villain && getCurrentChampion() instanceof AntiHero)
				|| (target instanceof Hero && getCurrentChampion() instanceof AntiHero)
				|| (target instanceof AntiHero && getCurrentChampion() instanceof Hero)
				|| (target instanceof AntiHero && getCurrentChampion() instanceof Villain)) {
			damage = (int) (1.5 * getCurrentChampion().getAttackDamage());
		} else {
			damage = getCurrentChampion().getAttackDamage();
		}
		target.setCurrentHP(target.getCurrentHP() - damage);
	}
	
	public boolean checkRange(Damageable c, Ability a) {
		if (Math.abs(getCurrentChampion().getLocation().x - c.getLocation().x)
				+ Math.abs(getCurrentChampion().getLocation().y - c.getLocation().y) > a.getCastRange()) {
			return false;
		}
		return true;
	}
	
	public boolean checkEffect(Champion c) {
		ArrayList<Effect> effectsToRemove = new ArrayList<Effect>();
		for (Effect e : c.getAppliedEffects()) {
			if (e instanceof Shield) {
				effectsToRemove.add(e);
				break;
			}
		}
		for (Effect e : effectsToRemove) {
			c.getAppliedEffects().remove(e);
			e.remove(c);
			return true;
		}
		return false;
	}

	public void checkCell(int x, int y, ArrayList<Damageable> targets, Ability a) {
		if (board[x][y] != null) {
			if (board[x][y] instanceof Cover) {
				if (a instanceof DamagingAbility) {
					targets.add((Cover) board[x][y]);
				}
			} else if (board[x][y] instanceof Champion) {
				if (a instanceof HealingAbility) {
					if (isFriend((Champion) board[x][y])) {
						targets.add((Champion) board[x][y]);
					}
				} else if (a instanceof DamagingAbility) {
					if (!isFriend((Champion) board[x][y])) {
						if (!checkEffect((Champion) board[x][y])) {
							targets.add((Champion) board[x][y]);
						}
					}
				} else if (a instanceof CrowdControlAbility) {
					if (((CrowdControlAbility) a).getEffect().getType() == EffectType.BUFF) {
						if (isFriend((Champion) board[x][y])) {
							targets.add((Champion) board[x][y]);
						}
					} else {
						if (!isFriend((Champion) board[x][y])) {
							targets.add((Champion) board[x][y]);
						}
					}
				}
			}
		}
	}
	
	public void removeDead() {
		ArrayList<Champion> champsToRemove1 = new ArrayList<Champion>();
		for (Champion c : firstPlayer.getTeam()) {
			if (c.getCurrentHP() == 0 || c.getCondition() == Condition.KNOCKEDOUT) {
				c.setCondition(Condition.KNOCKEDOUT);
				board[c.getLocation().x][c.getLocation().y] = null;
				champsToRemove1.add(c);
			}
		}
		for (Champion c : champsToRemove1) {
			firstPlayer.getTeam().remove(c);
		}
		
		ArrayList<Champion> champsToRemove2 = new ArrayList<Champion>();
		for (Champion c : secondPlayer.getTeam()) {
			if (c.getCurrentHP() == 0 || c.getCondition() == Condition.KNOCKEDOUT) {
				c.setCondition(Condition.KNOCKEDOUT);
				board[c.getLocation().x][c.getLocation().y] = null;
				champsToRemove2.add(c);
			}
		}
		for (Champion c : champsToRemove2) {
			secondPlayer.getTeam().remove(c);
		}
		
		//to remove from queue
		ArrayList<Champion> temp = new ArrayList<Champion>();
		while (!turnOrder.isEmpty()) {
			Champion c = (Champion) turnOrder.remove();
			if (c.getCurrentHP() != 0 || c.getCondition() != Condition.KNOCKEDOUT) {
				temp.add(c);
			}
		}
		for (Champion c: temp) {
			turnOrder.insert(c);
		}
	}
	
	public void removeCovers(ArrayList<Damageable> targets) {
		for (Damageable target : targets) {
			if (target instanceof Cover) {
				if (target.getCurrentHP() == 0) {
					board[target.getLocation().x][target.getLocation().y] = null;
				}
			}
		}
	}

	public void updateTimers() {
		ArrayList<Effect> effectsToRemove = new ArrayList<Effect>();
		for (Effect e : getCurrentChampion().getAppliedEffects()) {
			e.setDuration(e.getDuration() - 1);
			if (e.getDuration() <= 0 ) {
				effectsToRemove.add(e);
			}
		}
		for (Effect e : effectsToRemove) {
			getCurrentChampion().getAppliedEffects().remove(e);
			e.remove(getCurrentChampion());
		}
		for (Ability a : getCurrentChampion().getAbilities()) {
			a.setCurrentCooldown(a.getCurrentCooldown() - 1);
		}
	}

}
