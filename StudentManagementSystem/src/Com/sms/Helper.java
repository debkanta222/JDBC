 package Com.sms;
//importing packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import Com.sms.fetch.FetchTable;
import Com.sms.student.InsertTable;
import Com.sms.update.UpdateTable;


public class Helper {
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

public static void main(String...args) throws Exception {
	InsertTable it = new InsertTable();
	FetchTable ft = new FetchTable();
	UpdateTable ut = new UpdateTable();
	Scanner scan =new Scanner(System.in); 
	System.out.println("""
			Press 1 for Insert : 
			Press 2 for Display :
			Press 3 for Update :
			Press 4 for Delete :
			Press 5 for Exit : 
			""");
	int variable = scan.nextInt();
	System.out.println("-------------------------------");
	switch(variable) {
	case 1 : 
	  it.saveMenuDriven();
	  break;
	case 2 :  
		ft.fetchMenuDriven();
		break;
	case 3 :
		ut.updateMenuDriven();
		break;
	case 4 :
		ut.deleteMenuDriven();
		break;
	case 5 :
		System.exit(0);
		break;
	}
}}