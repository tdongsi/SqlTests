package my.sqltest;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.beust.jcommander.JCommander;

/**
 * Unit test for simple App.
 */
public class AppTest {

	/**
	 * Test command line
	 */
	@Test
	public void testCommandlineParams() {
		String[] argv = { "-u", "dbadmin", "-p", "password","-url", "jdbc:vertica://192.168.5.133:5433/VMart", "-f", "test.sql"};
		App.AppParameter params = new App.AppParameter();
		new JCommander(params, argv);
		
		Assert.assertEquals(params.getUsername(), "dbadmin");
		Assert.assertEquals(params.getPassword(), "password");
		Assert.assertEquals(params.getInputfile(), "test.sql");
		Assert.assertEquals(params.getUrl(), "jdbc:vertica://192.168.5.133:5433/VMart");
	}
}
