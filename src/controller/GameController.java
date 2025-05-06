package controller;

import model.MancalaBoard;
import model.Player;
import view.BoardPanel;
import view.StatusPanel;

public class GameController {
    private MancalaBoard mancalaBoard;
    private BoardPanel boardPanel;
    private StatusPanel statusPanel;
    //Controller
    public GameController(MancalaBoard mancalaBoard, BoardPanel boardPanel, StatusPanel statusPanel) {
        this.mancalaBoard = mancalaBoard;
        this.boardPanel = boardPanel;
        this.statusPanel = statusPanel;

        //Allow BoardPanel notify controller when a pit is selected by clicking
        boardPanel.setClickHandler(this::handlePitClick);
        updateTurnDisplay();
    }

    private void handlePitClick(int pitIndex) {
        if (mancalaBoard.makeMove(pitIndex)) {
            boardPanel.repaint();
            if (mancalaBoard.isGameOver()) {
                statusPanel.showWinner(mancalaBoard.getWinner());
            } else {
                updateTurnDisplay();
            }
        }
    }
    public void undoMove() {
        mancalaBoard.undo();
        boardPanel.repaint();
        updateTurnDisplay();
    }
    private void updateTurnDisplay() {
        statusPanel.updateTurn(mancalaBoard.getCurrentPlayer());
    }
}
