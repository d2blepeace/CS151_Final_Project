package controller;

import model.MancalaBoard;
import view.BoardPanel;
import view.StatusPanel;
import javax.swing.*;

public class GameController {
    private final MancalaBoard board;
    private final BoardPanel boardPanel;
    private final StatusPanel statusPanel;

    public GameController(MancalaBoard board, BoardPanel boardPanel, StatusPanel statusPanel) {
        this.board = board;
        this.boardPanel = boardPanel;
        this.statusPanel = statusPanel;
    }

    // Called when pit is clicked (View -> Controller -> Model)
    public void handlePitClick(int pitIndex) {
        if (board.makeMove(pitIndex)) { // Mutates model state
            updateUI();
            checkGameEnd();
        }
    }
    public boolean canUndo() {
        return board.canUndo();
    }

    // Called when undo button is clicked
    public void handleUndo() {
        if (board.canUndo()) {
            board.undo(); // Mutates model state
            updateUI();
        }
    }

    private void updateUI() {
        boardPanel.repaint();
        statusPanel.updateTurn(board.getCurrentPlayer());
    }

    private void checkGameEnd() {
        if (board.isGameOver()) {
            statusPanel.showWinner(board.getWinner());
            JOptionPane.showMessageDialog(boardPanel, "Game Over!");
        }
    }

    public void switchPlayer() {
        board.switchPlayer();                // now public on MancalaBoard
        boardPanel.repaint();
        statusPanel.updateTurn(board.getCurrentPlayer());
    }
}