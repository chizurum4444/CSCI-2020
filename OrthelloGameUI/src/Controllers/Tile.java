package Controllers;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

/*
 * Tile
 *
 * This helper class contains all the data for creating a tile object,
 * each tile represents a piece on the board whether it be black, white, R
 * or empty
 *
 * @author Dayne Dellaire 100741322
 * @author Chizurum Agomoth 100756924
 * @author Nakil Jung 100803433
 * @version 1.0
 * @since 11/04/2021
 */
public class Tile extends StackPane {
    public Tile(){
        /*
         * Tile
         *
         * constructor that creates an empty tile space that has a black
         * border that is 100x100
         */
        Rectangle border = new Rectangle(100,100);
        border.setFill(null);
        border.setStroke(Color.BLACK);

        setAlignment(Pos.CENTER);
        getChildren().addAll(border);
    }

    public void drawWhite() {
        /*
         * drawWhite
         *
         * draws a white circle on the current tile piece
         */
        Circle whiteCircle = new Circle(30);
        whiteCircle.setFill(Color.WHITE);
        getChildren().addAll(whiteCircle);
    }

    public void drawBlack() {
        /*
         * drawWhite
         *
         * draws a black circle on the current tile piece
         */
        Circle blackCircle = new Circle(30);
        blackCircle.setFill(Color.BLACK);
        getChildren().addAll(blackCircle);
    }

    public void drawClick() {
        /*
         * drawClick
         *
         * draws a smaller red circle on the current tile piece
         */
        Circle redCircle = new Circle(20);
        redCircle.setFill(Color.RED);

        getChildren().addAll(redCircle);
    }
}
