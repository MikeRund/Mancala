package leaderboard;

import java.util.ArrayList;

/**
* The User class represents a user in the system, with a username. 
* The User has a list to contain their favorite players.
* It provides methods to set and retrieve the username of the user, 
* and adding and removing favorite players from their list.
*/
public class User {

    private String username;
    private ArrayList<User> favouritePlayer;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public User(Player player) {
        this.username = player.getUsername();
        this.favouritePlayer = new ArrayList<>();
    }

    public User(String selectedUsername) {
        this.username = selectedUsername;
    }

    public ArrayList<User> getFavouritePlayerList() {
        return favouritePlayer;
    }

    public void addFavourite(User favouriteUser) {
        favouritePlayer.add(favouriteUser);
    }

    public void removeFavourite(int favouritePlayerIndex) {
        favouritePlayer.remove(favouritePlayerIndex);
    }

    public int getFavouritePlayerIndex(Player player) {
        User user = new User(player);
        for (int i = 0; i < favouritePlayer.size(); i++) {
            if (favouritePlayer.get(i).equals(user)) {
                return i;
            }
        }
        return -1;
    }
}
