package registration.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import registration.util.SQLConnection;

public class AssignmentDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public static void assignRepairer(String username, int duration, int marId) {
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		
		LocalDateTime now = LocalDateTime.now();
		Timestamp sqlNow = Timestamp.valueOf(now);
		
		String queryString = "insert into assignment (mar_id, assigned_to, assigned_date, estimate_repair) ";
		try{
			stmt = conn.createStatement();
			String insertuser = queryString + " VALUES ('"  
					+ marId  +   "','"
					+ username + "','"		
					+ sqlNow.toString() + "','"
					+ duration + "')";
			stmt.executeUpdate(insertuser);	
			conn.commit(); 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
