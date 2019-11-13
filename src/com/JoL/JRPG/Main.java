package com.JoL.JRPG;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.JoL.JRPG.graphics.Images;

public class Main extends Canvas implements Runnable {
	public static int WIDTH = 640, HEIGHT = 480;
	
	private boolean running;
	private Thread thread;
	
	private Screen screen;
	
	public Main() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		 
		setSize(size);
		
		//Load all images
		new Images();
		 
		screen = new Screen(WIDTH, HEIGHT);
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
 
	public synchronized void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static double deltaTime = 0;
	public void run() {
		long last = System.nanoTime();
		long lastAcc = last;
		int frames = 0;
		double accDeltaTime = 0;
		while(running) {
			deltaTime = System.nanoTime() - last;
			accDeltaTime += deltaTime;
			last = System.nanoTime();
			
			tick();
			render();
			
			frames++;
			
			if (System.nanoTime() - lastAcc >= 1e9) {
				System.out.println("FPS: " + frames);
				
				lastAcc = System.nanoTime();
				frames = 0;
			}
			
			/*if (frames > 60) {
				try {
					Thread.sleep((int) ((1.0 - (accDeltaTime > 1 ? 1 : accDeltaTime))*1000.0));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				accDeltaTime = 0;
			}*/
		}
	}
	
	public static double deltaTime() {
		return deltaTime * 1e-9;
	}
	 
	private void tick() {
	}
 
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());
		
		screen.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Main game = new Main();
		
		JFrame frame = new JFrame("Graphics Engine");
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(game, 0);
		
		frame.setContentPane(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		 
		frame.setSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setPreferredSize(frame.getSize());
		
		game.start();
	}
}
