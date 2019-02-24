package part2;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import Part0.FileHelper;
import Part0.Maze;

public class AlgoStatisticsGenerator {
	public static void main(String[] args) {
		Instant.now();
		// Create arrays to hold the information
		List<Integer> numberExpandedArray = new ArrayList<Integer>();
		List<Integer> pathLengthArray = new ArrayList<Integer>();
		List<Duration> runningTimeArray = new ArrayList<Duration>();
		
		// Get the mazes into an array
		String folderLocation = "src/stat/";
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
	    				Maze m = Maze.loadMaze(file.getPath());
	    				Game g = new Game(m);
	    				RepeatedAStarAgent agent = new RepeatedAStarAgent(g);
	    				Instant now = Instant.now();
	    					agent.Run('b', 'l');
	    					numberExpandedArray.add(agent.numberOfExpandedCells);
	    					pathLengthArray.add(agent.pathLength);
	    				Instant then = Instant.now();
	    				Duration between = Duration.between(now, then);
	    				runningTimeArray.add(between);
	    			}
	    		}
	        }
	    }
		
	    for(int i = 0; i < numberExpandedArray.size(); i++) {
	    	System.out.println("---"+i+"---");
	    	System.out.println("Number Expanded: "+numberExpandedArray.get(i));
	    	System.out.println("Path Length: " + pathLengthArray.get(i));
	    	System.out.println("Penetrance: " + (double)pathLengthArray.get(i)/numberExpandedArray.get(i));
	    	System.out.println("Running Time: " + runningTimeArray.get(i).toNanos());
	    }
	    
		// Loop through all 50 mazes, recording the number of nodes expanded, the final path length, and the running time
			// Add functionality for run to return all of that information
				// number of nodes expanded is done
				// final path length needs to be gotten
					// this includes actually storing the full path length, displaying it in the history along with the true maze, then returning length
					// then one needs to calculate penetrance
				// running time needs to be calculated
		// Write it out as a csv
	}
}
