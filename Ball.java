package nucleoBall;

import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;

public class Ball implements KeyListener
{
	private double x, y;
	private double vx, vy;
	private int radius;
	private Color invisColor;

	private double gravity;
	private double cap;

	private Deflector deflector;
	//private Brick brick;
	private BrickManager brickManager;
	private int dX, dY, bY; // how much the deflector and bricks affect the ball
	private ButtonGamePanel bGP;
	private MenuCardLayout mcl;
	public int delay;
	private InformationPanel infoP;

	private boolean hit;
	private boolean question;

	private boolean gameOver;
	private boolean DNA;

	private Image adenineD, thymineD, cytosineD, guanineD;
	private Image adenineR, uracilR, cytosineR, guanineR;

	private Image chose;
	private int random;

	public QuestionPanel q;

	private int difficulty;

	public boolean correctBrick;
	
	public boolean noBackDifficulty; // user cannot go back from a question or 
	// info panel
	
	private int speedTime;
	private int prevSpeedTime;
	private int playTime;
	private int prevPlayTime;
	
	private boolean ignore;
	
	public Ball(int x, int y, int radius, double vx, double vy, int difficulty, Color color)
	{
		speedTime = 0;
		ignore = false;
		
		invisColor = color;
		
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.vx = vx;
		this.vy = vy;
		
		bY = 10;
		delay = 0;

		if(difficulty==1) 
		{
			gravity = 0.5;
			cap = 10;
			dY = 15;
			dX = 5;
			speedTime = 250;
			prevSpeedTime = speedTime;
			playTime = 100;
			prevPlayTime = playTime;
		}
		else if(difficulty==2)
		{
			gravity = 1;
			cap = 10;
			dY = 20;
			dX = 5;
			speedTime = 250;
			prevSpeedTime = speedTime;
			playTime = 300;
			prevPlayTime = playTime;
		}
		else if(difficulty==3)
		{
			gravity = 1.5;
			cap = 10;
			dY = 30;
			dX = 5;
			speedTime = 150;
			prevSpeedTime = speedTime;
			playTime = 250;
			prevPlayTime = playTime;
		}

		adenineD = new ImageIcon("src//nucleoBall//DNA_Adenine.png").getImage();
		thymineD = new ImageIcon("src//nucleoBall//DNA_Thymine.png").getImage();
		cytosineD = new ImageIcon("src//nucleoBall//DNA_Cytosine.png").getImage();
		guanineD = new ImageIcon("src//nucleoBall//DNA_Guanine.png").getImage();
		adenineR = new ImageIcon("src//nucleoBall//RNA_Adenine.png").getImage();
		uracilR = new ImageIcon("src//nucleoBall//RNA_Uracil.png").getImage();
		cytosineR = new ImageIcon("src//nucleoBall//RNA_Cytosine.png").getImage();
		guanineR = new ImageIcon("src//nucleoBall//RNA_Guanine.png").getImage();

		hit = false;
		gameOver = false;
		DNA = true;
		question = false;
		correctBrick = false;
		noBackDifficulty = false;

		random = (int)(Math.random()*4+1);

		chooseImage();

		q = new QuestionPanel();
		q.setMenu(mcl);
		q.setBall(this);


		this.difficulty = difficulty;
		//System.out.println(difficulty);


		//score = bGP.sc;
		

	}

	public void setButtonGamePanel(ButtonGamePanel bGamePanel) 
	{ 
		bGP = bGamePanel; 
		bGP.setBall(this);
	}
	public void setMenu(MenuCardLayout mcl) { this.mcl = mcl; }

	public void setDeflector(Deflector def) {deflector = def;}
	public void setBrickManager(BrickManager brickManager) {this.brickManager = brickManager; } // temp
	//public void setBrick(Brick brick) {this.brick = brick;} // temp

	public void move()
	{
		//faster();                   /// DONTODNOTNodnepofnporqg FORGET
		//limitPlayTime();
		
		x+=vx;
		y+=vy;

		if(vy<cap)
			vy+=gravity;
		else 
			vy+=0.1;
		//gravity+=0.015;
		//y+=gravity;

		if(collidedWithDeflector()) 
		{
			//color = Color.GREEN;
			checkCollisionWithDeflector();
		}

		/*for(int i=0; i<brickManager.getBricks().size(); i++)
		{
			boolean result = brickManager.getBricks().get(i).isHit();

			if(result)
			{
				checkCollisionWithBrick(brickManager.getBricks().get(i));
			}
		}*/

		if(brickManager.isHit())
		{

			checkCollisionWithBrick(brickManager.brickHit());
			//makeInfo(brickManager.brickHit().getNumber());

			bGP.incrementScore();
			playTime = prevPlayTime; //reset playtime

			hit = true; // for InfoPanel


			//if(bGP.sc%1==0) question = true;
		}

		else if(!brickManager.isHit())
		{
			//gameOver();
		}

		/*if(brick.getIsHit())
		{
			checkCollisionWithBrick();
		}*/

		if(difficulty==1) makeInfo();

		else
		{
			makeQuestion();
		}


		if(y+radius>1000 || y-radius<0 || x-radius<0 || x+radius>1200)
		{
			gameOver();
		}

		deflector.setX(1000);
		deflector.setY(1000);

	}
	
	public void limitPlayTime()
	{
		playTime--;
		
		if(playTime==0 && !gameOver)
		{
			gameOver();
		}
		
	}
	
	public void faster()
	{
		speedTime--;
		if(speedTime==0)
		{
			speedTime = prevSpeedTime;

			if(!(dY<=10))
			{
				dY-=2;
			}
			
			if(!(dX>=7))
			{
				dX+=2;
			}
		}
	}

