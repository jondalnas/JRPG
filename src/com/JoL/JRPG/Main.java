package com.JoL.JRPG;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int WIDTH = 640, HEIGHT = 480;
	
	private boolean running;
	private Thread thread;
	
	private Screen screen;
	
	public Main() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		 
		setSize(size);
		 
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
		byte frames = 0;
		double accDeltaTime = 0;
		while(running) {
			deltaTime = System.nanoTime() - last;
			accDeltaTime += deltaTime;
			
			tick();
			render();
			
			frames++;
			if (frames > 60) {
				try {
					Thread.sleep((int) ((1.0 - (accDeltaTime > 1 ? 1 : accDeltaTime))*1000.0));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				accDeltaTime = 0;
			}
		}
	}
	
	public static double deltaTime() {
		return deltaTime;
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
