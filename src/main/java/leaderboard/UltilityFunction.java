package leaderboard;

import java.util.ArrayList;

import javafx.scene.control.TableCell;

/**
* The UltilityFunction class contains methods
* that various classes need to access.
*/
public class UltilityFunction {

    /**
     * Marks the given {@code favouriteUser} as a favourite player of the given {@code user}.
     *
     * @param user          the user whose favourite player list is being updated
     * @param favouriteUser the user being marked as a favourite player
     */
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

    /**
     * Removes the given {@code favouriteUser} from the favourite player list of the given {@code user}.
     *
     * @param user          the user whose favourite player list is being updated
     * @param favouriteUser the user being unmarked as a favourite player
     */
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

    /**
     * Determines if a player with the given {@code username} is a favourite player of the given {@code user}.
     *
     * @param user     the user whose favourite player list is being searched
     * @param username the username of the player being checked
     * @return {@code true} if the player is a favourite player of the user, {@code false} otherwise
     */
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

    /**
     * Sets the given {@code player}'s wins, losses, and total games played to the given values.
     *
     * @param wins   the number of wins to set
     * @param losses the number of losses to set
     * @param player the player whose statistics are being updated
     */
    public void setPlayerStatistic(int wins, int losses, Player player) {
        player.setWins(wins);
        player.setLosses(losses);
        player.setTotalGames(wins + losses);   
    }

    /**
     * Updates a table cell with an integer value.
     *
     * @param item   the integer value to set in the cell
     * @param empty  {@code true} if the cell is empty, {@code false} otherwise
     * @param cell   the cell being updated
     */
    public void updateIntegerCell(Integer item, boolean empty, TableCell<Player, Integer> cell) {
        if (empty || item == null) {
            cell.setText(null);
        } else {
            cell.setText(item.toString());
            cell.setStyle("-fx-alignment: CENTER;");
        }
    }

    /**
     * Updates a table cell with a string value.
     *
     * @param item   the string value to set in the cell
     * @param empty  {@code true} if the cell is empty, {@code false} otherwise
     * @param cell   the cell being updated
     */
    public void updateStringCell(String item, boolean empty, TableCell<Player, String> cell) {
        if (empty || item == null) {
            cell.setText(null);
        } else {
            cell.setText(item.toString());
            cell.setStyle("-fx-alignment: CENTER;");
        }
    }

    /**
     * Updates a table cell with a double value.
     *
     * @param item   the double value to set in the cell
     * @param empty  {@code true} if the cell is empty, {@code false} otherwise
     * @param cell   the cell being updated
     */
    public void updateDoubleCell(Double item, boolean empty, TableCell<Player, Double> cell) {
        if (empty || item == null) {
            cell.setText(null);
        } else {
            String formattedValue = String.format("%.2f", item);
            cell.setText(formattedValue);
            cell.setStyle("-fx-alignment: CENTER;");
        }
    }

    /**
     *Updates a table cell for displaying a favourite player.
     * If the user has marked the player as favourite, a star is displayed in the cell.
     * @param item the boolean value of whether the player is a favourite or not
     * @param empty a boolean value indicating whether the cell is empty or not
     * @param cell the table cell being updated
     * @param sampleUser the user who has marked the favourite players
     * @param ultilityFunction the utility function instance that provides the "isPlayerFavourite" method
     */
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
