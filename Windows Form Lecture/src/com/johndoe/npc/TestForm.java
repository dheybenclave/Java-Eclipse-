package com.johndoe.npc;

import javax.swing.*;

import java.awt.event.*;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TestForm {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static Scanner input1 = new Scanner(System.in);
	JButton button,button1,button2;
	JTextField txtnum1,txtnum2;
	JLabel label,label1,label2;
	JPanel panel;
	JTextArea textarea;
	JPasswordField txtpass;
	JRadioButton rdbplus, rdbminus, rdbtimes, rdbdivide;
	static int formsizex = 500;
	static int formsizey = 600;
	
	public static void main(String[] args) {
		CreateShowButton();		
	}

	public TestForm(){		
		label = new JLabel("Enter First Number : ");
		label1 = new JLabel("Enter Last Number : ");
		label2 = new JLabel("Reading text in files");
		button = new JButton("Click Me");
		button1 = new JButton("Create File");
		txtnum1 = new JTextField("");
		txtnum2 = new JTextField("");
		rdbplus = new JRadioButton("+");
		rdbminus = new JRadioButton("-");
		rdbtimes = new JRadioButton("*");
		rdbdivide = new JRadioButton("/");
		textarea = new JTextArea();
		txtpass = new JPasswordField();
		button2 = new JButton("Read File");
		
	}
	
	public void AddControlForm(Container container)
	{
		container.setLayout(null);
		
		label.setBounds(90,80,150,70);
		label1.setBounds(90,100,150,70);
		
		button.setBounds(290,160,100,40);
		button.addActionListener(new ButtonEventHandler());
		
		button1.setBounds(300,350,100,40);
		button1.addActionListener(new ButtonEventHandler());
			
		txtnum1.setBounds(200,105,200,20);
		txtnum1.setBackground(Color.DARK_GRAY);
		txtnum1.setForeground(Color.white);
		
		txtnum2.setBounds(200,130,200,20);
		txtnum2.setBackground(Color.DARK_GRAY);
		txtnum2.setForeground(Color.white);
		
		rdbplus.setBounds(110,170,40,20);
		rdbminus.setBounds(160,170,40,20);
		rdbtimes.setBounds(210,170,40,20);
		rdbdivide.setBounds(250,170,40,20);
		
		textarea.setBounds(50,220,400,100);
		
		txtpass.setBounds(50,340,100,20);
		
		label2.setBounds(50,360,300,100);
		button2.setBounds(300,460,100,40);

		container.add(rdbplus);
		container.add(rdbminus);
		container.add(rdbtimes);
		container.add(rdbdivide);

		container.add(label);
		container.add(label1);
		container.add(button);
		container.add(button1);
		container.add(txtnum1);
		container.add(txtnum2);
		container.add(textarea);
		container.add(txtpass);
		container.add(label2);
		container.add(button2);
	}
	
	public static void CreateShowButton()
	{
		JFrame frame = new JFrame("Form Title");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TestForm form = new TestForm();
		form.AddControlForm(frame.getContentPane());
		frame.setSize(formsizex,formsizey);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	
	}
	
	public void Compute()
	{	
		 int sum = 0;
		 int valtxt = Integer.valueOf(txtnum1.getText().toString());
		 int valtxt1 = Integer.valueOf(txtnum2.getText().toString());
		
		if(rdbplus.isSelected())
			sum = valtxt + valtxt1;
		else if(rdbminus.isSelected())
			sum = valtxt - valtxt1;
		else if(rdbtimes.isSelected())
			sum = valtxt * valtxt1;
		else if(rdbdivide.isSelected())
			sum = valtxt / valtxt1;
		
		
		JOptionPane.showMessageDialog(null," The total is : " +sum);	
	}
	private void WriteToFile(String filename)
	{
		String message = textarea.getText().toString();
		try
		{
			FileWriter fw =new FileWriter(filename+".txt");
			fw.append(message);
			fw.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	public void ReadFile(String path)
	{
		
	}
	public class ButtonEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource()==button)
				{
					Compute();
				}
			else if(e.getSource()==button1)
				{
					WriteToFile("D:\\Dheo Claveria's Documents\\hello");
				}		  
			
		}
	}

}

