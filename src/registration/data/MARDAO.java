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
		String query = " SELECT * from mar";
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while (result.next()) {
				MAR mar = new MAR();
				
				mar.setId(Integer.parseInt(result.getString("mar_id")));
				mar.setDescription(result.getString("description"));
				mar.setUrgency(result.getString("urgency"));
				mar.setFacilityName(result.getString("facility_name"));
				mar.setReportedBy(result.getString("reported_by"));
//				mar.setDate(result.getString("creation_date"));
				
				marList.add(mar);
			}
		}  catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return marList;
	}

	public static ArrayList<MAR> getAssignedMAR () {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		String query = ""
				+ "SELECT mar.mar_id, mar.description, mar.facility_name, mar.urgency, mar.creation_date, assignment.assigned_to "
				+ "from mar "
				+ "join assignment on mar.mar_id = assignment.mar_id "
				+ "where assignment.assigned_to is not null;";
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while (result.next()) {
				MAR mar = new MAR();
				
				mar.setId(Integer.parseInt(result.getString("mar_id")));
				mar.setDescription(result.getString("description"));
				mar.setUrgency(result.getString("urgency"));
				mar.setFacilityName(result.getString("facility_name"));
				mar.setAssignedTo(result.getString("assigned_to"));
				
				marList.add(mar);
			}
		}  catch (SQLException e) {System.out.println(e.getMessage());}
		return marList;
	}
	
	public static ArrayList<MAR> getUnassignedMAR () {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		String query = ""
				+ "SELECT mar.mar_id, mar.description, mar.facility_name, mar.urgency, mar.creation_date, assignment.assigned_to "
				+ "from mar "
				+ "left outer join assignment on mar.mar_id = assignment.mar_id "
				+ "where assignment.assigned_to is null;";
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while (result.next()) {
				MAR mar = new MAR();
				
				mar.setId(Integer.parseInt(result.getString("mar_id")));
				mar.setDescription(result.getString("description"));
				mar.setUrgency(result.getString("urgency"));
				mar.setFacilityName(result.getString("facility_name"));
				mar.setAssignedTo(result.getString("assigned_to"));
				
				marList.add(mar);
			}
		}  catch (SQLException e) {System.out.println(e.getMessage());}
		return marList;
	}
	
	public static MAR getMARByID(int id) {
		MAR mar = new MAR();
		String query = " SELECT * from mar where mar_id = " + id + ";";
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(query);
			result.first();
			
			mar.setId(Integer.parseInt(result.getString("mar_id")));
			mar.setFacilityName(result.getString("facility_name"));
			mar.setDescription(result.getString("description"));
			mar.setUrgency(result.getString("urgency"));
//			mar.setDate(Date.valueOf(result.getString("creation_date")));
			
		}  catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return mar;
	}
}
