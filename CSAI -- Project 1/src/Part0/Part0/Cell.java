package Part0;
import static Part0.CellStatus.*;
public class Cell {
	public CellStatus status = Unblocked;
	public int[] coordinates = {0,0};
	private Maze parentMaze;
	public Cell(Maze parentMaze) {
		this.parentMaze = parentMaze;
	}
	
	public int[] getCoordinates() {
		return coordinates;
	}
	
	public void setAsBlocked() {
		this.status = Blocked;
	}
	
	public void setAsUnblocked() {
		this.status = Unblocked;
	}
	
	private Cell moveToNeighbor(MoveDirection direction) {
		
		int[] newCoordinates = new int[2];
		int xMove = 0;
		int yMove = 0;
		
		switch(direction) {
			case Up:
				yMove = -1;
				break;
			case Right:
				xMove = 1;
				break;
			case Down:
				yMove = 1;
				break;
			case Left:
				xMove = -1;
				break;
		}
		
		newCoordinates[0] += xMove; newCoordinates[1] += yMove;
		return parentMaze.getCellAtCoordinates(newCoordinates);
	}
}
