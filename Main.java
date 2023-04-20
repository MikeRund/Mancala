public class Main {
    public static void main(String[] args) {
        // Create some sample players
        Player alice = new Player("Alice");
        Player bob = new Player("Bob");
        Player cat = new Player("Cat");
        Player dog = new Player("Dog");

        // Create a leaderboard
        LeaderBoard leaderBoard = new LeaderBoard();

        // Update the leaderboard with the sample players
        leaderBoard.updateLeaderBoard(alice, true);
        leaderBoard.updateLeaderBoard(bob, false);
        leaderBoard.updateLeaderBoard(cat, true);
        leaderBoard.updateLeaderBoard(dog, false);

        // Add more wins and games to players
        leaderBoard.updateLeaderBoard(alice, true);
        leaderBoard.updateLeaderBoard(alice, false);
        leaderBoard.updateLeaderBoard(bob, true);
        leaderBoard.updateLeaderBoard(bob, true);
        leaderBoard.updateLeaderBoard(cat, false);
        leaderBoard.updateLeaderBoard(cat, false);
        leaderBoard.updateLeaderBoard(dog, true);
        leaderBoard.updateLeaderBoard(dog, true);
        leaderBoard.updateLeaderBoard(alice, true);
        leaderBoard.updateLeaderBoard(alice, true);
        leaderBoard.updateLeaderBoard(alice, true);
        leaderBoard.updateLeaderBoard(alice, true);
        leaderBoard.updateLeaderBoard(alice, true);
        leaderBoard.updateLeaderBoard(alice, true);
        leaderBoard.updateLeaderBoard(alice, true);

        // Create a sample user to view the leaderboard
        User sampleAlice = new User(alice);
  
        // Mark some users as the player1's favourite
        User favUser2 = new User(bob);
        User favUser3 = new User(cat);
        leaderBoard.markFavouriteUser(sampleAlice, favUser2);
        leaderBoard.markFavouriteUser(sampleAlice, favUser3);
        
        System.out.println("Leaderboard sorted by win percentage:");
        // Display the leaderboard sorted by win percentage
        leaderBoard.sortLeaderBoardWinPercent();
        leaderBoard.displayLeaderBoard(sampleAlice);

        System.out.println("\nLeaderboard sorted by wins:");
        // Display the leaderboard sorted by wins
        leaderBoard.sortLeaderBoardWins();
        leaderBoard.displayLeaderBoard(sampleAlice);
    }
}
