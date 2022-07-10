package model.world;

import java.util.ArrayList;

import model.effects.Effect;
import model.effects.EffectType;
import model.effects.Embrace;

public class Hero extends Champion {

	public Hero(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);

	}

	public void useLeaderAbility(ArrayList<Champion> targets) {
		for (Champion target: targets) {
			ArrayList<Effect> effectsToRemove = new ArrayList<Effect>();
			for (Effect e : target.getAppliedEffects()) {
				if (e.getType() == EffectType.DEBUFF) {
					effectsToRemove.add(e);
				}
			}
			for (Effect e : effectsToRemove) {
				target.getAppliedEffects().remove(e);
				e.remove(target);
			}
			Embrace embrace = new Embrace(2);
			target.getAppliedEffects().add(embrace);
			embrace.apply(target);
		}
	}

}
