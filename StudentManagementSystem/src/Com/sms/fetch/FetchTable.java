package Com.sms.fetch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Com.sms.Helper;

public class FetchTable {
	//fetching employee details in the database (display)
			public void fetchMenuDriven() throws SQLException{
				Connection conn = Helper.con();
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery("select * from MenuDriven");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getInt(5));			
				}	
			}
}