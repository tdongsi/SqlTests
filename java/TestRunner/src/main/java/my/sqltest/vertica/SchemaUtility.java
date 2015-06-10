package my.sqltest.vertica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility functions that deal with Vertica schemas.
 * 
 * @author tdongsi
 *
 */
public class SchemaUtility {
	
	private static final Logger logger = LoggerFactory
			.getLogger(SchemaUtility.class);

	/**
	 * Override default constructor.
	 */
	private SchemaUtility() {
		throw new AssertionError("This utility class should have no instance");
	}

	/**
	 * Use the schema, specified by name. For multiple schemas, separate them by
	 * ','. E.g.: "public, v_catalog" Basically execute the Vertica query: set
	 * search_path to schemaName1, schemaName2
	 * 
	 * @param name
	 */
	public static void useVerticaSchema(Connection conn, String schema) {
		String query = String.format("set search_path to %s", schema);

		try {
			Statement stmt = conn.createStatement();
			boolean hasOutput = stmt.execute(query);
			assert !hasOutput;

			ResultSet rs = stmt.executeQuery("show search_path");
			while (rs.next()) {
				logger.debug("Search path: {}", rs.getString(2));
			}
		} catch (SQLException e) {
			logger.error("Error switching schema {}", schema);
			logger.error("Error executing SQL: {}", e.getMessage());
		}

	}
}
