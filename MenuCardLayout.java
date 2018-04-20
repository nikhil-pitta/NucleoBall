package nucleoBall;

// has all references

import java.awt.*;
import javax.swing.*;

public class MenuCardLayout extends JPanel
{
	public CardLayout cl;// variable for cardLayout
	
	// below are all the references of all the panels
	// this is so that I can choose whichever one 
	// I want to show
	
	private InstructionPanel iP;
	private StartPanel sP;
	private DifficultyPanel dP;
	private GameOverPanel gP;
	private volatile GameHolder gH;
	private volatile boolean setGame;
	
	private GamePanel g;
	
	public MenuCardLayout()// initialize all variables and
	// add them to the cardLayout()
	{
		setGame = false;
		
		cl = new CardLayout();
		setLayout(cl);
		
		iP = new InstructionPanel();
		sP = new StartPanel();
		dP = new DifficultyPanel();
		gP = new GameOverPanel();
		
		g = new GamePanel(this);
	
		sP.setMenu(this);
		iP.setMenu(this);
		dP.setMenu(this);
		gP.setMenu(this);
		
		
		
		
		add(iP, "instructions");
		add(sP, "start");
		add(dP, "difficult");
		add(gP, "game over");
		
		cl.show(this, "start");
	}
	
	public GameHolder getGameHolder()
	{
		return gH;
	}
	
	// instructions or start
	public void instOrst(boolean instructions, boolean start)// checks to see which 
	// button was pressed and depending on which boolean is true, it shows that panel
	{
		if(instructions) 
		{
			cl.show(this, "instructions");
		}
		
		else if(start)
		{
			cl.show(this, "difficult");
		}
	}
	
	// go back to startPanel from either instructionPanel or 
	// difficultyPanel
	public void startBack()
	{
		cl.show(this, "start");
	}
	
	// go back to difficulyPanel from gamePanel
	
	public void difficultyBack()
	{
		cl.show(this, "difficult");
		setGame = false;
	}
	
	// checks to see which difficulty was pressed
	public void setDifficulty(int diff)
	{
		//System.out.println("setDiff");
		//System.out.println(dP.mcl==gP.mcl);
		//System.out.println(gP.mcl);

		gH = new GameHolder(this, diff);
		add(gH, "game");
		gH.game.ball.setButtonGamePanel(gH.buttons);
		gH.game.ball.setMenu(this);
		
		//System.out.println("difficulty");
		
		setGame = true;

		cl.show(this, "game");
	}
	
	public void gameOver()
	{
		cl.show(this, "game over");
	}

	public boolean getSetGame() 
	{
		//System.out.println(setGame);
		return setGame;
	}
}