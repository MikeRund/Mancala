package leaderboard;

import java.util.ArrayList;

import javafx.scene.control.TableCell;

/**
* The UltilityFunction class contains methods
* that various classes need to access.
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

    public void setPlayerStatistic(int wins, int losses, Player player) {
        player.setWins(wins);
        player.setLosses(losses);
        player.setTotalGames(wins + losses);   
    }

    public void updateIntegerCell(Integer item, boolean empty, TableCell<Player, Integer> cell) {
        if (empty || item == null) {
            cell.setText(null);
        } else {
            cell.setText(item.toString());
            cell.setStyle("-fx-alignment: CENTER;");
        }
    }

    public void updateStringCell(String item, boolean empty, TableCell<Player, String> cell) {
        if (empty || item == null) {
            cell.setText(null);
        } else {
            cell.setText(item.toString());
            cell.setStyle("-fx-alignment: CENTER;");
        }
    }

    public void updateDoubleCell(Double item, boolean empty, TableCell<Player, Double> cell) {
        if (empty || item == null) {
            cell.setText(null);
        } else {
            String formattedValue = String.format("%.2f", item);
            cell.setText(formattedValue);
            cell.setStyle("-fx-alignment: CENTER;");
        }
    }

    public void updateFavouriteCell(Boolean item, boolean empty, TableCell<Player, Boolean> cell, 
    User sampleUser, UltilityFunction ultilityFunction) {
        if (empty || item == null) {
            cell.setText(null);
        } else {
            Player currentPlayer = cell.getTableView().getItems().get(cell.getIndex());
            if (ultilityFunction.isPlayerFavourite(sampleUser, currentPlayer.getUsername())) {
                cell.setText("★");
                cell.setStyle("-fx-alignment: CENTER;");
            } else {
                cell.setText(null);
            }
        }
    }
}
