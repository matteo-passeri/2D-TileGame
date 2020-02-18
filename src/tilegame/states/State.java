package tilegame.states;

import java.awt.Graphics;

import tilegame.Handler;

public abstract class State {
	
	// GameState Manager
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;		
	}
	
	public static State getState() {
		return currentState;		
	}
	// end GameState Manager
	
	// class
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
