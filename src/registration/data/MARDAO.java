package registration.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import registration.model.MAR;
import registration.model.User;
import registration.util.SQLConnection;
import java.util.Date;

public class MARDAO {
	
	static SQLConnection DBMgr = SQLConnection.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	 //2016/11/16 12:08:43
	//System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
	
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
	
		
	//Ajinkya
	
	private static void StoreListinDB (MAR mar,String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try{
			stmt = conn.createStatement();
			String insertmar = queryString + " VALUES ('"
					+ mar.getDescription()  +   "','"
					+ mar.getFacilityName()  +   "','"
					+ mar.getUrgency()  +   "','"
					+ mar.getDate()  +   "','"										
					+ mar.getReportedBy() + "')";
			stmt.executeUpdate(insertmar);	
			conn.commit(); 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertmar(MAR mar) {  
		StoreListinDB(mar,"INSERT INTO mar(description,facility_name,urgency,creation_date,reported_by) ");
	}
	
	
}
