import java.util.ArrayList;

public class node {

	Integer[] value;
	int id;
	ArrayList<node> assocZ;
	node leftChild;
	node rightChild;
	
	Integer[] region;
	
	//associated structure
	
	public node(int x, int y, int z, int i){
		value = new Integer[3];
		value[0] = new Integer(x);
		value[1] = new Integer(y);
		value[2] = new Integer(z);
		id = i;
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

		
	public boolean isInQuery(query foo){
		return value[0] <= foo.xMax && value[0] >= foo.xMin && 
				value[1] <= foo.yMax && value[1] >= foo.yMin && 
				value[2] <= foo.zMax && value[2] >= foo.zMin;
	}

	public String toString(){
		return "X: " + value[0] + "  Y: " + value[1] + "  Z: " + value[2];
	}
}
