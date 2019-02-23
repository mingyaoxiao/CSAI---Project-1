package part2;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static Part0.CellStatus.*;
import Part0.Cell;
import Part0.Maze;
public class L_Grid 
{	
	public L_Cell[][] L_Grid;
	public PriorityQueue<L_Cell> open;
	public PriorityQueue<L_Cell> closed;
	public RepeatedAStarAgent agent;
	public int numberOfExpandedCells;
	public List<int[]> newBlockedCells;
	int x_size;
	int y_size;
	Maze m;
	//src/maze0.maze.maze
	L_Grid(char ver, char tie, Maze m, RepeatedAStarAgent agent){
		this.m = m;
		this.agent = agent;
		x_size = m.size[0];
		y_size = m.size[1];
		L_Grid = new L_Cell[x_size][y_size];
		for(int i = 0; i < x_size; ++i) 
		{			
			for(int j = 0; j < y_size; ++j) 
			{				
				Cell c = m.getCellAtCoordinates(new int[] {i,j});
				L_Grid[i][j] = new L_Cell();
				//L_Grid[i][j].isBlocked = ((char) ch == '1') ? true : false;
				if(c.status == Unblocked) {
					L_Grid[i][j].isBlocked = false;
				}
				else if(c.status == Blocked){
					L_Grid[i][j].isBlocked = true;
				}
				L_Grid[i][j].xCoor = i;
				L_Grid[i][j].yCoor = j;
				L_Grid[i][j].gVal = Double.POSITIVE_INFINITY;
				switch(ver)
				{
					case 'b':
						L_Grid[i][j].hVal = i + j;
						break;
					case 'a':
					case 'f':
					default:
						L_Grid[i][j].hVal = (100 - i) + (100 - j);
						break;
				}
				L_Grid[i][j].actionRemoved = false;
			}
		}
		switch(tie)
		{
			case 's':
				open = new PriorityQueue<L_Cell>(10, L_Cell.smaller);
				closed = new PriorityQueue<L_Cell>(10, L_Cell.smaller);
				break;
			case 'l':
			default:
				open = new PriorityQueue<L_Cell>(10, L_Cell.larger);
				closed = new PriorityQueue<L_Cell>(10, L_Cell.larger);
				break;
		}
		newBlockedCells = new ArrayList<int[]>();
	}
	/*
	L_Grid(char ver, char tie, String s) throws IOException 
	{		
		grid = new Cell[101][101];
		FileReader fr = new FileReader(s);
		for(int i = 0; i < 101; ++i) 
		{			
			for(int j = 0; j < 101; ++j) 
			{				
				int ch = fr.read();
				grid[i][j] = new Cell();
				grid[i][j].isBlocked = ((char) ch == '1') ? true : false;
				grid[i][j].xCoor = i;
				grid[i][j].yCoor = j;
				grid[i][j].gVal = Double.POSITIVE_INFINITY;
				switch(ver)
				{
					case 'b':
						grid[i][j].hVal = i + j;
						break;
					case 'a':
					case 'f':
					default:
						grid[i][j].hVal = (100 - i) + (100 - j);
						break;
				}
				grid[i][j].actionRemoved = false;
			}
			fr.read();
		}
		fr.close();
		switch(tie)
		{
			case 's':
				open = new PriorityQueue<Cell>(10, Cell.smaller);
				closed = new PriorityQueue<Cell>(10, Cell.smaller);
				break;
			case 'l':
			default:
				open = new PriorityQueue<Cell>(10, Cell.larger);
				closed = new PriorityQueue<Cell>(10, Cell.larger);
				break;
		}
	}
	*/
	
	public void printL_Grid() 
	{		
		for(int i = 0; i < 101; ++i) 
		{			
			for(int j = 0; j < 101; ++j) 
			{				
				System.out.print((L_Grid[i][j].isBlocked) ? "1" : "0");
			}
			System.out.println();
		}
	}

	public void action(char ver, int nextX, int nextY, L_Cell prevL_Cell)
	{
		if(L_Grid[nextX][nextY].gVal > prevL_Cell.gVal + 1)
		{					
			L_Grid[nextX][nextY].gVal = prevL_Cell.gVal + 1;
			switch(ver)
			{
				case 'b':
					L_Grid[nextX][nextY].next = L_Grid[prevL_Cell.xCoor][prevL_Cell.yCoor];
					break;
				case 'a':
				case 'f':
				default:
					L_Grid[nextX][nextY].prev = L_Grid[prevL_Cell.xCoor][prevL_Cell.yCoor];
					break;
			}
			if(open.contains(L_Grid[nextX][nextY])) 
			{						
				open.remove(L_Grid[nextX][nextY]);
			}					
			open.add(L_Grid[nextX][nextY]);
		}
	}
	
