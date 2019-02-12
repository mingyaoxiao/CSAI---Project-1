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
		try {
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
		catch(Exception e){
			System.out.println("The program encountered an error of some sort and needs to close.");
		}
	}

	private static void generateMazesState() {
		while(true) {
			System.out.println("Welcome to the Maze Generator module.");
			System.out.println("Please choose an option:");
			System.out.println("1) Generate a collection of mazes");
			System.out.println("2) Exit");
			
			int choice = s.nextInt();
			s.nextLine();
			switch(choice) {
			case 1:
				specification:
				while(true) {
					System.out.println("Type Exit at any time to exit.");
					System.out.println("Please specify the location in which you want to generate mazes.");
					String folderPath = s.nextLine();
					if(folderPath.equalsIgnoreCase("Exit")) break specification;
					Optional<File> dirOrErr = FileHelper.returnEmptyOrValidDirectory(folderPath);
					if(dirOrErr.isPresent()) {
						System.out.println("Please specify how many mazes you wish to generate.");
						String str_n = s.nextLine();
						if(str_n.equalsIgnoreCase("Exit")) break specification;
						int n = 0;
						try {
							n = Integer.parseInt(str_n);
						}catch(Exception e){}
						
						System.out.println("Please specify the prefix of each maze");
						String prefix = s.nextLine();
						if(prefix.equalsIgnoreCase("Exit")) break specification;
						
						System.out.println("Please specify the beginning index of your maze (For the naming).");
						String str_begin = s.nextLine();
						if(str_begin.equalsIgnoreCase("Exit")) break specification;
						int beginIndex = 0;
						try {
							beginIndex = Integer.parseInt(str_begin);
						}catch(Exception e){}
						
						System.out.println("Please specify the size of your maze.");
						String str_size = s.nextLine();
						if(str_size.equalsIgnoreCase("Exit")) break specification;
						int size = 0;
						try {
							size = Integer.parseInt(str_size);
						}catch(Exception e){}
						
						if(size < 1 || size > 500 || n < 1 || n > 500) {
							System.out.println("Some settings are not acceptable.");
							System.out.println("Possibly size is too large or too small. (Size needs to be within 1 and 500).");
							System.out.println("Or number of mazes is too large or too small. (n needs to be within 1 and 500).");
						}
						final int beginIndex_final = beginIndex;
						MazeGenerator.createMazes(n, (i) -> prefix+(i+beginIndex_final), folderPath, size, size);
						
						break;
					}
					else {
						System.out.println("Invalid folder option. Press 1 to attempt again.");
						String reentry = s.nextLine();
						if(!reentry.equals("1")) break specification;
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
					if(dirOrErr.isPresent()) {
						viewMazesState(folderPath);
						break;
					}
					else {
						System.out.println("Invalid folder option. Press 1 to attempt again.");
						String reentry = s.nextLine();
						if(!reentry.equals("1")) break reentry;
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

	private static void displayMaze(MazeDisplay currentDisplay) {
		if(currentDisplay == null) {
			System.out.println("There are no more mazes to display in this direction.");
			return;
		}
		Object render = currentDisplay.maze.getRender();
		System.out.println("\n");
		System.out.println("Now displaying: " + currentDisplay.fileName);
		System.out.println(render.toString());
		
	}
	
	private static void viewMazesState(String folderPath) {
		MazeViewer mV = new MazeViewer();
		mV.loadMazes(folderPath);
		mV.resetCursor();
		if(mV.loadedMazeDisplays.size() < 1) {
			System.out.println("No mazes in this folder.");
			return;
		}
		MazeDisplay currentMaze = null;
		while(true) {
			System.out.println("Welcome to the Maze Viewer module.");
			System.out.println("Please choose an option:");
			System.out.println("1) Next Display");
			System.out.println("2) Previous Display");
			System.out.println("3) Exit");
			
			int choice = s.nextInt();
			s.nextLine();
			switch(choice) {
			case 1:
				currentMaze = mV.nextDisplay();
				displayMaze(currentMaze);
				break;
			case 2:
				currentMaze = mV.prevDisplay();
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
