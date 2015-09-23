package homework1;

public class Jockey {
	
	
	static void work(int total,int goal,int from){
		if(total==1){
			System.out.println("put the 1 from "+from+" to "+goal);
		}
		else{
			int calculate;
			for(calculate=1;calculate<4;calculate++){
				if(calculate!=goal&&calculate!=from)
					break;
			}
			work(total-1,calculate,from);
			
			System.out.println("put the "+total+" from "+from+" to "+goal);
			work(total-1,goal,calculate);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jockey.work(5, 3,1);
	}

}
