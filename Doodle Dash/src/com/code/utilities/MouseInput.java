/**
 * MouseInput.java
 * Methods for mouse events to execute code
 * Cade
 */

package com.code.utilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private static final int       NUM_BUTTONS = 10;

    private static final boolean[] buttons     = new boolean[NUM_BUTTONS];
    private static final boolean[] lastButtons = new boolean[NUM_BUTTONS];
    private static int             x           = -1, y = -1;
    public static int              lastX       = x, lastY = y;
    private static boolean         moving;

    
    /**
	 * pre: mouse event
	 * post: Checks if mouse button was pressed
	 */
    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    /**
	 * pre: mouse event
	 * post: Checks if a mouse was released- not pressed anymore
	 */
    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    /**
     * pre: mouse event
     * post: checks if mouse has changed x and y coordinates
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        moving = true;
    }

    /**
     * pre: N/A
     * post: updates mouse position and updates mouse button array
     */
    public static void update() {
        for (int i = 0; i < NUM_BUTTONS; i++)
            lastButtons[i] = buttons[i];

        if (x == lastX && y == lastY) moving = false;
        lastX = x;
        lastY = y;
    }

    /**
     * pre: N/A
     * post: returns if button is down
     */
    public static boolean isDown(int button) {
        return buttons[button];
    }

    /**
     * pre: N/A
     * post: returns true if button is down and it is not the last pressed
     */
    public static boolean wasPressed(int button) {
        return isDown(button) && !lastButtons[button];
    }

    /**
     * pre: N/A 
     * post: returns true if button is not down and it is the last button pressed
     */
    public static boolean wasReleased(int button) {
        return !isDown(button) && lastButtons[button];
    }

    /**
     * pre: N/A
     * post: gets x value of mouse
     */
    public static int getX() {
        return x;
    }

    /**
     * pre; N/A
     * post: gets y value of mouse
     */
    public static int getY() {
        return y;
    }

    /**
     * pre: N/A
     * post: returns true if mouse is moving - changing x and y coordinates
     */
    public static boolean isMoving() {
        return moving;
    }

}
