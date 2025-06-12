package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameUI extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private String[][] board = new String[3][3];
    private GameLogic logic = new GameLogic();
    private JLabel status = new JLabel("Vez de: X");
    private int xWins = 0, oWins = 0;
    private JLabel score = new JLabel("Placar - X: 0 | O: 0");

    public GameUI() {
        setTitle("Jogo da Velha");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        // Painel do tabuleiro
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        boardPanel.setBackground(Color.DARK_GRAY);

        Font btnFont = new Font("Arial", Font.BOLD, 60);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton("");
                btn.setFont(btnFont);
                btn.setFocusPainted(false);
                btn.setBackground(new Color(50, 50, 50));
                btn.setForeground(Color.WHITE);

                int finalI = i;
                int finalJ = j;

                btn.addActionListener(e -> handleMove(finalI, finalJ, btn));

                buttons[i][j] = btn;
                boardPanel.add(btn);
            }
        }

        // Painel de status
        JPanel statusPanel = new JPanel(new GridLayout(2, 1));
        statusPanel.setBackground(new Color(30, 30, 30));
        status.setForeground(Color.WHITE);
        status.setHorizontalAlignment(SwingConstants.CENTER);
        score.setForeground(Color.LIGHT_GRAY);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        statusPanel.add(status);
        statusPanel.add(score);

        // Botão de reiniciar
        JButton resetButton = new JButton("Reiniciar");
        resetButton.setBackground(new Color(70, 130, 180));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.addActionListener(e -> resetGame());

        add(statusPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(resetButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void handleMove(int i, int j, JButton btn) {
        if (board[i][j] == null) {
            String player = logic.getCurrentPlayer();
            board[i][j] = player;
            btn.setText(player);
            btn.setForeground(player.equals("X") ? Color.CYAN : Color.PINK);

            if (logic.checkWin(board)) {
                status.setText("Vitória de: " + player);
                if (player.equals("X")) xWins++;
                else oWins++;
                updateScore();
                disableBoard();
            } else if (logic.isBoardFull(board)) {
                status.setText("Empate!");
            } else {
                logic.switchPlayer();
                status.setText("Vez de: " + logic.getCurrentPlayer());
            }
        }
    }

    private void disableBoard() {
        for (JButton[] row : buttons)
            for (JButton b : row)
                b.setEnabled(false);
    }

    private void updateScore() {
        score.setText("Placar - X: " + xWins + " | O: " + oWins);
    }

    private void resetGame() {
        board = new String[3][3];
        logic = new GameLogic();
        status.setText("Vez de: X");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
    }
}
