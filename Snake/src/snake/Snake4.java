package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Snake4 extends JPanel implements KeyListener {

	private int vx, vy;
	private int[] x;
	private int[] y;
	private boolean[] shown;
	private Food f;
	private int shownUpTo;

	public static final int width = 30;
	public static final int speed = width;
	private int lastX;
	private int lastY;
	public static final int arrayLength = Coordinator.boardHeight * Coordinator.boardWidth / 900;

	private boolean upDirection, downDirection, leftDirection, rightDirection;

	public Snake4() {
		shownUpTo = 1;
		x = new int[arrayLength];
		y = new int[arrayLength];
		shown = new boolean[arrayLength];
		f = new Food();

		x[0] = 300;
		y[0] = 300;

		for (int i = 1; i < x.length; i++) {
			x[i] = x[0] + i * width;
			y[i] = y[0];

		}
		upDirection = false;
		downDirection = false;
		leftDirection = false;
		rightDirection = false;

	}

	public int getLastX() {
		lastX = x[0];
		return lastX;
	}

	public int getLastY() {
		lastY = y[0];
		return lastY;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		f.drawFood(g);

		g.setColor(Color.red);
		g.fillRect(x[0], y[0], width, width);

		g.setColor(Color.black);
		g.drawRect(x[0], y[0], width, width);

		for (int i = 1; i < x.length; i++) {
			if (shown[i]) {
				g.setColor(Color.green);
				g.fillRect(x[i], y[i], width, width);
				g.setColor(Color.black);
				g.drawRect(x[i], y[i], width, width);
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_DOWN) {
			upDirection = false;
			downDirection = true;
			leftDirection = false;
			rightDirection = false;

		} else if (code == KeyEvent.VK_UP) {
			upDirection = true;
			downDirection = false;
			leftDirection = false;
			rightDirection = false;

		} else if (code == KeyEvent.VK_LEFT) {
			upDirection = false;
			downDirection = false;
			leftDirection = true;
			rightDirection = false;

		} else if (code == KeyEvent.VK_RIGHT) {
			upDirection = false;
			downDirection = false;
			leftDirection = false;
			rightDirection = true;

		}

		else if (code == KeyEvent.VK_SPACE) {
			upDirection = false;
			downDirection = false;
			leftDirection = false;
			rightDirection = false;

		}

	}

	public void move() {
		x[0] += vx;
		y[0] += vy;

		System.out.println(x[1] + ", " + y[1] + "    " + x[0] + ", " + y[0]);

		for (int z = x.length - 1; z > 0; z--) {
			x[z] = x[(z - 1)];
			y[z] = y[(z - 1)];
		}

		if (leftDirection && (!rightDirection)) {
			x[0] -= speed;
		} else if (rightDirection && (!leftDirection)) {
			x[0] += speed;
		} else if (upDirection && (!downDirection)) {
			y[0] -= speed;
		} else if (downDirection && (!upDirection)) {
			y[0] += speed;
		}

	}

	public boolean isCollided() {
		if (x[0] < 0 || x[0] > Coordinator.boardWidth || y[0] < 0 || y[0] > Coordinator.boardWidth)
			return true;
		for (int i = 0; i < arrayLength; i++) {
			if (x[i] == x[0] && y[i] == y[0] && shown[i]) {
				return true;
			}
		}
		return false;
	}

	public void eatenFood() {

		if (f.getX() >= x[0] && f.getX() <= x[0] + width && f.getY() >= y[0] && f.getY() <= y[0] + width) {

			shownUpTo += 3;
			for (int i = 1; i < shownUpTo; i++) {
				shown[i] = true;
			}

			f.setNewPos(x, y, shown);

		}

	}

}
