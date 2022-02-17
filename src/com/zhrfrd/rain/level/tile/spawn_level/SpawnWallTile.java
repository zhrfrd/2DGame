package com.zhrfrd.rain.level.tile.spawn_level;

import com.zhrfrd.rain.graphics.Screen;
import com.zhrfrd.rain.graphics.Sprite;
import com.zhrfrd.rain.level.tile.Tile;

public class SpawnWallTile extends Tile {

	public SpawnWallTile(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void render (int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);   //Pixel precision
	}
	
	@Override
	public boolean solid () {
		return true;
	}
}
