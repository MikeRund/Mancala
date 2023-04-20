import java.util.ArrayList;

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

    public ArrayList<User> getFavouritePlayer() {
        return favouritePlayer;
    }

    public void addFavourite(User user) {
        favouritePlayer.add(user);
    }

    public void removeFavourite(User user) {
        favouritePlayer.remove(user);
    }
}
