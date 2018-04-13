
public class node {

	Integer[] value;
	
	node leftChild;
	node rightChild;
	
	int[] region;
	
	//associated structure
	
	public node(int x, int y, int z){
		value = new Integer[3];
		value[0] = new Integer(x);
		value[1] = new Integer(y);
		value[2] = new Integer(z);
	}
	
	public void setLeftChild(node left){
		leftChild = left;
	}
	
	public void setRightChild(node right){
		rightChild = right;
	}
	
	public void setRegion(int xMin, int xMax, int yMin, int yMax){
		region = new int[4];
		region[0] = xMin;
		region[1] = xMax;
		region[2] = yMin;
		region[3] = yMax;
	}
	
	public String toString(){
		return "X: " + value[0] + "  Y: " + value[1] + "  Z: " + value[2];
	}
}
