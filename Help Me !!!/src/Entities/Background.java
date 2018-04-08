package Entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import Utilities.Util;

public class Background {

	Image backgroundImage = Util.loadImage("bin/New folder/water.png");
	private int sizeXb;
	private int sizeYb;
	private int posXb;
	private int posYb;
	
	public Background(){
		sizeXb = 600;
		sizeYb = 450;
		posYb = 1;
		posXb = 1; 
	}
	public void draw(Graphics g){
		g.drawImage(backgroundImage, posXb, posYb, sizeXb, sizeYb, null);
	}
	
}
