public class Board {

    private final int NUM_OF_COLUMNS = 7;
    private final int NUM_OF_ROW = 6;
    private char[][] board;


    public Board() {
        board = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
    }

    /*
     * Checks if the move the user made is valid. If it is valid then the
     * symbol is inserted in the column number(player input) of the board.
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
     * currentRow returns the row number where there a piece is absent (' ')
     * is present in the column the player wants to place the symbol.
     */
    public int currentRow(int column) {
        int currentRow = 0;
        while (currentRow <= NUM_OF_ROW - 1 && board[currentRow][column - 1] != ' ') {
            currentRow += 1;
        }
        return currentRow;
    }

    /*
     * This method is used for the defensive move in AIPlayer.
     * It removes a piece from the board by replacing the symbol with ' '.
     */
    public void rollbackMove(int column) {
        if (currentRow(column) == 0) {
            board[currentRow(column)][column - 1] = ' ';
        } else {
            board[currentRow(column) - 1][column - 1] = ' ';
        }
    }
}