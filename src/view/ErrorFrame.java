package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ErrorFrame extends JFrame {

	public ErrorFrame(String m) {
		setSize(500,100);
		setTitle("Marvel Ultimate War");

		ImageIcon image = new ImageIcon("resources/logo.png");
		setIconImage(image.getImage());
		JLabel message = new JLabel(m);
		message.setBackground(Color.BLACK);
		message.setOpaque(true);
		message.setFont(new Font("Arial",Font.PLAIN,15));
		message.setHorizontalAlignment(JLabel.CENTER);
		message.setHorizontalTextPosition(JLabel.CENTER);
		message.setForeground(Color.WHITE);
		getContentPane().add(message);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		setVisible(true);
	}
}
