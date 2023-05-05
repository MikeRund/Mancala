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
    public static UserData getInstance() {
        return INSTANCE;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedInProperty() {
        return loggedInProperty.get();
    }

    public BooleanProperty loggedInProperty() {
        return loggedInProperty;
    }

    public void setLoggedInProperty(boolean loggedInProperty) {
        this.loggedInProperty.set(loggedInProperty);
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }
}
