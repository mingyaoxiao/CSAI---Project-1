package part2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RealDFS 
{
	public static void main(String[] args) throws IOException 
	{		
		int size = 101;
		L_Cell[][] maze = new L_Cell[size][size];
		for(int i = 0; i < size; ++i) 
		{			
			for(int j = 0; j < size; ++j) 
			{
				maze[i][j] = new L_Cell();
				maze[i][j].xCoor = i;
				maze[i][j].yCoor = j;
				maze[i][j].isBlocked = true;
			}
		}
		ArrayList<L_Cell> list = new ArrayList<L_Cell>();
		maze[1][1].isBlocked = false;
		list.add(maze[1][1]);
		ArrayList<Integer> dirs = new ArrayList<Integer>();
		dirs.add(0); //north
		dirs.add(1); //east
		dirs.add(2); //south
		dirs.add(3); //west
		int[] xChange = new int[] {0, 2, 0, -2};
		int[] yChange = new int[] {-2, 0, 2, 0};
		ArrayList<L_Cell> visited = new ArrayList<L_Cell>();
		visited.add(maze[1][1]);
		while(!list.isEmpty())
		{
			L_Cell c = list.get(list.size() - 1);
			Collections.shuffle(dirs, new Random());
			boolean neighborless = true;
			for(int i = 0; i < 4; ++i)
			{
				int newX = c.xCoor + xChange[dirs.get(i)];
				int newY = c.yCoor + yChange[dirs.get(i)];
				if(newX >= 1 && newY >= 1 && newX <= size - 2 && newY <= size - 2 && !visited.contains(maze[newX][newY]))
				{
					int midX = c.xCoor + xChange[dirs.get(i)] / 2;
					int midY = c.yCoor + yChange[dirs.get(i)] / 2;
					maze[midX][midY].isBlocked = false;
					maze[newX][newY].isBlocked = false;
					list.add(maze[newX][newY]);
					visited.add(maze[newX][newY]);
					neighborless = false;
					break;
				}
			}
			if(neighborless)
				list.remove(c);
		}
		
		for(int n = 0; n < 50; ++n) 
		{
			FileWriter fw = new FileWriter("realGrid" + n + ".txt");
			for(int i = 0; i < size; ++i)
			{
				for(int j = 0; j < size; ++j)
				{
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
