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
	String highscore;
	public static String readscore = "0";
	Image backgroundimage, bird;
	SoundEngine bgSound,deadSound,eatSound;
	int speed;
	
		public GameEngine()
		{
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
		//CreateObs();
	}
	
	private void update() {

			if(!GameOver)
			{
				
				repaint();
				CheckSnakeDirection();
				ChangeSnakeMovements();
				MoveSnake();
				SpeedSnake();
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
			posY = posY - 10;
			snake.Headpath = "src/pics/snake_up.png";
			break;
		case DOWN:
			posY = posY + 10;
			snake.Headpath= "src/pics/snake_down.png";
			break;
		case LEFT:
			posX = posX - 10;
			snake.Headpath = "src/pics/snake_right.png";
			break;
		case RIGHT:
			snake.Headpath = "src/pics/snake_left.png";
			posX = posX + 10;break;
		}
	}

	private void SpeedSnake()
	{	
			try {
				if(foodEaten == 0 )
				{
					speed = 65;
				}
				Thread.sleep(speed);				
				if((foodEaten - lastTen) == 2)
				{
					lastTen = foodEaten;
					speed = speed -=10 ; 
					
				}	
			} catch (InterruptedException e) {
				speed  =65;
				e.printStackTrace();
			}
	}
	
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		if(GameOver)
		{
			DrawBackground(g);
			birdforward =0;
			birdupdown=0;
			deadSound = new SoundEngine("src/sounds/die1.wav");
			deadSound.Play();		
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
			DrawBackground(g);
			g.setColor(Color.WHITE);
			Font stringf = new Font("Century Gothic", Font.BOLD, 50); 
			g.setFont(stringf);
			g.drawString("Foods eaten " +  foodEaten, 40,50);
			g.drawString(String.valueOf(birdforward), 40, 150);
			g.drawString(String.valueOf(birdupdown), 40, 250);
			try {
				g.drawString("High Score " +  PlayGame.ReadFile("C:\\Users\\Claveria\\Documents\\text.txt").toString(), 1000,50);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			snake.draw(g);
			
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
	int s ;
	int negw, negx;
	int cons = PlayGame.frame.getWidth();
	Random r =new  Random();
	int birdforward=0;
	int birdupdown =r.nextInt(2);
	String path ;

	private void DrawBackground(Graphics g)
	{
		try
		{
		
			backgroundimage = Util.LoadImage("src/pics/bgimage.png");
			bird = Util.LoadImage(path);			
			g.drawImage(backgroundimage,xcount,0,PlayGame.frame.getWidth(),PlayGame.frame.getHeight(),null);
			g.drawImage(backgroundimage,negx-2,0,PlayGame.frame.getWidth(),PlayGame.frame.getHeight(),null);
			g.drawImage(bird,birdforward,birdupdown,100,100,null);
		}
		catch(Exception sx)
		{
			sx.printStackTrace();
		}
		
	}
	private int lastTen = 0;
	
	public void backgrounds()
	{
		birdforward = birdforward + 10;
	
		if(birdforward % 20 == 0)
		{
			birdupdown -= 20;
			path = "src/pics/UP.png";
		}
		else
		{birdupdown+=20;path = "src/pics/DOWN.png";}

		if(birdforward == cons)
		{
			birdforward = 0;
			birdupdown = r.nextInt(500+50);
			
		}
		

	}
	@Override
	public void run() {

		negw = PlayGame.frame.getWidth();
		negx = PlayGame.frame.getWidth()+1;
		try{
			
			bgSound = new SoundEngine("src/sounds/alert.wav");
			bgSound.Loop();
			while(true)
			{
				
				xcount = xcount-2;
				negx = negx-2;
				
				if(xcount == -negw)
				{
					negx = cons;
					xcount = 0;
				}
				backgrounds();
				update();	
				SpeedSnake();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	

}






