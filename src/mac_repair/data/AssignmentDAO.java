package mac_repair.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import mac_repair.model.Assignment;
import mac_repair.util.DateUtils;
import mac_repair.util.SQLConnection;

import java.sql.Date;
import java.sql.ResultSet;

public class AssignmentDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	static DateUtils dateUtils = new DateUtils();
	
//	get count of mars assigned to a repairer on a given date
	public static int getAssignmentCountByDay(String username, Date date)
	{ 
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		int result = 0;
		
		String day = date.toString().split(" ")[0];
		String queryString = "SELECT count(*) FROM macrepairsys.assignment "
				+ "where date(assigned_date) = '" + day + "' and assigned_to = '" + username + "'";
		try{
			stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(queryString); 
			resultSet.next();
			result = resultSet.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
//	get count of mars assigned to a repairer on a given date
	public static int getAssignmentCountByWeek(String username)
	{
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		int result = 0;
		
		String startDay = dateUtils.getCurrentWeekStart().toString();
		String endDay = dateUtils.getCurrentWeekEnd().toString();
		System.out.println(startDay + " " + endDay);
		String queryString = "SELECT count(*) FROM macrepairsys.assignment "
				+ "where date(assigned_date) >= '" + startDay + "' and date(assigned_date) <= '" + endDay + "' and assigned_to = '" + username + "'";
		try{
			stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(queryString); 
			resultSet.next();
			result = resultSet.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
		
public static void assignRepairer(Assignment assignment) {
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		
		LocalDateTime now = LocalDateTime.now();
		Timestamp sqlNow = Timestamp.valueOf(now);
		String queryString = "insert into assignment (mar_id, assigned_to, assigned_date, urgency, estimate_repair) ";
		try{
			stmt = conn.createStatement();
			String insertuser = queryString + " VALUES ('"  
					+ assignment.getMarId()  +   "','"
					+ assignment.getAssignedTo() + "','"		
					+ sqlNow.toString() + "','"
					+ assignment.getUrgency() + "','"
					+ assignment.getEstimate() + "')";
			stmt.executeUpdate(insertuser);	
			conn.commit(); 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}



	public static Assignment getAssignedToByMarId(int marId) {
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		Assignment assign = new Assignment();
		LocalDateTime now = LocalDateTime.now();
		Timestamp sqlNow = Timestamp.valueOf(now);

		String queryString = "SELECT * FROM assignment where mar_id='" + marId + "'";
		try{
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(queryString); 
			while (result.next()) 
			{

				assign.setAssignedTo(result.getString("assigned_to"));
				assign.setAssignmentId(Integer.parseInt(result.getString("assignment_id")));
				assign.setUrgency(result.getString("urgency"));
				assign.setEstimate(Integer.parseInt(result.getString("estimate_repair")));
				assign.setAssignedDate(dateUtils.getSqlDate(result.getString("assigned_date")));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return assign;
	}

}

