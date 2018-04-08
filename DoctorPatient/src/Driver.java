import java.io.BufferedReader;
import java.io.InputStreamReader;

//dheoclaveriabscs3a
public class Driver {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	public static void Printall(String d)
	{
		System.out.print(d);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Doctor[] doctor = new Doctor[1];
		
		try
		{
			for(int i = 0 ; i < doctor.length; i++ )
			{
				Printall("Doctor's First name :");
				String dfname = input.readLine();
				System.out.print("Doctor's last name :");
				String dlname = input.readLine();
				System.out.print("Doctor's Specialization :");
				String specs= input.readLine();		
				
				doctor[i] = new Doctor(dfname,dlname,specs);
				GetPatient(doctor[i]);
			}
			
		}
		catch(Exception ex)
		{
			for(Doctor d :  doctor)
			{
				System.out.println(ex);
				d.PrintDoctors();
				d.PrintAllPatient();
				d.PrintAllDiagnosis();
			}
		}

	}
	
		public static void GetPatient(Doctor d)
		{
			try
			{
				Printall("Press Enter to add patient or 1 to stop :");
				String patientfunc = input.readLine();
				
				while(!patientfunc.equals("1"))
				{
					if(patientfunc.equals(""))
					{
					Printall("Enter Patient(s) Fullname :");
					String pfname = input.readLine();
					
					Printall("Enter Patient(s) address :");
					String add = input.readLine();
					
					d.AddPatient(pfname,add);
					
					Printall("Press Enter to add Diagnosis or 2 to stop :");
					String diagnosisfunc = input.readLine();
					
					while(!diagnosisfunc.equals("2"))
							{
								if(diagnosisfunc.equals(""))
								{
								Printall("Enter Diagnosis(s) Examination :");
								String check = input.readLine();
								d.AddDiagnosis(check);
								
								Printall("Press Enter to add Diagnosis or 2 to stop :");
								diagnosisfunc = input.readLine();	
								}
								else
								{
									Printall("Invalid keyword !,\nPlease press Enter to add Diagnosis or 2 to stop :");
									diagnosisfunc= input.readLine();
								}
							}
					Printall("Press Enter to add patient or 1 to stop :");
					patientfunc = input.readLine();
					}
					else
					{
						Printall("Invalid keyword !,\nPlease press Enter to add Patient or 1 to stop :");
						patientfunc= input.readLine();
					}
				}
				
			
				d.PrintDoctors();
				d.PrintAllPatient();
				d.PrintAllDiagnosis();
				
			}
			catch(Exception ex)
			{
	
			}
		}
		
			
}
