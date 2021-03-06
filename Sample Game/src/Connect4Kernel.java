import java.awt.*;


import java.awt.image.*;


import java.net.*;


import java.applet.*;





class Connect4Kernel


{


   public final static int EMPTY         =  0;


   public final static int COMP          =  1;


   public final static int HUMAN         = -1;


   public final static int BORDER        = 99;





   public final static int GAME_NOT_OVER =  0;


   public final static int ONE_HAS_WON   =  1;


   public final static int BOARD_FULL    = -1;





   public final static int UNDECIDED     =  0;


   public final static int PLAYER_WINS   =  1;


   public final static int PLAYER_LOOSES = -1;





   private int             board[]       = new int[156];


   private int             field[]       = new int[13];





   private int             maxDepth;


   private Connnect4Kernel        c4;





   private int             cnt;


   public  int             Cnt() { return cnt; }


   


   // ------------------------------------------------------------------------


   private boolean _IsEmpty(int column)


   {


      if(field[column]<114) return true; 


      else                  return false;


   }





   // ------------------------------------------------------------------------


   private int _DoMove(int player, int column)


   {


      int f          = field[column];


      board[f]       = player;


      field[column] += 13;


      return f;


   }





   // ------------------------------------------------------------------------


   private int _BestMove(int player, int move[], int depth)


   {


      int         NO_MORE_SEARCH = 10; // final!





      int         res            = PLAYER_LOOSES;


      int         rivals_move[]  = new int[1];


      int         i;


      int         curr_field,


                  curr_column;


      int         value[]        = new int[1];


      int         max_value      = -1;





      for(move[0]=-1, depth--, i=3, value[0]=0 ; i<NO_MORE_SEARCH ; i++, cnt++)


      {


         if(_IsEmpty(i))


         {


            if(move[0] == -1) move[0] = i;





            curr_field = _DoMove(player, curr_column = i);





            switch(GameOver(curr_field, value))


            {


               case ONE_HAS_WON:





                  move[0] = i;


                  i       = NO_MORE_SEARCH;


                  res     = PLAYER_WINS;


                  break;





               case BOARD_FULL:





                  if(res == PLAYER_LOOSES)


                  {


                     move[0] = i;


                     res     = UNDECIDED;


                     i       = NO_MORE_SEARCH; // Da das Brett jetzt voll ist, war dies eh das letzte Feld


                  }


                  break;





               case GAME_NOT_OVER:


               default:





                  if(depth!=0)


                  { 


                     switch(_BestMove(player == HUMAN ? COMP : HUMAN, rivals_move, depth))


                     {


                        case PLAYER_LOOSES:


   


                           if(res!=PLAYER_WINS || value[0]>max_value)


                           {


                              move[0]   = i;


                              res       = PLAYER_WINS;


                              max_value = value[0];


                           }


                           break;


                  


                        case UNDECIDED:


  


                           if(res==PLAYER_LOOSES || (res==UNDECIDED && value[0]>max_value))


                           {


                              move[0]   = i;


                              res       = UNDECIDED;


                              max_value = value[0];


                           }


                           break;





                        case PLAYER_WINS:





                           // not a good move


                           if(res==PLAYER_LOOSES && value[0]>max_value)


                           {


                              move[0]   = i;


                              max_value = value[0];


                           }


                           break;


                     }


                  }


                  else


                  {


                     if(res==PLAYER_LOOSES && value[0]>max_value)


                     {


                        move[0]   = i;


                        res       = UNDECIDED;


                        max_value = value[0];


                     }                


                  }





                  break;


            }





            board[curr_field]  = EMPTY;


            field[curr_column]   -= 13;


         }





         if(depth==maxDepth)


         {


            StringBuffer str = new StringBuffer(30);


            str.append("Analyzing");


            for(int j=3 ; j<i ; j++)


               str.append(".");





            c4.DrawStatus(str.toString());


         }


      }





      return res;


   }





   // ------------------------------------------------------------------------


   private int _Field(int l, int r) { return l*13+r; }





   // ------------------------------------------------------------------------


   private int _Column(int f) { return f%13; }





   // ------------------------------------------------------------------------


   private int _Line(int f) { return f/13; }





   // ------------------------------------------------------------------------


   public Connect4Kernel(Connnect4Kernel _c4)


   {


      int i;





      c4 = _c4;





      for(i=0 ; i<156 ; i++)


         if(_Column(i)>2 && _Column(i)<10 && _Line(i)>2 && _Line(i)<9)


            board[i] = EMPTY;


         else


            board[i] = BORDER;





      for(i=0 ; i<13 ; i++)


         field[i] = 3*13+i;


   }





