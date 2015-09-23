package homework1;

import java.util.Scanner;

public class Boomer {
	char[] text={'a','b','c','d','e'};
	
	void swap(int first,int second){
		char temp=text[first];
		text[first]=text[second];
		text[second]=temp;
	}
	
	void work(int from,int to,int num){
		if(num==1){
			for(int i=from;i<=to;i++){
				for(int j=0;j<from;j++){
					System.out.print(text[j]+" ");
				}
				System.out.println(text[i]);
			}
		}
		else if(to-from+1==num){
			for(int i=0;i<=to;i++){
				System.out.print(text[i]+" ");
			}
			System.out.println();
		}
		else{
			//no from
			swap(from,to);
			this.work(from, to-1, num);
			swap(from,to);
			//has from
			this.work(from+1, to, num-1);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Boomer b=new Boomer();
		b.work(0, 4, 3);
	}

}
