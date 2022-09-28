/**
 * Player_1.java
 * Creates player object, and assigns all characteristics from Entity.java, Mob.java, Player_1.java
 * Characteristics like texture, x, y, animation, 
 * Key input is used to move the player object
 * Cade and Cole
 */


package com.code.entities;

import java.awt.event.KeyEvent;

import com.code.render.Animation;
import com.code.states.StateManager;
import com.code.textures.Texture;
import com.code.utilities.KeyInput;
import com.code.textures.TileMap;

public class Player_1 extends Mob {
	
	private static Animation cycle = new Animation(7,
            new Texture(new Texture("run"), 2, 1, 64),
            new Texture(new Texture("run"), 3, 1, 64),
            new Texture(new Texture("run"), 4, 1, 64),
            new Texture(new Texture("run"), 2, 2, 64), 
            new Texture(new Texture("run"), 3, 2, 64),
            new Texture(new Texture("run"), 4, 2, 64)
            );
	
	private int count = 0;
	
	/**
	 * pre: N/A
	 * post: Player entity constructed
	 */
	public Player_1(double x, double y, TileMap tileMap) {
		super(new Texture(new Texture("Mc_Sheet_NoLine"), 1, 1, 64), 
				x, y, tileMap, cycle);
		
	}
	/**
	 * pre: tick methods loop
	 * post: Checks key input and move character accordingly
	 */
	public void tick() {
		
		
		if (KeyInput.isDown(KeyEvent.VK_W)) {
			if (count == 0) { 
				jump(15);
			}
			count++;
		}	
		if(KeyInput.isDown(KeyEvent.VK_A)) {
			dx = -4;
		}
		if(KeyInput.isDown(KeyEvent.VK_D)) {
			dx = 4;
			
		}
		if (KeyInput.wasReleased(KeyEvent.VK_A) || KeyInput.wasReleased(KeyEvent.VK_D)) {
			dx = 0;
			
		}
		if (KeyInput.wasReleased(KeyEvent.VK_W)) {
			count = 0;
		}
		
		super.tick();
	}
	
		
	
	
	
}