   // -----------------------------------------


   public boolean IsEmpty(int column)


   {


      if(column>=0 && column<7) return _IsEmpty(column+3);


      else                      return false;


   }





   // -----------------------------------------


   public int GameOver(int f, int value[])


   {


      int comp  = board[f];


      int rival;


      int i;


      int u, d, l, r, ul, ur, dl, dr;


      int cnt0,  cnt1,  cnt2,  cnt3;


      int cnt0b, cnt1b, cnt2b, cnt3b;


      int f0a, f0b, f1a, f1b, f2a, f2b, f3a, f3b;


      int f0c, f0d, f1c, f1d, f2c, f2d, f3c, f3d;


      int tmp;





      cnt0  = cnt1  = cnt2  = cnt3  = 1;


      cnt0b = cnt1b = cnt2b = cnt3b = 0;


      u = d = l = r = ul = ur = dl = dr = f;


      f0a = f0b = f0c = f0d = f1a = f1b = f1c = f1d = f2a = f2b = f2c = f2d = f3a = f3b = f3c = f3d = 1;





      if(comp!=EMPTY && comp!=BORDER)


      {


         rival = board[f] == HUMAN ? COMP : HUMAN;;





         for(i=1 ; i<4 ; i++)


         {


            // horizontal


            if(f0c!=0)


            {


               if((tmp = board[++r]) != BORDER)            


                  if(f0a!=0)


                     if(tmp==comp) cnt0++;


                     else


                     {


                        f0a = 0;


                        if(tmp==EMPTY) cnt0b++;


                        else           f0c = 0;


                     }


                  else


                     if(tmp!=rival) cnt0b++;


                     else           f0c = 0;


               else


                  f0c = 0;


            }


            if(f0d!=0)


            {


               if((tmp = board[--l]) != BORDER)            


                  if(f0b!=0)


                     if(tmp==comp) cnt0++;


                     else


                     {


                     f0b = 0;


                        if(tmp==EMPTY) cnt0b++;


                        else           f0d = 0;


                     }


                  else


                     if(tmp!=rival) cnt0b++;


                     else           f0d = 0;


               else


                  f0d = 0;


            }





            // links unten -> rechts oben


            if(f1c!=0)


            {


               if((tmp = board[ur+=14]) != BORDER)            


                  if(f1a!=0)


                     if(tmp==comp) cnt1++;


                     else


                     {


                        f1a = 0;


                        if(tmp==EMPTY) cnt1b++;


                        else           f1c = 0;


                     }


                  else


                     if(tmp!=rival) cnt1b++;


                     else           f1c = 0;


               else


                  f1c = 0;


            }


            if(f1d!=0)


            {


               if((tmp = board[dl-=14]) != BORDER)            


                  if(f1b!=0)


                     if(tmp==comp) cnt1++;


                     else


                     {


                        f1b = 0;


                        if(tmp==EMPTY) cnt1b++;


                        else           f1d = 0;


                     }


                  else


                     if(tmp!=rival) cnt1b++;


                     else           f1d = 0;


               else


                  f1d = 0;


            }





            // links oben -> rechts unten


            if(f2c!=0)


            {


               if((tmp = board[ul+=12]) != BORDER)            


                  if(f2a!=0)


                     if(tmp==comp) cnt2++;


                     else


                     {


                        f2a = 0;


                        if(tmp==EMPTY) cnt2b++;


                        else           f2c = 0;


                     }


                  else


                     if(tmp!=rival) cnt2b++;


                     else           f2c = 0;


               else


                  f2c = 0;


            }


            if(f2d!=0)


            {


               if((tmp = board[dr-=12]) != BORDER)            


                  if(f2b!=0)


                     if(tmp==comp) cnt2++;


                     else


                     {


                        f2b = 0;


                        if(tmp==EMPTY) cnt2b++;


                        else           f2d = 0;


                     }


                  else


                     if(tmp!=rival) cnt2b++;


                     else           f2d = 0;


               else


                  f2d = 0;


            }





            // vertikal


            if(f3c!=0)


            {


               if((tmp = board[u+=13]) != BORDER)            


                  if(f3a!=0)


                     if(tmp==comp) cnt3++;


                     else


                     {


                        f3a = 0;


                        if(tmp==EMPTY) cnt3b++;


                        else           f3c = 0;


                     }


                  else


                     if(tmp!=rival) cnt3b++;


                     else           f3c = 0;


               else


                  f3c = 0;


            }


            if(f3d!=0)


            {


               if((tmp = board[d-=13]) != BORDER)            


                  if(f3b!=0)


                     if(tmp==comp) cnt3++;


                     else


                     {


                        f3b = 0;


                        if(tmp==EMPTY) cnt3b++;


                        else           f3d = 0;


                     }


                  else


                     if(tmp!=rival) cnt3b++;


                     else           f3d = 0;


               else


                  f3d = 0;


            }


         }





         // Wertigkeit des Zuges bestimmen


         value[0] = 0;


         if((cnt0b + cnt0)>3)


            value[0]  = (cnt0-1)*6 + cnt0b;


         if((cnt1b + cnt1)>3)


            value[0] += (cnt1-1)*6 + cnt1b;


         if((cnt2b + cnt2)>3)


            value[0] += (cnt2-1)*6 + cnt2b;


         if((cnt3b + cnt3)>3)


            value[0] += (cnt3-1)*6 + cnt3b;





         if(cnt0>3 || cnt1>3 ||cnt2>3 ||cnt3>3)


            return ONE_HAS_WON;





         for(i=3 ; i<10 ; i++)


            if(field[i]<156)


               return GAME_NOT_OVER;





         return BOARD_FULL;


      }





      return GAME_NOT_OVER;


   }





