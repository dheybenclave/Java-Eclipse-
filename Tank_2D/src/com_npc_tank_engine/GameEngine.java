package com_npc_tank_engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import com_npc_tank_utilities.Direction;
import com_npc_tank_utilities.Util;
import com_npc_tank_entities.Tank;
import com_npc_tank_utilities.Keyboard;
import com_npc_tank.PlayGame;



public class GameEngine extends JPanel implements Runnable {

	private Keyboard keys;
	private Tank tank;
	private boolean restrictUpDown= false;
	private boolean restrictLeftRight = false;
	private int posX,posY;
	private Direction tankDirection;
	private Image tankbody ,background1 ,background2;
	
	int xcount = 0;
	int negw, negx;
	int cons = PlayGame.frame.getWidth();
	
	public GameEngine()
	{
		keys = Keyboard.getInstance();
		Keyboard keyboard = Keyboard.getInstance();
		PlayGame.frame.addKeyListener(keyboard);
		initialize();
		new Thread(this).start();
	}
	
	private void initialize()
	{	
		tank = new Tank();
	}
	
	private void ChangeTankMovements() 
	{
		if(tankDirection == Direction.LEFT ||tankDirection == Direction.RIGHT)
		{
			restrictLeftRight = true;
			restrictUpDown = false;
		}
		else
		{
			restrictLeftRight = false;
			restrictUpDown = true;
		}
		
	}
	private void CheckTankDirection() {
		
		if(restrictLeftRight == false)
		{
			if(keys.isDown(KeyEvent.VK_LEFT))
			{
			tankDirection = Direction.LEFT;
		
			
			}
			else if(keys.isDown(KeyEvent.VK_RIGHT))
			{
				tankDirection = Direction.RIGHT;
		
			}
		}
		else if(restrictUpDown == false)
		{
			if(keys.isDown(KeyEvent.VK_UP))	
			{
			tankDirection = Direction.UP;
						}
			else if(keys.isDown(KeyEvent.VK_DOWN))
			{
				tankDirection = Direction.DOWN;
			
			}
		}
		else
		{
			
		}
	}
	
	private void MoveTank() {
		
		switch(tankDirection){
		case UP:
			tank.tankY = tank.tankY-10;
			System.out.print("asd");
			tank.path = "src/pics/tank_left.png";break;
		case DOWN:
			tank.tankY = tank.tankY+10;
			tank.path = "src/pics/tank_right.png";break;
		case LEFT:
			tank.tankX = tank.tankX-10;
			tank.path = "src/pics/tank_up.png";break;
		case RIGHT:
			tank.tankX = tank.tankX+10;
			tank.path = "src/pics/tank_down.png";break;
		}
	}
	
	public void update()
	{
		repaint();
	

	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		tank.path = "src/pics/tank_up.png";
		tank.draw(g);
	}
	private void DrawBackground(Graphics g)
	{
		try
		{
		
			background1 = Util.LoadImage("src/pics/bgimage.png");
			background2 = Util.LoadImage("src/pics/bgimage.png");
			
			g.drawImage(background1,xcount,0,PlayGame.frame.getWidth(),PlayGame.frame.getHeight(),null);
			g.drawImage(background2,negx-2,0,PlayGame.frame.getWidth(),PlayGame.frame.getHeight(),null);
		}
		catch(Exception sx)
		{
			sx.printStackTrace();
		}
		
	}
	
	


	@Override
	public void run() {
		// TODO Auto-generated method stub
		update();
	}
	
}
