package Controllers;

/*
 * OthelloGame
 *
 * This class is used to create a game of Othello, it creates a 8x8 board and
 * takes 2 players at input and then a game of Othello can be played
 *
 * @author Dayne Dellaire 100741322
 * @author Chizurum Agomoth 100756924
 * @author Nakil Jung 100803433
 * @version 1.0
 * @since 11/04/2021
 */
public class OthelloController {

    private final Player[] player; //Array of players 1 and 2
    private Board board; //The Othello game board
    private int turn; //The current turn of the game (can either be 1 or 2)

    public OthelloController(Player player1, Player player2){
        /*
         * OthelloGame
         *
         * This constructor takes 2 inputs player 1 and player 2 and will make
         * the basic start of an Othello game, it will initialize an 8x8 board and
         * put the 4 starting pieces in it
         *
         * @param Player player1, the first player
         * @param Player player2, the second player
         */
        player = new Player[2];
        player[0] = player1;
        player[1] = player2;
        turn = (int)( Math.random() * 2 + 1) - 1;
        board = new Board(8,8);

        board.setPieceAt(3,3, new Piece('W'));
        board.setPieceAt(3,4, new Piece('B'));
        board.setPieceAt(4,3, new Piece('B'));
        board.setPieceAt(4,4, new Piece('W'));
    }

    public int[] getPieceCount(){ return board.countPieces(); }
    public Player getCurrentPlayer(){ return player[turn]; }
    public Player getPlayer1(){ return player[0]; }
    public Player getPlayer2(){ return player[1]; }

    public void addWinToPlayer(){
        /*
         * addWinToPlayer
         *
         * the method will get the current piece count, check which player
         * has more pieces and then add a win to that player that has more pieces
         */
        int[] pieceCount = getPieceCount();

        if (pieceCount[0] > pieceCount[1]) {
            player[0].addWin();
        } else if (pieceCount[1] > pieceCount[0]) {
            player[1].addWin();
        }
    }

    private void skipTurn(){
        /*
         * skipRound
         *
         * this method will skip a players turn
         * (changing the turn value)
         */
        if(turn == 0){
            turn = 1;
        } else if(turn == 1){
            turn = 0;
        }
    }

    public void playRound(int x, int y){
        /*
         * playRound
         *
         * this method duplicates what a normal game of Othello looks like
         * except it does not check if the current player move is valid, so
         * in order for the game to work x and y must already be valid player
         * moves
         *
         * @param int x, x coordinate in the board the piece should be placed
         * @param int y, y coordinate in the board the piece should be placed
         */
        System.out.println(player[turn].getName() + ": ");
        placePiece(x,y);
        skipTurn();
    }

    public boolean getGameOver(){
        /*
         * getGameOver
         *
         * This method will check if the Othello game is over or not
         *
         * @return boolean, true if a player can still make a move otherwise false
         */
        for(int i = 0; i < board.getLength(); i++){
            for(int j = 0; j < board.getLength(); j++){
                if(getPossibleMoves(i,j) || getPossibleMoves(i,j)){
                    return false;
                }
            }
        }
        return true;
    }

