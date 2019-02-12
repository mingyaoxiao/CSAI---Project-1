package Part0;
public class UnvisitedNodesTracker {
	boolean[][] MazeModel;
	int sizeX;
	int sizeY;
	public UnvisitedNodesTracker(int sizeX, int sizeY) {
		MazeModel = new boolean[sizeX][sizeY];
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	public void markNodeAsVisited(int x, int y) {
		MazeModel[x][y] = true;
	}
	public int[] getUnvisitedNode() {
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeX; j++) {
				if(MazeModel[i][j]) {
					return new int[]{i,j};
				}
			}
		}
		return new int[]{-1,-1};
	}
}
