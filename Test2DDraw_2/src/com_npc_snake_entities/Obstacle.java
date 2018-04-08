package com_npc_snake_entities;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
public class Obstacle {

	private Point ObstacleLocation;

	
	Rectangle block1,block2,block3,block4;
	int w1 = 0;
	int h1 = 0;
	
	public Obstacle(int x,int y ){
	
		ObstacleLocation= new Point(x,y );
	}
	public void SetObstacleSize(int w, int h)
	{
		w1 = w;
		h1 = h;
	}
	public Point GetObstacleSize(){
		return new Point(w1, h1);
	}
	
	public void SetObstacleLocation(int x, int y )
	{
		ObstacleLocation.setLocation(x,y);
	}
	
	public Point GetObstacleLocation()
	{
		return ObstacleLocation;
	}
	
	public void draw(Graphics g){
		
		g.setColor(Color.GREEN);
		g.drawRect((int)ObstacleLocation.getX(),(int)ObstacleLocation.getX(),w1,h1);
		g.setColor(Color.ORANGE);
		g.fillRect((int)ObstacleLocation.getX(),(int)ObstacleLocation.getX(),w1,h1);

	}
	
}