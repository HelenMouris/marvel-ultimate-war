package model.effects;

import model.world.Champion;
import model.world.Condition;

public class Stun extends Effect {

	public Stun(int duration) {
		super("Stun", duration, EffectType.DEBUFF);
	}

	public void apply(Champion c) {
		c.setCondition(Condition.INACTIVE);
	}

	public void remove(Champion c) {
		boolean isStun = false;
		boolean isRoot = false;
		for (Effect e: c.getAppliedEffects()) {
			if (e instanceof Stun) {
				isStun = true;
			} else if ( e instanceof Root) {
				isRoot = true;
			}
		}
		
		if (isStun) {
			c.setCondition(Condition.INACTIVE);
		}
		if (!isStun && isRoot) {
			c.setCondition(Condition.ROOTED);
		}
		if (!isStun && !isRoot) {
			c.setCondition(Condition.ACTIVE);
		}

	}

}
