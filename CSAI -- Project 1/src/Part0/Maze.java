package Part0;

public class Maze implements Serializable{
	public int size[] = new int[2];
	public Object renderedView;
	public Cell[][] cells;
	
	public getU
	
	public Maze(int sizeX, int sizeY) {
		size = new int[] {sizeX, sizeY};
		initializeBlankMaze();
		renderCells();
	}
	
	public void renderCells() {
		renderedView = new Object();
	}
	
	private void initializeBlankMaze() {
		for(int i = 0; i < size[0]; i++) {
			for(int j = 0; j < size[1]; j++) {
				cells[i][j] = new Cell(this);
			}
		}
	}
	
	public static Maze loadMaze(String filepath) {
		return new Maze(0,0);
		//Function Stub
	}
	
	public void saveMaze() {
		return; //Function Stubs
	}
	
	public Cell getCellAtCoordinates(int[] coordinates) {
		return new Cell(this); //Function Stub
	}
}
