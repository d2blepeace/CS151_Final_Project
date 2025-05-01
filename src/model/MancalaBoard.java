package model;

import java.util.Stack;

/**
 * Represents the Mancala game board, handling pits, mancalas, player turns, and undo functionality.
 */
public class MancalaBoard {
    //Player fields
    private Player playerA, playerB;
    // set player = this field to change turn
    private Player currentPlayer;
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
        pits = new Pit[14];
        for (int i = 0; i < 14; i++) {
            boolean isMancala = (i == 6 || i == 13);    //A Mancala = 6; B Mancala = 13
            Player owner = (i < 6 || i == 6) ? playerA : playerB;
            int stones = isMancala ? 0 : stonesPerPit;
            pits[i] = new Pit(stones, isMancala, owner, i);
        }
    }
    public boolean makeMove(int pitIndex) {

        return true;
    }

    private void switchPlayer() {
        if (currentPlayer == playerA) {
            currentPlayer = playerB;
        } else {
            currentPlayer = playerA;
        }
    }

    private int getMancalaIndex(Player currentPlayer) {
        return 0;
    }

    private void saveState() {
        int[] currentBoardState = new int[14];
        for (int i = 0; i < 14; i++) {
            currentBoardState[i] = pits[i].getStones();
        }
        boardHistory.push(currentBoardState);
        playerHistory.push(currentPlayer); // Save the current player state
        currentPlayer.incrementUndoCount();
    }

    public boolean canUndo() {
        //Condition of undo move
        return !boardHistory.isEmpty() && currentPlayer.getUndoCount() < MAX_UNDO_PER_TURN;
    }
    //pop from the stack to restore - up to 3 removes
    public void undo() {
        if (!canUndo()) return;

        int[] previous = boardHistory.pop();
        playerHistory.pop();

        for (int i = 0; i < 14; i++) {
            pits[i].setStones(previous[i]);
        }
        currentPlayer.incrementUndoCount();
        switchPlayer();
    }

    public boolean isGameOver() {
        //Check for win condition
        return true;
    }

    public Player getWinner() {
        return null;
    }
    public Pit getPit(int index) {
        return pits[index];
    }
}
