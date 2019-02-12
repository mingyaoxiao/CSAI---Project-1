package Part0;

import java.io.File;
import java.util.Optional;
import java.util.Scanner;

public class MazeViewerApp {
//	public void LaunchApp() {
//		// function stub
//	}
	static Scanner s;
	public static void main(String[] args) {
		System.out.println("Welcome to the Maze App");
		s = new Scanner(System.in);
		boolean programEnded = false;
		while(!programEnded) {
			System.out.println("Welcome to the main module.");
			System.out.println("Please choose an option:");
			System.out.println("1) Generate Mazes");
			System.out.println("2) View Mazes");
			System.out.println("3) Exit");
			
			int choice = s.nextInt();
			switch(choice) {
			case 1:
				generateMazesState();
				break;
			case 2:
				loadMazesState();
				break;
			case 3:
				programEnded = true;
				break;
			default:
				System.out.println("Invalid option.");
				break;
			}
		}
		
		s.close();
	}

	private static void generateMazesState() {
			// TODO Auto-generated method stub
			
	}
	
	private static void loadMazesState() {
		while(true) {
			System.out.println("Welcome to the Maze Loader module.");
			System.out.println("Please choose an option:");
			System.out.println("1) Open a folder of mazes");
			System.out.println("2) Exit");
			
			int choice = s.nextInt();
			s.nextLine();
			switch(choice) {
			case 1:
				reentry:
				while(true) {
					System.out.println("Please input the path to the folder:");
					String folderPath = s.nextLine();
					Optional<File> dirOrErr = FileHelper.returnEmptyOrValidDirectory(folderPath);
					if(dirOrErr.isPresent()) viewMazesState(folderPath);
					else {
						System.out.println("Invalid folder option. Press 1 to attempt again.");
						String reentry = s.nextLine();
						if(reentry == "1") break reentry;
					}
				}
				break;
			case 2:
				return;
			default:
				System.out.println("Invalid option.");
				break;
			}
		}
	}

	private static void displayMaze(Maze currentMaze) {
		if(currentMaze == null) {
			System.out.println("There are no more mazes to display in this direction.");
			return;
		}
		Object render = currentMaze.getRender();
		System.out.println(render.toString());
	}
	
	private static void viewMazesState(String folderPath) {
		MazeViewer mV = new MazeViewer();
		mV.loadMazes(folderPath);
		Maze currentMaze = mV.nextDisplay();
		if(currentMaze == null) {
			System.out.println("No mazes in this folder.");
			return;
		}
		
		while(true) {
			System.out.println("Welcome to the Maze Viewer module.");
			System.out.println("Please choose an option:");
			System.out.println("1) Next Display");
			System.out.println("2) Previous Display");
			System.out.println("3) Exit");
			
			int choice = s.nextInt();
			switch(choice) {
			case 1:
				currentMaze = mV.prevDisplay();
				displayMaze(currentMaze);
				break;
			case 2:
				currentMaze = mV.nextDisplay();
				displayMaze(currentMaze);
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid option.");
				break;
			}
		}
	}
}
