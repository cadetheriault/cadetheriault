/**
 * DoodleDash.Java
 * Main Class, starts game and calls main looped methods
 * looped classes are: tick(), run(), render()
 * @author Cade Theriault and Cole Brito
 * ICS4U
 * Audience: Kids
 * 2020-01-20
 */

package com.code.game;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.code.states.InstructionState;
import com.code.states.MenuState;
import com.code.states.PlayState;
import com.code.states.StateManager;
import com.code.utilities.KeyInput;
import com.code.utilities.MouseInput;

public class DoodleDash extends Canvas implements Runnable {

	public static final String 		TITLE = "Doodle Dash";
	public static final int 		WIDTH = 640;
	public static final int 		HEIGHT = WIDTH / 4 * 3;
	
	private static boolean 			running;
	
	private StateManager 			stateManager;
	
	public static DoodleDash		Instance;
	
	/**
	 * pre: N/A
	 * post: States created
	 */
	public DoodleDash() {
		
		addKeyListener(new KeyInput());
		MouseInput mouseI = new MouseInput();
		addMouseListener(mouseI);
		addMouseMotionListener(mouseI);
		
		
		//Creating States
		stateManager = new StateManager();
		stateManager.addState(new MenuState());
		stateManager.addState(new PlayState());
		stateManager.addState(new InstructionState());
		
		Instance = this;
		
	}
	
	/**
	 * pre: game start
	 * post: tick counter, starts chain of tick methods through all other classes
	 */
	public void tick() {
		stateManager.tick();
		
		if (KeyInput.wasPressed(KeyEvent.VK_ESCAPE)) {
			stateManager.setState("Menu");
		}
		
	}
	
	/**
	 * pre: 
	 * post: 
	 */
	public void render() {
		
		/*
		 * BufferStrategy allows for the textures to be 'saved' for rendering
		 * so it does not have to be re-loaded when it is re-rendered
		 */
		BufferStrategy buffer = getBufferStrategy();
		
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = buffer.getDrawGraphics();
		Graphics2D g2D = (Graphics2D) g;
		
		//What is being drawn or rendered
		
		g2D.setColor(Color.BLACK);
		g2D.fillRect(0, 0, WIDTH, HEIGHT);
		
		stateManager.render(g2D);
		
		//
		
		g.dispose();
		buffer.show();
	}
	
	/**
	 * pre: N/A
	 * post: Runs game 
	 */
	private void start() {
		if(running) return;
		running = true;
		new Thread(this, "Main-Thread").start();
	}
	
	/**
	 * pre: N/A
	 * post: Stops Game
	 */
	private static void stop() {
		
		if (!running) return;
			running = false;
	}
	
	/**
	 * pre: Game Starts
	 * post: Tick Counter set, tick and update methods start looping
	 */
	@Override
	public void run() {
		running = true;
        requestFocus();
        double targetFPS = 60.0;
        double nsPerTick = 1000000000.0 / targetFPS;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double unprocessed = 0.0;
        int fps = 0;
        int tps = 0;
        boolean canRender = false;

        while (running = true) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;
            if (unprocessed >= 1.0) {
                tick();
                KeyInput.update();
                MouseInput.update();
                unprocessed--;
                tps++;
                canRender = true;
            } else {
            	canRender = false;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (canRender = true) {
                render();
                fps++;
            }

            if (System.currentTimeMillis() - 1000 > timer) {
                timer += 1000;
                System.out.printf("FPS: %d \n",  tps);
                fps = 0;
                tps = 0;
            }
            
        }

        System.exit(0);
	}
	
	
	/**
	 * pre: N/A
	 * post: Game Starts
	 */
	public static void main(String[] args) {
		
		DoodleDash game = new DoodleDash();
		JFrame jframe = new JFrame(TITLE);
		jframe.add(game);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.setResizable(false);
		jframe.setVisible(true);
		jframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.err.println("Exiting Game");
                System.exit(0);
                stop();
            }
        });
		jframe.setLocationRelativeTo(null);
		jframe.setFocusable(true);
		game.start();
		
		
	}

	

}
