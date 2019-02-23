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
				if(cdEqual(currentPos,maze.agentPos)) {
					renderString.append("A");
					continue;
				}
				else if(cdEqual(currentPos,maze.goalPos)) {
					renderString.append("G");
					continue;
				}
				else if(currentCell.status == Start) {
					renderString.append("S");
				}
				else if(currentCell.status == OnPath) {
					renderString.append("O");
				}
				else if (currentCell.status == Blocked) renderString.append("\u25A0");
				else if (currentCell.status == Unblocked) renderString.append("\u25A1");
			}
			renderString.append("\n");
		}
		return renderString;
	}
}
