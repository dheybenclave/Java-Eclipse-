package Entities;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import Utilities.Util;

public class Bubble {
	private int posX;
	private int posY;
	private int sizeX = 20;
	private int sizeY = 20;
	private Image image = Util.loadImage("bin/New folder/Bubble.png");
	private boolean active;
	//private Image image = null;
	//private Direction direction;
	
	public Bubble(int x, int y){
		this.posX =x;
		this.posY = y;
		active = false;
	}
	

	public void setactive(boolean a ){
		this.active = a;
	}

	public boolean getactive(){
		return active;
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
	public void setBubblePosition(int x, int y) {
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
		Rectangle enemyRect = new Rectangle(posX,posY,sizeX+10,sizeY+20);
		return enemyRect;
	}
	
	public void draw(Graphics g){
		if(active)
		{
		posX+=10;
		if(posX<+30)
		{
			active = false;
		}
		g.drawImage(image, posX, posY, sizeX, sizeY, null);
		}
		if(!active)
		{
			posX = 100000;
		}
	}
}