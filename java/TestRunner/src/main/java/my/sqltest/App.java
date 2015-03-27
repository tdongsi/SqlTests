package my.sqltest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import my.sqltest.JdbcConnections;

/**
 * @author cdongsi
 *
 */
public class App 
{
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	/**
	 * Parsing command-line parameter using JCommander
	 */
	public static class AppParameter {
		@Parameter(names = {"-database", "-d"}, description = "Test database's JDBC URL.", required = true)
	    private String url;
		
		@Parameter(names = {"-username", "-u"}, description = "Login credentials.", required = true)
		private String username;
		
		@Parameter(names = {"-password", "-p"}, description = "Login credentials.", required = true)
		private String password;
		
		@Parameter(names = {"-file", "-f"}, description = "SQL file to be tested.", required = true)
		private List<String> inputfile = new ArrayList<String>();

		public String getUrl() {
			return url;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}

		public List<String> getInputfile() {
			return inputfile;
		}
		
		public static AppParameter parseCommandLine(String[] args) {
			AppParameter params = new AppParameter();
	        JCommander jc = new JCommander(params);
//	        jc.setCaseSensitiveOptions(false);
//	        jc.setAllowAbbreviatedOptions(true);
	        jc.parse(args);
			return params;
		}
		
	}
	
    public static void main( String[] args )
    {
        AppParameter params = null;
        try {
        	params = AppParameter.parseCommandLine(args);
		} catch (ParameterException e) {
			System.out.println("ERROR: " + e.getMessage() );
			return;
		}
        
        logger.debug("Database URL: {}", params.getUrl() );
        logger.debug("Username: {}", params.getUsername());
        // DO NOT log password
        logger.debug("SQL file: {}", params.getInputfile());
        
        Connection conn = JdbcConnections.getVerticaConnection(
        		params.getUsername(), params.getPassword(), params.getUrl());
        
        // do something
        logger.info("Connected to database");
        
        if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("Could not close connection");
			}
		}
    }

	
}
