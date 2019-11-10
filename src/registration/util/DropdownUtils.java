package registration.util;

import java.util.ArrayList;

import registration.data.UserDAO;
import registration.model.Facility;

public class DropdownUtils {
	
//	return dropdown list for roles
	public static ArrayList<String> getRoleDropdown() {
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("");
		roles.add("Student");
		roles.add("Faculty");
		roles.add("Repairer");
		if (UserDAO.getUsersByRole("Facility Manager").isEmpty()) {
			roles.add("Facility Manager");
		}
		if (UserDAO.getUsersByRole("Admin").isEmpty()) {
			roles.add("Admin");
		}
		return roles;
	}
	
//	return dropdown list for all roles
	public static ArrayList<String> getAllRolesDropdown() {
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("Student");
		roles.add("Faculty");
		roles.add("Repairer");
		roles.add("Facility Manager");
		roles.add("Admin");
		return roles;
	}
	
//	return dropdown list for states
	public static ArrayList<String> getStateDropdown() {
		ArrayList<String> states = new ArrayList<String>();
		states.add("");
		states.add("Alabama");
		states.add("Alaska");
		states.add("Arizona");
		states.add("Arkansas");
		states.add("California");
		states.add("Colorado");
		states.add("Connecticut");
		states.add("Delaware");
		states.add("District of Columbia");
		states.add("Florida");
		states.add("Georgia");
		states.add("Hawaii");
		states.add("Idaho");
		states.add("Illinois");
		states.add("Indiana");
		states.add("Iowa");
		states.add("Kansas");
		states.add("Kentucky");
		states.add("Louisiana");
		states.add("Maine");
		states.add("Maryland");
		states.add("Massachusetts");
		states.add("Michigan");
		states.add("Minnesota");
		states.add("Mississippi");
		states.add("Missouri");
		states.add("Montana");
		states.add("Nebraska");
		states.add("Nevada");
		states.add("New Hampshire");
		states.add("New Jersey");
		states.add("New Mexico");
		states.add("New York");
		states.add("North Carolina");
		states.add("North Dakota");
		states.add("Ohio");
		states.add("Oklahoma");
		states.add("Oregon");
		states.add("Pennsylvania");
		states.add("Rhode Island");
		states.add("South Carolina");
		states.add("South Dakota");
		states.add("Tennessee");
		states.add("Texas");
		states.add("Utah");
		states.add("Vermont");
		states.add("Virginia");
		states.add("Washington");
		states.add("West Virginia");
		states.add("Wisconsin");
		states.add("Wyoming");
		return states;
	}
	
//	Facility Type Dropdown
	public static ArrayList<Facility> getFacilityTypeDropdown() {
		ArrayList<Facility> facilities = new ArrayList<Facility>();
		facilities.add(new Facility("MR", "Multipurpose rooms", "1", "Same Day", "Indoor"));
		facilities.add(new Facility("IBBC", "Indoor basketball courts", "1", "Same Day", "Indoor"));
		facilities.add(new Facility("IVBC", "Volleyball courts", "1", "Same Day", "Indoor"));
		facilities.add(new Facility("SCG", "Indoor soccer gymnasium", "2", "Same Day", "Indoor"));
		facilities.add(new Facility("RBC", "Racquetball courts", "0.5", "Same Day", "Indoor"));
		facilities.add(new Facility("BMC", "Badminton courts", "0.5", "Same Day", "Indoor"));
		facilities.add(new Facility("TT", "Table Tennis", "0.5", "Same Day", "Indoor"));
		facilities.add(new Facility("CR", "Conference rooms", "1", "Same Day", "Indoor"));
		facilities.add(new Facility("OVBC", "Outdoor Volleyball Courts", "2", "7-Day", "Outdoor"));
		facilities.add(new Facility("OBBC", "Outdoor Basketball Courts", "2", "7-Day", "Outdoor"));
		return facilities;
	}
	
//	Repair time by duration
	public static ArrayList<String[]> getRepairTimeDropdown(String duration) {
		ArrayList<String[]> repairTimes = new ArrayList<String[]>();
		repairTimes.add(new String[]{"1 hour", "1" });
		repairTimes.add(new String[]{"2 hours", "2" });
		repairTimes.add(new String[]{"5 hours", "5" });
		repairTimes.add(new String[]{"10 hours", "10" });
		if (duration.equals("7-Day")) {
			repairTimes.add(new String[]{"1 day", "18" });
			repairTimes.add(new String[]{"2 days", "36" });
		}
		return repairTimes;
	}
}
