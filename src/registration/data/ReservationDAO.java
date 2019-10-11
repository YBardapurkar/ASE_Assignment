package registration.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import registration.model.MAR;
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
	
	
	public static void updateReservation(Reservation reservation) {
		String query1 = "UPDATE reservation " + "SET " + "start_timestamp = '" + reservation.getStartTime() + "' "+", end_timestamp = '"+ reservation.getEndTime() + "' "+"WHERE mar_id ='" + reservation.getMarId() + "'" + ";";

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query1);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static boolean reserveCheck(int mard_id)
	{
		boolean resultRet = false;
		String querySelect = "select *  FROM macrepairsys.reservation where mar_id = " + mard_id;
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();			
			ResultSet result = stmt.executeQuery(querySelect);
			while (result.next()) {
				resultRet = true;
			} 
		}	
		catch (SQLException e) {System.out.println(e.getMessage());}{
			}
		return resultRet;
	}
	

}
