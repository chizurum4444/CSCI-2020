package Controllers;

import java.util.Arrays;

/*
 * Board
 *
 * This is a generic Board class that stores white and black pieces. However the board could be
 * easily reconfigured to store all kinds of pieces
 *
 * @author Dayne Dellaire 100741322
 * @author Chizurum Agomoth 100756924
 * @author Nakil Jung 100803433
 * @version 1.0
 * @since 11/04/2021
 */
public class Board {
    private Piece[][] board;

    public Board(int r, int c){
        /*
         * Board
         *
         * basic constructor that will take rows and columns as inputs and create a new 2D array of pieces that
         * represent a game board
         *
         * @param int r, the numbers of rows in the board
         * @param int c, the numbers of columns in the board
         */
        board = new Piece[r][c];
        for (Piece[] pieces : board) { //i is row j is column
            Arrays.fill(pieces, null);
        }
    }

    public int getRows(){ return board.length; }
    public int getCols(){ return board[0].length; }
    public int getLength(){ return board.length; }
    public Piece getPieceAt(int x, int y){ return board[x][y]; }
    public Piece[][] getBoard(){ return board; }

    public void setPieceAt(int x, int y, Piece piece){board[x][y] = piece;}
    public void swapPieceColor(int x, int y){board[x][y].switchColor();}

    public int[] countPieces(){
        /*
         * countPieces
         *
         * This method will count the number of white and black pieces in the
         * game board
         *
         * @return int[], index 0 holds the number of white pieces
         * index 1 holds the number of black pieces
         */
        int[] count = new int[2];
        for (Piece[] pieces : board) {
            for (int j = 0; j < board.length; j++) {
                if (pieces[j] != null) {
                    if (pieces[j].getColor() == 'W') {
                        count[0] += 1;
                    } else if (pieces[j].getColor() == 'B') {
                        count[1] += 1;
                    }
                }
            }
        }
        return count;
    }

    public boolean withinBounds(int x, int y){
        /*
         * withinBounds
         *
         * checks if the current piece being placed is within the boards bounds
         *
         * @param int x, x coordinate to check if it  is in the board
         * @param int y, y coordinate to check if it is in the board
         * @return boolean, true if the x and y coordinates are in the boards bounds
         */
        return x < board.length && y < board[0].length && x >= 0 && y >= 0;
    }

    public boolean checkEmpty(int x, int y){
        /*
         * checkEmpty
         *
         * checks if a certain spot in the board has a piece or not
         *
         * @param int x, x coordinate to check
         * @param int y, y coordinate to check
         * @return boolean, true if the spot in the board is not empty otherwise false
         */
        return board[x][y] == null;
    }

    public String toString(){
        //Generic toString method
        String r = "  1 2 3 4 5 6 7 8\n";
        String curr;

        for(int i = 0; i < board.length; i++) { //i is row j is column
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == null){
                    curr = " ";
                } else {curr = board[i][j].toString();}

                if(j == 0) {
                    r += (i+1) + " " + curr;
                } else if(j == board[j].length - 1) {
                    r += " " + curr + " " + (i+1) + "\n";
                } else {r += " " + curr;}
            }
        }
        r += "  1 2 3 4 5 6 7 8";

        return r;
    }
}
