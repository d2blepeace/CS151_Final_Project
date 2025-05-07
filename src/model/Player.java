package model;

/**
 * Thai Nguyen
 * Represents a player in the Mancala game, tracking their name, side (A or B),
 * and the number of undos they have used in the current turn.
 */

public class Player {
    private String name;
    private boolean isPlayerA;
    private int undoCount;

    /**
     * Constructs a new Player.
     *
     * @param name       the display name of the player
     * @param isPlayerA   if this player is Player A; false for Player B
     */
    public Player(String name, boolean isPlayerA) {
        this.name = name;
        this.isPlayerA = isPlayerA;
        this.undoCount = 0;
    }
    /**
     * Returns whether this player is Player A.
     *
     * @return true if Player A; false otherwise
     */
    public boolean isPlayerA() {
        return isPlayerA;
    }
    /**
     * Returns the display name of this player.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Resets this player's undo count to zero.
     * Should be called at the start of each new turn.
     */
    public void resetUndoCount() { undoCount = 0; }

}
