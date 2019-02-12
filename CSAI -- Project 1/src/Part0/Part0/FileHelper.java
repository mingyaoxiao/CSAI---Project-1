package Part0;

import java.io.File;
import java.util.Optional;

public class FileHelper {
	public static String baseUrl = "src/Part0/Part0/"; 
	
	public static String getExtension(String fileName) {
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i);
		}
		return extension;
	}
	
	public static String getName(String fileName) {
		String name = "";

		int i = fileName.lastIndexOf('\\');
		int j = fileName.lastIndexOf('.');
		if (i > 0 && j > 0) {
		    name = fileName.substring(i+1,j);
		}
		return name;
	}
	
	public static Optional<File> returnEmptyOrValidDirectory(String filePath){
		File f = new File(filePath);
		if(!f.isDirectory()) return Optional.empty();
		else return Optional.of(f);
	}
}
