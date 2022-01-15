import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player {

	public HumanPlayer(char symbol, Board board, String name) {
		super(symbol, board, name);
	}

	@Override
	/*
	 * I was trying to implement my makeMove method in such a way that even though a player chooses ' ' as a
	 * symbol it enforces them to change it. That is the purpose of the else block.
	 * If the user inputs an invalid move, i.e the symbol is placed in a column that is already full,
	 * the use is prompted to enter another value *assuming inclusive of 1 and 7* until valid move is played. 
	 * All of the entries given by the player are strings which are converted to integers using Integer.parseInt()
	 * If the user does not enter a number as input then the player's turn is lost.(NumberFormatException)
	 * And the IO exception for when the InputStreamReader class is not able to read the user input. 
	 */
	public void makeMove(Board board) {

		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		int move;
		try {
			if (this.symbol != ' ') {
				System.out.print(this.name + ", please input your move: ");
				move = Integer.parseInt(bufferReader.readLine());
				
				while (!board.insert(move, symbol)) {
					System.out.print(this.name + ", please input valid move: ");
					move = Integer.parseInt(bufferReader.readLine());
				}
			} else {

				while (this.symbol == ' ') {
					System.out.print(this.name + " Please enter a valid symbol: ");
					this.symbol = bufferReader.readLine().charAt(0);
				}

				System.out.print(this.name + ", please input your move: ");
				move = Integer.parseInt(bufferReader.readLine());

				while (!board.insert(move, symbol)) {
					System.out.print(this.name + ", please input valid move: ");
					move = Integer.parseInt(bufferReader.readLine());

				}
			}

		} catch (NumberFormatException e) {
			System.out.println("Not a Number, you lost your turn!!");
		} catch (IOException e) {
			System.out.println("IOException");
		} 
	}
}

