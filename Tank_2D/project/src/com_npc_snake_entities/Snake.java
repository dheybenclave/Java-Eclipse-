package com_npc_snake_entities;

import java.awt.*;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

import com_npc_snake.PlayGame;
import com_npc_snake_engine.GameEngine;
import com_npc_snake_utilities.Util;

public class Snake {
	
	private int size = 10;
	private int headlocation = 250;
	private static ArrayList<Point> snakebody;
	public boolean isFoodeaten = false;
	public boolean isDead = false;
	private Obstacle obstacle;

	public Snake(){
		
		snakebody  = new ArrayList<Point>();
		obstacle = new Obstacle(0,0);
		initSnakeBody();
	}
	
	
	private void initSnakeBody(){
		
		snakebody.add(new Point(headlocation,headlocation));
		for(int i = 0 ; i < 4; i++)
		{
			snakebody.add(new Point(headlocation,snakebody.get(i).getLocation().y-10));
		}
	}
	
	public void SetSnakeHead(int x, int y, int dx, int dy){
		
		try{
			
			snakebody.get(0).setLocation(x,y);
			for(int i = 1 ; i<snakebody.size();i++)
			{
				int tempX  =snakebody.get(i).getLocation().x;
				int tempY  =snakebody.get(i).getLocation().y;
				
				snakebody.get(i).setLocation(dx,dy);
				dx = tempX;
				dy = tempY;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	public void draw(Graphics g){
		
		SnakeHead = Util.LoadImage(Headpath);
		SnakeBody = Util.LoadImage(Bodypath);
		
		for(int i = 0 ; i < snakebody.size();i++)
		{
			
			//g.setColor(Color.BLACK);
			//g.fillRect((int)snakebody.get(i).getX(),(int)snakebody.get(i).getY(),size,size);
			
			if(i == 0)
			{
				g.drawImage(SnakeHead,(int)snakebody.get(i).getX(),(int)snakebody.get(i).getY(),size +10,size +10, null);
			}
			else
			{
				g.drawImage(SnakeBody,(int)snakebody.get(i).getX(),(int)snakebody.get(i).getY(),size +5,size +5, null);
			}
		}
	}
	
	public void growSnake(){
		
		snakebody.add(new Point(snakebody.get(snakebody.size()-1).getLocation().x,
								snakebody.get(snakebody.size()-1).getLocation().y));
		
	}
	
	public Point GetHead()
	{
		return new Point(snakebody.get(0).getLocation().x, 
				snakebody.get(0).getLocation().y);
	}	
	
	public boolean isEaten(Food f){
		
		if(snakebody.get(0).getLocation().x == f.GetFoodLocation().x &&
			snakebody.get(0).getLocation().y == f.GetFoodLocation().y)
			{
			isFoodeaten = true;	
			}
		
		return isFoodeaten;
	}
	

	public boolean isDead(){
	
		if(snakebody.get(0).getLocation().x+10 >= PlayGame.frame.getSize().width ||
					snakebody.get(0).getLocation().x  < 0 )
				{
					isDead = true;			


					
				} 
		if(snakebody.get(0).getLocation().y+10 >= PlayGame.frame.getSize().height ||
				   snakebody.get(0).getLocation().y  < 0 )
				{
					isDead = true;
				
				} 

		for(int i = 1 ; i < snakebody.size();i++)
		{
			if(snakebody.get(0).getLocation().x  == snakebody.get(i).getLocation().x &&
					snakebody.get(0).getLocation().y  == snakebody.get(i).getLocation().y)
			{
				isDead = true;
			}
		}
		if(snakebody.get(0).getLocation().x == obstacle.GetObstacleLocation().x  &&
				snakebody.get(0).getLocation().y == obstacle.GetObstacleLocation().y)
		{
			isDead = true;
		}
		
		return isDead;
	}
	public static String Headpath = "src/pics/snake_up.png";
	private String Bodypath = "src/pics/record.png";
	private Image SnakeHead, SnakeBody;
	
}







