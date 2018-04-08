package com_npc_tank_entities;

import java.awt.Graphics;
import java.awt.Image;

import com_npc_tank.PlayGame;
import com_npc_tank_utilities.Util;

public class Tank {
	
	 public static int tankX = 230;
	 public static int tankY = 600;
	public String path;
	private Image tankbody;
	public Tank()
	{
	
	}
	
	public void draw(Graphics g)
	{
		tankbody = Util.LoadImage(path);
		g.drawImage(tankbody,tankX,tankY,120,120,null);
	}
	

}
