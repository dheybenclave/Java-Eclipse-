import java.util.ArrayList;
import java.util.List;

public class Patient {	
			
	private String fullname;
	private String address;
	
			public Patient(String f, String a)
			{
				this.fullname = f;
				this.address = a;
			}		
			public void PrintPatient()
			{
				System.out.println("Fullname : " + this.fullname);
				System.out.println("Address : " + this.address);				
			}
}
