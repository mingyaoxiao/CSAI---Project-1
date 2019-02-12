package Part0;

public class Cell {
	public CellStatus status = CellStatus.Unblocked;
	public int[] coordinates = {0,0};
	private Maze parentMaze;
	public Cell(Maze parentMaze) {
		this.parentMaze = parentMaze;
	}
	
	public void setAsBlocked() {
		this.status = CellStatus.Blocked;
	}
	
	public void setAsUnblocked() {
		this.status = CellStatus.Unblocked;
	}
	
	private Cell cellMove(MoveDirection direction) {
		return new Cell(parentMaze); // Function Stub
	}
}
