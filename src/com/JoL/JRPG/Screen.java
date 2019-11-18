package com.JoL.JRPG;

import java.awt.Color;
import java.awt.Graphics;

public class Screen {
	public int width, height;
	public Map map;
	
	public Screen(int w, int h) {
		width = w;
		height = h;
		
		map = new Map(w, h);
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.blue);
		g.fillRect(50, 50, width-50, height-50);
		
		map.render(g);
	}
}
