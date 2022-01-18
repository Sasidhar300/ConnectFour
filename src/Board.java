public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;
	private char[][] board;

	public Board() {
		board = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
	}

	/*
	 * Checks if the move the user made is valid. If it is valid then the symbol is
	 * inserted in the column number(player input) of the board.
	 */
	public boolean insert(int column, char symbol) {
		boolean success = true;
		int row = currentRow(column);
		if (row < NUM_OF_ROW) {
			board[row][column - 1] = symbol;
		} else {
			success = false;
		}

		return success;
	}

	/*
	 * currentRow returns the row number where there a piece is absent (' ') is
	 * present in the column the player wants to place the symbol.
	 */
	public int currentRow(int column) {
		int currentRow = 0;
		while (currentRow <= NUM_OF_ROW - 1 && board[currentRow][column - 1] != ' ') {
			currentRow += 1;
		}
		return currentRow;
	}
	
	/* 
	 * Checks the board array for any other character other than the symbol of 
	 * the current player and empty space (' ').
	 * This method is implemented while assuming that none of the player will 
	 * have ' ' (empty space) as a symbol. 
	 */
	public char getOtherPlayerSymbol(char symbol) {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				if (board[i][j] != symbol && board[i][j] != ' ' ) {
					return board[i][j];
				}
			}
		}
		return ' '; 
	}
	
	/* 
	 * This method is used for the defensive move in AIPlayer. 
	 * It removes a piece from the board by replacing the symbol with ' '.
	 */
	public void rollbackMove(int column) {
		if(currentRow(column) == 0) {
			board[currentRow(column)][column-1] = ' ';
		}else {
			board[currentRow(column)-1][column-1] = ' ';
		}
	}
	
		public boolean containsWin() {
		boolean containsWin = false;
		// Checking rows
		for(int i = 0; i < NUM_OF_ROW; i++) {
			for(int j = 0; j < NUM_OF_COLUMNS - 2 - 1; j++) {
				if ((board[i][j] != ' ')
						&& (board[i][j] == board[i][j+1])
						&& (board[i][j+2] == board[i][j+3])
						&& (board[i][j+1] == board[i][j+2])) {
					containsWin = true;
				}
			}
		}
		// Checking columns 
		if (!containsWin) {
			for(int j = 0; j < NUM_OF_COLUMNS; j++) {
				for(int i = 0; i < NUM_OF_ROW - 2 - 1; i++) {
					if ((board[i][j] != ' ')
							&& (board[i][j] == board[i+1][j])
							&& (board[i+2][j] == board[i+3][j])
							&& (board[i+1][j] == board[i+2][j])) {
						containsWin = true;

					}
				}
			}
		}
		//Checking forward diagonal
		if (!containsWin) {
			for(int i = 0; i < NUM_OF_ROW - 2 - 1; i++) {
				for(int j = 0; j < NUM_OF_COLUMNS - 2 - 1; j++) {
					if ((board[i][j] != ' ')
							&& (board[i][j] == board[i+1][j+1])
							&& (board[i+2][j+2] == board[i+3][j+3])
							&& (board[i+1][j+1] == board[i+2][j+2])) {
						containsWin = true;
					}
				}
			}
		}
		//Checking backward diagonal
		if (!containsWin) {
			for(int i = 5; i > NUM_OF_ROW - 3 - 1; i--) {
				for(int j = 0; j < NUM_OF_COLUMNS - 2 - 1; j++) {
					if ((board[i][j] != ' ')
							&& (board[i][j] == board[i-1][j+1])
							&& (board[i-2][j+2] == board[i-3][j+3])
							&& (board[i-1][j+1] == board[i-2][j+2])) {
						containsWin = true;
					}
				}
			}
		}

		return containsWin;
	}
	
	public boolean isTie() {
		boolean isTie = true;
		for(int j = 0; j < NUM_OF_COLUMNS; j++) {
			if(board[NUM_OF_ROW-1][j] == ' ' && !containsWin()) {
				isTie = false;
			}
		}
		return isTie;
	}
	
	public int getNUM_OF_COLUMNS() {
		return NUM_OF_COLUMNS;
	}
	
	public int getNUM_OF_ROW() {
		return NUM_OF_ROW;
	}

	public void printBoard() {
		for (int i = NUM_OF_ROW - 1; i >= 0; i--) {
			System.out.println();
			System.out.print("|");
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				System.out.print(" " + board[i][j] + " |");
			}
			System.out.println("");
		}
	}

	public void reset() {
		for (int i = 0; i < NUM_OF_ROW; i++) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++) {
				board[i][j] = ' ';
			}
		}
	}

}