	public void makeQuestion()
	{
		if(question)
		{
			delay++;

			if(delay==3 && !gameOver)
			{
				noBackDifficulty = true;
				q.makeVisible();
				delay = 0;
				question = false;
				noBackDifficulty = false;
			}
		}

	}

	public void makeInfo()
	{
		if(hit)
		{
			delay++;

			if(delay==3 && !gameOver)
			{
				noBackDifficulty = true;
				infoP = new InformationPanel(brickManager.brickHit().getNumber());
				infoP.setBall(this);
				delay = 0;
				hit = false;
				noBackDifficulty = false;
			}
		}
	}


	public void checkForCorrect(Brick brick)
	{
		if(difficulty==1)
		{
			if(random==1)
			{
				if(DNA && brick.getNumber()==4)
				{
					correctBrick = true;
					return;
				}

				else if(!DNA && brick.getNumber()==8)
				{
					correctBrick = true;
					return;
				}

				else correctBrick = false;

			}

			else if(random==2)
			{
				if(DNA && brick.getNumber()==3)
				{
					correctBrick = true;
					return;
				}

				else if(!DNA && brick.getNumber()==7)
				{
					correctBrick = true;
					return;
				}

				else correctBrick = false;

			}

			else if(random==3)
			{
				if(DNA && brick.getNumber()==2)
				{
					correctBrick = true;
					return;
				}

				else if(!DNA && brick.getNumber()==6)
				{
					correctBrick = true;
					return;
				}

				else correctBrick = false;
			}

			else if(random==4)
			{
				if(DNA && brick.getNumber()==1)
				{
					correctBrick = true;
					return;
				}

				else if(!DNA && brick.getNumber()==5)
				{
					correctBrick = true;
					return;
				}

				else correctBrick = false;

			}
		}

		else 
		{
			if(random==1)
			{
				if(DNA && brick.getNumber()==4)
				{
					return;
				}

				else if(!DNA && brick.getNumber()==8)
				{
					return;
				}

				else gameOver();

			}

			else if(random==2)
			{
				if(DNA && brick.getNumber()==3)
				{
					return;
				}

				else if(!DNA && brick.getNumber()==7)
				{
					return;
				}

				else gameOver();

			}

			else if(random==3)
			{
				if(DNA && brick.getNumber()==2)
				{
					return;
				}

				else if(!DNA && brick.getNumber()==6)
				{
					return;
				}

				else gameOver();

			}

			else if(random==4)
			{
				if(DNA && brick.getNumber()==1)
				{
					return;
				}

				else if(!DNA && brick.getNumber()==4)
				{
					return;
				}

				else gameOver();
			}
		}

	}


	public void gameOver()
	{
		playTime = 0;
		mcl.gameOver();
		gameOver = true;
	}

	public boolean collidedWithDeflector()
	{
		return (Math.sqrt((x-deflector.getX() )*(x-deflector.getX())+(y-deflector.getY())*
				(y-deflector.getY()))<(radius + deflector.getSize())) ;
	}

	public void draw(Graphics g)
	{
		g.setColor(invisColor); // dont forget
		g.fillOval((int)x-radius, (int)y-radius, radius*2, radius*2);


		g.drawImage(chose, (int)x-radius, (int)y-radius, radius*2+5, radius*2+5, null);
	}

	public void checkCollisionWithDeflector()
	{
		if(deflector.getY()>y) vy = -dY;
		if(deflector.getY()<y) vy = -dY;
		if(deflector.getX()<x) vx = +dX;
		if(deflector.getX()>x) vx = -dX;
	}

	public void checkCollisionWithBrick(Brick brick)
	{
		checkForCorrect(brick);
		///if((brick.getX()>x || brick.getX()<x)) vx = -vx;
		if(!brick.isRight())
		{
			if(y>brick.getY() && x>brick.getX()-brick.getSize() && x<brick.getX()+brick.getSize()) vy = +bY;
			if(y<brick.getY() && x>brick.getX()-brick.getSize() && x<brick.getX()+brick.getSize()) vy = -bY;
			else /*if(x>brick.getX() || x<brick.getSize())*/ vx = -vx;
		}

		else
		{
			if(y>brick.getY() && x>brick.getX()-brick.getSize() && x<brick.getX()+brick.getSize()) vy = +bY;
			if(y<brick.getY() && x>brick.getX()-brick.getSize() && x<brick.getX()+brick.getSize()) vy = -bY;
			else /*if(x>brick.getX() || x<brick.getSize())*/ vx = -vx;
		}

		//delay++;

		//if(brick.getY()>y /*&& (brick.getX()-radius<=x && x<=brick.getX()+radius)*/) vy = -15;
		//if(brick.getY()<y && (brick.getX()-radius<=x && x<=brick.getX()+radius)) vy = 15;

		random = (int)(Math.random()*4+1);

		chooseImage();

	}

	public void chooseImage()
	{
		if(DNA)
		{
			if(random==1) chose = adenineD;
			else if(random==2) chose = cytosineD;
			else if(random==3) chose = guanineD;
			else if(random==4) chose = thymineD;
		}

		else
		{
			if(random==1) chose = adenineR;
			else if(random==2) chose = cytosineR;
			else if(random==3) chose = guanineR;
			else if(random==4) chose = uracilR;
		}
	}

	public double getX(){ return x; }
	public double getY(){ return y; }
	public int getRadius(){ return radius; }
	public double getVX(){ return vx; }
	public double getVY(){ return vy; }

	public int getNumber() { return random; }
	public boolean getDNA() { return DNA; }

	public void setVX(int vx){this.vx = vx;}
	public void setVY(int vy){this.vy = vy;}


	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode()==KeyEvent.VK_W)
		{
			DNA = !DNA;

			chooseImage();
		}
	}

	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
}
