package Part0;

import static Part0.MoveDirection.*;

public class MazeCreatingTest {
	public static void main(String[] args) {
		//generateMaze(5,5);
		Maze m = Maze.loadMaze(FileHelper.baseUrl + "testMaze3");
		m.toString();
		Cell c = m.getCellAtCoordinates(2,3);
		c.setAsBlocked();
		c = c.moveToNeighbor(Up);
		c.setAsBlocked();
		c = c.moveToNeighbor(Left);
		c.setAsBlocked();
		c = c.moveToNeighbor(Left);
		c.setAsBlocked();
		c = c.moveToNeighbor(Down);
		c.setAsBlocked();
		c = c.moveToNeighbor(Down);
		c.setAsBlocked();
		c = c.moveToNeighbor(Down);
		c.setAsBlocked();
		c = c.moveToNeighbor(Down);
		String renderString = m.getRender().toString();
		if(c == null) renderString += "\n Boundary reached";
		System.out.println(renderString);
	}
	
	private static void generateMaze(int sizeX, int sizeY) {
		Maze m = Maze.createNewMaze(sizeY, sizeY);
		m.saveMaze(FileHelper.baseUrl + "testMaze3");
	}
}
