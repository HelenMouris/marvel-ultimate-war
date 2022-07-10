package buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import engine.Game;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;

public class CustomButton extends JButton implements MouseListener {
	
	public CustomButton() {
		
	}
	
	public void setAbilityInfo(Game game, JLabel a, int i) {
		if (game.getCurrentChampion().getAbilities().get(i) instanceof HealingAbility) {
			a.setText("<html>" + "Heal " + game.getCurrentChampion().getAbilities().get(i).getCastArea() +"<br/>" +
					"Cast Range: " + game.getCurrentChampion().getAbilities().get(i).getCastRange() + "<br/>" +
					"Mana: " + game.getCurrentChampion().getAbilities().get(i).getManaCost() + "<br/>" +
					"Action pts: " + game.getCurrentChampion().getAbilities().get(i).getRequiredActionPoints() + "<br/>" +
					"Base Cool Down: " + game.getCurrentChampion().getAbilities().get(i).getBaseCooldown() + "<br/>" +
					"Current Cool Down: " + game.getCurrentChampion().getAbilities().get(i).getCurrentCooldown() + "<br/>" +
					"Heal Amount: " + ((HealingAbility) game.getCurrentChampion().getAbilities().get(i)).getHealAmount() + "<br/>" +
					"</html>");
		} else if (game.getCurrentChampion().getAbilities().get(i) instanceof DamagingAbility) {
			a.setText("<html>" + "Damage " + game.getCurrentChampion().getAbilities().get(i).getCastArea() + "<br/>" +
					"Cast Range: " + game.getCurrentChampion().getAbilities().get(i).getCastRange() + "<br/>" +
					"Mana: " + game.getCurrentChampion().getAbilities().get(i).getManaCost() + "<br/>" +
					"Action pts: " + game.getCurrentChampion().getAbilities().get(i).getRequiredActionPoints() + "<br/>" +
					"Base Cool Down: " + game.getCurrentChampion().getAbilities().get(i).getBaseCooldown() + "<br/>" +
					"Current Cool Down: " + game.getCurrentChampion().getAbilities().get(i).getCurrentCooldown() + "<br/>" +
					"Damage Amount: " + ((DamagingAbility) game.getCurrentChampion().getAbilities().get(i)).getDamageAmount() + "<br/>" +
					"</html>");
		} else if (game.getCurrentChampion().getAbilities().get(i) instanceof CrowdControlAbility) {
			a.setText("<html>" + "CC " + game.getCurrentChampion().getAbilities().get(i).getCastArea() + "<br/>" +
					"Cast Range: " + game.getCurrentChampion().getAbilities().get(i).getCastRange() + "<br/>" +
					"Mana: " + game.getCurrentChampion().getAbilities().get(i).getManaCost() + "<br/>" +
					"Action pts: " + game.getCurrentChampion().getAbilities().get(i).getRequiredActionPoints() + "<br/>" +
					"Base Cool Down: " + game.getCurrentChampion().getAbilities().get(i).getBaseCooldown() + "<br/>" +
					"Current Cool Down: " + game.getCurrentChampion().getAbilities().get(i).getCurrentCooldown() + "<br/>" +
					"Effect: " + ((CrowdControlAbility) game.getCurrentChampion().getAbilities().get(i)).getEffect().getName() + "<br/>" +
					"Duration: " + ((CrowdControlAbility) game.getCurrentChampion().getAbilities().get(i)).getEffect().getDuration() + "<br/>" +
					"</html>");
		}
		
	}
	
	public void setPic(Game game, JLabel a, int i) {
		if (game.getCurrentChampion().getAbilities().get(i) instanceof HealingAbility) {
			a.setIcon(new ImageIcon("resources/healingStone.png"));
		} else if (game.getCurrentChampion().getAbilities().get(i) instanceof DamagingAbility) {
			a.setIcon(new ImageIcon("resources/damagingStone.png"));
		} else if (game.getCurrentChampion().getAbilities().get(i) instanceof CrowdControlAbility) {
			a.setIcon(new ImageIcon("resources/ccStone.png"));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
