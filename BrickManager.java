package nucleoBall;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class BrickManager {

	private ArrayList<Brick> leftBricks;
	private ArrayList<Brick> rightBricks;
	//private int numOfLiveBricks;
	private int currLengthOfWall;
	private int brickWidth;

	private Brick hitBrick;
	private Color color;
	
	private Image adenineD, cytosineD, guanineD, thymineD;
	private Image adenineR, cytosineR, guanineR, uracilR;
	
	private Image chose;
	
	private int random;


	// 1. create managees (bricks) objects.
	public BrickManager(int lx, int rx, Color color)
	{
		random = 0;
		this.color = color;
		
		leftBricks = new ArrayList<Brick>(10);
		rightBricks = new ArrayList<Brick>(10);
		brickWidth = 100;

		//System.out.println(bricks.size());
		
		adenineD = new ImageIcon("src//nucleoBall//DBlock_Adenine.png").getImage();
		cytosineD = new ImageIcon("src//nucleoBall//DBlock_Cytosine.png").getImage();
		guanineD = new ImageIcon("src//nucleoBall//DBlock_Guanine.png").getImage();
		thymineD = new ImageIcon("src//nucleoBall//DBlock_Thymine.png").getImage();
		
		adenineR = new ImageIcon("src//nucleoBall//RBlock_Adenine.png").getImage();
		cytosineR = new ImageIcon("src//nucleoBall//RBlock_Cytosine.png").getImage();
		guanineR= new ImageIcon("src//nucleoBall//RBlock_Guanine.png").getImage();
		uracilR = new ImageIcon("src//nucleoBall//RBlock_Uracil.png").getImage();



		for(int i=0; i<100; i++)
		{
			//int length = makeLength();
			
			chooseImage();

			leftBricks.add(new Brick(lx, -1000 + brickWidth*2*i, 50, brickWidth, color, 1, false, chose, random));
			rightBricks.add(new Brick(rx, 5000 -  brickWidth*2*i, 50, brickWidth, color, -1, true, chose, random));

		}

	}
	
	public void chooseImage()
	{
		random = (int)(Math.random()*8+1);
		
		if(random==1) chose = adenineD;
		else if(random==2) chose = cytosineD;
		else if(random==3) chose = guanineD;
		else if(random==4) chose = thymineD;
		else if(random==5) chose = adenineR;
		else if(random==6) chose = cytosineR;
		else if(random==7) chose = guanineR;
		else if(random==8) chose = uracilR;
		
	}

	/*public int makeLength()
	{
		int length = (int)(Math.random()*30 + 50);
		currLengthOfWall+=length*2;
		return length;
	}*/


	// 2. use (delegate) responsibilities to the managees.
	public void draw(Graphics g)
	{
		//System.out.println(bricks.size());
		
		for(int i=0; i<leftBricks.size(); i++)
		{
			leftBricks.get(i).draw(g);
			
		}

		for(int i=0; i<rightBricks.size(); i++)
		{
			rightBricks.get(i).draw(g);
		}
	}

	public void move()
	{
		//System.out.println(bricks.size());

		for(int i=0; i<leftBricks.size(); i++)
		{
			leftBricks.get(i).move();

		}

		for(int i=0; i<rightBricks.size(); i++)
		{
			rightBricks.get(i).move();

		}
	}



	/*	public boolean isHit()
	{
		for(int i=0; i<bricks.size(); i++)
		{
			if(bricks.get(i).isHit())
			{
				// if it is the bottom one busted.

				// if it is one of the middle ones.
				bricks.remove(i);

				return true;
			}
			//numOfLiveBricks--;
		}
		return false;
	}*/

	public boolean isHit()
	{
		for(int i=0; i<leftBricks.size(); i++)
		{
			if(leftBricks.get(i).isHit())
			{
				hitBrick = leftBricks.get(i);
				leftBricks.remove(i);
				return true;
			}

		}

		for(int i=0; i<rightBricks.size(); i++)
		{
			if(rightBricks.get(i).isHit())
			{
				hitBrick = rightBricks.get(i);
				rightBricks.remove(i);
				return true;
				
				
			}
		}

		return false;

	}

	public Brick brickHit()
	{
		return hitBrick;
	}


	public ArrayList<Brick> getBricks(){ return leftBricks;}

	public void setBall(Ball ball)
	{
		for(int i=0; i<leftBricks.size(); i++)
		{
			leftBricks.get(i).setBall(ball);
		}
		
		for(int i=0; i<rightBricks.size(); i++)
		{
			rightBricks.get(i).setBall(ball);
		}
	}

}

