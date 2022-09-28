/**
 * Texture.java
 * Assigns and changes textures, renders textures, get dimension methods
 * Cole
 */

package com.code.textures;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Texture {

	private final static Map<String, BufferedImage> texMap = new HashMap<String, BufferedImage>();
	
	private BufferedImage 		image;
	private String 				fileName;
	private int 				width, height;
	
	/**
	 * pre: New texture assigned to object, file passed
	 * post: Creates a texture from a full image 
	 * Sprite Sheets
	 */
	public Texture(String fileName) {
		this.fileName 				= fileName;
		BufferedImage oldTexture 	= texMap.get(fileName);
		
		// Check if there is a previous texture 
		if (oldTexture != null ) {
			this.image = oldTexture;
		} else {
			//Loads file and applies to texture
			try {
				System.out.println("Loading Texture: " + fileName );
				this.image = ImageIO.read(new File("./resource/Textures/" + fileName + ".png"));
				texMap.put(fileName, image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Dimension of image become texture dimensions
		this.width 					= image.getWidth();
		this.height 				= image.getHeight();
		
	}
	
	/**
	 * pre: New texture assigned to object, file passed
	 * post: Creates a texture by taking a sub image of the image passed, with different width and height
	 * Sub-Image for Sprites and Tiles
	 */
	public Texture(Texture spriteSheet, int x, int y, int width, int height) {
		
		this.width = width;
		this.height = height;
		
		String ss = spriteSheet.fileName + "_" + x + "_" + y;
		BufferedImage oldImage = texMap.get(ss);
		//Checks for previous image
		if (oldImage != null) {
			this.image = oldImage;
		} else {
			// Get sub image from full texture using dimensions passed
			this.image = spriteSheet.image.getSubimage(	x * width - width,
														y * height - height,
														width, 
														height);
		}	
	}
			
	/**
	 * pre: New texture assigned to object, file passed
	 * post: Creates a texture by taking a sub image of the image passed, with same width and height
	 * Sub-Image for Sprites and Tiles
	 */
	public Texture(Texture spriteSheet, int x, int y, int size) {
		this(spriteSheet, x, y, size, size);
	}
		
	/**
	 * pre: N/A
	 * post: renders image
	 */
	public void render(Graphics2D g, double x, double y) {
	    g.drawImage(image, (int) x, (int) y, null);
	}

	/**
	 * pre: N/A
	 * post: renders image from one point to another
	 */
	public void render(Graphics2D g, int destX1, int destX2, int srcX1, int srcX2, int y) {
	    g.drawImage(image, destX1, y, destX2, y + height, srcX1, 0, srcX2, height, null);
	}

	/**
	 * pre: N/A
	 * post: returns width variable
	 */
	public int getWidth() {
	    return width;
	}

	/**
	 * pre: N/A
	 * post: returns height variable 
	 */
	public int getHeight() {
	    return height;
	}

	/**
	 * pre: N/A
	 * post: returns image 
	 */
	public BufferedImage getImage() {
	    return image;
	}
	
	
}
