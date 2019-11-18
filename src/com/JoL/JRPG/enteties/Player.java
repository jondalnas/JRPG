package com.JoL.JRPG.enteties;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.JoL.JRPG.Input;
import com.JoL.JRPG.Main;
import com.JoL.JRPG.graphics.Images;

public class Player extends Entity {
	public void tick() {
		double xSpeed = 0, ySpeed = 0;
		
		if (Input.keys[KeyEvent.VK_S]) {
			ySpeed = Main.deltaTime() * 100;
		}
		if (Input.keys[KeyEvent.VK_W]) {
			ySpeed = -Main.deltaTime() * 100;
		}
		if (Input.keys[KeyEvent.VK_D]) {
			xSpeed = Main.deltaTime() * 100;
		}
		if (Input.keys[KeyEvent.VK_A]) {
			xSpeed = -Main.deltaTime() * 100;
		}
		
		move(xSpeed, ySpeed);
	}
	
	public void move(double xSpeed, double ySpeed) {
		if (xSpeed != 0 && ySpeed != 0) {
			move(xSpeed, 0);
			move(0, ySpeed);
			return;
		}
		
		double newX = x + xSpeed;
		double newY = y + ySpeed;
		
		if (newX < 0 || newX >= Images.mapCollision.width || newY < 0 || newY >= Images.mapCollision.height) return;
		
		int tileColor = Images.mapCollision.pixels[(int) newX + (int) newY * Images.mapCollision.width];
		if (tileColor == 0) return;
		
		x = newX;
		y = newY;
	}
	
	public void render(Graphics g) {
		g.drawImage(Images.spritesheet, Main.WIDTH / 2 - (16 / 2) * 4, Main.HEIGHT / 2 - 16 * 4, Main.WIDTH / 2 + (16 / 2) * 4, Main.HEIGHT / 2, 0, 0, 16, 16, null);
	}
}
