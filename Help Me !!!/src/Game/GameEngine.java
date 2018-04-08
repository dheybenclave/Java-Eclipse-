package Game;
//Sir ayaw magReload ng bubble kapag di tumama sa shark, hanggang 3 lang tapos ayaw na
//Wala po akong High Score




import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

import javax.swing.JPanel;



import Entities.Background;
import Entities.Enemy;
import Entities.Character;
import Entities.Bubble;
import Utilities.Direction;
import Utilities.KeyBoard;



public class GameEngine extends JPanel implements  Runnable{

	private static final long serialVersionUID = 1L;
	public int enemysize=1;
	public static final int bubblesize=10;
	private int enemySpeed = 0;
	private int moveSpeed = 8;
	private int bubbleSpeed  = -10;
	private ArrayList<Enemy> enemy;
	Random rand = new Random();
	private Thread gameThread;
	private KeyBoard keys;
	private Character character;
	private ArrayList<Bubble> bubble;
	private Bubble bubble1;
	private Bubble bubble2;
	private Bubble bubble3;
	private int currBubble = 0;
	private int startingPosX = 0;
	private int startingPosY = 0;
	private int reloadTime = 10;
	private boolean readyToFire = false;
	Background background;
	private int recoil = 0;
	Rectangle leftborder = new Rectangle(1, 1);
	Rectangle rightborder = new Rectangle(1, 1);
	private int enemyspawnrest = 100;
	private int addlevel = 3;
	private boolean gameOver;
	private int life = 5;
	public static int score;
	public static String highscore;
	
	SoundEngine bgSound, killSound, hurtSound, deadSound;
	
	public void initEnemy(){
		for(int x = 0; x <= enemysize; x++){
			int d = rand.nextInt(PlayGame.WIDTH);
			int e =rand.nextInt(PlayGame.HEIGHT);
			enemy.add(new Enemy(d,e));
			//enemy.get(x).setY(-e);
			//enemy.get(x).setX(d);
		}
	}
	
	
	public void initBubble(){
		for(int x = 0; x < 60; x++){
			bubble.add(new Bubble(100,100));
			System.out.println("Hey");
		}
	}
	
	public void loadbubble()
	{
		bubble1 = new Bubble(0,100000);
		bubble2 = new Bubble(0,100000);
		bubble3 = new Bubble(0,100000);
	}
	public void fireBubble(){
		if(currBubble != -1)
			for(int x = 0; x <=  currBubble; x++){
					bubble.get(x).setBubblePosition(0, bubbleSpeed);
					

					
			}
		
	}
	
	public void drawLeftBorder(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(1, 1, 680, 1);
	}
	
	public void drawRightBorder(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(1, 440, 680, 1);
	}
	
	public void drawLastRightBorder(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(PlayGame.WIDTH-10,1, 20, PlayGame.HEIGHT);
	}
	
	public void startInvasion(){
		for(int x = 0; x < enemy.size(); x++){
			enemy.get(x).setAIPosition(0, enemySpeed);
		}
	}
	
	public GameEngine () {
		enemy = new ArrayList<Enemy>();
		keys = KeyBoard.getInstance();
		background = new Background();
		character = new Character();
	
		bubble = new ArrayList<Bubble>();
		gameThread = new Thread(this);
		
		this.requestFocus();
		gameThread.start(); 

		loadbubble();
		gameOver = false;

	}
	
	public void restart()
	{
		life = 5;
		score = 0;
		gameOver = false;
		enemysize = 0;
		character = new Character();
		enemy = new ArrayList<Enemy>();
	}
	
	public void updatehighscore() throws IOException
	{//  UPDATE SCORE -- TAWAGIN MO ANG METHOD NA TO SA update()
		try {

			FileReader f = new FileReader("bin/HighScore.txt");//DIRECTORY
		BufferedReader rd = new BufferedReader(f);
		highscore = rd.readLine();
		String text = String.valueOf(score);
		
		
		//if the score is higher than recorded then rewrite
		 if(Integer.parseInt(text) > Integer.parseInt(highscore))
		 {
			 FileWriter outFile = new FileWriter("bin/HighScore.txt");//DIRECTORY
			 PrintWriter out = new PrintWriter(outFile);
			 out.print(text);
			 out.close();
		 }
} 	catch (FileNotFoundException e) {}
	}
	
