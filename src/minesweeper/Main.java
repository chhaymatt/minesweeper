package minesweeper;

public class Main {

	public static void main(String[] args) {

		
		String[][] board = new String[11][11]; // Extra row/column for countin
		int mines = 10;
		
		// Fill board with unknowns
		for (int x = 0; x < board.length; x++) {
			
			for (int y = 0; y < board.length; y++) {
				board[x][y] = "?";
			}
			
		}
		
		displayBoard(board);
		generateMines(board, mines);
		displayBoard(board);
		findNearby(board);
		displayBoard(board);
		
	}
	
	// Display board
	public static void displayBoard(String[][] board) {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board.length; y++) {
				// Add spacing between each tile and a new row when y = 0
				if (y > 0)
					System.out.print(" ");
				else
					System.out.println("");
				
//				// Display tile
//				if (board[x][y].equals("?"))
//					System.out.print("");
//				else 
					System.out.print(board[x][y]);
			}
		}
		System.out.println("");
	}
	
	// Fill board with mines
	public static void generateMines(String[][] board, int mines) {
		for (int i = 0; i < mines; i++) {
			
			// Generate a random coordinate
			int x = (int)(10 * Math.random());
			int y = (int)(10 * Math.random());
			
			//System.out.println("");
			//System.out.println("i: "+ i +" "+ x + ", " + y);
			
			if (x > 0 && x <= board.length && y > 0 && y < board.length) {
				// Add a mine only if there is no mine in that spot
				if(board[x][y].equals("B"))
					i--; // Generate another coordinate to place the mine
				else
					board[x][y] = "B";
			}
		}
	}
	
	// Find how many mines are around a tile
	public static void findNearby(String[][] board) {
		for (int x = 1; x < board.length - 1; x++) {
			for (int y = 1; y < board.length - 1; y++) {
				if(board[x][y].equals("?")) {
					int count = 0;
					for (int i = x - 1; i <= x + 1; i++) {
						for (int j = y - 1; j <= y + 1; j++) {
							if (board[i][j].equals("B"))
								count++;
						}
					}
					board[x][y] = String.valueOf(count);
				}
				
			}
		}
	}

}
