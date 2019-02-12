package Part0;
import java.io.File;
import java.util.*;
public class MazeViewer {
	List<MazeDisplay> loadedMazeDisplays;
	ListIterator<MazeDisplay> mazeIterator;
	boolean wentRight = false;
	boolean wentLeft = false;
	
	public MazeViewer() {
		loadedMazeDisplays = new ArrayList<MazeDisplay>();
		mazeIterator = loadedMazeDisplays.listIterator();
	}
	
	public void loadThenAddMaze(String path) {
		Maze readMaze = Maze.loadMaze(path);
		mazeIterator.add(new MazeDisplay(FileHelper.getName(path),readMaze));
	}
	
	public void loadMazes(String folderLocation) {
		File folder = new File(folderLocation);
		if(!folder.isDirectory()) return;
	    File[] files = folder.listFiles();
	    for (File file : files)
	    {
	    	//System.out.println(file.getName() + " -- " + FileHelper.getExtension(file.getName()));
	    	if (FileHelper.getExtension(file.getName()).equals(Maze.mazeExtension))
	        {
	    		if(file.canRead()) {
	    			if(file.isFile()) {
	    				loadThenAddMaze(file.getPath());
	    			}
	    		}
	        }
	    }
	}
	
	public MazeDisplay nextDisplay() {
		if(this.wentLeft) {
			this.wentLeft = false;
			mazeIterator.next();
		}
		if(mazeIterator.hasNext()) {
			this.wentRight = true;
			return mazeIterator.next();
		}
		else {
			this.wentRight = false;
		}
		return null;
	}
	
	public MazeDisplay prevDisplay() {
		if(this.wentRight) {
			this.wentRight = false;
			mazeIterator.previous();
		}
		if(mazeIterator.hasPrevious()) {
			this.wentLeft = true;
			return mazeIterator.previous();
		}
		else {
			this.wentLeft = false;
		}
		return null;
	}
	
	public void resetCursor() {
		this.mazeIterator = this.loadedMazeDisplays.listIterator();
	}
}
