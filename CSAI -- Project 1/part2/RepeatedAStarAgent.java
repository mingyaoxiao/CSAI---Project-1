package part2;

import java.util.List;

import Part0.Maze;

public class RepeatedAStarAgent {
	Game game;
	
	public RepeatedAStarAgent(Game game) {
		this.game = game;
	}
	
	public List<String> ComputePath(L_Cell curr){
		return null;
	}
	
	public void Execute(List<String> Path) {
		
	}
	
	public GameVisualization Run() {
		L_Grid agent = new L_Grid(this.game.trueMaze, this);
		agent.aStar();
		return null;
	}

	public void addToVisualization(L_Cell curr, List<int[]> newBlockedCells) {
		// TODO Auto-generated method stub
		List<String> path = this.ComputePath(curr);
		Execute(path);
	}
}
