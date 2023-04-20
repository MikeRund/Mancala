import java.util.ArrayList;

/**
 * Displays the leaderboard, including rank, username, wins, win percentage, and favorite status.
 * Put a new player to the leaderboard.
 */
public class LeaderBoard {

    private ArrayList<Player> leaderBoard = new ArrayList<>();

    public void updateLeaderBoard(Player player, boolean hasWon) {

        Player playerExists;        
        Player foundThePlayer = findThePlayer(player);
    
        if (foundThePlayer != null) {
            playerExists = foundThePlayer;
            if (hasWon) {
                playerExists.incrementWins();
            }
            playerExists.incrementTotalGames();
        } else {
            Player newPlayer = new Player(player);
            if (hasWon) {
                newPlayer.incrementWins();
            }
            newPlayer.incrementTotalGames();
            addNewPlayerToLeaderBoard(newPlayer);
        }
    }
       
    public Player findThePlayer(Player player) {
        for (int i = 0; i < leaderBoard.size(); i++) {
            if (leaderBoard.get(i).getUsername().equals(player.getUsername())) {
                return leaderBoard.get(i);
            }
        }
        return null;
    }
    
    public void addNewPlayerToLeaderBoard(Player newPlayer) {
        leaderBoard.add(newPlayer);
    }
            
    public void sortLeaderBoardWinPercent() {
        // Process of comparing and swapping players
        for (int index = 0; index < leaderBoard.size() - 1; index++) {
            // Compare adjacent players and swap them if needed
            for (int playerIndex = 0; playerIndex < leaderBoard.size() - 1 - index; playerIndex++) {
                Player player1 = leaderBoard.get(playerIndex);
                Player player2 = leaderBoard.get(playerIndex + 1);
                comparePlayersWinPercent(playerIndex, player1, player2);
            }
        }
    }
    
    private void comparePlayersWinPercent(int playerIndex, Player player1, Player player2) {
        if (player1.getWinPercentage() < player2.getWinPercentage()) {
            // Swap the players if the player1 has a lower win percentage.
            leaderBoard.set(playerIndex, player2);
            leaderBoard.set(playerIndex + 1, player1);
        }
    }
    
    public void sortLeaderBoardWins() {
        // Process of comparing and swapping players
        for (int index = 0; index < leaderBoard.size() - 1; index++) {
            // Compare adjacent players and swap them if needed
            for (int playerIndex = 0; playerIndex < leaderBoard.size() - 1 - index; playerIndex++) {
                Player player1 = leaderBoard.get(playerIndex);
                Player player2 = leaderBoard.get(playerIndex + 1);
                comparePlayersWins(playerIndex, player1, player2);
            }
        }
    }

    private void comparePlayersWins(int playerIndex, Player player1, Player player2) {
        if (player1.getWins() < player2.getWins()) {
            // Swap the players if the player1 has lower wins.
            leaderBoard.set(playerIndex, player2);
            leaderBoard.set(playerIndex + 1, player1);
        }
    }
    
    private void displayLeaderBoard(User user, ArrayList<Player> sortedLeaderBoard, boolean showWinPercentage) {
        for (int i = 0; i < sortedLeaderBoard.size(); i++) {
            Player player = sortedLeaderBoard.get(i);
            String username = player.getUsername();
            int wins = player.getWins();
            double winPercentage = player.getWinPercentage();
    
            // Check if the player is a favorite of the input user
            boolean isFavourite = isPlayerFavourite(user, username);
            String favString;
            if (isFavourite) {
                favString = " fav";
            } else {
                favString = "    ";
            }
    
            if (showWinPercentage == true) {
                // Use the format method to create a formatted string for win percentage
                System.out.format("%-5d %-18s %-5.1f%% %-4s%n", (i + 1), username, winPercentage, favString);
            } else {
                // Use the format method to create a formatted string for wins
                System.out.format("%-5d %-18s %-5d %-4s%n", (i + 1), username, wins, favString);
            }
        }
    }
    
    private boolean isPlayerFavourite(User user, String username) {
        ArrayList<User> favouritePlayers = user.getFavouritePlayer();
        
        for (int i = 0; i < favouritePlayers.size(); i++) {
            User favPlayer = favouritePlayers.get(i);
            if (favPlayer.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

        public void displayLeaderBoardByWins(User user) {
        System.out.println("Leaderboard sorted by wins:");
        System.out.println("------ LEADERBOARD ------");
    
        // Use format method for header line
        System.out.format("%-5s %-18s %-6s %-4s%n", "Rank", "Username", "Wins", "Fav");
    
        // Sort the board based on wins
        sortLeaderBoardWins();
    
        displayLeaderBoard(user, leaderBoard, false);
    }
    
    public void displayLeaderBoardByWinPercentage(User user) {
        System.out.println("Leaderboard sorted by win percentage:");
        System.out.println("------ LEADERBOARD ------");
    
        // Use format method for header line
        System.out.format("%-5s %-18s %-7s %-4s%n", "Rank", "Username", "Win  %", "Fav");
    
        // Sort the leaderBoard based on win percentage
        sortLeaderBoardWinPercent();
    
        displayLeaderBoard(user, leaderBoard, true);
    }
    
    public void markFavouriteUser(User user, User favouriteUser) {
        user.addFavourite(favouriteUser);
    }

    public void unmarkFavouriteUser(User user, User favouriteUser) {
        user.removeFavourite(favouriteUser);
    }
}
