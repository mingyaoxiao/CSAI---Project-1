package Part0;

import java.io.Console;
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
			switch(choice) {
			case 1:
				viewMazesState("");
				break;
			case 2:
				s.close();
				return;
			default:
				System.out.println("Invalid option.");
				break;
			}
		}
	}

	private static void viewMazesState(String filepath) {
		MazeViewer mV = new MazeViewer();
		mV.loadMazes(filepath);
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
				viewMazesState("");
				break;
			case 2:
				currentMaze = mV.nextDisplay();
				return;
			case 3:
				s.close();
				return;
			default:
				System.out.println("Invalid option.");
				break;
			}
		}
	}
}
