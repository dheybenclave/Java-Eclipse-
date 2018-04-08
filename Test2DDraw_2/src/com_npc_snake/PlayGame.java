package com_npc_snake;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.*;

import com_npc_snake_engine.GameEngine;
import com_npc_snake_utilities.Keyboard;

public class PlayGame {

	public static final int width = 1500;
	public static final int height = 700;
	public static JFrame frame = new JFrame();
	public static int highscore = 0;
	public static void main(String[] args)
	{
		frame.setTitle("A Snake");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width,height);
		frame.setLocationRelativeTo(null);
		
		Keyboard keyboard = Keyboard.getInstance();
		frame.addKeyListener(keyboard);
		
		GameEngine gameengine =new GameEngine();
		frame.add(gameengine);
		

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
