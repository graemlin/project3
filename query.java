import java.util.ArrayList;


public class query {

	int xMin;
	int xMax;
	int yMin;
	int yMax;
	int zMin;
	int zMax;
	
	/**
	 * Creates a new query object
	 * @param coords xMin, xMax, yMin, yMax, zMin, zMax
	 */
	public query(int[] coords){
		xMin = coords[0];
		xMax = coords[1];
		yMin = coords[2];
		yMax = coords[3];
		zMin = coords[4];
		zMax = coords[5];
	}
		
	public ArrayList<node> checkNodesByZ(ArrayList<node> foo){
		ArrayList<node> inQuery = new ArrayList<node>();
		for(node bar : foo){
			if(bar.isInQuery(this)) inQuery.add(bar);
		}
		return inQuery;
	}
	
	public String toString(){
		return "xMin: " + xMin + "  xMax: " + xMax + "  yMin: " + yMax
				+ "  yMax: " + yMax + "  zMin: " + zMin + "  zMax: " + zMax;
	}
}
