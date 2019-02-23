package Part0;

import java.util.function.Function;

public class MazeGenerator {
	public static void createMazes(int n, Function<Integer,String> nameFunc, String folderLocation, int sizeX, int sizeY) {
		for(int i = 0; i < n; i++) {
			Maze maze = Maze.createNewMaze(sizeX, sizeY);
			
			DFS builder = new DFS(maze);
			maze=builder.dfs(maze);
			maze.saveMaze(folderLocation+"maze"+i+Maze.mazeExtension);
			System.out.println("Created the file '" + i + Maze.mazeExtension +  "' in the directory '"+folderLocation+"' with size of " + sizeX + " by " + sizeY);
		}
	}
}
