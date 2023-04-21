package gameboard;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class BoardController {

    //Load buttons
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
    private Button startButton;
    private int holeIndex;


    public int button1Clicked() {
        System.out.println("Button 1 clicked");
        return 0;
    }
    public int button2Clicked() {
        System.out.println("Button 2 clicked");
        return 1;

    }
    public int button3Clicked() {
        System.out.println("Button 3 clicked");
        return 2;
    }
    public int button4Clicked() {
        System.out.println("Button 4 clicked");
        return 3;
    }
    public int button5Clicked() {
        System.out.println("Button 5 clicked");
        return 4;
    }
    public int button6Clicked() {
        System.out.println("Button 6 clicked");
        return 5;
    }
    public int store1Clicked() {
        System.out.println("Store 1 clicked");
        return 6;
    }
    public int button7Clicked() {
        System.out.println("Button 7 clicked");
        return 7;
    }
    public int button8Clicked() {
        System.out.println("Button 8 clicked");
        return 8;
    }public int button9Clicked() {
        System.out.println("Button 9 clicked");
        return 9;
    }
    public int button10Clicked() {
        System.out.println("Button 10 clicked");
        return 10;
    }
    public int button11Clicked() {
        System.out.println("Button 11 clicked");
        return 11;
    }
    public int button12Clicked() {
        System.out.println("Button 12 clicked");
        return 12;
    }
    public int store2Clicked() {
        System.out.println("Store 2 clicked");
        return 13;
    }

    public void updateHoleUI(Game game) {
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

    public void startButtonClicked() {
        startButton.setDisable(true);
        System.out.println("Start button clicked!");
        Game game = new Game();
//        game.startGame();
        updateHoleUI(game);
        System.out.println("Game initialized!");
        startGameUI(game);
    }

    public void startGameUI(Game game) {

        game.setPlayer1(1);
        game.setPlayer2(2);
        game.currentPlayer = game.getStartingPlayer();
        int currentPlayer = game.currentPlayer;
        Scanner in = new Scanner(System.in);

        // Create a new thread to run the game logic
        Thread gameThread = new Thread(() -> {
            //Game logic
            while(!game.isGameOver()){
                System.out.println("Player + " + currentPlayer +"'s turn");
                game.getBoard().displayBoardCommandLine();
                System.out.println("\n" + "PLayer " + game.currentPlayer + " choose a hole");
                int holeIndex = in.nextInt();

                //Update board and GUI with move
                game.makeMove(holeIndex);
                Platform.runLater(() ->  updateHoleUI(game));

                game.switchPlayer();
            }

            Platform.runLater(game::getWinner);
            startButton.setDisable(false);
        });

        gameThread.start();

        //Game logic
        //Will exist the while loop when all the pieces are out of a row
//        while(!game.isGameOver()) {
//            game.board.displayBoardCommandLine();
//            System.out.println("\n" + "PLayer " + game.currentPlayer + " choose a hole");
//            int holeIndex = in.nextInt();
//            game.makeMove(holeIndex);
//            game.switchPlayer();
//        }
//        game.getWinner();
    }


}
