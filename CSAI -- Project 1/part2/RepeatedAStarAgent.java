package part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static Part0.CellStatus.*;

import Part0.Cell;
import Part0.Maze;
import Part0.Cell;

public class RepeatedAStarAgent {
	Game game;
	List<L_Cell> prevPath;
	List<L_Cell> clearPath;
	List<L_Cell> finalPath;
	int counter;
	StringBuilder historyFrame;
	List<String> history;
	int numberOfExpandedCells;
	boolean incompletable = false;
	int[] agentPos;
	int pathLength = 0;
	int iterations = 0;
	boolean completed = true;
	L_Cell b;
	
	
	public RepeatedAStarAgent(Game game) {
		this.historyFrame = new StringBuilder();
		this.history = new ArrayList<String>();
		this.game = game;
		prevPath = new ArrayList<L_Cell>();
		clearPath = new ArrayList<L_Cell>();
		finalPath = new ArrayList<L_Cell>();
	}
	
	public List<String> ComputePath(L_Cell agentStart, L_Cell agentEnd){
		return null;
	}
	
	public void Execute(List<String> Path) {
		
	}
	
	public void Run(char version, char tiebreaker) {
			L_Grid agent = new L_Grid(version, tiebreaker, this.game.trueMaze, this);
			
			this.agentPos = new int[]{this.game.trueMaze.agentPos[0],this.game.trueMaze.agentPos[1]};
			//history.add(" Initial Configuration: " +"" + this.game.trueMaze.getRender());
			agent.aStar(version);
			game.trueMaze.getCellAtCoordinates(this.agentPos).status = Unblocked;
			int numberOfExpandedCells = agent.numberOfExpandedCells;
			finalPath.forEach((c) -> game.trueMaze.getCellAtCoordinates(c.xCoor, c.yCoor).onPath = true);
			history.add("Final Path and Configuration");
			game.trueMaze.getCellAtCoordinates(agent.startPosX,agent.startPosY).status = Agent;
			history.add(game.trueMaze.getRender().toString());
			history.add(" Number of nodes expanded: "+this.numberOfExpandedCells +"");
			//history.add(" Number of nodes expanded: "+this.numberOfExpandedCells +"");
		/*else if (mode == AlgoMode.Adaptive){
			return null;
		}
		else if (mode == AlgoMode.RepeatedBackward) {
			BackwardGrid agent = new BackwardGrid(this.game.trueMaze, this);
			this.agentPos = new int[]{this.game.trueMaze.agentPos[0],this.game.trueMaze.agentPos[1]};
			agent.aStar();
			int numberOfExpandedCells = agent.numberOfExpandedCells;
			history.add(" Number of nodes expanded: "+this.numberOfExpandedCells +"");
			return history;
		}*/
	}
	
	public void addToVisualization(L_Cell agentStart, L_Cell agentEnd, List<int[]> newBlockedCells, L_Cell last, int numberExpanded, L_Grid grid) {
		if(b==null) {
			b = last;
		}
		else {
			game.trueMaze.getCellAtCoordinates(b.xCoor,b.yCoor).status = Blocked;
			game.trueMaze.getCellAtCoordinates(b.xCoor,b.yCoor).undetected = false;
			b = last;
		}
		game.trueMaze.getCellAtCoordinates(this.agentPos).status = Unblocked;
		this.agentPos = new int[]{agentStart.xCoor,agentStart.yCoor};
		game.trueMaze.getCellAtCoordinates(this.agentPos).status = Agent;
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
		
		
		// 1. Reset cellstatus from agentvisited to unblocked.
		clearPath.forEach((c) -> game.trueMaze.getCellAtCoordinates(c.xCoor, c.yCoor).onPath = false);
		clearPath.clear();
		
		Iterator<L_Cell> it = prevPath.iterator();
		while(it.hasNext()) {
			L_Cell cur = it.next();
			//game.trueMaze.getCellAtCoordinates(cur.xCoor, cur.yCoor).status = Unblocked;
			game.trueMaze.getCellAtCoordinates(cur.xCoor, cur.yCoor).onPath = false;
		}
		
		// 2. Set the appropriate cells from unblocked to agentvisited.
		prevPath.clear();
		L_Cell ptr = agentStart;
		int counter = 0; 
		while(ptr.xCoor != grid.goalPosX || ptr.yCoor != grid.goalPosY) {
			L_Cell cur = ptr;
			Cell visitedCell = null;
			try {
				visitedCell = game.trueMaze.getCellAtCoordinates(cur.xCoor, cur.yCoor);
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			
			//visitedCell.status = OnPath;
			//game.trueMaze.getCellAtCoordinates(cur.xCoor, cur.yCoor).status = OnPath;
			visitedCell.onPath = true;
			
			if(!prevPath.contains(agentEnd)) {
				finalPath.add(cur);
				prevPath.add(cur);
			}
			else {
				clearPath.add(cur);
				//clearPath.add(cur);
			}
			counter++;
			ptr = ptr.next;
		}
		
		// 3. Set the appropriate cells from unblocked to blocked.
		Cell blockedCellPtr = null;
		try {
			for(int index = 0; index < newBlockedCells.size(); index++) {
				blockedCellPtr = game.trueMaze.getCellAtCoordinates(newBlockedCells.get(index));
				Cell visitedCell = game.trueMaze.getCellAtCoordinates(blockedCellPtr.coordinates[0], blockedCellPtr.coordinates[1]);
				//visitedCell.status = Blocked;
				visitedCell.undetected = false;
			}
		}
		catch (Exception e) {
			this.toString();
		}
		
		//this.game.trueMaze.getCellAtCoordinates(agentStart.xCoor, agentStart.yCoor).status = Unblocked;
		//this.game.trueMaze.getCellAtCoordinates(agentEnd.xCoor, agentEnd.yCoor).status = Agent;
		// 4. Render the aware maze and add it to the visualization history.
		if(iterations == 426) {
			this.toString();
		}
		if(game.trueMaze.getCellAtCoordinates(last.xCoor,last.yCoor).status != Goal) {
			game.trueMaze.getCellAtCoordinates(last.xCoor,last.yCoor).status = Start;
		}
		history.add(" Iteration #" + iterations + "");
		history.add(game.trueMaze.getRender()+"");
		System.out.println(game.trueMaze.getRender());
		//history.append(counter);
		
		this.pathLength += counter;
	}
}
