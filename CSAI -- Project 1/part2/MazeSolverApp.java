package part2;

import java.util.Scanner;

import Part0.Maze;

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
		String history = agent.Run();
		
		
		// display the visualizatin
		System.out.println(history);
		
		
	}
}
