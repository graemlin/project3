import java.util.ArrayList;

public class node {

	Integer[] value;
	ArrayList<node> assocZ;
	node leftChild;
	node rightChild;
	
	Integer[] region;
	
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
	
	public void setRegion(Integer[] r){
		region = new Integer[4];
		region[0] = r[0]; //xMin;
		region[1] = r[1]; //xMax;
		region[2] = r[2]; //yMin;
		region[3] = r[3]; //yMax;
	}

	public ArrayList<node> getAssocZ() {
		return assocZ;
	}

	public void setAssocZ(ArrayList<node> assocZ) {
		this.assocZ = assocZ;
	}

	public String toString(){
		return "X: " + value[0] + "  Y: " + value[1] + "  Z: " + value[2];
	}
}
