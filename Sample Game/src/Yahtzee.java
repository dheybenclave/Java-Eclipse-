/* Yahtzee.java by Dan Seidman


**


**   An unauthorized point-and-click implementation of the E. S. 


**   Lowe game "Yahtzee", now owned by Milton Bradley.


*/





import java.awt.Rectangle;


import java.awt.Event;


import java.awt.Color;


import java.awt.Graphics;


import java.awt.Font;


import java.awt.FontMetrics;


import java.applet.AudioClip;





public class Yahtzee extends java.applet.Applet {





	int totalWidth, totalHeight;


	int fontSize, dieSize, pipSize;			// dimensions


	int rollNum;					// which roll of the dice we're on


	Die dice[] = new Die[5];


	BigButton rollBar, newGameBar;


	ScoreGroup scores[] = new ScoreGroup[14];


	Font scoreFont, labelFont, rbFont, ngFont, msgFont;


	FontMetrics fm;					// metrics for labels


	int paintFlags;					// flags signalling what to repaint


	final int PaintDICE = 1;


	final int PaintSCORES = 2;


	final int PaintLABELS = 4;


	final int PaintTOPSEC = 8;


	final int PaintROLLBAR = 16;


	final int PaintNGBAR = 32;


	final int PaintMSG = 64;


	final int PaintGRAND = 128;


	final int PaintCHIPS = 256;


	final int PaintNOCHIPS = 512;


	final int PaintALL = 32767;


	int underMouse = 99;				// which score group the mouse is now in


	AudioClip Aend;


	AudioClip AgoodY;


	AudioClip AbadY;


	AudioClip Anew;


	AudioClip Aroll;


	int diceSelected;				// how many dice are currently highlighted


	int bonus;					// number of bonus chips earned so far


	int filledGroups;				// how many of the 13 score groups have been filled


	Color darkYellow = new Color(224, 224, 0);	// used for bonus chips


	int topSection;					// current +- running tab for top section bonus





	public void init() {


		Die d;





		setBackground(Color.lightGray);


		Aend = getAudioClip(getCodeBase(), "AUDIO/cowbell.au");


		AgoodY = getAudioClip(getCodeBase(), "AUDIO/whoopy.au");


		AbadY = getAudioClip(getCodeBase(), "AUDIO/ooh.au");


		Anew = getAudioClip(getCodeBase(), "AUDIO/harp.au");


		Aroll = getAudioClip(getCodeBase(), "AUDIO/ding.au");





		// initialize dimensions of various features


		totalWidth = Math.min(size().width, size().height);


		totalHeight = totalWidth;		// force it to be square


		dieSize = totalWidth / 6;


		pipSize = dieSize / 5;


		fontSize = totalHeight / 24;


		scoreFont = new Font("Helvetica", Font.BOLD, fontSize);


		labelFont = new Font("TimesRoman", Font.BOLD, fontSize);


		rbFont = new Font("TimesRoman", Font.PLAIN, dieSize / 2);


		ngFont = new Font("TimesRoman", Font.PLAIN, fontSize);


		msgFont = new Font("TimesRoman", Font.PLAIN, fontSize);


		fm = getFontMetrics(labelFont);





		// initialize dice


		for (int i=0; i<5; i++) {


			d = new Die(this, i);


			dice[i] = d;


		}





		// initialize rollbar (the "button" to roll the dice)


		rollBar = new BigButton(this, totalWidth / 2, dieSize * 3 / 2, 


			totalWidth / 3, dieSize);





		// initialize the New Game button


		newGameBar = new BigButton(this, totalWidth * 2 / 3, totalHeight * 2 / 3, 


			totalWidth / 5, dieSize * 2 / 3);





		// initialize score boxes


		scores[0] = new ScoreGroup(this, 0, "1's");


		scores[1] = new ScoreGroup(this, 1, "2's");


		scores[2] = new ScoreGroup(this, 2, "3's");


		scores[3] = new ScoreGroup(this, 3, "4's");


		scores[4] = new ScoreGroup(this, 4, "5's");


		scores[5] = new ScoreGroup(this, 5, "6's");


		scores[6] = new ScoreGroup(this, 6, "3 of a kind");


		scores[7] = new ScoreGroup(this, 7, "4 of a kind");


		scores[8] = new ScoreGroup(this, 8, "Full house");


		scores[9] = new ScoreGroup(this, 9, "Sm straight");


		scores[10] = new ScoreGroup(this, 10, "Lg straight");


		scores[11] = new ScoreGroup(this, 11, "Yahtzee");


		scores[12] = new ScoreGroup(this, 12, "Chance");


		scores[13] = new ScoreGroup(this, 13, "Grand Total");


		newGame();


	}





