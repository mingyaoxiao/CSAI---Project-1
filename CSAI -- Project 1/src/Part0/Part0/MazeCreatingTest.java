package Part0;

import static Part0.MoveDirection.*;

public class MazeCreatingTest {
	public static void main(String[] args) {
		generateMaze(10,10);
		Maze m = Maze.loadMaze(FileHelper.baseUrl + "GeneratedMazes/testMaze4.maze");
		m.toString();
		Cell c = m.getCellAtCoordinates(2,3);
		c.setAsBlocked();
		c = c.moveToNeighbor(Up);
		c.setAsBlocked();
		c = c.moveToNeighbor(Left);
		c.setAsBlocked();
		c = c.moveToNeighbor(Left);
		c.setAsBlocked();
		c = c.moveToNeighbor(Left);
		c.setAsBlocked();
		String renderString = m.getRender().toString();
		System.out.println(renderString);
		m.saveMaze(FileHelper.baseUrl + "GeneratedMazes/testMaze4");
	}
	
	private static void generateMaze(int sizeX, int sizeY) {
		Maze m = Maze.createNewMaze(sizeY, sizeY);
		m.saveMaze(FileHelper.baseUrl + "GeneratedMazes/testMaze4");
	}
}
