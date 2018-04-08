
public class Maze {
	private static int[][] maze = 
	     {{1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	      {0,0,1,0,0,0,0,0,0,0,0,0,0,1},
	      {1,0,1,1,1,1,1,0,1,1,1,1,1,1},
	      {1,0,0,0,0,0,0,0,0,0,0,0,0,1},
	      {1,1,1,0,1,1,1,0,1,0,1,1,1,1},
	      {1,0,1,0,1,0,0,0,0,0,1,0,1,1},
	      {1,0,1,1,1,0,1,0,1,1,1,0,1,1},
	      {1,0,0,0,0,0,1,0,0,0,0,0,1,1},
	      {1,1,1,0,1,1,1,1,1,1,1,1,1,1},
	      {1,0,1,0,1,0,0,0,0,0,0,0,1,1},
	      {1,0,1,0,0,0,1,0,1,1,1,0,1,1},
	      {1,0,1,0,1,1,1,0,1,0,1,0,1,1},
	      {1,0,1,0,0,1,1,0,1,0,1,0,0,1},
	      {1,1,1,1,1,1,1,1,1,1,1,1,3,1}};

	  public static void main(String[] args) {    
		  solve(1,0);
		  print();
	  }

	  private static boolean solve(int rowcounter, int colcounter) {

	    if (maze[rowcounter][colcounter] == 3) {
	      return true;
	    }

	    maze[rowcounter][colcounter] = 2;

	    if (available(rowcounter - 1, colcounter) && solve(rowcounter - 1, colcounter)) {
	      return true;
	    }
	    if (available(rowcounter + 1, colcounter) && solve(rowcounter + 1, colcounter)) {
	      return true;
	    }
	    if (available(rowcounter, colcounter - 1) && solve(rowcounter, colcounter - 1)) {
	      return true;
	    }
	    if (available(rowcounter, colcounter + 1) && solve(rowcounter, colcounter + 1)) {
	      return true;
	    }

	    maze[colcounter][colcounter] = 0;

	    return false;
	  }


	  private static boolean available(int row, int col) {
	    return row >= 0 && row < maze.length
	        && col >= 0 && col < maze[row].length
	        && (maze[row][col] == 0 || maze[row][col] == 3);
	  }

	  private static final String[] SYMBOLS = {" 0", "-1", " 1", " 2" };

	  private static void print(){
	    for(int row = 0; row < maze.length; ++row) {
	      for(int col = 0; col < maze[row].length; ++col) {
	        System.out.print(SYMBOLS[maze[row][col]]);
	      }
	      System.out.println();
	    }
	  }

}
