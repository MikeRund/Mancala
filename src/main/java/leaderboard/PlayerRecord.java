package leaderboard;

import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The PlayerRecord class responsible for managing the player record of players.
 * It provide a functionality to update the player record.
 */
public class PlayerRecord {

    private ArrayList<Player> playerRecord = new ArrayList<>();

    private SimpleStringProperty playerName;
    private SimpleIntegerProperty wins;
    private SimpleIntegerProperty losses;

    public PlayerRecord() {
    }

    public String getPlayerName() {
        return playerName.get();
    }

    public void setPlayerName(String playerName) {
        this.playerName.set(playerName);
    }

    public int getWins() {
        return wins.get();
    }

    public void setWins(int wins) {
        this.wins.set(wins);
    }

    public int getLosses() {
        return losses.get();
    }

    public void setLosses(int losses) {
        this.losses.set(losses);
    }

    public void updatePlayerRecord(Player player, int wins, int losses) {
        Player foundThePlayer = findThePlayer(player);
    
        if (foundThePlayer != null) {
            setPlayerStatistic(wins, losses, foundThePlayer);
        } else {
            Player newPlayer = new Player(player);
            setPlayerStatistic(wins, losses, newPlayer);
            addNewPlayerToPlayerRecord(newPlayer);
        }
    }

    public Player findThePlayer(Player player) {
        for (int i = 0; i < playerRecord.size(); i++) {
            if (playerRecord.get(i).getUsername().equals(player.getUsername())) {
                return playerRecord.get(i);
            }
        }
        return null;
    }

    public void setPlayerStatistic(int wins, int losses, Player player) {
        player.setWins(wins);
        player.setLosses(losses);
        player.setTotalGames(wins + losses);   
    }

    public void addNewPlayerToPlayerRecord(Player newPlayer) {
        playerRecord.add(newPlayer);
    }

    public ObservableList<Player> getPlayers() {
        return FXCollections.observableArrayList(playerRecord);
    }

    public ArrayList<Player> getPlayerRecord() {
        return playerRecord;
    }

    public boolean isPlayerFavourite(User user, String username) {
        ArrayList<User> favouritePlayers = user.getFavouritePlayerList();
        
        for (int i = 0; i < favouritePlayers.size(); i++) {
            User favouritePlayer = favouritePlayers.get(i);
            if (favouritePlayer.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }  
}
