
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
	
	public String toString(){
		return "xMin: " + xMin + "  xMax: " + xMax + "  yMin: " + yMax
				+ "  yMax: " + yMax + "  zMin: " + zMin + "  zMax: " + zMax;
	}
}
