/**
 * ParallaxEngine.java
 * Set, Move and Render layers
 * From Youtube Tutorial : Teivodov 
 */

package com.code.render;

import java.awt.Graphics2D;


public class ParallaxEngine {

	 private ParallaxLayer[] layers;

	 
	   /**
	    * pre: layers have to be passed
	    * post: layers are set to array
	    */
	    public ParallaxEngine(ParallaxLayer... layers) {
	        this.layers = layers;
	    }

	    /**
	     * pre: Mob is moving right
	     * post: layers are set to move right
	     */
	    public void setRight() {
	        for (int i = 0; i < layers.length; i++)
	            layers[i].setRight();
	    }

	    /**
	     * pre: Mob is moving left
	     * post: layers are set to move left
	     */
	    public void setLeft() {
	        for (int i = 0; i < layers.length; i++)
	            layers[i].setLeft();
	    }

	    /**
	     * pre: Mob is not moving
	     * post: layers stop moving
	     */
	    public void stop() {
	        for (int i = 0; i < layers.length; i++)
	            layers[i].stop();
	    }

	    /**
	     * pre: mob is moving
	     * post: layers begin moving
	     */
	    public void move() {
	        for (int i = 0; i < layers.length; i++)
	            layers[i].move();
	    }

	    /**
	     * Graphics2D is passed
	     * renders layers
	     */
	    public void render(Graphics2D g) {
	        for (int i = 0; i < layers.length; i++)
	            layers[i].render(g);
	    }
	
	
	
	
}
