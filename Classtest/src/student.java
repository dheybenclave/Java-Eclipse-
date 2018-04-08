
public class student {public student(String s,String f,String l,String c)
		{
			this.studNo = s;
			this.fName = f;
			this.lName = l;
			this.courseName = c;
		}

		private String studNo;
		private String fName;
		private String lName;
		private String courseName;
		
		public void PrintStudentDetails()
		{
			System.out.println("Fullname : " + this.fName + " " + this.lName);
			System.out.println("StudentNo : " + this.studNo);
			System.out.println("Course : " + this.courseName);
			
		
		}
	}

