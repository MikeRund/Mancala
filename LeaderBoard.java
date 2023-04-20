import java.util.ArrayList;
import java.util.List;

/**
 * Displays the leaderboard, including rank, username, wins, win percentage, and favorite status.
 * Put a new player to the leaderboard.
 */
public class LeaderBoard {

    private List<Player> leaderBoard = new ArrayList<>();

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
        for (int i = 0; i < leaderBoard.size() - 1; i++) {
            // Compare adjacent players and swap them if needed
            for (int j = 0; j < leaderBoard.size() - 1 - i; j++) {
                Player player1 = leaderBoard.get(j);
                Player player2 = leaderBoard.get(j + 1);
                comparePlayersWinPercent(j, player1, player2);
            }
        }
    }
    
    private void comparePlayersWinPercent(int j, Player player1, Player player2) {
        if (player1.getWinPercentage() < player2.getWinPercentage()) {
            // Swap the players if the player1 has a lower win percentage.
            leaderBoard.set(j, player2);
            leaderBoard.set(j + 1, player1);
        }
    }
    
    public void sortLeaderBoardWins() {
        // Process of comparing and swapping players
        for (int i = 0; i < leaderBoard.size() - 1; i++) {
            // Compare adjacent players and swap them if needed
            for (int j = 0; j < leaderBoard.size() - 1 - i; j++) {
                Player player1 = leaderBoard.get(j);
                Player player2 = leaderBoard.get(j + 1);
                comparePlayersWins(j, player1, player2);
            }
        }
    }

    private void comparePlayersWins(int index, Player player1, Player player2) {
        if (player1.getWins() < player2.getWins()) {
            // Swap the players if the player1 has lower wins.
            leaderBoard.set(index, player2);
            leaderBoard.set(index + 1, player1);
        }
    }
    
    public void displayLeaderBoard(User user) {
        System.out.println("------ LEADERBOARD ------");

        // Use format method for headings
        System.out.format("%-5s %-18s %-5s %-7s %-5s%n"
        , "Rank", "Username", "Wins", "Win  %", "Fav");

        for (int i = 0; i < leaderBoard.size(); i++) {
            Player player = leaderBoard.get(i);
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

            // Use format method for columns
            System.out.format("%-5d %-18s %-5d %-5.1f%% %-4s%n"
            , (i + 1), username, wins, winPercentage, favString);
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
    
    public void markFavouriteUser(User user, User favouriteUser) {
        user.addFavourite(favouriteUser);
    }

    public void unmarkFavouriteUser(User user, User favouriteUser) {
        user.removeFavourite(favouriteUser);
    }
}
