package nucleoBall;

// executes all methods from the game

import java.awt.*;
import javax.swing.*;

public class GamePanel
{
	private int difficulty;
	private DrawingPanel board;
	private Graphics canvas;
	public Ball ball;
	private Deflector deflector;
	private Brick brick;
	private BrickManager brickManager;
	private GameOverPanel p;
	private MenuCardLayout mcl;
	
	private Color invisColor;
	
	public GamePanel(int diff, DrawingPanel board)
	{
		invisColor = new Color(0, 0, 0, 0);
		
		this.board = board;
		difficulty = diff;

		//setLocation();
		p = new GameOverPanel();
		
		ball = new Ball(500, 500, 30, 0.5, 0.5, difficulty, invisColor);

		deflector = new Deflector(200, 200, 50, invisColor);
		//brick = new Brick(200, 200, 50, 50, Color.BLUE);
		brickManager = new BrickManager(200, Coordinator.GAME_WIDTH-250, invisColor);
		
	
		
		board.addMouseListener(deflector);
		board.addKeyListener(ball);
	
		ball.setDeflector(deflector);
		ball.setBrickManager(brickManager);
		//ball.setBrick(brick); // temp
		//brick.setBall(ball);
		
		brickManager.setBall(ball);
		
		canvas = board.getCanvas();


	
	}
	
	public GamePanel(MenuCardLayout mcl) { this.mcl = mcl; }
	
	public void start()
	{
		
		board.clear();
		//System.out.println("setLocation");
		
		ball.draw(canvas);
		///brick.draw(canvas);
		
		brickManager.draw(canvas);
		
		deflector.draw(canvas);
		
		ball.move();
		brickManager.move();
		
		//if(ball.getY()>1000) mcl.gameOver();

		
		board.repaint();
	}
	
	/*public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		setBackground(Color.YELLOW);
		
		if(difficulty==1)
			g.setColor(Color.GREEN);
		
		if(difficulty==2)
			g.setColor(Color.BLUE);
		
		if(difficulty==3)
			g.setColor(Color.BLACK);
		
		g.fillOval(20, 20,  40,  40);

	}*/
}
