public class Winner {
    private char winner = '-';
    private StringBuffer board;

    public Winner(StringBuffer board) {
        this.board = board;
    }

    public void checkHorizontalWinner() {
        // check for horizontal winner
        for (int i = 0; i < 9; i += 3) {
            if (board.charAt(i) != '-' && board.charAt(i + 1) == board.charAt(i)
                    && board.charAt(i + 2) == board.charAt(i)) {
                board.charAt(i);
            }
        }
    }

    public void checkVerticalWinner() {
        // check for vertical winner
        for (int i = 0; i < 3; ++i) {
            if (board.charAt(i) != '-' && board.charAt(i + 3) == board.charAt(i)
                    && board.charAt(i + 6) == board.charAt(i)) {
                board.charAt(i);
            }
        }
    }

    public void checkDiagonalWinner() {
        if (board.charAt(0) != '-' && board.charAt(4) == board.charAt(0) && board.charAt(8) == board.charAt(0))
        {
            board.charAt(0);
        }
        if (board.charAt(2) != '-' && board.charAt(4) == board.charAt(2) && board.charAt(6) == board.charAt(2))
        {
            board.charAt(2);
        }
    }

    public char getWinner() {
        checkHorizontalWinner();
        checkVerticalWinner();
        checkDiagonalWinner();
        return winner;
    }
}
