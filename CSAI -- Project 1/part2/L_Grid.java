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
	public BinaryHeap<L_Cell> open;
	public ArrayList<L_Cell> closed;
	public RepeatedAStarAgent agent;
	public int numberOfExpandedCells;
	public List<int[]> newBlockedCells;
	int x_size;
	int y_size;
	int startPosX;
	int startPosY;
	int goalPosX;
	int goalPosY;
	Maze m;
	int counter = -1;
	//src/maze0.maze.maze
	L_Grid(char ver, char tie, Maze m, RepeatedAStarAgent agent){
		this.m = m;
		this.agent = agent;
		x_size = m.size[0];
		y_size = m.size[1];
		L_Grid = new L_Cell[x_size][y_size];
		startPosX = 1;
		startPosY = 1;
		goalPosX = 99;
		goalPosY = 99;
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
						L_Grid[i][j].hVal = (goalPosX - i) + (goalPosY - j);
						break;
				}
				L_Grid[i][j].actionRemoved = false;
			}
			
		}
		switch(tie)
		{
			case 's':
				open = new BinaryHeap<L_Cell>(L_Cell.smaller);
				closed = new ArrayList<L_Cell>();
				break;
			case 'l':
			default:
				open = new BinaryHeap<L_Cell>(L_Cell.larger);
				closed = new ArrayList<L_Cell>();
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
		for(int i = 0; i <= x_size; ++i) 
		{			
			for(int j = 0; j <= y_size; ++j) 
			{				
				System.out.print((L_Grid[i][j].isBlocked) ? "1" : "0");
			}
			System.out.println();
		}
	}

	public void action(char ver, int nextX, int nextY, L_Cell prevL_Cell, int a, int b)
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
				open.delete(L_Grid[nextX][nextY]);
			}	
			
			open.insert(L_Grid[nextX][nextY]);
		}
	}
	
	public boolean computePath(char ver, int a, int b) 
	{		
		this.numberOfExpandedCells = 0;
		while(!closed.contains(L_Grid[a][b])) 
		{			
			if(open.peek() == null) return false;
			L_Cell state = open.poll();
			if(state.xCoor == this.startPosX && state.yCoor == startPosY && state.gVal ==0) {
				this.toString();
			}
			closed.add(state);
			this.numberOfExpandedCells++;
			//up
			try {
			if(state.xCoor > this.startPosX)
			{
				if(!closed.contains(L_Grid[state.xCoor - 1][state.yCoor]) && !L_Grid[state.xCoor - 1][state.yCoor].actionRemoved)
				{
					L_Cell c = L_Grid[state.xCoor-1][state.yCoor];
					if(c.search < counter) {
						c.gVal = Double.POSITIVE_INFINITY;
						c.search = counter;
					}
					action(ver, state.xCoor - 1, state.yCoor, state, a, b);
				}
			}
			//right
			if(state.yCoor + 2 < x_size)
			{
				if(!closed.contains(L_Grid[state.xCoor][state.yCoor + 1]) && !L_Grid[state.xCoor][state.yCoor + 1].actionRemoved)
				{
					L_Cell c = L_Grid[state.xCoor][state.yCoor + 1];
					if(c.search < counter) {
						c.gVal = Double.POSITIVE_INFINITY;
						c.search = counter;
					}
					action(ver, state.xCoor, state.yCoor + 1, state, a, b);
				}
			}
			//down
			if(state.xCoor + 2 < y_size)
			{
				if(!closed.contains(L_Grid[state.xCoor + 1][state.yCoor]) && !L_Grid[state.xCoor + 1][state.yCoor].actionRemoved)
				{
					L_Cell c = L_Grid[state.xCoor+1][state.yCoor];
					if(c.search < counter) {
						c.gVal = Double.POSITIVE_INFINITY;
						c.search = counter;
					}
					action(ver, state.xCoor + 1, state.yCoor, state, a, b);
				}
			}
			//left
			if(state.yCoor > this.startPosY)
			{
				if(!closed.contains(L_Grid[state.xCoor][state.yCoor - 1]) && !L_Grid[state.xCoor][state.yCoor - 1].actionRemoved)
				{
					L_Cell c = L_Grid[state.xCoor][state.yCoor - 1];
					if(c.search < counter) {
						c.gVal = Double.POSITIVE_INFINITY;
						c.search = counter;
					}
					action(ver, state.xCoor, state.yCoor - 1, state, a, b);
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
		L_Cell curr = L_Grid[startPosX][startPosY];
		//preliminary check
		if(L_Grid[startPosX][startPosX + 1].isBlocked)
			L_Grid[startPosX][startPosX + 1].actionRemoved = true;
		if(L_Grid[1 + startPosX][startPosX].isBlocked)
			L_Grid[1 + startPosX][startPosX].actionRemoved = true;
		try {
			if(L_Grid[startPosX - 1][startPosX].isBlocked)
				L_Grid[startPosX - 1][startPosX].actionRemoved = true;
			if(L_Grid[startPosX][startPosX - 1].isBlocked)
				L_Grid[startPosX][startPosX - 1].actionRemoved = true;
		}
		catch(Exception e) {}//in case of out of bound, fail silently.}
		
		while(curr.xCoor != this.goalPosX || curr.yCoor != this.goalPosY)
		{
			counter++;
			open.clear();
			closed.clear();
			boolean success = false;
			switch(ver)
			{
				case 'b':
					L_Grid[this.goalPosX][this.goalPosY].gVal = 0;
					L_Grid[curr.xCoor][curr.yCoor].gVal = Double.POSITIVE_INFINITY;
					open.insert(L_Grid[this.goalPosX][this.goalPosY]);
					success = computePath(ver, curr.xCoor, curr.yCoor);
					break;
				case 'a':
				case 'f':
				default:
					L_Grid[curr.xCoor][curr.yCoor].gVal = 0;
					L_Grid[this.goalPosX][goalPosY].gVal = Double.POSITIVE_INFINITY;
					open.insert(L_Grid[curr.xCoor][curr.yCoor]);
					success = computePath(ver, goalPosX, goalPosY);
					break;
			}
			if(!success)
			{
				//agent.addToVisualization(null, null, null);
				System.out.println("cannot reach target");
				this.agent.incompletable = true;
				return;
			}
			if(ver == 'a')
				closed.forEach((n) -> n.hVal = L_Grid[goalPosX][goalPosY].gVal - n.gVal);
			switch(ver)
			{
				case 'b':
					L_Cell advance = L_Grid[curr.xCoor][curr.yCoor];
					while(!(advance.xCoor == goalPosX && advance.yCoor == goalPosY))
					{
						L_Cell further = advance.next;
						further.prev = advance;
						advance = further;
					}
					break;
				case 'a':
				case 'f':
				default:
					L_Cell backtrack = L_Grid[goalPosX][goalPosY];
					while(backtrack != curr)
					{
						L_Cell prior = backtrack.prev;
						prior.next = backtrack;
						backtrack = prior;
					}
					break;
			}
			
			L_Cell ptr = curr;
			L_Cell agentStart = curr;
			System.out.print(",("+ptr.xCoor + "," + ptr.yCoor+")");
			if(ptr.xCoor == 2 && ptr.yCoor == 4) {
				this.toString();
			}
			while(ptr != null && (ptr.xCoor != goalPosX || ptr.yCoor != goalPosY) && !ptr.isBlocked)
			{
				if(ptr.next == null) 
					{ptr.toString();}
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
				if(ptr == L_Grid[goalPosX][goalPosY]) {
					this.toString();
					curr = L_Grid[goalPosX][goalPosY];
				}
				System.out.print(",("+ptr.xCoor + "," + ptr.yCoor+")");
			}
			System.out.println("\n");
			L_Cell agentEnd = null;
			if(ptr.isBlocked)
				agentEnd = ptr.prev;
			if(curr == L_Grid[goalPosX][goalPosY]){
				agentEnd = L_Grid[goalPosX][goalPosY];
			}
			agent.addToVisualization(agentStart, agentEnd, newBlockedCells, ptr ,this.numberOfExpandedCells, this);
			this.newBlockedCells.clear();
			if(ptr.isBlocked)
				curr = ptr.prev;
			System.out.println("D");
			if(ver == 'b')
			{
				for(int i = 0; i < x_size; ++i) 
				{			
					for(int j = 0; j < y_size; ++j) 
					{
						L_Grid[i][j].hVal = Math.abs(curr.xCoor - i) + Math.abs(curr.yCoor - j);
					}
				}
			}
		}
		System.out.println("reached the target");
	}
}