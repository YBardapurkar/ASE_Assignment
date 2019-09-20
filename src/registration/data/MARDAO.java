package registration.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import registration.model.MAR;
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
		String query = "SELECT mar.mar_id, mar.description, mar.facility_name, mar.urgency, mar.creation_date, assignment.assigned_to "
				+ "from mar "
				+ "left outer join assignment on mar.mar_id = assignment.mar_id ";
		
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
//				mar.setDate(result.getString("creation_date"));
				
				marList.add(mar);
			}
		}  catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return marList;
	}

//	Get list of assigned MAR
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
	
//	Get list of MAR by assigned repairer username
	public static ArrayList<MAR> getMARByAssignedRepairer (String assignedTo) {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		String query = ""
				+ "SELECT mar.mar_id, mar.description, mar.facility_name, mar.urgency, mar.creation_date, assignment.assigned_to "
				+ "from mar "
				+ "join assignment on mar.mar_id = assignment.mar_id "
				+ "where assignment.assigned_to = '" + assignedTo + "'";
		
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
		System.out.println("in db");
	}

	public static void insertmar(MAR mar) {  
		StoreListinDB(mar,"INSERT INTO mar(description,facility_name,urgency,creation_date,reported_by) ");
	}
	
//	Yash
//	get list of unassigned MAR
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
	
//	get MAR by mar_id
	public static MAR getMARByID(int id) {
		MAR mar = new MAR();
		String query = ""
				+ "SELECT mar.mar_id, mar.description, mar.facility_name, mar.urgency, mar.creation_date, assignment.assigned_to "
				+ "from mar "
				+ "left outer join assignment on mar.mar_id = assignment.mar_id "
				+ "where mar.mar_id = '" + id + "';";
		
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
			mar.setAssignedTo(result.getString("assigned_to"));
//			mar.setDate(Date.valueOf(result.getString("creation_date")));
			
		}  catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return mar;
	}
	
	private static ArrayList<MAR> ReturnMatchingUserList (String queryString) {
		ArrayList<MAR> marListInDB = new ArrayList<MAR>();
		Statement stmt = null;
//		final SQLConnection  obj = SQLConnection.getInstance();
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet marList = stmt.executeQuery(queryString);
			while (marList.next()) { 
				MAR mar = new MAR();
				mar.setId(Integer.parseInt(marList.getString("mar_id")));
				mar.setDescription(marList.getString("description"));
				mar.setUrgency(marList.getString("urgency"));
				mar.setEstimateRepair(marList.getString("estimate_repair"));
				mar.setFacilityName(marList.getString("facility_name"));
				marListInDB.add(mar);	
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return marListInDB;
	}
	
	public static ArrayList<MAR> getMARSubmittedByUser(String username) {
		ArrayList<MAR> marList = new ArrayList<MAR>();
		String query = ""
				+ "SELECT mar.mar_id, mar.description, mar.facility_name, mar.urgency, mar.creation_date, assignment.assigned_to "
				+ "from mar "
				+ "left outer join assignment on mar.mar_id = assignment.mar_id "
				+ "where mar.reported_by = '" + username + "';";
		
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

	public static ArrayList<MAR>  listMARS(String username,MAR mar) {  
		String selectUser = "select t1.mar_id,assigned_date,estimate_repair,facility_name,urgency,description from macrepairsys.assignment t1 inner join macrepairsys.mar t2 on t1.mar_id = t2.mar_id and t1.assigned_to ='";
		selectUser+=username+"'";
		return ReturnMatchingUserList(selectUser);
	}
	
	
	private static ArrayList<MAR> ReturnMatchingMARList (String queryString) {
		 ArrayList<MAR> usersListInDB = new ArrayList<MAR>();
		 
		 Statement stmt = null;
		 Connection conn = SQLConnection.getDBConnection();
		 try {
		  stmt = conn.createStatement();
		  ResultSet result = stmt.executeQuery(queryString);
		  while (result.next()) {
		   
		   MAR mar = new MAR(); 
		   mar.setId(Integer.parseInt(result.getString("mar_id")));
			mar.setDescription(result.getString("description"));
			mar.setUrgency(result.getString("urgency"));
			mar.setFacilityName(result.getString("facility_name"));
			mar.setAssignedTo(result.getString("assigned_to"));
		   
		   usersListInDB.add(mar); 
		  }
		 } catch (SQLException e) {
		  System.out.println(e.getMessage());
		 }
		 return usersListInDB;
		}
	
	public static ArrayList<MAR> searchUnassignedMAR(String searchField, String filter, String Urg)  {
		 
		 
		 if(filter.equals("1"))
		 {
		  return (ReturnMatchingMARList("SELECT mar.mar_id, mar.description, mar.facility_name, mar.urgency, mar.creation_date, assignment.assigned_to from mar left outer join assignment on mar.mar_id = assignment.mar_id  where assignment.assigned_to is null and facility_name ='"+searchField+"' "));
		 }
		 
		 else 
		 {
		  return (ReturnMatchingMARList("SELECT mar.mar_id, mar.description, mar.facility_name, mar.urgency, mar.creation_date, assignment.assigned_to from mar left outer join assignment on mar.mar_id = assignment.mar_id where assignment.assigned_to is null and mar.urgency='"+Urg+"' "));   
		 }
		 

		}


		

}