   // ------------------------------------------------------------------------


   public int DoMove(int player, int column)


   {


      int f = -1;


      if(IsEmpty(column))


         f = _DoMove(player, column+3);


      return f;


   }





   // ------------------------------------------------------------------------


   public int BestMove(int player, int move[], int depth)


   {


      maxDepth = depth-1;


      cnt      = 0;





      int res = _BestMove(player, move, depth);


      move[0] -= 3;





      return res;


   }


 


   // ------------------------------------------------------------------------


   public int Field(int l, int r) 


   {


      return (l+3)*13+r+3;


   }





   // ------------------------------------------------------------------------


   public int Column(int f)  { return _Column(f) -3; }





   // ------------------------------------------------------------------------


   public int Line(int f)  { return _Line(f)-3; }





   // ------------------------------------------------------------------------


   public char F(int l, int r)


   {


      switch(board[Field(l,r)])


      {


         case EMPTY:  return ' ';


         case COMP:   return 'X';


         default:     return '0';


      }


   }





   // ------------------------------------------------------------------------


   public int FieldState(int l, int r)


   {


      return board[Field(l,r)];


   }


};








// ===========================================================================





public class Connnect4Kernel extends Applet


{


   // ------------------------------------------------------------------------


   public final static int WAITING_FOR_MOVE  = 0;


   public final static int ANALYZING         = 1;


   public final static int RESTART           = 2;





   public final static int BORDER_WIDTH      = 5;





   // ------------------------------------------------------------------------


   Connect4Kernel c4k;





   int            currField;


   int            level;


   int            bestMove[]           = new int[1];


   int            res                  = Connect4Kernel.UNDECIDED;


   int            value[]              = new int[1];


   int            changeCnt            = 0;


   boolean        levelEverDecreased   = false;


   int            lastCompArrowColumn  = -1;


   int            lastHumanArrowColumn = -1;


   boolean        lastHumanArrowEmpty  = false;





   int            currFontSize         = 0;


   Font           font;


   StringBuffer   currStatus;





   Graphics       g                    = null;


   Panel          panel                = null;


   int            mode                 = -1;





   // ------------------------------------------------------------------------


   public void init()


   {


      c4k                  = new Connect4Kernel(this);





      bestMove[0]          = c4k.Field(0,0);


      value[0]             = 0;





      level                = 4;


      levelEverDecreased   = false;


      changeCnt            = 0;





      lastHumanArrowColumn = -1;


      lastCompArrowColumn  = -1;


      lastHumanArrowEmpty  = true;





      currStatus           = new StringBuffer("Start your game!");





      if(g==null)


         g = getGraphics();





      SetMode(WAITING_FOR_MOVE);


   }





   // ------------------------------------------------------------------------


   public void SetMode(int m) { mode = m; }





   // ------------------------------------------------------------------------


   public boolean IsMode(int m) { return mode==m; }





   // ------------------------------------------------------------------------


   public void DrawStatus(String str)


