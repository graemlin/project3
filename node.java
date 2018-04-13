
public class node {

	Integer[] value;
	
	node leftChild;
	node rightChild;
	
	int[] region;
	
	//associated structure
	
	public node(int[] value){
		this.value = new Integer[3];
		this.value[0] = new Integer(value[0]);
		this.value[1] = new Integer(value[1]);
		this.value[2] = new Integer(value[2]);
	}
	
	public void setLeftChild(node left){
		leftChild = left;
	}
	
	public void setRightChild(node right){
		rightChild = right;
	}
}
