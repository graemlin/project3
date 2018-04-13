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
}
