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
        
        // Display the leaderboard sorted by win percentage
        leaderBoard.displayLeaderBoardByWinPercentage(sampleAlice);

        // Display the leaderboard sorted by wins
        leaderBoard.displayLeaderBoardByWins(sampleAlice);
    }
}
