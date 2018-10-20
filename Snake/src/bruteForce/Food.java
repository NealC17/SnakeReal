package bruteForce;

import java.awt.Color;
import java.awt.Graphics;




public class Food{
	private int x,y;
	public static final int width=Snake4.width;
	
	
	public Food(){
		x=((int)(Math.random()*30.0))*30;
		y=((int)(Math.random()*30.0))*30;
		
	}
	
	
	
	public void drawFood(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(x, y, width, width);
	}	
	
	public int getX(){return x;}
	public int getY(){return y;}
	
	public void setNewPos(int[] xx, int[] yy, boolean[] shown){
		x=((int)(Math.random()*30.0))*30;
		y=((int)(Math.random()*30.0))*30;
		
		
		
	}
}
