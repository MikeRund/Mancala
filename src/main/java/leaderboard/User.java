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

    /**
     * Sets the username of the user.
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the username of the user.
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Creates a new User object from a Player object.
     * @param player The Player object to create the User from.
     */
    public User(Player player) {
        this.username = player.getUsername();
        this.favouritePlayer = new ArrayList<>();
    }

    /**
     * Creates a new User object with the given username.
     * @param selectedUsername The username to set for the new User object.
     */
    public User(String selectedUsername) {
        this.username = selectedUsername;
    }

    /**
     * Returns the list of favourite players of the user.
     * @return The list of favourite players of the user.
     */
    public ArrayList<User> getFavouritePlayerList() {
        return favouritePlayer;
    }

    /**
     * Adds a player to the user's list of favourite players.
     * @param favouriteUser The player to add to the list of favourite players.
     */
    public void addFavourite(User favouriteUser) {
        favouritePlayer.add(favouriteUser);
    }

    /**
     * Removes a player from the user's list of favourite players.
     * @param favouritePlayerIndex The index of the player to remove from the list of favourite players.
     */
    public void removeFavourite(int favouritePlayerIndex) {
        favouritePlayer.remove(favouritePlayerIndex);
    }

    /**
     * Returns the index of the given player in the user's list of favourite players.
     * @param player The player to search for.
     * @return The index of the given player in the user's list of favourite players, or -1 if the player is not found.
     */
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
