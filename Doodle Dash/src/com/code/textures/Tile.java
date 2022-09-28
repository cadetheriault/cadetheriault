/**
 * Tile.java
 * Creates tiles from textures and dimensions assigned
 * Cole
 */

package com.code.textures;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import com.code.textures.Texture;

public class Tile {

	private static final Texture            terrain = new Texture("TerrainSheet");
    private static final Map<Integer, Tile> tileMap = new HashMap<Integer, Tile>();
    protected Texture                       tile;
    protected boolean                       solid;
    protected static int                           id;

    public static final Tile tile1 = new Tile(0xFFff0000, new Texture(terrain, 1, 1, 64));
    public static final Tile tile2 = new Tile(0xFFffffff, new Texture(terrain, 2, 1, 64));
    public static final Tile tile3 = new Tile(0xFFfff200, new Texture(terrain, 3, 1, 64));
    public static final Tile tile4 = new Tile(0xFF9b3fcc, new Texture(terrain, 4, 1, 64));
    public static final Tile tile5 = new Tile(0xFF00ff48, new Texture(terrain, 1, 2, 64));
    public static final Tile tile6 = new Tile(0xFF00c036, new Texture(terrain, 2, 2, 64));
    public static final Tile tile7 = new Tile(0xFF8cfffb, new Texture(terrain, 3, 2, 64));

    /**
     * pre: N/A
     * post: Constructs tiles with a texture by comparing 
     */
    private Tile(int id, Texture tile) {
        this.id = id;
        this.tile = tile;
        solid = true;
        tileMap.put(id, this);
    }

    /**
     * pre: Graphics2D pass 
     * post: Renders the tile
     */
    public void render(Graphics2D g, int x, int y) {
        tile.render(g, x, y);
    }

    /**
     * pre: N/A
     * post: Returns solid boolean variable
     */
    public boolean isSolid() {
        return solid;
    }

    /**
     * pre: N/A
     * post: Returns id
     */
    public static Tile getFromID(int id) {
        return tileMap.get(id);
    }

}
	
	

