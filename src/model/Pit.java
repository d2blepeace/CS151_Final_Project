package model;


/**
 * Thai Nguyen
 * Represents a single pit on the Mancala board.
 * A pit may be a regular pit or a player's Mancala.
 * Each pit holds a number of stones and knows which player it belongs to.
 */
public class Pit {
    private int stones;
    private int index;
    private boolean isMancala;
    private Player player;

    //Constructor
    public Pit(int stones, boolean isMancala, Player player, int index) {
        this.stones = stones;
        this.isMancala = isMancala;
        this.player = player;
        this.index = index;
    }
    /**
     * Returns the current number of stones in this pit.
     *
     * @return the stone count
     */
    public int getStoneCount() {
        return stones;
    }

    /**
     * Checks if this pit is a Mancala.
     *
     * @return true if this pit is a Mancala
     */
    public boolean isMancala() {return isMancala;}

    /**
     * Returns the owner of this pit.
     *
     * @return the player who owns this pit
     */
    public Player getPlayer() {
        return player;
    }
    /**
     * Sets the number of stones in this pit directly (used for undo and initialization).
     *
     * @param stones the new stone count
     */
    public void setStones(int stones){
        this.stones = stones;
    }
    // Adds a single stone to this pit (used during sowing).

    public void addStone() {
        this.stones++;
    }
    /**
     * Adds multiple stones to this pit (used during capture).
     *
     * @param count the number of stones to add
     */
    public void addStones(int count) {
        this.stones += count;
    }
    // Removes all stones from this pit and returns the number removed
    public int removeAllStones() {
        int removed = stones;
        stones = 0;
        return removed;
    }


}
