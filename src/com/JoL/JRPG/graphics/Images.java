package com.JoL.JRPG.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	public static Bitmap mario = loadBitmapImage("map.png");
	public static Bitmap denmark = loadBitmapImage("Denmark-Map.jpg");
	
	private static BufferedImage loadImage(String imageName) {
		try {
			return ImageIO.read(Images.class.getResource("/tex/" + imageName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static Bitmap loadBitmapImage(String imageName) {
		try {
			BufferedImage img = ImageIO.read(Images.class.getResource("/tex/" + imageName));

			int w = img.getWidth();
			int h = img.getHeight();
			Bitmap result = new Bitmap(w, h);
			img.getRGB(0, 0, w, h, result.pixels, 0, w);
			
			for (int i = 0; i < result.pixels.length; i++) {
				int pixel = result.pixels[i];
				if (pixel == 0xffff00ff) result.pixels[i] = -1;
			}
			
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
