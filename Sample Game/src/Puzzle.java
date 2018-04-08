



// PUZZLE.JAVA -  A simple class to put a jigsaw puzzle together.

//

// author Randy Reichle. Copyright (C) 1996 Randy Reichle 

// All Rights Reserved.

// 

// Description: Simple jigsaw puzzle from within Java applet, 

//              implements simple animation, comic book style

//              messages and can connect directly to a URL.

//  

// Usage Notes: Java 2.0 Beta 

//  

// Date       : 02/15/96 

//  

// Example of how to call the applet: 

//   <applet code="puzzle.class" width=513 height=248>

//     </applet>

//  

// LEGALESE: 

// Permission to use, copy, modify, and distribute this software 

// and its documentation for NON-COMMERCIAL or COMMERCIAL purposes and 

// without fee is hereby granted.  No express promises are made,

// as to the usability of this software for any purpose whatsoever.



import java.applet.Applet;

import java.awt.*;

import java.util.*;

import java.awt.image.*;

import java.net.*; 

import java.io.*; 





public class Puzzle extends Applet implements Runnable {



  Button

    start_button,
    

    instruction_button,

    rotate_button;



  Thread

    kicker = null;



  Image

    imgs[],

    waves[],

    talking,

    offScrImage;



  Graphics

    offScrGr;



  URL                          // connecting to URL requires using a web

    currURL = null;            // browser for connection to occur



  int

    pi,

    pi1,

    ro1 = 0,

    co1 = 0,

    ro2 = 0,

    co2 = 0,

    loaded=0,

    wa=0,

    wave=0,

    done,

    cheat=0,

    move=0,

    number=0,

    inst=0,

    starter=0,

    talk=0;





  int be[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,

              18,18,18,18,18,18,18,18,18,18,18,18,18,18,18, 18 };



  int b[]= { 25, 55,100, 55,173, 55,246, 55,319, 55,392, 55,465, 55,

             25,127,100,127,                        392,127,465,127,

             25,199,100,199,                        392,199,465,199,

        

              164, 126, 204, 126, 244, 126, 284, 126, 324, 126,

              164, 166, 204, 166, 244, 166, 284, 166, 324, 166,

              164, 206, 204, 206, 244, 206, 284, 206, 324, 206 };



  int ran[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };



  public void init() {

    try {

      currURL = new URL("http://www.javasoft.com/");       

      } catch (IOException e) {}



    randomize();



    if(loaded==0) {

      talking = getImage(getCodeBase(), "talk.gif");

      load_pieces(getImage(getCodeBase(), "pieces.gif"), this);

      load_wave(getImage(getCodeBase(), "wave.gif"), this);

      loaded=1;

      }

    offScrImage = createImage(513, 248);

    offScrGr = offScrImage.getGraphics();



    add(start_button = new Button("Restart"));

    add(instruction_button = new Button("Instructions"));

    add(rotate_button = new Button("Rotate"));

    }



  public void load_pieces(Image base_image, Component component) {

    imgs = new Image[19];

    for (int i=1; i<=19; i++) {

      ImageFilter crop = new CropImageFilter(0, 70*(i-1), 70, 70);

      imgs[i-1] = component.createImage(

            new FilteredImageSource(base_image.getSource(), crop));

      component.prepareImage(imgs[i-1], component);

      }

    }



  public void load_wave(Image base_image, Component component) {

    waves = new Image[11];

    for (int i=1; i<=11; i++) {

      ImageFilter crop = new CropImageFilter(0, 68*(i-1), 55, 68);

      waves[i-1] = component.createImage(

            new FilteredImageSource(base_image.getSource(), crop));

      component.prepareImage(waves[i-1], component);

      }

    }



  public void run() {

    Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

    while ( kicker != null ) {

      if(starter==0) { sleep(900); starter=1; }

      if(wave!=0) sleep(10);

      repaint();

      }

    }



  public boolean imageUpdate(Image img, int flags, int x, int y,

                                                       int w, int h) {

    return true;

    }



