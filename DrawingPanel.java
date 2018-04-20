package nucleoBall;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class DrawingPanel extends JPanel implements MouseListener
{
	private BufferedImage bImage;
	private Graphics canvas;
	
	public DrawingPanel(int width, int height)
	{

		bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		canvas = bImage.getGraphics();
		
		//((Graphics2D)canvas).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//((Graphics2D)canvas).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		
		addMouseListener(this);
	}
	
	public Graphics getCanvas(){return canvas;}
	
	public void clear()
	{ 
		canvas.setColor(new Color(153, 204, 255));
		canvas.fillRect(0, 0, bImage.getWidth(), bImage.getHeight());
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(bImage, 0, 0, null);
		
		//super.paintComponent(g);
		
		//g.drawOval(30, 20, 20, 20);
	}
	
	public void mousePressed(MouseEvent e)
	{
		requestFocusInWindow();
	}


	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}
