/**
 * 
 */
package Part0;
import static Part0.CellStatus.*;
/**
 * @author Will
 *
 */
public class DFS {

	/**
	 * @param args
	 */
	Maze maze;
	
	public DFS (Maze maze){
		this.maze = maze;
	}
	
	public Maze dfs(Maze maze){
		Maze maze2 = maze;
		Cell cell = maze2.getCellAtCoordinates(0, 0);
		UnvisitedNodesTracker track = new UnvisitedNodesTracker(maze2.size[0], maze2.size[1]);
		
		cell.setAsUnblocked();
		track.markNodeAsVisited(cell.coordinates[0], cell.coordinates[1]);
		int cellCount = 1;
		//now to move to the navigation through the maze
		
		while(track.getUnvisitedNode()[0]!= -1 && track.getUnvisitedNode()[1] != -1){
			int[] t = track.getUnvisitedNode();
			cell = maze2.getCellAtCoordinates(t[0], t[1]);
			double rand = Math.random()*10;
			if(rand<=7){
				cell.setAsUnblocked();
			}
			else{
				cell.setAsBlocked();
			}
			track.markNodeAsVisited(cell.coordinates[0], cell.coordinates[1]);
		}
		
		maze2.getCellAtCoordinates(0, 0).status = Start;
		maze2.getCellAtCoordinates(maze2.size[0]-1, maze2.size[1]-1).status = Goal;
		
//		while(maze2.getCellAtCoordinates(track.getUnvisitedNode()[0], track.getUnvisitedNode()[1])!=null){
//			while(cell.moveToNeighbor(MoveDirection.Down)!=null){
//				cell= cell.moveToNeighbor(MoveDirection.Down);
//				cellCount++;
//				double rand = Math.random()*10;
//				if(rand<=7){
//					cell.setAsUnblocked();
//				}
//				else{
//					cell.setAsBlocked();
//				}
//				track.markNodeAsVisited(cell.coordinates[0], cell.coordinates[1]);
//			}
//			int[] t = track.getUnvisitedNode();
//			cell=maze2.getCellAtCoordinates(t[0], t[1]);
//		}
		return maze2;
		
	}

}