  public void update(Graphics g) {

    paint(g);

    }



  public void paint(Graphics g) {

    if(wave==0 && inst==0) {

      offScrGr.setColor(Color.blue);

      offScrGr.fillRect(0, 0, 513, 248);

      offScrGr.setColor(Color.red);

      offScrGr.drawRect( 154, 116, 201, 121); 



      for(int i=0; i<60; i+=2) {        // draw squares if piece is missing

        if(be[i/2] == 18) offScrGr.drawRect( b[i], b[i+1], 20, 20);

        }

      for(int i=0; i<60; i+=2) {                             // draw pieces

        if(be[i/2] != 18) {

          offScrGr.drawImage(imgs[be[i/2]], b[i]-24, b[i+1]-24, this);

          }

        }



      offScrGr.setColor(Color.orange);

      offScrGr.setFont(new Font("TimesRoman", Font.PLAIN, 24));

      offScrGr.drawString(" Waving Duke ",10,23);

      offScrGr.drawString(" Jigsaw Puzzle ",355,23);



      if(starter==0) {

        offScrGr.setColor(Color.white);

        offScrGr.fillRect( 155, 117, 200, 120); 

        offScrGr.setColor(Color.red);

        offScrGr.setFont(new Font("TimesRoman", Font.PLAIN, 14));

        offScrGr.drawString("Pieces are",230,150);

        offScrGr.drawString("Please be patient",210,220);

        offScrGr.setFont(new Font("TimesRoman", Font.PLAIN, 24));

        offScrGr.drawString("L O A D I N G",185,190);

        }



      done=0; check_if_done(g);



      if(cheat==1) {

        for(int i=0; i<15; i++) be[i]=18;

        for(int i=15; i<30; i++) { be[i]=i-15; cheat=0; }

        }

      }



    if(wave==1 && inst==0) duke_waving(g);



    if(move==1) offScrGr.drawImage(imgs[be[30]], co2, ro2, this);



    g.drawImage(offScrImage, 0, 0, this);

    }



  public void randomize() {

    int

      rnum,

      count = 0;



    for(;;) {

      rnum = (int)(15 * Math.random());

      for(int i=0; i<=14; i++) {

        if(rnum==ran[i]) { ran[i]=100; be[count]=rnum; count++; break; }

        }

      if(count==15) break;

      }



    for(int i=0; i<=14; i++) {

      if(be[i]==5) be[i]=15;

      if(be[i]==12) be[i]=16; 

      if(be[i]==2) be[i]=17;

      }



    }



  public void start() {

    if (kicker == null) { kicker = new Thread(this); kicker.start(); }

    }



  public void stop() {

    kicker = null;

    }



  public void sleep(int pause) {

    for(int i=0; i<1000; i++) { for(int j=0; j<pause; j++); }

    }



  public void restart() {

    if(inst==0) {

      for(int i=0; i<15; i++)

      ran[i]=i;

      randomize(); 

      for(int i=15; i<30; i++) be[i]=18;

      start_button.setLabel("Restart");

      number=0; wave=0;

      }

    }



  public void rotate() {

    if(move==1) {

      if(pi==15) { be[pi1]=5; pi=5; be[30]=5; move=0; be[30]=5; }

      if(pi==16) { be[pi1]=12; pi=12; be[30]=12; move=0; be[30]=5; }

      if(pi==17) { be[pi1]=2; pi=2; be[30]=2; move=0; be[30]=5; }

      }

    }



  public void instructions() {

    instruction_button.setLabel("Resume");

    offScrGr.setColor(Color.white);

    offScrGr.fillRect( 155, 117, 200, 120); 

    offScrGr.setColor(Color.black);

    offScrGr.setFont(new Font("TimesRoman", Font.PLAIN, 12));

    offScrGr.drawString("- Click puzzle piece you want to move.",160,135);

    offScrGr.drawString("- Click square to move piece to.",160,152);

    offScrGr.drawString("- After 5 moves, the 'Restart' button",160,169);

    offScrGr.drawString("changes to 'Solve'.",175,182);

    offScrGr.drawString("- if frustrated, click 'Solve' button",160,199);

    offScrGr.drawString("-To rotate piece, click piece and then",160,216);

    offScrGr.drawString("click 'Rotate' button.",175,229);

    inst=1; 

    }



