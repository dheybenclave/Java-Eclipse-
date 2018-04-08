import java.io.InputStreamReader;
import java.io.BufferedReader;



public class Driver {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		student[] students = new student[2];
		try
		{
		for (int i = 0; i < students.length; i++)
		{
			System.out.print("Enter first name :");
			String fname = input.readLine();
			System.out.print("Enter last name :");
			String lname = input.readLine();
			System.out.print("Enter Student Number :");
			String studNo = input.readLine();
			System.out.print("Enter Your Course :");
			String courseName = input.readLine();
			
			
			
			students[i] = new student(studNo, fname,lname,courseName);
		}
		}catch(Exception ex)
		{
			
		}
		for (int i = 0; i < students.length; i++)
		{
			students[i].PrintStudentDetails();
		}
		
		
	}

}
