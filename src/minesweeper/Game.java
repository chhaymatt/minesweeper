package minesweeper;

public class Game {
	private int boardSize;
	private String[][] board; // For mine generation and counting empty spaces
	private String[][] boardVisible; // To display to user
	private String[][] boardEnd; // To display to user at the end of game

	private int mines;
	private String unknown = ConsoleColours.WHITE_BACKGROUND + "?" + ConsoleColours.RESET;
	private String mine = ConsoleColours.RED_BACKGROUND + "B" + ConsoleColours.RESET;
	private String empty = " ";
	private boolean isDone = false;

	public Game(int boardSize, int mines) {
		this.boardSize = boardSize;
		this.mines = mines;
		this.board = new String[boardSize + 2][boardSize + 2];
		this.boardVisible = new String[boardSize + 2][boardSize + 2];
		this.boardEnd = new String[boardSize + 2][boardSize + 2];

		for (int x = 0; x < board.length; x++) {

			for (int y = 0; y < board.length; y++) {

				if (x == 0 || y == 0 || x == board.length || y == board.length) {
					// Hide the outer border
					board[x][y] = empty;
					boardVisible[x][y] = empty;
					boardEnd[x][y] = empty;
				} else {
					board[x][y] = unknown;
					boardVisible[x][y] = unknown;
					boardEnd[x][y] = unknown;

				}
			}

		}

		System.out.println(ConsoleColours.CYAN + "The board is " + boardSize + " by " + boardSize + " and contains "
				+ mines + " mines" + ConsoleColours.RESET);
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
						System.out.print(ConsoleColours.RED_BACKGROUND + board[x][y] + ConsoleColours.RESET);
						break;
					default:
						System.out.print(board[x][y]);
				}

				// Show axis
				if (x == 0)
					// Display y axis
					if (y < 10)
						// Add leading space for numbers less than 10
						System.out.print(ConsoleColours.BLACK_BOLD_BRIGHT + " " + y + ":" + ConsoleColours.RESET);
					else
						System.out.print(ConsoleColours.BLACK_BOLD_BRIGHT + y + ":" + ConsoleColours.RESET);
				else if (y == 0)
					// Display x axis
					if (x < 10)
						// Add leading space for numbers less than 10
						System.out.print(ConsoleColours.BLACK_BOLD_BRIGHT + " " + x + ConsoleColours.RESET);
					else
						System.out.print(ConsoleColours.BLACK_BOLD_BRIGHT + x + ConsoleColours.RESET);
			}

		}
		System.out.println("");

	}

	// Update board
	public void updateBoard() {
		displayBoard(boardVisible);
		// displayBoard(board); // Diagnostics
		// System.out.println("");
		// displayBoard(boardEnd); // Diagnostics
	}

	// Fill board with mines
	public void generateMines() {
		for (int i = 0; i < mines; i++) {

			// Generate a random coordinate to place the mine
			int x = (int) (boardSize * Math.random());
			int y = (int) (boardSize * Math.random());

			if (x > 0 && x < board.length - 2 && y > 0 && y < board.length - 2 && !board[x][y].equals(mine)) {
				// Add a mine only within the visible bounds and it doesn't exist
				board[x][y] = mine;
				boardEnd[x][y] = mine;
			} else {
				// If outside of visible bounds or mine already exists, generate another
				// coordinate
				i--;
			}
		}
	}

	// Reveal surrounding tiles if it's unknown
	public void revealNearby(int x, int y) {
		for (int i = x - 1; i < x + 2; i++) {
			for (int j = y - 1; j < y + 2; j++) {
				if (board[i][j].equals(unknown)) {
					board[i][j] = empty;
					boardVisible[i][j] = empty;
				} else if (board[i][j].equals(mine)) {
					boardVisible[i][j] = unknown;
				}
			}
		}
	}

	// Count how many mines are around a tile
	public void countNearby() {
		for (int x = 1; x < board.length - 1; x++) {
			for (int y = 1; y < board.length - 1; y++) {
				if (board[x][y].equals(empty)) {
					int count = 0;
					for (int i = x - 1; i <= x + 1; i++) {
						for (int j = y - 1; j <= y + 1; j++) {
							if (board[i][j].equals(mine))
								count++;
						}
					}

					// Remove tile if the count is zero
					if (count == 0) {
						boardVisible[x][y] = empty;
						boardEnd[x][y] = empty;

					} else {
						// Display count on tile (0-8)
						boardVisible[x][y] = String.valueOf(count);
						boardEnd[x][y] = String.valueOf(count);
					}
				}

			}
		}
	}

	// Check if the player has revealed all empty tiles from board
	public void checkWin() {
		int count = 0;
		for (int x = 1; x < board.length - 1; x++) {
			for (int y = 1; y < board.length - 1; y++) {
				if (board[x][y].equals(unknown)) {
					// If board contains unknowns, then increase count
					count++;
				}
			}
		}

		// If count is zero, then all tiles have been revealed and the player wins
		if (count == 0) {
			System.out.println(ConsoleColours.GREEN_BACKGROUND + "Congratulations! You won!" + ConsoleColours.RESET);
			this.isDone = true;
		}
	}

	public void turn(int x, int y) {
		if (board[x][y].equals(unknown)) {
			board[x][y] = empty;
			boardVisible[x][y] = empty;
			revealNearby(x, y);
			countNearby();
			updateBoard();
			checkWin();
		} else if (board[x][y].equals(mine)) {
			System.out.println("");
			System.out.println(ConsoleColours.RED_BACKGROUND + "Boom! Oh no, there was a mine at " + x + ", " + y + "!"
					+ ConsoleColours.RESET);
			this.isDone = true;
			displayBoard(boardEnd);
		} else {
			System.out.println("");
			System.out.println(ConsoleColours.YELLOW + "Try another tile at " + x + ", " + y + " was already revealed"
					+ ConsoleColours.RESET);
		}
	}

	public boolean getDone() {
		return this.isDone;
	}

}
