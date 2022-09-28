/**
 * Mob.Java
 * Abstract class used to assign characteristics to Player_1.java object
 * Cade
 */


package com.code.entities;

import java.awt.Graphics2D;

import com.code.render.Animation;
import com.code.textures.Texture;
import com.code.textures.TileMap;

public abstract class Mob extends Entity {

	protected double 	dx, dy;
	protected double 	maxDy;
	protected double 	gravity;
	protected boolean 	falling;
	protected boolean 	canJump;
	protected boolean 	moving;
	protected Animation anime;
	/**
	 * pre: Variables for location, texture, map, and animation are assigned
	 * post: Variables are assigned for Player_1
	 */
	public Mob(Texture texture, double x, double y, TileMap tileMap, Animation anime) {
		super(texture, x, y, tileMap);
		this.anime 		= anime;
		gravity 		= 0.5;
		maxDy 			= 5;
		
	}
	/**
	 * pre: Tick method chain called
	 * post: Check if Mob is moving, animates if moving is true
	 */
	@Override
	public void tick() {
		
		//Checks if player is falling 
		if (dy > 0) {
			falling = true;
		} else if (dy < 0) {
			falling = false;
		}
		
		//Calls movement method
		move();
		
		//Call falling method
		falling();
		
		//Checks if player is moving horizontally
		if (dx != 0) {
			moving = true;
		} else {
			moving = false;
		}
		
		//starts animation cycle if moving
		if (moving) {
			anime.run(); 
		}
	}
	
	/**
	 * pre: Graphics and position variables are passed
	 * post: renders mob if not moving, renders animation if moving
	 */
	@Override
	public void render(Graphics2D g, int offsetX, int offsetY) {
		if (!moving) {
			super.render(g, offsetX, offsetY);	
		} else {
			anime.render(g, x + offsetX, y + offsetY);
		}
	}
	
	/**
	 * pre: Tick method calls
	 * post: Checks collision if movement is allowed
	 */
	public void move() {
		
		//Checks horizontal collision
		boolean horiz = tileMap.getTileCollision(texture.getWidth(), x, y, x + dx, y, false);
		//Checks vertical collision
	    boolean vert = tileMap.getTileCollision(texture.getWidth(), x, y, x, y + dy, true);
	    
	    //If horizontal collision is false, then movement happens
	    if (!horiz) {
	    	x += dx;
	    }
	    //If vertical collision is false, the movement happens
	    if (!vert) {
	    	y += dy;
	    } 
	}
	
	/**
	 * pre: Tick method calls
	 * post: Adds gravity to make Mob Fall
	 */
	protected void falling() {
		canJump = false;
		//Adding gravity to dy makes Mob fall to bottom of screen
		dy+= gravity;
		if (dy > maxDy) {
			dy = maxDy;
		}
	}
	
	/**
	 * pre: jumpHeight is passed and jump() is called by Player_1
	 * post: Subtracts jumpHeight to make Mob Jump
	 */
	protected void jump(double jumpHeight) {
		if (canJump = true) {
			//Subtract jumpHeight to dy makes Mob Jump up towards max height
			dy -= jumpHeight;
			canJump = false;
		}
	}
	
	/**
	 * pre: Passes canJump variable
	 * post: Assigns canJump
	 */
	public void setCanJump(boolean canJump) {
		this.canJump = canJump;
	}
	
	/*
	 * pre: Passes dy variable
	 * post: Assigns dy variable
	 */
	public void setVelocityY(double dy) {
		this.dy = dy;
	}
	
	/**
	 * pre: N/A
	 * post: Returns falling variable
	 */
	public boolean isFalling() {
		return falling;
	}
	
	/**
	 * pre: N/A
	 * post: Returns true if dx < 0
	 */
	public boolean isMovingLeft() {
		return dx < 0;
	}
	
	/**
	 * pre: N/A
	 * post: Returns true if dx > 0
	 */
	public boolean isMovingRight() {
		return dx > 0;
	}
	
	/**
	 * pre: N/A
	 * post: Returns moving variable
	 */
	public boolean isMoving() {
		return moving;
	}
	
	
	
	
	
	
	
}
