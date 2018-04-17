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
    	if(sortedZ.size() == 1){
    		if(sortedZ.get(0).value[2] >= zMin && sortedZ.get(0).value[2] <= zMax) return sortedZ;
    		else return new ArrayList<node>();
    	}
    
    	if(sortedZ.get(sortedZ.size()-1).value[2] < zMin) return new ArrayList<node>();
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
		    else //if(sortedZ.get(mid).value[2].intValue() > zMin)
		    {
			top = mid-1;
		    }
		}
		int minI = mid;
	
		top = sortedZ.size() - 1;
		bottom = 0;
		mid = 0;
		mid = (top + bottom)/2;
	
		while(top > bottom)
		{
		    if(sortedZ.get(mid).value[2].intValue() < zMax)
		    {
			bottom = mid+1;
		    }
		    else //if(sortedZ.get(mid).value[2].intValue() > zMin)
		    {
			top = mid-1;
		    }
		    mid = (top + bottom)/2;
		}
		int maxI = mid;
		int comps = 0;
		while(minI > 0 && sortedZ.get(minI-1).value[2] >= zMin){
			minI--;
			comps++;
		}
		while(maxI > 0 && sortedZ.get(maxI-1).value[2] > zMax){
			maxI--;
			//comps++;
		}
		while(maxI < sortedZ.size() && sortedZ.get(maxI).value[2] <= zMax){
			maxI++;
			//comps++;
		}
		while(minI < sortedZ.size() && sortedZ.get(minI).value[2] < zMin){
			minI++;
			//comps++;
			//System.out.println("MinComp");
		}
		//if(maxI < sortedZ.size() && sortedZ.get(maxI).value[2] <= zMax) maxI++;*/
		int index = 0;
		//System.out.println("Comps: " + comps);
		
		/*while(index <= sortedZ.size() && sortedZ.get(index).value[2] < zMin){
			index++;
		}
		int oldminI = minI;
		minI = index;
		index = sortedZ.size()-1;
		while(index >= 0 && sortedZ.get(index).value[2] > zMax){
			index--;
		}
		int oldMaxI = maxI;
		maxI = index+1;
		*/

		
		if(maxI < minI || minI == sortedZ.size() || maxI == 0) {
			return new ArrayList<node>();
		}
		
		/*if(oldminI != minI || oldMaxI != maxI){
			System.out.println("DISPARITY " + this);
		}*/
		
		ArrayList<node> range = new ArrayList<node>(sortedZ.subList(minI, maxI));
	
		return range;

    }
    
    /*public ArrayList<node> superSearch(ArrayList<node> sortedZ){
    	ArrayList<node> results;
    	
    	int minI = -1;
    	int maxI = -1;
    	int top = sortedZ.size() - 1;
    	int bottom = 0;
    	int mid = 0;
    	
    	while(bottom < top){
    		mid = (top + bottom) / 2;
    		if(sortedZ.get(mid).value[2] < zMin){
    			bottom = mid + 1;
    		}
    		else{
    			top = mid;
    		}
    	}
    	minI = bottom;
    }*/
	
	public String toString(){
		return "xMin: " + xMin + "  xMax: " + xMax + "  yMin: " + yMax
				+ "  yMax: " + yMax + "  zMin: " + zMin + "  zMax: " + zMax;
	}
}
