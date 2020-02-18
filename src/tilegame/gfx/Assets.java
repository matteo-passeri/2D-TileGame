package tilegame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 64, height = 64;
	
	public static Font font28;
	
	public static BufferedImage[] player_still;
	public static BufferedImage tree, tree_snow, rock, dirt, grass, stone, sand, player_down_stand, player_up_stand, player_left_stand, player_right_stand;
	public static BufferedImage wood;
	public static BufferedImage[] animAttackHit, player_down, player_up, player_left, player_right;
	public static BufferedImage[] btn_start;
	public static BufferedImage inventoryScreen;
	
	public static void init() {
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/texture/sheet.png"));
		
		inventoryScreen = ImageLoader.loadImage("/texture/inventoryScreen.png");
		
		wood = sheet.crop(width*2, height *6, width, height);
		
		animAttackHit = new BufferedImage[1];
		animAttackHit[0] = sheet.crop(width, height *6, width, height);
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width *8, height *4, width*2, height);
		btn_start[1] = sheet.crop(width *8, height *5, width*2, height);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		player_still = new BufferedImage[4];
		
		player_still[0] = sheet.crop(width, height *5, width, height);
		player_still[1] = sheet.crop(width, height *4, width, height);
		player_still[2] = sheet.crop(width*4, height *4, width, height);
		player_still[3] = sheet.crop(width*4, height *5, width, height);
		
		
		player_down[0] = sheet.crop(0, height *4, width, height);
		player_down[1] = sheet.crop(width *2, height *4, width, height);
		
		player_up[0] = sheet.crop(0, height *5, width, height);
		player_up[1] = sheet.crop(width *2, height *5, width, height);
		
		player_left[0] = sheet.crop(width *3, height *4, width, height);
		player_left[1] = sheet.crop(width *5, height *4, width, height);
		
		player_right[0] = sheet.crop(width *3, height *5, width, height);
		player_right[1] = sheet.crop(width *5, height *5, width, height);
		
		
		stone = sheet.crop(0, 0, width, height);
		sand = sheet.crop(0, height, width, height);
		grass = sheet.crop(0, height*2, width, height);
		dirt = sheet.crop(0, height*3, width, height);
		
		rock = sheet.crop(0, height *6, width, height);
		tree = sheet.crop(width *7, height *4, width, height*2);
		tree_snow = sheet.crop(width *6, height *4, width, height*2);

	}

}
