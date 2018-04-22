/**

 Group Member 1:				Milan Bhatia		19228
 Group Member 2:				Alexander Mundy		19228
 Group Member 3:				Aliyah White		12721


 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Uses System.in to parse input, then calls buildKDTree and SearchKDTree
 */
public class Main {

	public static void main(String[] args){
		
		int numPoints; //number of points
		int numQueries; //number of queries
		
		Scanner s = new Scanner(System.in);
		
		numPoints = s.nextInt();
		numQueries = s.nextInt();
		
		//create ArrayLists of nodes and queries
		ArrayList<Node> points = new ArrayList<Node>();
		ArrayList<Query> queries = new ArrayList<Query>();
		
		//add the x, y, and z values read in, and id value from the for loop, as a Node to the Node array
		int[] coords = new int[3];
		for(int i = 0; i < numPoints; i++){
			points.add(new Node(s.nextInt(),s.nextInt(), s.nextInt(), i));
		}
		
		//add the queries read in to the Query array
		coords = new int[6];
		for(int i = 0; i < numQueries; i++){
			coords[0] = s.nextInt();
			coords[1] = s.nextInt();
			coords[2] = s.nextInt();
			coords[3] = s.nextInt();
			coords[4] = s.nextInt();
			coords[5] = s.nextInt();
			queries.add(new Query(coords));
		}
		
		//create an ArrayList of nodes that will then be sorted by their x, y, or z values
		ArrayList<Node> pointsByX = new ArrayList<Node>(points);
		ArrayList<Node> pointsByY = new ArrayList<Node>(points);
		ArrayList<Node> pointsByZ = new ArrayList<Node>(points);
		
		Collections.sort(pointsByX, new SortNodes('x'));
		Collections.sort(pointsByY, new SortNodes('y'));
		Collections.sort(pointsByZ, new SortNodes('z'));

		//set the region of x and y by using the sorted nodes
		Integer[] region = {pointsByX.get(0).value[0], pointsByX.get(pointsByX.size() -1).value[0],
				pointsByY.get(0).value[1], pointsByY.get(pointsByY.size() -1).value[1]};

		//define the root Node
		Node root = KDTree.buildKDTree(pointsByX,pointsByY,pointsByZ,region,0);
		
		//for each of the queries, seach the KDTree to get the number of points within that range and print it out
		for(int i = 0; i < numQueries; i++){
			int result = KDTree.searchKDTree(root, queries.get(i)).size();
			System.out.println(result);
		}
	}
}
