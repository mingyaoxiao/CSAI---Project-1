package Part0;
import static Part0.CellStatus.*;

import java.io.Serializable;
public class Cell implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4687250664459748573L;
	public CellStatus status = Unblocked;
	public int[] coordinates = {0,0};
	private Maze parentMaze;
	public Cell(Maze parentMaze, int xCoordinate, int yCoordinate) {
		this.parentMaze = parentMaze;
		coordinates[0] = xCoordinate;
		coordinates[1] = yCoordinate;
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
	
	public Cell peek(MoveDirection direction) {
		return moveToNeighbor(direction);
	}
	
	public Cell moveToNeighbor(MoveDirection direction) {
		
		int[] newCoordinates = new int[] {this.coordinates[0], this.coordinates[1]};
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
		
		newCoordinates[0] += yMove; newCoordinates[1] += xMove;
		return parentMaze.getCellAtCoordinates(newCoordinates);
	}
}
