package leaderboard;

import javafx.application.Application;
import javafx.stage.Stage;

public class LeaderBoardMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create some sample players
        Player alice = new Player("Alice");
        Player bob = new Player("Bob");
        Player cat = new Player("Cat");
        Player dog = new Player("Dog");

    
        UltilityFunction ultilityFunction = new UltilityFunction();

        // Create a leaderboard
        LeaderBoard leaderBoard = new LeaderBoard(ultilityFunction);

        // Create a player record
        PlayerRecord playerRecord = new PlayerRecord(ultilityFunction);

        UltilityFunction utility = new UltilityFunction();

        // Update the leaderboard with the sample players
        leaderBoard.updateLeaderBoard(alice, 90, 60);
        leaderBoard.updateLeaderBoard(bob, 3, 0);
        leaderBoard.updateLeaderBoard(cat, 1, 2);
        leaderBoard.updateLeaderBoard(dog, 2, 2);

        // Update the player record with the sample players
        playerRecord.updatePlayerRecord(alice, 90, 60);
        playerRecord.updatePlayerRecord(bob, 3, 0);
        playerRecord.updatePlayerRecord(cat, 1, 2);
        playerRecord.updatePlayerRecord(dog, 2, 2);

        // Create a sample user to view the leaderboard
        User sampleAlice = new User(alice);

        // Mark some users as Alice's favourite
        User favUser2 = new User(bob);
        User favUser3 = new User(cat);
        utility.markFavouriteUser(sampleAlice, favUser2);
        utility.markFavouriteUser(sampleAlice, favUser3);

        PlayerRecordController playerRecordController = new PlayerRecordController(playerRecord, 
        sampleAlice, ultilityFunction);

        PlayerRecordGUI playerRecordGUI = new PlayerRecordGUI(playerRecordController);
        playerRecordGUI.start(primaryStage);

        LeaderBoardController leaderBoardController = new LeaderBoardController(leaderBoard, sampleAlice, 
        playerRecord, playerRecordGUI, ultilityFunction, playerRecordController);

        LeaderBoardGUI leaderBoardGUI = new LeaderBoardGUI(leaderBoardController);
        leaderBoardGUI.start(primaryStage);

        AddPlayerGUI addPlayerGUI = new AddPlayerGUI(leaderBoardController, leaderBoard, sampleAlice, 
        playerRecord, playerRecordController);

        addPlayerGUI.start(new Stage());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
