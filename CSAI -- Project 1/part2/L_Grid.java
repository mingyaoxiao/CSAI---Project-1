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
	
	L_Grid(Maze m, RepeatedAStarAgent agent){
		this.agent = agent;
		for(int i = 0; i < 101; ++i) 
		{			
			for(int j = 0; j < 101; ++j) 
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
				L_Grid[i][j].hVal = (100 - i) + (100 - j);
				L_Grid[i][j].actionRemoved = false;
			}
		}
		open = new PriorityQueue<L_Cell>(10, L_Cell.comp);
		closed = new PriorityQueue<L_Cell>(10, L_Cell.comp);
		newBlockedCells = new ArrayList<int[]>();
	}
	
	L_Grid(String s) throws IOException 
	{		
		L_Grid = new L_Cell[101][101];
		FileReader fr = new FileReader(s);
		for(int i = 0; i < 101; ++i) 
		{			
			for(int j = 0; j < 101; ++j) 
			{				
				int ch = fr.read();
				L_Grid[i][j] = new L_Cell();
				L_Grid[i][j].isBlocked = ((char) ch == '1') ? true : false;
				L_Grid[i][j].xCoor = i;
				L_Grid[i][j].yCoor = j;
				L_Grid[i][j].gVal = Double.POSITIVE_INFINITY;
				L_Grid[i][j].hVal = (100 - i) + (100 - j);
				L_Grid[i][j].actionRemoved = false;
			}
			fr.read();
		}
		fr.close();
		open = new PriorityQueue<L_Cell>(10, L_Cell.comp);
		closed = new PriorityQueue<L_Cell>(10, L_Cell.comp);
	}
	
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

	public void action(int nextX, int nextY, L_Cell prevL_Cell)
	{
		if(L_Grid[nextX][nextY].gVal > prevL_Cell.gVal + 1)
		{					
			L_Grid[nextX][nextY].gVal = prevL_Cell.gVal + 1;
			L_Grid[nextX][nextY].prev = L_Grid[prevL_Cell.xCoor][prevL_Cell.yCoor];
			prevL_Cell.next = L_Grid[nextX][nextY];
			if(open.contains(L_Grid[nextX][nextY])) 
			{						
				open.remove(L_Grid[nextX][nextY]);
			}					
			open.add(L_Grid[nextX][nextY]);
		}
	}
	
	public void computePath() 
	{		
		while(!closed.contains(L_Grid[100][100])) 
		{			
			L_Cell state = open.poll();
			closed.add(state);
			this.numberOfExpandedCells++;
			//up
			if(state.yCoor > 0)
			{
				if(!closed.contains(L_Grid[state.xCoor][state.yCoor - 1]) && !L_Grid[state.xCoor][state.yCoor - 1].actionRemoved)
				{
					action(state.xCoor, state.yCoor - 1, state);
				}
			}
			//right
			if(state.xCoor < 100)
			{
				if(!closed.contains(L_Grid[state.xCoor + 1][state.yCoor]) && !L_Grid[state.xCoor + 1][state.yCoor].actionRemoved)
				{
					action(state.xCoor + 1, state.yCoor, state);
				}
			}
			//down
			if(state.yCoor < 100)
			{
				if(!closed.contains(L_Grid[state.xCoor][state.yCoor + 1]) && !L_Grid[state.xCoor][state.yCoor + 1].actionRemoved)
				{
					action(state.xCoor, state.yCoor + 1, state);
				}
			}
			//left
			if(state.xCoor > 0)
			{
				if(!closed.contains(L_Grid[state.xCoor - 1][state.yCoor]) && !L_Grid[state.xCoor - 1][state.yCoor].actionRemoved)
				{
					action(state.xCoor - 1, state.yCoor, state);
				}
			}
		}
	}
	
	public void aStar()
	{
		L_Cell curr = L_Grid[0][0];
		//preliminary check
		if(L_Grid[0][1].isBlocked)
			L_Grid[0][1].actionRemoved = true;
		if(L_Grid[1][0].isBlocked)
			L_Grid[1][0].actionRemoved = true;
		while(curr.xCoor != 100 && curr.yCoor != 100)
		{
			L_Grid[curr.xCoor][curr.yCoor].gVal = 0;
			L_Grid[100][100].gVal = Double.POSITIVE_INFINITY;
			open = null;
			closed = null;
			open.add(L_Grid[curr.xCoor][curr.yCoor]);
			computePath();
			if(open == null)
			{
				agent.addToVisualization(curr, newBlockedCells);
				System.out.println("cannot reach target");
				return;
			}
			L_Cell ptr = curr.next;
			while(ptr.xCoor != 100 && ptr.yCoor != 100 && !ptr.isBlocked)
			{
				//update action costs
				if(ptr.yCoor > 0 && L_Grid[ptr.xCoor][ptr.yCoor - 1].isBlocked)
					{
						L_Grid[ptr.xCoor][ptr.yCoor - 1].actionRemoved = true;
						this.newBlockedCells.add(new int[] {ptr.xCoor, ptr.yCoor - 1} );
					}
				if(ptr.xCoor < 100 && L_Grid[ptr.xCoor + 1][ptr.yCoor].isBlocked)
					{
						L_Grid[ptr.xCoor + 1][ptr.yCoor].actionRemoved = true;
						this.newBlockedCells.add(new int[] {ptr.xCoor + 1, ptr.yCoor} );
					}
				if(ptr.yCoor < 100 && L_Grid[ptr.xCoor][ptr.yCoor + 1].isBlocked)
					{
						L_Grid[ptr.xCoor][ptr.yCoor + 1].actionRemoved = true;
						this.newBlockedCells.add(new int[] {ptr.xCoor, ptr.yCoor + 1} );
					}
				if(ptr.xCoor > 0 && L_Grid[ptr.xCoor - 1][ptr.yCoor].isBlocked)
					{
						L_Grid[ptr.xCoor - 1][ptr.yCoor].actionRemoved = true;
						this.newBlockedCells.add(new int[] {ptr.xCoor - 1, ptr.yCoor} );
					}
				ptr = ptr.next;
			}
			agent.addToVisualization(curr, newBlockedCells);
			this.newBlockedCells.clear();
			curr = ptr.prev;
		}
		System.out.println("reached the target");
	}
}