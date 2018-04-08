package Game;


import Game.MainMenu;
import Utilities.Util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu {

	private Image background;
	private int sizeXb;
	private int sizeYb;
	private int posXb;
	private int posYb;
	
	public MainMenu()
	{
		sizeXb = 600;
		sizeYb = 480;
		posYb = 1;
		posXb = 1;
		
		
		
		
		background = Util.loadImage("src/New folder/Menubg.png");
		
		
		//background = Util.loadImage("bin/background.png");
	}
	public static void main(String[] args) 
	{
	
		final JFrame frame = new JFrame("Help Me !!!");
		JButton buttonStart = new JButton("Start");
		
		buttonStart.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PlayGame playgame = new PlayGame();
				frame.setVisible(false);
				
			
			}
			
		});
		
		buttonStart.setBounds(0,0,150,50);
		MainMenu menu = new MainMenu();
		loadImage li = menu.new loadImage();
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(230,250,150,50);
		panel.add(buttonStart);
		
		frame.add(panel);
		frame.add(li);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,480);
		frame.setLocation( 390, 100 );
		//frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		
	}
	private class loadImage extends Component
	{
		BufferedImage img = null;
		public loadImage() {
			try
			{
				img = ImageIO.read(new File("src/New folder/Menubg.png"));
			} catch (IOException e){}
		}
		public void paint(Graphics g)
		{
			
			
		
			g.drawImage(img, 0,0,600,480,null);
			
			Font f = new Font ("Lucida Fax", 50,68);
			
			g.setFont(f);
			g.setColor(Color.red);
			g.drawString("Help Me !!! " ,  150, 100);
		}
	}
	
	public static String ReadFile(String filename) throws IOException{
		Path path = Paths.get(filename);
	
		try(BufferedReader reader = Files.newBufferedReader(path)){
			
			String lines = null;
			while((lines = reader.readLine())!= null)
			{
			return lines;
			}
			return lines;
		}
	}
	
	public static void WriteFile(String filename,ArrayList<String> lines ) throws IOException{
		
		Path path = Paths.get(filename);
		
		try(BufferedWriter writer = Files.newBufferedWriter(path)){
			
			for(String line : lines)
			{
				writer.write(line);
				writer.newLine();
			}
		}
	}

}
