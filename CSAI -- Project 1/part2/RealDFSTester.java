package part2;

import static Part0.CellStatus.Blocked;
import static Part0.CellStatus.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Part0.Maze;

public class RealDFSTester {
	public static void main(String[] args) throws IOException {
		String filePath = "realGrid0.txt";
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
		
		maze.getCellAtCoordinates(1, 1).status = Agent;
		maze.getCellAtCoordinates(99, 99).status = Goal;
		
		System.out.println(maze.getRender());
		
		RepeatedAStarAgent agent = new RepeatedAStarAgent(new Game(maze));
		agent.Run('f', 's');
		
		
		FileWriter fw = new FileWriter("realGrid0Visualization.txt");
		
		agent.history.forEach((s) -> {
			try {
				fw.write(s + "\r\n");
				fw.flush();
				fw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	});
	}
}
