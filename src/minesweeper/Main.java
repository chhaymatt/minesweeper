package minesweeper;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println(ConsoleColours.CYAN_BOLD + "Hello! Welcome to Minesweeper!" + ConsoleColours.RESET);
		System.out.println(ConsoleColours.CYAN + "Made by Matthew Chhay 2022" + ConsoleColours.RESET);
		
		int size = 10;
		int mines = 10;
		
		// Create a Minesweeper Game(boardSize, mines)
		Game minesweeper = new Game(size, mines);
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			minesweeper.updateBoard();
			while(!minesweeper.getDone()) {
				System.out.println("");
				System.out.print(String.format(ConsoleColours.CYAN + "Enter x coordinate (%s-%s inclusive): ", 1, size) + ConsoleColours.RESET);
				int x = getIntBetweenBounds(0, size + 1, scanner);
				
				System.out.print(String.format(ConsoleColours.CYAN + "Enter y coordinate (%s-%s inclusive): ", 1, size) + ConsoleColours.RESET);
				int y = getIntBetweenBounds(0, size + 1, scanner);
				
				minesweeper.turn(x, y);
			}
			System.out.println("");
			System.out.println(ConsoleColours.CYAN + "Want to play again?" + ConsoleColours.RESET);
			System.out.println(ConsoleColours.GREEN_BOLD + "1: Yes" + ConsoleColours.RESET);
			System.out.println(ConsoleColours.RED_BOLD + "2: No " + ConsoleColours.RESET);
			int playAgainInput = getIntBetweenBounds(0, 3, scanner);
			if (playAgainInput == 1) {
				minesweeper = new Game(size, mines);
			} else {
				System.out.println(ConsoleColours.GREEN + "Thank you for playing! You may close the console." + ConsoleColours.RESET);
				break;
			}
		}
	}
	
	public static int getIntBetweenBounds(int lower, int upper, Scanner scanner) {
		boolean correctInput = false;
		int output = 0;
		while (correctInput == false) {
			try {
				output = scanner.nextInt();
				if (output > lower && output < upper) {
					correctInput = true;
				} else { 
					System.out.println(ConsoleColours.YELLOW + "Enter a number between " + lower + " and " + upper + ConsoleColours.RESET);
				}
			} catch (Exception e) { // Error will display here
				scanner.next(); // Flushes because  scanner.nextInt() never finishes
				System.out.println(ConsoleColours.YELLOW + "You must enter a whole number" + ConsoleColours.RESET);
			}
		}
		return output;
	}
	

}
