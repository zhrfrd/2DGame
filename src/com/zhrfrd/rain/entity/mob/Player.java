package com.zhrfrd.rain.entity.mob;

import com.zhrfrd.rain.Game;
import com.zhrfrd.rain.Mouse;
import com.zhrfrd.rain.graphics.Screen;
import com.zhrfrd.rain.graphics.Sprite;
import com.zhrfrd.rain.input.Keyboard;

public class Player extends Mob{
	private Keyboard input;
	private Sprite sprite;
	private int anim;
	private boolean walking = false;
	
	public Player (Keyboard input ) {
		this.input = input;
		sprite = Sprite.player_forward;
	}
	
	public Player (int x , int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_forward;
	} 
	
	@Override
	public void update () {
		int xa = 0, ya = 0;
		if (anim < 7500)   //To avoid having an exception when it reaches the maximum capacity of an integer
			anim ++;
		else
			anim = 0;
		if (input.up) ya --;
		if (input.down) ya ++;
		if (input.left) xa --;
		if (input.right) xa ++;
		if (xa != 0 || ya != 0) {
			move (xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		updateShooting ();
	}
	
	private void updateShooting () {
		if (Mouse.getButton() == 1) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;   //X click mouse from center of screen
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;   //Y click mouse from center of screen
			double dir = Math.atan2(dy, dx);          //Direction (tangent)
			shoot (x, y, dir);   //Shoot from point (x,y) to direction dir	
		}
	}

	@Override
	public void render (Screen screen) {
		int flip = 0;
		if (dir == 0) {
			sprite = Sprite.player_forward;
			if (walking) {
				if (anim % 20 > 10)
					sprite = Sprite.player_forward_1;
				else
					sprite = Sprite.player_forward_2;
			}
		}
		if (dir == 1) {
			sprite = Sprite.player_side;
			if (walking) {
				if (anim % 20 > 10)
					sprite = Sprite.player_side_1;
				else
					sprite = Sprite.player_side_2;
			}
		}
		if (dir == 2) {
			sprite = Sprite.player_back;
			if (walking) {
				if (anim % 20 > 10)
					sprite = Sprite.player_back_1;
				else
					sprite = Sprite.player_back_2;
			}
		}
		if (dir == 3) {
			sprite = Sprite.player_side;
			if (walking) {
				if (anim % 20 > 10)
					sprite = Sprite.player_side_1;
				else
					sprite = Sprite.player_side_2;
			}
			flip = 1;
		}
		screen.renderPlayer(x - 16, y - 16, sprite, flip);   //Adjust player to the center 
	}
}
