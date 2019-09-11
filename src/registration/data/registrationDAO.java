package registration.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import registration.util.SQLConnection;
import registration.model.registration_model;

public class registrationDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<registration_model> ReturnMatchingUsersList (String queryString) {
		ArrayList<registration_model> usersListInDB = new ArrayList<registration_model>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				
				registration_model r2 = new registration_model(); 
				r2.setusername(userList.getString("username"));
				r2.setpassword(userList.getString("password"));
				r2.setfirstname(userList.getString("firstname"));
				r2.setlastname(userList.getString("lastname"));
				r2.setutaid(userList.getString("utaid"));
				r2.setphone(userList.getString("phonenumber"));
				r2.setemail(userList.getString("email"));
				r2.setstreet(userList.getString("street"));
				r2.setcity(userList.getString("city"));
				r2.setzipcode(userList.getString("state"));
				r2.setstate(userList.getString("zipcode"));
				r2.setrole(userList.getString("role"));
				
				
				usersListInDB.add(r2);	
				
			}
		} catch (SQLException e) {System.out.println(e.getMessage());}
		
		return usersListInDB;
	}
	
	
	private static void StoreListinDB (registration_model r1,String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();

		try{
			
			stmt = conn.createStatement();
			
			String insertuser = queryString + " VALUES ('"  
					+ r1.getusername()  +   "','"
					+ r1.getpassword() + "','"		
					+ r1.getfirstname() + "','"
					+ r1.getlastname() + "','"
					+ r1.getrole() + "','"
					+ r1.getutaid() + "','"
					+ r1.getphone() + "','"
					+ r1.getemail() + "','"
					+ r1.getstreet() + "','"
					+ r1.getcity() + "','"
					+ r1.getstate() + "','"
					+ r1.getzipcode() + "')";
			

			

			stmt.executeUpdate(insertuser);	
		
			conn.commit(); 


		} catch (SQLException e) {System.out.println(e.getMessage());}
	}

	public static void insertuser(registration_model r1) {  
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
		
		registration_model r3 = new registration_model();
		while (userList.next()) {
		 
		r3.setusername(userList.getString("username"));
		r3.setpassword(userList.getString("password"));
		}
		
		pass = r3.getpassword();
		
		
		}
		catch (SQLException e) {System.out.println("this is the error message "+e.getMessage());}
		System.out.println("value of password in database "+pass);
		return pass;
	}

	
}
