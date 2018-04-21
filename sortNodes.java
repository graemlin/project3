import java.util.Comparator;


public class sortNodes implements Comparator<node>{
	
	private char compareBy = 'x';

	//default constructor
	public sortNodes(){
		
	}
	
	//determines which axis the comparison will be on depending on the char passed in
	public sortNodes(char compareByAxis){
		compareBy = compareByAxis;
	}

	//inspired by piazza post 168
	//compare function
	public int compare(node foo, node bar){
		//if the x, y, or z values is not equal, compare those values; if they are equal, compare their ids
		switch(compareBy){
		case 'x':
			 return (foo.value[0] != bar.value[0]) ? foo.value[0] - bar.value[0]:foo.id - bar.id;
		case 'y':
			return (foo.value[1] != bar.value[1]) ? foo.value[1] - bar.value[1]:foo.id - bar.id;
		case 'z':
			return (foo.value[2] != bar.value[2]) ? foo.value[2] - bar.value[2]:foo.id - bar.id;
		}
		return 0;
	}
}
