package part2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import Part0.FileHelper;
import Part0.Maze;

public class AlgoStatisticsGenerator {
	public static void main(String[] args) throws IOException {
		String folderLocation = "demo";
		generateAlgos(folderLocation);
	}
	
	public static void generateAlgos(String folderLocation) throws IOException {
		Instant.now();
		// Create arrays to hold the information
		List<Integer> numberExpandedArray = new ArrayList<Integer>();
		List<Integer> pathLengthArray = new ArrayList<Integer>();
		List<Duration> runningTimeArray = new ArrayList<Duration>();
		List<Integer> algoType = new ArrayList<Integer>();
		List<String> completableMazes = new ArrayList<String>();
		
		// Get the mazes into an array
		File folder = new File(folderLocation);
		if(!folder.isDirectory()) return;
		for(int i = 0; i < 4; i++) {
		//int i = 3;
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
		    					switch(i%4) {
		    						case 0:
		    							agent.Run('f', 'l');
		    							break;
		    						case 1:
		    							agent.Run('f', 's');
		    							break;
		    						case 2:
		    							agent.Run('b', 'l');
		    							break;
		    						case 3:
		    							agent.Run('a', 'l');
		    							break;
		    						default:
		    							break;
		    					}
		    					/*if(!agent.incompletable) {
		    						mazeCounter++;
		    						
		    						//System.out.println(file.getName());
		    						completableMazes.add(file.getName());
		    						
		    					}
		    					else {
		    						if(mazeCounter>50)file.delete();
		    						file.delete();
		    					}*/
		    					numberExpandedArray.add(agent.numberOfExpandedCells);
		    					pathLengthArray.add(agent.pathLength);
		    				Instant then = Instant.now();
		    				Duration between = Duration.between(now, then);
		    				runningTimeArray.add(between);
		    				algoType.add(i%4);
		    			}
		    		}
		        }
		    }
		}
		
		FileWriter fw = null;
	    BufferedWriter bw = null;
	    PrintWriter out = null;
			/*fw = new FileWriter("statistics.txt", true);
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);*/
			fw = new FileWriter("src/statistics.txt");
		//out.println("Number Expanded,Path Length,Penetrance,Running Time,Algorithm Type");
		for(int j = 0; j < numberExpandedArray.size(); j++) {
			System.out.println(algoType.get(j)+","+numberExpandedArray.get(j)+","+ pathLengthArray.get(j)+","+((double)pathLengthArray.get(j)/numberExpandedArray.get(j))+","+ runningTimeArray.get(j).toNanos());
			//out.println("f");
			//fw.write(algoType.get(i)+","+numberExpandedArray.get(i)+","+ pathLengthArray.get(i)+","+((double)pathLengthArray.get(i)/numberExpandedArray.get(i))+","+ runningTimeArray.get(i).toNanos()+"\n");
		}
	    
		// Loop throurecording the number of nodes expanded, the final path length, and the running time
			// Add fungh all 50 mazes, ctionality for run to return all of that information
				// number of nodes expanded is done
				// final path length needs to be gotten
					// this includes actually storing the full path length, displaying it in the history along with the true maze, then returning length
					// then one needs to calculate penetrance
				// running time needs to be calculated
		// Write it out as a csv
	}
}
