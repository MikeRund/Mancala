package leaderboard;

import java.util.ArrayList;

/**
* The UltilityFunction class contains methods
* that both LeaderBoard and PlayerRecord need to access.
* It provides functionalities to mark and unmark favourite players.
*/
public class UltilityFunction {

    public void markFavouriteUser(User user, User favouriteUser) {
        ArrayList<User> favouritePlayerList = user.getFavouritePlayerList();
        boolean alreadyMarked = false;
        int i;
    
        for (i = 0; i < favouritePlayerList.size() && !alreadyMarked; i++) {
            User favouritePlayer = favouritePlayerList.get(i);
            if (favouritePlayer.getUsername().equals(favouriteUser.getUsername())) {
                alreadyMarked = true;
            }
        }
    
        if (!alreadyMarked) {
            user.addFavourite(favouriteUser);
        }
    }
    
    public void unmarkFavouriteUser(User user, User favouriteUser) {
        ArrayList<User> favouritePlayers = user.getFavouritePlayerList();
        int favouritePlayerIndex = -1;
        boolean found = false;
        for (int i = 0; i < favouritePlayers.size() && !found; i++) {
            User favouritePlayer = favouritePlayers.get(i);
            if (favouritePlayer.getUsername().equals(favouriteUser.getUsername())) {
                favouritePlayerIndex = i;
                found = true;
            }
        }
        if (favouritePlayerIndex != -1) {
            user.removeFavourite(favouritePlayerIndex);
        }
    }
}
