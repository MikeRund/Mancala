import java.util.ArrayList;
import java.util.List;

public class LeaderBoard {

    private List<User> players = new ArrayList<>();

    public void updateLeaderBoard(Player player) {

        boolean playerExists;        
        boolean foundThePlayer = findThePlayer(player);
    
        if (foundThePlayer == true) {
            playerExists = true;
        } else {
            playerExists = false;
        }
    
        if (playerExists == false) {
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
    
    public void updatePlayersInLeaderBoard(List<Player> playersToUpdate) {
        for (Player player : playersToUpdate) {
            updateLeaderBoard(player);
        }
    }
    
    private void sortLeaderBoardWinPercent() {
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = 0; j < players.size() - 1 - i; j++) {
                if (players.get(j).getWinPercentage() < players.get(j + 1).getWinPercentage()) {
                    User temp = players.get(j);
                    players.set(j, players.get(j + 1));
                    players.set(j + 1, temp);
                }
            }
        }
    }
    
    private void sortLeaderBoardWins() {
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = 0; j < players.size() - 1 - i; j++) {
                if (players.get(j).getWins() < players.get(j + 1).getWins()) {
                    User temp = players.get(j);
                    players.set(j, players.get(j + 1));
                    players.set(j + 1, temp);
                }
            }
        }
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
