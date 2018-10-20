package snake;

import javax.swing.JFrame;

public class Coordinator {

	public static final int boardWidth = 30 * Snake4.width, boardHeight = 30 * Snake4.width;
	static JFrame frame;
	static Snake4 s;
	static boolean gameOn;

	public Coordinator() {

		s = new Snake4();
		frame = new JFrame("game ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, Coordinator.boardWidth + 100, Coordinator.boardHeight + 100);

		frame.getContentPane().add(s);
		frame.addKeyListener(s);

		frame.setVisible(true);

		gameOn = true;

	}

	public static void main(String[] args) {

		Coordinator a = new Coordinator();

		while (!s.isCollided()) {
			s.move();
			s.eatenFood();
			frame.repaint();
			try {
				Thread.sleep(75);
			} catch (InterruptedException e) {
			}
		}

	}

}
