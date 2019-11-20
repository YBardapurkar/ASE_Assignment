package mac_repair.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import mac_repair.model.MAR;
import mac_repair.util.SQLConnection;


public class MARDAO {
	
	static SQLConnection DBMgr = SQLConnection.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	 //2016/11/16 12:08:43
	//System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
	
//	get all mar
	public static ArrayList<MAR> getAllMAR () {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		marList.addAll(getMARListFacilityManager(""));
		return marList;
	}
		
//	Get list of MAR by assigned repairer username
	public static ArrayList<MAR> getMARByAssignedRepairer (String assignedTo) {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		marList.addAll(getMARList("where assignment.assigned_to = '" + assignedTo + "' "));
		return marList;
	}
	
//	Search list of MAR by assigned repairer username
	public static ArrayList<MAR> searchMARByAssignedRepairer (String assignedTo) {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		marList.addAll(getMARList("where assignment.assigned_to like '%" + assignedTo + "%' "));
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
					//+ mar.getUrgency()  +   "','"
					+ mar.getDate()  +   "','"										
					+ mar.getReportedBy() + "')";
			stmt.executeUpdate(insertmar);	
			conn.commit(); 
			
			//Extracting mar id
			System.out.println(mar.getDate());
			
			stmt = conn.createStatement();
			String idForMar = "SELECT MAX(mar_id) as recent_id FROM macrepairsys.mar ORDER BY mar_id";
			ResultSet resultId = stmt.executeQuery(idForMar);
			
			while (resultId.next()) {
				
				
				mar.setId(Integer.parseInt(resultId.getString("recent_id")));
			
		} 
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static void insertmar(MAR mar) {  
		StoreListinDB(mar,"INSERT INTO mar(description,facility_name,creation_date,reported_by) ");
	}
	
//	get list of unassigned MAR
	public static ArrayList<MAR> getUnassignedMAR () {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		marList.addAll(getMARList("where assignment.assigned_to is null "));
		return marList;
	}
	
//	get MAR by mar_id
	public static MAR getMARByID(int id) {
		MAR mar = new MAR();
		ArrayList<MAR> marList = new ArrayList<MAR>();
		marList.addAll(getMARListFacilityManager("where mar.mar_id = '" + id + "' "));
		return marList.isEmpty() ? mar : marList.get(0);
	}
	
//	get list of MAR created by user
	public static ArrayList<MAR> getMARSubmittedByUser(String username) {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		marList.addAll(getMARListForUser("where mar.reported_by = '" + username + "' "));
		return marList;
	}
	
//	Search MAR by facility name (partial match)
	public static ArrayList<MAR> searchMARByFacilityName(String facilityName) {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		marList.addAll(getMARList("where mar.facility_name like '%" + facilityName + "%' "));
		return marList;
	}
	
//	Get list of MAR by creation date (exact match)
	public static ArrayList<MAR> getMARByDate(String dateString) {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		marList.addAll(getMARList("where date(mar.creation_date) = '" + dateString + "' "));
		return marList;
	}
	
	//Ajinkya
	
//	Get list of MAR by creation date (exact match)
	public static ArrayList<MAR> getAssignedMARByDate(String dateString) {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		marList.addAll(getMARList("where date(assignment.assigned_date) = '" + dateString + "' "));
		return marList;
	}
	
//	do not remove this yet
	private static ArrayList<MAR> getMARList (String queryWhere) {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		String querySelect = ""
				+ "SELECT mar.mar_id, mar.description, mar.facility_name, assignment.urgency, mar.creation_date, assignment.assigned_to "
				+ "from mar "
				+ " left outer join assignment on mar.mar_id = assignment.mar_id ";
		String queryOrder = "order by mar.mar_id;";
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			
			System.out.println(querySelect + queryWhere + queryOrder);
			ResultSet result = stmt.executeQuery(querySelect + queryWhere + queryOrder);
			while (result.next()) {
				MAR mar = new MAR();
				
				mar.setId(Integer.parseInt(result.getString("mar_id")));
				mar.setDescription(result.getString("description"));
			//	mar.setUrgency(result.getString("urgency"));
				mar.setFacilityName(result.getString("facility_name"));
			//	mar.setAssignedTo(result.getString("assigned_to"));
				mar.setDate(result.getString("creation_date"));
				
				marList.add(mar);
			}
		} catch (SQLException e) {System.out.println(e.getMessage());}
		return marList;
	}
	
	
	private static ArrayList<MAR> getMARListFacilityManager (String queryWhere) {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		String querySelect = ""
				+ "SELECT mar.mar_id, mar.description, mar.facility_name, assignment.urgency, mar.creation_date, assignment.assigned_to FROM mar\r\n" + 
				"LEFT JOIN assignment ON mar.mar_id = assignment.mar_id ";
				
		String queryOrder = "order by mar.mar_id;";
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			System.out.println(querySelect + queryWhere + queryOrder);
			ResultSet result = stmt.executeQuery(querySelect + queryWhere + queryOrder);
			while (result.next()) {
				MAR mar = new MAR();
				
				mar.setId(Integer.parseInt(result.getString("mar_id")));
				mar.setDescription(result.getString("description"));
				//mar.setUrgency(result.getString("urgency"));
				mar.setFacilityName(result.getString("facility_name"));
				mar.setDate(result.getString("creation_date"));
				//mar.setAssignedTo(result.getString("assigned_to"));
				
				marList.add(mar);
			}
		} catch (SQLException e) {System.out.println(e.getMessage());}
		return marList;
	}
	
	private static ArrayList<MAR> getMARListForUser (String queryWhere) {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		String querySelect = ""
				+ "SELECT mar.mar_id, mar.description, mar.facility_name,mar.creation_date "
				+ "from mar ";
		String queryOrder = " order by mar.mar_id;";
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			System.out.println(querySelect + queryWhere + queryOrder);
			ResultSet result = stmt.executeQuery(querySelect + queryWhere + queryOrder);
			System.out.println(result);
			while (result.next()) {
				MAR mar = new MAR();
				
				mar.setId(Integer.parseInt(result.getString("mar_id")));
				mar.setDescription(result.getString("description"));
				mar.setFacilityName(result.getString("facility_name"));
				mar.setDate(result.getString("creation_date"));
				marList.add(mar);
			}
		} catch (SQLException e) {System.out.println(e.getMessage());}
		return marList;
	}
	
}
