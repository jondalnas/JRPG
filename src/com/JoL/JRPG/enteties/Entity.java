package com.JoL.JRPG.enteties;

import java.awt.Graphics;

public abstract class Entity {
	public double x, y;
	
	public abstract void tick();
	public abstract void render(Graphics g);
}
