package my.sqltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcConnections {
	private static final Logger logger = LoggerFactory.getLogger(JdbcConnections.class);
	
	/**
	 * Return a JDBC connection to a Vertica server.
	 * 
	 * @param username
	 * @param password
	 * @param url
	 * @return
	 */
	public static Connection getVerticaConnection(String username, String password, String url) {
		Connection conn;

		// Load JDBC driver
		try {
			Class.forName("com.vertica.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// Could not find the driver class. Likely an issue
			// with finding the .jar file.
			e.printStackTrace();
			logger.error("Could not find the JDBC driver class.");
			return null;
		}

		// Create property object to hold username & password
		Properties myProp = new Properties();
		myProp.put("user", username);
		myProp.put("password", password);
		try {
			conn = DriverManager.getConnection(url, myProp);
		} catch (SQLException e) {
			// Could not connect to database.
			logger.error("Could not connect to database. Check url, username, and password. "
					+ e.getMessage());
			return null;
		}

		// Connection is established
		return conn;
	}

}
