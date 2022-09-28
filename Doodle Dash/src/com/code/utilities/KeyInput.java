/**
 * KeyInput.java
 * Methods for key events to execute code
 * Cole
 */

package com.code.utilities;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	private static final int 		NUMKEYS 	= 256;
	
	private static final boolean[] 	keys 		= new boolean[NUMKEYS];
	private static final boolean[] 	lastKeys 	= new boolean[NUMKEYS];
	
	
	/**
	 * pre: key event
	 * post: Checks if a key was pressed
	 */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}
	
	/**
	 * pre: key event
	 * post: Checks if a key was released- not pressed anymore
	 */
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	/**
	 * pre: key event
	 * post: returns key when the key is held down
	 * @param keyCode
	 * @return
	 */
	public static boolean isDown(int keyCode) {
		return keys[keyCode];
	}
	
	/**
	 * pre: key event
	 * post: returns true if key is down and it is not the last key pressed
	 */
	public static boolean wasPressed(int keyCode ) {
		return isDown(keyCode) && !lastKeys[keyCode];
	}
	
	/**
	 * pre: key event
	 * post: returns true if key is not down anymore and it is the last key pressed
	 */
	public static boolean wasReleased(int keyCode ) {
		return !isDown(keyCode) && lastKeys[keyCode];
	}
	
	/**
	 * pre: N/A
	 * post: updates and goes through key arrays
	 */
	public static void update() {
		for (int i = 0; i < NUMKEYS; i++) {
			lastKeys[i] = keys[i];
		}
		
	}
	
}
