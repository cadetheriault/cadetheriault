/**
 * Buttons.java
 * Button object for the menu and instruction states
 * Cole
 */

package com.code.utilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.code.utilities.Fonts;
import com.code.game.DoodleDash;

public class Buttons extends Rectangle {

	private Font 	font;
	private Color	color;
	private String 	text;
	private boolean selected;
	private int		textY;
	
	/**
	 * pre: pass text, textY, font, color; menu state or instruction state
	 * post: constructs button object by assigning the variables
	 */
	public Buttons(String text, int textY, Font font, Color color) {
		this.font 		= 	font;
		this.color 		= 	color;
		this.text 		= 	text;
		this.textY 		= 	textY;
		
	}
	
	/**
	 * pre: N/A
	 * post: sets the selected variable
	 */
	public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    /**
     * pre: Menu state or Instructions state being rendered, pass graphics
     * post: renders button(s)
     */
    public void render(Graphics g){
        Fonts.drawString(g, font, color, text, textY);
        
        FontMetrics fm = g.getFontMetrics();
        this.x = (DoodleDash.WIDTH - fm.stringWidth(text)) / 2;
        this.y = textY - fm.getHeight();
        this.width = fm.stringWidth(text);
        this.height = fm.getHeight();

    }
	
}