	public boolean computePath(char ver, int a, int b) 
	{		
		this.numberOfExpandedCells = 0;
		while(!closed.contains(L_Grid[a][b])) 
		{			
			if(open.peek() == null) return false;
			L_Cell state = open.poll();
			if(state.xCoor == 2 && state.yCoor == 0 && state.gVal ==0) {
				this.toString();
			}
			closed.add(state);
			this.numberOfExpandedCells++;
			//up
			try {
			if(state.xCoor > 0)
			{
				if(!closed.contains(L_Grid[state.xCoor - 1][state.yCoor]) && !L_Grid[state.xCoor - 1][state.yCoor].actionRemoved)
				{
					L_Grid[state.xCoor-1][state.yCoor].gVal = Double.POSITIVE_INFINITY;
					action(ver, state.xCoor - 1, state.yCoor, state);
				}
			}
			//right
			if(state.yCoor + 1 < x_size)
			{
				if(!closed.contains(L_Grid[state.xCoor][state.yCoor + 1]) && !L_Grid[state.xCoor][state.yCoor + 1].actionRemoved)
				{
					L_Grid[state.xCoor][state.yCoor + 1].gVal = Double.POSITIVE_INFINITY;
					action(ver, state.xCoor, state.yCoor + 1, state);
				}
			}
			//down
			if(state.xCoor + 1 < y_size)
			{
				if(!closed.contains(L_Grid[state.xCoor + 1][state.yCoor]) && !L_Grid[state.xCoor + 1][state.yCoor].actionRemoved)
				{
					L_Grid[state.xCoor + 1][state.yCoor].gVal = Double.POSITIVE_INFINITY;
					action(ver, state.xCoor + 1, state.yCoor, state);
				}
			}
			//left
			if(state.yCoor > 0)
			{
				if(!closed.contains(L_Grid[state.xCoor][state.yCoor - 1]) && !L_Grid[state.xCoor][state.yCoor - 1].actionRemoved)
				{
					L_Grid[state.xCoor][state.yCoor - 1].gVal = Double.POSITIVE_INFINITY;
					action(ver, state.xCoor, state.yCoor - 1, state);
				}
			}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public void aStar(char ver)
	{
		L_Cell curr = L_Grid[0][0];
		//preliminary check
		if(L_Grid[0][1].isBlocked)
			L_Grid[0][1].actionRemoved = true;
		if(L_Grid[1][0].isBlocked)
			L_Grid[1][0].actionRemoved = true;
		while(curr.xCoor != x_size -1 || curr.yCoor != y_size -1)
		{
			open.clear();
			closed.clear();
			boolean success = false;
			switch(ver)
			{
				case 'b':
					L_Grid[100][100].gVal = 0;
					L_Grid[curr.xCoor][curr.yCoor].gVal = Double.POSITIVE_INFINITY;
					open.add(L_Grid[100][100]);
					success = computePath(ver, curr.xCoor, curr.yCoor);
					break;
				case 'a':
				case 'f':
				default:
					L_Grid[curr.xCoor][curr.yCoor].gVal = 0;
					L_Grid[100][100].gVal = Double.POSITIVE_INFINITY;
					open.add(L_Grid[curr.xCoor][curr.yCoor]);
					success = computePath(ver, 100, 100);
					break;
			}
			if(!success)
			{
				//agent.addToVisualization(null, null, null);
				System.out.println("cannot reach target");
				return;
			}
			switch(ver)
			{
				case 'b':
					L_Cell advance = L_Grid[curr.xCoor][curr.yCoor];
					while(!(advance.xCoor == 100 && advance.yCoor == 100))
					{
						L_Cell further = advance.next;
						further.prev = advance;
						advance = further;
					}
					break;
				case 'a':
				case 'f':
				default:
					L_Cell backtrack = L_Grid[100][100];
					while(backtrack != curr)
					{
						L_Cell prior = backtrack.prev;
						prior.next = backtrack;
						backtrack = prior;
					}
					break;
			}
			
			L_Cell ptr = curr;
			L_Cell prev = curr;
			L_Cell agentStart = curr;
			System.out.print(",("+ptr.xCoor + "," + ptr.yCoor+")");
			while(ptr != null && (ptr.xCoor != x_size -1 || ptr.yCoor != x_size-1) && !ptr.isBlocked)
			{
				if(ptr.next == null) 
					{ptr.toString();}
				if(ver == 'a')
					ptr.hVal = L_Grid[100][100].gVal - ptr.gVal;
				//update action costs
				if(ptr.xCoor > 0 && L_Grid[ptr.xCoor - 1][ptr.yCoor].isBlocked)
					{
						L_Grid[ptr.xCoor - 1][ptr.yCoor].actionRemoved = true;
						this.newBlockedCells.add(new int[] {ptr.xCoor - 1, ptr.yCoor} );
					}
				if(ptr.yCoor < y_size -1 && L_Grid[ptr.xCoor][ptr.yCoor + 1].isBlocked)
					{
						L_Grid[ptr.xCoor][ptr.yCoor + 1].actionRemoved = true;
						this.newBlockedCells.add(new int[] {ptr.xCoor, ptr.yCoor + 1} );
					}
				if(ptr.xCoor < x_size -1 && L_Grid[ptr.xCoor + 1][ptr.yCoor].isBlocked)
					{
						L_Grid[ptr.xCoor + 1][ptr.yCoor].actionRemoved = true;
						this.newBlockedCells.add(new int[] {ptr.xCoor + 1, ptr.yCoor} );
					}
				if(ptr.yCoor > 0 && L_Grid[ptr.xCoor][ptr.yCoor - 1].isBlocked)
					{
						L_Grid[ptr.xCoor][ptr.yCoor - 1].actionRemoved = true;
						this.newBlockedCells.add(new int[] {ptr.xCoor, ptr.yCoor - 1} );
					}
				ptr = ptr.next;
				if(ptr == L_Grid[x_size-1][y_size-1]) {
					this.toString();
					curr = L_Grid[x_size-1][y_size-1];
				}
				System.out.print(",("+ptr.xCoor + "," + ptr.yCoor+")");
			}
			System.out.println("\n");
			L_Cell agentEnd = null;
			if(ptr.isBlocked)
				agentEnd = ptr.prev;
			if(curr == L_Grid[x_size-1][y_size-1]){
				agentEnd = L_Grid[x_size-1][y_size-1];
			}
			agent.addToVisualization(agentStart, agentEnd, newBlockedCells, this.numberOfExpandedCells);
			this.newBlockedCells.clear();
			if(ptr.isBlocked)
				curr = ptr.prev;
			System.out.println("D");
		}
		System.out.println("reached the target");
	}
}