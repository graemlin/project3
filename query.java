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
		
    public ArrayList<node> binarySearchonZ(ArrayList<node> sortedZ)
    {
	int top = sortedZ.size() - 1;
	int bottom = 0;
	int mid = 0;

	while(top > bottom)
	{
	    mid = (top + bottom)/2;
	    if(sortedZ.get(mid).value[2].intValue() < zMin)
	    {
		bottom = mid+1;
	    }
	    else if(sortedZ.get(mid).value[2].intValue() > zMin)
	    {
		top = mid-1;
	    }
	}
	int minI = mid;

	top = sortedZ.size() - 1;
	bottom = 0;
	mid = 0;

	while(top > bottom)
	{
	    mid = (top + bottom)/2;
	    if(sortedZ.get(mid).value[2].intValue() < zMin)
	    {
		bottom = mid+1;
	    }
	    else if(sortedZ.get(mid).value[2].intValue() > zMin)
	    {
		top = mid-1;
	    }
	}
	int maxI = mid;

	ArrayList<node> range = (ArrayList<node>) sortedZ.subList(minI, maxI);

	return range;

    }
	
	public String toString(){
		return "xMin: " + xMin + "  xMax: " + xMax + "  yMin: " + yMax
				+ "  yMax: " + yMax + "  zMin: " + zMin + "  zMax: " + zMax;
	}
}
