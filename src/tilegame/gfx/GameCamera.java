package tilegame.gfx;

import tilegame.Game;
import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.tile.Tile;

public class GameCamera {
	
	private Handler handler;
	private float xOffSet, yOffSet;
	
	public GameCamera (Handler handler, float xOffSet, float yOffSet){
		this.handler = handler;
		this.xOffSet = xOffSet;
		this.yOffSet = yOffSet;
	}
	
	public void checkBlankSpace() {
		if(xOffSet < 0) {
			xOffSet = 0;
		}else if(xOffSet > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
			xOffSet = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}
		
		if(yOffSet < 0) {
			yOffSet = 0;
		}else if(yOffSet > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
			yOffSet = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		}
	}
	
	public void centerOnEntity(Entity e) {
		xOffSet = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffSet = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}
	
	public void move(float xAmt, float yAmt) {
		xOffSet += xAmt;
		yOffSet += yAmt;
		checkBlankSpace();
	}

	public float getxOffSet() {
		return xOffSet;
	}

	public void setxOffSet(float xOffSet) {
		this.xOffSet = xOffSet;
	}

	public float getyOffSet() {
		return yOffSet;
	}

	public void setyOffSet(float yOffSet) {
		this.yOffSet = yOffSet;
	}
}
