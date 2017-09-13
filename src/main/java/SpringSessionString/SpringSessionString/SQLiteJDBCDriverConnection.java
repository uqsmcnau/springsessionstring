package SpringSessionString.SpringSessionString;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteJDBCDriverConnection {

	// Used For Testing
	public static void clearDatabase() {
	    Connection connection = null;
	    
	    try {
			Class.forName("org.sqlite.JDBC");

		    // create a database connection
		    connection = DriverManager.getConnection("jdbc:sqlite:database.db");
		    Statement statement = connection.createStatement();
		    statement.setQueryTimeout(30);  // set timeout to 30 sec.
		    
		    statement.executeUpdate("drop table if exists session_id");
	    } catch(ClassNotFoundException e) {
	    	System.err.println(e.getMessage());
	    } catch(SQLException e) {
	    	System.err.println(e.getMessage());
	    } finally {
	    	try {
	    		if (connection != null) {
	    			connection.close();
	    		}
	    	} catch(SQLException e) {
	    		// connection close failed.
	    		System.err.println(e);
	    	}
	    }			
	}
	
	// Update the database with the users current state string
	public static void setUserSessionString(String user_id, String session_string) {		
	    Connection connection = null;
	    
	    try {
			Class.forName("org.sqlite.JDBC");

		    // create a database connection
		    connection = DriverManager.getConnection("jdbc:sqlite:database.db");
		    Statement statement = connection.createStatement();
		    statement.setQueryTimeout(30);  // set timeout to 30 sec.
		    
		    statement.executeUpdate("create table if not exists session_id (user_id string, value string)");
		    
		    // Remove all existing entries
		    statement.executeUpdate("delete from session_id where user_id='" + user_id + "'");
		    statement.executeUpdate("insert into session_id values('" + user_id + "', '" + session_string + "')");
	    } catch(ClassNotFoundException e) {
	    	System.err.println(e.getMessage());
	    	e.printStackTrace();
	    } catch(SQLException e) {
	    	System.err.println(e.getMessage());
	    	e.printStackTrace();
	    } finally {
	    	try {
	    		if (connection != null) {
	    			connection.close();
	    		}
	    	} catch(SQLException e) {
	    		// connection close failed.
	    		System.err.println(e);
	    	}
	    }	
	}
	
	// Get the users current state string from the database
	public static String getUserSessionString(String user_id) {		
	    Connection connection = null;
	    
	    String returnValue = "";
	    
	    try {
			Class.forName("org.sqlite.JDBC");

		    // create a database connection
		    connection = DriverManager.getConnection("jdbc:sqlite:database.db");
		    Statement statement = connection.createStatement();
		    statement.setQueryTimeout(30);  // set timeout to 30 sec.
		    
		    statement.executeUpdate("create table if not exists session_id (user_id string, value string)");
		    
		    ResultSet rs = statement.executeQuery("select * from session_id where user_id='" + user_id + "'");
		    while(rs.next()) {
		    	returnValue = rs.getString("value");
		    }
	    } catch(ClassNotFoundException e) {
	    	System.err.println(e.getMessage());
	    	e.printStackTrace();
	    } catch(SQLException e) {
	    	System.err.println(e.getMessage());
	    	e.printStackTrace();
	    } finally {
	    	try {
	    		if (connection != null) {
	    			connection.close();
	    		}
	    	} catch(SQLException e) {
	    		// connection close failed.
	    		System.err.println(e);
	    	}
	    }
	    return returnValue;
	}
	
}