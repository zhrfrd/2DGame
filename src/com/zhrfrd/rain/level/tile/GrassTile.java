package com.zhrfrd.rain.level.tile;

import com.zhrfrd.rain.graphics.Screen;
import com.zhrfrd.rain.graphics.Sprite;

public class GrassTile extends Tile {
	public GrassTile(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void render (int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);   //Pixel precision
	}
}