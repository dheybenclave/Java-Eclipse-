/******************************************************************************
* File : Tetris3.java
* Author : http://java.macteki.com/
* Description :
*   Step by Step walk-through for building the tetris game
* Tested with : JDK 1.6
******************************************************************************/
 
// Step 1 : draw a green background
// Step 2 : draw a red cell with black border
// Step 3 : create a drawCell method()
 
class Tetris3 extends javax.swing.JPanel
{
  public void init()
  {
    this.setPreferredSize(new java.awt.Dimension(640,480));
    this.setBackground(java.awt.Color.GREEN);
  }
 
  public void drawCell(java.awt.Graphics gr, int x,int y)
  {
    gr.setColor(java.awt.Color.BLACK);
    gr.fillRect(x,y,24,24);
    gr.setColor(java.awt.Color.RED);
    gr.fillRect(x+1,y+1,22,22);
  }
 
  public void paint(java.awt.Graphics gr)
  {
    super.paint(gr);
    drawCell(gr,0,0);
  }
 
  public static void main(String[] args) throws Exception
  {
    javax.swing.JFrame window = new javax.swing.JFrame("Macteki Tetris");
    window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
 
    Tetris3 tetris = new Tetris3();
    tetris.init();
 
    window.add(tetris);
    window.pack();
    window.setVisible(true);
  } 
}