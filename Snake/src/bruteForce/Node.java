package bruteForce;

public class Node {

	
	// h is heuristic, g is movement cost, f is h+g
	private int h,g,f;
	
	//parent node is a node that leads to this node
	private Node parent;
	
	//visual position
	private int x,y;
	
	//target which it shall go to
	private int targetX, targetY;
	
	//tell which node is where
	private int nodeID;
	
	public Node(int x, int y, int nodeID){
		this.x=x;
		this.y=y;
		
		this.nodeID = nodeID;

		
	}
	
	
	public int getNodeID() {
		return nodeID;
	}




	public int getY() {
		return y;
	}


	public int getX() {
		return x;
	}


	//target is food
	public void setTarget(Food f){
		targetX=f.getX();
		targetY=f.getY();
	}
	
	public void calcH(){
		int yDist = Math.abs(targetY-getY());
		int xDist = Math.abs(targetX-x);
		h=xDist+yDist;
	}
	
	public Node find(int x, int y){
		if(this.x==x&&this.getY()==y)return this;
		else return null;
	}
	
	public String toString(){
		return String.valueOf(nodeID);
	}
	
}
