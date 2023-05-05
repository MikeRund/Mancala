/**
 * The UserData class represents a user's data and provides access to the user's information.
 */
package mainmenu;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class UserData {
    private static final UserData INSTANCE = new UserData();
    private String username;
    private boolean loggedIn;
    private BooleanProperty loggedInProperty = new SimpleBooleanProperty(false);

    private int wins;
    private int losses;
    private int games;

    private UserData() {

    }
    /**
     * Returns the singleton instance of the UserData class.
     * @return the singleton instance of the UserData class.
     */
    public static UserData getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the user's username.
     * @return the user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     * @param username the user's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns whether the user is logged in.
     * @return true if the user is logged in, false otherwise.
     */
    public boolean getLoggedIn() {
        return loggedIn;
    }

    /**
     * Sets whether the user is logged in.
     * @param loggedIn true if the user is logged in, false otherwise.
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * Returns the value of the loggedInProperty.
     * @return true if the user is logged in, false otherwise.
     */
    public boolean isLoggedInProperty() {
        return loggedInProperty.get();
    }

    /**
     * Returns the loggedInProperty.
     * @return the loggedInProperty.
     */
    public BooleanProperty loggedInProperty() {
        return loggedInProperty;
    }

    /**
     * Sets the value of the loggedInProperty.
     * @param loggedInProperty true if the user is logged in, false otherwise.
     */
    public void setLoggedInProperty(boolean loggedInProperty) {
        this.loggedInProperty.set(loggedInProperty);
    }

    /**
     * Returns the user's number of wins.
     * @return the user's number of wins.
     */
    public int getWins() {
        return wins;
    }

    /**
     * Sets the user's number of wins.
     * @param wins the user's number of wins.
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Returns the user's number of losses.
     * @return the user's number of losses.
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Sets the user's number of losses.
     * @param losses the user's number of losses.
     */
    public void setLosses(int losses) {
        this.losses = losses;
    }

    /**
     * Returns the user's number of games played.
     * @return the user's number of games played.
     */
    public int getGames() {
        return games;
    }

    /**
     * Sets the user's number of games played.
     * @param games the user's number of games played.
     */
    public void setGames(int games) {
        this.games = games;
    }
}
