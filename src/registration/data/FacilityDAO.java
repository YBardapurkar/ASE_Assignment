package registration.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import registration.model.Facility;
import registration.util.SQLConnection;


public class FacilityDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public static ArrayList<Facility> getAllFacilities() {
		return ReturnMatchingFacilitysList("");
	}
	

	
	public static Facility getFacilityByFacilityName(String facilityName) {
		Facility facility = new Facility();
		ArrayList<Facility> facilityList =  ReturnMatchingFacilitysList("WHERE facility_name = '" + facilityName + "' ");
		return facilityList.isEmpty() ? facility : facilityList.get(0);
	}
	
	public static ArrayList<Facility> getFacilitiesByFacilityType(String facilityType) {
		
		return ReturnMatchingFacilitysList("WHERE facility_type = '" + facilityType + "' ");
	}

	private static ArrayList<Facility> ReturnMatchingFacilitysList (String queryWhere) {
		String querySelect = "SELECT * from facility ";
		String queryOrder = "order by length(facility_name),facility_name;";
	//	System.out.println(querySelect + queryWhere + queryOrder);
		
		ArrayList<Facility> facilityList = new ArrayList<Facility>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(querySelect + queryWhere + queryOrder);
		
			
			
			while (result.next()) {
				
				System.out.println(result.getString("facility_type"));
				Facility newFacility = new Facility(); 
				newFacility.setFacilityName(result.getString("facility_name"));
				newFacility.setFacilityType(result.getString("facility_type"));
				newFacility.setFacilityInterval(result.getString("facility_interval"));
				newFacility.setFacilityDuration(result.getString("duration"));
				newFacility.setFacilityVenue(result.getString("venue"));
				
				facilityList.add(newFacility);
				
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return facilityList;
	}
	
	private static void StoreListinDB (String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try{
			stmt = conn.createStatement();
			stmt.executeUpdate(queryString);	
			conn.commit(); 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertNewFacility(Facility facility) {
		String query = "insert into facility(facility_name,facility_type,facility_interval,duration,venue) values('" 
				+ facility.getFacilityName() + "', '"
				+ facility.getFacilityType() + "', '"
				+ facility.getFacilityInterval() + "', '"
				+ facility.getFacilityDuration() + "', '"
				+ facility.getFacilityVenue() + "')";
		StoreListinDB(query);
	}

//	get list of facility names
	public static ArrayList<String> getFacilityName() {
		ArrayList<String> facilityNames = new ArrayList<>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet facilityList = stmt.executeQuery("SELECT facility_name FROM macrepairsys.facility");
			
			while (facilityList.next()) {		
				facilityNames.add(facilityList.getString("facility_name"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return facilityNames;
	}
	
	
public static ArrayList<Facility>  showFacilities (String query) {
	ArrayList<Facility> facilityList = new ArrayList<Facility>();
	
	System.out.println(query);
	
	
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();
	try {
		stmt = conn.createStatement();
		//System.out.println(querySelect + queryWhere + queryOrder);
		ResultSet result = stmt.executeQuery(query);
		
		while (result.next()) {
			Facility facility = new Facility();
			
//			facility.setStartTimestamp(result.getString("start_timestamp"));
//			facility.setEndTimestamp(result.getString("end_timestamp"));
			facility.setFacilityName(result.getString("facility_name"));
			facility.setFacilityVenue(result.getString("venue"));
			facility.setFacilityDuration(result.getString("duration"));
			facility.setFacilityType(result.getString("facility_type"));
			facility.setFacilityInterval(result.getString("facility_interval"));
			
			facilityList.add(facility);
		}
	} catch (SQLException e) {System.out.println(e.getMessage());}
	return facilityList;
}

		
		public static ArrayList<Facility> showFacilitiesCalling (String facilityType, String timestamp) 
		{
			/*return showFacilities("SELECT reservation.start_timestamp, reservation.end_timestamp, facility.facility_type, facility.facility_interval, facility.duration, facility.venue, facility.facility_name from facility "
					+ "inner join mar on mar.facility_name = facility.facility_name "
					+ "INNER JOIN reservation ON mar.mar_id = reservation.mar_id WHERE facility.facility_type = '"+facilityType+"'"
							+ " and reservation.start_timestamp <= '" + timestamp + "' and reservation.end_timestamp >= '" + timestamp + "'");*/
	
			
			return showFacilities("select * from facility where facility_type = '"+facilityType+"'" 
					+ " and facility_name not in (SELECT facility.facility_name from facility inner join" 
					+ " mar on mar.facility_name = facility.facility_name" 
					+ " INNER JOIN reservation ON mar.mar_id = reservation.mar_id WHERE facility.facility_type = '"+facilityType+"'" 
					+ " and reservation.start_timestamp <= '" + timestamp + "'" 
					+ " and reservation.end_timestamp >= '" + timestamp + "')");

		}
}


