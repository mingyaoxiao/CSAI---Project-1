package Part0;
import static Part0.CellStatus.*;
public class Cell {
	public CellStatus status = Unblocked;
	public int[] coordinates = {0,0};
	private Maze parentMaze;
	public Cell(Maze parentMaze) {
		this.parentMaze = parentMaze;
	}
	
	public void setAsBlocked() {
		this.status = Blocked;
	}
	
	public void setAsUnblocked() {
		this.status = Unblocked;
	}
	
	private Cell moveToNeighbor(MoveDirection direction) {
		
		switch(direction) {
			case Up: 
				break;
			case Right:
				break;
			case Down:
				break;
			case Left:
				break;
			
		}
		
		return new Cell(parentMaze); // Function Stub
	}
}
