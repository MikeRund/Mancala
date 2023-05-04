package mainmenu;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class UserData {
    private static final UserData INSTANCE = new UserData();
    private String username;
    private boolean loggedIn;
    private BooleanProperty loggedInProperty = new SimpleBooleanProperty(false);

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
}
