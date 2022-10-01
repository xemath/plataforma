package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {
	private MouseInputs mouseInputs;
	private float xDelta = 100, yDelta = 100;
	private int frames = 0;
	private long lastCheck = 0;
	private float xDir=0.03f, yDir=0.03f;
	private Color color = new Color(120, 90, 255);
	private Random random;
	
	public GamePanel() {
		random = new Random();
		mouseInputs = new MouseInputs(this);
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}
	public void changeXDelta(int value) {
		this.xDelta += value;
		repaint();
	}
	public void changeYDelta(int value) {
		this.yDelta += value;
		repaint();
	}
	public void moveRect(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		updateRectangle();
		
		g.fillRect((int)xDelta, (int)yDelta, 200, 50);
		
		frames ++;
		if(System.currentTimeMillis() - lastCheck >= 1000) {
			lastCheck = System.currentTimeMillis();
			System.out.println("FPS: " + frames);
			frames = 0;
		}
		repaint();
		
	}
	private Color getColorRandom() {
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		return new  Color(r, g, b);
	}
	private void updateRectangle() {
		xDelta += xDir;
		if(xDelta > 400 || xDelta < 0)
			xDir *=-1;
			color = getColorRandom();
		yDelta+=yDir;
		if(yDelta > 400 || yDelta < 0)
			yDir *=-1;
			color = getColorRandom();
		
	}
}