	void newGame() {		// initialize all values for a new game


		int i;





		rollNum = 1;


		for (i = 0; i < 5; i++)


			dice[i].zeroDie();


		for (i = 0; i < 13; i++)


			scores[i].zeroScore();


		rollBar.zeroBB();


		newGameBar.zeroBB();


		diceSelected = 5;


		bonus = 0;


		filledGroups = 0;


		topSection = 0;


		paintFlags = PaintALL;


		Anew.play();


	}





	public void update(Graphics g) {


		paint(g);


	}





	public void paint(Graphics g) {


		Die d;


		ScoreGroup s;


		int i;


		String t1, t2;





		// each part of the screen will be painted unless we have cleared its bit of PaintFLAGS --


		// note that the score groups and dice also have individual flags as well





		// dice


		if ((paintFlags & PaintDICE) != 0) {


		  for (i = 0; i < 5; i++)


		  {


		    d = dice[i];


		    if (!d.noPaint) {


			if (d.selected)


				g.setColor(Color.red);


			else


				g.setColor(Color.white);


			g.fillRoundRect(d.x, d.y, d.width, d.height, 5, 5);	// die surface


			g.setColor(Color.black);


			if (d.value > 0) {	// draw the pips


				int center = (dieSize - pipSize) / 2;


				int corner = (pipSize / 2);


				int farcorner = dieSize - (3 * pipSize / 2);


				if (d.value % 2 == 1) {	// odd #'s have center pip


					g.fillOval(d.x + center, d.y + center, pipSize, pipSize);


				}


				if (d.value > 1) {	// 2-6 have TL and BR corner pips


					g.fillOval(d.x + corner, d.y + corner, pipSize, pipSize);


					g.fillOval(d.x + farcorner, d.y + farcorner, pipSize, pipSize);


				}


				if (d.value > 3) {	// 4-6 have TR and BL corner pips


					g.fillOval(d.x + farcorner, d.y + corner, pipSize, pipSize);


					g.fillOval(d.x + corner, d.y + farcorner, pipSize, pipSize);


				}


				if (d.value == 6) {	// 6 has CL and CR pips


					g.fillOval(d.x + corner, d.y + center, pipSize, pipSize);


					g.fillOval(d.x + farcorner, d.y + center, pipSize, pipSize);


				}


			}


		    }


		    d.noPaint = false;


		  }


		}





		// rollbar


		if ((paintFlags & PaintROLLBAR) != 0) {


		    if (rollBar.active) {


			g.setColor(Color.red);


			g.fill3DRect(rollBar.x, rollBar.y, rollBar.width, rollBar.height, rollBar.raised);


		    } else {


			g.setColor(getBackground());


			g.fill3DRect(rollBar.x, rollBar.y, rollBar.width, rollBar.height, true);


		    }


		    if (rollNum < 4) {


			g.setColor(Color.black);


			g.setFont(rbFont);


			g.drawString("Roll " + rollNum, rollBar.x + (rollBar.width / 5),


				rollBar.y + (rollBar.height * 2 / 3));


		    }


		}





		// new-game bar


		if ((paintFlags & PaintNGBAR) != 0) {


			g.setColor(Color.green);


			g.fill3DRect(newGameBar.x, newGameBar.y, newGameBar.width, 


				newGameBar.height, newGameBar.raised);


			g.setColor(Color.black);


			g.setFont(ngFont);


			g.drawString("New game", newGameBar.x + (newGameBar.width / 10), 


					newGameBar.y + (fontSize * 3 / 2));


		}





		// score boxes and their labels


		if ((paintFlags & PaintLABELS) != 0) {


		    g.setFont(labelFont);


		    g.setColor(Color.blue);


		    for (i = 0; i < 13; i++)


			g.drawString(scores[i].name,


				scores[i].x - fm.stringWidth(scores[i].name) - 5,


				scores[i].y + fontSize);


		}


		if ((paintFlags & PaintSCORES) != 0) {


			g.setFont(scoreFont);


			for (i = 0; i < 13; i++)


			{


			    s = scores[i];


			    if (!s.noPaint) {


				g.setColor(Color.white);


				g.fillRect(s.x, s.y, s.width, s.height);


				if (s.filled) {


				    g.setColor(Color.black);


				    g.drawString(String.valueOf(s.value), s.x + 1, s.y + fontSize);


				}


				else if (i == underMouse) {


				    g.setColor(Color.red);


				    g.drawString(String.valueOf(s.value), s.x + 1, s.y + fontSize);


				}


			    }


			    s.noPaint = false;


			}


		}





		// grand total score box


		if ((paintFlags & PaintGRAND) != 0) {


			if (filledGroups == 13) {	// game over


				s = scores[13];


				g.setColor(getBackground());


				g.fillRect(0, scores[2].y, scores[0].x - scores[0].width, scores[0].height * 3);


				g.setColor(Color.white);


				g.fillRect(s.x, s.y, s.width, s.height);


				g.setFont(labelFont);


				g.setColor(Color.magenta);


				g.drawString(s.name, s.x - fm.stringWidth(s.name) - 5, s.y + fontSize);


				g.setFont(scoreFont);


				g.drawString(String.valueOf(s.value), s.x + 1, s.y + fontSize);


				if (topSection >= 0) {


					g.drawString(" +35", fontSize, scores[3].y);


					g.setFont(ngFont);


					g.drawString("points", fontSize, scores[4].y);


				} else {


					g.drawString(" X", fontSize, scores[3].y);


				}


			} else {		// clear it


				g.setColor(getBackground());


				g.fillRect(0, scores[13].y, scores[13].x + scores[13].width + 1,


						 scores[13].height + 1);


			}


		}





		// +- indicator for top section


		if ((paintFlags & PaintTOPSEC) != 0) {


			if (filledGroups != 13) {		// final result always handled above


				g.setColor(getBackground());


				g.fillRect(0, scores[2].y, scores[0].x - scores[0].width, scores[0].height * 3);


				g.setColor(Color.red);


				g.setFont(scoreFont);


				g.drawString((topSection > 0 ? "+" : "") + topSection, fontSize, scores[3].y);


			}


		}





		// a brief instructional message


		if ((paintFlags & PaintMSG) != 0) {


			g.setColor(getBackground());


			g.fillRect(totalWidth / 2, rollBar.y + rollBar.height + 1,


				totalWidth / 2, dieSize);


			g.setColor(Color.black);


			g.setFont(msgFont);


			if (filledGroups == 13) {


				t1 = "Press the green bar";


				t2 = "  for another game";


			} else if (rollNum == 1) {


				t1 = "Press the Roll bar";


				t2 = "   to roll the dice";


			} else if (rollNum == 4) {


				t1 = "Select a score box";


				t2 = "";


			} else {


				t1 = "Select a score box, or";


				t2 = " select dice and reroll";


			}


			g.drawString(t1, totalWidth / 2, rollBar.y + rollBar.height + fontSize + 10);


			g.drawString(t2, totalWidth / 2, rollBar.y + rollBar.height + (2 * fontSize) + 10);


		}





		// bonus chips


		if ((paintFlags & PaintNOCHIPS) != 0) {	// need to be cleared for new game


			g.setColor(getBackground());


			g.fillRect(totalWidth / 2, totalHeight - (fontSize * 2) - 20, totalWidth / 2,


					fontSize * 2);


		} else if ((paintFlags & PaintCHIPS) != 0) {


		    for (i = 0; i < bonus; i++) {


			g.setColor(darkYellow);


			g.fillOval(i * (fontSize * 2 + 10) + totalWidth / 2, 


					totalHeight - (fontSize * 2) - 20, fontSize * 2, fontSize * 2);


			g.setColor(Color.yellow);


			g.fillOval(i * (fontSize * 2 + 10) + totalWidth / 2 + fontSize / 2,


					 totalHeight - (fontSize * 3 / 2) - 20,	fontSize, fontSize);


		    }


		}





		paintFlags = PaintALL;		// reset


	}





