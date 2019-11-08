package com.JoL.JRPG.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Map3D {
	public int width, height;
	
	private BufferedImage img;
	private int[] pixels;
	
	public Map3D(int width, int height) {
		this.width = width;
		this.height = height;

		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
	}
	
	public void render(Graphics g) {
		pixels[0] = 0xff00ff;
		pixels[1] = 0xff00ff;
		pixels[2] = 0xff00ff;
		pixels[3] = 0xff00ff;
		pixels[4] = 0xff00ff;
		pixels[5] = 0xff00ff;
		
		g.drawImage(img, 0, 0, width, height, null);
	}
}
