package model.effects;

import model.world.Champion;
import model.world.Condition;

public class Root extends Effect {

	public Root(int duration) {
		super("Root", duration, EffectType.DEBUFF);

	}

	public void apply(Champion c) {
		if (c.getCondition() == Condition.ACTIVE) {
			c.setCondition(Condition.ROOTED);
		}		
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
		
		if (isStun || c.getCondition() == Condition.INACTIVE) {
			c.setCondition(Condition.INACTIVE);
		} else if (!isStun && isRoot && c.getCondition() != Condition.INACTIVE) {
			c.setCondition(Condition.ROOTED);
		} else if (!isStun && !isRoot && c.getCondition() != Condition.INACTIVE) {
			c.setCondition(Condition.ACTIVE);
		}
	}
}
