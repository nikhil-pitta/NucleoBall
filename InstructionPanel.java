package nucleoBall;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/* Hello! Before attempting to play this game, here are a few things you should know. This "
				+ "game will help you learn about the fundamentals of DNA and RNA. When you go back to the homepage, there "
				+ "will be a button called \"start\". Once you press this button, there will be three difficulties present."
				+ "If you do not know much about DNA, try playing the easy difficulty before attempting the other difficulties"
				+ "The game will immediately start once you press a difficulty button. Before attempting to explain the game mechanics, "
				+ "try to understand the basic structure of DNA and RNA. These two nucleic acids are composed of 4 nucleotides each: "
				+ "Thymine, Cytosine, Guanine, and Adenine. But in RNA, there is Uracil instead of Thymine. In these nucleic "
				+ "acids, the nucleotides join together in a specific fasion to form a rung that you might traditionally see on "
				+ "a DNA replica. The way nucleoides join is by having Adenine pair with Thymine and Cytosine pair with Guanine. "
				+ "In RNA, however, Adenine pairs with Uracil. Back to the game mechanics. You will enter a screen where.*/

public class InstructionPanel extends JPanel
{
	private JTextArea instructions;// JLabel with instructions
	private JButton back; // button that takes you back to the startPanel
	private MenuCardLayout mcl; // panel with cardLayout()
	private JLabel greeting;
	private Font font;
	private Image backGround;

	public InstructionPanel() // initialize field variables 
	{
		setLayout(null);

		instructions = new JTextArea();
		back = new JButton();
		greeting = new JLabel();
		
		backGround = new ImageIcon("src//nucleoBall//backGround.jpg").getImage();

		setInstructions();
	}

	public void setInstructions()// sets Location of all components and 
	// adds the button to an ActionListener called BackHandler
	
	{
		instructions.setEditable(false);  
		instructions.setCursor(null);  
		instructions.setOpaque(false);  
		instructions.setFocusable(false);
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		
		font = new Font("TimesRoman", Font.PLAIN + Font.BOLD, 25);
		
		instructions.setFont(font);
		instructions.setForeground(new Color(255, 255, 0));
		instructions.setText(
				  "Hello! Before attempting to play this game, here are a few things you should know. "
				+ "This game will help you learn about the fundamentals of DNA and RNA. When you go "
				+ "back to the homepage, there will be a button called \"start\". Once you press this "
				+ "button, there will be three difficulties present. Once you press a difficulty, the "
				+ "game will begin. Before I explain how to play this game, here are the basics you "
				+ "should know about DNA and RNA. DNA and RNA are nucleic acids which consists of 4 "
				+ "nucleotides. The nucleotides are Adenine, Cytosine, Guanine, and Thymine. In RNA, there is "
				+ "Uracil instead of Thymine. These nucleotides pair up with each other: Adenine and Thymine and Cytosine and Guanine."
				+ "In RNA, Adenine and Uracil pair up. These are the basics and for more information, play the easy difficulty as the "
				+ "information that is given will be needed in order to play harder difficulties. In order to play the game, you must match"
				+ "the nucleotide on the ball with the nucleotides on the blocks. Blue indicates DNA and red indicates RNA. You can switch"
				+ "between these two by pressing \"w\". In the easy mode, once you attempt to match, a screen will come up giving you needed"
				+ "information. In the harder difficulties, a question will come up instead. That's all you need to know! Good Luck!");

		instructions.setBounds(Coordinator.GAME_WIDTH/2 - 400, 125, 800, 675); // temporary
		add(instructions);
	
		back.setText("Go Back To Menu");
		back.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		back.setForeground(new Color(0, 204, 0));
		back.setBackground(new Color(0, 0, 204));
		back.setBounds(Coordinator.GAME_WIDTH/2 - 200, (int)(Coordinator.GAME_HEIGHT*0.8), 400, 100);
		back.addActionListener(new BackHandler());
		add(back);
	
		font = new Font("TimesRoman", Font.BOLD, 50);
		
		greeting.setFont(font);
		greeting.setForeground(new Color(255, 255, 0));
		greeting.setText("Welcome to NucleoBall!");
		greeting.setBounds(Coordinator.GAME_WIDTH/2 - 260, 30, 520, 50); // temporary
		add(greeting);
	
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(backGround, 0, 0, Coordinator.GAME_WIDTH, Coordinator.GAME_HEIGHT, null);
	}
	
	public void setMenu(MenuCardLayout men){ mcl = men;}// method in order to get the
	// reference of the panel with the cardLayout()
	
	// puts you back at startPanel
	class BackHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			mcl.startBack();
		}
	}
}