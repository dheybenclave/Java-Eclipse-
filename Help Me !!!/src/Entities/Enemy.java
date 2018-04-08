package Entities;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import Utilities.Util;

public class Enemy {
	private int posX;
	private int posY;
	private int sizeX = 32;
	private int sizeY = 32;
	private Image image = Util.loadImage("bin/New folder/shark.png");
	boolean alive;
	//private Image image = null;
	//private Direction direction;
	
	public Enemy(int x, int y){
		this.posX = x;
		this.posY = y;
		alive = true;
	}
	
	public void setX(int x){
		this.posX = x;
	}
	
	public void setY(int y){
		this.posY = y;
	}

	public void setAIImage(Image i){
		this.image = i;
	}
	public void setAIPosition(int x, int y) {
		posX += x;
		posY += y;
	}
	public int getX(){
		return this.posX;
	}
	
	public int getY(){
		return this.posY;
	}
	
	public int getSizeX(){
		return this.sizeX;
	}
	
	public int getSizeY(){
		return this.sizeY;
	}
	
	public Rectangle getRectangle(){
		Rectangle enemyRect = new Rectangle(posX,posY,sizeX,sizeY);
		return enemyRect;
	}
	public void setlife(boolean l)
	{
		this.alive = l;
	}
	public void draw(Graphics g){//Draws Terrain	
		if(alive)
		{
			posX--;
			g.drawImage(image, posX, posY, sizeX, sizeY, null);
		}
		if(!alive)
		{
			posX = -100000;
			posY = 0; 
		}
			
	}
}