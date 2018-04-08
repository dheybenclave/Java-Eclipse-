package com_npc_snake_entities;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com_npc_snake_utilities.Util;
public class Food {

	private Point FoodLocation;
	
	public Food(int x,int y ){
		
		FoodLocation = new Point(x,y);
		
	}
	public void SetFoodLocation(int x, int y )
	{
		FoodLocation.setLocation(x,y);
	}
	
	public Point GetFoodLocation()
	{
		return FoodLocation;
	}
	
	public void draw(Graphics g){
		
		foodpath = Util.LoadImage(Food);
		//g.setColor(Color.BLACK);
		//g.drawRect((int)FoodLocation.getX(),(int)FoodLocation.getX(),10, 10);
		//g.setColor(Color.RED);
		//g.fillRect((int)FoodLocation.getX(),(int)FoodLocation.getX(),10, 10);
		g.drawImage(foodpath,(int)FoodLocation.getX(),(int)FoodLocation.getX(),20, 20,null);
	}
	private String Food ="src/pics/shabu.png";
	private Image foodpath;
	
}
