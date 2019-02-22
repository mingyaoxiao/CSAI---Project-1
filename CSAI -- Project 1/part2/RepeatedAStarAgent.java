package part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static Part0.CellStatus.*;

import Part0.Maze;

public class RepeatedAStarAgent {
	Game game;
	List<L_Cell> prevPath;
	public RepeatedAStarAgent(Game game) {
		this.game = game;
		prevPath = new ArrayList<L_Cell>();
	}
	
	public List<String> ComputePath(L_Cell agentStart, L_Cell agentEnd){
		return null;
	}
	
	public void Execute(List<String> Path) {
		
	}
	
	public GameVisualization Run() {
		L_Grid agent = new L_Grid(this.game.trueMaze, this);
		agent.aStar();
		int numberOfExpandedCells = agent.numberOfExpandedCells;
		return null;
	}
	
	public void addToVisualization(L_Cell agentStart, L_Cell agentEnd, List<int[]> newBlockedCells) {
		// TODO Auto-generated method stub
		/*List<String> path = this.ComputePath(agentStart, agentEnd);
		Execute(path);*/
		
		// 1. Reset cellstatus from agentvisited to unblocked.
		Iterator<L_Cell> it = prevPath.iterator();
		while(it.hasNext()) {
			L_Cell cur = it.next();
			game.awareMaze.getCellAtCoordinates(cur.xCoor, cur.yCoor).status = Unblocked;
		}
		// 2. Set the appropriate cells from unblocked to agentvisited.
		L_Cell ptr = agentStart;
		L_Cell prev = ptr;
		while(ptr != null) {
			L_Cell cur = ptr;
			game.awareMaze.getCellAtCoordinates(cur.xCoor, cur.yCoor).status = AgentVisited;
		}
		// 3. Set the appropriate cells from unblocked to blocked.
		// 4. Render the aware maze and add it to the visualization history. 
	}
}
