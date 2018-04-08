import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;


public class Array_Test {
	
	public static void main(String[] args) {
		int index = 6;
		Array_Test array = new Array_Test();
		array.generateRandomValue();
		//array.insertValue(12);
		//array.printArrays();
		//array.deleteValue(4);
		//array.printArrays();
		//array.bubbleSort();
		//array.printArrayHorizontal();
		//array.selectSort();
		//array.insertionSort();
	
		//String[] names = {"Sehun" , "Taehyung" , "John" , "Ace" , "Matrik" , "Bo" , "Jun"};
		//sortObject(names);
		///for(String n : names){
			//System.out.println(n);
		//}
		//System.out.println("The value get at index " + index  + " is " + array.getValueAtIndex(index));
		//System.out.println("Inserted value " + array.insertValue(12));
		//System.out.println("Delete " + array.deleteValue(4));
		
		//Stack<String> s = new Stack<>();
		//s.push("4");
		//s.push("2");
		/*s.push("1");
		s.push("3");
		
		

		//System.out.println(s.size());
		while(!s.isEmpty()){
			//System.out.println(s.pop());
			//System.out.println(s.size());
		}*/
	
		Queue<String> q = new PriorityQueue<>();
		q.add("Ivy");
		q.add("Rose");
		q.add("Ruiz");
		
		while(!q.isEmpty()){
			System.out.print("|");
			System.out.print(q.remove());
			System.out.print("|");
			System.out.print(q.size());
		}
		
	}

	private int[] myArray = new int[50];
	private int size= 10;
	
	private void generateRandomValue(){
		
		for(int i =0; i < size; i++){
			Random r = new Random();
			myArray[i] = r.nextInt(20) + 1;
			
		}
	}
	private void printArrays()
	{
		System.out.println("--------------");
		for(int i =0; i < size; i++){
			System.out.print("|" + i + "|");
			System.out.println(myArray[i] + "|");
			System.out.println("--------------");
		}
	}
	public int getValueAtIndex(int index){
		if(index < size)	
		return myArray[index];	
		return 0;
	}
	public int insertValue(int value){
		if(size < myArray.length){
			myArray[size] = value;
			size++;
		}
		return value;
	}
	public void deleteValue(int index){
		for(int i =0; i < size; i++)
		{
			if(myArray[index] == myArray[i])
			{
			  myArray[i] = myArray[i+1];
			}
		}
		size--;
	}
	public void deleteElement(int index){
		if(index < size){
			for(int i = index; i < (size -1); i++){
				myArray[i] = myArray[1 + 1];
			}
			size--;
		}
	}
	public void printArrayHorizontal(){
		for(int i =0; i < size; i++){
			System.out.print("|" + myArray[i] + " ");
		}
		System.out.println("|");
		for(int i =0; i < size; i++)	{
			System.out.print("------------------");
		}
		System.out.println();
	}
	public void bubbleSort(){
		int temp; 
		for(int i = size; i >=0; i--){
			for(int j =0; j < (size -1); j++){
				temp = j +1;
				if(myArray[j] > myArray[temp]){
					swap(j , temp);
				}
			}
			printArrayHorizontal();
		}
	}
	public void swap(int val1 , int val2){
		int temp = myArray[val1];
		myArray[val1] = myArray[val2];
		myArray[val2] = temp;
	}
	public void selectSort(){
		for(int i =0; i < size; i++){
			int min = i;
			for(int j =i; j < size; j++){
				if(myArray[min] >  myArray[j]){
					min =j;
				}
			}
			swap(i , min);
			printArrayHorizontal();
		}
		
	}
	public void insertionSort(){
		for(int i =1; i < size; i++)
		{
			int j = i;
			int toInsert = myArray[i];
			while((j > 0) && (myArray[j-1] > toInsert)){
				myArray[j] = myArray[j-1];
				j--;			
			}
			myArray[j] = toInsert;
		
		}
		printArrayHorizontal();
	}
	public static void sortObject(Comparable[] arr){
		for(int i =0; i < arr.length; i++){
			for(int j =i; j > 0 && less(arr[j] , arr[j -1]); j--){
				exchange(arr , j, j -1);
			}
		}
	}
	public static boolean less(Comparable<Comparable> v , Comparable<?> w){
		
		return (v.compareTo(w) < 0);
	}
	public static void exchange(Comparable[] arr ,int i , int j){
		Comparable<?> a = arr[i];
		arr[i] = arr[j];
		arr[j] = a;
	}
	
	
}
