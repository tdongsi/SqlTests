package my.sqltest.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility functions that deal with file system.
 * 
 * Copied from my https://github.com/tdongsi/java repo.
 * 
 * @author tdongsi
 * 
 */
public class SystemUtility {

	/**
	 * Enforce that this class should not be instantiated. Suppress default
	 * constructor.
	 */
	private SystemUtility() {
		throw new AssertionError("Do not instantiate.");
	}

	/**
	 * Check if a file exists at the given path
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isFileExists(String filePath) {
		Path path = Paths.get(filePath);
		return Files.isRegularFile(path) && Files.exists(path);
	}

	/**
	 * Equivalent to "cp src dest".
	 * 
	 * @param src
	 *            : path to the source file
	 * @param dest
	 *            : path to the destination file
	 * @return true if the copying is success, false otherwise.
	 */
	public static boolean copyFile(String src, String dest) {
		InputStream inStream = null;
		OutputStream outStream = null;
		boolean success = true;

		try {
			File inFile = new File(src);
			File outFile = new File(dest);

			inStream = new FileInputStream(inFile);
			outStream = new FileOutputStream(outFile);

			byte[] buffer = new byte[1024];

			int length;
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}

			if (inStream != null)
				inStream.close();
			if (outStream != null)
				outStream.close();

			System.out.println("Done copying file.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			success = false;
		} catch (IOException e) {
			e.printStackTrace();
			success = false;
		}

		return success;
	}

}
