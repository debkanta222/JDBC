package lAB_01DEC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


class DuplicateDataException extends Exception{
	public DuplicateDataException(String str) {
		super(str);
	}
}

class LessSalaryAmountException extends Exception{
	public LessSalaryAmountException(String str) {
		super(str);
	}
}

class DepartmentNotFoundException extends Exception{
	public DepartmentNotFoundException(String str) {
		super(str);
	}
}

public class EmployeeUploader {
	static {
		 try {
		 	Class.forName("com.mysql.cj.jdbc.Driver");//registering mysql driver
	 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 }
	 public static Connection con()throws SQLException{
		 return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Debkanta@1998");
		 
	 }
	 	static Scanner sc = new Scanner(System.in);
		int e_id, e_contact, d_id;
		float e_salary;
		String e_name, e_address,d_name,d_head,d_desc;
		//saving employee details in database
		public void storeDepartmentDetails()throws SQLException{
	    	System.out.println("Enter Department id :");
	    	d_id=sc.nextInt();
	    	System.out.println("enter Department name :");
	    	d_name=sc.next();
	    	System.out.println("Enter Department Head :");
	    	d_head=sc.next();
	    	System.out.println("Enter Department Description :");
	    	d_desc=sc.next();
	    	Connection conn=EmployeeUploader.con();
	    	PreparedStatement stmt=conn.prepareStatement("insert into Department values(?,?,?,?)");
	    	stmt.setInt(1, d_id);
	    	stmt.setString(2, d_name);
	    	stmt.setString(3, d_head);
	    	stmt.setString(4, d_desc);
	    	stmt.executeUpdate();
	    	System.out.println("----New Record Inserted----");
		}
		public void storeEmployeeDetails()throws SQLException, DepartmentNotFoundException, LessSalaryAmountException, DuplicateDataException{
	    	System.out.println("Enter Employee Id :");
	    	e_id=sc.nextInt();
	    	System.out.println("Enter Employee Name :");
	    	e_name=sc.next();
	    	System.out.println("Enter Employee Address :");
	    	e_address=sc.next();
	    	System.out.println("Enter Employee Salary :");
	    	e_salary=sc.nextFloat();
	    	System.out.println("Enter Employee Contact :");
	    	e_contact=sc.nextInt();
	    	System.out.println("Enter Department Id : ");
	    	d_id=sc.nextInt();
	    	Connection conn=EmployeeUploader.con();
	    	PreparedStatement stmt=conn.prepareStatement("insert into Employee values(?,?,?,?,?,?)");
	    	stmt.setInt(1, e_id);
	    	stmt.setString(2, e_name);
	    	stmt.setString(3, e_address);
	    	stmt.setFloat(4, e_salary);
	    	stmt.setInt(5, e_contact);
	    	stmt.setInt(6, d_id);
	    	stmt.executeUpdate();
	    	//validation logic
	     /*   if((d_id==e_id) || (d_name==e_name)){	//if already not present in DB
	            throw new DuplicateDataException("Employee already exists");
	        }
	        if(e_salary<1000) {		
	            throw new LessSalaryAmountException("Salary can not be less than 1000/-");
	        }if(d_id==0){
	            throw new DepartmentNotFoundException("Department does not exists");
	        }
	    	System.out.println("----New Record Inserted----");*/
		}
		public void retrieveEmployeeDetails() throws SQLException{
			Connection conn = EmployeeUploader.con();
			Statement stmt = conn.createStatement();
			ResultSet rs1=stmt.executeQuery("select * from Department");
			while(rs1.next()) {
				System.out.println(rs1.getInt(1)+" "+rs1.getString(2)+" "+rs1.getString(3)+" "+rs1.getString(4));			
			}
			System.out.println("---------------------------\n");
			System.out.println("Details of Employee:");
			ResultSet rs2=stmt.executeQuery("select * from Employee");
			while(rs2.next()) {
				System.out.println(rs2.getInt(1)+" "+rs2.getString(2)+" "+rs2.getString(3)+" "+rs2.getFloat(4)+" "+rs2.getInt(5));			
			}
			System.out.println("---------------------------\n");
		}
	public void calculatePF(int e_id) throws SQLException{
		Connection conn = EmployeeUploader.con();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select e_salary from Employee where e_id ="+e_id);
		while(rs.next()) {
			e_salary=rs.getInt(1);
			System.out.println(e_salary);
			if(e_salary>=1000.00 && e_salary<1500) {
				float pf = (e_salary*5)/100;
				System.out.println("Your PF is : " + pf);
			}
			 if(e_salary>=1500.00 && e_salary <2000) {
				float pf = (e_salary*6)/100;
				System.out.println("Your PF is : " + pf);
			}
			 if(e_salary>=2000.00) {
				float pf = (e_salary*7)/100;
				System.out.println("Your PF is : " + pf);
			}
		}
	}
		
	public static void main(String[] args) throws SQLException, DepartmentNotFoundException, LessSalaryAmountException, DuplicateDataException {
		EmployeeUploader eu = new EmployeeUploader();
		System.out.println("""
				Press 1 for Insert Department Records: 
				Press 2 for Insert Employee Records :
				Press 3 for Display Records :
				Press 4 for Checking PF :
				Press 5 for Exit : 
				""");
		int variable = sc.nextInt();
		System.out.println("-------------------------------");
		switch(variable) {
		case 1 : 
			eu.storeDepartmentDetails();
			break;
		case 2 :  
			eu.storeEmployeeDetails();
			break;
		case 3 :
			System.out.println("Details of Department:");
			eu.retrieveEmployeeDetails();
			break;
		case 4 :
			System.out.println("Enter employee id :");
			int id = sc.nextInt();
			eu.calculatePF(id);
			break;
		case 5 :
			System.out.println("--Thank You--");
			break;
		}
	}
}