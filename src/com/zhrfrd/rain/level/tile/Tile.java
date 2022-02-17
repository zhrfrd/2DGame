package com.zhrfrd.rain.level.tile;

import com.zhrfrd.rain.graphics.Screen;
import com.zhrfrd.rain.graphics.Sprite;
import com.zhrfrd.rain.level.tile.spawn_level.SpawnFloorTile;
import com.zhrfrd.rain.level.tile.spawn_level.SpawnGrassTile;
import com.zhrfrd.rain.level.tile.spawn_level.SpawnHedgeTile;
import com.zhrfrd.rain.level.tile.spawn_level.SpawnWallTile;
import com.zhrfrd.rain.level.tile.spawn_level.SpawnWaterTile;

public class Tile {
	public int x, y;
	public Sprite sprite;
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile rock = new FlowerTile(Sprite.flower);
	public static Tile flower = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_hedge = new SpawnHedgeTile(Sprite.spawn_hedge);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_wall1 = new SpawnWallTile(Sprite.spawn_wall1);
	public static Tile spawn_wall2 = new SpawnWallTile(Sprite.spawn_wall2);
	public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);
	public static final int col_spawn_grass = 0xff00ff00;
	public static final int col_spawn_hedge = 0;   //Unused
	public static final int col_spawn_water = 0;   //Unused
	public static final int col_spawn_wall1 = 0xff7e7c66;
	public static final int col_spawn_wall2 = 0xff000000;
	public static final int col_spawn_floor = 0xffa36f00;

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render (int x, int y, Screen screen) {
	}

	public boolean solid () {
		return false;
	}
}