	public void moveCharacter(){
		leftborder.setBounds(1, 1, 680, 1);
		rightborder.setBounds(1, 440, 680, 1);
		
		if(!character.getBounds().intersects(leftborder)){
		if(keys.isDown(KeyEvent.VK_UP))
		{
			character.setCharacterPosition(-moveSpeed);
		}
		}
		if(!character.getBounds().intersects(rightborder)){
		if (keys.isDown(KeyEvent.VK_DOWN))
		{
			character.setCharacterPosition(moveSpeed);
		}
		}
		if (keys.isDown(KeyEvent.VK_SPACE) )
		{
			
				startingPosX = character.getX() + 10;
				startingPosY = character.getY();
				
				if(!bubble1.getactive() && recoil == 0)
				{
					bubble1.setactive(true);
					bubble1.setX(startingPosX);
					bubble1.setY(startingPosY);
					recoil = 10;
					killSound = new SoundEngine ("src/Pew.wav");
					killSound.play();
					//score++;
				}
				else
				if(!bubble2.getactive() && bubble1.getactive() && recoil == 0)
				{
				bubble2.setactive(true);
				bubble2.setX(startingPosX);
				bubble2.setY(startingPosY);
				recoil = 10;
				//score++;
				}
				else
				if(!bubble3.getactive() && bubble1.getactive() && bubble2.getactive() && recoil == 0)
				{
					bubble3.setactive(true);
					bubble3.setX(startingPosX);
					bubble3.setY(startingPosY);
					recoil = 10;
					//score++;
				}
			//}
		}
	}
	
	
	public void drawBorders(Graphics g) {
	
		g.setColor(Color.red);
		g.drawRect(1, 3, 1,440);
	}
	
	
	public void CharactersomethingLife()
	{
		for(int x=0;x<enemy.size();x++)
		{
			if(character.getX()+character.getSizeX()==enemy.get(x).getX()+ enemy.get(x).getSizeX())
			{
			enemy.get(x).setlife(false);
			life--;
			System.out.println("-1 LIFE");
			}
		}
		if(life <=0  ){
			gameOver = true;
			
			
			System.out.println("GAME OVER");
			//restart();
		}
	}
	
	private void printScore(Graphics g) {
	
			
			if (gameOver == true)
			{
				Font f = new Font("Berlin Sans FB",10,20);
				g.setColor(Color.red);
				g.setFont(f);
				
				g.drawString("GameOver",  245, 190);
				g.drawString("Score : " + score,  245, 210);
				g.drawString("Press Enter to Play Again", 170, 230);
				
				
			}
			else {

				Font f = new Font("Berlin Sans FB",10,20);
				g.setColor(Color.red);
				g.setFont(f);
				g.drawString("Score : " + score,  30, 40);
				g.drawString("High Score : " + highscore,  150, 40);
				g.drawString(" Life: " + life , 500, 40);
			}
		
		
	}
	
	
	protected void paintComponent(Graphics g){

		background.draw(g);
		drawEnemy(g);
		printScore(g);
		bubble1.draw(g);
		bubble2.draw(g);
		bubble3.draw(g);
		drawBorders(g);
		character.draw(g);
		drawLeftBorder(g);
		drawRightBorder(g);
		drawLastRightBorder(g);
		GetScore(g);
		

	}
	
	public void GetScore(Graphics g)
	{
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(String.valueOf(score));
		try {
			Font highs = new Font("Century Gothic", Font.BOLD, 25); 
			g.setFont(highs);
			g.setColor(Color.white);
			g.drawString("Highest Score  " +  MainMenu.ReadFile("src/New folder/score.txt").toString(),80,250);
		
			if(score > Integer.valueOf(MainMenu.ReadFile("src/New folder/score.txt")))
			{
				MainMenu.WriteFile("src/New folder/score.txt",lines);
			}
			else 
			{
				g.drawString("Highest Score " +  MainMenu.ReadFile("src/New folder/score.txt").toString(),80,250);	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	                               
	public void reloadBubble(){
		if(reloadTime != 0)
			reloadTime--;
		
		if(reloadTime == 0){
			readyToFire = true;
			reloadTime = 5;
		}
	}
	
	public void killEnemy()
	{
		for(int x = 0; x< enemy.size(); x++)
			{
				if(bubble1.getRectangle().intersects(enemy.get(x).getRectangle()))
				{
					enemy.get(x).setlife(false);
					System.out.println("Pew");
					bubble1.setactive(false);
					score++;
				}
				if(bubble2.getRectangle().intersects(enemy.get(x).getRectangle()))
				{
					enemy.get(x).setlife(false);
					System.out.println("Pew");
					bubble2.setactive(false);
					score++;
				}
				if(bubble3.getRectangle().intersects(enemy.get(x).getRectangle()))
				{
					enemy.get(x).setlife(false);
					System.out.println("Pew");
					bubble3.setactive(false);
					score++;
				}
			}
	}
	
	public void drawEnemy(Graphics g){
		for(int x = 0; x < enemy.size(); x++){
			enemy.get(x).draw(g);
		}
	}
	
	private void watchForReset() throws IOException {
        

        if (keys.isDown(KeyEvent.VK_ENTER)) {
        	if(gameOver){

	        		restart();
        	}
        }
    }
	

	public void update() {
		try {
			updatehighscore();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		enemyspawnrest--;
		System.out.println(enemyspawnrest);
		if(enemyspawnrest<0)
		{
			initEnemy();
			enemyspawnrest = 100;
			addlevel--;
			if(addlevel<0)
			{
				enemysize++; 
				addlevel = 3;
			}
		}
		reloadBubble();
		//startInvasion();
		moveCharacter();
		//fireBubble();
		if(recoil >0)
			recoil--;
		killEnemy();
		CharactersomethingLife();
		repaint(); 
	}	
	public void run() {
		try {
	        while (true) {
	        	if(!gameOver)
	        	{
	        		update();
	        		
	        		Thread.sleep(20);
	        	}
	        	else watchForReset();
	        	
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}