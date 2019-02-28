package part2;

import static Part0.CellStatus.Blocked;
import static Part0.CellStatus.Unblocked;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Part0.Maze;

public class RealDFS 
{
	public static void main(String[] args) throws IOException 
	{		
		makeDFSMaze(50);
	}
	
	public static void makeDFSMaze(int k) throws IOException {
		for(int n = 0; n < k; ++n)
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
			
			 
			FileWriter fw = new FileWriter("demo/dfsMaze" + n + ".txt");
			for(int i = 0; i < size; ++i)
			{
				for(int j = 0; j < size; ++j)
				{
					if(maze[i][j].isBlocked)
						fw.write("1");
					else
						fw.write("0");
				}
				fw.write("\r\n");
			}
			fw.close();
			
			FileReader fr = new FileReader("demo/dfsMaze" + n + ".txt");
			Maze myMaze = Maze.createNewMaze(101, 101);
			for(int i = 0; i < 101; i++) {
				for(int j = 0; j < 101; j++) {
					if(maze[i][j].isBlocked == true) {
						myMaze.getCellAtCoordinates(i,j).status = Blocked;
					}
					else {
						myMaze.getCellAtCoordinates(i,j).status = Unblocked;
					}
				}
			}
			myMaze.saveMaze("demo/dfsMaze" + n);
		}
		
	}
}
