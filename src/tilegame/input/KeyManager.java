package tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	public boolean attacking = false;
	
	private boolean[] keys, justPressed, cantPress;
	public boolean up, down, right, left;
	public boolean aUp, aDown, aRight, aLeft;
	
	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
		
	}
	
	public boolean keyJustPressed(int keyCode){
		  if(keyCode < 0 || keyCode >= keys.length)
		  return false;
		  return justPressed[keyCode];
		  }
	
	public void tick() {
		for (int i = 0; i < keys.length; i++) {
			if(cantPress[i] && !keys[i]){
				cantPress[i] = false;				
			} else if(justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			}
			if(!cantPress[i] && keys[i]) {
				justPressed[i] = true;
			}
		}
				
		up = keys[KeyEvent.VK_W];
		down= keys[KeyEvent.VK_S];
		left= keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		// attack buttons
		aUp = keys[KeyEvent.VK_UP];
		aDown = keys[KeyEvent.VK_DOWN];
		aLeft = keys[KeyEvent.VK_LEFT];
		aRight = keys[KeyEvent.VK_RIGHT];
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
		
		if(keys[e.getKeyCode()] == keys[KeyEvent.VK_UP] 
				|| keys[e.getKeyCode()] == keys[KeyEvent.VK_DOWN] 
				|| keys[e.getKeyCode()] == keys[KeyEvent.VK_LEFT] 
				|| keys[e.getKeyCode()] == keys[KeyEvent.VK_RIGHT])
			attacking = true;
		
		System.out.println("Pressed!" + attacking);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
		if(attacking == true)
			attacking = false;
		System.out.println(attacking);
	}

	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
