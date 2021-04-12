package UI;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import Controllers.*;

/*
* Main
* This program will allow 2 local users to play the game Othello
* It has a fully traversable UI that has room for expansion ie: Online functionalities
* The game will keep track of the players and how many rounds they have won and such etc.
*
* @author Dayne Dellaire 100741322
* @author Chizurum Agomoth 100756924
* @author Nakil Jung 100803433
* @version 1.0
* @since 11/04/2021
 */
public class OthelloGameUI extends Application {
    private boolean gameOver = false;
    private Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        /*
        * start
        *
        * This method initializes the Ui and calls on the display
        * menu method to display the first menu to the user
        *
        * @param primaryStage this is the stage in which all scenes are displayed
         */
        primaryStage.setResizable(false);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        displayMenu(primaryStage);
    }

    public void displayConnectMenu(Stage primaryStage) {
        /*
        * displayConnectMenu
        *
        * This method will display the connect menu to the user. The connect menu
        * is non-functional but has been implemented for future versions with online
        * capabilities. In theory it would let the user connect to another hosting user
        *
        * @param primaryStage this is the stage in which all scenes are displayed
         */
        primaryStage.setTitle("Connect");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        Label playerLabel = new Label("Name: ");
        playerLabel.setFont(Font.font("Verdana", 14));
        TextField playerName = new TextField();

        Button btNext = new Button("Next");
        btNext.setPrefWidth(70);
        Button btReturn = new Button("Return");
        btReturn.setPrefWidth(70);

        grid.add(playerLabel, 0,0);
        grid.add(playerName, 1, 0);
        grid.add(btReturn, 0, 4);
        grid.add(btNext, 1, 4);

        Scene ConnectMenu = new Scene(grid, 300, 275);
        primaryStage.setScene(ConnectMenu);

        btReturn.setOnAction(actionEvent -> displayOnlineMenu(primaryStage));
        btNext.setOnAction(actionEvent_ -> {
            Label portLabel = new Label("Port: ");
            Label ipLabel = new Label("Ip: ");
            TextField port = new TextField();
            TextField ipAddress = new TextField();

            Text connectText = new Text("Connect");
            connectText.setFont(Font.font("Verdana", 14));

            Button btConnect = new Button("Connect");
            btConnect.setPrefWidth(70);

            grid.getChildren().remove(playerLabel);
            grid.getChildren().remove(playerName);

            grid.add(connectText, 0, 0);
            grid.add(portLabel, 0, 2);
            grid.add(ipLabel, 0, 1);
            grid.add(port, 1, 2);
            grid.add(ipAddress, 1, 1);

            GridPane.setHalignment(connectText, HPos.CENTER);

            btReturn.setOnAction(actionEvent -> displayConnectMenu(primaryStage));
            btConnect.setOnAction(actionEvent -> {
                if (port.getText().isEmpty() || ipAddress.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Port and Ip");
                    alert.setHeaderText(null);
                    alert.setContentText("Enter both a port and ip to connect to");
                    alert.showAndWait();
                } else {
                    //boolean error = false;
                    try {
                        System.out.println(port.getText());
                    } catch (NumberFormatException e) {
                        //error = true;

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Enter a Valid Port");
                        alert.setHeaderText(null);
                        alert.setContentText("Error: Input a valid port ie: 25565");
                        alert.showAndWait();
                    }

                    //if (!error) {
                        /*try {
                            //This is where the Networking code would go
                        } catch (ConnectException e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText(null);
                            alert.setContentText("Error connecting to ip: " + ipAddress.getText() + " on port: " + port.getText());
                            alert.showAndWait();
                        } catch (IllegalArgumentException e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText(null);
                            alert.setContentText("Error connecting to client: port out of range");
                            alert.showAndWait();
                        } catch (IOException e) {}
                    */
                    //}
                }
            });
        });
    }

    public void displayHostMenu(Stage primaryStage) {
        /*
         * displayHostMenu
         *
         * This method will display the host menu to the user. The host menu
         * is non-functional but has been implemented for future versions with online
         * capabilities. In theory it would let the user host a game of Othello and let
         * another user connect to them
         *
         * @param primaryStage this is the stage in which all scenes are displayed
         */
        primaryStage.setTitle("Host");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        Text hostText = new Text("Host");
        hostText.setFont(Font.font("Verdana", 14));

        Label portLabel = new Label("Port: ");
        TextField port = new TextField();

        Button btStart = new Button("Start");
        btStart.setPrefWidth(70);
        Button btReturn = new Button("Return");
        btReturn.setPrefWidth(70);

        grid.add(hostText, 0, 0);
        grid.add(portLabel, 0, 1);
        grid.add(port, 1, 1);
        grid.add(btStart, 0, 2);
        grid.add(btReturn, 1, 2);

        GridPane.setHalignment(hostText, HPos.CENTER);

        Scene hostScene = new Scene(grid, 300, 275);
        primaryStage.setScene(hostScene);

        btReturn.setOnAction(actionEvent -> displayOnlineMenu(primaryStage));
        btStart.setOnAction(actionEvent -> {
            if (port.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Port");
                alert.setHeaderText(null);
                alert.setContentText("Enter a Port number ie: 25565");
                alert.showAndWait();
            } else {
                boolean error = false;
                try {
                    System.out.println(port.getText());
                } catch (NumberFormatException e) {
                    error = true;

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Enter a Valid Port");
                    alert.setHeaderText(null);
                    alert.setContentText("Error: Input a valid port ie: 25565");
                    alert.showAndWait();
                }

                if (!error) {

                    hostText.setText("Starting");
                    grid.getChildren().remove(portLabel);
                    grid.getChildren().remove(port);
                    grid.getChildren().remove(btStart);
                    grid.getChildren().remove(btReturn);
                }
            }
        });
    }

    public void displayMenu(Stage primaryStage) {
        /*
         * displayMenu
         *
         * This method will display the first menu that the user is shown
         * it allows the user to select whether they would like to play locally
         * or online
         *
         * @param primaryStage this is the stage in which all scenes are displayed
         */
        primaryStage.setTitle("Othello By Group 7");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        Text gameText = new Text("Othello");
        Text groupText = new Text("By: Dayne Dellaire");
        gameText.setFont(Font.font("Verdana", 50));
        groupText.setFont(Font.font("Verdana", 14));

        Button btApp1 = new Button("Local");
        btApp1.setPrefWidth(200);
        Button btApp2 = new Button("Online");
        btApp2.setPrefWidth(200);

        grid.add(gameText, 0, 0);
        grid.add(groupText, 0, 1);
        grid.add(btApp1, 0, 3);
        grid.add(btApp2, 0, 4);

        GridPane.setHalignment(gameText, HPos.CENTER);
        GridPane.setHalignment(groupText, HPos.CENTER);

        mainScene = new Scene(grid, 300, 275);
        primaryStage.setScene(mainScene);
        primaryStage.show();

        //Displays the file transfer scree
        btApp1.setOnAction(actionEvent -> displayLocalMenu(primaryStage));
        btApp2.setOnAction(actionEvent -> displayOnlineMenu(primaryStage));
    }

    public void displayOnlineMenu(Stage primaryStage) {
        /*
         * displayOnlineMenu
         *
         * This method will display the menu after the user has clicked online from
         * the main menu. It has buttons that the user can select to choose whether
         * they would like to host a game or connect to one
         *
         * @param primaryStage this is the stage in which all scenes are displayed
         */
        primaryStage.setTitle("Online");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);

        Text onlineText = new Text("Online");
        onlineText.setFont(Font.font("Verdana", 50));

        Button btHost = new Button("Host");
        btHost.setPrefWidth(200);
        Button btConnect = new Button("Connect");
        btConnect.setPrefWidth(200);
        Button btReturn = new Button("Return");
        btReturn.setPrefWidth(200);

        grid.add(onlineText, 0, 0);
        grid.add(btHost, 0, 2);
        grid.add(btConnect, 0, 3);
        grid.add(btReturn, 0, 4);

        GridPane.setHalignment(onlineText, HPos.CENTER);

        Scene onlineMenu = new Scene(grid, 300, 275);
        primaryStage.setScene(onlineMenu);

        btHost.setOnAction(actionEvent -> displayHostMenu(primaryStage));
        btConnect.setOnAction(actionEvent -> displayConnectMenu(primaryStage));
        btReturn.setOnAction(actionEvent -> displayMenu(primaryStage));
    }

    public void displayLocalMenu(Stage primaryStage) {
        /*
         * displayLocalMenu
         *
         * This method will display the menu after the user has chosen local from the main menu
         * it has 2 fields in which the user will enter names for the first player and the second
         * if the user clicks on continue with 2 valid player names then it will create 2 player objects
         * and call on the method startOthello
         *
         * @param primaryStage this is the stage in which all scenes are displayed
         */
        primaryStage.setTitle("Local");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);

        Text localText = new Text("Local");
        localText.setFont(Font.font("Verdana", 14));

        Label player1Label = new Label("Player 1 Name: ");
        Label player2Label = new Label("Player 2 Name: ");
        TextField player1Name = new TextField();
        TextField player2Name = new TextField();

        Button btReturn = new Button("Return");
        btReturn.setPrefWidth(70);
        Button btContinue = new Button("Continue");
        btContinue.setPrefWidth(70);

        grid.add(localText, 0, 0);
        grid.add(player1Label, 0, 1);
        grid.add(player2Label, 0, 2);
        grid.add(player1Name, 1, 1);
        grid.add(player2Name, 1, 2);
        grid.add(btReturn, 0, 4);
        grid.add(btContinue, 1, 4);

        GridPane.setHalignment(localText, HPos.CENTER);

        Scene localMenu = new Scene(grid, 300, 275);
        primaryStage.setScene(localMenu);

        btReturn.setOnAction(actionEvent -> displayMenu(primaryStage));
        btContinue.setOnAction(actionEvent -> {
            if (player1Name.getText().isEmpty() || player2Name.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Enter Player Name");
                alert.setHeaderText(null);
                alert.setContentText("Each Player Must Have A Name");
                alert.showAndWait();
            } else {
                Player player1 = new Player(player1Name.getText(), 0, 'W');
                Player player2 = new Player(player2Name.getText(), 0, 'B');
                startOthello(player1, player2, primaryStage);
            }
        });
    }

    public void startOthello(Player player1, Player player2, Stage primaryStage) {
        /*
         * startOthello
         *
         * This method will initialize the scene for the game of Othello and then
         * call on the displayBoard method to start the game
         *
         * @param player1 the first player of the Othello game
         * @param player2 the second player of the Othello game
         * @param primaryStage this is the stage in which all scenes are displayed
         */
        OthelloController game = new OthelloController(player1, player2);

        Pane root = new Pane();
        root.setMaxHeight(800);
        root.setMaxWidth(800);

        Label text = new Label();
        text.setFont(Font.font("Verdana", 20));

        Label currentPlayer = new Label();
        currentPlayer.setFont(Font.font("Verdana", 20));

        HBox botHBox = new HBox();
        botHBox.getChildren().addAll(text);

        StackPane gameInfoPane = new StackPane();
        gameInfoPane.getChildren().addAll(text, currentPlayer);
        StackPane.setAlignment(text, Pos.CENTER_LEFT);
        StackPane.setAlignment(currentPlayer, Pos.CENTER_RIGHT);

        StackPane playerInfoPane = new StackPane();
        Text player1Info = new Text("   " + player1.getName());
        Text player2Info = new Text(player2.getName() + "   ");
        Text wins = new Text(player1.getWins() + " | " + player2.getWins());

        player1Info.setFont(Font.font("Verdana", 20));
        player2Info.setFont(Font.font("Verdana", 20));
        wins.setFont(Font.font("Verdana", 20));
        playerInfoPane.getChildren().addAll(player1Info, wins, player2Info);
        StackPane.setAlignment(player1Info, Pos.CENTER_LEFT);
        StackPane.setAlignment(player2Info, Pos.CENTER_RIGHT);
        StackPane.setAlignment(wins, Pos.CENTER);

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(playerInfoPane, root, gameInfoPane);

        Scene scene = new Scene(vBox, 800, 885);
        primaryStage.setTitle("Othello Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        playOthello(root, text, currentPlayer, game, vBox, primaryStage);
    }

    public String getCurrentPlayer(OthelloController game) {
        /*
         * getCurrentPlayer
         *
         * This method will take a current Othello game as input
         * and return a String that contains the current player name and
         * color
         *
         * @param OthelloGame game the current game of Othello
         * @return String contains the current player name and color
         */
        return "Current Player: " + game.getCurrentPlayer().getName() +
                "   Color: " + game.getCurrentPlayer().getColor() + "   ";
    }

    public void playOthello(Pane root, Label text, Label currentPlayer, OthelloController game, VBox Vbox, Stage primaryStage) {
        /*
         * playOthello
         *
         * this method controls the flow of the Othello game, it will display and update the UI
         * for the game to the user, when the game is over it will let the user click restart or return
         * back to the main menu
         *
         * @param Pane root, a 800x800 pane that prints the board tiles
         * @param Label text, an empty Label that updates with the current amount of pieces in the board
         * @param Label currentPlayer, an empty Label that updates with the current player name and color
         * @param OthelloGame game, a game of Othello
         * @param VBox Vbox, a VBox that contains all UI artifacts
         * @param primaryStage this is the stage in which all scenes are displayed
         */
        Rectangle background = new Rectangle(800, 800);
        background.setFill(Color.GREEN);
        root.getChildren().add(background);
        int[] pieceCount;
        Piece[][] temp;

        for (int i = 0; i < 8; i++) {
            pieceCount = game.getPieceCount();
            text.setText("White Pieces: " + pieceCount[0] + "     Black Pieces: " + pieceCount[1]);
            currentPlayer.setText(getCurrentPlayer(game));
            for (int j = 0; j < 8; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 100);
                tile.setTranslateY(i * 100);

                temp = game.getMoveLocations();

                if (game.getGameOver()) {
                    gameOver = true;
                }
                if (temp != null && temp[i][j] != null) {
                    if (temp[i][j].getColor() == 'W') {
                        tile.drawWhite();
                    } else if (temp[i][j].getColor() == 'B') {
                        tile.drawBlack();
                    } else if (temp[i][j].getColor() == 'R' && !gameOver) {
                        tile.drawClick();
                        tile.setOnMouseClicked(event -> {
                            if (event.getButton() == MouseButton.PRIMARY) {
                                System.out.println("Click! " + event.getSceneY() / 100 + " " + event.getSceneX() / 100);
                                game.playRound((int) (event.getSceneY() / 100 - 0.30), (int) (event.getSceneX() / 100));
                                playOthello(root, text, currentPlayer, game, Vbox, primaryStage);
                            }
                        });
                    }
                }
                root.getChildren().add(tile);
            }
        }
        if (gameOver) {
            Button btReturn = new Button("Return");
            btReturn.setPrefWidth(200);
            Button btRestart = new Button("Restart");
            btRestart.setPrefWidth(200);

            HBox hBox = new HBox();
            hBox.getChildren().addAll(btReturn, btRestart);
            hBox.setSpacing(15);
            Vbox.getChildren().add(hBox);

            game.addWinToPlayer();

            System.out.println("Game Over");
            gameOver = false;

            btReturn.setOnAction(actionEvent -> primaryStage.setScene(mainScene));
            btRestart.setOnAction(actionEvent -> startOthello(game.getPlayer1(), game.getPlayer2(), primaryStage));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}



