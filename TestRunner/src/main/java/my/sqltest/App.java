package my.sqltest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

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
		@Parameter(names = "-url", description = "Test database's JDBC URL.", required = true)
	    private String url;
		
		@Parameter(names = {"-username", "-u"}, description = "Login credentials.", required = true)
		private String username;
		
		@Parameter(names = {"-password", "-p"}, description = "Login credentials.", required = true)
		private String password;
		
		@Parameter(names = {"-file", "-f"}, description = "SQL file to be tested.", required = true)
		private String inputfile;

		public String getUrl() {
			return url;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}

		public String getInputfile() {
			return inputfile;
		}
		
	}
	
    public static void main( String[] args )
    {
        AppParameter params = new AppParameter();
        new JCommander(params, args);
        
        logger.debug("Database URL: {}", params.getUrl() );
        logger.debug("Username: {}", params.getUsername());
        // DO NOT log password
        logger.debug("SQL file: {}", params.getInputfile());
    }
}
