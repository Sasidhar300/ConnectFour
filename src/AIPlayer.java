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
    }
}
