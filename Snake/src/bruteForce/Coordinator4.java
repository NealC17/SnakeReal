package bruteForce;


import java.awt.LayoutManager;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JSlider;


public class Coordinator4{

	public static final int boardWidth =30*Snake4.width+30, boardHeight = 30*Snake4.width;
	static JFrame frame, frame2;
	static Snake4 s ;
	static boolean gameOn;
	private static int slep=1;

	static JSlider j;
	public static void draw(){


		frame2 = new JFrame("Set SLEEP");
		j = new JSlider();

		s = new Snake4();
		frame = new JFrame("game ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,boardWidth+23,boardHeight+27);

		j.setMinimum(1);
		frame2.setBounds(1000,0,boardWidth/2,boardHeight/2);

		frame.getContentPane().add(s);

		frame2.getContentPane().add(j);
		frame.setVisible(true);
		frame2.setVisible(true);


		gameOn = true;

	}

	public static void main(String[] args) throws InterruptedException {

		draw();
		int upperBound=5;

		for(int i = 0; true;i++){

			if(i!=0){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) { }


				s.reset();
				frame.repaint();
			}
			while(!s.isCollided()){
				s.update();
				frame.repaint();
				slep=(int)(j.getValue());
				Thread.sleep(slep);


			}

		}
	}
}



