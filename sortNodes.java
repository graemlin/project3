import java.util.Comparator;


public class sortNodes implements Comparator<node>{
	
	private char compareBy = 'x';

	public sortNodes(){
		
	}
	
	public sortNodes(char compareByAxis){
		compareBy = compareByAxis;
	}
	
	public int compare(node foo, node bar){
		switch(compareBy){
		case 'x':
			return (int)(foo.value[0] - bar.value[0]);
		case 'y':
			return (int)(foo.value[1] - bar.value[1]);
		case 'z':
			return (int)(foo.value[2] - bar.value[2]);
		}
		return 0;
	}
}
