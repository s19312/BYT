public class TicTacToe {
	public StringBuffer board;

	public TicTacToe(String s) {
		board = new StringBuffer(s);
	}

	public TicTacToe(String s, int position, char player) {
		board = new StringBuffer(s);
		board.setCharAt(position, player);
	}

	public int suggestMove(char player) {
		for (int i = 0; i < 9; i++) {
			if (board.charAt(i) == '-') {
				TicTacToe game = makeMove(i, player);
				if (game.board.charAt(0) == player)
					return i;
			}
		}

		for (int i = 0; i < 9; i++) {
			if (board.charAt(i) == '-')
				return i;
		}
		return -1;
	}

	public char winner() {
		return new Winner(board).getWinner();
	}
	public TicTacToe makeMove(int i, char player) {
		return new TicTacToe(board.toString(), i, player);
	}
}
