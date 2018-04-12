
public class node {

	int[] value;
	
	node leftChild;
	node rightChild;
	
	int[] region;
	
	//associated structure
	
	public node(int[] value){
		this.value = value;
	}
	
	public void setLeftChild(node left){
		leftChild = left;
	}
	
	public void setRightChild(node right){
		rightChild = right;
	}
}
