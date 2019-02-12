package Part0;

import java.io.File;
import java.util.Optional;

public class FileHelper {
	public static String getExtension(String fileName) {
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		}
		return extension;
	}
	
	public static Optional<File> returnEmptyOrValidDirectory(String filePath){
		File f = new File(filePath);
		if(!f.isDirectory()) return Optional.empty();
		else return Optional.of(f);
	}
}
