package com.zhrfrd.rain.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.zhrfrd.rain.entity.Entity;
import com.zhrfrd.rain.entity.projectile.Projectile;
import com.zhrfrd.rain.entity.projectile.WizardProjectile;
import com.zhrfrd.rain.graphics.Sprite;

public abstract class Mob extends Entity {
	protected Sprite sprite;
	protected int dir = 0;   //Direction 0-North, 1-East, 2-South, 3-West
	protected boolean moving = false;
	
	public void move (int xa, int ya) {
		System.out.println ("Size: " + level.getProjectiles().size());
		if (xa != 0 && ya != 0) {   //If we are trying to move diagonally keep moving during collision
			move (xa, 0);   //
			move (0, ya);   //Split the two movements xa and ya
			return;
		}
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		if (!collision (xa, ya)) {
			x += xa;
			y += ya;
		}
	}
	
	@Override
	public void update () {
		
	}
	
	protected void shoot (int x, int y, double dir) {
//		dir *= 180 / Math.PI;
		Projectile  p = new WizardProjectile (x, y, dir);
		level.addProjectile(p);
	}
	
	private boolean collision (int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c ++) {   //Check each corner
			int xt = ((x + xa) + c  % 2 * 14 - 8) /16;
			int yt = ((y + ya) + c  / 2 * 12 + 3) /16;
			if (level.getTile(xt, yt).solid())
				solid = true;
		}
		return solid;
	}
	
	public void render () {
		
	}
}
