package part2;

import Part0.*;

import static Part0.CellStatus.Blocked;
import static Part0.CellStatus.Unblocked;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BackwardGrid 
{	
	public L_Cell[][] grid;
	public PriorityQueue<L_Cell> open;
	public PriorityQueue<L_Cell> closed;
	public RepeatedAStarAgent agent;
	public int numberOfExpandedCells;
	public List<int[]> newBlockedCells;
	int x_size;
	int y_size;
	Maze m;
	BackwardGrid(Maze m, RepeatedAStarAgent agent)
	{		
		grid = new L_Cell[101][101];
		this.m = m;
		this.agent = agent;
		x_size = m.size[0];
		y_size = m.size[1];
		grid = new L_Cell[x_size][y_size];
		for(int i = 0; i < x_size; ++i) 
		{			
			for(int j = 0; j < y_size; ++j) 
			{				
				Cell c = m.getCellAtCoordinates(new int[] {i,j});
				grid[i][j] = new L_Cell();
				//grid[i][j].isBlocked = ((char) ch == '1') ? true : false;
				if(c.status == Unblocked) {
					grid[i][j].isBlocked = false;
				}
				else if(c.status == Blocked){
					grid[i][j].isBlocked = true;
				}
				grid[i][j].xCoor = i;
				grid[i][j].yCoor = j;
				grid[i][j].gVal = Double.POSITIVE_INFINITY;
				grid[i][j].hVal = (x_size - i) + (y_size - j);
				grid[i][j].actionRemoved = false;
			}
		}
		open = new PriorityQueue<L_Cell>(10, L_Cell.larger);
		closed = new PriorityQueue<L_Cell>(10, L_Cell.larger);
		this.newBlockedCells = new ArrayList<int[]>();
	}
	
	BackwardGrid(String s) throws IOException 
	{		
		grid = new L_Cell[101][101];
		FileReader fr = new FileReader(s);
		for(int i = 0; i < 101; ++i) 
		{			
			for(int j = 0; j < 101; ++j) 
			{				
				int ch = fr.read();
				grid[i][j] = new L_Cell();
				grid[i][j].isBlocked = ((char) ch == '1') ? true : false;
				grid[i][j].xCoor = i;
				grid[i][j].yCoor = j;
				grid[i][j].gVal = Double.POSITIVE_INFINITY;
				grid[i][j].hVal = i + j;
				grid[i][j].actionRemoved = false;
			}
			fr.read();
		}
		fr.close();
		open = new PriorityQueue<L_Cell>(10, L_Cell.larger);
		closed = new PriorityQueue<L_Cell>(10, L_Cell.larger);
	}
	
	public void printGrid() 
	{		
		for(int i = 0; i < 101; ++i) 
		{			
			for(int j = 0; j < 101; ++j) 
			{				
				System.out.print((grid[i][j].isBlocked) ? "1" : "0");
			}
			System.out.println();
		}
	}

	public void action(int nextX, int nextY, L_Cell prevL_Cell)
	{
		/*if(grid[nextX][nextY].gVal > prevL_Cell.gVal + 1)
		{					
			grid[nextX][nextY].gVal = prevL_Cell.gVal + 1;
			grid[nextX][nextY].next = grid[prevL_Cell.xCoor][prevL_Cell.yCoor];
			prevL_Cell.prev = grid[nextX][nextY];
			if(open.contains(grid[nextX][nextY])) 
			{						
				open.remove(grid[nextX][nextY]);
			}					
			open.add(grid[nextX][nextY]);
		}*/
		if(grid[nextX][nextY].gVal > prevL_Cell.gVal + 1)
		{					
			grid[nextX][nextY].gVal = prevL_Cell.gVal + 1;
			grid[nextX][nextY].next = grid[prevL_Cell.xCoor][prevL_Cell.yCoor];
			if(open.contains(grid[nextX][nextY])) 
			{						
				open.remove(grid[nextX][nextY]);
			}					
			open.add(grid[nextX][nextY]);
		}
	}
	
	public boolean computePath(int a, int b) 
	{		
		this.numberOfExpandedCells = 0;
		while(!closed.contains(grid[a][b])) 
		{			
			if(open.peek() == null)
				return false;
			L_Cell state = open.poll();
			closed.add(state);
			this.numberOfExpandedCells++;
			//up
			if(state.xCoor > 0)
			{
				if(!closed.contains(grid[state.xCoor - 1][state.yCoor]) && !grid[state.xCoor - 1][state.yCoor].actionRemoved)
				{
					grid[state.xCoor-1][state.yCoor].gVal = Double.POSITIVE_INFINITY;
					action(state.xCoor - 1, state.yCoor, state);
				}
			}
			//right
			if(state.yCoor + 1 < x_size)
			{
				if(!closed.contains(grid[state.xCoor][state.yCoor + 1]) && !grid[state.xCoor][state.yCoor + 1].actionRemoved)
				{
					grid[state.xCoor][state.yCoor + 1].gVal = Double.POSITIVE_INFINITY;
					action(state.xCoor, state.yCoor + 1, state);
				}
			}
			//down
			if(state.xCoor + 1 < y_size)
			{
				if(!closed.contains(grid[state.xCoor + 1][state.yCoor]) && !grid[state.xCoor + 1][state.yCoor].actionRemoved)
				{
					grid[state.xCoor + 1][state.yCoor].gVal = Double.POSITIVE_INFINITY;
					action(state.xCoor + 1, state.yCoor, state);
				}
			}
			//left
			if(state.yCoor > 0)
			{
				if(!closed.contains(grid[state.xCoor][state.yCoor - 1]) && !grid[state.xCoor][state.yCoor - 1].actionRemoved)
				{
					grid[state.xCoor][state.yCoor - 1].gVal = Double.POSITIVE_INFINITY;
					action(state.xCoor, state.yCoor - 1, state);
				}
			}
		}
		return true;
	}
	
	public void aStar()
	{
			L_Cell curr = grid[0][0];
			//preliminary check
			if(grid[0][1].isBlocked)
				grid[0][1].actionRemoved = true;
			if(grid[1][0].isBlocked)
				grid[1][0].actionRemoved = true;
			while(curr.xCoor != x_size -1 || curr.yCoor != y_size -1)
			{
				grid[x_size -1][y_size -1].gVal = 0;
				grid[curr.xCoor][curr.yCoor].gVal = Double.POSITIVE_INFINITY;
				open.clear();
				closed.clear();
				open.add(grid[x_size - 1][x_size - 1]);
				if(curr == grid[13][3]) {
					this.toString();
				}
				boolean success = computePath(curr.xCoor, curr.yCoor);
				if(!success)
				{
					System.out.println("cannot reach target");
					return;
				}
				
				L_Cell advance = grid[curr.xCoor][curr.yCoor];
				while(!(advance.xCoor == x_size - 1 && advance.yCoor == y_size - 1))
				{
					L_Cell further = advance.next;
					further.prev = advance;
					advance = further;
				}

				L_Cell ptr = curr;
				L_Cell prev = curr;
				L_Cell agentStart = curr;
				
				System.out.print(",("+ptr.xCoor + "," + ptr.yCoor+")");
					while(ptr != null && (ptr.xCoor != x_size -1 || ptr.yCoor != x_size-1) && !ptr.isBlocked)
					{
						if(ptr.prev == null) 
						{ptr.toString();}
						//update action costs
						if(ptr.xCoor > 0 && grid[ptr.xCoor - 1][ptr.yCoor].isBlocked)
							{
								grid[ptr.xCoor - 1][ptr.yCoor].actionRemoved = true;
								this.newBlockedCells.add(new int[] {ptr.xCoor - 1, ptr.yCoor} );
							}
						if(ptr.yCoor < y_size -1 && grid[ptr.xCoor][ptr.yCoor + 1].isBlocked)
							{
								grid[ptr.xCoor][ptr.yCoor + 1].actionRemoved = true;
								this.newBlockedCells.add(new int[] {ptr.xCoor, ptr.yCoor + 1} );
							}
						if(ptr.xCoor < x_size -1 && grid[ptr.xCoor + 1][ptr.yCoor].isBlocked)
							{
								grid[ptr.xCoor + 1][ptr.yCoor].actionRemoved = true;
								this.newBlockedCells.add(new int[] {ptr.xCoor + 1, ptr.yCoor} );
							}
						if(ptr.yCoor > 0 && grid[ptr.xCoor][ptr.yCoor - 1].isBlocked)
							{
								grid[ptr.xCoor][ptr.yCoor - 1].actionRemoved = true;
								this.newBlockedCells.add(new int[] {ptr.xCoor, ptr.yCoor - 1} );
							}
						ptr = ptr.next;
						if(ptr == grid[x_size-1][y_size-1]) {
							this.toString();
							curr = grid[x_size-1][y_size-1];
						}
						System.out.print(",("+ptr.xCoor + "," + ptr.yCoor+")");
					}
						System.out.println("\n");
						L_Cell agentEnd = null;
						if(ptr.isBlocked)
							agentEnd = ptr.prev;
						if(curr == grid[x_size-1][y_size-1]){
							agentEnd = grid[x_size-1][y_size-1];
						}
						try {
						agent.addToVisualization(agentStart, agentEnd, newBlockedCells, this.numberOfExpandedCells);
						}
						catch (Exception e) {
							e.printStackTrace();
						}
						this.newBlockedCells.clear();
						if(ptr.isBlocked)
							curr = ptr.prev;
						System.out.println("D");
			}
			System.out.println("reached the target");
	}
}