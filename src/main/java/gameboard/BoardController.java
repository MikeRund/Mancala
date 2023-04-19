package gameboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController {

    //Load buttons
    @FXML
    private Button startButton;

    @FXML
    private Button store1;
    @FXML
    private Button store2;
    @FXML
    private Button hole1;
    @FXML
    private Button hole2;
    @FXML
    private Button hole3;
    @FXML
    private Button hole4;
    @FXML
    private Button hole5;
    @FXML
    private Button hole6;
    @FXML
    private Button hole7;
    @FXML
    private Button hole8;
    @FXML
    private Button hole9;
    @FXML
    private Button hole10;
    @FXML
    private Button hole11;
    @FXML
    private Button hole12;
    @FXML
    private Label playerLabel;
    private Game game;

    public void startGame() {
        Game game = new Game();
        game.setPlayer1(1);
        game.setPlayer2(2);
        game.setCurrentPlayer(game.getStartingPlayer());

        Hole[] holes = game.getBoard().getAllHoles();
        int currentPlayer = game.getCurrentPlayer();
        String currentPlayerMsg = "It's Player " + currentPlayer + "'s turn";
        playerLabel.setText(currentPlayerMsg);

        while(!game.isGameOver()) {
            for(int i = 0; i < holes.length - 1; i++) {
                int holeIndex = i;
                Button holeButton = getButtonFromIndex(holeIndex);
                holeButton.setOnAction(e -> {
                    game.makeMove(holeIndex);
                    updateButtonLabels();
                    game.switchPlayer();
                    playerLabel.setText(currentPlayerMsg);
                });
            }
        }
        game.getWinner();
    }

    private Button getButtonFromIndex(int index) {
        switch(index) {
            case 0:
                return hole1;
            case 1:
                return hole2;
            case 2:
                return hole3;
            case 3:
                return hole4;
            case 4:
                return hole5;
            case 5:
                return hole6;
            case 6:
                return store1;
            case 7:
                return hole7;
            case 8:
                return hole8;
            case 9:
                return hole9;
            case 10:
                return hole10;
            case 11:
                return hole11;
            case 12:
                return hole12;
            case 13:
                return store2;
            default:
                return null;
        }
    }

    private void updateButtonLabels() {
        Hole[] holes = game.getBoard().getAllHoles();
        hole1.setText(Integer.toString(holes[0].getPieces()));
        hole2.setText(Integer.toString(holes[1].getPieces()));
        hole3.setText(Integer.toString(holes[2].getPieces()));
        hole4.setText(Integer.toString(holes[3].getPieces()));
        hole5.setText(Integer.toString(holes[4].getPieces()));
        hole6.setText(Integer.toString(holes[5].getPieces()));
        store1.setText(Integer.toString(holes[6].getPieces()));
        hole7.setText(Integer.toString(holes[7].getPieces()));
        hole8.setText(Integer.toString(holes[8].getPieces()));
        hole9.setText(Integer.toString(holes[9].getPieces()));
        hole10.setText(Integer.toString(holes[10].getPieces()));
        hole11.setText(Integer.toString(holes[11].getPieces()));
        hole12.setText(Integer.toString(holes[12].getPieces()));
        store2.setText(Integer.toString(holes[13].getPieces()));
    }


//    @FXML
//    public void initialize() {
//        Game game = new Game();
//        Hole[] holes = game.getBoard().getAllHoles();
//
//        //Set start button
//        startButton.setText("Start game");
//        startButton.setOnAction( e -> startGame());
//
//        //Set player label
//        int currentPlayer = game.getCurrentPlayer();
//        String currentPlayerMsg = "It's Player " + currentPlayer + "'s turn";
//        playerLabel.setText(currentPlayerMsg);
//
//        //Set text of each button to number pf pieces in hole
//        hole1.setText(Integer.toString(holes[0].getPieces()));
//        hole2.setText(Integer.toString(holes[1].getPieces()));
//        hole3.setText(Integer.toString(holes[2].getPieces()));
//        hole4.setText(Integer.toString(holes[3].getPieces()));
//        hole5.setText(Integer.toString(holes[4].getPieces()));
//        hole6.setText(Integer.toString(holes[5].getPieces()));
//        store1.setText(Integer.toString(holes[6].getPieces()));
//        hole7.setText(Integer.toString(holes[7].getPieces()));
//        hole8.setText(Integer.toString(holes[8].getPieces()));
//        hole9.setText(Integer.toString(holes[9].getPieces()));
//        hole10.setText(Integer.toString(holes[10].getPieces()));
//        hole11.setText(Integer.toString(holes[11].getPieces()));
//        hole12.setText(Integer.toString(holes[12].getPieces()));
//        store2.setText(Integer.toString(holes[13].getPieces()));
//
//        //Link buttons to corresponding holes
//        hole1.setOnAction(e -> game.makeMove(0));
//        hole2.setOnAction(e -> game.makeMove(1));
//        hole3.setOnAction(e -> game.makeMove(2));
//        hole4.setOnAction(e -> game.makeMove(3));
//        hole5.setOnAction(e -> game.makeMove(4));
//        hole6.setOnAction(e -> game.makeMove(5));
//        hole7.setOnAction(e -> game.makeMove(7));
//        hole8.setOnAction(e -> game.makeMove(8));
//        hole9.setOnAction(e -> game.makeMove(9));
//        hole10.setOnAction(e -> game.makeMove(10));
//        hole11.setOnAction(e -> game.makeMove(11));
//        hole12.setOnAction(e -> game.makeMove(12));
//    }

}
