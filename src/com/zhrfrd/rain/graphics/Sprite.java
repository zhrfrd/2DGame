package com.zhrfrd.rain.graphics;

public class Sprite {
	public final int SIZE;
	private int x, y; // Coordinates of the sprite inside the sprite sheet
	private int height, width;
	public int [] pixels;
	private SpriteSheet sheet; // Because the sprite can be in different sprite sheets you need to declare which sprite sheet
	public static Sprite grass = new Sprite(16, 0, 2, SpriteSheet.tiles);
	public static Sprite flower = new Sprite (16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite (16, 1, 2, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1b87e0);
	//Player sprites
	public static Sprite player_forward = new Sprite (32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_back = new Sprite (32, 2, 5, SpriteSheet.tiles);
	public static Sprite player_side = new Sprite (32, 1, 5, SpriteSheet.tiles);
	public static Sprite player_forward_1 = new Sprite (32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_forward_2 = new Sprite (32, 0, 7, SpriteSheet.tiles);
	public static Sprite player_back_1 = new Sprite (32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_back_2 = new Sprite (32, 2, 7, SpriteSheet.tiles);
	public static Sprite player_side_1 = new Sprite (32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_side_2 = new Sprite (32, 1, 7, SpriteSheet.tiles);
	//Spawn level sprites
	public static Sprite spawn_grass = new Sprite (16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_hedge = new Sprite (16, 1, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_water = new Sprite (16, 2, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_wall1 = new Sprite (16, 0, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_wall2 = new Sprite (16, 0, 2, SpriteSheet.spawn_level);
	public static Sprite spawn_floor = new Sprite (16, 1, 1, SpriteSheet.spawn_level);
	//Projectiles sprites
	public static Sprite projectile_wizard = new Sprite (16, 0, 0, SpriteSheet.projectile_wizard);
	//Particles
	public static Sprite particle_normal = new Sprite (3, 0xaaaaaa);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int [SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite (int width, int height, int colour) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int [width * height];
		setColour (colour);
	}

	public Sprite(int size, int colour) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int [SIZE * SIZE];
		setColour(colour);
	}

	public void setColour (int colour) {
		for (int i = 0; i < width * height; i++) {
			pixels [i] = colour;
		}
	}
	
	public int getWidth () {
		return width;
	}
	
	public int getHeight () {
		return height;
	}

	private void load () {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels [x + y * SIZE] = sheet.pixels [(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}