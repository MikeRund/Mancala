package gameboard;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class BoardController {
    //Now working with button clicks

    //Load buttons
    @FXML
    Node root;
    @FXML
    private Label playerTurnLabel;
    @FXML
    private Label goAgainLabel;
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
    private CountDownLatch latch;


    public void button1Clicked() {
        System.out.println("Button 1 clicked");
        holeIndex = 0;
        latch.countDown();
    }
    public void button2Clicked() {
        System.out.println("Button 2 clicked");
        holeIndex = 1;
        latch.countDown();
    }
    public void button3Clicked() {
        System.out.println("Button 3 clicked");
        holeIndex = 2;
        latch.countDown();

    }
    public void button4Clicked() {
        System.out.println("Button 4 clicked");
        holeIndex = 3;
        latch.countDown();
    }
    public void button5Clicked() {
        System.out.println("Button 5 clicked");
        holeIndex = 4;
        latch.countDown();
    }
    public void button6Clicked() {
        System.out.println("Button 6 clicked");
        holeIndex = 5;
        latch.countDown();
    }
    public void store1Clicked() {
        System.out.println("Store 1 clicked");
//        holeIndex = 6;
//        latch.countDown();
    }
    public void button7Clicked() {
        System.out.println("Button 7 clicked");
        holeIndex = 7;
        latch.countDown();
    }
    public void button8Clicked() {
        System.out.println("Button 8 clicked");
        holeIndex = 8;
        latch.countDown();
    }
    public void button9Clicked() {
        System.out.println("Button 9 clicked");
        holeIndex = 9;
        latch.countDown();
    }
    public void button10Clicked() {
        System.out.println("Button 10 clicked");
        holeIndex = 10;
        latch.countDown();
    }
    public void button11Clicked() {
        System.out.println("Button 11 clicked");
        holeIndex = 11;
        latch.countDown();
    }
    public void button12Clicked() {
        System.out.println("Button 12 clicked");
        holeIndex = 12;
        latch.countDown();
    }
    public void store2Clicked() {
        System.out.println("Store 2 clicked");
//        holeIndex = 13;
//        latch.countDown();
    }

    public void updateHoleUI(Game game) {
        int currentPlayer = game.getCurrentPlayer();
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
        playerTurnLabel.setText("It's Player " + currentPlayer + "'s turn");
    }

    public void startButtonClicked() {
        goAgainLabel.setText(null);
        startButton.setDisable(true);
        System.out.println("Start button clicked!");
        Game game = new Game();
        game.setPlayer1(1);
        game.setPlayer2(2);
        game.currentPlayer = game.generateStartingPlayer();
        int currentPlayer = game.currentPlayer;
        playerTurnLabel.setText("It's Player " + currentPlayer +"'s turn");
//        game.startGame();
        updateHoleUI(game);
        System.out.println("Game initialized!");
        startGameUI(game);
    }

    public void startGameUI(Game game) {

//        game.setPlayer1(1);
//        game.setPlayer2(2);
//        game.currentPlayer = game.generateStartingPlayer();
        int currentPlayer = game.currentPlayer;
        //Scanner in = new Scanner(System.in);

        // Create a new thread to run the game logic
        Thread gameThread = new Thread(() -> {
            //Game logic
            while(!game.isGameOver()){
                System.out.println("Player + " + currentPlayer +"'s turn");
                game.getBoard().displayBoardCommandLine();
                System.out.println("\n" + "PLayer " + game.currentPlayer + " choose a hole");
                //int holeIndex = in.nextInt();

//                Update board and GUI with move
//                disableOpponentsButtons(game);
//                getHoleIndex();
//                game.makeMove(holeIndex);
//                Platform.runLater(() ->  updateHoleUI(game));

                makeMoveUI(game);
//                Thread moveThread = new Thread(() -> makeMoveUI(game));
//                moveThread.start();
                game.switchPlayer();
            }

            Platform.runLater(game::getWinner);
            startButton.setDisable(false);
        });
        gameThread.start();
    }

    public void getHoleIndex() {
        //Uses a countdown latch with a 1 count to act as a simple on/off latch
        latch = new CountDownLatch(1);
        try {
            latch.await();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void disableButton(Button button, boolean disable) {
        Platform.runLater(() -> button.setDisable(disable));
    }

    //Method to ensure:
    //Player 1 chooses from holes 1 - 6
    //Player 2 chooses from holes 7 - 12
    public void disableOpponentsButtons(Game game) {
        int currentPLayer = game.getCurrentPlayer();
        if(currentPLayer == 1) {
            for(int i = 1; i <= 6; i++){
                Button button = (Button)root.lookup("#hole" + i);
                if(button.isDisabled()){
                    disableButton(button, false);
                }
            }
            for(int i = 7; i <= 12; i++){
                Button button = (Button)root.lookup("#hole" + i);
                if(!button.isDisabled()){
                    disableButton(button, true);
                }
            }
        } else if(currentPLayer == 2) {
            for(int i = 1; i <= 6; i++){
                Button button = (Button)root.lookup("#hole" + i);
                if(!button.isDisabled()){
                    disableButton(button, true);
                }
            }
            for(int i = 7; i <= 12; i++){
            Button button = (Button)root.lookup("#hole" + i);
                if(button.isDisabled()){
                    disableButton(button, false);
                }
            }
        }
    }

    public void makeMoveUI(Game game) {
        AtomicBoolean chooseAgain = new AtomicBoolean();
        AtomicBoolean pickAndGo = new AtomicBoolean();
        AtomicBoolean moveFinished = new AtomicBoolean(false);
        AtomicBoolean alertClosed = new AtomicBoolean();
        int currentplayer = game.getCurrentPlayer();

//        Thread moveThread = new Thread(() -> {

            while (!moveFinished.get()) {

                //Update board and GUI with move
                disableOpponentsButtons(game);
                getHoleIndex();
                game.makeMove(holeIndex);
                Platform.runLater(() -> updateHoleUI(game));
                System.out.println("Last hole is " + game.lastHoleIndex);

//                Check go again conditions
                chooseAgain.set(checkChooseAgain(game, game.lastHoleIndex));
                pickAndGo.set(checkPickAndGo(game, game.lastHoleIndex));

                while (chooseAgain.get() || pickAndGo.get()) {

                    if (chooseAgain.get()) {
                        System.out.println("Last hole is " + game.lastHoleIndex);
                        System.out.println("Player chooses again!");

                        Platform.runLater(() -> goAgainLabel.setText("Player " + currentplayer + " chooses again!"));
                        getHoleIndex();
                        game.makeMove(holeIndex);

                        game.getBoard().displayBoardCommandLine();
                        System.out.println("Now last hole is " + game.lastHoleIndex);
                        Platform.runLater(() -> updateHoleUI(game));
                        Platform.runLater(() -> goAgainLabel.setText(null));
                        chooseAgain.set(checkChooseAgain(game, game.lastHoleIndex));
                    }

                    if (pickAndGo.get()) {
                        int lastHole = game.lastHoleIndex;
                        System.out.println("Last hole is " + lastHole);
                        System.out.println("Move continues with pick ");

                        //Alert box pop out
                        alertClosed.set(false);
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("Continue from " + lastHole);
                            alert.showAndWait();
                            alertClosed.set(true);
                        });

                        while (!alertClosed.get()) {
                            //Wait for the alert box to close
                        }

                        game.makeMove(game.lastHoleIndex);


                        //This bit
//                        Platform.runLater(() -> goAgainLabel.setText("Move continues from last hole!"));
//                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
//                        pause.setOnFinished(e -> game.makeMove(holeIndex));
//                        pause.play();
//                        Platform.runLater(() -> goAgainLabel.setText(null));

                        Platform.runLater(() -> updateHoleUI(game));
                        game.getBoard().displayBoardCommandLine();
                        System.out.println("Now the last hole is " + game.lastHoleIndex);
                        pickAndGo.set(checkPickAndGo(game, game.lastHoleIndex));
                    }

                }
                moveFinished.set(true);
            }
//        });
//        moveThread.start();

    }

//    public void checkBothGoAgain(Game game, int holeIndex, boolean chooseAgain, boolean pickAndGo){
//        int player1 = game.getPlayer1();
//        int player2 = game.getPlayer2();
//        int currentPlayer = game.getCurrentPlayer();
//
//        if(player1 == currentPlayer && holeIndex == 6) {
//            chooseAgain = true;
//        } else if(player2 == currentPlayer && holeIndex == 13) {
//            chooseAgain = true;
//        } else {
//            chooseAgain = false;
//        }
//        if(game.getBoard().getAllHoles()[holeIndex].getPieces() != 0){
//            pickAndGo = true;
//        } else {
//            pickAndGo = false;
//        }
//
//    }

    public boolean checkChooseAgain(Game game, int holeIndex) {
        int player1 = game.getPlayer1();
        int player2 = game.getPlayer2();
        int currentPlayer = game.getCurrentPlayer();

        if(player1 == currentPlayer && holeIndex == 6) {
            return true;
        } else if(player2 == currentPlayer && holeIndex == 13) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPickAndGo(Game game, int holeIndex) {

        if(holeIndex == 6 || holeIndex == 13){
            return false;
        } else if(game.getBoard().getAllHoles()[holeIndex].getPieces() != 1){
            return true;
        }
        return false;
    }





}