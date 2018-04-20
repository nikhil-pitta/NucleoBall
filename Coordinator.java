package nucleoBall;

import javax.swing.JFrame;

//creates JFrame 


public class Coordinator
{
	private JFrame frame;
	private static MenuCardLayout mcl;// reference of Panel with cardLayout
	public static int GAME_WIDTH, GAME_HEIGHT; // width and height of JFrame

	private static GameHolder gH = null;
	private static GamePanel game = null;

	public Coordinator() // initialize all variables, add panel to JFrame
	{
		frame = new JFrame("Nucleo Ball");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GAME_WIDTH = 1200;
		GAME_HEIGHT = 1000;
		frame.setSize(GAME_WIDTH, GAME_HEIGHT);
		mcl = new MenuCardLayout();
		frame.getContentPane().add(mcl);

		frame.setVisible(true);

	}

	public static void main(String[] args) throws InterruptedException
	{
		new Coordinator();

		while(true)
		{
			gH = mcl.getGameHolder();

			if(gH!=null)
			{
				game = gH.getGame();

				while(mcl.getSetGame())
				{
					game.start();
					Thread.sleep(20);;
				}
			}
		}
	}
}


