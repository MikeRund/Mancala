/**

 * The BoardController class is responsible for managing the user interface of the Mancala game. It implements the
 * Initializable interface to be able to initialize JavaFX components when the user interface is loaded.
 * The class contains methods for updating the game board UI, updating the stone images, and handling button clicks.
 * @author [Mike Rundle]
 */

package gameboard;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import leaderboard.Player;
import logIn.DBUtils;
import mainmenu.UserData;
import java.io.FileInputStream;
import java.sql.SQLException;
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

    /**
     * Handles the button click event for hole 1.
     */
    public void button1Clicked() {
        System.out.println("Button 1 clicked");
        holeIndex = 0;
        latch.countDown();
    }

    /**
     * Handles the button click event for hole 2.
     */
    public void button2Clicked() {
        System.out.println("Button 2 clicked");
        holeIndex = 1;
        latch.countDown();
    }

    /**
     * Handles the button click event for hole 3.
     */
    public void button3Clicked() {
        System.out.println("Button 3 clicked");
        holeIndex = 2;
        latch.countDown();

    }

    /**
     * Handles the button click event for hole 4.
     */
    public void button4Clicked() {
        System.out.println("Button 4 clicked");
        holeIndex = 3;
        latch.countDown();
    }

    /**
     * Handles the button click event for hole 5.
     */
    public void button5Clicked() {
        System.out.println("Button 5 clicked");
        holeIndex = 4;
        latch.countDown();
    }

    /**
     * Handles the button click event for hole 6.
     */
    public void button6Clicked() {
        System.out.println("Button 6 clicked");
        holeIndex = 5;
        latch.countDown();
    }

    /**
     * Handles the button click event for store 1.
     */
    public void store1Clicked() {
        System.out.println("Store 1 clicked");
    }
    public void button7Clicked() {
        System.out.println("Button 7 clicked");
        holeIndex = 7;
        latch.countDown();
    }

    /**
     * Handles the button click event for hole 8.
     */
    public void button8Clicked() {
        System.out.println("Button 8 clicked");
        holeIndex = 8;
        latch.countDown();
    }

    /**
     * Handles the button click event for hole 9.
     */
    public void button9Clicked() {
        System.out.println("Button 9 clicked");
        holeIndex = 9;
        latch.countDown();
    }

    /**
     * Handles the button click event for hole 10.
     */
    public void button10Clicked() {
        System.out.println("Button 10 clicked");
        holeIndex = 10;
        latch.countDown();
    }

    /**
     * Handles the button click event for hole 11.
     */
    public void button11Clicked() {
        System.out.println("Button 11 clicked");
        holeIndex = 11;
        latch.countDown();
    }

    /**
     * Handles the button click event for hole 12.
     */
    public void button12Clicked() {
        System.out.println("Button 12 clicked");
        holeIndex = 12;
        latch.countDown();
    }

    /**
     * Handles the button click event for store 2.
     */
    public void store2Clicked() {
        System.out.println("Store 2 clicked");
    }

    /**
     * Returns an Image object with the specified number of stones.
     *
     * @param pieces the number of stones to display in the image
     * @return the Image object with the specified number of stones
     */
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
        }

        try {
            FileInputStream inputStream = new FileInputStream(imageName);
            Image stoneImage = new Image(inputStream);
            return stoneImage;

        } catch (Exception e){
            System.out.println(e);
        } return null;
    }

    /**
     * Updates the UI of the holes and stores on the game board with the current state of the game.
     *
     * @param game the current game
     */
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

    /**
     * Initializes a new game with a player and starts the game UI.
     *
     * @throws SQLException if there is an error with the database
     */
    public void startButtonClicked() throws SQLException {
        goAgainLabel.setText(null);
        startButton.setDisable(true);
        System.out.println("Start button clicked!");

        Game game = new Game();
        String username = UserData.getInstance().getUsername();
        setGamePlayer(username, game);
        System.out.println("Game has player with name: " + game.getPlayer().getUsername());

        game.setPlayer1(1);
        game.setPlayer2(2);
        game.currentPlayer = game.generateStartingPlayer();
        int currentPlayer = game.currentPlayer;
        playerTurnLabel.setText("It's Player " + currentPlayerLabel(game, currentPlayer) +"'s turn");
        updateHoleUI(game);
        System.out.println("Game initialized!");

        startGameUI(game);
    }

    /**
     * Returns a label string indicating the current player.
     *
     * @param game the current game
     * @param currentPlayer the current player
     * @return a label string indicating the current player
     */
    public String currentPlayerLabel(Game game, int currentPlayer) {
        if(currentPlayer == 1) {
            return game.getPlayer().getUsername();
        } else {
            return "opponent";
        }
    }

    /**
     * Sets the player of the current game to the player with the specified username.
     *
     * @param username the username of the player to set
     * @param game the current game
     * @throws SQLException if there is an error with the database
     */
    public void setGamePlayer(String username, Game game) throws SQLException {
        try{
            Player player = new Player(username);
            game.setPlayer(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the game UI and runs the game logic in a new thread.
     *
     * @param game the current game
     */
    public void startGameUI(Game game) {

        int currentPlayer = game.currentPlayer;
        Thread gameThread = new Thread(() -> {
            //Game logic
            while(!game.isGameOver()){
                makeMoveUI(game);
                game.switchPlayer();
                Platform.runLater(() -> updateHoleUI(game));
            }

            Platform.runLater(game::getWinner);
            Platform.runLater(() -> {
                updateWinnerScores(game.getWinner());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Player " + currentPlayerLabel(game, game.getWinner()) + " wins!" );
                alert.showAndWait();
            });
            startButton.setDisable(false);
        });
        gameThread.start();
    }

    /**
     * Updates the scores of the winner after a game has ended.
     * @param playerNum the number of the winning player
     */
    public void updateWinnerScores(int playerNum){
        if(playerNum == 1) {
            DBUtils.updatePlayerStats(1, 0);
        } else {
            DBUtils.updatePlayerStats(0, 1);
        }
    }

    /**
     * Blocks the current thread until the latch count reaches zero.
     */
    public void getHoleIndex() {
        //Uses a countdown latch with a 1 count to act as a simple on/off latch
        latch = new CountDownLatch(1);
        try {
            latch.await();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Disables a button in the JavaFX application thread.
     * @param button the Button to be disabled
     * @param disable the boolean value that determines whether or not the Button should be disabled
     */
    public void disableButton(Button button, boolean disable) {
        Platform.runLater(() -> button.setDisable(disable));
    }

    /**
     * Disables the Buttons of the player who is not currently making a move.
     * @param game the current Game being played
     */
    public void disableOpponentsButtons(Game game) {
        int currentPLayer = game.getCurrentPlayer();
        if(currentPLayer == 1) {
            for(int i = 0; i <=5; i++){
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


    /**
     * Allows the current player to make a move on the game board using the UI.
     *
     * @param game the game object representing the current game state
     */
    public void makeMoveUI(Game game) {
        AtomicBoolean chooseAgain = new AtomicBoolean();
        AtomicBoolean pickAndGo = new AtomicBoolean();
        AtomicBoolean moveFinished = new AtomicBoolean(false);
        AtomicBoolean alertClosed = new AtomicBoolean();
        int currentPlayer = game.getCurrentPlayer();

            while (!moveFinished.get()) {

                //Update board and GUI with move
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

                        pickAndGo.set(checkPickAndGo(game, game.lastHoleIndex));
                        chooseAgain.set(checkChooseAgain(game, game.lastHoleIndex));
                    }

                }
                moveFinished.set(true);
            }
    }

    /**
     * Checks if the current player gets to choose again after their move.
     *
     * @param game      the game object representing the current game state
     * @param holeIndex the index of the hole where the last piece was dropped
     * @return true if the current player gets to choose again, false otherwise
     */
    public boolean checkChooseAgain(Game game, int holeIndex) {
        int player1 = game.getPlayer1();
        int player2 = game.getPlayer2();
        int currentPlayer = game.getCurrentPlayer();

        if(!game.isGameOver()) {
            if (player1 == currentPlayer && holeIndex == 6) {
                return true;
            } else return player2 == currentPlayer && holeIndex == 13;
        }
        return false;
    }

    /**
     * This method checks if the current move made by the player can result in the player picking up all the pieces in the hole and continuing the move from the next hole.
     * @param game an instance of the Game class containing the current state of the game
     * @param holeIndex the index of the hole that the player last moved a piece into
     * @return true if the current move allows the player to pick up all the pieces in the hole and continue the move from the next hole, false otherwise
     */
    public boolean checkPickAndGo(Game game, int holeIndex) {

        if(holeIndex == 6 || holeIndex == 13){
            return false;
        } else return game.getBoard().getAllHoles()[holeIndex].getPieces() != 1;
    }
}