package com.zhrfrd.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.zhrfrd.rain.entity.mob.Player;
import com.zhrfrd.rain.graphics.Screen;
import com.zhrfrd.rain.graphics.SpriteSheet;
import com.zhrfrd.rain.input.Keyboard;
import com.zhrfrd.rain.level.Level;
import com.zhrfrd.rain.level.TileCoordinate;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L; // Don't need it and don't need to understand
	private static int width = 300;
	private static int height = width / 16 * 9; // Aspect ration 16:9
	private static int scale = 3;
	public static String title = "Rain";
	private Thread thread;
	private JFrame frame;
	private boolean running = false;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int [] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // Get the image raster, get the data buffer of the raster and convert it to a integer of data buffer
	private Screen screen;
	private Keyboard key;
	private Level level;
	private Player player;

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		frame = new JFrame();
		screen = new Screen(width, height);
		key = new Keyboard();
		level = Level.spawn;
		TileCoordinate playerSpawn = new TileCoordinate (16, 10);
		player = new Player (playerSpawn.x (), playerSpawn.y (), key);
		player.init(level);
		setPreferredSize(size); // Component method to set Canvas size
		addKeyListener(key);
		Mouse mouse = new Mouse ();
		addMouseListener (mouse);
		addMouseMotionListener (mouse);
	}

	public void start () { // Do not confuse with thread.start()
		running = true;
		thread = new Thread(this, "Display");
		thread.start(); // go to the run () method
	}

	public synchronized void stop () {
		running = false;
		try {
			thread.join(); // On closing, join all the threads together
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run () // Called in thread.start ();
	{
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0; // How many frames per second
		int updates = 0; // How many updates per second (it should be always 60)
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update(); // Update 60 times per second
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) { // Each second
				timer += 1000; // Increase by 10000 each time in order to keep the upper condition
				frame.setTitle(title + "  |  " + updates + "ups " + frames + "fps");
				updates = 0;
				frames = 0;
			}
		}
	}

	public void update () {
		key.update(); // Get the key pressed
		player.update();
		level.update ();
	}

	public void render () {
		BufferStrategy bs = getBufferStrategy(); // Get actual buffer strategy from the object Game (subclass of Canvas) and save it to bs. the first time you access render () bs is null
		if (bs == null) { // To avoid creating a buffer strategy each time you enter the method render () create a condition. The first time you access the method render () the object bs is null
			createBufferStrategy(3); // Triple buffering
			return;
		}
		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		screen.renderSheet (40, 40, SpriteSheet.player_down, false); 
		for (int i = 0; i < pixels.length; i++) { // Set the colour to each pixel
			pixels [i] = screen.pixels [i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font ("Verdana", 0, 50));
//		g.fillRect(Mouse.getX() - 32, Mouse.getY() - 32, 64, 64);
//		if (Mouse.getButton() != -1)
		g.dispose();
		bs.show(); // Show the content of the buffer in queue
	}
	
	public static int getWindowWidth () {
		return width * scale;
	}
	
	public static int getWindowHeight () {
		return height * scale;
	}

	public static void main (String [] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.add(game); // You can add it because game is a subclass of Canvas. You basically add a Canvas to the frame
		game.frame.pack(); // pack () sets the size of the frame according to the preferred size of the component game
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null); // Position frame in the center of the window
		game.frame.setVisible(true);
		game.start(); // Go to function start ();
		game.requestFocus(); // Focus on the application when open
	}
}
