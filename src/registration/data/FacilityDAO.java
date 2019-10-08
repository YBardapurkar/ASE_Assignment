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
		String queryOrder = "order by facility_name;";
		
		ArrayList<Facility> facilityList = new ArrayList<Facility>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(querySelect + queryWhere + queryOrder);
			
			while (result.next()) {
				
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
}