package com.zhrfrd.rain.level;

import java.util.ArrayList;
import java.util.List;

import com.zhrfrd.rain.entity.Entity;
import com.zhrfrd.rain.graphics.Screen;
import com.zhrfrd.rain.level.tile.Tile;

public class Level {
	protected int width, height;
	protected int [] tilesInt;
	protected int [] tiles;   //Contains all the level tiles
	public static Level spawn = new SpawnLevel ("/levels/spawn.png");
	private List <Entity> entities = new ArrayList <Entity> ();

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int [width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel () {
	}

	protected void loadLevel (String path) {
	}

	public void update () {
		for (int i = 0; i < entities.size (); i ++) {
			entities.get(i).update();
		}
	}

	private void time () {
	}

	public void render (int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;   //Beginning left of the screen
		int x1 = (xScroll + screen.width + 16) >> 4;   //End right of the screen + 16 to not show black space
		int y0 = yScroll >> 4;   //Beginning top of the screen
		int y1 = (yScroll + screen.height + 16) >> 4;   //End bottom of the screen + 16 to not show black space
		for (int y = y0; y < y1; y ++) {
			for (int x = x0; x < x1; x ++) {
				getTile (x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size (); i ++) {
			entities.get(i).render(screen);
		}
	}
	
	public void add (Entity e) {
		entities.add(e);
	}

	public Tile getTile (int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.voidTile;
		if (tiles [x + y * width] == Tile.col_spawn_floor) return Tile.spawn_floor;
		if (tiles [x + y * width] == Tile.col_spawn_grass) return Tile.spawn_grass;
		if (tiles [x + y * width] == Tile.col_spawn_hedge) return Tile.spawn_hedge;
		if (tiles [x + y * width] == Tile.col_spawn_wall1) return Tile.spawn_wall1;
		if (tiles [x + y * width] == Tile.col_spawn_wall2) return Tile.spawn_wall2;
		if (tiles [x + y * width] == Tile.col_spawn_water) return Tile.spawn_water;
		return Tile.voidTile;
	}
}
