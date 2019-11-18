package com.JoL.JRPG.graphics.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.JoL.JRPG.Main;
import com.JoL.JRPG.graphics.Bitmap;
import com.JoL.JRPG.graphics.Images;

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
	
	public void render(Graphics g, double playerX, double playerY) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xffffff;
		}
		
		render3D(Images.map, 0.01, 4, playerX, playerY);
		
		g.drawImage(img, 0, 0, width, height, null);
	}
	
	private void render3D(Bitmap map, double tilt, double zoom, double xOffs, double yOffs) {
		int h = height / 2;
		int w = width / 2;
		
		for (int y = 0; y < height; y++) {
			double scale = 1.0 / ((y - h) * tilt + zoom);
			
			if (scale < 0) continue;
			
			int yPix = (int) ((y - h) * scale + yOffs);
			if (yPix < 0 || yPix >= map.height) continue;
			
			for (int x = 0; x < width; x++) {
				int xPix = (int) ((x - w) * scale + xOffs);
				if (xPix < 0 || xPix >= map.width) continue;
				
				pixels[x+y*width] = map.pixels[xPix+yPix*map.width];
			}
		}
	}
}
