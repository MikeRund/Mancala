package leaderboard;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create some sample players
        Player alice = new Player("Alice");
        Player bob = new Player("Bob");
        Player cat = new Player("Cat");
        Player dog = new Player("Dog");

        // Create a leaderboard
        LeaderBoard leaderBoard = new LeaderBoard();

        // Update the leaderboard with the sample players
        leaderBoard.updateLeaderBoard(alice, 90, 60);
        leaderBoard.updateLeaderBoard(bob, 3, 0);
        leaderBoard.updateLeaderBoard(cat, 1, 2);
        leaderBoard.updateLeaderBoard(dog, 2, 2);

        // Create a sample user to view the leaderboard
        User sampleAlice = new User(alice);

        // Mark some users as Alice's favourite
        User favUser2 = new User(bob);
        User favUser3 = new User(cat);
        leaderBoard.markFavouriteUser(sampleAlice, favUser2);
        leaderBoard.markFavouriteUser(sampleAlice, favUser3);

        // Create an instance of LeaderboardGUI and launch the application
        LeaderboardGUI leaderboardGUI = new LeaderboardGUI(leaderBoard, sampleAlice);
        leaderboardGUI.start(primaryStage);
        // Create an instance of AddPlayerGUI and launch the application
        AddPlayerGUI addPlayerGUI = new AddPlayerGUI(leaderboardGUI, leaderBoard, sampleAlice);
        addPlayerGUI.start(new Stage());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
