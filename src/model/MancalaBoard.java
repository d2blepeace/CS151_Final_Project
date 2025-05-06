package model;

import java.util.Stack;

/**
 * Thai Nguyen
 * Represents the Mancala game board, handling pits, mancalas, player turns, and undo functionality.
 */
public class MancalaBoard {
    //Player fields
    private Player playerA, playerB;
    // set player = this field to change turn
    private Player currentPlayer;
    private int currentUndoCount = 0;  // Reset after switching player
    //Pits field and the index: 0-5 (Player A), 6 (A Mancala), 7-12 (Player B), 13 (B Mancala)
    private Pit[] pits;
    //Undo fields
    private Stack<int[]> boardHistory;
    private Stack<Player> playerHistory;
    private int undoCountThisTurn = 0;
    private boolean lastWasUndo = false;
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
            Player owner = (i < 6 || i == 6) ? playerA : playerB; // pit 6 = A, pit 13 = B
            int stones = isMancala ? 0 : stonesPerPit;
            pits[i] = new Pit(stones, isMancala, owner, i);
        }
    }
    public boolean makeMove(int pitIndex) {
        Pit selectedPit = pits[pitIndex];

        // Invalid move: empty pit or not current player's pit
        if (selectedPit.getStoneCount() == 0 || selectedPit.getPlayer() != currentPlayer || selectedPit.isMancala()) {
            return false;
        }

        saveState();
        // reset count at start of a real move
        undoCountThisTurn = 0;
        lastWasUndo      = false;
        currentPlayer.resetUndoCount();

        int stones = selectedPit.removeAllStones();
        int index = pitIndex;

        while (stones > 0) {
            index = (index + 1) % 14;

            // Skip opponent's mancala
            if (pits[index].isMancala() && pits[index].getPlayer() != currentPlayer) {
                continue;
            }

            pits[index].addStone();
            stones--;
        }

        // --- CAPTURE RULE ---
        Pit lastPit = pits[index];

        if (!lastPit.isMancala() &&
                lastPit.getStoneCount() == 1 &&
                lastPit.getPlayer() == currentPlayer) {

            int oppositeIndex = 12 - index;
            Pit oppositePit = pits[oppositeIndex];

            if (!oppositePit.isMancala() &&
                    oppositePit.getPlayer() != currentPlayer &&
                    oppositePit.getStoneCount() > 0) {

                int captured = oppositePit.removeAllStones() + lastPit.removeAllStones();
                pits[getMancalaIndex(currentPlayer)].addStones(captured);
            }
        }

        // --- FREE TURN RULE ---
        if (lastPit.isMancala() && lastPit.getPlayer() == currentPlayer) {
            // Free turn, don't switch
            return true;
        }

        // Otherwise, switch turn
        switchPlayer();
        return true;
    }

    public void switchPlayer() {
        if (currentPlayer == playerA) {
            currentPlayer = playerB;
        } else {
            currentPlayer = playerA;
        }
    }
    // Expose current player for UI
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private int getMancalaIndex(Player currentPlayer) {
        return (currentPlayer == playerA) ? 6 : 13;
    }

    private void saveState() {
        int[] currentState = new int[14];
        for (int i = 0; i < 14; i++) {
            currentState[i] = pits[i].getStoneCount();
        }
        boardHistory.push(currentState);
        playerHistory.push(currentPlayer);
        currentUndoCount++;
    }

    public boolean canUndo() {
        //Condition of undo move
        return  !lastWasUndo
                && undoCountThisTurn < MAX_UNDO_PER_TURN
                && !boardHistory.isEmpty();
    }
    //pop from the stack to restore - up to 3 removes
    public void undo() {
        if (!canUndo()) return;

        int[] previous = boardHistory.pop();
        currentPlayer = playerHistory.pop();

        for (int i = 0; i < pits.length; i++) {
            pits[i].setStones(previous[i]);
        }

        undoCountThisTurn++;
        lastWasUndo       = true;
    }

    public boolean isGameOver() {
        //Check for win condition: When one side's 6 pits are empty
        boolean aEmpty = true;
        boolean bEmpty = true;

        //Check if the indexes 0-6 or 7-13 is empty
        for (int i = 0; i < 6; i++) {
            if (pits[i].getStoneCount() > 0) aEmpty = false;
        }
        for (int i = 7; i < 13; i++) {
            if (pits[i].getStoneCount() > 0) bEmpty = false;
        }

        return aEmpty || bEmpty;
    }
    // Win condition: Count final mancala stones. Whoever have more stones in the mancala wins
    public Player getWinner() {
        if (!isGameOver()) return null;

        int scoreA = pits[6].getStoneCount();
        int scoreB = pits[13].getStoneCount();

        //Count remaining stones
        int aRemaining = 0;
        int bRemaining = 0;
        for (int i = 0; i < 6; i++) {
            aRemaining += pits[i].removeAllStones();
        }
        for (int i = 7; i < 13; i++) {
            bRemaining += pits[i].removeAllStones();
        }
        // Add to each player's Mancala
        pits[6].addStones(aRemaining);
        pits[13].addStones(bRemaining);

        //Count the score for each player
        scoreA += aRemaining;
        scoreB += bRemaining;

        // Display the winner
        if (scoreA > scoreB) {
            return playerA;
        }
        if (scoreB > scoreA) {
            return playerB;
        }
        return null; // tie
    }
    public Pit getPit(int index) {
        if (index < 0 || index >= 14) {
            throw new IndexOutOfBoundsException("Pit index must be 0–13");
        }
        return pits[index];
    }
}