  public void resume() {

    instruction_button.setLabel("Instructions");

    inst=0;

    for(int i=30; i<60; i+=2) {                    // show images on screen

      offScrGr.drawImage(imgs[be[i/2]], b[i]-24, b[i+1]-24, this);

      }

    }



  public void check_if_done(Graphics g) {

    for(int i=15; i<30; i++) {

      if(be[i]!=i-15) { done=1; break; }

      }

    if(done==0) {

      offScrGr.setColor(Color.magenta);

      offScrGr.setFont(new Font("TimesRoman", Font.PLAIN, 14));

      offScrGr.drawString("' Move Mouse Over DUKE '",175,105);

      start_button.setLabel("Restart");

      wave=1;

      }

    }



  public void duke_waving(Graphics g) {

    offScrGr.drawImage(waves[wa],160,156, this);

    if(talk==1) offScrGr.drawImage(talking,155,87,this);

    if(talk==2) {

      offScrGr.setColor(Color.blue);

      offScrGr.fillRect(155, 85, 200, 70);

      offScrGr.setColor(Color.red);

      offScrGr.drawRect( 154, 116, 201, 121); 

      for(int i=30; i<60; i+=2) {                // show images on screen

        offScrGr.drawImage(imgs[be[i/2]], b[i]-24, b[i+1]-24, this);

        }

      talk=0;

      offScrGr.setColor(Color.magenta);

      offScrGr.setFont(new Font("TimesRoman", Font.PLAIN, 14));

      offScrGr.drawString("' Move Mouse Over DUKE '",175,105);

      offScrGr.drawImage(waves[wa],160,156, this);

      }

    wa++; if (wa==10) wa=0;

    }



  public boolean handleEvent(Event evt) {



    if (evt.id == Event.MOUSE_UP && inst==0 && wave==0) {

      ro1 = evt.y;    co1 = evt.x;

      }



    if (evt.id == Event.MOUSE_DOWN && inst==0) {

      ro1 = evt.y;    co1 = evt.x;     



      if(wave==1) {

        if(co1>=222 && co1<=351 && ro1>=205 && ro1<=218 && wave==1) {

          getAppletContext().showDocument(currURL);

          }

        }



      if(wave==0) {

        for(int i=0; i<60; i+=2) {

          if(co1>=b[i]-10 && co1<=b[i]+30 && ro1>=b[i+1]-10 && ro1<=b[i+1]+30){

            if((be[i/2]<18)) {

              co1=b[i]; ro1=b[i+1]; pi=be[i/2]; pi1=i/2; move=1;

              be[30]=pi;

              break;

              }

            if(be[i/2]==18 && (move==1)) { 

              be[i/2]=pi; be[pi1]=18; move=0; number++;

              if(number>=5) { start_button.setLabel("Solve"); }

              break;

              }

            }

          }

        }

      }



    if (evt.id == Event.MOUSE_MOVE && inst==0) {

      ro1 = evt.y;    co1 = evt.x;

      ro2=evt.y-30; co2=evt.x-30;

      if(co1>=160 && co1<=215 && ro1>=156 && ro1<=225 && inst==0) {

        talk=1;

        }    

      if((co1<=160||co1>=215||ro1<=156||ro1>=225) && inst==0 && talk==1){

        talk=2;

        }    

      }



    switch(evt.id) {

      case Event.ACTION_EVENT: {

        if("Solve".equals(evt.arg)) { if(inst==0) cheat=1; }

        if("Restart".equals(evt.arg)) { restart(); }

        if("Instructions".equals(evt.arg)) { instructions(); }

        if("Resume".equals(evt.arg)) { resume(); }

        if("Rotate".equals(evt.arg)) { rotate(); }

        }

      }

    return true;

    }



}


