package tilegame.states;

import java.awt.Graphics;

import tilegame.Handler;
import tilegame.gfx.Assets;
import tilegame.ui.ClickListener;
import tilegame.ui.UIImageButton;
import tilegame.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.btn_start, new ClickListener() {

			@Override
			public void onClick() {
				// UIManager has to be disable or 
				// utent can still click the buttons
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
		}));
	}
	
	

	@Override
	public void tick() {
		uiManager.tick();
		
		// temporally just for dev (skip menu)
		handler.getMouseManager().setUIManager(null);
		State.setState(handler.getGame().gameState);
		// end temp
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}
