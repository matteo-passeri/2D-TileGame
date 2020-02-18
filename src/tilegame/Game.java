package tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import tilegame.display.Display;
import tilegame.gfx.Assets;
import tilegame.gfx.GameCamera;
import tilegame.input.KeyManager;
import tilegame.input.MouseManager;
import tilegame.states.GameState;
import tilegame.states.MenuState;
import tilegame.states.State;

public class Game implements Runnable {
	
	private Display display;	
	private int width, height;
	public String title;
	
	private boolean running = false;	
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
//	private BufferedImage ball;
//	private spriteSheet sheet;
	
	// states
	public State gameState;
	public State menuState;
	
	// input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	// Camera
	private GameCamera gameCamera;
	
	// Handler
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;	
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		// we need both Listener for Frame and Canvas
		// depending on which is on focus
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
		
//		ball = imageLoader.loadImage("/texture/bouncingBall.png");
//		sheet = new spriteSheet(ball);
	}
	
	// update variable
	private void tick() {
		keyManager.tick();
		
		if(State.getState() != null) 
			State.getState().tick();
		
	}
	
	// render
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// clear first
		g.clearRect(0, 0, width, height);
		// draw start
		
		if(State.getState() != null) 
			State.getState().render(g);
		
		
		// draw stop
		bs.show();
		g.dispose();
	}
	

	@Override
	public void run() {
		
		init();		
		
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		// checking FPS...
		long timer = 0;
		int ticks = 0;
		//...
		
		
		while (running) {
			 now = System.nanoTime();
			 delta += (now - lastTime) / timePerTick;
			// checking FPS...
			 timer += now - lastTime;
			 // ...
			 lastTime = now;
			 
			if( delta >= 1) {
			tick();
			render();
			// checking FPS...
			ticks++;
			// ...			
			delta--;
			}
			
			// checking FPS...
			if(timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks + " per Second.");
				ticks = 0;
				timer = 0;
			}
			
			
		}
		
		stop();
		
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public synchronized void start() {
		 if(running)
				return;
		 running = true;		
		 thread = new Thread(this);
		 thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
