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
	
static int count;

	private static ArrayList<Facility> ReturnMatchingFacilitysList (String queryString) {
		ArrayList<Facility> FacilityListInDB = new ArrayList<Facility>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet facilityList = stmt.executeQuery(queryString);
			
			while (facilityList.next()) {
				
				Facility newFacility = new Facility(); 
				newFacility.setFacilityName(facilityList.getString("facility_name"));
				newFacility.setFacilityType(facilityList.getString("facility_type"));
				newFacility.setFacilityInterval(facilityList.getString("facility_interval"));
				newFacility.setFacilityDuration(facilityList.getString("duration"));
				newFacility.setFacilityVenue(facilityList.getString("venue"));
				
				FacilityListInDB.add(newFacility);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return FacilityListInDB;
	}
	
	private static void StoreListinDB (String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try{
			stmt = conn.createStatement();
			
			
			
			/*String insertfacility = queryString + " VALUES ('"  
					+ r1.getFacilityName()  +   "','"
					+ r1.getFacilityType() + "','"		
					+ r1.getFacilityInterval() + "','"
					+ r1.getDuration() + "','"
					+ r1.getVenue() + "')";*/
			stmt.executeUpdate(queryString);	
			conn.commit(); 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static ArrayList<Facility> getFacilitiesByFacilityType(String facilityType)
	{
		return ReturnMatchingFacilitysList(" SELECT * from facility WHERE facility_type = '"+facilityType+"' order by length(facility_name),facility_name");
		
	}
	
	public static int settingFacilityCount(String facilityType)
	{
		ReturnMatchingFacilitysList(" SELECT * from facility WHERE facility_type = '"+facilityType+"' order by length(facility_name),facility_name");
		return count;
	}

	public static void insertNewFacility(Facility facility) {
		String query = "insert into facility(facility_name,facility_type,facility_interval,duration,venue) values('" 
				+ facility.getFacilityName() + "', '"
				+ facility.getFacilityType() + "', '"
				+ facility.getFacilityInterval() + "', '"
				+ facility.getFacilityDuration() + "', '"
				+ facility.getFacilityVenue()+"')";
		StoreListinDB(query);
	}

	public static ArrayList<String> getFacilityName()
	{
		ArrayList<String> FacilityListInDB = new ArrayList<>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			count = 0;
			stmt = conn.createStatement();
			ResultSet facilityList = stmt.executeQuery("SELECT facility_name FROM macrepairsys.facility");
			
			while (facilityList.next()) {		
				FacilityListInDB.add(facilityList.getString("facility_name"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return FacilityListInDB;
	}
	
	
	public static ArrayList<Facility> searchFacilityByDate(String facilityType) //displaying same day facilities
	{
		return ReturnMatchingFacilitysList(" SELECT * from facility WHERE facility_type = '"+facilityType+"'");
		
	}
	
	
}