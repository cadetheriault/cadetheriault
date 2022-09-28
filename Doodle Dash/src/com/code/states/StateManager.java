/**
 * StateManager.java
 * Add states to the game, sets active state, ticks and renders current state
 * Cade
 */

package com.code.states;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public class StateManager {

	private Map<String, State>	map; 
	private State 				currentState;
	
	/**
	 * pre: Game Starts
	 * post: map created for rendering the states
	 */
	public StateManager() {
		map = new HashMap<String, State>();
		
	}
	
	/**
	 * pre: state passed
	 * post: Adds state to game
	 */
	public void addState(State state) {
		map.put(state.getName().toUpperCase(), state);
		state.init();
		if (currentState == null) {
			state.enter();
			currentState = state;
		}
		
	}
	
	/**
	 * pre: state passed
	 * post: sets current state
	 */
	public void setState(String name) {
		State state = map.get(name.toUpperCase());
		
		// if there is no state named as passed, error message is printed
		if(state == null){
            System.err.println("State <" + name + "> does not exist!");
            return;
        }
		
		currentState.exit();
		state.enter();
		currentState = state;
		
	}
	/**
	 * pre: N/A
	 * post: ticks 
	 */
	public void tick() {
		currentState.tick(this);
		
	}
	
	/**
	 * pre: passes Graphics, a state has been assigned
	 * post: renders current state
	 * @param g
	 */
	public void render(Graphics2D g) {
		currentState.render(g);
		
	}
	
	
	
	
	
}
