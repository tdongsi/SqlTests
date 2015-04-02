package my.sqltest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import com.google.common.base.Strings;

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
		
		public static JCommander jc;
		
		@Parameter(names = {"-database", "-d"}, description = "Test database's JDBC URL.", required = true)
	    private String url;
		
		@Parameter(names = {"-username", "-u"}, description = "Login credentials.", required = true)
		private String username;
		
		@Parameter(names = {"-password", "-p"}, description = "Login credentials.", required = true)
		private String password;
		
		@Parameter(names = {"-file", "-f"}, description = "SQL file to be tested.", required = true)
		private List<String> inputfile = new ArrayList<String>();
				
		// Vertica-specific hidden option.
		
		@Parameter(names = {"-schema", "-s"}, description = "Vertica schema to be tested.", required = false, hidden=true)
		private String schema;
		
		public String getUrl() {
			return url;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}

		public List<String> getInputFiles() {
			return inputfile;
		}
		
		public String getSchema() {
			return schema;
		}

		public static AppParameter parseCommandLine(String[] args) {
			AppParameter params = new AppParameter();
	        jc = new JCommander(params);
	        jc.setProgramName("java -jar TestRunner.jar");
	        
	        // These are only available in later JCommander version
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
			AppParameter.jc.usage();
			return;
		}
        
        logger.debug("Database URL: {}", params.getUrl() );
        logger.debug("Username: {}", params.getUsername());
        // DO NOT log password
        logger.debug("SQL file: {}", params.getInputFiles());
        logger.debug("Schema name: {}", Strings.nullToEmpty(params.getSchema()));
        
        Connection conn = JdbcConnections.getVerticaConnection(
        		params.getUsername(), params.getPassword(), params.getUrl());
        
        if (params.getSchema() != null ) {
        	VerticaUtility.useVerticaSchema(conn, params.getSchema() );
        }
        
        if ( conn != null ) {
        	logger.info("Connected to database");
        	
        	// do something
        	SqlRunner runner = new SqlRunner(conn);
        	try {
				for ( String file : params.getInputFiles()) {
					logger.info("Running file: {}", file);
					runner.runScript(file);
				}
			} catch (IOException e1) {
				logger.error("Error reading one of the files: {}", params.getInputFiles());
			} catch (SQLException e1) {
				logger.error("Error executing SQL in file: {}", e1.getMessage());
			}
        	
        	// Close connection
        	try {
				conn.close();
			} catch (SQLException e) {
				logger.error("Could not close connection");
			}
        } else {
        	logger.error( "Could not connect to the specified database.");
        }

    }

	
}
