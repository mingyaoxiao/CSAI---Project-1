
package Part0;
import java.io.*;

public class Maze implements Serializable{
	private static final long serialVersionUID = -2585827010147174704L;
	public int size[] = new int[2];
	public Object renderedView;
	public Cell[][] cells;
	public static final String mazeExtension = "maze";
	
	public Cell getCellAtCoordinates(int x, int y) {
		return null;
	}
	
	public Maze(int sizeX, int sizeY) {
		size = new int[] {sizeX, sizeY};
		initializeBlankMaze();
		renderCells();
	}
	
	public void renderCells() {
		renderedView = MazeRenderer.renderMaze(this);
	}
	
	private void initializeBlankMaze() {
		for(int i = 0; i < size[0]; i++) {
			for(int j = 0; j < size[1]; j++) {
				cells[i][j] = new Cell(this);
			}
		}
	}
	
	public static Maze loadMaze(String filePath) {
		ObjectInputStream mazeReader = null;
		try {
			mazeReader = new ObjectInputStream(new FileInputStream(filePath));
			Maze readMaze = (Maze) mazeReader.readObject();
			mazeReader.close();
			return readMaze;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				mazeReader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}
	}
	
	public void saveMaze(String filePath) {
		ObjectOutputStream mazeWriter = null;
		try {
			mazeWriter = new ObjectOutputStream(new FileOutputStream(filePath));
			mazeWriter.writeObject(this);
			mazeWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				mazeWriter.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public Cell getCellAtCoordinates(int[] coordinates) {
		int x = coordinates[0];
		int y = coordinates[1];
		if(x == -1 ||y  == -1) return null;
		return cells[x][y];
	}
}
