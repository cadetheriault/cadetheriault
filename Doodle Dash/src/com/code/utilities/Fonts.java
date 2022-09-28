/**
 * Fonts.java
 * Draws string with color, text and a position (x,y)
 * Cole
 */

package com.code.utilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.code.game.DoodleDash;

public class Fonts {

	/**
	 * pre: graphics, font, color, text, x, y passed
	 * post: draws string/renders text
	 */
	public static void drawString(Graphics g, Font f, Color c, String text, int x, int y) {
        g.setColor(c);
        g.setFont(f);
        g.drawString(text, x, y);
    }

	/**
	 * pre: graphics, font, color, text passed, x and y are determined so text is centered
	 * post: draws string/renders text
	 */
    public static void drawString(Graphics g, Font f, Color c, String text) {
        FontMetrics fm = g.getFontMetrics(f);
        
        // x and y are determined by dividing the width and height by 2, to center
        int x = (DoodleDash.WIDTH - fm.stringWidth(text)) / 2; 
        int y = ((DoodleDash.HEIGHT - fm.getHeight()) / 2) + fm.getAscent(); 
        drawString(g, f, c, text, x, y);
    }

    /**
	 * pre: graphics, font, color, text, x passed, y is determined so text is centered vertically
	 * post: draws string/renders text
	 */
    public static void drawString(Graphics g, Font f, Color c, String text, double x) {
        FontMetrics fm = g.getFontMetrics(f);
        
        //y is determined by dividing the height by 2, to center
        int y = ((DoodleDash.HEIGHT - fm.getHeight()) / 2) + fm.getAscent(); 
        drawString(g, f, c, text, (int) x, y);
    }

    /**
	 * pre: graphics, font, color, text, y passed, x is passed so text is centered horizontally
	 * post: draws string/renders text
	 */
    public static void drawString(Graphics g, Font f, Color c, String text, int y) {
        FontMetrics fm = g.getFontMetrics(f);
        
        // x is determined by dividing the width by 2, to center
        int x = (DoodleDash.WIDTH - fm.stringWidth(text)) / 2; 
        drawString(g, f, c, text, x, y);
    }
}
