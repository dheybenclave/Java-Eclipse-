package com_npc_tank_utilities;
import com_npc_tank_utilities.Direction;
import com_npc_tank_entities.Tank;
import com_npc_tank_utilities.Keyboard;
import com_npc_tank.PlayGame;

import java.awt.event.*;

import javax.swing.*;

//import com_npc_snake_utilities.Keyboard;


public class Keyboard implements KeyListener {
	
	private static Keyboard instance;
	private boolean[] keys;
	
	public Keyboard()
	{	
		keys = new boolean[256];
	}
	
	public static Keyboard getInstance()
	{	
		if(instance == null)
			instance = new Keyboard();
	
		return instance;
	}
	
	public boolean isDown(int key)
	{
		if(key >= 0 && key <= keys.length){
			return keys[key];
		}	
		return false;
	}
	
	public void keyPressed(KeyEvent e) 
	{	
		if(e.getKeyCode() >= 0 && e.getKeyCode()<= keys.length){
			keys[e.getKeyCode()] = true;
		}
	}
	
	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode() >= 0 && e.getKeyCode()<= keys.length){
			keys[e.getKeyCode()] = false;
		}
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
