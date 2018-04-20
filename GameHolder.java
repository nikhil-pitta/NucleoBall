package nucleoBall;

// panel that holds the buttons and the game

import java.awt.*;
import javax.swing.*;

public class GameHolder extends JPanel
{
	public ButtonGamePanel buttons; // reference of the panel
	// which gives you the option to go back to the menu
	public GamePanel game; // reference of the panel that holds the game
	private DrawingPanel panel;
	
	public GameHolder(MenuCardLayout mcl, int diff) // initialize and set locations
	// of all field variables
	{
		//System.out.println("hi");
		
		setLayout(null);
		
		buttons = new ButtonGamePanel(mcl);
		buttons.setBounds(0, 0, Coordinator.GAME_WIDTH, 150);
		
		panel = new DrawingPanel(Coordinator.GAME_WIDTH, 850);
		panel.setBounds(0, 100, Coordinator.GAME_WIDTH, 850);
		
		game = new GamePanel(diff, panel); // initializes GamePanel
		// with appropriate difficulty
		
		//game.setBounds(0, 100, Coordinator.GAME_WIDTH, 700);
		
		add(buttons);
		//add(game);
		add(panel);
		
	}	
	
	public GamePanel getGame(){ return game; }
}
