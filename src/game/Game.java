package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;



public class Game {

	public final static int WIDTH = 800, HEIGHT = 600;

	private String gameName = "Flappy Bird";

	private Canvas game = new Canvas();

	private Input input;

	private ArrayList<Updatable> updatables = new ArrayList<>();
	private ArrayList<Renderable> renderables = new ArrayList<>();

	// When working outside of the game class I wont be able to access the private
	// arrays.
	// Helper methods to access the private array lists outside of this class.
	public void addUpdatable(Updatable u) {
		updatables.add(u);
	}

	public void removeUpdatable(Updatable u) {
		updatables.remove(u);
	}

	public void addRenderable(Renderable r) {
		renderables.add(r);
	}

	public void removeRenderable(Renderable r) {
		renderables.remove(r);
	}

	public void start() {
		// Initialize the windows
		Dimension gameSize = new Dimension(Game.WIDTH, Game.HEIGHT);

		// Set program title name
		JFrame gameWindow = new JFrame(gameName);
		// Close the window with and program with X
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gameWindow.setSize(gameSize);

		// code does not account for resizeable window.
		gameWindow.setResizable(false);
		gameWindow.setVisible(true);

		game.setSize(gameSize);
		game.setMinimumSize(gameSize);
		game.setMaximumSize(gameSize);
		game.setPreferredSize(gameSize);
		gameWindow.add(game);
		gameWindow.setLocationRelativeTo(null);

		// init input

		// Game Loop
		final int TICKS_PER_SECOND = 60;
		final int TIME_PER_TICK = 1000 / TICKS_PER_SECOND;
		final int MAX_FRAMESKIPS = 5;

		long nextGameTick = System.currentTimeMillis();
		int loops;
		// were gonna generate a value for amount of times for the render. Allows us to
		// render the game characters through an update.
		float interpolation;

		long timeAtLastFPSCheck = 0;
		int ticks = 0;

		boolean running = true;
		while (running) {
			
			
			//Testing the render method with red square 
			//update();
			//render(1.0f);

		}

		// Game ends
	}

	private void update() {
		for (Updatable u : updatables) {
			u.update(input);
		}
	}

	private void render(float interpolation) {

		// double buffered. while the game is being rendered its always looking to pre
		// render the next frame to have a smooth transition,
		// THis prevent flickering and any other visual errors. Double buffering is
		// important

		BufferStrategy b = game.getBufferStrategy();

		// Buffer wont run first time when game starts. Checking here if the buffer is
		// running first time
		if (b == null) {
			game.createBufferStrategy(2);
			return;
		}

		Graphics2D g = (Graphics2D) b.getDrawGraphics();

		g.clearRect(0, 0, game.getWidth(), game.getHeight());

		for (Renderable r : renderables) {
			r.render(g, interpolation);
		}

		g.dispose();
		b.show();

	}

	public static void main(String[] args) {
		Game g = new Game();

		g.renderables.add(new Renderable() {
			@Override
			public void render(Graphics2D g, float interpolation) {
				g.setColor(Color.RED);
				g.drawRect(300, 250, 50, 100);

			}
		});

		g.start();
	}
}
