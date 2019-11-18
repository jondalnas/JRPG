package com.JoL.JRPG;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.JoL.JRPG.enteties.Entity;
import com.JoL.JRPG.enteties.Player;
import com.JoL.JRPG.graphics.Images;
import com.JoL.JRPG.graphics.map.Map3D;

public class Map {
	public List<Entity> entities = new ArrayList<Entity>();
	
	private Map3D map;
	
	private int width, height;
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		
		map = new Map3D(width, height);
		entities.add(new Player());
	}
	
	public void tick() {
		for (Entity e : entities) {
			e.tick();
		}
	}
	
	public void render(Graphics g) {
		map.render(g, entities.get(0).x, entities.get(0).y);
		
		for (Entity e : entities) {
			e.render(g);
		}
	}
}
