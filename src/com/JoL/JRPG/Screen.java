package com.JoL.JRPG;

import java.awt.Color;
import java.awt.Graphics;

import com.JoL.JRPG.map.Map3D;

public class Screen {
	public int width, height;
	private Map3D map;
	
	public Screen(int w, int h) {
		width = w;
		height = h;
		
		map = new Map3D(w, h);
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.blue);
		g.fillRect(50, 50, width-50, height-50);
		
		map.render(g);
	}
}
