package part2;

import Part0.Maze;

public class Game {
	Maze trueMaze;
	Maze awareMaze; 
	GameVisualization visualization;
	
	public Game(Maze maze) {
		this.trueMaze = maze;
		this.awareMaze = maze.cloneMaze();
		this.visualization = new GameVisualization(awareMaze);
	}
}
