/**
 * Entity.java
 * Abstract class to assign characteristics to Mob.java
 * Characteristics like x, y, texture
 * Cade
 */

package com.code.entities;

import java.awt.Graphics2D;

import com.code.textures.Texture;
import com.code.textures.TileMap;

public abstract class Entity {

	protected double x, y;
	protected Texture texture;
	protected TileMap tileMap;
	
	
	/**
	 * pre: Variables for location, texture and map are assigned 
	 * post: Variables assigned for Mob
	 */
	public Entity(Texture texture, double x, double y, TileMap tileMap) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.tileMap = tileMap;
		tileMap.addEntity(this);
	}
	
	public abstract void tick();
	
	/**
	 * pre: Graphics and position variables passed 
	 * post: Texture of Entity is rendered
	 */
	public void render(Graphics2D g, int offsetX, int offsetY) {
		texture.render(g,  x + offsetX,  y + offsetY);
	}
	/**
	 * pre: x passed
	 * post: x assigned
	 */
	public void setX (double x) {
		this.x = x;
	}
	
	/**
	 * pre: y passed
	 * post: y assigned
	 */
	 public void setY (double y) {
		 this.y = y;
	 }
	 
	 /**
	  * pre: calling x value
	  * post: returning x value
	  */
	 public double getX() {
		 return x;
	 }
	 
	 /**
	  * pre: calling y value
	  * post: returning y value
	  */
	 public double getY() {
		 return y;
	 }
	 
	
}
