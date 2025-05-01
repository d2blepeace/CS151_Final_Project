package model;

import model.Player;

//	Represents a single pit (either normal pit or mancala)
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
    //Setters and getters
    public int getStones() {return stones;}
    public int getIndex() {return index;}
    public boolean isMancala() {return isMancala;}
    public Player player() {return player;}
    public void setStones(int stones){
        this.stones = stones;
    }
    // Use for single stone addition during a regular move
    public void addStone() {
        this.stones++;
    }
    // Use for capturing multiple stones
    public void addStones(int count) {
        this.stones += count;
    }
    public int removeAllStones() {
        int removed = stones;
        stones = 0;
        return removed;
    }
    public int getStoneCount() {
        return stones;
    }
}
