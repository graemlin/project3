import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main {

	public static void main(String[] args){
		
		int numPoints; //number of points
		int numQueries; //number of queries
		
		Scanner s = new Scanner(System.in);
		
		numPoints = s.nextInt();
		numQueries = s.nextInt();
		
		ArrayList<node> points = new ArrayList<node>();
		ArrayList<query> queries = new ArrayList<query>();
		
		int[] coords = new int[3];
		for(int i = 0; i < numPoints; i++){
			points.add(new node(s.nextInt(),s.nextInt(), s.nextInt()));
		}
		
		coords = new int[6];
		for(int i = 0; i < numQueries; i++){
			coords[0] = s.nextInt();
			coords[1] = s.nextInt();
			coords[2] = s.nextInt();
			coords[3] = s.nextInt();
			coords[4] = s.nextInt();
			coords[5] = s.nextInt();
			queries.add(new query(coords));
		}
		
		ArrayList<node> pointsByX = new ArrayList<node>(points);
		ArrayList<node> pointsByY = new ArrayList<node>(points);
		ArrayList<node> pointsByZ = new ArrayList<node>(points);
		
		Collections.sort(pointsByX, new sortNodes('x'));
		Collections.sort(pointsByY, new sortNodes('y'));
		Collections.sort(pointsByZ, new sortNodes('z'));


		Integer[] region = {pointsByX.get(0).value[0], pointsByX.get(pointsByX.size() -1).value[0],
				pointsByY.get(0).value[1], pointsByY.get(pointsByY.size() -1).value[1]};

		node root = buildKDTree(pointsByX,pointsByY,pointsByZ,region,0);

		//test code
		System.out.println("points test");
		for(int i = 0; i < numPoints; i++){
			System.out.println(points.get(i));
		}
		
		System.out.println("query test");
		for(int i = 0; i < numQueries; i++){
			System.out.println(queries.get(i));
		}
		
		System.out.println("pointsByX test");
		for(int i = 0; i < numPoints; i++){
			System.out.println(pointsByX.get(i));
		}
		
		System.out.println("pointsByY test");
		for(int i = 0; i < numPoints; i++){
			System.out.println(pointsByY.get(i));
		}
		
		System.out.println("pointsByZ test");
		for(int i = 0; i < numPoints; i++){
			System.out.println(pointsByZ.get(i));
		}
	}

	private static node buildKDTree(ArrayList<node> sortedX, ArrayList<node> sortedY, ArrayList<node> sortedZ, Integer[] region, int depth) {

		if(depth%2 == 0) {
			if(sortedX.size() == 1) {
				node leaf = sortedX.get(0);
				leaf.rightChild = null;
				leaf.leftChild = null;

				//setting its associated structure to itself
				ArrayList<node> assoc = new ArrayList<>();
				assoc.add(leaf);
				leaf.assocZ = assoc;

				//setting its region to itself

//				leaf.setRegion(leaf.value);
				return leaf;
			}

			int median = (int)Math.ceil((double)sortedX.size()/2);

			//split on x with median on left
			ArrayList<node> leftX = new ArrayList<>(sortedX.subList(0, median));
			ArrayList<node> rightX = new ArrayList<>(sortedX.subList(median, sortedX.size()));
			node splitNode = leftX.get(leftX.size()-1);
			splitNode.setAssocZ(sortedZ);

			ArrayList<node> leftY = new ArrayList<>();
			ArrayList<node> rightY = new ArrayList<>();
			ArrayList<node> leftZ = new ArrayList<>();
			ArrayList<node> rightZ = new ArrayList<>();

			//select subset of nodes from sorted y list
			for (int i=0; i<sortedY.size(); i++) {
				if(sortedY.get(i).value[0] > splitNode.value[0]) {
					rightY.add(sortedY.get(i));
				} else {
					leftY.add(sortedY.get(i));
				}
			}


			//select subset of nodes from sorted z list
			for (int i=0; i<sortedZ.size(); i++) {
				if(sortedZ.get(i).value[0] > splitNode.value[0]) {
					rightZ.add(sortedZ.get(i));
				} else {
					leftZ.add(sortedZ.get(i));
				}
			}

			Integer[] leftRegion = {region[0], splitNode.value[0], region[2], region[3]};
			Integer[] rightRegion = {splitNode.value[0],region[1], region[2], region[3]};

			node vLeft = buildKDTree(leftX, leftY, leftZ, leftRegion,depth+1);
			node vRight = buildKDTree(rightX, rightY, rightZ, rightRegion, depth+1);

			vLeft.setRegion(leftRegion);
			vRight.setRegion(rightRegion);

			splitNode.leftChild = vLeft;
			splitNode.rightChild = vRight;

			return splitNode;

		} else {
			if(sortedY.size() == 1) {
				node leaf = sortedY.get(0);
				leaf.rightChild = null;
				leaf.leftChild = null;

				//setting its associated structure to itself
				ArrayList<node> assoc = new ArrayList<>();
				assoc.add(leaf);
				leaf.assocZ = assoc;

				//setting its region to itself
//				leaf.setRegion(leaf.value);
				return leaf;
			}

			int median = (int)Math.ceil((double)sortedY.size()/2);
			//split on y with median on bottom
			ArrayList<node> bottomY = new ArrayList<>(sortedY.subList(0, median));
			ArrayList<node> topY = new ArrayList<>(sortedY.subList(median, sortedY.size()));
			node splitNode = bottomY.get(bottomY.size()-1);
			splitNode.setAssocZ(sortedZ);

			ArrayList<node> bottomX = new ArrayList<>();
			ArrayList<node> topX = new ArrayList<>();
			ArrayList<node> bottomZ = new ArrayList<>();
			ArrayList<node> topZ = new ArrayList<>();

			//select subset of nodes from sorted x list
			for (int i=0; i<sortedX.size(); i++) {
				if(sortedX.get(i).value[1] > splitNode.value[1]) {
					topX.add(sortedX.get(i));
				} else {
					bottomX.add(sortedX.get(i));
				}
			}

			//select subset of nodes from sorted z list
			for (int i=0; i<sortedZ.size(); i++) {
				if(sortedZ.get(i).value[1] > splitNode.value[1]) {
					topZ.add(sortedZ.get(i));
				} else {
					bottomZ.add(sortedZ.get(i));
				}
			}

			//used to recursively set regions for proceeding nodes
			Integer[] bottomRegion = {region[0], region[1],region[2] , splitNode.value[1]};
			Integer[] topRegion = {region[0], region[1], splitNode.value[1], region[3]};

			node vLeft = buildKDTree(bottomX, bottomY, bottomZ, bottomRegion,depth+1);
			node vRight = buildKDTree(topX, topY, topZ, topRegion, depth+1);

			vLeft.setRegion(bottomRegion);
			vRight.setRegion(topRegion);

			splitNode.leftChild = vLeft;
			splitNode.rightChild = vRight;

			return splitNode;
		}

	}
}
