package nucleoBall;

import java.awt.Color;
import java.awt.Font;

// panel in the game with the buttons

import java.awt.event.*;

import javax.swing.*;

public class ButtonGamePanel extends JPanel
{
	private JButton back; // button to go back to difficulty
	// selection
	private MenuCardLayout mcl;
	private JLabel score;
	private Ball ball;
	public int sc; // score of game
	private Font font;
	private JLabel scoreHeader;

	public ButtonGamePanel(MenuCardLayout mcl) //Initialize all field variables
	{
		setLayout(null);

		setBackground(new Color(153, 204, 255));
		back = new JButton();
		score = new JLabel();
		scoreHeader = new JLabel();
		this.mcl = mcl;
		
		sc = 0;

		setLocation();
		
	}	

	public void incrementScore() 
	{
		sc++; 
		setScore();
	}

	public void setLocation() // sets Location of the button and gives the button
	// its appropriate actionListener
	{
		back.setText("Go Back");
		back.setBounds(100 ,(int)(Coordinator.GAME_HEIGHT*0.015), 150, 25);
		back.addActionListener(new BackSelectionHandler());
		add(back);
		
		font = new Font("TimesRoman", Font.BOLD + Font.ITALIC, 30);
		scoreHeader.setFont(font);
		scoreHeader.setText("Score");
		scoreHeader.setBounds(Coordinator.GAME_WIDTH/2 - 38, 10, 70, 25);
		add(scoreHeader);
		
		font = new Font("TimesRoman", Font.PLAIN + Font.BOLD, 30);

		score.setFont(font);
		score.setText(String.valueOf(sc));
		score.setBounds(Coordinator.GAME_WIDTH/2 - 15, 55, 20, 25);
		add(score);
	}
	
	public void setScore()
	{
		score.setText(String.valueOf(sc));
		score.setBounds(Coordinator.GAME_WIDTH/2 - 15, 55, 20, 25);
		add(score);
	}
	
	public void setBall(Ball ball) { this.ball = ball; }
	
	class BackSelectionHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			if(!ball.noBackDifficulty)
			mcl.difficultyBack();
		}
	}
}
