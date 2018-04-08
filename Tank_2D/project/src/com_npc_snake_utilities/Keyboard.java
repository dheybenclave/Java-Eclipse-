package com_npc_snake_utilities;

import java.awt.event.*;
import javax.swing.*;

public class Keyboard implements KeyListener {

	private static Keyboard instance;
	private boolean[] keys;
	
	public Keyboard(){
		
		keys = new boolean[256];
	}
	
	public static Keyboard getInstance(){
		
		if(instance == null)
			instance = new Keyboard();
	
		return instance;
	}
	
	public boolean isDown(int key){
		if(key >= 0 && key <= keys.length){
			return keys[key];
		}
		
		return false;
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode() >= 0 && e.getKeyCode()<= keys.length){
			keys[e.getKeyCode()] = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	
		if(e.getKeyCode() >= 0 && e.getKeyCode()<= keys.length){
			keys[e.getKeyCode()] = false;
		}
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
