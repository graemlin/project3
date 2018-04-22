import java.util.ArrayList;


public class KDTree {

	/**
	 *
	 * @param root the root of a 2D kd-tree
	 * @param q the search Query
	 * @return a list of nodes within the Query region
	 */
	public static ArrayList<Node> searchKDTree(Node root, Query q){
		ArrayList<Node> inQuery = new ArrayList<Node>(); //ArrayList of nodes in the Query range
		//if the root is in the Query and has no children, add the root to the ArrayList and return it
		if(root.isInQuery(q) && root.leftChild == null && root.rightChild == null){
			inQuery.add(root);
			return inQuery;
		}
		//if the root has a left child and the all of the child's x and y values are in the Query range, add all the
		//associated z values in the range to the ArrayList
		if(root.leftChild != null && root.leftChild.region[0] >= q.xMin && root.leftChild.region[1] <= q.xMax && 
				root.leftChild.region[2] >= q.yMin && root.leftChild.region[3] <= q.yMax){
			inQuery.addAll(q.binarySearchonZ(root.leftChild.assocZ));
		}
		//if the root has a left child and some of the child's x and y values are in the Query range, recursively search the child
		//to find the x and y values in range
		else if(root.leftChild != null && (root.leftChild.region[1] >= q.xMin || 
				root.leftChild.region[0] <= q.xMax) && (root.leftChild.region[3] >= q.yMin || root.leftChild.region[2] <= q.yMax)){
			inQuery.addAll(searchKDTree(root.leftChild, q));
		}
		//if the root has a right child and the all of the child's x and y values are in the Query range, add all the
		//associated z values in the range to the ArrayList
		if(root.rightChild != null && root.rightChild.region[0] >= q.xMin && root.rightChild.region[1] <= q.xMax && 
				root.rightChild.region[2] >= q.yMin && root.rightChild.region[3] <= q.yMax){
			inQuery.addAll(q.binarySearchonZ(root.rightChild.assocZ));
		}
		//if the root has a right child and some of the child's x and y values are in the Query range, recursively search the child
		//to find the x and y values in range
		else if(root.rightChild != null && (root.rightChild.region[1] >= q.xMin || 
				root.rightChild.region[0] <= q.xMax) && (root.rightChild.region[3] >= q.yMin || root.rightChild.region[2] <= q.yMax)){
			inQuery.addAll(searchKDTree(root.rightChild, q));
		}
		//return the ArrayList of nodes in the Query range
		return inQuery;
	}

