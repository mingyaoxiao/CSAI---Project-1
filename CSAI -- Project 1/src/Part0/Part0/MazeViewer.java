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
		File folder = new File(folderLocation);
		if(!folder.isDirectory()) return;
	    File[] files = folder.listFiles();
	    for (File file : files)
	    {
	    	System.out.println(file.getName() + " -- " + FileHelper.getExtension(file.getName()));
	    	if (FileHelper.getExtension(file.getName()).equals(Maze.mazeExtension))
	        {
	    		if(file.canRead()) {
	    			if(file.isFile()) {
	    				loadThenAddMaze(FileHelper.getName(file.getPath()));
	    			}
	    		}
	        }
	    }
	}
	
	public Maze nextDisplay() {
		if(mazeIterator.hasNext()) return mazeIterator.next();
		return null;
	}
	
	public Maze prevDisplay() {
		if(mazeIterator.hasPrevious()) return mazeIterator.previous();
		return null;
	}
	
	public void resetCursor() {
		this.mazeIterator = this.loadedMazes.listIterator();
	}
}
