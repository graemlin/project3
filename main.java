import java.util.Scanner;


public class main {

	public static void main(String[] args){
		
		int numPoints; //number of points
		int numQueries; //number of queries
		
		Scanner s = new Scanner(System.in);
		
		numPoints = s.nextInt();
		numQueries = s.nextInt();
		
		node[] points = new node[numPoints];
		query[] queries = new query[numQueries];
		
		int[] coords = new int[3];
		for(int i = 0; i < numPoints; i++){
			points[i] = new node(s.nextInt(),s.nextInt(), s.nextInt());
		}
		
		coords = new int[6];
		for(int i = 0; i < numQueries; i++){
			coords[0] = s.nextInt();
			coords[1] = s.nextInt();
			coords[2] = s.nextInt();
			coords[3] = s.nextInt();
			coords[4] = s.nextInt();
			coords[5] = s.nextInt();
			queries[i] = new query(coords);
		}
		
		
	}
}
