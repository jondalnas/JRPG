package com.JoL.JRPG;

import java.awt.Color;
import java.awt.Graphics;

public class Screen {
	public int width, height;
	
	public Screen(int w, int h) {
		width = w;
		height = h;
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.blue);
		
	}
}
