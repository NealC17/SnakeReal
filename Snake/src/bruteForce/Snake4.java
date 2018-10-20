package bruteForce;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Snake4 extends JPanel {

	private int[] x;
	private int[] y;
	private boolean[] shown;
	private Food f;
	private int shownUpTo;

	public static final int width = 30;
	public static final int speed = width;
	public static final int arrayLength = Coordinator4.boardHeight * Coordinator4.boardWidth / 900;

	private boolean up, down, left, right;

	Node[][] nodes;

	public Snake4() {
		super.setBounds(0, 0, 0, 0);

		reset();

	}

	public void reset() {
		int count = 0;
		nodes = new Node[Coordinator4.boardWidth / 30][Coordinator4.boardHeight / 30];
		for (int i = 0; i < nodes.length; i++) {
			for (int k = 0; k < nodes[i].length; k++) {
				nodes[i][k] = new Node(width * i, width * k, count);
				count++;
			}
		}
		shownUpTo = 1;
		x = new int[arrayLength];
		y = new int[arrayLength];
		shown = new boolean[arrayLength];
		f = new Food();

		x[0] = 450 + 30;
		y[0] = 450 - 60;

		for (int i = 1; i < x.length; i++) {
			x[i] = x[0] + i * width;
			y[i] = y[0];

		}

		left = true;

	}

	public int getShownUpTo() {
		return shownUpTo;
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
		for (int i = 0; i < nodes.length; i++) {

			for (int k = 0; k < nodes[i].length; k++) {
				g.drawRect(nodes[i][k].getX(), nodes[i][k].getY(), width, width);
				g.drawString(nodes[i][k].toString(), nodes[i][k].getX(), nodes[i][k].getY());
			}
		}

	}

	public void move() {

		// followBoard();
		// followFood();
		// checkCollision();

		for (int i = x.length - 1; i > 0; i--) {
			x[i] = x[(i - 1)];
			y[i] = y[(i - 1)];
		}

		if (left) {
			x[0] -= speed;
		} else if (right) {
			x[0] += speed;
		} else if (up) {
			y[0] -= speed;
		} else if (down) {
			y[0] += speed;
		}

	}

	public boolean isCollided() {
		if (x[0] < 0 || x[0] > Coordinator4.frame.getWidth() || y[0] < 0 || y[0] > Coordinator4.frame.getHeight())
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

			int increment = 5;
			shownUpTo += increment;
			for (int i = 1; i < getShownUpTo(); i++) {
				shown[i] = true;
			}

			f.setNewPos(x, y, shown);

		}

	}

	public void update() {
		move();
		AStar();
		eatenFood();
	}

	public void AStar() {

	}

	public void followBoard() {

		if (x[0] == 0) {
			down = true;
			left = false;
		}
		if (y[0] >= 840) {
			down = false;
			right = true;
		}
		if (x[0] >= Coordinator4.boardWidth - width) {
			right = false;
			up = true;
		}
		if (y[0] < 30) {
			up = false;
			left = true;
		}
		if (x[0] < 30) {
			down = true;
			left = false;
		}
	}

	public void followFood() {
		int distX = x[0] - f.getX();
		int distY = y[0] - f.getY();

		if (distX < 10 && distY < 0) {
			up = false;
			down = true;
			right = false;
			left = false;
		}

	}

	private void checkCollision() {
		for (int i = 0; i < arrayLength; i++) {

			if (down && shown[i] && y[i] - width == y[0] && x[i] == x[0]) {
				down = false;
				left = true;
			}

		}

	}

}
