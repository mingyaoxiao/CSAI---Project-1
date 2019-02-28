package Part0;
import static Part0.CellStatus.*;

public class MazeRenderer {
	
	public static boolean cdEqual(int[] c1, int[] c2) {
		return (c1[0] == c2[0] && c1[1] == c2[1]);
	}
	
	public static Object renderMaze(Maze maze) {
		int sizeX = maze.size[0];
		int sizeY = maze.size[1];
		StringBuilder renderString = new StringBuilder();
		//System.out.println(maze.goalPos);
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {
				int[] currentPos = new int[] {i,j};
				Cell currentCell = maze.getCellAtCoordinates(currentPos);
				if(currentCell.status==Agent) {
					//renderString.append("\u2573");
					renderString.append("A");
					continue;
				}
				else if(currentCell.status == Goal) {
					renderString.append("G");
					continue;
				}
				else if(currentCell.status == Start) {
					renderString.append("B");
				}
				else if(currentCell.onPath) {
					renderString.append("\u2573");
				}
				else if (currentCell.status == Blocked) {
					if(currentCell.undetected) {
						renderString.append("\u2592");
						//System.out.println("really undetected");
					}
					else {
						renderString.append("\u2593"); //25A0
						//System.out.println("really detected");
					}
				}
				else if (currentCell.status == Unblocked) renderString.append("\u2591"); //25A1
			}
			renderString.append("\r\n");
		}
		return renderString;
	}
}
