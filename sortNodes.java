import java.util.Comparator;


public class sortNodes implements Comparator<node>{
	
	private char compareBy = 'x';

	public sortNodes(){
		
	}
	
	public sortNodes(char compareByAxis){
		compareBy = compareByAxis;
	}

	//inspired by piazza post 168
	public int compare(node foo, node bar){
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
