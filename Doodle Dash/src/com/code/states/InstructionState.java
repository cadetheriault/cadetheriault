/**
 * Instructions.java
 * State of game that displays instructions
 * Cade and Cole
 */

package com.code.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.code.game.DoodleDash;
import com.code.textures.Texture;
import com.code.utilities.Buttons;
import com.code.utilities.Fonts;

public class InstructionState implements State{

	private Buttons instr, descrip1, descrip2, descrip3, tip;
	private Texture mainCharacter = new Texture(new Texture("Mc_Sheet_NoLine"), 1, 1, 64);
	
	/**
	 * pre: State to be set
	 * post: Creates objects used in state
	 */
	@Override
	public void init() {
		
		instr = new Buttons("Instructions", 150, new Font("Segoe Print",Font.PLAIN, 40),
				Color.BLACK); 
		
		descrip1 = new Buttons("Objective of the game is to reach the end ", 300, new Font("Segoe Print",Font.PLAIN, 20),
				Color.BLACK);
		descrip3 = new Buttons("of the map and find the Princess.", 330, new Font("Segoe Print",Font.PLAIN, 20),
				Color.BLACK);
		
		descrip2 = new Buttons("Run and Jump using W, A, D", 360, new Font("Segoe Print",Font.PLAIN, 20),
				Color.BLACK);
		tip = new Buttons("You Can Double Jump!", 430, new Font("Segoe Print",Font.PLAIN, 17),
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
	 * post: N/A
	 */
	@Override
	public void tick(StateManager stateManager) {}

	/**
	 * pre: Graphics2D is passed, state is called
	 * post: renders properties of the state: string, labels/buttons, textures
	 */
	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, DoodleDash.WIDTH, DoodleDash.HEIGHT);
		
		Fonts.drawString(g, new Font("Segoe Print",Font.PLAIN, 15) , Color.BLACK, "Press Escape to go Back to the Menu", 75);
		
		instr.render(g);
		mainCharacter.render(g, DoodleDash.WIDTH / 2 - (mainCharacter.getWidth() / 2),
				DoodleDash.HEIGHT / 2 - mainCharacter.getHeight());
		
		descrip1.render(g);
		descrip2.render(g);
		descrip3.render(g);
		tip.render(g);
		
	}
	
	
	
	/**
	 * pre: N/A
	 * post: N/A
	 */
	@Override
	public void exit() {}

	/**
	 * pre: N/A
	 * post: Returns the name of the state
	 */
	@Override
	public String getName() {
		return "Instructions";
	}

}
