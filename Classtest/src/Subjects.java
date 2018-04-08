
public class Subjects {
	
	private String subjectCode;
	private String subjectName;
	private int units;
	
	public Subjects(String sCode, String sName, int units)
	{
		this.subjectCode = sCode;
		this.subjectName = sName;
		this.units = (units <= 0? units : 0);
	}
	public void PrintSubjectDetails()
	{
		System.out.println("SubjectCode : " + this.subjectCode);
		System.out.println("SubjectName : " + this.subjectName);
		System.out.println("Units : " + this.units);
		


		
	}
	
	}

