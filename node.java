
public class node {

	int value;
	
	node leftChild;
	node rightChild;
	
	int[] region;
	
	//associated structure
	
	public node(int value, int[] region){
		this.value = value;
		this.region = region;
	}
	
	public void setLeftChild(node left){
		leftChild = left;
	}
	
	public void setRightChild(node right){
		rightChild = right;
	}
}
