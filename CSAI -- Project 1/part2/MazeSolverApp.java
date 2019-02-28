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
		runAlgo('f', 'l', null);
	}
	
	public static void runAlgo(char version, char tiebreaker, String folder) {
		//src/stat/maze0.maze.maze{
				// Ask to visualize A* on a maze
				Scanner scanner = new Scanner(System.in);
				//Get filepath of maze
				System.out.println("Please enter a filepath to the maze");
				String mazePath = scanner.nextLine();
				Maze maze = Maze.loadMaze(mazePath);
				//Maze maze = Maze.loadMaze("src/stat/maze3.maze.maze");
				
				//create a repeated astar agent
				// create a game object
				Game g = new Game(maze);
				RepeatedAStarAgent agent = new RepeatedAStarAgent(g);
				// run it on the maze

				agent.Run(version, tiebreaker);
				List<String> history = agent.history; 
				
				// display the visualization
				//if(agent.incompletable == false) {
					File f = new File("history.txt");
					//	f.createNewFile();
					try(FileWriter fw = new FileWriter(folder+"/"+"Version_"+version+";Tiebreaker_"+tiebreaker+";"+mazePath.substring(mazePath.lastIndexOf('.'))+".txt", true);
						    BufferedWriter bw = new BufferedWriter(fw);
						    PrintWriter out = new PrintWriter(bw))
						{
							for(int i = 0; i < history.size(); i++) {
								//System.out.println(history.get(i));
								out.println(" Iteration #" + i);
							    out.println(history.get(i));
							    out.flush();out.flush();out.flush();
							}
						} catch (IOException e) {
						}
					
				//}
				//else {
					//System.out.println("Game cannot be completed.");
				//}
				
				
	}
}
