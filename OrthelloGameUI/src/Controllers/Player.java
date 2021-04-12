package Controllers;

public class Player {
    private final String NAME;
    private final char  COLOR;
    private int wins;


    public Player(String name, int wins, char color) {
        NAME = name;
        COLOR = color;
        this.wins = wins;

    }

    public String getName(){ return NAME; }
    public char getColor(){ return COLOR; }
    public int getWins(){ return wins; }
    public void addWin(){ wins++; }
}
