import java.util.ArrayList;


public class KDTree {

	public static ArrayList<node> searchKDTree(node root, query q){
		ArrayList<node> inQuery = new ArrayList<node>();
		if(root.isInQuery(q) && root.leftChild == null && root.rightChild == null){
			inQuery.add(root);
			return inQuery;
		}
		if(root.leftChild != null && root.leftChild.region[0] >= q.xMin && root.leftChild.region[1] <= q.xMax && 
				root.leftChild.region[2] >= q.yMin && root.leftChild.region[3] <= q.yMax){
			System.out.println("bSearch on : " + root.leftChild + " query: " + q);
			inQuery.addAll(q.binarySearchonZ(root.leftChild.assocZ));
		}
		else if(root.leftChild != null && (root.leftChild.region[1] >= q.xMin || 
				root.leftChild.region[0] <= q.xMax) && (root.leftChild.region[3] >= q.yMin || root.leftChild.region[2] <= q.yMax)){
			System.out.println("searchKDT on : " + root.leftChild + " query: " + q);
			inQuery.addAll(searchKDTree(root.leftChild, q));
		}
		if(root.rightChild != null && root.rightChild.region[0] >= q.xMin && root.rightChild.region[1] <= q.xMax && 
				root.rightChild.region[2] >= q.yMin && root.rightChild.region[3] <= q.yMax){
			System.out.println("bSearch on root RC: " + root.rightChild + " query: " + q);
			inQuery.addAll(q.binarySearchonZ(root.rightChild.assocZ));
		}
		else if(root.rightChild != null && (root.rightChild.region[1] >= q.xMin || 
				root.rightChild.region[0] <= q.xMax) && (root.rightChild.region[3] >= q.yMin || root.rightChild.region[2] <= q.yMax)){
			System.out.println("searchKDT on : " + root.rightChild + " query: " + q);
			inQuery.addAll(searchKDTree(root.rightChild, q));
		}
		return inQuery;
	}
	
	public static node buildKDTree(ArrayList<node> sortedX, ArrayList<node> sortedY, ArrayList<node> sortedZ, Integer[] region, int depth) {

		if(depth%2 == 0) {
			if(sortedX.size() == 1) {
				node leaf = new node(sortedX.get(0).value[0], sortedX.get(0).value[1],
						sortedX.get(0).value[2], sortedX.get(0).id);
				leaf.rightChild = null;
				leaf.leftChild = null;

				//setting its associated structure to itself
				ArrayList<node> assoc = new ArrayList<>();
				assoc.add(leaf);
				leaf.assocZ = assoc;

				//set region to itself
				Integer[] reg = {leaf.value[0], leaf.value[0], leaf.value[1], leaf.value[1]};
				leaf.setRegion(reg);
				return leaf;
			}

			int median = (int)Math.ceil((double)sortedX.size()/2);

			//split on x with median on left
			ArrayList<node> leftX = new ArrayList<>(sortedX.subList(0, median));
			ArrayList<node> rightX = new ArrayList<>(sortedX.subList(median, sortedX.size()));
			node medianNode = leftX.get(leftX.size()-1);

			node splitNode = new node(medianNode.value[0], medianNode.value[1], medianNode.value[2], medianNode.id);
			splitNode.setAssocZ(sortedZ);

			ArrayList<node> leftY = new ArrayList<>();
			ArrayList<node> rightY = new ArrayList<>();
			ArrayList<node> leftZ = new ArrayList<>();
			ArrayList<node> rightZ = new ArrayList<>();

			//select subset of nodes from sorted y list
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

			node vLeft = buildKDTree(leftX, leftY, leftZ, leftRegion,depth+1);
			node vRight = buildKDTree(rightX, rightY, rightZ, rightRegion, depth+1);

			splitNode.leftChild = vLeft;
			splitNode.rightChild = vRight;

			return splitNode;

		} else {
			if(sortedY.size() == 1) {
				node leaf = new node(sortedY.get(0).value[0], sortedY.get(0).value[1],
						sortedY.get(0).value[2], sortedY.get(0).id);
				leaf.rightChild = null;
				leaf.leftChild = null;

				//setting its associated structure to itself
				ArrayList<node> assoc = new ArrayList<>();
				assoc.add(leaf);
				leaf.assocZ = assoc;

				//setting its region to itself
				Integer[] reg = {leaf.value[0], leaf.value[0], leaf.value[1], leaf.value[1]};
				leaf.setRegion(reg);
				return leaf;
			}

			int median = (int)Math.ceil((double)sortedY.size()/2);
			//split on y with median on bottom
			ArrayList<node> bottomY = new ArrayList<>(sortedY.subList(0, median));
			ArrayList<node> topY = new ArrayList<>(sortedY.subList(median, sortedY.size()));
			node medianNode = bottomY.get(bottomY.size()-1);
			node splitNode = new node(medianNode.value[0], medianNode.value[1], medianNode.value[2], medianNode.id);
			splitNode.setAssocZ(sortedZ);

			ArrayList<node> bottomX = new ArrayList<>();
			ArrayList<node> topX = new ArrayList<>();
			ArrayList<node> bottomZ = new ArrayList<>();
			ArrayList<node> topZ = new ArrayList<>();

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
			node vLeft = buildKDTree(bottomX, bottomY, bottomZ, bottomRegion,depth+1);
			node vRight = buildKDTree(topX, topY, topZ, topRegion, depth+1);


			splitNode.leftChild = vLeft;
			splitNode.rightChild = vRight;

			return splitNode;
		}

	}

}
