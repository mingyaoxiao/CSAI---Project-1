package Part0;
import static Part0.CellStatus.*;

public class MazeRenderer {
	public static Object renderMaze(Maze maze) {
		int sizeX = maze.size[0];
		int sizeY = maze.size[1];
		StringBuilder renderString = new StringBuilder();
		
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {
				Cell currentCell = maze.getCellAtCoordinates(new int[] {i,j});
				if (currentCell.status == Blocked) renderString.append(" \u25A0 ");
				else if (currentCell.status == Unblocked) renderString.append(" \u25A1 ");
			}
			renderString.append("\n");
		}
		
		
		return renderString;
	}
}
