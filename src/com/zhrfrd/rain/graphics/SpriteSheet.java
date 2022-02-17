package com.zhrfrd.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	public final int SIZE;
	public int [] pixels;
	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 256);
	public static SpriteSheet spawn_level = new SpriteSheet ("/textures/sheets/spawn_level.png", 48);

	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		pixels = new int [SIZE * SIZE];
		load();
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