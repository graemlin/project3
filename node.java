import java.util.ArrayList;

/**
 * Encapsulates a point
 */
public class node {

	Integer[] value;
	int id;
	ArrayList<node> assocZ;
	node leftChild;
	node rightChild;
	
	Integer[] region;
	
	//associated structure
	
	//defines the x, y, z, and id of the node
	public node(int x, int y, int z, int i){
		value = new Integer[3];
		value[0] = new Integer(x);
		value[1] = new Integer(y);
		value[2] = new Integer(z);
		id = i;
	}
	
	//sets the left child node
	public void setLeftChild(node left){
		leftChild = left;
	}
	
	//sets the right child node
	public void setRightChild(node right){
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
	public ArrayList<node> getAssocZ() {
		return assocZ;
	}

	//sets the associated Z structure
	public void setAssocZ(ArrayList<node> assocZ) {
		this.assocZ = assocZ;
	}

	//determines if a node's x, y, and z values are within a query range	
	public boolean isInQuery(query foo){
		return value[0] <= foo.xMax && value[0] >= foo.xMin && 
				value[1] <= foo.yMax && value[1] >= foo.yMin && 
				value[2] <= foo.zMax && value[2] >= foo.zMin;
	}

	//prints string of x, y, and z values
	public String toString(){
		return "X: " + value[0] + "  Y: " + value[1] + "  Z: " + value[2];
	}
}
