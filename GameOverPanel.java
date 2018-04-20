package nucleoBall;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

public class GameOverPanel extends JPanel
{
	private JLabel instructions;// JLabel with instructions
	private JButton back; // button that takes you back to the startPanel
	public MenuCardLayout mcl; // panel with cardLayout()

	private Font font;
	private Image gameOver;
	private Image backGround;

	public GameOverPanel() // initialize field variables 
	{
		setLayout(null);

		instructions = new JLabel();
		back = new JButton();

		gameOver = new ImageIcon("src//nucleoBall//gameOver.png").getImage();
		backGround = new ImageIcon("src//nucleoBall//GameOverBack.png").getImage();

		setComponents();
	}

	public void setComponents()// sets Location of all components and 
	// adds the button to an ActionListener called BackHandler
	{
		//instructions.setIcon(gameOver);
		//instructions.setText("Game Over");
		instructions.setBounds(Coordinator.GAME_WIDTH/2 - 100, (int)(Coordinator.GAME_HEIGHT*0.2), 600, 700); // temporary
		//add(instructions);
		font = new Font("TimesRoman", Font.PLAIN, 50);

		back.setFont(font);
		back.setForeground(new Color(0, 204, 0));
		back.setBackground(new Color(0, 0, 204));
		back.setText("Try Again?");
		back.setBounds(Coordinator.GAME_WIDTH/2 - 150, (int)(Coordinator.GAME_HEIGHT*0.6), 300, 300);
		back.addActionListener(new BackHandler());
		add(back);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(backGround, 0, 0, Coordinator.GAME_WIDTH, Coordinator.GAME_HEIGHT, null);
		g.drawImage(gameOver, Coordinator.GAME_WIDTH/2 - 300, (int)(Coordinator.GAME_HEIGHT/2-550), 600, 700, null);
	}

	public void setMenu(MenuCardLayout men){ mcl = men; }
	// method in order to get the
	// reference of the panel with the cardLayout()

	// puts you back at startPanel
	class BackHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			mcl.difficultyBack();;
		}
	}
}

