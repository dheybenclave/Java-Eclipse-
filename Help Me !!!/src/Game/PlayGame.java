package Game;



import javax.swing.JFrame;
import javax.swing.JPanel;

import Utilities.KeyBoard;



public class PlayGame {	
	
	     
		public static final int WIDTH = 600;
	    public static final int HEIGHT = 480;
	    
	    
	   
	    
	 
		public PlayGame( ){      
	    
final JFrame mainFrame = new JFrame( "Welcome" );
		
		mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		mainFrame.setVisible(true);
		mainFrame.setResizable( true );
		mainFrame.setLocation( 390, 100 );
		mainFrame.setSize(WIDTH,HEIGHT);
		
		
		
		KeyBoard keyboard = KeyBoard.getInstance();
	    mainFrame.addKeyListener(keyboard);
		
		GameEngine game = new GameEngine();
		mainFrame.add(game);
	    
	    
	        
		}
	   // }
}









