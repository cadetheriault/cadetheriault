/**
 * TileMap.java
 * Creates the Map with entities, layers, tiles and renders the map and Checks for collision
 * From Youtube Tutorial: Teivodov
 */

package com.code.textures;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.code.entities.Entity;
import com.code.entities.Player_1;
import com.code.render.ParallaxEngine;
import com.code.render.ParallaxLayer;

public class TileMap {

	private static final int 	TILE_SIZE = 64;
	private static final int 	TILE_SIZE_BITS = 6;
	
	private Tile[] 				tiles;
	private String 				name;
	private int    				width;
	private int    				height;
	
	private Player_1       		player1;
	private ParallaxEngine 		parallaxEngine;

	private ArrayList<Entity> 	entities;

	/**
	 * pre: pass level name, play state is active
	 * post: tileMap is constructed and back ground layers added
	 */
	public TileMap(String name) {
	    entities = new ArrayList<Entity>();
	    ParallaxLayer back = new ParallaxLayer(new Texture("Background1"), (int) ((16 * 0.25) * -0.25));
	    ParallaxLayer mid = new ParallaxLayer(new Texture("mid"), (int) ((16 * 0.25) * -0.25));
	    this.parallaxEngine = new ParallaxEngine(back, mid);
	    load(name);
	}

	/**
	 * pre: passes pixels
	 * post: returns a value (converts the number of pixels to a tile size)
	 */
	public static int pixelsToTiles(int pixels) {
		//since the tiles are square, the use of '>>' allows for value of pixels to be multiplied by 2^x
	    return pixels >> TILE_SIZE_BITS; 
	}

	/**
	 * pre: passes tiles
	 * post: returns a value (converts a tile to a number of pixels)
	 */
	public static int tilesToPixels(int tiles) {
		//since the tiles are square, the use of '<<' allows for value of pixels to be divided by 2^x
	    return tiles << TILE_SIZE_BITS; 
	}

	/**
	 * pre: tick method looping
	 * post: updates the map and background layers
	 */
	public void tick() {
	    if (player1.isMovingLeft()) {
	        parallaxEngine.setLeft();
	    } else if (player1.isMovingRight()) {
	        parallaxEngine.setRight();
	    }    
	    
	    if (player1.isMoving()) {
	            parallaxEngine.move();
	    }
	    
	    for (int i = 0; i < entities.size(); i++) {
	        entities.get(i).tick();
	    }
	    
	        player1.tick();
	    }

	/**
	 * pre: game being rendered, pass Graphics2D, width and height
	 * post: renders the map 
	 */
	 public void render(Graphics2D g, int screenWidth, int screenHeight) {
		 
	    int mapWidth = tilesToPixels(width);
	    int mapHeight = tilesToPixels(height);
	    int offsetX = (int) (screenWidth / 2 - player1.getX() - TILE_SIZE / 2);
	    int offsetY = (int) (screenHeight / 2 - player1.getY() - TILE_SIZE / 2);
	    offsetX = Math.min(offsetX, 0);
	    offsetX = Math.max(offsetX, screenWidth - mapWidth);
	    offsetY = Math.min(offsetY, 0);
	    offsetY = Math.max(offsetY, screenHeight - mapHeight);

	    int firstX = pixelsToTiles(-offsetX);
	    int lastX = firstX + pixelsToTiles(screenWidth) + 1;
	    int firstY = pixelsToTiles(-offsetY);
	    int lastY = firstY + pixelsToTiles(screenHeight) + 1;
	        
	    parallaxEngine.render(g);

	    for (int y = firstY; y <= lastY; y++) {
	        for (int x = firstX; x <= lastX; x++) {
	            Tile t = getTile(x, y);
	            if (t != null) t.render(g, tilesToPixels(x) + offsetX, tilesToPixels(y) + offsetY);
	        }
	    }

	    for (int i = 0; i < entities.size(); i++)
	        entities.get(i).render(g, offsetX, offsetY);
	    player1.render(g, offsetX, offsetY);

	    }
	    
	    /**
	     * pre: N/A
	     * post: returns boolean value if collision occurs
	     */
	    public boolean getTileCollision(int size, double oldX, double oldY, double newX, double newY, boolean vert) {
	        double fromX = Math.min(oldX, newX);
	        double fromY = Math.min(oldY, newY);
	        double toX = Math.max(oldX, newX);
	        double toY = Math.max(oldY, newY);

	        int fromTileX = pixelsToTiles((int) fromX);
	        int fromTileY = pixelsToTiles((int) fromY);
	        int toTileX = pixelsToTiles((int) toX + size - 1);
	        int toTileY = pixelsToTiles((int) toY + size - 1);

	        // the loops go through every column and row of tiles to check if the tiles touch or intersect
	        for (int y = fromTileY; y <= toTileY; y++) {
	            for (int x = fromTileX; x <= toTileX; x++) {
	                if (x < 0 || x >= width || (getTile(x, y) != null && getTile(x, y).isSolid())) {
	                	//Checks if Mob is hitting something when jumping or falling
	                    if (vert) {
	                        if (player1.isFalling()) {
	                        	player1.setCanJump(false);
	                            player1.setY(tilesToPixels(y) - size);
	                        } else
	                            player1.setY(tilesToPixels(y + 1));
	                        player1.setVelocityY(0);
	                    }
	                    return true;
	                }
	            }
	        }
	        return false;
	    }

	    /**
	     * pre: N/A
	     * post: sets tile position and texture
	     */
	    public void setTile(int x, int y, Tile tile) {
	        tiles[x + y * width] = tile;
	    }

	    /**
	     * pre: pass x and y
	     * post: returns the tile from the position
	     */
	    public Tile getTile(int x, int y) {
	        if (x < 0 || x >= width || y < 0 || y >= height) return null;
	        return tiles[x + y * width];
	    }

	    /**
	     * pre: tile map constructed, pass map name
	     * post: assigns dimensions, spawns Mobs, sets all tiles on the map
	     */
	    private void load(String name) {
	        BufferedImage image = null;
	        //name variable is used to load the map image to then create tile map
	        try {
	            image = ImageIO.read(new File("./resource/Textures/" + name + ".png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        this.name = name;
	        this.width = image.getWidth();
	        this.height = image.getHeight();
	        
	        tiles = new Tile[width * height];
	        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);
	        
	        for (int y = 0; y < height; y++) {
	            for (int x = 0; x < width; x++) {
	                int id = pixels[x + y * width];
	                //Spawns player
	                if (id == 0xFF0000FF)
	                    player1 = new Player_1(tilesToPixels(x), tilesToPixels(y), this);
	                //Sets all tiles
	                else if (Tile.getFromID(id) != null)
	                    setTile(x, y, Tile.getFromID(id));
	            }
	        }
	    }

	    /**
	     * pre: N/A 
	     * post: adds entity to map
	     */
	    public void addEntity(Entity e) {
	        if (!(e instanceof Player_1)) entities.add(e);
	    }

	    /**
	     * pre: N/A
	     * post: removes entity from map
	     */
	    public void removeEntity(Entity e) {
	        if (!(e instanceof Player_1)) entities.remove(e);
	    }

	    /**
	     * pre: N/A
	     * post: returns an array of entities
	     */
	    public ArrayList<Entity> getEntities() {
	        return entities;
	    }

	    /**
	     * pre: N/A
	     * post: returns the player object
	     */
	    public Player_1 getPlayer() {
	        return player1;
	    }
	
	   
	
}
