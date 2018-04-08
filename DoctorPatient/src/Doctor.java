import java.util.ArrayList;
import java.util.List;

public class Doctor {
	
	private List<Patient> patient;
	private List<Diagnosis> diagnosis;	
	private String fname;
	private String lname;
	private String specs;
	private static int counterpatient = 0 ;
	private static int counterdiagnosis = 0 ;
	
		public Doctor(String f, String l, String s)
		{
			this.fname = f;
			this.lname = l;
			this.specs = s;
			patient = new ArrayList<Patient>();
			diagnosis = new ArrayList<Diagnosis>();
		}
		
		public void AddPatient(String sfllname, String address)
		{
			patient.add(new Patient(sfllname,address));
			patient.get(counterpatient);
			counterpatient++;
		}
		public void PrintAllPatient()
		{
			for(Patient p : patient )
			{p.PrintPatient();}
		}
		
		public void AddDiagnosis(String dcheck)
		{
			diagnosis.add(new Diagnosis(dcheck));
		}
		public void PrintAllDiagnosis()
		{
			for(Diagnosis d : diagnosis )
			{d.PrintDiagnosis();
			patient.get(counterdiagnosis);
			counterdiagnosis++;
			}
		}
		
		public void PrintDoctors()
		{
			System.out.println("Doctor's Fullname : " + this.fname + " " + this.lname);
			System.out.println("Doctor's Specilization : " + this.specs);
		}
}
