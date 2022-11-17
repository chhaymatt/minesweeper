package minesweeper;

public class Game {
	private int boardSize;
	private String[][] board; // For mine generation
	private String[][] boardVisible; // To display to user
	
	private int mines;
	private String unknown = ConsoleColours.WHITE_BACKGROUND_BRIGHT + "?" + ConsoleColours.RESET;
	private String mine = ConsoleColours.RED_BACKGROUND + "B" + ConsoleColours.RESET;
	private String empty = " ";
	private boolean isDone = false;
	
	public Game(int boardSize, int mines) {
		this.boardSize = boardSize;
		this.mines = mines;
		this.board = new String[boardSize + 2][boardSize + 2];
		this.boardVisible = new String[boardSize + 2][boardSize + 2];
		
		for (int x = 0; x < board.length; x++) {
			
			for (int y = 0; y < board.length; y++) {
				
				if (x == 0 || y == 0 || x == board.length || y == board.length) {
					// Hide the outer border
					board[x][y] = empty;
					boardVisible[x][y] = empty;
				} else {
					board[x][y] = unknown;
					boardVisible[x][y] = unknown;
					
				}
			}
			
		}
		System.out.println("The board is " + boardSize + " by " + boardSize + " and contains " + mines + " mines");
		generateMines();
	}
	
	// Display board
	public void displayBoard(String[][] board) {
		
		for (int y = board.length - 2; y >= 0; y--) {
			
			for (int x = 0; x < board.length - 1; x++) {
				
				
				// Add spacing between each tile and a new row when y = 0
				if (x > 0 && y > 0)
					System.out.print("  ");
				else if (x == 0)
					System.out.println("");
				
				
				// Display tile
				//System.out.print(board[x][y]);
				switch (board[x][y]) {
					case "1":
						System.out.print(ConsoleColours.BLUE_BACKGROUND + board[x][y] + ConsoleColours.RESET);
						break;
					case "2":
						System.out.print(ConsoleColours.GREEN_BACKGROUND_BRIGHT + board[x][y] + ConsoleColours.RESET);
						break;
					case "3":
						System.out.print(ConsoleColours.RED_BACKGROUND_BRIGHT + board[x][y] + ConsoleColours.RESET);
						break;
					case "4":
						System.out.print(ConsoleColours.PURPLE_BACKGROUND_BRIGHT + board[x][y] + ConsoleColours.RESET);
						break;
					case "5":
						System.out.print(ConsoleColours.PURPLE_BACKGROUND + board[x][y] + ConsoleColours.RESET);
						break;
					case "6":
						System.out.print(ConsoleColours.CYAN_BACKGROUND + board[x][y] + ConsoleColours.RESET);
						break;
					case "7":
						System.out.print(ConsoleColours.BLACK_BACKGROUND_BRIGHT + board[x][y] + ConsoleColours.RESET);
						break;
					case "8":
						System.out.print(ConsoleColours.WHITE_BACKGROUND_BRIGHT + board[x][y] + ConsoleColours.RESET);
						break;
					default: 
						System.out.print(board[x][y]);
				}
				// Display y axis
				if(x == 0) {
					if(y < 10) {
						System.out.print(ConsoleColours.BLACK_BOLD_BRIGHT + " " + y + ":" + ConsoleColours.RESET);
					} else {
						System.out.print(ConsoleColours.BLACK_BOLD_BRIGHT + y + ":" + ConsoleColours.RESET);
					}
				} else if (y == 0) {
					// Display x axis
					if(x < 10) {
						System.out.print(ConsoleColours.BLACK_BOLD_BRIGHT + " "+ x + ConsoleColours.RESET);
					} else {
						System.out.print(ConsoleColours.BLACK_BOLD_BRIGHT + x + ConsoleColours.RESET);
					}
				}
				
			}
			
		}
		
	}
	
	// Update board
	public void updateBoard() {
		displayBoard(boardVisible);
		System.out.println("");
		displayBoard(board); // Diagnostics
		System.out.println("");
	}
	
	// Fill board with mines
	public void generateMines() {
		for (int i = 0; i < mines; i++) {
			
			// Generate a random coordinate to place the mine
			int x = (int)(boardSize * Math.random());
			int y = (int)(boardSize * Math.random());
			
			//System.out.println("");
			//System.out.println("i: "+ i +" "+ x + ", " + y);
			
			if (x > 0 && x < board.length - 2 && y > 0 && y < board.length - 2 && !board[x][y].equals(mine)) {
				// Add a mine only within the visible bounds and it doesn't exist
				board[x][y] = mine;
			} else {
				// If outside of visible bounds or mine already exists, generate another coordinate
				i--;
			}
		}
	}
	
	// Find how many mines are around a tile
	public void countNearby() {
		for (int x = 1; x < board.length - 2; x++) {
			for (int y = 1; y < board.length - 2; y++) {
				if(board[x][y].equals(empty)) {
					int count = 0;
					for (int i = x - 1; i <= x + 1; i++) {
						for (int j = y - 1; j <= y + 1; j++) {
							if (board[i][j].equals(mine))
								count++;
						}
					}
					
					if (count == 0) {
						boardVisible[x][y] = empty;
					} else {
						boardVisible[x][y] = String.valueOf(count);
					}
					
				}
				
			}
		}
	}

	// Reveal surrounding tiles if it's unknown 
	public void revealNearby(int x, int y) {
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (board[i][j].equals(unknown)) {
					board[i][j] = empty;
					boardVisible[i][j] = empty;
				} else if (board[i][j].equals(mine)) { 
					boardVisible[i][j] = unknown;
				}
			}
		}
	}
	
	public void turn(int x, int y) {
		if (board[x][y].equals(unknown)) {
			board[x][y] = empty;
			boardVisible[x][y] = empty;
		} else if (board[x][y].equals(mine)) {
			System.out.println("Oh no! There was a mine at " + x + ", " + y);
			this.isDone = true;
			displayBoard(board);
		} else {
			System.out.println("Try another tile, that one has been revealed");
		}
	}
	
	public boolean getDone() {
		return this.isDone;
	}
	
	
}
