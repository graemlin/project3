import java.util.ArrayList;

/**
 * Encapsulates a point
 */
public class Node {

	Integer[] value;
	int id;
	ArrayList<Node> assocZ;
	Node leftChild;
	Node rightChild;
	
	Integer[] region;
	
	//associated structure
	
	//defines the x, y, z, and id of the Node
	public Node(int x, int y, int z, int i){
		value = new Integer[3];
		value[0] = new Integer(x);
		value[1] = new Integer(y);
		value[2] = new Integer(z);
		id = i;
	}
	
	//sets the left child Node
	public void setLeftChild(Node left){
		leftChild = left;
	}
	
	//sets the right child Node
	public void setRightChild(Node right){
		rightChild = right;
	}
	
	//sets the region of x and y values
	public void setRegion(Integer[] r){
		region = new Integer[4];
		region[0] = r[0]; //xMin;
		region[1] = r[1]; //xMax;
		region[2] = r[2]; //yMin;
		region[3] = r[3]; //yMax;
	}

	//returns the associated Z structure
	public ArrayList<Node> getAssocZ() {
		return assocZ;
	}

	//sets the associated Z structure
	public void setAssocZ(ArrayList<Node> assocZ) {
		this.assocZ = assocZ;
	}

	//determines if a Node's x, y, and z values are within a Query range
	public boolean isInQuery(Query foo){
		return value[0] <= foo.xMax && value[0] >= foo.xMin && 
				value[1] <= foo.yMax && value[1] >= foo.yMin && 
				value[2] <= foo.zMax && value[2] >= foo.zMin;
	}

	//prints string of x, y, and z values
	public String toString(){
		return "X: " + value[0] + "  Y: " + value[1] + "  Z: " + value[2];
	}
}
