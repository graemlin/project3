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
		
		//create ArrayLists of nodes and queries
		ArrayList<node> points = new ArrayList<node>();
		ArrayList<query> queries = new ArrayList<query>();
		
		//add the x, y, and z values read in, and id value from the for loop, as a node to the node array
		int[] coords = new int[3];
		for(int i = 0; i < numPoints; i++){
			points.add(new node(s.nextInt(),s.nextInt(), s.nextInt(), i));
		}
		
		//add the queries read in to the query array
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
		
		//create an ArrayList of nodes that will then be sorted by their x, y, or z values
		ArrayList<node> pointsByX = new ArrayList<node>(points);
		ArrayList<node> pointsByY = new ArrayList<node>(points);
		ArrayList<node> pointsByZ = new ArrayList<node>(points);
		
		Collections.sort(pointsByX, new sortNodes('x'));
		Collections.sort(pointsByY, new sortNodes('y'));
		Collections.sort(pointsByZ, new sortNodes('z'));

		//set the region of x and y by using the sorted nodes
		Integer[] region = {pointsByX.get(0).value[0], pointsByX.get(pointsByX.size() -1).value[0],
				pointsByY.get(0).value[1], pointsByY.get(pointsByY.size() -1).value[1]};

		//define the root node
		node root = KDTree.buildKDTree(pointsByX,pointsByY,pointsByZ,region,0);
		
		//for each of the queries, seach the KDTree to get the number of points within that range and print it out
		for(int i = 0; i < numQueries; i++){
			int result = KDTree.searchKDTree(root, queries.get(i)).size();
			System.out.println(result);
		}
	}
}
