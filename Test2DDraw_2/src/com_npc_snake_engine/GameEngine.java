package com_npc_snake_engine;

import com_npc_snake.PlayGame;
import com_npc_snake_entities.Food;
import com_npc_snake_entities.Obstacle;
import com_npc_snake_entities.Snake;
import com_npc_snake_utilities.Direction;
import com_npc_snake_utilities.Keyboard;
import com_npc_snake_utilities.Util;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class GameEngine extends JPanel implements Runnable {
	//dheoclaveriabscs3a
	private Keyboard keys;
	private Snake snake;
	private int posX,posY;
	private int oldX, oldY;
	public static int foodEaten  = 0;
	private boolean restrictUpDown= false;
	private boolean restrictLeftRight = false;
	private Direction snakeDirection;
	private Food food;
	private Obstacle obstacle;
	private static boolean GameOver = false;
	private static int speed = 70;
	String highscore;
	public static String readscore = "0";
	Image backgroundimage;
	SoundEngine bgSound,deadSound,eatSound;
	public GameEngine(){
		 keys = Keyboard.getInstance();
		 initialize();
		new Thread(this).start();
	}
	
	private void initialize(){
		
		GameOver = false;
		snake = new Snake();
		posX = oldX = posY = oldY = 250;
		foodEaten = 0;
		snakeDirection = Direction.DOWN;
		food = new Food(0,0);
		obstacle  = new Obstacle(0,0);

		ProduceFood();
		CreateObs();
	}
	
	private void update() {

			if(!GameOver)
			{
				repaint();
				CheckSnakeDirection();
				ChangeSnakeMovements();
				MoveSnake();
				snake.SetSnakeHead(posX, posY, oldX, oldY);
				oldX = posX;
				oldY = posY;
				if(snake.isEaten(food))
				{

					eatSound = new SoundEngine("src/sounds/hard_step1.wav");
					eatSound.Play();
					ProduceFood();	
					foodEaten++;
					snake.growSnake();
					snake.isFoodeaten = false;
				}
				if(snake.isDead())
				{
					GameOver = true;
					ArrayList<String> lines = new ArrayList<String>();
					lines.add(String.valueOf(GameEngine.foodEaten));
					try {
						if(foodEaten > Integer.valueOf(PlayGame.ReadFile("C:\\Users\\Claveria\\Documents\\text.txt")))
						{
							PlayGame.WriteFile("C:\\Users\\Claveria\\Documents\\text.txt",lines);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
					
			}
			if(GameOver && keys.isDown(KeyEvent.VK_ENTER))
			{
				speed  = 100;
				initialize();
				
			}
		
	}
	

	
	private void ChangeSnakeMovements() {
		if(snakeDirection == Direction.LEFT || snakeDirection == Direction.RIGHT)
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
	private void CheckSnakeDirection() {
			
			if(restrictLeftRight == false)
			{
				if(keys.isDown(KeyEvent.VK_LEFT))
				{
				snakeDirection = Direction.LEFT;
				}
				else if(keys.isDown(KeyEvent.VK_RIGHT))
				{
					snakeDirection = Direction.RIGHT;
				}
			}
			else if(restrictUpDown == false)
			{
				if(keys.isDown(KeyEvent.VK_UP))
				{
				snakeDirection = Direction.UP;
				}
				else if(keys.isDown(KeyEvent.VK_DOWN))
				{
					snakeDirection = Direction.DOWN;
				}
			}
			else
			{
				
			}
		}
	
	
	private void MoveSnake() {
		
		switch(snakeDirection){
		
		case UP:
			posY = posY - 10;break;
		case DOWN:
			posY = posY + 10;break;
		case LEFT:
			posX = posX - 10;break;
		case RIGHT:
			posX = posX + 10;break;
		}
	}

	
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		if(GameOver)
		{
			deadSound = new SoundEngine("src/sounds/die1.wav");
			deadSound.Play();
			DrawBackground(g);
			g.setColor(Color.RED);
			Font stringf = new Font("Century Gothic", Font.BOLD, 50); 
			g.setFont(stringf);
			g.drawString("You Dead", 40, 50);
			
			try {
				g.drawString("High Score " +  PlayGame.ReadFile("C:\\Users\\Claveria\\Documents\\text.txt").toString(), 1000,50);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
		}
		else
		{
			Font stringf = new Font("Century Gothic", Font.BOLD, 50); 
			g.setFont(stringf);
			g.drawString("Foods eaten " +  foodEaten, 40,50);
			try {
				g.drawString("High Score " +  PlayGame.ReadFile("C:\\Users\\Claveria\\Documents\\text.txt").toString(), 1000,50);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DrawBackground(g);
			snake.draw(g);
			g.setColor(Color.BLUE);
			food.draw(g);
			obstacle.draw(g);
			
			Rectangle r = new Rectangle(obstacle.GetObstacleLocation().x, 
										obstacle.GetObstacleLocation().y, 
										obstacle.GetObstacleSize().x,
										obstacle.GetObstacleSize().y);
			Rectangle r2 = new Rectangle(snake.GetHead().x, snake.GetHead().y, 40, 40);
			if (r.intersects(r2)) 
			{
				GameOver = true;
				System.out.println("Collided");
			}
			
		}
	}
	
	private int GetRandomPosition(int high, int low){
		
		return(int)(Math.floor(Math.random()* (1+high-low) + low)* 20);
	}
	
	private void ProduceFood(){
	
		int locX = GetRandomPosition(PlayGame.width/20-5, 1);
		int locY = GetRandomPosition(PlayGame.height/20-5, 1);
		
		food.SetFoodLocation(locY, locY);
	}
	private void CreateObs()
	{	
		int locX = GetRandomPosition(PlayGame.width/20-5, 1);
			int locY = GetRandomPosition(PlayGame.height/20-5, 1);
			Random r = new Random();
			obstacle.SetObstacleSize(40,r.nextInt(300));
			obstacle.SetObstacleLocation(locX, locY);
	}
	int xcount = 0;
	int negw, negx;
	int cons = PlayGame.frame.getWidth();
	private void DrawBackground(Graphics g)
	{
		try
		{
		
			backgroundimage = Util.LoadImage("src/pics/bgimage.png");
			g.drawString(String.valueOf(negw), 40, 40);
			
			g.drawImage(backgroundimage,xcount,0,PlayGame.frame.getWidth(),PlayGame.frame.getHeight(),null);
			g.drawImage(backgroundimage,negx-20,0,PlayGame.frame.getWidth(),PlayGame.frame.getHeight(),null);
			
		}
		catch(Exception sx)
		{
			sx.printStackTrace();
		}
		
	}
	private int lastTen = 0;
	@Override
	public void run() {

		negw = PlayGame.frame.getWidth();
		negx = PlayGame.frame.getWidth()+1;
		try{
			bgSound = new SoundEngine("src/sounds/alert.wav");
			bgSound.Loop();
			while(true)
			{

			xcount = xcount-20;
			negx = negx - 20;
			if(xcount == -negw)
			{
				negx = cons;
				xcount = 0;
			}
				update();
				Thread.sleep(speed);
				
				if((foodEaten - lastTen) == 5)
				{
					lastTen = foodEaten;
					speed-= 10 ; 
					
				}
			


			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	

}






