/**
 * MenuState.java
 * State of Game that displays the Menu
 * Cade
 */

package com.code.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.code.game.DoodleDash;
import com.code.utilities.Buttons;
import com.code.utilities.Fonts;
import com.code.utilities.KeyInput;
import com.code.utilities.MouseInput;

public class MenuState implements State {

	private Buttons 	button1, button2, button3 ;
	private int			selectedButton;	
	
	/**
	 * pre: State to be set
	 * post: Creates objects used in state
	 */
	@Override
	public void init() {
		
		selectedButton = 1;
		
		button1 = new Buttons("Play Game", 200, new Font("Segoe Print",Font.PLAIN, 50),
				Color.ORANGE);
		button2 = new Buttons("Instrunctions", 300, new Font("Segoe Print",Font.PLAIN, 30),
				Color.BLACK);
		button3 = new Buttons("Exit", 400, new Font("Segoe Print",Font.PLAIN, 30),
				Color.BLACK);
		
	}

	/**
	 * pre: N/A
	 * post: N/A
	 */
	@Override
	public void enter() {}

	/**
	 * pre: N/A
	 * post: Checks key and mouse input to determine which button is selected
	 */
	@Override
	public void tick(StateManager stateManager) {
		
		//if statement for moving up the list
		if (KeyInput.wasPressed(KeyEvent.VK_W) || KeyInput.wasPressed(KeyEvent.VK_UP)) {
			
			if (selectedButton == 1) {
				selectedButton = 1;
				
			} else if (selectedButton == 2) { 
				selectedButton = 1;
				
			} else if (selectedButton == 3) {
				selectedButton = 2;
				
			}
		}
			//if statement for moving down the list
			if (KeyInput.wasPressed(KeyEvent.VK_S) || KeyInput.wasPressed(KeyEvent.VK_DOWN)) {
				
				if (selectedButton == 1) {
					selectedButton = 2;
					
				} else if (selectedButton == 2) { 
					selectedButton = 3;
					
				} else if (selectedButton == 3) {
					selectedButton = 3;
					
				}
			}
			
			boolean clicked = false;
			//if statements for mouse intersection with button
	            if (button1.intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
	            	selectedButton = 1;
	            	clicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
	                
	            }
	        
	            if (button2.intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
		            selectedButton = 2;
	            	clicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
	                
	            }
	            
	            if (button3.intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
	            	selectedButton = 3;
	            	clicked = MouseInput.wasPressed(MouseEvent.BUTTON1);
	                
	            }
	            
	        if (clicked || KeyInput.wasPressed(KeyEvent.VK_ENTER)) {
	            select(stateManager);
	        }
	}
	/**
	 * pre: N/A
	 * post: button clicked either selects differet state or exits game
	 */
	private void select(StateManager stateManager) {
		
		switch (selectedButton) {
			case 1: 
				stateManager.setState("level1"); 
				break;
			case 2:
				stateManager.setState("Instructions"); 
				break;
			case 3: 
				System.out.println("Exit");	
				System.exit(0);
				break;
		
		}
		
	}

	/**
	 * pre: Graphics2D is passed, state is called
	 * post: renders properties of the state: string, labels/buttons
	 */
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, DoodleDash.WIDTH, DoodleDash.HEIGHT);
		Fonts.drawString(g, new Font("Segoe Print", Font.BOLD, 68),Color.BLACK, DoodleDash.TITLE, 70);
		
		if (selectedButton == 1) {
			button1.setSelected(true);
			button2.setSelected(false);
			button3.setSelected(false);
		} else if (selectedButton == 2) {
			button1.setSelected(false);
			button2.setSelected(true);
			button3.setSelected(false);		
		} else if (selectedButton == 3) {
			button1.setSelected(false);
			button2.setSelected(false);
			button3.setSelected(true);		
		}	
		
		button1.render(g);
		button2.render(g);
		button3.render(g);
		
	}

	/**
	 * pre: N/A
	 * post: N/A
	 */
	
	@Override
	public void exit() {}

	/**
	 * pre: N/A
	 * post: Returns name of state
	 */
	
	@Override
	public String getName() {
		return "Menu";
	}

	

}
