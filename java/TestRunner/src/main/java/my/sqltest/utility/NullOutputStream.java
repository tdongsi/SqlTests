package my.sqltest.utility;

import java.io.IOException;
import java.io.OutputStream;

/**
 * An output stream that does nothing. For usage similar to file "/dev/null".
 * Copied from my https://github.com/tdongsi/java repo.
 * 
 * @author tdongsi
 *
 */
public class NullOutputStream extends OutputStream {

	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int b) throws IOException {
		// Do nothing
	}

}