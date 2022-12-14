/**
 * State.java
 * interface for other state classes
 * Cade and Cole
 */

package com.code.states;

import java.awt.Graphics2D;

public interface State {

	public void init();
	
	public void enter();
	
	public void tick(StateManager stateManager);
	
	public void render(Graphics2D g);
	
	public void exit();
	
	public String getName();
	
}
