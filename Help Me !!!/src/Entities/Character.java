package Entities;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import Utilities.Util;

public class Character {
	private int posX;
	private int posY;
	private int sizeX ;
	private int sizeY ;
	private Image image = Util.loadImage("bin/New folder/fish.png");
	//private Direction direction;
	
	public Character(){
		
		sizeX = 50;
		sizeY = 50;
		
		
		this.posX =20;
		this.posY = 200;
	}
	
	public void setX(int x ){
		this.posX += x;
	}
	
	public void setY(int y){
		this.posY += y;
	}

	public void setAIImage(Image i){
		this.image = i;
	}
	public void setCharacterPosition(int x) {
		posY += x;
		//posY += y;
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
	
	public Rectangle getBounds() {
		return new Rectangle(this.posX,this.posY,this.sizeX,this.sizeY);
	}
	
	public Rectangle getRectangle(){
		Rectangle enemyRect = new Rectangle(posX,posY,sizeX,sizeY);
		return enemyRect;
	}
	public void setImage(Image image){
		this.image = image;
	}
	
	public void draw(Graphics g){//Draws Terrain	
		g.drawImage(image, posX, posY, sizeX, sizeY, null);
	}
}