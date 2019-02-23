package part2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Part0.Maze;
import static part2.AlgoMode.*;

public class MazeSolverApp {
	public static void main(String[] args) {
		// Ask to visualize A* on a maze
		Scanner scanner = new Scanner(System.in);
		//Get filepath of maze
		System.out.println("Please enter a filepath to the maze");
		String mazePath = scanner.nextLine();
		Maze maze = Maze.loadMaze(mazePath);
	
		
		//create a repeated astar agent
		// create a game object
		Game g = new Game(maze);
		RepeatedAStarAgent agent = new RepeatedAStarAgent(g);
		// run it on the maze
		char version = 'f';
		char tiebreaker = 'f';
		List<String> history = agent.Run(version, tiebreaker);
		
		// display the visualizatin
		//if(agent.incompletable == false) {
			File f = new File("history.txt");
			try {
				f.createNewFile();
				try(FileWriter fw = new FileWriter("myfile.txt", true);
					    BufferedWriter bw = new BufferedWriter(fw);
					    PrintWriter out = new PrintWriter(bw))
					{
						for(int i = 0; i< history.size(); i++) {
							//System.out.println(history.get(i));
						    out.println(history.get(i));
						}
					} catch (IOException e) {
					    //exception handling left as an exercise for the reader
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(history);
			
		//}
		//else {
			//System.out.println("Game cannot be completed.");
		//}
		
		
	}
}
