package part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Part0.Maze;
import static Part0.CellStatus.*;

public class TextMazeTester {
	public static void main(String[] args) throws IOException {
		String filePath = "src/grid0.txt";
		File f = new File(filePath);
		FileReader fr = new FileReader(filePath);
		Maze maze = Maze.createNewMaze(101, 101);
		for(int i = 0; i < 101; i++) {
			for(int j = 0; j < 101; j++) {
				int c = fr.read();
				if(c == '0') {
					maze.getCellAtCoordinates(i, j).status = Unblocked;
				}
				else {
					maze.getCellAtCoordinates(i, j).status = Blocked;
				}
			}
			fr.read();
		}
		
		RepeatedAStarAgent agent = new RepeatedAStarAgent(new Game(maze));
		agent.Run('a', 'l');
		
		FileWriter fw = new FileWriter("grid0Visualization.txt");
		
		agent.history.forEach((s) -> {
			try {
				fw.write(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		fw.write("Number of nodes expanded: " + agent.numberOfExpandedCells);
		System.out.println("Number of nodes expanded: " + agent.numberOfExpandedCells);
		/*for(int i = 0; i < agent.history.size(); i++) {
			
		}*/
		//fw.write(agent.history);
	}
}
