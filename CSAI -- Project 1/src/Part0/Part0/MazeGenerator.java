package Part0;

import java.util.function.Function;

public class MazeGenerator {
	public static void createMazes(int n, Function<Integer,String> nameFunc, String folderLocation, int sizeX, int sizeY) {
		for(int i = 0; i < n; i++) {
			System.out.println("Created the file '" + nameFunc.apply(i) + Maze.mazeExtension +  "' in the directory '"+folderLocation+"' with size of " + sizeX + " by " + sizeY);
		}
	}
}
