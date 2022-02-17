package com.zhrfrd.rain.entity.projectile;

import com.zhrfrd.rain.graphics.Screen;
import com.zhrfrd.rain.graphics.Sprite;

public class WizardProjectile extends Projectile {

	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 20;
		rateOfFire = 15;
		sprite = Sprite.grass;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	@Override
	public void update () {
		move ();
	}
	
	@Override
	protected void move () {
		x += nx;
		y += ny;
	}
	
	@Override
	public void render (Screen screen) {
		screen.renderTile(x, y, sprite);
	}
}
