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
		this.xa = random.nextGaussian() + 1.8;   //Random number between -1 and 1 that is more likely to be closer to 0  
		if (this.xa < 0)
			xa = 0.1;   //Avoid few particles passing the wall 
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
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}
	
	@Override
	public void render (Screen screen) {
		screen.renderSprite((int) xx - 12, (int) yy - (int) zz, sprite, true);
	}
}
