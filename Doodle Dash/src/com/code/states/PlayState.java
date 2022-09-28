/**
 * PlayState.java
 * State of game where it is played
 * Cade and Cole
 */

package com.code.states;

import java.awt.Graphics2D;

import com.code.game.DoodleDash;
import com.code.textures.TileMap;

public class PlayState implements State{

	private TileMap tileMap;
	
	/**
	 * pre: State to be set
	 * post: Creates objects used in state
	 */
	@Override
	public void init() {
		tileMap = new TileMap("FinalMap");
	}

	/**
	 * pre: N/A
	 * post: N/A
	 */
	
	@Override
	public void enter() {}
	
	/**
	 * pre: N/A
	 * post: calls tick method from TileMap object
	 */
	@Override
	public void tick(StateManager stateManager) {
		tileMap.tick();
	}

	/**
	 * pre: Graphics2D is passed, state is called
	 * post: renders properties of state: tileMap
	 */
	@Override
	public void render(Graphics2D g) {
		tileMap.render(g,  DoodleDash.WIDTH, DoodleDash.HEIGHT);
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
		return "level1";
	}

}
