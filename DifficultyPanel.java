package nucleoBall;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

// an option to take from the start panel

import java.awt.event.*;
import javax.swing.*;

public class DifficultyPanel extends JPanel
{
	private JLabel title;// says, "Select Difficulty"
	private JButton easy, medium, hard, back; // difficulties and back button
	public MenuCardLayout mcl; // reference of panel with cardLayout in order 
	// to call a method
	private int difficulty;
	private Image backGround;

	public DifficultyPanel() // initialize all field variables
	{
		setLayout(null);

		title = new JLabel();
		back = new JButton();
		easy = new JButton();
		medium = new JButton();
		hard = new JButton();
		
		backGround = new ImageIcon("src//nucleoBall//backGround.jpg").getImage();

		setInstructions();
	}

	public void setInstructions() // sets Locations of all components and
	// adds all buttons to an ActionListener called BackHandler
	{
		title.setText("Select a Difficulty");
		title.setFont(new Font("TimesRoman", Font.PLAIN + Font.BOLD, 50));
		title.setForeground(new Color(255, 255, 0));
		title.setBounds(Coordinator.GAME_WIDTH/2 - 190, 35, 380, 60); // temporary
		add(title);

		back.setText("Go Back To Menu");
		back.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		back.setForeground(new Color(0, 204, 0));
		back.setBackground(new Color(0, 0, 204));
		back.setBounds(Coordinator.GAME_WIDTH/2 - 100, (int)(Coordinator.GAME_HEIGHT*0.8), 200, 80);
		back.addActionListener(new BackHandler());
		add(back);
		
		Font font = new Font("TimesRoman", Font.PLAIN, 70);

		easy.setText("Easy");
		easy.setFont(font);
		easy.setForeground(new Color(0, 204, 0));
		easy.setBackground(new Color(0, 0, 204));
		easy.setBounds(Coordinator.GAME_WIDTH/2 - 150, (int)(Coordinator.GAME_HEIGHT*0.157), 300, 150);
		easy.addActionListener(new EasyHandler());
		add(easy);

		medium.setText("Medium");
		medium.setFont(font);
		medium.setForeground(new Color(0, 204, 0));
		medium.setBackground(new Color(0, 0, 204));
		medium.setBounds(Coordinator.GAME_WIDTH/2 - 150, (int)(Coordinator.GAME_HEIGHT*0.37), 300, 150);
		medium.addActionListener(new MediumHandler());
		add(medium);

		hard.setText("Hard");
		hard.setFont(font);
		hard.setForeground(new Color(0, 204, 0));
		hard.setBackground(new Color(0, 0, 204));
		hard.setBounds(Coordinator.GAME_WIDTH/2 - 150, (int)(Coordinator.GAME_HEIGHT*0.57), 300, 150);
		hard.addActionListener(new HardHandler());
		add(hard);

	}

	public void setMenu(MenuCardLayout men){ mcl = men;}// method in order to get the
	// reference of the panel with the cardLayout()
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(backGround, 0, 0, Coordinator.GAME_WIDTH, Coordinator.GAME_HEIGHT, null);
	}

	// puts you back at startPanel
	class BackHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			mcl.startBack();
		}
	}

	// All the actionListeners below set the appropriate 
	// difficulty for the game

	class EasyHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			difficulty = 1;
			mcl.setDifficulty(difficulty);
		}
	}

	class MediumHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			difficulty = 2;
			mcl.setDifficulty(difficulty);

		}
	}

	class HardHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			difficulty = 3;
			mcl.setDifficulty(difficulty);
		}
	}

	public int getDifficulty() { return difficulty; }

}