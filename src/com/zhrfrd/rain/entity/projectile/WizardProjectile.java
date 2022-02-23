package com.zhrfrd.rain.entity.projectile;

import com.zhrfrd.rain.graphics.Screen;
import com.zhrfrd.rain.graphics.Sprite;

public class WizardProjectile extends Projectile {
	public static final int FIRE_RATE = 10;   //Higher the number, slower the projectile shooting rate
	
	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 20;
		sprite = Sprite.projectile_wizard;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	@Override
	public void update () {
		if (level.tileCollision(x, y, nx, ny, 7))
			remove ();
		move ();
	}
	
	@Override
	protected void move () {
		x += nx;
		y += ny;
		if (distance () > range)
			remove ();
	}
	
	private double distance () {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x)) + (yOrigin - y) * (yOrigin - y));
		return dist;
	}

	@Override
	public void render (Screen screen) {
		screen.renderProjectile((int) x - 12, (int) y - 2, this);
	}
}