	public boolean mouseDown (Event evt, int x, int y) {


		Die d;





		// if on a die, select or deselect it; if on the bar, depress it; else ignore


		if (rollNum > 1 && rollNum < 4)


		    for (int i = 0; i < 5; i++) {


			d = dice[i];


			if (d.inside(x, y)) {


				paintFlags = PaintDICE;


				if (d.selected) {


					diceSelected--;


					d.selected = false;


					if (diceSelected == 0) {


						rollBar.active = false;


						paintFlags |= PaintROLLBAR;


					}


				} else {


					diceSelected++;


					d.selected = true;


					rollBar.active = true;


					if (diceSelected == 1)


						paintFlags |= PaintROLLBAR;


				}


				for (int j = 0; j < 5; j++)


					if (i != j)


						dice[j].noPaint = true;


				repaint();


				return true;


			}


		    }


		


		if (rollBar.inside(x, y) && rollBar.active) {


			rollBar.raised = false;


			paintFlags = PaintROLLBAR;


			repaint();


			return true;


		}





		if (newGameBar.inside(x, y)) {


			newGameBar.raised = false;


			paintFlags = PaintNGBAR;


			repaint();


			return true;


		}





		return(false);


	}





	public boolean mouseUp (Event evt, int x, int y) {





		// if on depressed bar, roll


		if (!rollBar.raised && rollBar.inside(x, y)) {


			rollBar.raised = true;


			Aroll.play();


			roll();


			paintFlags = PaintDICE | PaintROLLBAR | PaintMSG;


			repaint();


			if (dice[0].value == dice[1].value && dice[1].value == dice[2].value &&


			    dice[2].value == dice[3].value && dice[3].value == dice[4].value) {	// Yahtzee rolled


				Aroll.stop();


				if (scores[11].filled && scores[11].value == 0)


					AbadY.play();


				else


					AgoodY.play();


			}


			return true;


		}





		// if the user wants a new game, reinitialize


		if (!newGameBar.raised && newGameBar.inside(x, y)) {


			newGame();


			paintFlags = PaintALL;


			repaint();


			return true;


		}





		// if on a valid scoregroup, select and record the score


		if (underMouse != 99 && scores[underMouse].inside(x, y)) {


			paintFlags = PaintSCORES | PaintDICE | PaintROLLBAR | PaintMSG;


			if (scores[11].filled && scores[11].value == 50 &&	// detect bonus chip earned


			    dice[0].value == dice[1].value && dice[1].value == dice[2].value &&


			    dice[2].value == dice[3].value && dice[3].value == dice[4].value) {


				bonus++;


				paintFlags |= PaintCHIPS;


			}


			scores[underMouse].filled = true;


			if (underMouse < 6) {	// adjust top section indicator


				topSection += (scores[underMouse].value) - (3 * (underMouse + 1));


				paintFlags |= PaintTOPSEC;


			}


			underMouse = 99;


			rollBar.raised = true;


			if (++filledGroups == 13) {	// game over


				int t = bonus * 100;			// bonus chips


				for (int i = 0; i < 13; i++)


					t += scores[i].value;		// basic score


				if (topSection >= 0)


					t += 35;			// top bonus


				scores[13].value = t;


				rollNum = 4;


				rollBar.active = false;


				paintFlags |= PaintGRAND;


				Aend.play();


			} else {			// reset the dice


				for (int j = 0; j < 5; j++)


					dice[j].zeroDie();


				diceSelected = 5;


				rollNum = 1;


				rollBar.active = true;


			}


			repaint();


			return(true);


		}





		// if bar had been depressed; cancel that


		if (!rollBar.raised) {


			rollBar.raised = true;


			paintFlags = PaintROLLBAR;


			repaint();


			return(true);


		}


		if (!newGameBar.raised) {


			newGameBar.raised = true;


			paintFlags = PaintNGBAR;


			repaint();


			return(true);


		}





		return(false);


	}





