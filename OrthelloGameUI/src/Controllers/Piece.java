package Controllers;

/*
 * Piece
 *
 * This helper class represents a single piece of Othello
 *
 * @author Dayne Dellaire 100741322
 * @author Chizurum Agomoth 100756924
 * @author Nakil Jung 100803433
 * @version 1.0
 * @since 11/04/2021
 */
public class Piece {
    private char color; //The color of the piece

    public Piece(char color){
        this.color = color;
    }
    public char getColor(){return color;}

    public void switchColor(){
        /*
         * switchColor
         *
         * This method will check what the pieces current color is and
         * either change it to white or black accordingly
         */
        if(color == 'W'){
            color = 'B';
        } else if(color == 'B') {
            color = 'W';
        }
    }

    public String toString(){
        //Generic toString
        return color+"";
    }
}
