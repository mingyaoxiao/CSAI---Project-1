package part2;

import Part0.Maze;

public class Game {
	Maze trueMaze;
	Maze awareMaze; 
	GameVisualization visualization;
	
	public Game(Maze maze) {
		this.trueMaze = maze;
		this.awareMaze = Maze.createNewMaze(trueMaze.size[0], trueMaze.size[1]);
		this.visualization = new GameVisualization(awareMaze);
	}
}
