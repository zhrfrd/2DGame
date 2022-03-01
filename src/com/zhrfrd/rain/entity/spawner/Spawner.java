package com.zhrfrd.rain.entity.spawner;

import java.util.ArrayList;
import java.util.List;

import com.zhrfrd.rain.entity.Entity;
import com.zhrfrd.rain.entity.particle.Particle;
import com.zhrfrd.rain.level.Level;

public class Spawner extends Entity{ 
	private List<Entity> entities = new ArrayList<Entity> ();
	public enum Type {
		MOB, PARTICLE
	}
	private Type type; 
	
	public Spawner (int x, int y, Type type, int amount, Level level) {
		init (level);
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
