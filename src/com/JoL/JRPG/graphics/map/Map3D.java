package com.JoL.JRPG.graphics.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.JoL.JRPG.Main;
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
		
		double i = (Math.sin(time * 0.0000000000001) + 1)/2*Math.PI;
		System.out.println(i);
		
		for (int y = 0; y < Images.denmark.height; y++) {
			int yPix = y;
			int yy = y;// - (int) ((Math.sin(i) + 1) * 500);
			
			if (yy < 0 || yy >= height || yPix < 0 || yPix >= Images.denmark.height) continue;
			
			for (int x = 0; x < Images.denmark.width * (y*0.01 + 1); x++) {
				int xPix = (int) ((x + (-width / 2 + (int) ((Math.cos(i)) * 1000) + Images.denmark.width / 2)) / (y*0.01 + 1));
				int xx = x;
				
				if (xx < 0 || xx >= width || xPix < 0 || xPix >= Images.denmark.width) continue;
				
				pixels[xx+yy*width] = Images.denmark.pixels[xPix+yPix*Images.denmark.width];
			}
		}
		
		g.drawImage(img, 0, 0, width, height, null);
	}
}
