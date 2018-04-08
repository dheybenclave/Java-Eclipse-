/******************************************************************************
* File : Tetris9.java
* Author : http://java.macteki.com/
* Description :
*   Step by Step walk-through for building the tetris game
* Tested with : JDK 1.6
******************************************************************************/
 
// Step 1 : draw a green background
// Step 2 : draw a red cell with black border
// Step 3 : create a drawCell method()
// Step 4 : draw a token
// Step 5 : move the factor 24 into drawCell(), remove "*24" from caller
// Step 6 : create a drawToken() method
// Step 7 : create a eraseCell() method, create an occupied array
// Step 8 : modify drawCell() so that only fill the occupied array,
//          move the actual drawing code to paint()
// Step 9 : modify drawToken() to accept an array of relative position
 
class Tetris9 extends javax.swing.JPanel
{
  int[][] occupied = new int[10][20];
 
  public void init()
  {
    this.setPreferredSize(new java.awt.Dimension(640,480));
    this.setBackground(java.awt.Color.GREEN);
 
    // array of relative position
    int[] xArray = { 0,0,1,2 };   
    int[] yArray = { 0,1,0,0 };
    drawToken(0,0,xArray, yArray);
 
    drawToken(5,10,xArray, yArray);
 
    eraseCell(5,10);
 
  }
 
  public void drawCell(int x,int y)
  {
    occupied[x][y] = 1;
  }
 
  public void eraseCell(int x,int y)
  {
    occupied[x][y] = 0;
  }
 
  public void drawToken(int x, int y, int[] xArray, int[] yArray)
  {
    for (int i=0;i<4;i++)
    {
      drawCell(x+xArray[i],y+yArray[i]);
    }
  }
 
  public void paint(java.awt.Graphics gr)
  {
    super.paint(gr);
 
    for (int x=0;x<10;x++)
      for (int y=0;y<20;y++)
        if (occupied[x][y]==1)
        {
          // draw cell
          gr.setColor(java.awt.Color.BLACK);
          gr.fillRect(x*28,y*28,28,28);
          gr.setColor(java.awt.Color.RED);
          gr.fillRect(x*24+1,y*24+1,22,22);
        }
        else
        {
          // erase cell
          gr.setColor(java.awt.Color.BLACK);
          gr.fillRect(x*24,y*24,24,24);
        }
  }
 
  public static void main(String[] args) throws Exception
  {
    javax.swing.JFrame window = new javax.swing.JFrame("Macteki Tetris");
    window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
 
    Tetris9 tetris = new Tetris9();
    tetris.init();
 
    window.add(tetris);
    window.pack();
    window.setVisible(true);
  } 
}