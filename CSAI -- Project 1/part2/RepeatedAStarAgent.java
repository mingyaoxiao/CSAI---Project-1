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
	int[] agentPos;
	int pathLength = 0;
	int iterations = 0;
	boolean completed = true;
	
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
	
	public void Run(char version, char tiebreaker) {
			L_Grid agent = new L_Grid(version, tiebreaker, this.game.trueMaze, this);
			this.agentPos = new int[]{this.game.trueMaze.agentPos[0],this.game.trueMaze.agentPos[1]};
			history.add("\n Initial Configuration: " +"\n" + this.game.trueMaze.getRender());
			agent.aStar(version);
			int numberOfExpandedCells = agent.numberOfExpandedCells;
			history.add("\n Number of nodes expanded: "+this.numberOfExpandedCells +"\n");
		/*else if (mode == AlgoMode.Adaptive){
			return null;
		}
		else if (mode == AlgoMode.RepeatedBackward) {
			BackwardGrid agent = new BackwardGrid(this.game.trueMaze, this);
			this.agentPos = new int[]{this.game.trueMaze.agentPos[0],this.game.trueMaze.agentPos[1]};
			agent.aStar();
			int numberOfExpandedCells = agent.numberOfExpandedCells;
			history.add("\n Number of nodes expanded: "+this.numberOfExpandedCells +"\n");
			return history;
		}*/
	}
	
	public void addToVisualization(L_Cell agentStart, L_Cell agentEnd, List<int[]> newBlockedCells, int numberExpanded) {
		iterations++;
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
		
		// 0. Move the agent 
		this.game.awareMaze.getCellAtCoordinates(agentStart.xCoor, agentStart.yCoor).status = Unblocked;
		this.game.awareMaze.getCellAtCoordinates(agentEnd.xCoor, agentEnd.yCoor).status = Agent;
		
		// 1. Reset cellstatus from agentvisited to unblocked.
		Iterator<L_Cell> it = prevPath.iterator();
		while(it.hasNext()) {
			L_Cell cur = it.next();
			game.awareMaze.getCellAtCoordinates(cur.xCoor, cur.yCoor).status = Unblocked;
		}
		
		// 2. Set the appropriate cells from unblocked to agentvisited.
		prevPath.clear();
		L_Cell ptr = agentStart;
		int counter = 0; 
		while(!prevPath.contains(agentEnd)) {
			L_Cell cur = ptr;
			Cell visitedCell = null;
			try {
				visitedCell = game.awareMaze.getCellAtCoordinates(cur.xCoor, cur.yCoor);
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			
			visitedCell.status = OnPath;
			game.trueMaze.getCellAtCoordinates(cur.xCoor, cur.yCoor).status = OnPath;
			prevPath.add(cur);
			counter++;
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
		history.add("\n Iteration #" + iterations + "\n\n" + game.awareMaze.getRender()+"\n");
		//history.append(counter);
		
		this.pathLength += counter;
	}
}
