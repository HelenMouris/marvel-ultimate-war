package view;

import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Intro1GIF extends JFrame implements Serializable {
	private JPanel contentPane;

	public Intro1GIF() {
		setSize(1500, 825);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setTitle("Marvel Ultimate War");

		ImageIcon image = new ImageIcon("resources/logo.png");
		setIconImage(image.getImage());

		BackgroundPanel back = new BackgroundPanel(new ImageIcon("resources/marvel-intro.gif").getImage()
				.getScaledInstance(1500, 825, Image.SCALE_DEFAULT));
		setContentPane(back);

		setVisible(true);
		try {
			Thread.sleep((long) (7.7 * 1000));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
