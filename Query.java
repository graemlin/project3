/**

 Group Member 1:				Milan Bhatia		19228
 Group Member 2:				Alexander Mundy		19228
 Group Member 3:				Aliyah White		12721


 */

import java.util.ArrayList;

/**
 * encapsulates a Query region. Also contains logic
 * to search associated arrays sorted on z-coordinates
 * (called in searchKDTree())
 */
public class Query {

	int xMin;
	int xMax;
	int yMin;
	int yMax;
	int zMin;
	int zMax;
	
	/**
	 * Creates a new Query object
	 * @param coords xMin, xMax, yMin, yMax, zMin, zMax
	 */
	public Query(int[] coords){
		xMin = coords[0];
		xMax = coords[1];
		yMin = coords[2];
		yMax = coords[3];
		zMin = coords[4];
		zMax = coords[5];
	}
		
    //binary search function for Z that takes in an ArrayList of sorted nodes by Z
    public ArrayList<Node> binarySearchonZ(ArrayList<Node> sortedZ)
    {
	//if the sortedZ array is size 1
    	if(sortedZ.size() == 1){
		//if the Z value is greater than or equal to the min and les than or equal to the max, return the Z array, else return an empty array
    		if(sortedZ.get(0).value[2] >= zMin && sortedZ.get(0).value[2] <= zMax) return sortedZ;
    		else return new ArrayList<Node>();
    	}
    
	//if the greatest value Z in the array is less than the min, return an empty array
    	if(sortedZ.get(sortedZ.size()-1).value[2] < zMin) return new ArrayList<Node>();
	
	//set the top to the size of the Z array minus 1, the bottom to 0, and the mid to 0
	int top = sortedZ.size() - 1;
	int bottom = 0;
        int mid = 0;	
	
	    	//while the top is greater than the bottom
		while(top > bottom)
		{
		    //set the mid to the top plus the bottom divided by 2
		    mid = (top + bottom)/2;
			
		    //if the Z value at the mid is less than the zMin, set the bottom to mid+1, else set the top to mid-1
		    if(sortedZ.get(mid).value[2].intValue() < zMin)
		    {
			bottom = mid+1;
		    }
		    else 
		    {
			top = mid-1;
		    }
		}
	    
	        //set the minI to the mid
		int minI = mid;
	
	        //set the top to the size of the Z array minus 1, the bottom to 0, and the mid to 0
		top = sortedZ.size() - 1;
		bottom = 0;
		mid = 0;
		
	    	//set the mid to the top plus bottom divided by 2
	        mid = (top + bottom)/2;
	
	        //while top is greater than the bottom
		while(top > bottom)
		{
	            //if the Z value at the mid is less than the zMax, set the bottom to mid+1, else set the top to mid-1
		    if(sortedZ.get(mid).value[2].intValue() < zMax)
		    {
			bottom = mid+1;
		    }
		    else
		    {
			top = mid-1;
		    }
		    //set the mid to the top plus bottom divided by 2
		    mid = (top + bottom)/2;
		}
	        //set the maxI to the mid
		int maxI = mid;
	    
	        //while the minI is greater than 0 and the Z value at the index below minI is greater or equal to the min, decrease minI
		while(minI > 0 && sortedZ.get(minI-1).value[2] >= zMin){
			minI--;
		}
	       //while the maxI is greater than 0 and the Z value at the index below maxI is greater than the max, decrease maxI
		while(maxI > 0 && sortedZ.get(maxI-1).value[2] > zMax){
			maxI--;
		}
	        //while the maxI is less than the size of the sortedZ array and the Z value at the maxI index is less than or equal to the max, 
	        //increase maxI
		while(maxI < sortedZ.size() && sortedZ.get(maxI).value[2] <= zMax){
			maxI++;
		}
	        //while the minI is less than the size of the sortedZ array and the Z value at the minI index is less than the min, 
	        //increase minI
		while(minI < sortedZ.size() && sortedZ.get(minI).value[2] < zMin){
			minI++;
		}
		
	        //if the maxI is less than the minI or the minI is equal to the size of the sortedZ array or maxI is equal to 0,
	        //return an empty array
		if(maxI < minI || minI == sortedZ.size() || maxI == 0) {
			return new ArrayList<Node>();
		}
		
	        //create an ArrayList of nodes called range which is a sublist of sortedZ which contains all of the points within the minI and maxI
		ArrayList<Node> range = new ArrayList<Node>(sortedZ.subList(minI, maxI));
	
	        //return the ArrayList range
		return range;

    }
	//print string of x, y, and z mins and max
	public String toString(){
		return "xMin: " + xMin + "  xMax: " + xMax + "  yMin: " + yMax
				+ "  yMax: " + yMax + "  zMin: " + zMin + "  zMax: " + zMax;
	}
}
