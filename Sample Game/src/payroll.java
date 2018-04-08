   import java.awt.*;
    import java.applet.*;
    import java.awt.event.*;
    import java.awt.Label;
    import java.text.DecimalFormat;

     public class payroll extends Applet implements ActionListener{
      TextField txtname,txtposition,txtdays,txtrate, txtsalary;
      Label lblname,lblposition,lbldays,lblrate,lblsalary,title;
      Button button,clear;

 DecimalFormat dFormat = new DecimalFormat("0.00");
      public void init(){
        setLayout(null);


          title = new Label("Simple Payroll System");
		  title.setBounds(90,20,150,20);
		  add(title);
		  title.setAlignment(title.CENTER);

        lblname = new Label("Employee Name ");
        lblname.setBounds(20,50,100,20);
        add(lblname);

        txtname = new TextField(51);
        txtname.setBounds(150,50,180,20);
        add(txtname);

        lbldays = new Label("Number of Days");
        lbldays.setBounds(20,90,100,20);
        add(lbldays);

        txtdays = new TextField(5);
        txtdays.setBounds(150,90,100,20);
        add(txtdays);

        lblrate = new Label("Rate Per Day");
        lblrate.setBounds(20,130,130,20);
        add(lblrate);
        txtrate = new TextField(5);
        txtrate.setBounds(150,130,100,20);
        add(txtrate);


        lblsalary = new Label("Employee Salary");
        lblsalary.setBounds(20,170,130,20);
        add(lblsalary);
        txtsalary = new TextField(5);
        txtsalary.setBounds(150,170,100,20);
        add(txtsalary);


        button = new Button(" Compute Salary ");
        button.setBounds(70,230,100,20);
        add(button);

		clear = new Button(" Clear ");
        clear.setBounds(230,230,100,20);
        add(clear);

        button.addActionListener(this);
        clear.addActionListener(this);


        }
        public void actionPerformed(ActionEvent ae){

       int days =Integer.parseInt(txtdays.getText());
       double rate=Double.parseDouble(txtrate.getText());
       double compute_salary = (days * rate);

       txtsalary.setText("Php " +dFormat.format(compute_salary));
        txtsalary.setEditable(false);
			if(ae.getSource() == clear)
			{
			 txtname.setText("");
			  txtdays.setText("");
			  txtrate.setText("");
			   txtsalary.setText("");
			   txtname.requestFocus();
    }
}
}