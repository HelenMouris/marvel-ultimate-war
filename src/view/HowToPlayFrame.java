package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import engine.Game;

public class HowToPlayFrame extends JFrame implements MouseListener{
	private BackgroundPanel back;
	private JButton done ;
	
	public HowToPlayFrame() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1100,725);
		setResizable(false);
		setLocationRelativeTo(null);
		
		setTitle("Marvel Ultimate War");
		
		ImageIcon image = new ImageIcon("resources/logo.png");
		setIconImage(image.getImage()); 
		
		back = new BackgroundPanel(new ImageIcon("resources/background.jpg").getImage().getScaledInstance(1500, 825, Image.SCALE_DEFAULT));
		setContentPane(back);
		
		setLayout(null);
		
		JPanel left = new JPanel();
		left.setBounds(20, 20, 500, 600);
		left.setVisible(true);
		left.setOpaque(false);
		
		JPanel right = new JPanel();
		right.setLayout(new GridLayout(27,1));
		right.setBounds(550, 20, 500, 600);
		right.setVisible(true);
		right.setOpaque(false);
		
		
		left.setLayout(null);
		ArrayList<JLabel> jl=new ArrayList() ;
		JLabel jl0=new JLabel("Game Flow:");jl.add(jl0);
		jl0.setBounds(0,0,500,20);
		JLabel jl1=new JLabel("1-Each player will select his three champions to form his team.");jl.add(jl1);
		jl1.setBounds(0,20,500,50);
		JLabel jl2=new JLabel("2-The champions will take turns based on their speed.");jl.add(jl2);
		jl2.setBounds(0,50,500,50);
		JLabel jl3=new JLabel ("<html>" +" 3-When the turn goes to a champion, the player controlling " + "<br/>" +
								"the champion can use him to carry out any action as long as " + "<br/>" +
								"the champion has enough action points needed for this action " + "<br/>" +
								"and also enough mana in case of using any of his abilities" + "</html>");jl.add(jl3);
								jl3.setBounds(0,100,500,70);
		JLabel jl4=new JLabel("<html>" + "4-After that, the champion can end his turn and the " + "<br/>" +
								"turn will go to the next champion." + "</html>");jl.add(jl4);
								jl4.setBounds(0,180,500,50);
		JLabel jl5=new JLabel("<html>" +"5-The turns will keep passing over the living champions till a "  + "<br/>" +
								"player is able to defeat all of the three champions of the " + "<br/>" +
								"opponent player."+ "</html>");jl.add(jl5);
								jl5.setBounds(0,240,500,60);
		JLabel jl6=new JLabel("Possible actions that can be done:");jl.add(jl6);
		jl6.setBounds(0,310,500,50);
		JLabel jl7=new JLabel("1- Move to an empty cell. (1 action point)");jl.add(jl7);
		jl7.setBounds(0,350,500,50);
		JLabel jl8=new JLabel("2- Do a normal attack. (2 action points)");jl.add(jl8);
		jl8.setBounds(0,370,500,50);
		JLabel jl9=new JLabel("3- Cast an ability. (refer to required mana and action points)");jl.add(jl9);
		jl9.setBounds(0,390,500,50);
		JLabel jl10=new JLabel("4- Use Leader Ability. (only if champion is leader)");jl.add(jl10);
		jl10.setBounds(0,410,500,50);
		JLabel jl11=new JLabel("Tips:");jl.add(jl11);
		jl11.setBounds(0,450,500,50);
		JLabel jl12=new JLabel("1- Always condsider attack range with noraml attacks and abilities.");jl.add(jl12);
		jl12.setBounds(0,480,500,50);
		JLabel jl13=new JLabel("1- Hover over pictures to see more details about them.");jl.add(jl13);
		jl13.setBounds(0,510,500,50);
		
		for(int i=0;i<jl.size();i++){
			jl.get(i).setFont(new Font("Arial",Font.BOLD,15));jl.get(i).setForeground(Color.WHITE);
			left.add(jl.get(i));
		}
		
		jl0.setFont(new Font("Arial",Font.BOLD,25));jl0.setForeground(Color.RED);
		jl6.setFont(new Font("Arial",Font.BOLD,25));jl6.setForeground(Color.RED);
		jl11.setFont(new Font("Arial",Font.BOLD,25));jl11.setForeground(Color.RED);
		
		
		right.setLayout(null);
		ArrayList<JLabel> jrl=new ArrayList() ;
		JLabel jrl0=new JLabel("Abilities:");jrl.add(jrl0);
		jrl0.setBounds(0,0,500,20);
		JLabel jrl1=new JLabel("<html>" + "1-Healing Stone:" + "<br/>"
								+ "Restore health points to friendly champions" + "</html>");
		jrl1.setIcon(new ImageIcon("resources/healingStone.png"));
		jrl1.setHorizontalAlignment(JLabel.LEFT);
		jrl1.setHorizontalTextPosition(JLabel.RIGHT);
		jrl1.setBounds(0,20,500,90);
		jrl.add(jrl1);
		
		JLabel jrl2=new JLabel("<html>" + "2-Damaging Stone:" + "<br/>"
				+ "Decrease health points of opponent's champions or covers." + "</html>");
		jrl2.setIcon(new ImageIcon("resources/damagingStone.png"));
		jrl2.setHorizontalAlignment(JLabel.LEFT);
		jrl2.setHorizontalTextPosition(JLabel.RIGHT);
		jrl2.setBounds(0,110,500,90);
		jrl.add(jrl2);
		
		JLabel jrl3=new JLabel("<html>" + "3-Crowd Control Stone:" + "<br/>"
				+ "Can empower or weaken their targets by applying different effects" + "</html>");
		jrl3.setIcon(new ImageIcon("resources/ccStone.png"));
		jrl3.setHorizontalAlignment(JLabel.LEFT);
		jrl3.setHorizontalTextPosition(JLabel.RIGHT);
		jrl3.setBounds(0,200,500,90);
		jrl.add(jrl3);
		
		JLabel jrl4 = new JLabel("Effects:");jrl.add(jrl4);
		jrl4.setBounds(0,320,500,20);
		
		JPanel p1=new JPanel();  
		p1.setOpaque(false);
		
		JLabel e1=new JLabel("<html>" + "1-Dodge: 50% chance of dodging normal attacks & speed +20%" + "<br/>" + "<br/>"
				+ "2-Embrace: Mana, HP, speed, and attack damage +20%" + "<br/>" + "<br/>"
				+ "3-PowerUp: heal and damage amount of all abilities +20%" + "<br/>" + "<br/>"
				+ "4-Shield: Block the next attack or damaging ability && speed +2%" + "<br/>" + "<br/>"
				+ "5-SpeedUp: speed + 15% && max and current action pts +1" + "</html>");
		e1.setFont(new Font("Arial",Font.BOLD,15));
		e1.setBounds(0,20,500,90);
		p1.add(e1);
		
	    JPanel p2=new JPanel(); 
	    p2.setOpaque(false);
	    
	    JLabel e2=new JLabel("<html>" + "1-Disarm: Can't use normal attacks but gain a damaing ability Punch" + "<br/>" + "<br/>"
				+ "2-Root: Cannot move" + "<br/>" + "<br/>"
				+ "3-Shock: Speed and attack damage -10% && max and current" + "<br/>"+ "action pts -1" + "<br/>"
				+ "4-Silence: Cannot use abilities but current and max action pts +2" + "<br/>" + "<br/>"
				+ "5-Stun: Cannot play their turn for the duration" + "</html>");
	    e2.setFont(new Font("Arial",Font.BOLD,14));
		e2.setBounds(0,20,500,90);
		p2.add(e2);
		
		JTabbedPane tp=new JTabbedPane();  
	    tp.setBounds(0,350,510,200);  
	    tp.setBackground(Color.WHITE);
	    tp.add("BUFF",p1);  
	    tp.add("DEBUFF",p2);  
		
		for(int i=0;i<jrl.size();i++){
			jrl.get(i).setFont(new Font("Arial",Font.BOLD,15));jrl.get(i).setForeground(Color.WHITE);
			right.add(jrl.get(i));
		}
		
		right.add(tp);
		
		jrl0.setFont(new Font("Arial",Font.BOLD,25));jrl0.setForeground(Color.RED);
		jrl4.setFont(new Font("Arial",Font.BOLD,25));jrl4.setForeground(Color.RED);
		
		done = new JButton();
		done.setBounds(450,600,170,60);
		done.setIcon(new ImageIcon("resources/doneb.png"));
		done.setOpaque(false);
		done.setContentAreaFilled(false);
		done.setBorderPainted(false);
		done.setFocusPainted(false);
		done.setForeground(Color.white);
		done.addMouseListener(this);
		
		
		
		add(left);
		add(right);
		add(done);
		
		setVisible(true);
		revalidate();
		repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		dispose();
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
		done.setIcon(new ImageIcon("resources/donebHover.png"));
		try {	File file = new File("resources/sounds/hover.wav");
		Clip clip = AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(file));
		clip.start();
		return;
		} catch(Exception e1) {
		System.err.println(e1.getMessage());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		done.setIcon(new ImageIcon("resources/doneb.png"));
	}
	
	
}
