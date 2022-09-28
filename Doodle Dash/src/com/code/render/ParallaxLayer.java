/**
 * ParallaxLayer.java
 * Assigns texture, dimensions and renders layers
 * From Youtube Tutorial : Teivodov
 */

package com.code.render;

import java.awt.Graphics2D;

import com.code.game.DoodleDash;
import com.code.textures.Texture;


public class ParallaxLayer {

    private Texture texture;
    private int     x, y;
    private int     width, height;
    private int     dx;
    private int     gap;
    private boolean left, right;

    /**
     * pre: pass texture, dx, gap 
     * post: assigns variables
     */
    public ParallaxLayer(Texture texture, int dx, int gap) {
        this.texture = texture;
        this.dx = dx;
        this.gap = gap;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.x = this.y = 0;
    }

    /**
     * pre: pass texture, dx
     * post: assigns variables and sets gap to 0
     */
    public ParallaxLayer(Texture texture, int dx) {
        this(texture, dx, 0);
    }

    /**
     * pre: N/A
     * post: Sets right true, set left false
     */
    public void setRight() {
        right = true;
        left = false;
    }

    /**
     * pre: N/A
     * post: Sets left true, sets right false
     */
    public void setLeft() {
        right = false;
        left = true;
    }

    /**
     * pre: N/A
     * post: Sets right and left to false
     */
    public void stop() {
        right = left = false;
    }

    /**
     * pre: N/A
     * post: Sets x values to move the layers
     */
    public void move() {
        if (right) x = (x + dx) % (width + gap);
        else x = (x - dx) % width;
    }
    /**
     * pre: map being rendered, Graphics2D is passed
     * post: renders layers with x changes and y values
     */
    public void render(Graphics2D g) {
        if (x == 0)
            texture.render(g, 0, DoodleDash.WIDTH, 0, DoodleDash.WIDTH, y);
        else if (x > 0 && x < DoodleDash.WIDTH) {
            texture.render(g, x, DoodleDash.WIDTH, 0, DoodleDash.WIDTH - x, y);
            texture.render(g, 0, x, width - x, width, y);
        } else if (x >= DoodleDash.WIDTH)
            texture.render(g, 0, DoodleDash.WIDTH, width - x, width - x + DoodleDash.WIDTH, y);
        else if (x < 0 && x >= DoodleDash.WIDTH - width)
            texture.render(g, 0, DoodleDash.WIDTH, -x, DoodleDash.WIDTH - x, y);
        else if (x < DoodleDash.WIDTH - width) {
            texture.render(g, 0, width + x, -x, width, y);
            texture.render(g, gap + width + x, gap + DoodleDash.WIDTH, 0, DoodleDash.WIDTH - width - x, y);
        }
    }
	
}