   {


      Dimension d  = size();


      int       dx = (d.width - 2*BORDER_WIDTH) / 7;


      int       dy = d.height / 8;


      int       i;


      Font      f;





      // clear status display area


      g.setColor(Color.white);


      g.fillRect(BORDER_WIDTH, 7*dy+1, 7*dx, dy-BORDER_WIDTH-1);





      if(currFontSize!=dy)


      {


         currFontSize = dy;


         font = new Font("Dialog", 0, (dy*5)/10);


      }





      g.setFont(font);





      currStatus = new StringBuffer(str);





      g.setColor(Color.black);


      g.drawString(currStatus.toString(), BORDER_WIDTH + 10, 7*dy+(dy*5)/8);





      for(i=4 ; i<9 && i<=level ; i++)


      {


         g.setColor(Color.green);


         g.fillRect(7*dx-BORDER_WIDTH-20, 8*dy-BORDER_WIDTH-(i-3)*4-1, 22, 3);         


      }


   }





   // ------------------------------------------------------------------------


   public void DrawChip(int c, int r)


   {


      Color color;





      Dimension d = size();


      int dx = (d.width - 2*BORDER_WIDTH) / 7;


      int dy = d.height / 8;


      int xk = BORDER_WIDTH + dx*c + dx/8;


      int yk = d.height - (r+2)*dy + dy/8;


      int xs = (dx*3)/4;


      int ys = (dy*3)/4;





      switch(c4k.FieldState(r, c))


      {


         case Connect4Kernel.COMP:


            color = Color.green;


            break;


         case Connect4Kernel.HUMAN:


            color = Color.red;


            break;


         case Connect4Kernel.EMPTY:


         default:


            color = Color.white;


            break;


      }





      g.setColor(color);


      g.fillOval(xk, yk, xs, ys);


//      g.setColor(Color.black);


//      g.drawOval(xk, yk, xs, ys);


   }





   // ------------------------------------------------------------------------


   public void paint(Graphics g)


   {


      Dimension d  = size();


      int       dx = (d.width - 2*BORDER_WIDTH) / 7;


      int       dy = d.height / 8;


      int       r, c;





      // clear entire display area


      g.setColor(Color.white);


      g.fillRect(0, 0, 7*dx+2*BORDER_WIDTH-1, dy*8-1);





      g.setColor(Color.blue.darker().darker());


      g.fillRect(BORDER_WIDTH, dy, dx*7, dy*6);





      for(r = 0 ; r < 6 ; r++)


         for(c = 0 ; c < 7 ; c++)


            DrawChip(c, r);





      DrawStatus(currStatus.toString());





      g.setColor(Color.gray);


      for(r=0 ; r<BORDER_WIDTH ; r++)


      {


         g.drawLine(r, r, r, dy*8-r-1);


         g.drawLine(r+1, r, dx*7+2*BORDER_WIDTH-1, r);


      }


      g.setColor(Color.darkGray);


      for(r=0 ; r<BORDER_WIDTH ; r++)


      {


         g.drawLine(dx*7-r+2*BORDER_WIDTH-1, r+1, dx*7-r+2*BORDER_WIDTH-1, dy*8-r-1);


         g.drawLine(r, dy*8-r-1, dx*7+2*BORDER_WIDTH-1, dy*8-r-1);


      }


   }





   // ------------------------------------------------------------------------


   public int DoMove(int player, int column)


   {


      int curr_field = c4k.DoMove(player, column);





      DrawChip(column, c4k.Line(curr_field));





      if(player==Connect4Kernel.HUMAN)


         lastCompArrowColumn  = -1;


      else


         lastHumanArrowColumn = -1;





      DrawArrows(column, player);





      return curr_field;


   }





   // ------------------------------------------------------------------------


   private void AdaptLevel()


   {


      if(c4k.Cnt()<6000)


      {


         if(++changeCnt == 2)


         {


            level++;


            changeCnt          = 0;


         }


      }


      else if(c4k.Cnt()>40000)


      {


         level--;


         changeCnt = 0;


         levelEverDecreased = true;


      }


      else


         changeCnt = 0;


   }





   // ------------------------------------------------------------------------


   public boolean mouseUp(Event evt, int x, int y)


