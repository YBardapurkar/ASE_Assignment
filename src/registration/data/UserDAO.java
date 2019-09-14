package registration.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import registration.util.SQLConnection;
import registration.model.User;

public class UserDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<User> ReturnMatchingUsersList (String queryString) {
		ArrayList<User> usersListInDB = new ArrayList<User>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				
				User user = new User(); 
				user.setUsername(userList.getString("username"));
				user.setPassword(userList.getString("password"));
				user.setFirstname(userList.getString("firstname"));
				user.setLastname(userList.getString("lastname"));
				user.setUtaId(userList.getString("utaid"));
				user.setPhone(userList.getString("phonenumber"));
				user.setEmail(userList.getString("email"));
				user.setStreet(userList.getString("street"));
				user.setCity(userList.getString("city"));
				user.setZipcode(userList.getString("state"));
				user.setState(userList.getString("zipcode"));
				user.setRole(userList.getString("role"));
				
				usersListInDB.add(user);	
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return usersListInDB;
	}
	
	
	private static void StoreListinDB (User r1,String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try{
			stmt = conn.createStatement();
			String insertuser = queryString + " VALUES ('"  
					+ r1.getUsername()  +   "','"
					+ r1.getPassword() + "','"		
					+ r1.getFirstname() + "','"
					+ r1.getLastname() + "','"
					+ r1.getRole() + "','"
					+ r1.getUtaId() + "','"
					+ r1.getPhone() + "','"
					+ r1.getEmail() + "','"
					+ r1.getStreet() + "','"
					+ r1.getCity() + "','"
					+ r1.getState() + "','"
					+ r1.getZipcode() + "')";
			stmt.executeUpdate(insertuser);	
			conn.commit(); 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertuser(User user) {  
		StoreListinDB(user,"INSERT INTO registration(username,password,firstname,lastname,role,utaid,phonenumber,email,street,city,state,zipcode) ");
	}

	public static Boolean usernameUnique(String username)  {
//		boolean andy = (ReturnMatchingUsersList(" SELECT * from registration WHERE username = '"+username+"' ORDER BY username").isEmpty());
		return (ReturnMatchingUsersList(" SELECT * from registration WHERE username = '"+username+"'").isEmpty());
	}

	public static User login(String username, String password)  {
		String query = "SELECT * from registration WHERE username = '"+username+"' and password = '"+password+"' ORDER BY username";
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		User user = new User();
		try{
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(query);
			while (userList.next()) {
				user.setUsername(userList.getString("username"));
				user.setPassword(userList.getString("password"));
				user.setRole(userList.getString("role"));
			}
		} catch (SQLException e) {
			System.out.println("this is the error message "+e.getMessage());
		}
		
		return user;
	}
}
