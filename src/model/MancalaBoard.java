package model;

import java.util.Stack;

/**
 * Represents the Mancala game board, handling pits, mancalas, player turns, and undo functionality.
 */
public class MancalaBoard {
    //Player fields
    private Player playerA, playerB;
    private Player currentPlayer;       // set player = this field to change turn
    //Pits field and the index: 0-5 (Player A), 6 (A Mancala), 7-12 (Player B), 13 (B Mancala)
    private Pit[] pits;
    //Undo fields
    private Stack<int[]> boardHistory;
    private Stack<Player> playerHistory;
    private final int MAX_UNDO_PER_TURN = 3;

    //Constructor
    public MancalaBoard() {
        boardHistory = new Stack<>();
        playerHistory = new Stack<>();
        playerA = new Player("Player A", true);
        playerB = new Player("Player B", false);
        currentPlayer = playerA;
    }
    // initialize 6 pits per player + 1 mancala per player
    public void startBoard(int stonesPerPit) {
    }
    public boolean makeMove(int pitIndex) {
        return true;
    }
    public boolean canUndo() {
        //Condition of undo move
        return true;
    }
    //pop from the stack to restore - up to 3 removes
    public void undo() {

    }

    public boolean isGameOver() {
        //Check for win condition
        return true;
    }

    public Player getWinner() {

        return null;
    }

}
