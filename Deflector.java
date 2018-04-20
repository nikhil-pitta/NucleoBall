package nucleoBall;

import java.awt.*;
import java.awt.event.*;

public class Deflector implements MouseListener
{
	private int x, y;
	private int size;
	private Color color;
	
	public Deflector(int x, int y, int size, Color color)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval(x-size, y-size, size*2, size*2);
		
		//g.setColor(Color.WHITE);
		//g.fillRect(x-size, y-size, size*2, size*2);
		
	}
	
	public void mousePressed(MouseEvent e) 
	{
		x = e.getX();
		y = e.getY();
	}
	
	public int getX(){ return x; }
	public int getY(){ return y; }
	public int getSize(){ return size; }
	
	public void setX(int x){ this.x = x; }
	public void setY(int y){ this.y = y; }

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
