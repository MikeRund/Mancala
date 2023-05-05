package gameboard;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import leaderboard.Player;
import logIn.DBUtils;
import mainmenu.UserData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
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
    private ImageView image1 = new ImageView();
    @FXML
    private ImageView image2 = new ImageView();
    @FXML
    private ImageView image3 = new ImageView();
    @FXML
    private ImageView image4 = new ImageView();
    @FXML
    private ImageView image5 = new ImageView();
    @FXML
    private ImageView image6 = new ImageView();
    @FXML
    private ImageView image7 = new ImageView();
    @FXML
    private ImageView image8 = new ImageView();
    @FXML
    private ImageView image9 = new ImageView();
    @FXML
    private ImageView image10 = new ImageView();
    @FXML
    private ImageView image11 = new ImageView();
    @FXML
    private ImageView image12 = new ImageView();
    @FXML
    private ImageView store1Image = new ImageView();
    @FXML
    private ImageView store2Image = new ImageView();
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
    }

    public Image updateStoneImage(int pieces) {
        System.out.println("Before switch");
        System.out.println(System.getProperty("user.dir"));
        String imageName;
        switch (pieces) {
            case 0:
                return null;
            case 1:
                imageName = "./src/main/resources/images/1Stone.png";
                break;
            case 2:
                imageName = "./src/main/resources/images/2Stones.png";
                break;
            case 3:
                imageName = "./src/main/resources/images/3Stones.png";
                break;
            case 4:
                imageName = "./src/main/resources/images/4Stones.png";
                break;
            case 5:
                imageName = "./src/main/resources/images/5Stones.png";
                break;
            default:
                imageName = "./src/main/resources/images/6Stones.png";
                break;
//            default:
//                return null;
        }
        //System.out.println("Image name: " + imageName);
        try {
            FileInputStream inputStream = new FileInputStream(imageName);
//            Image stoneImage = new Image((getClass().getResourceAsStream(imageName)));
            Image stoneImage = new Image(inputStream);
//            if(pieces == 1) {
//                double scaleFactor = 0.5;
//                stoneImage = new Image(inputStream, 15, 15, false, false);
//            }
            return stoneImage;

        } catch (Exception e){
            System.out.println(e);
        } return null;
    }

    public void updateHoleUI(Game game)  {

        Hole[] holes = game.getBoard().getAllHoles();
        for(int i = 0; i < 6; i++){
            int holeIndex = i + 1;
            Button button = (Button)root.lookup("#hole" + holeIndex);
            button.setContentDisplay(ContentDisplay.CENTER);
            button.setText((Integer.toString(holes[i].getPieces())));
            ImageView image = (ImageView)root.lookup("#image" + holeIndex);
            image.setImage(updateStoneImage(holes[i].getPieces()));
        }
        for(int i = 7; i <= 12; i++){
            Button button = (Button)root.lookup("#hole" + i);
            button.setContentDisplay(ContentDisplay.CENTER);
            button.setText((Integer.toString(holes[i].getPieces())));
            ImageView image = (ImageView)root.lookup("#image" + i);
            image.setImage(updateStoneImage(holes[i].getPieces()));
        }
        int currentPlayer = game.getCurrentPlayer();

        store1.setText(Integer.toString(holes[6].getPieces()));
        store1Image.setImage(updateStoneImage(holes[6].getPieces()));

        store2.setText(Integer.toString(holes[13].getPieces()));
        store2Image.setImage(updateStoneImage(holes[13].getPieces()));
        playerTurnLabel.setText("It is " + currentPlayerLabel(game, game.currentPlayer) + "'s turn");
    }

    public void startButtonClicked() throws SQLException {
        goAgainLabel.setText(null);
        startButton.setDisable(true);
        System.out.println("Start button clicked!");

        Game game = new Game();
        String username = UserData.getInstance().getUsername();
        setGamePlayer(username, game);
        System.out.println("Game has player with name: " + game.getPlayer().getUsername());
        //DBUtils.updatePlayerStats(1,0);

        game.setPlayer1(1);
        game.setPlayer2(2);
        game.currentPlayer = game.generateStartingPlayer();
        int currentPlayer = game.currentPlayer;
        playerTurnLabel.setText("It's Player " + currentPlayerLabel(game, currentPlayer) +"'s turn");
        updateHoleUI(game);
        System.out.println("Game initialized!");

        startGameUI(game);
    }

    public String currentPlayerLabel(Game game, int currentPlayer) {
        if(currentPlayer == 1) {
            return game.getPlayer().getUsername();
        } else {
            return "opponent";
        }
    }

    public void setGamePlayer(String username, Game game) throws SQLException {
        try{
            Player player = new Player(username);
            game.setPlayer(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                System.out.println("It is + " + currentPlayerLabel(game, currentPlayer) +"'s turn");
                game.getBoard().displayBoardCommandLine();
                System.out.println("\n" + "Now " + currentPlayerLabel(game, currentPlayer) + " chooses a hole");

                makeMoveUI(game);
                game.switchPlayer();
                Platform.runLater(() -> updateHoleUI(game));
            }

            Platform.runLater(game::getWinner);
            Platform.runLater(() -> {
                updateWinnerScores(game.getWinner());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Player " + game.getWinner() + " wins!" );
                alert.showAndWait();
                //alertClosed.set(true);
            });
            startButton.setDisable(false);
        });
        gameThread.start();
    }

    public void updateWinnerScores(int playerNum){
        if(playerNum == 1) {
            DBUtils.updatePlayerStats(1, 0);
        } else {
            DBUtils.updatePlayerStats(0, 1);
        }
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
                int holeIndex = i + 1;
                Button button = (Button)root.lookup("#hole" + holeIndex);
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


    public void disableEmptyHoles(Game game){
        Hole[] holes = game.getBoard().getAllHoles();

        for(int i = 1; i <= 6; i++){
            Hole hole = holes[i];
            Button button = (Button)root.lookup("#hole" + i);

            if(hole.getPieces() == 0){
                button.setDisable(true);
            } else {
                button.setDisable(false);
            } 
        }

        for(int i = 7; i <= 12; i++){
            Hole hole = holes[i];
            int holeIndex = i + 1;
            Button button = (Button)root.lookup("#hole" + i);

            if(hole.getPieces() == 0){
                button.setDisable(true);
            } else {
                button.setDisable(false);
            }
        }
    }

    public void makeMoveUI(Game game) {
        AtomicBoolean chooseAgain = new AtomicBoolean();
        AtomicBoolean pickAndGo = new AtomicBoolean();
        AtomicBoolean moveFinished = new AtomicBoolean(false);
        AtomicBoolean alertClosed = new AtomicBoolean();
        int currentPlayer = game.getCurrentPlayer();

            while (!moveFinished.get()) {

                //Update board and GUI with move
                disableEmptyHoles(game);
                disableOpponentsButtons(game);
                getHoleIndex();
                game.makeMove(holeIndex);
                Platform.runLater(() -> updateHoleUI(game));
                System.out.println("Last hole is " + game.lastHoleIndex);

                //Check go again conditions
                chooseAgain.set(checkChooseAgain(game, game.lastHoleIndex));
                pickAndGo.set(checkPickAndGo(game, game.lastHoleIndex));

                while (chooseAgain.get() || pickAndGo.get()) {

                    if (chooseAgain.get()) {
                        System.out.println("Last hole is " + game.lastHoleIndex);
                        System.out.println("Player chooses again!");

                        Platform.runLater(() -> goAgainLabel.setText("Now " + currentPlayerLabel(game, currentPlayer) + " chooses again!"));
                        getHoleIndex();
                        game.makeMove(holeIndex);

                        game.getBoard().displayBoardCommandLine();
                        System.out.println("Now last hole is " + game.lastHoleIndex);
                        Platform.runLater(() -> updateHoleUI(game));
                        Platform.runLater(() -> goAgainLabel.setText(null));
                        chooseAgain.set(checkChooseAgain(game, game.lastHoleIndex));
                        pickAndGo.set(checkPickAndGo(game, game.lastHoleIndex));
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
                        Platform.runLater(() -> updateHoleUI(game));
                        game.getBoard().displayBoardCommandLine();
                        System.out.println("Now the last hole is " + game.lastHoleIndex);
                        pickAndGo.set(checkPickAndGo(game, game.lastHoleIndex));
                        chooseAgain.set(checkChooseAgain(game, game.lastHoleIndex));
                    }

                }
                moveFinished.set(true);
            }
//        });
//        moveThread.start();

    }

    public boolean checkChooseAgain(Game game, int holeIndex) {
        int player1 = game.getPlayer1();
        int player2 = game.getPlayer2();
        int currentPlayer = game.getCurrentPlayer();

        if(!game.isGameOver()) {
            if (player1 == currentPlayer && holeIndex == 6) {
                return true;
            } else if (player2 == currentPlayer && holeIndex == 13) {
                return true;
            } else {
                return false;
            }
        }
        return false;
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