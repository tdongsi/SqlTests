package my.sqltest;

import java.util.Arrays;

import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * Unit test for simple App.
 */
public class AppTest {

	/**
	 * Test command line
	 */
	@Test
	public void testCommandlineParams() {
		String[] argv = { "-u", "dbadmin", "-p", "password","-d", "jdbc:vertica://192.168.5.133:5433/VMart", "-f", "test.sql"};
		App.AppParameter params = App.AppParameter.parseCommandLine(argv);
		
		Assert.assertEquals(params.getUsername(), "dbadmin");
		Assert.assertEquals(params.getPassword(), "password");
		Assert.assertEquals(params.getInputfile(), Arrays.asList("test.sql"));
		Assert.assertEquals(params.getUrl(), "jdbc:vertica://192.168.5.133:5433/VMart");
	}
	
	@Test
	public void testCommandlineParams_Full() {
		String[] argv = { "-username", "dbadmin", "-password", "password","-d", "jdbc:vertica://192.168.5.133:5433/VMart", "-file", "test.sql"};
		App.AppParameter params = App.AppParameter.parseCommandLine(argv);
		
		Assert.assertEquals(params.getUsername(), "dbadmin");
		Assert.assertEquals(params.getPassword(), "password");
		Assert.assertEquals(params.getInputfile(), Arrays.asList("test.sql"));
		Assert.assertEquals(params.getUrl(), "jdbc:vertica://192.168.5.133:5433/VMart");
	}
	
	@Test
	public void testCommandlineParams_Mixed() {
		String[] argv = { "-username", "dbadmin", "-password", "password","-d", "jdbc:vertica://192.168.5.133:5433/VMart", "-f", "test.sql"};
		App.AppParameter params = App.AppParameter.parseCommandLine(argv);
		
		Assert.assertEquals(params.getUsername(), "dbadmin");
		Assert.assertEquals(params.getPassword(), "password");
		Assert.assertEquals(params.getInputfile(), Arrays.asList("test.sql"));
		Assert.assertEquals(params.getUrl(), "jdbc:vertica://192.168.5.133:5433/VMart");
	}
	
	@Test
	public void testCommandlineParams_MultipleFiles() {
		String[] argv = { "-u", "dbadmin", "-p", "password","-d", 
				"jdbc:vertica://192.168.5.133:5433/VMart", 
				"-f", "test.sql", "-f", "test2.sql", "-f", "my.test"};
		App.AppParameter params = App.AppParameter.parseCommandLine(argv);
		
		Assert.assertEquals(params.getUsername(), "dbadmin");
		Assert.assertEquals(params.getPassword(), "password");
		Assert.assertEquals(params.getInputfile(), Arrays.asList("test.sql","test2.sql", "my.test"));
		Assert.assertEquals(params.getUrl(), "jdbc:vertica://192.168.5.133:5433/VMart");
	}
}
