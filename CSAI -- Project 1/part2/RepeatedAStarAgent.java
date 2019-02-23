package part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static Part0.CellStatus.*;

import Part0.Cell;
import Part0.Maze;

public class RepeatedAStarAgent {
	Game game;
	List<L_Cell> prevPath;
	int counter;
	StringBuilder historyFrame;
	List<String> history;
	int numberOfExpandedCells;
	boolean incompletable = false;
	public RepeatedAStarAgent(Game game) {
		this.historyFrame = new StringBuilder();
		this.history = new ArrayList<String>();
		this.game = game;
		prevPath = new ArrayList<L_Cell>();
	}
	
	public List<String> ComputePath(L_Cell agentStart, L_Cell agentEnd){
		return null;
	}
	
	public void Execute(List<String> Path) {
		
	}
	
	public List<String> Run() {
		L_Grid agent = new L_Grid(this.game.trueMaze, this);
		agent.aStar();
		int numberOfExpandedCells = agent.numberOfExpandedCells;
		history.add("\n Number of nodes expanded: "+this.numberOfExpandedCells +"\n");
		return history;
	}
	
	public void addToVisualization(L_Cell agentStart, L_Cell agentEnd, List<int[]> newBlockedCells, int numberExpanded) {
		this.numberOfExpandedCells += numberExpanded;
		if(newBlockedCells == null) {
			this.incompletable = true;
			return;
		}
		System.out.println("E");
		if (counter > 100000) {
			System.out.println("hmm");
			return;
		}
		counter++;
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
		prevPath.clear();
		L_Cell ptr = agentStart;
		while(!prevPath.contains(agentEnd)) {
			L_Cell cur = ptr;
			Cell visitedCell = game.awareMaze.getCellAtCoordinates(cur.xCoor, cur.yCoor);
			visitedCell.status = OnPath;
			prevPath.add(cur);
			ptr = ptr.next;
		}
		
		// 3. Set the appropriate cells from unblocked to blocked.
		Cell blockedCellPtr = null;
		try {
			for(int index = 0; index < newBlockedCells.size(); index++) {
				blockedCellPtr = game.awareMaze.getCellAtCoordinates(newBlockedCells.get(index));
				Cell visitedCell = game.awareMaze.getCellAtCoordinates(blockedCellPtr.coordinates[0], blockedCellPtr.coordinates[1]);
				visitedCell.status = Blocked;
				
			}
		}
		catch (Exception e) {
			this.toString();
		}
		
		
		// 4. Render the aware maze and add it to the visualization history. 
		history.add("\n Iteration #" + counter + "\n\n" + game.awareMaze.getRender()+"\n");
		//history.append(counter);
	}
}
