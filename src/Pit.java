//This class represent the attributes of the Pit
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
    public void addStone() {
        this.stones++;
    }
    public int removeAllStones() {
        int removed = stones;
        stones = 0;
        return removed;
    }
}
