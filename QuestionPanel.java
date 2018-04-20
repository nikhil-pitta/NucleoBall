package nucleoBall;

import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class QuestionPanel extends JPanel implements ActionListener
{
	private JFrame frame;
	private int time;
	private ButtonGroup buttonG;
	private JRadioButton op1, op2, op3, op4;
	private int width, height;
	private JTextArea question;
	private File inFile;
	private Scanner sc;
	private String answer;
	private String o1, o2, o3, o4;
	private String quest;
	private Font font;

	private MenuCardLayout mcl;
	private Ball ball;
	private boolean correct;

	private int randomNumber;

	public QuestionPanel()
	{
		setLayout(null);

		randomNumber = (int)(Math.random()*3 + 1);
		randomNumber = -8 + randomNumber*9;

		width = 500;
		height = 700;

		frame = new JFrame();		
		frame.setSize(width, height);

		time = 5000;
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.getContentPane().add(this);
		setComponents();
		getQuestion();



	}

	public void getQuestion()
	{
		inFile = new File("src//nucleoball//Questions.txt");

		try
		{
			sc = new Scanner(inFile);
		}
		catch (FileNotFoundException e)
		{
			System.err.println("The file Questions.txt cannot be found");
			System.exit(1);
		}

		//some random stuff to find question
		for(int i=0; i<randomNumber-1; i++)
		{
			sc.nextLine();
		}

		font = new Font("TimesRoman", Font.ITALIC + Font.BOLD, 20);

		quest = sc.nextLine();

		question.setEditable(false);  
		question.setCursor(null);  
		question.setOpaque(false);  
		question.setFocusable(false);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(font);
		question.setText(quest);

		sc.nextLine();

		o1 = sc.nextLine();
		o2 = sc.nextLine();
		o3 = sc.nextLine();
		o4 = sc.nextLine();


		op1.setText(o1);
		op1.setActionCommand(o1);
		op1.addActionListener(this);

		op2.setText(o2);
		op2.setActionCommand(o2);
		op2.addActionListener(this);

		op3.setText(o3);
		op3.setActionCommand(o3);
		op3.addActionListener(this);

		op4.setText(o4);
		op4.setActionCommand(o4);
		op4.addActionListener(this);

		sc.nextLine();

		answer = sc.nextLine();

	}

	public void setComponents()
	{
		question = new JTextArea();
		buttonG = new ButtonGroup();

		op1 = new JRadioButton();
		op2 = new JRadioButton();
		op3 = new JRadioButton();
		op4 = new JRadioButton();

		op1.setBounds((int)(width/2) - 50, (int)(height*0.1), 100, 60);
		op2.setBounds((int)(width/2) - 50, (int)(height*0.3), 100, 60);
		op3.setBounds((int)(width/2) - 50, (int)(height*0.5), 100, 60);
		op4.setBounds((int)(width/2) - 50, (int)(height*0.7), 100, 60);

		buttonG.add(op1);
		buttonG.add(op2);
		buttonG.add(op3);
		buttonG.add(op4);

		question.setBounds(width/2 - 200, (int)(height*0.015), 400, 50);
		add(question);

		add(op1);
		add(op2);
		add(op3);
		add(op4);


	}

	public void waitAndCheckAnswer()
	{
		try 
		{
			Thread.sleep(time);
			frame.dispose();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}

		if(correct) frame.dispose();

		else 
		{
			frame.dispose();
			ball.gameOver();
		}
	}

	public void makeVisible()
	{
		frame.setVisible(true);
		waitAndCheckAnswer();
	}

	public void actionPerformed(ActionEvent e)
	{
		//System.out.println("hi");

		if(!e.getActionCommand().equals(answer))
		{
			correct = false;
		}

		else
		{			
			correct = true;
		}

	}

	public void setMenu(MenuCardLayout mcl){this.mcl = mcl; }
	public void setBall(Ball ball) {this.ball = ball;}
}