   {


      int r;





      if(IsMode(RESTART))


      {


         init();


         repaint();


         return true;


      }





      SetMode(ANALYZING);





      game: if(c4k.GameOver(bestMove[0], value)==Connect4Kernel.GAME_NOT_OVER)


      {


         // get column by position of mouse click


         Dimension d = size();


         r = ((x-BORDER_WIDTH) * 7) / d.width;


         if(r<0 || r>6)


            break game;





         if(c4k.IsEmpty(r))


         {


            currField = DoMove(Connect4Kernel.HUMAN, r);





            switch(res = c4k.GameOver(currField, value))


            {


               case Connect4Kernel.BOARD_FULL:





                  DrawStatus("It ends in a tie! (click to restart)");


                  SetMode(RESTART);


                  break;





               case Connect4Kernel.ONE_HAS_WON:





                  DrawStatus("You won! (click to restart)");


                  SetMode(RESTART);


                  break game;





               case Connect4Kernel.GAME_NOT_OVER:


            


                  res = c4k.BestMove(Connect4Kernel.COMP, bestMove, level);


                  currField = DoMove(Connect4Kernel.COMP, bestMove[0]);





                  AdaptLevel();





                  switch(res)


                  {


                     case Connect4Kernel.PLAYER_LOOSES:





                        // DrawStatus("I could loose!");


                        DrawStatus("Your move!");


                        break;


                  


                     case Connect4Kernel.PLAYER_WINS:





                        if(c4k.GameOver(currField, value)!=Connect4Kernel.GAME_NOT_OVER)


                        {


                           DrawStatus("I won! (click to restart)");


                           SetMode(RESTART);


                           break game;


                        }


                        else


                        {


                          if(levelEverDecreased)


                             DrawStatus("Your move!");


                          else


                             DrawStatus("I'm going to win!");


                        }


                        break;





                     case Connect4Kernel.UNDECIDED:


                     default:


                        DrawStatus("Your move!");


                        break;


                  }





                  break;


            }


         }


//         else


//            DrawStatus("Column occupied");


      }





      if(IsMode(ANALYZING))


         SetMode(WAITING_FOR_MOVE);


      return true;


   }





   // ------------------------------------------------------------------------


   void DrawArrow(Graphics g, int dx, int dy, int c, Color color, boolean fill)


   {


      int       xa[] = new int[8];


      int       ya[] = new int[8];





      g.setColor(color);





      xa[0] = BORDER_WIDTH + c*dx+dx/2;      ya[0] = (dy*3)/4;


      xa[1] =                xa[0] + dx/5;   ya[1] = ya[0] - dy/4;


      xa[2] =                xa[0] + dx/10;  ya[2] = ya[1];


      xa[3] =                xa[2];          ya[3] = ya[0] - dy/2;


      xa[4] =                xa[0] - dx/10;  ya[4] = ya[3];


      xa[5] =                xa[4];          ya[5] = ya[1];


      xa[6] =                xa[0] - dx/5;   ya[6] = ya[1];


      xa[7] =                xa[0];          ya[7] = ya[0];





      if(fill)


         g.fillPolygon(xa, ya, 8);


      else


         g.drawPolygon(xa, ya, 8);


   }





   // ------------------------------------------------------------------------


   void DrawArrows(int column, int player)


   { 


      if(column>=0 && column<7)


      {


         boolean empty = c4k.IsEmpty(column);





         Color     color;


         Dimension d      = size(); 


         int       dx     = (d.width - 2*BORDER_WIDTH) / 7;


         int       dy     = d.height / 8;





         // clear arrow display area


         g.setColor(Color.white);


         g.fillRect(BORDER_WIDTH, BORDER_WIDTH, 7*dx, dy-BORDER_WIDTH);





         if(player==Connect4Kernel.HUMAN)


         {


            lastHumanArrowColumn = column;


            lastHumanArrowEmpty  = empty;


         }


         else if(player==Connect4Kernel.COMP)


            lastCompArrowColumn = column;


         else


         {


            lastHumanArrowColumn = -1;


            lastCompArrowColumn  = -1;


         }





         if(lastHumanArrowColumn!=-1)


         {


            if(lastHumanArrowEmpty)


               color = Color.red;


            else


               color = Color.gray;





            DrawArrow(g, dx, dy, lastHumanArrowColumn, color, true);


         }





         if(lastCompArrowColumn!=-1)


         {


            color = Color.green;


            DrawArrow(g, dx, dy, lastCompArrowColumn, color, lastCompArrowColumn!=lastHumanArrowColumn);


         }


      }


   }





   // ------------------------------------------------------------------------


   public boolean mouseMove(Event evt, int x, int y)


   {


      if(IsMode(WAITING_FOR_MOVE))


      {


         Dimension d = size();


         int       c;





         // examine column


         c = ((x-BORDER_WIDTH) * 7) / d.width;





         if(c!=lastHumanArrowColumn)


            DrawArrows(c, Connect4Kernel.HUMAN);


      }





      return true;


   }





   // ------------------------------------------------------------------------


   public String getAppletInfo()


   {


      return "Connect4 - Java (beta) Applet by Sven Wiebus, Dec. 1995";


   }


}




