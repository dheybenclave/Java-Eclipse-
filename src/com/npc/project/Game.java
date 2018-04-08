package com.npc.project;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.*;


public class Game{

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static Scanner input1 = new Scanner(System.in);
	JButton button,button1,button2;
	JTextField txtnum1,txtnum2;
	JLabel label,label1,label2;
	JPanel panel;
	JTextArea textarea;
	JPasswordField txtpass;
	JRadioButton rdbplus, rdbminus, rdbtimes, rdbdivide;
	static int formsizex = 1000;
	static int formsizey = 600;
	
	public static void main(String[] args) {

		CreateShowButton();		
	}

	public Game(){	
		panel = new JPanel();
		label = new JLabel("Enter First Number : ");
		button = new JButton("Click Me");
		txtnum1 = new JTextField("");
		
		
	}
	
	public void AddControlForm(Container container)
	{
		
		container.setLayout(null);
		
		label.setBounds(50,400,250,150);
		label.setForeground(Color.white);
		label.setFont(Font.getFont("Century Gothic"));
		
		button.setBounds(290,160,100,40);
		button.addActionListener(new ButtonEventHandler());
			
		txtnum1.setBounds(70,400,500,50);
		txtnum1.setBackground(Color.WHITE);
		txtnum1.setForeground(Color.DARK_GRAY);
		txtnum1.setFont(Font.getFont("Century Gothic"));
		
		panel.setBounds(0,0,this.formsizex,this.formsizey);
		panel.setBackground(Color.DARK_GRAY);
		panel.createImage(formsizex, formsizey);
		container.add(panel);
		container.add(label);
		container.add(button);
		container.add(txtnum1);

	}
	
	public static void CreateShowButton()
	{
		JFrame frame = new JFrame("Hula o Huli !");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game form = new Game();
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

	
	public class ButtonEventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
/*		if(e.getSource()==button)
		{
					Compute();
		}
		else if(e.getSource()==button1)
		{
					WriteToFile("D:\\Dheo Claveria's Documents\\hello");
		}	
*/	  
			JOptionPane.showMessageDialog(null,"HELLO");
		}
	}

}