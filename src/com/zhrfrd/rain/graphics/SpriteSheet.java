package com.zhrfrd.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	public final int SIZE, WIDTH, HEIGHT;
	public int [] pixels;
	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 256);
	public static SpriteSheet spawn_level = new SpriteSheet ("/textures/sheets/spawn_level.png", 48);
	public static SpriteSheet projectile_wizard = new SpriteSheet ("/textures/sheets/projectiles/projectile_wizard.png", 48);
	public static SpriteSheet player = new SpriteSheet ("/textures/sheets/player_sheet.png", 128, 96);
	public static SpriteSheet player_down = new SpriteSheet (player, 1, 0, 1, 3, 32);

	//Extract section of a spritesheet (example: player going down)
	public SpriteSheet (SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		SIZE = -1;   //Not square
		WIDTH = w;
		HEIGHT = h;
		pixels = new int [w * h];
		for (int y0 = 0; y0 < h; y0 ++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0 ++) {
				int xp = xx + x0;
				pixels [x0 + y0 * w] = sheet.pixels [xp + yp * sheet.WIDTH];
			}
		}
	}
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int [SIZE * SIZE];
		load();
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		if (width == height)
			SIZE = width;
		else
			SIZE = -1;   //Not square
		WIDTH = width;
		HEIGHT = height;
		pixels = new int [WIDTH * HEIGHT];
		load ();
	}

	private void load () {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path)); // Load the image from path
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w); // Translate image to pixels
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
