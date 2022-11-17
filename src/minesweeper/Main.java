package minesweeper;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println(ConsoleColours.CYAN_BOLD + "Hello! Welcome to Minesweeper!" + ConsoleColours.RESET);
		
		// Create a Minesweeper Game(boardSize, mines)
		int size = 11;
		int mines = 10;
		Game minesweeper = new Game(size, mines);
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("");
			minesweeper.updateBoard();
			while(!minesweeper.getDone()) {
				System.out.println("");
				System.out.print(String.format("Enter x coordinate (%s-%s inclusive): ", 1, size));
				int x = getIntBetweenBounds(0, size + 1, scanner);
				
				System.out.print(String.format("Enter y coordinate (%s-%s inclusive): ", 1, size));
				int y = getIntBetweenBounds(0, size + 1, scanner);
				
				minesweeper.turn(x, y);
				
				if (!minesweeper.getDone()) {
					minesweeper.revealNearby(x, y);
					minesweeper.countNearby();
					minesweeper.updateBoard();
				}
			}
			System.out.println("");
			System.out.println("");
			System.out.println("Want to play again?");
			System.out.println("1: Yes");
			System.out.println("2: No");
			int playAgainInput = getIntBetweenBounds(0, 3, scanner);
			if (playAgainInput == 1) {
				minesweeper = new Game(size, mines);
			} else {
				System.out.println("Thank you for playing! You may close the console.");
				break;
			}
		}
	}
	
	public static int getIntBetweenBounds(int lower, int upper, Scanner scanner) {
		boolean correctInput = false;
		int output = 0;
		while (correctInput ==  false) {
			try {
				output = scanner.nextInt();
				if (output > lower && output < upper)
					correctInput = true;
				else
					System.out.println("Enter a number between " + lower + " and " + upper);
				
			} catch(Exception e) { // Error will display here
				scanner.next(); // Flushes because  scanner.nextInt() never finishes
				System.out.println("You must enter a whole number");
			}
		}
		return output;
	}
	

}
