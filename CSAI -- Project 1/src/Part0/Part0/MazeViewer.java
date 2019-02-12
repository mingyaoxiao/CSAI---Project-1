package Part0;
import java.io.File;
import java.util.*;
public class MazeViewer {
	List<Maze> loadedMazes;
	
	public void loadThenAddMaze(String fileLocation) {
		
	}
	
	public void loadMazes(String folderLocation) {
	//Will call loadAndAddMaze for each maze file it encounters
		File folder = new File("F:/Path");
		if(!folder.isDirectory()) return;
	    File[] files = folder.listFiles();
	    for (File file : files)
	    {
	        if (file.isFile())
	        {
	            System.out.println(file.getName());
	        }
	    }
	}
	
	public Maze nextDisplay() {
		return null;
	}
}