	/**
	 * Builds a 2D kd-tree alternatively splitting between x and y axes.
	 * Each internal Node references an associated array sorted on z-coordinates
	 * @param sortedX nodes sorted by x-coordinate. Used to split nodes at even depth
	 * @param sortedY nodes sorted by y-coordinate. Used to split nodes at odd depth
	 * @param sortedZ nodes sorted by z-coordinate. Used to build associated structures
	 * @param region bounds a Node to an xMin, xMax, yMin, yMax rectangular region
	 * @param depth counter to determine when to split horizontally/vertically
	 * @return the root of the kd-tree
	 */
	public static Node buildKDTree(ArrayList<Node> sortedX, ArrayList<Node> sortedY, ArrayList<Node> sortedZ, Integer[] region, int depth) {

		if(depth%2 == 0) {
			if(sortedX.size() == 1) {
				Node leaf = new Node(sortedX.get(0).value[0], sortedX.get(0).value[1],
						sortedX.get(0).value[2], sortedX.get(0).id);
				leaf.rightChild = null;
				leaf.leftChild = null;

				//setting its associated structure to itself
				ArrayList<Node> assoc = new ArrayList<>();
				assoc.add(leaf);
				leaf.assocZ = assoc;

				//set region to itself
				Integer[] reg = {leaf.value[0], leaf.value[0], leaf.value[1], leaf.value[1]};
				leaf.setRegion(reg);
				return leaf;
			}

			int median = (int)Math.ceil((double)sortedX.size()/2);

			//split on x with median on left
			ArrayList<Node> leftX = new ArrayList<>(sortedX.subList(0, median));
			ArrayList<Node> rightX = new ArrayList<>(sortedX.subList(median, sortedX.size()));
			Node medianNode = leftX.get(leftX.size()-1);

			Node splitNode = new Node(medianNode.value[0], medianNode.value[1], medianNode.value[2], medianNode.id);
			splitNode.setAssocZ(sortedZ);

			ArrayList<Node> leftY = new ArrayList<>();
			ArrayList<Node> rightY = new ArrayList<>();
			ArrayList<Node> leftZ = new ArrayList<>();
			ArrayList<Node> rightZ = new ArrayList<>();

			//select subset of nodes from sorted y list
			//resort to id to break ties
			for (int i=0; i<sortedY.size(); i++) {
				if(sortedY.get(i).value[0] > splitNode.value[0]) {
					rightY.add(sortedY.get(i));
				}
				else if(sortedY.get(i).value[0] < splitNode.value[0])  {
					leftY.add(sortedY.get(i));
				}
				else {
					if(sortedY.get(i).id > splitNode.id) {
						rightY.add(sortedY.get(i));
					} else {
						leftY.add(sortedY.get(i));
					}
				}
			}


			//select subset of nodes from sorted z list
			for (int i=0; i<sortedZ.size(); i++) {
				if(sortedZ.get(i).value[0] > splitNode.value[0]) {
					rightZ.add(sortedZ.get(i));
				}
				else if (sortedZ.get(i).value[0] < splitNode.value[0]){
					leftZ.add(sortedZ.get(i));
				} else {
					if (sortedZ.get(i).id > splitNode.id){
						rightZ.add(sortedZ.get(i));
				} else {
						leftZ.add(sortedZ.get(i));
					}
			} }

			splitNode.setRegion(region);
			Integer[] leftRegion = {region[0], splitNode.value[0], region[2], region[3]};
			Integer[] rightRegion = {splitNode.value[0],region[1], region[2], region[3]};

			Node vLeft = buildKDTree(leftX, leftY, leftZ, leftRegion,depth+1);
			Node vRight = buildKDTree(rightX, rightY, rightZ, rightRegion, depth+1);

			splitNode.leftChild = vLeft;
			splitNode.rightChild = vRight;

			return splitNode;

		} else {
			if(sortedY.size() == 1) {
				Node leaf = new Node(sortedY.get(0).value[0], sortedY.get(0).value[1],
						sortedY.get(0).value[2], sortedY.get(0).id);
				leaf.rightChild = null;
				leaf.leftChild = null;

				//setting its associated structure to itself
				ArrayList<Node> assoc = new ArrayList<>();
				assoc.add(leaf);
				leaf.assocZ = assoc;

				//setting its region to itself
				Integer[] reg = {leaf.value[0], leaf.value[0], leaf.value[1], leaf.value[1]};
				leaf.setRegion(reg);
				return leaf;
			}

			int median = (int)Math.ceil((double)sortedY.size()/2);
			//split on y with median on bottom
			ArrayList<Node> bottomY = new ArrayList<>(sortedY.subList(0, median));
			ArrayList<Node> topY = new ArrayList<>(sortedY.subList(median, sortedY.size()));
			Node medianNode = bottomY.get(bottomY.size()-1);
			Node splitNode = new Node(medianNode.value[0], medianNode.value[1], medianNode.value[2], medianNode.id);
			splitNode.setAssocZ(sortedZ);

			ArrayList<Node> bottomX = new ArrayList<>();
			ArrayList<Node> topX = new ArrayList<>();
			ArrayList<Node> bottomZ = new ArrayList<>();
			ArrayList<Node> topZ = new ArrayList<>();

			//select subset of nodes from sorted x list
			for (int i=0; i<sortedX.size(); i++) {
				if(sortedX.get(i).value[1] > splitNode.value[1]) {
					topX.add(sortedX.get(i));
				}
				else if(sortedX.get(i).value[1] < splitNode.value[1]) {
					bottomX.add(sortedX.get(i));
				}
				else {
					if (sortedX.get(i).id > splitNode.id) {
						topX.add(sortedX.get(i));
					} else {
						bottomX.add(sortedX.get(i));
					}
				}
			}

			//select subset of nodes from sorted z list
			for (int i=0; i<sortedZ.size(); i++) {
				if(sortedZ.get(i).value[1] > splitNode.value[1]) {
					topZ.add(sortedZ.get(i));
				}
				else if(sortedZ.get(i).value[1] < splitNode.value[1]){
					bottomZ.add(sortedZ.get(i));
				} else {
					if (sortedZ.get(i).id > splitNode.id) {
						topZ.add(sortedZ.get(i));
					} else {
						bottomZ.add(sortedZ.get(i));
					}
				}
			}

			//used to recursively set regions for proceeding nodes
			Integer[] bottomRegion = {region[0], region[1],region[2] , splitNode.value[1]};
			Integer[] topRegion = {region[0], region[1], splitNode.value[1], region[3]};

			splitNode.setRegion(region);
			Node vLeft = buildKDTree(bottomX, bottomY, bottomZ, bottomRegion,depth+1);
			Node vRight = buildKDTree(topX, topY, topZ, topRegion, depth+1);


			splitNode.leftChild = vLeft;
			splitNode.rightChild = vRight;

			return splitNode;
		}

	}

}
