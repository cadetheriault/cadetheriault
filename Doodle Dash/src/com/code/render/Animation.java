/**
 * Animation.java
 * Takes the textures passed into Animation object and cycles through th textures at an assigned speed
 * Cole
 */


package com.code.render;

import java.awt.Graphics2D;

import com.code.entities.Player_1;
import com.code.textures.Texture;

public class Animation {

	private int count;
	private int index;
	private int speed;
	private int numFrames;
	private Texture currentFrame;
	private Texture[] frames;
	private Player_1 player1;
	
	/**
	 * pre: Speed variable and list of textures passed
	 * post: Assigns variables
	 */
	public Animation(int speed, Texture... frames) {
		this.speed = speed;
		this.frames = frames;
		this.numFrames = frames.length;
	}
	
	/**
	 * pre: N/A
	 * post: currentFrame changes to the next in the array of frames
	 */
	private void nextFrame() {
		
		// Current frame becomes next frame in array
		currentFrame = frames[index++];
		
		//If index reaches the end of the list, restarts at 0
		if (index >= numFrames) {
			index = 0;
		}
		
	}
	
	/**
	 * pre: mob must be moving
	 * post: next frame
	 */
	public void run() {
		
		count++;
		if (count > speed) {
			count = 0;
			nextFrame();
		}
		
	}
	/**
	 * pre: mob must be moving
	 * post: render next frame
	 */
	public void render (Graphics2D g, double x, double y) {
		if (currentFrame != null) {
			currentFrame.render(g, x, y);
		}
	}
	
}
