import java.util.Random;

public class AIPlayer extends Player {

	public AIPlayer(char symbol, Board board, String name) {
		super(symbol, board, name);
	}

	@Override
	public void makeMove(Board board) {

		if (this.symbol == ' ') {
			/*
			 * If the AI has space character as the symbol then it is replaced with a
			 * character selected from "possibleCharacters" using the random library.
			 * At the same time making sure the new symbol of the player is not 
			 * identical to that of the opponent.
			 */
			String possibleCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			Random randomCharacter = new Random();
			char newSymbol = possibleCharacters.charAt(randomCharacter.nextInt(possibleCharacters.length()));
			while (board.getOtherPlayerSymbol(symbol) == newSymbol) {
				newSymbol = possibleCharacters.charAt(randomCharacter.nextInt(possibleCharacters.length()));
			}

			System.out.println(this.name + ", you chose *space character* as your symbol the AI chose a new symbol for you.");
			System.out.println("Your new symbol for the rest of the game is: " + newSymbol);
			this.symbol = newSymbol;

			/*
			 * Offensive Move: Places a piece(symbol) on the board at every valid position
			 * on the board (making use of board.insert()) and checks if the game is won
			 * with the move made. If true the symbol is placed in that column. Otherwise
			 * the move is retracted using the (board.rollbackMove()) method.
			 */
			boolean madeMove = false;
			for (int i = 1; i <= board.getNUM_OF_COLUMNS(); i++) {
				if (board.insert(i, symbol)) {
					if (board.containsWin()) {
						madeMove = true;
						System.out.print(this.name + " made a move: " + i);
						break;
					} else {
						board.rollbackMove(i);
					}
				}
			}

			/*
			 * Defensive Move: Gets the symbol of the other player using
			 * (board.getOtherPlayerSymbol()) and places it in all of the space characters
			 * on the board and checks for a winning move. If a winning move is present then
			 * the AI makes the same move to block the opponent player
			 */
			char otherPlayerSymbol = board.getOtherPlayerSymbol(symbol);
			if (!board.containsWin() && (madeMove == false)) {
				for (int i = 1; i <= board.getNUM_OF_COLUMNS(); i++) {
					if (board.insert(i, otherPlayerSymbol)) {
						if (board.containsWin()) {
							board.rollbackMove(i);
							board.insert(i, symbol);
							madeMove = true;
							System.out.print(this.name + " made a move: " + i);
							break;
						} else {
							board.rollbackMove(i);
						}
					}
				}
			}

			/*
			 * If there isn't a winning move or a defensive move then the AI will place a
			 * piece(symbol) at a random place on the board by making use of the random
			 * library.
			 */
			if (!board.containsWin() && madeMove == false) {
				Random random = new Random();
				int move = random.nextInt(board.getNUM_OF_COLUMNS()) + 1;
				while (!board.insert(move, symbol)) {
					move = random.nextInt(board.getNUM_OF_COLUMNS()) + 1;
				}
				System.out.print(this.name + " made a move: " + move);
			}
		} else {

			/*
			 * If the symbol of the AI is not *space character (' ')* then only this block
			 * of code runs
			 */
			boolean madeMove = false;
			for (int i = 1; i <= board.getNUM_OF_COLUMNS(); i++) {
				if (board.insert(i, symbol)) {
					if (board.containsWin()) {
						madeMove = true;
						System.out.print(this.name + " made a move: " + i);
						break;
					} else {
						board.rollbackMove(i);
					}
				}
			}

			char otherPlayerSymbol = board.getOtherPlayerSymbol(symbol);
			if (!board.containsWin() && (madeMove == false)) {
				for (int i = 1; i <= board.getNUM_OF_COLUMNS(); i++) {
					if (board.insert(i, otherPlayerSymbol)) {
						if (board.containsWin()) {
							board.rollbackMove(i);
							board.insert(i, symbol);
							madeMove = true;
							System.out.print(this.name + " made a move: " + i);
							break;
						} else {
							board.rollbackMove(i);
						}
					}
				}
			}

			if (!board.containsWin() && madeMove == false) {
				Random random = new Random();
				int move = random.nextInt(board.getNUM_OF_COLUMNS()) + 1;
				while (!board.insert(move, symbol)) {
					move = random.nextInt(board.getNUM_OF_COLUMNS()) + 1;
				}
				System.out.print(this.name + " made a move: " + move);
			}
		}

	}

}