    private void placePiece(int x, int y){
        /*
         * placePiece
         *
         * This method will take an x and y coordinate and try to look for a complete Othello line
         * around it, in the case that it does it will switch all pieces that arn't the current players
         * color to the opposite color
         *
         * @param int x, x location for the piece
         * @param int y, y location for the piece
         */
        board.setPieceAt(x,y, new Piece(player[turn].getColor()));
        Direction[] possibleMove = new Direction[8];
        boolean hasPath = false;

        possibleMove[0] = new Direction(0, 1); //Look Right
        possibleMove[1] = new Direction(1, 1); //Look Right Bottom
        possibleMove[2] = new Direction(1, 0); //Look Down
        possibleMove[3] = new Direction(1, -1); //Look Down Left
        possibleMove[4] = new Direction(0, -1); //Look Left
        possibleMove[5] = new Direction(-1, -1); //Look Up Left
        possibleMove[6] = new Direction(-1, 0); //Look Up
        possibleMove[7] = new Direction(-1, 1); //Look Up Right

        for(Direction direction : possibleMove){
            int[][] tempBoard = new int[board.getRows()][board.getCols()];
            int x_pos = x;
            int y_pos = y;

            x_pos += direction.getDeltaX();
            y_pos += direction.getDeltaY();

            if(board.withinBounds(x_pos, y_pos) && board.getPieceAt(x_pos, y_pos) != null &&
                    board.getPieceAt(x_pos, y_pos).getColor() != player[turn].getColor()){
                tempBoard[x_pos][y_pos] = 1;
                x_pos += direction.getDeltaX();
                y_pos += direction.getDeltaY();

                while(board.withinBounds(x_pos, y_pos) && board.getPieceAt(x_pos,y_pos) != null &&
                        board.getPieceAt(x_pos,y_pos).getColor() != player[turn].getColor()){
                    tempBoard[x_pos][y_pos] = 1;
                    x_pos += direction.getDeltaX();
                    y_pos += direction.getDeltaY();
                }

                if(board.withinBounds(x_pos, y_pos) && board.getPieceAt(x_pos, y_pos) != null &&
                        board.getPieceAt(x_pos,y_pos).getColor() == player[turn].getColor()){
                    hasPath = true;
                }
            }

            if(hasPath){
                for(int i = 0; i < board.getLength(); i++){
                    for(int j = 0; j < board.getLength(); j++){
                        if(tempBoard[i][j] == 1){
                            board.swapPieceColor(i,j);
                        }
                    }
                }
                hasPath = false;
            }
        }
    }
    public Piece[][] getMoveLocations(){
        /*
         * getMoveLocations
         *
         * This method checks if the current player can make any moves around the board,
         * it then returns a board filled with White, Black and R pieces. R representing a
         * space in which the current player can make a move
         *
         * @return Piece[][], 2D array representing the game board that has extra pieces 'R' in
         * it representing a spot in which the current player can make a move
         */
        Piece[][] tempBoard = new Piece[8][8];
        boolean found = false;

        for(int i = 0; i < board.getLength(); i++){
            for(int j = 0; j < board.getLength(); j++){
                tempBoard[i][j] = board.getPieceAt(i,j);
                if(getPossibleMoves(i,j)){
                    tempBoard[i][j] = new Piece('R');
                    found = true;
                }
            }
        }

        if(!found){
            skipTurn();
            System.out.println("Skipped " + player[turn].getName() + " " + player[turn].getColor());
            return board.getBoard();
        }

        return tempBoard;
    }

    public boolean getPossibleMoves(int x, int y){
        /*
         * getPossibleMoves
         *
         * This method will check around a x and y position in a board if there is a possible
         * Othello move that can be made if there is return true else return false
         *
         * @param int x, x coordinate of the piece
         * @param iny y, y coordinate of the piece
         * @return boolean, true if a move can be made, false otherwise
         */
        boolean hasPath = false;
        Direction[] possibleMove = new Direction[8];

        if(!board.checkEmpty(x,y)){
            return false;
        }

        possibleMove[0] = new Direction(0, 1); //Look Right
        possibleMove[1] = new Direction(1, 1); //Look Right Bottom
        possibleMove[2] = new Direction(1, 0); //Look Down
        possibleMove[3] = new Direction(1, -1); //Look Down Left
        possibleMove[4] = new Direction(0, -1); //Look Left
        possibleMove[5] = new Direction(-1, -1); //Look Up Left
        possibleMove[6] = new Direction(-1, 0); //Look Up
        possibleMove[7] = new Direction(-1, 1); //Look Up Right

        for(Direction direction : possibleMove){
            int x_pos = x;
            int y_pos = y;
            x_pos += direction.getDeltaX();
            y_pos += direction.getDeltaY();

            if(board.withinBounds(x_pos, y_pos) && board.getPieceAt(x_pos, y_pos) != null
                    && board.getPieceAt(x_pos, y_pos).getColor() != player[turn].getColor()){
                x_pos += direction.getDeltaX();
                y_pos += direction.getDeltaY();

                while(board.withinBounds(x_pos, y_pos) && board.getPieceAt(x_pos,y_pos) != null &&
                        board.getPieceAt(x_pos,y_pos).getColor() != player[turn].getColor()){
                    x_pos += direction.getDeltaX();
                    y_pos += direction.getDeltaY();
                }

                if(board.withinBounds(x_pos, y_pos) && board.getPieceAt(x_pos, y_pos) != null &&
                        board.getPieceAt(x_pos,y_pos).getColor() == player[turn].getColor()){
                    hasPath = true;
                }
            }
        }
        return hasPath;
    }

    public String toString(){
        //Basic toString method
        String r = "";
        r += player[0].getName() + " " + player[0].getColor() + "\n" +
                player[1].getName() + " " + player[1].getColor() + "\n" + board;
        return r;
    }
/*
    public static void main(String[]args){
        Player player = new Player("Dayne", 0, 'W');
        Player player2 = new Player("Niko", 0, 'B');
        OrthelloGame game = new OrthelloGame(player, player2);

        game.printArray(game.getMoveLocations());
   }
 */
}
