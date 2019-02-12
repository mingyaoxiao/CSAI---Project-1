package Part0;
import java.io.File;
import java.util.*;
public class MazeViewer {
	List<MazeDisplay> loadedMazeDisplays;
	ListIterator<MazeDisplay> mazeIterator;
	
	public MazeViewer() {
		loadedMazeDisplays = new ArrayList<MazeDisplay>();
		mazeIterator = loadedMazeDisplays.listIterator();
	}
	
	public void loadThenAddMaze(String name) {
		Maze readMaze = Maze.loadMaze(FileHelper.baseUrl + "\\" + name);
		mazeIterator.add(new MazeDisplay(name,readMaze));
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
	
	public MazeDisplay nextDisplay() {
		if(mazeIterator.hasNext()) return mazeIterator.next();
		return null;
	}
	
	public MazeDisplay prevDisplay() {
		if(mazeIterator.hasPrevious()) return mazeIterator.previous();
		return null;
	}
	
	public void resetCursor() {
		this.mazeIterator = this.loadedMazeDisplays.listIterator();
	}
}