	public boolean mouseMove (Event evt, int x, int y) {


		// if we move over a score box we can fill, highlight it


		ScoreGroup s;


		int newUnder = 99, i;





		if (rollNum != 1) {


			for (i = 0; i < 13; i++) {


				s = scores[i];


				if (s.inside(x, y) && !s.filled) {


					newUnder = i;


					if (i != underMouse)


						s.calc(i);


					break;


				}


			}


			if (newUnder != underMouse) {


				for (i = 0; i < 13; i++)


					scores[i].noPaint = true;


				if (underMouse != 99)


					scores[underMouse].noPaint = false;


				if (newUnder != 99)


					scores[newUnder].noPaint = false;


				underMouse = newUnder;


				paintFlags = PaintSCORES;


				repaint();


				return true;


			}


		}


		return(false);


	}





	// roll the selected dice


	void roll() {


		for (int i = 0; i < 5; 	i++)


		    if (dice[i].selected) {


			dice[i].value = 1 + (int)Math.floor(Math.random() * 6);


			if (dice[i].value > 6)	// shouldn't happen, but it's not clear


				dice[i].value = 6;


			dice[i].selected = false;


		    }


		    else


			dice[i].noPaint = true;


		diceSelected = 0;


		rollBar.active = false;


		rollNum++;


	}


}








