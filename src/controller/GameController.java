package controller;

import model.MancalaBoard;
import view.BoardPanel;
import view.StatusPanel;
import javax.swing.*;

/**
 * Zain Khan, Thai Nguyen
 * GameController manages the interaction between the model (MancalaBoard),
 * and the view components (BoardPanel and StatusPanel).
 * It processes user actions like pit selection and undo, updates the model,
 * and triggers the appropriate view updates.
 */
public class GameController {
    private final MancalaBoard board;
    private final BoardPanel boardPanel;
    private final StatusPanel statusPanel;

    /**
     * Constructs a GameController with references to the game board and UI panels.
     *
     * @param board         the game model representing the Mancala board state
     * @param boardPanel    the visual panel for drawing pits and mancalas
     * @param statusPanel   the UI panel for displaying turn info and undo
     */
    public GameController(MancalaBoard board, BoardPanel boardPanel, StatusPanel statusPanel) {
        this.board = board;
        this.boardPanel = boardPanel;
        this.statusPanel = statusPanel;
    }

    /**
     * Handles a pit being clicked by the current player.
     * If the move is valid, updates the board and UI, and checks for game completion.
     *
     * @param pitIndex the index of the pit that was clicked (0–13)
     */
    public void handlePitClick(int pitIndex) {
        if (board.makeMove(pitIndex)) {
            updateUI();
            checkGameEnd();
        }
    }

    /**
     * Returns whether the current player is allowed to undo the last move.
     *
     * @return true if undo is permitted, false otherwise
     */
    public boolean canUndo() {
        return board.canUndo();
    }

    /**
     * Undoes the last move if permitted and updates the board and UI state.
     */
    public void handleUndo() {
        if (board.canUndo()) {
            board.undo();
            updateUI();
        }
    }

    /**
     * Refreshes the board panel and updates the turn display in the status panel.
     */
    private void updateUI() {
        boardPanel.repaint();
        statusPanel.updateTurn(board.getCurrentPlayer());
    }

    /**
     * Checks if the game is over and, if so, shows the winner and ends the game.
     */
    private void checkGameEnd() {
        if (board.isGameOver()) {
            statusPanel.showWinner(board.getWinner());
            JOptionPane.showMessageDialog(boardPanel, "Game Over!");
        }
    }

    /**
     * Switches the turn to the next player and updates the view accordingly.
     */
    public void switchPlayer() {
        board.switchPlayer();
        boardPanel.repaint();
        statusPanel.updateTurn(board.getCurrentPlayer());
    }
}