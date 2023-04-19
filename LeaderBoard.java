import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LeaderBoard {

    public List<User> players;

    public LeaderBoard() {
        players = new ArrayList<>();
    }

    private void updateLeaderBoard(Player player) {
        boolean playerExists = findThePlayer(player);
    
        if (playerExists != true) {
            addPlayerToTheLeaderBoard(player);
        }
    
        // After updating the player information, you may want to sort the leaderboard.
        // Choose the appropriate sorting method, e.g., sort by wins or win percentage.
        sortLeaderBoardWinPercent();
    }
    
    private boolean findThePlayer(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getPlayer().equals(player)) {
                return true;
            }
        }
        return false;
    }
    
    private void addPlayerToTheLeaderBoard(Player player) {
        players.add(new User(player));
    }
    

    private void sortLeaderBoardWinPercent() {
        players.sort(Comparator.comparingDouble(User::getWinPercentage).reversed());
    }

    private void sortLeaderBoardWins() {
        players.sort(Comparator.comparingInt(User::getWins).reversed());
    }

    public void displayLeaderBoard(User user) {
        // Your logic for displaying the leaderboard for the input user.
    }

    private void markFavouriteUser(User user, User favourite) {
        user.addFavourite(favourite);
    }

    private void unmarkFavouriteUser(User user, User favourite) {
        user.removeFavourite(favourite);
    }
}
