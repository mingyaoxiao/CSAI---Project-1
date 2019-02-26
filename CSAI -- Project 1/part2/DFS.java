package part2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class DFS 
{
	public static void main(String[] args) throws IOException 
	{		
		int size = 101;
		int length = (int) Math.pow(10, String.valueOf(size).length());
		System.out.println(length);
		ArrayList<L_Cell> visited = new ArrayList<L_Cell>();
		Stack<L_Cell> stack = new Stack<L_Cell>();
		L_Cell[][] maze = new L_Cell[size][size];
		for(int i = 0; i < size; ++i) 
		{			
			for(int j = 0; j < size; ++j) 
			{
				maze[i][j] = new L_Cell();
				maze[i][j].xCoor = i;
				maze[i][j].yCoor = j;
			}
		}
		maze[0][0].isBlocked = false;
		L_Cell curr = maze[0][0];
		visited.add(curr);
		stack.push(curr);
		while(visited.size() != size * size)
		{
			ArrayList<L_Cell> neighbors = new ArrayList<L_Cell>();
			if(curr.yCoor > 0 && !visited.contains(maze[curr.xCoor][curr.yCoor - 1]))
				neighbors.add(maze[curr.xCoor][curr.yCoor - 1]);
			if(curr.xCoor < size - 1 && !visited.contains(maze[curr.xCoor + 1][curr.yCoor]))
				neighbors.add(maze[curr.xCoor + 1][curr.yCoor]);
			if(curr.yCoor < size - 1 && !visited.contains(maze[curr.xCoor][curr.yCoor + 1]))
				neighbors.add(maze[curr.xCoor][curr.yCoor + 1]);
			if(curr.xCoor > 0 && !visited.contains(maze[curr.xCoor - 1][curr.yCoor]))
				neighbors.add(maze[curr.xCoor - 1][curr.yCoor]);
			if(!neighbors.isEmpty())
			{
				stack.push(curr);
				Random r = new Random();
				int n = r.nextInt(neighbors.size());
				L_Cell next = neighbors.get(n);
				Random rand = new Random();
				int num = rand.nextInt(10);
				if(num < 3)
					maze[next.xCoor][next.yCoor].isBlocked = true;
				else
				{
					maze[next.xCoor][next.yCoor].isBlocked = false;
				}
				visited.add(next);
				curr = next;
			}
			else if(!stack.empty())
			{
				L_Cell next = stack.pop();
				curr = next;
			}
		}
		
		for(int n = 0; n < 1; ++n) 
		{
			FileWriter fw = new FileWriter("grid" + n + ".txt");
			for(int i = 0; i < size; ++i) 
			{
				for(int j = 0; j < size; ++j) 
				{
					/*
					if(i == 0 && j == 0) 
					{	
						fw.write("0");
						continue;
					}
					if(i == size - 1 && j == size - 1) 
					{
						fw.write("0");
						continue;
					}
					Random r = new Random();
					int num = r.nextInt(10);
					if(num < 3)
						fw.write("1");
					else
						fw.write("0");
					*/
					if(maze[i][j].isBlocked)
						fw.write("1");
					else
						fw.write("0");
				}
				fw.write("\n");
			}
			fw.close();
		}
	}
}
