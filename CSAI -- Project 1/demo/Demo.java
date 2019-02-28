package demo;

import java.io.IOException;
import java.util.Scanner;

import Part0.*;
import part2.*;


public class Demo {
	static Scanner s;
	public static void main(String args[]) {
		System.out.println("Demonstration of our code");
		try {
			System.out.println("Welcome to the Maze App");
			s = new Scanner(System.in);
			boolean programEnded = false;
			while(!programEnded) {
				System.out.println("Welcome to the main module.");
				System.out.println("Please choose the part you would like to have demonstrated:");
				System.out.println("0) Maze Generation/Viewing");
				System.out.println("2) Tiebreaking Between Larger and Smaller G-values");
				System.out.println("3) Forward and Backward Search");
				System.out.println("5) Adaptive Search");
				
				int choice = s.nextInt();
				switch(choice) {
				case 0:
					Part0();
					break;
				case 2:
					Part2();
					break;
				case 3:
					Part3();
					break;
				case 5:
					Part5();
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
			e.printStackTrace();
		}
	}
	private static void Part5() {
		// TODO Auto-generated method stub
		boolean programEnded = false;
		while(!programEnded) {
			System.out.println("Welcome to the Part 5 module.");
			System.out.println("Please choose the part you would like to have demonstrated:");
			System.out.println("1) Larger G Value Favoring - Forward Repeated A*");
			System.out.println("2) Larger G Value Favoring - Adaptive Repeated A*");
			System.out.println("3) Exit");
			
			
			int choice = s.nextInt();
			switch(choice) {
			case 1:
				part2.MazeSolverApp.runAlgo('f', 'l', "demo");
				System.out.println("The algorithm has been run and its visualization generated in the folder src/demo");
				break;
			case 2:
				part2.MazeSolverApp.runAlgo('a', 'l', "demo");
				System.out.println("The algorithm has been run and its visualization generated in the folder src/demo");
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid option.");
				break;
			}
		}
	}
	private static void Part3() {
		// TODO Auto-generated method stub
		boolean programEnded = false;
		while(!programEnded) {
			System.out.println("Welcome to the Part 3 module.");
			System.out.println("Please choose the part you would like to have demonstrated:");
			System.out.println("1) Larger G Value Favoring - Forward Repeated A*");
			System.out.println("2) Larger G Value Favoring - Backward Repeated A*");
			System.out.println("3) Exit");
			
			
			int choice = s.nextInt();
			switch(choice) {
			case 1:
				part2.MazeSolverApp.runAlgo('f', 'l', "src/demo");
				System.out.println("The algorithm has been run and its visualization generated in the folder src/demo");
				break;
			case 2:
				part2.MazeSolverApp.runAlgo('b', 'l', "src/demo");
				System.out.println("\n\n The algorithm has been run and its visualization generated in the folder src/demo");
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid option.");
				break;
			}
		}
	}
	private static void Part2() {
		// TODO Auto-generated method stub
		boolean programEnded = false;
		while(!programEnded) {
			System.out.println("Welcome to the Part 2 module.");
			System.out.println("Please choose the part you would like to have demonstrated:");
			System.out.println("1) Larger G Value Favoring - Forward Repeated A*");
			System.out.println("2) Smaller G Value Favoring - Forward Repeated A*");
			System.out.println("3) Exit");
			
			
			int choice = s.nextInt();
			switch(choice) {
			case 1:
				part2.MazeSolverApp.runAlgo('f', 'l', "src/demo");
				System.out.println("The algorithm has been run and its visualization generated in the folder src/demo");
				break;
			case 2:
				part2.MazeSolverApp.runAlgo('f', 's', "src/demo");
				System.out.println("The algorithm has been run and its visualization generated in the folder src/demo");
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid option.");
				break;
			}
		}
	}
	private static void Part0() throws IOException {
		// TODO Auto-generated method stub
		boolean programEnded = false;
		while(!programEnded) {
			System.out.println("Welcome to the Part 0 module.");
			System.out.println("Please choose the part you would like to have demonstrated:");
			System.out.println("1) Maze Generation");
			System.out.println("2) Maze Viewing");
			System.out.println("3) Exit");
			
			
			int choice = s.nextInt();
			switch(choice) {
			case 1:
				part2.RealDFS.makeDFSMaze(10);
				System.out.println("10 mazes, using DFS, have been generated in the folder demo");
				break;
			case 2:
				Part0.MazeViewerApp.viewMazesState("demo");
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
