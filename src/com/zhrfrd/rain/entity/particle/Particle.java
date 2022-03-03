package com.zhrfrd.rain.entity.particle;

import com.zhrfrd.rain.entity.Entity;
import com.zhrfrd.rain.graphics.Screen;
import com.zhrfrd.rain.graphics.Sprite;

public class Particle extends Entity {
	private Sprite sprite;
	private int life;
	private int time = 0;
	protected double xx, yy, zz;   // 
	protected double xa, ya, za;   //Amount of pixels the particle move in the x axis and y axis
	
	//Creates one single particle. It can be called by Particle (int x, int y, int life, int amount) repeatedly to have multiple particles
	public Particle (int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(20) -10);
		sprite = Sprite.particle_normal;		
		this.xa = random.nextGaussian();   //Random number between -1 and 1 that is more likely to be closer to 0  
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}
	
	@Override
	public void update () {
		time ++;
		if (time >= 7400)   //Avoid crash 
			time = 0;
		if (time > life)
			remove ();
		za -= 0.1;
		if (zz < 0) {
			zz = 0;
			za *= -0.55;
			xa *= 0.4;
			ya *= 0.4;
		}
		move (xx + xa, (yy + ya) + (zz + za));
	}
	
	private void move (double x, double y) {
		if (collision (x, y)) {
			this.xa *= - 0.5;
			this.ya *= - 0.5;
			this.za *= - 0.5;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za; 
	}
	
	public boolean collision (double x, double y) {   //The offsets are necessary because the projectile is in the center of the sprite tile
		boolean solid = false;
		for (int c = 0; c < 4; c ++) {   //Check each corner
			double xt = (x - c  % 2 * 16) / 16;
			double yt = (y - c  / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);   //Round up
			int iy = (int) Math.ceil(yt);   //
			if (c % 2 == 0) ix = (int) Math.floor(xt);   //If collision in right side round down for precise collision
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid())
				solid = true;
		}
		return solid;
	}

	@Override
	public void render (Screen screen) {
		screen.renderSprite((int) xx, (int) yy - (int) zz, sprite, true);
	}
}
