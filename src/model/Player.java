package model;

//Contains information about player

public class Player {
    private String name;
    private boolean isPlayerA;
    private int undoCount;

    //Constructor
    public Player(String name, boolean isPlayerA) {
        this.name = name;
        this.isPlayerA = isPlayerA;
        this.undoCount = 0;
    }
    //Getters and setters
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getUndoCount() { return undoCount; }

    public void resetUndoCount() { undoCount = 0; }

    public void incrementUndoCount() { undoCount++; }
}
