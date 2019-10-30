package registration.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {	
	public static String DB_DRIVER;
	public static String DB_CONNECTION;
	public static String DB_USER;
	public static String DB_PASSWORD; 
	public static SQLConnection single_instance = null;
	public SQLConnection() {
		DB_DRIVER = "com.mysql.jdbc.Driver";
		DB_CONNECTION  = "jdbc:mysql://localhost:3306/macrepairsys?autoReconnect=true&useSSL=false";
		DB_USER  = "root";
		DB_PASSWORD = "1234567890";
	}
	public static synchronized SQLConnection getInstance() {
        if (single_instance == null)
        	single_instance = new SQLConnection();
        return single_instance;
	}

	public static Connection getDBConnection() {	
		Connection dbConnection = null;	 

		try {	 
			Class.forName(DB_DRIVER);	 
		} catch (ClassNotFoundException e) {	 
		}

		try {	 
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			dbConnection.setAutoCommit(false);
		} catch (SQLException e) {	    
		}	 
		return dbConnection;	 
	}
}