package nucleoBall;

// start page for game

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartPanel extends JPanel
{
	private JLabel name;// name of the game
	private JButton start, instructions;  // buttons used to
	// access the startPanel or InstructionPanel
	private MenuCardLayout mcl; // panel that used cardLayout()
	private Image backGround;

	public StartPanel() // initialize all field variables
	{
		setLayout(null);
		backGround = new ImageIcon("src//nucleoBall//backGround.jpg").getImage();
		start = new JButton();
		instructions = new JButton();		
		name = new JLabel();

		setLocation();
	}

	public void setMenu(MenuCardLayout men){ mcl = men;}// method in order to get the
	// reference of the panel with the cardLayout()

	public void setLocation()
	{
		Font buttonFont = new Font("Serif", Font.PLAIN, 50);

		start.setFont(buttonFont);
		start.setForeground(new Color(0, 204, 0));
		start.setBackground(new Color(0, 0, 204));
		start.setText("Start");
		start.setBounds(Coordinator.GAME_WIDTH/2 - 100, (int)(Coordinator.GAME_HEIGHT*0.3), 200, 150);
		start.addActionListener(new StartHandler());
		add(start);

		instructions.setFont(buttonFont);
		instructions.setForeground(new Color(0, 204, 0));
		instructions.setBackground(new Color(0, 0, 204));
		instructions.setText("Instructions");
		instructions.setBounds(Coordinator.GAME_WIDTH/2 - 150, (int)(Coordinator.GAME_HEIGHT*0.6), 300, 150);
		instructions.addActionListener(new InstructionHandler());
		add(instructions);

		int nameW = 400;

		name.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 80));
		name.setText("NucleoBall");
		name.setForeground(new Color(255, 255, 0));
		name.setBounds(Coordinator.GAME_WIDTH/2 - nameW/2 + 5, 90, nameW, 70);
		add(name);

	}
	
	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
        g.drawImage(backGround, 0, 0, Coordinator.GAME_WIDTH, Coordinator.GAME_HEIGHT, null);
	}

	// handlers call check method in MenuCardLayout
	// first parameter is for instructions, second is for start

	// when this button is pressed, it should show the 
	// the InstructionPanel
	class InstructionHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			mcl.instOrst(true, false);
		}

	}
	// when this button is pressed, it should show the 
	// the DificultyPanel
	class StartHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			mcl.instOrst(false, true);
		}

	}
}

