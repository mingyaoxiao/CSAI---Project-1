package Part0;
import java.io.File;
import java.util.*;
public class MazeViewer {
	List<Maze> loadedMazes;
	ListIterator<Maze> mazeIterator;
	
	public MazeViewer() {
		loadedMazes = new ArrayList<Maze>();
		mazeIterator = loadedMazes.listIterator();
	}
	
	public void loadThenAddMaze(String fileLocation) {
		Maze readMaze = Maze.loadMaze(fileLocation);
		mazeIterator.add(readMaze);
	}
	
	public void loadMazes(String folderLocation) {
		File folder = new File("F:/Path");
		if(!folder.isDirectory()) return;
	    File[] files = folder.listFiles();
	    for (File file : files)
	    {
	        if (file.isFile() && FileHelper.getExtension(file.getName()) == Maze.mazeExtension)
	        {
	            loadThenAddMaze(file.getPath());
	        }
	    }
	}
	
	public Maze nextDisplay() {
		if(mazeIterator.hasNext())return mazeIterator.next();
		return null;
	}
}
