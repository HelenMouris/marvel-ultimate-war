package buttons;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.Control;
import model.world.Direction;

public class DirectionButton extends JButton {
	private Direction d;
	private ImageIcon image;
	
	public DirectionButton(Direction d,ImageIcon image, Control control) {
		super(image);
		this.d=d;
		
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		addActionListener(control);
	}

	public Direction getD() {
		return d;
	}
}
