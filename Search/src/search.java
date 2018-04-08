import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class search {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private int[] myarray = new int[50];
	private int size = 20;

	
	private void GenerateRandomVal(){
		
		for(int i  = 0; i<size;i++ ){
			
			Random r = new Random();
			myarray[i] = r.nextInt(20)+1;
			
		}	
	}
	
	private void printArray(){
		
		System.out.print(".........");
		for(int i = 0 ; i< size ;i++){
			
			System.out.print("|"+i+"|");
			System.out.print(myarray[i]+"|");
			System.out.print(".........");
		}
	}

}