class Die extends Rectangle {


	Yahtzee parent;


	boolean selected;


	boolean noPaint;


	int value;





	Die(Yahtzee target, int offset) {


		int gap = target.dieSize / 6;





		parent = target;


		width = height = parent.dieSize;


		x = (offset * (gap + width)) + gap;


		y = gap;


	}





	void zeroDie() {


		value = 0;


		selected = true;


		noPaint = false;


	}


}





class BigButton extends Rectangle {


	Yahtzee parent;


	boolean raised;


	boolean active;


	boolean noPaint;





	BigButton(Yahtzee target, int x, int y, int w, int h) {


		super(x, y, w, h);


		parent = target;


	}





	void zeroBB() {


		raised = true;


		active = true;


		noPaint = false;


	}


}





class ScoreGroup extends Rectangle {


	Yahtzee parent;


	int value;


	boolean filled;


	boolean noPaint;


	String name;





	ScoreGroup(Yahtzee target, int offset, String text) {


		x = target.fontSize * 6;


		y = target.dieSize + ((target.fontSize + 4) * (offset + 1));


		width = target.fontSize * 2;


		height = target.fontSize + 2;


		parent = target;


		name = text;


	}





	void zeroScore() {


		value = 0;


		filled = false;


		noPaint = false;


	}





	void calc(int groupNum) {


		// calculate the score for this group


		int i, j, points = 0, consec = 0, total = 0;


		int totals[] = new int[7];


		boolean yahtzee = false, joker = false, fh;





		for (i = 0; i < 5; i++) {


			j = parent.dice[i].value;


			total += j;


			totals[j] += 1;


			if (totals[j] == 5) {


				yahtzee = true;


				if (parent.scores[11].filled && parent.scores[j-1].filled)


					joker = true;		// joker condition


			}


		}





		if (groupNum < 6)					// 1's, 2's, etc.


			points = (groupNum + 1) * totals[groupNum + 1];


		else if (groupNum == 6 || groupNum == 7) {		// 3 or 4 of a kind


			for (j = 1; j < 7; j++)


				if (totals[j] > (groupNum - 4))


					points = total;


		}


		else if (groupNum == 8) {				// full house


			if (joker)


				points = 25;


			else {


				fh = true;


				for (j = 1; j < 7; j++)


					if (totals[j] == 1 || totals[j] == 4


					    || totals[j] == 5)


						fh = false;


				points = fh ? 25 : 0;


			}


		}


		else if (groupNum == 9 || groupNum == 10) {		// small or large straight


			if (joker)


				points = groupNum == 9 ? 30 : 40;


			else {


				for (j = 1; j < 7; j++) {


					if (totals[j] > 0)


						consec++;


					else


						consec = 0;


					if (consec > (groupNum - 6)) {


						points = groupNum == 9 ? 30 : 40;


						break;


					}


				}


			}


		}			


		else if (groupNum == 11)				// Yahtzee


			points = yahtzee ? 50 : 0;


		else if (groupNum == 12)				// chance


			points = total;





		parent.scores[groupNum].value = points;


	}


}

