package registration.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import registration.model.MAR;
import registration.util.SQLConnection;

public class MARDAO {
	
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public static ArrayList<MAR> getAllMAR () {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		String query = " SELECT * from mar join assignment on mar.mar_id = assignment.mar_id where assignment.assigned_to != ''";
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(query);
			while (userList.next()) {
				MAR mar = new MAR();
				
				marList.add(mar);
			}
		}  catch (SQLException e) {System.out.println(e.getMessage());}
		return marList;
	}
}
