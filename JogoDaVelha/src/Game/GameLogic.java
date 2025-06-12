package Game;

public class GameLogic {
    private String currentPlayer = "X";
    
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    public boolean checkWin(String[][] board) {
        // Linhas, colunas e diagonais
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]))
                return true;
            if (board[0][i] != null && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]))
                return true;
        }
        if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]))
            return true;
        if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))
            return true;
        
        return false;
    }

    public boolean isBoardFull(String[][] board) {
        for (String[] row : board)
            for (String cell : row)
                if (cell == null)
                    return false;
        return true;
    }
}
