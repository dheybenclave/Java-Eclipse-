/******************************************************************************
* File : Tetris1.java
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
 
class Tetris6 extends javax.swing.JPanel
{
  public void init()
  {
    this.setPreferredSize(new java.awt.Dimension(640,480));
    this.setBackground(java.awt.Color.GREEN);
  }
 
  public void drawCell(java.awt.Graphics gr, int x,int y)
  {
    gr.setColor(java.awt.Color.BLACK);
    gr.fillRect(x*24,y*24,24,24);
    gr.setColor(java.awt.Color.RED);
    gr.fillRect(x*24+1,y*24+1,22,22);
  }
 
  public void drawToken(java.awt.Graphics gr, int x, int y)
  {
    drawCell(gr,x+0,y+0);
    drawCell(gr,x+0,y+1);
    drawCell(gr,x+1,y+0);  
    drawCell(gr,x+2,y+0);    
  }
 
  public void paint(java.awt.Graphics gr)
  {
    super.paint(gr);
 
    drawToken(gr,0,0);
 
    drawToken(gr,5,10);
  }
 
  public static void main(String[] args) throws Exception
  {
    javax.swing.JFrame window = new javax.swing.JFrame("Macteki Tetris");
    window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
 
    Tetris6 tetris = new Tetris6();
    tetris.init();
 
    window.add(tetris);
    window.pack();
    window.setVisible(true);
  } 
}