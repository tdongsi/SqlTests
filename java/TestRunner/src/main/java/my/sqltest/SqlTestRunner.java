package my.sqltest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.script.ScriptEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cdongsi
 *
 */
public class SqlTestRunner {
	
	public static class JsonLiterals {
		public static final String NAME = "name";
		public static final String QUERY = "query";
		public static final String EXPECTED = "expected";
		public static final String EQUAL = "equal";
		public static final String FILE = "file";
	}
	
	private static final Logger logger = LoggerFactory.getLogger(SqlTestRunner.class);
	private static ScriptEngine engine;

	private Connection connection;

	/**
	 * Default constructor
	 */
	public SqlTestRunner(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Runs an SQL script, specified by the filePath parameter
	 * 
	 * @param filePath
	 *            - the source of the script
	 */
	public void runScript(String filePath) throws IOException, SQLException {
		try {
			boolean originalAutoCommit = connection.getAutoCommit();
			try {
				if (!originalAutoCommit) {
					connection.setAutoCommit(true);
				}
				runScript(connection, filePath);
			} finally {
				connection.setAutoCommit(originalAutoCommit);
			}
		} catch (IOException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error running script.  Cause: " + e, e);
		}
	}
	
	/**
	 * Runs an SQL script (specified by the filePath) using the
	 * connection passed in
	 * 
	 * @param conn
	 *            - the connection to use for the script
	 * @param filePath
	 *            - the source of the script
	 * @throws SQLException
	 *             if any SQL errors occur
	 * @throws IOException
	 *             if there is an error reading from the given filePath
	 */
	private void runScript(Connection conn, String filePath) throws IOException,
			SQLException {
		// TODO: Implement this
	}

}
