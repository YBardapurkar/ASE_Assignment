package registration.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import registration.model.Reservation;
import registration.model.User;
import registration.util.SQLConnection;

public class ReservationDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static void StoreListinDB (Reservation r1,String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try{
			stmt = conn.createStatement();
			String insertreservation = queryString + " VALUES ('"  
					+ r1.getMarId()  +   "','"
					+ r1.getStartTime() + "','"		
					+ r1.getEndTime()  + "')";
			stmt.executeUpdate(insertreservation);	
			conn.commit(); 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void insertReservation(Reservation reservation) {  
		StoreListinDB(reservation,"INSERT INTO reservation(mar_id,start_timestamp,end_timestamp) ");
	}

}
