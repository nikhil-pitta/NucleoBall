package nucleoBall;

import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class InformationPanel extends JPanel implements ActionListener
{
	private JFrame frame;
	private int time;
	private int width, height;
	private JTextArea info;
	private JTextArea fact;
	private JLabel factHead;
	private String logic;
	
	private JLabel correct;
	private Font font;
	
	private File inFile;
	private Scanner sc;
	private volatile boolean ok;
	private JButton back;

	private MenuCardLayout mcl;
	private Ball ball;
	
	private int brickNum;
	
	private int randomFact;

	public InformationPanel(int brickNum)
	{
		setLayout(null);

		width = 500;
		height = 700;

		frame = new JFrame();		
		frame.setSize(width, height);

		time = 5000;
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.getContentPane().add(this);
		ok = false;
		this.brickNum = brickNum;
		displayInfo();
		
		randomFact = (int)(Math.random()*8+11);
		//frame.setVisible(true);
		
		//waitForButton();

	}
	
	public void getFile()
	{
		inFile = new File("src//nucleoball//Information.txt");

		try
		{
			sc = new Scanner(inFile);
		}
		catch (FileNotFoundException e)
		{
			System.err.println("The file Information.txt cannot be found");
			System.exit(1);
		}
	}

	public void displayInfo()
	{
		getFile();
		
		//some random stuff to find question
		
		info = new JTextArea();
		fact = new JTextArea();
		
		info.setEditable(false);  
		info.setCursor(null);  
		info.setOpaque(false);  
		info.setFocusable(false);
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		
		fact.setEditable(false);  
		fact.setCursor(null);  
		fact.setOpaque(false);  
		fact.setFocusable(false);
		fact.setLineWrap(true);
		fact.setWrapStyleWord(true);
		
		back = new JButton("Got It!");
		back.setBounds(width/2 - 50, (int) (height*0.8), 100, 25);
		back.addActionListener(this);
		add(back);


	}
	
	public void getInfo()
	{
		correct = new JLabel();
		font = new Font("TimesRoman", Font.BOLD, 40);
		correct.setFont(font);
		
		if(ball.correctBrick) correct.setText("Correct!");
		
		else correct.setText("Wrong!");		
		
		if(brickNum==1 || brickNum==5)
		{
			logic = sc.nextLine();
		}
		
		else if(brickNum==2 || brickNum==6)
		{
			for(int i=0; i<6; i++) sc.nextLine();
			
			logic = sc.nextLine();
		}
		
		else if(brickNum==3 || brickNum==7)
		{
			for(int i=0; i<4; i++) sc.nextLine();
			
			logic = sc.nextLine();
			
		}
		
		else if(brickNum==4)
		{
			for(int i=0; i<2; i++) sc.nextLine();
			
			logic = sc.nextLine();
		}
		
		else if(brickNum==8)
		{
			for(int i=0; i<8; i++) sc.nextLine();
			
			logic = sc.nextLine();
		}
		
		
		correct.setBounds(width/2 - 75, (int) (height*0.005), 150, 100);
		add(correct);
		
		font = new Font("TimesRoman", Font.PLAIN, 20);	
		info.setFont(font);
		info.setText(logic);
		info.setBounds(width/2 - 200, (int) (height*0.15), 400, 100);
		add(info);
	
		getFact();
		
		frame.setVisible(true);
		
		waitForButton();

	}
	
	public void getFact()
	{
		getFile();
		
		for(int i=0; i<randomFact-1; i++) sc.nextLine();
		
		fact.setFont(font);
		fact.setText(sc.nextLine());
		fact.setBounds(width/2 - 200, (int) (height*0.5), 400, 300);
		add(fact);
		
		font = new Font("TimesRoman", Font.PLAIN + Font.ITALIC, 30);
		factHead = new JLabel("Fact");
		factHead.setFont(font);
		factHead.setBounds(width/2 - 30, (int) (height*0.2), 60, 300);
		add(factHead);
		
		
	}

	public void waitForButton()
	{
		while(true)
		{
			if(ok) break;
		}
		
		try 
		{
			Thread.sleep(1500);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

	}


	public void actionPerformed(ActionEvent e)
	{
		ok = true;
		frame.dispose();
	}

	public void setMenu(MenuCardLayout mcl){this.mcl = mcl; }
	public void setBall(Ball ball)
	{
		this.ball = ball;
		getInfo();
	}
}
