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
	
	double time;
	public void render(Graphics g) {
		time += Main.deltaTime();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xffffff;
		}
		
		double i = Math.sin(time) * Math.PI;
		
		render3D(Images.test, i);
		
		g.drawImage(img, 0, 0, width, height, null);
	}
	
	private void render3D(Bitmap map, double tilt) {
		double yOffs = (Math.sin(tilt)) * 100.0;
		double xOffs = Math.cos(tilt) * 100.0;
		
		for (int y = 0; y < map.height; y++) {
			double scale = (y*0.01 + 1) / 2;
			
			int yPix = (int) ((y + (int) yOffs) / (scale * 2));
			int yy = y;// - (int) ((Math.sin(i) + 1) * 500);
			
			if (yy < 0 || yy >= height || yPix < 0 || yPix >= map.height) continue;
			
			double mapWidth = map.width * scale;
			
			for (int x = (int) -mapWidth; x < mapWidth; x++) {
				int xx = x + width / 2;
				if (xx < 0 || xx >= width) continue;
				
				int xPix = (int) (((x - xOffs) + mapWidth) / (scale * 2));
				if (xPix < 0 || xPix >= map.width) continue;
				
				pixels[xx+yy*width] = map.pixels[xPix+yPix*map.width];
			}
		}
	}
}
