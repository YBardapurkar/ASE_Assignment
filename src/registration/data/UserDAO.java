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
				
				User r2 = new User(); 
				r2.setUsername(userList.getString("username"));
				r2.setPassword(userList.getString("password"));
				r2.setFirstname(userList.getString("firstname"));
				r2.setLastname(userList.getString("lastname"));
				r2.setUtaId(userList.getString("utaid"));
				r2.setPhone(userList.getString("phonenumber"));
				r2.setEmail(userList.getString("email"));
				r2.setStreet(userList.getString("street"));
				r2.setCity(userList.getString("city"));
				r2.setZipcode(userList.getString("state"));
				r2.setState(userList.getString("zipcode"));
				r2.setRole(userList.getString("role"));
				
				
				usersListInDB.add(r2);	
				
			}
		} catch (SQLException e) {System.out.println(e.getMessage());}
		
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


		} catch (SQLException e) {System.out.println(e.getMessage());}
	}

	public static void insertuser(User r1) {  
		StoreListinDB(r1,"INSERT INTO registration(username,password,firstname,lastname,role,utaid,phonenumber,email,street,city,state,zipcode) ");
	}

	public static Boolean usernameUnique(String username)  {
		boolean andy = (ReturnMatchingUsersList(" SELECT * from registration WHERE username = '"+username+"' ORDER BY username").isEmpty());
		
		return (ReturnMatchingUsersList(" SELECT * from registration WHERE username = '"+username+"'").isEmpty());
		
}

	public static String login(String username)  {
		
		String password_login = "SELECT * from registration WHERE username = '"+username+"' ORDER BY username";
		System.out.println("query string "+password_login);
		Statement stmt = null;
		String pass = "";
		Connection conn = SQLConnection.getDBConnection();
		try{
			
			System.out.println("after the try");
		stmt = conn.createStatement();
		
		ResultSet userList = stmt.executeQuery(password_login);
		System.out.println("after execting the query");
		
		User r3 = new User();
		while (userList.next()) {
		 
		r3.setUsername(userList.getString("username"));
		r3.setPassword(userList.getString("password"));
		}
		
		pass = r3.getPassword();
		
		
		}
		catch (SQLException e) {System.out.println("this is the error message "+e.getMessage());}
		System.out.println("value of password in database "+pass);
		return pass;
	}

	
}
