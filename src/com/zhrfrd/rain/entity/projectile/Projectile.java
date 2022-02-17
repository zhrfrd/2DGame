package com.zhrfrd.rain.entity.projectile;

import com.zhrfrd.rain.entity.Entity;
import com.zhrfrd.rain.graphics.Sprite;

public abstract class Projectile extends Entity {
	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	public double speed, rateOfFire, range, damage;
	
	public Projectile (int x, int y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	protected void move () {
		
	}
}
