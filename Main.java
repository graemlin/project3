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

	private static node buildKDTree(ArrayList<node> sortedX, ArrayList<node> sortedY, int depth) {

		if(depth%2 == 0) {
			if(sortedX.size() == 1) {
				node leaf = sortedX.get(0);
				leaf.rightChild = null;
				leaf.leftChild = null;
				return leaf;
			}

			int median = (int)Math.ceil(sortedX.size()/2);

			//split on x with median on left
			ArrayList<node> leftX = new ArrayList<>(sortedX.subList(0, median));
			ArrayList<node> rightX = new ArrayList<>(sortedX.subList(median, sortedX.size()));
			node splitNode = leftX.get(leftX.size()-1);

			ArrayList<node> leftY = new ArrayList<>();
			ArrayList<node> rightY = new ArrayList<>();

			//select subset of nodes from sorted y list
			for (int i=0; i<sortedY.size(); i++) {
				if(sortedY.get(i).value[0] > splitNode.value[0]) {
					rightY.add(sortedY.get(i));
				} else {
					leftY.add(sortedY.get(i));
				}
			}
			node vLeft = buildKDTree(leftX, leftY, depth+1);
			node vRight = buildKDTree(rightX, rightY, depth+1);

			splitNode.leftChild = vLeft;
			splitNode.rightChild = vRight;

			return splitNode;

		} else {
			if(sortedY.size() == 1) {
				node leaf = sortedY.get(0);
				leaf.rightChild = null;
				leaf.leftChild = null;
				return leaf;
			}

			int median = (int)Math.ceil(sortedY.size()/2);
			//split on y with median on bottom
			ArrayList<node> bottomY = new ArrayList<>(sortedY.subList(0, median));
			ArrayList<node> topY = new ArrayList<>(sortedY.subList(median, sortedY.size()));
			node splitNode = bottomY.get(bottomY.size()-1);

			ArrayList<node> bottomX = new ArrayList<>();
			ArrayList<node> topX = new ArrayList<>();

			//select subset of nodes from sorted x list
			for (int i=0; i<sortedX.size(); i++) {
				if(sortedX.get(i).value[1] > splitNode.value[1]) {
					topX.add(sortedX.get(i));
				} else {
					bottomX.add(sortedX.get(i));
				}
			}

			node vLeft = buildKDTree(bottomX, bottomY, depth+1);
			node vRight = buildKDTree(topX, topY, depth+1);

			splitNode.leftChild = vLeft;
			splitNode.rightChild = vRight;

			return splitNode; 
		}

	}
}
