
package Part0;
import java.io.*;

public class Maze implements Serializable{
	public int size[] = new int[2];
	public Object renderedView;
	public Cell[][] cells;
	
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
	
	public void saveMaze() {
		return; //Function Stubs
	}
	
	public Cell getCellAtCoordinates(int[] coordinates) {
		return new Cell(this); //Function Stub
	}
}
