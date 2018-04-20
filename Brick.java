package nucleoBall;

import java.awt.*;

import javax.swing.ImageIcon;

public class Brick
{
	private int x, y;
	private int size;
	private int length;
	private int vy;
	private Color color;
	private Ball ball;
	private int maxGravity;
	private boolean right;
	private Image chose;
	private int number;



	public Brick(int x, int y, int size, int length, Color color, int vy, boolean right, Image chose, int number)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
		this.length = length;
		this.vy = vy;
		maxGravity = 30;
		this.right = right;
		this.chose = chose;
		this.number = number;

	}

	public void setBall(Ball ball) { this.ball = ball; }

	public void move()
	{
		y+=vy;

		/*if(vy<maxGravity)
		{
			vy+=1;
			System.out.println(vy);

			/*if(vy>10)
			vy+=0.015;

		}
		else vy+=-(maxGravity+20);*/

	}

	public boolean isHit()
	{
		return x<=ball.getX()+ball.getRadius()+size 
				&& x>=ball.getX()-ball.getRadius()-size 
				&& (y<=ball.getY()+length+ball.getRadius()
				&& y>=ball.getY()-length-ball.getRadius());


	}

	public void draw(Graphics g)
	{
		/*if(isHit())
		{
			g.setColor(Color.GREEN);
			erase();
		}*/

		//else g.setColor(color);
		g.setColor(Color.black);

		//g.fillRect(x-size, y-length, size*2, length*2);
		g.drawImage(chose, x-size, y-length, size*2, length*2, null);
	}

	public void erase(){x=100000;} // temp

	public boolean getIsHit() {return isHit();}
	public boolean isRight() { return right; }

	public int getY() {return y;}
	public int getX() {return x;}
	public int getSize() {return size;}
	public int getNumber() {return number;}

}
