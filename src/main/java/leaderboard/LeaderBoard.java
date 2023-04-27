package leaderboard;

import java.util.ArrayList;

/**
 * The LeaderBoard class responsible for managing the leaderboard of players.
 * It provide functionalities to update and sort the leaderboard based
 * on different criterias, and handles the logic 
 * for marking and unmarking favorite players for a given user.
 */
public class LeaderBoard {

    private ArrayList<Player> leaderBoard = new ArrayList<>();

    public void updateLeaderBoard(Player player, int wins, int losses) {
        Player playerExist = findIfThePlayerExist(player);
    
        if (playerExist != null) {
            setPlayerStatistic(wins, losses, playerExist);
        } else {
            Player newPlayer = new Player(player);
            setPlayerStatistic(wins, losses, newPlayer);
            addNewPlayerToLeaderBoard(newPlayer);
            updateRanks();
        }
    }
       
    public Player findIfThePlayerExist(Player player) {
        for (int i = 0; i < leaderBoard.size(); i++) {
            if (leaderBoard.get(i).getUsername().equals(player.getUsername())) {
                return leaderBoard.get(i);
            }
        }
        return null;
    }

    public void setPlayerStatistic(int wins, int losses, Player player) {
        player.setWins(wins);
        player.setLosses(losses);
        player.setTotalGames(wins + losses);   
    }
    
    public void addNewPlayerToLeaderBoard(Player newPlayer) {
        leaderBoard.add(newPlayer);
    }
            
    public void sortLeaderBoardWinPercent() {
        // Process of comparing and swapping players
        for (int index = 0; index < leaderBoard.size() - 1; index++) {
            // Compare adjacent players and swap them if needed
            for (int playerIndex = 0; playerIndex < leaderBoard.size() - 1 - index; playerIndex++) {
                Player player1 = leaderBoard.get(playerIndex);
                Player player2 = leaderBoard.get(playerIndex + 1);
                comparePlayersWinPercent(playerIndex, player1, player2);
            }
        }
        updateRanks();
    }
    
    private void comparePlayersWinPercent(int playerIndex, Player player1, Player player2) {
        if (player1.getWinPercentage() < player2.getWinPercentage()) {
            // Swap the players if the player1 has a lower win percentage.
            leaderBoard.set(playerIndex, player2);
            leaderBoard.set(playerIndex + 1, player1);
        }
    }
    
    public void sortLeaderBoardWins() {
        // Process of comparing and swapping players
        for (int index = 0; index < leaderBoard.size() - 1; index++) {
            // Compare adjacent players and swap them if needed
            for (int playerIndex = 0; playerIndex < leaderBoard.size() - 1 - index; playerIndex++) {
                Player player1 = leaderBoard.get(playerIndex);
                Player player2 = leaderBoard.get(playerIndex + 1);
                comparePlayersWins(playerIndex, player1, player2);
            }
        }
        updateRanks();
    }

    private void comparePlayersWins(int playerIndex, Player player1, Player player2) {
        if (player1.getWins() < player2.getWins()) {
            // Swap the players if the player1 has lower wins.
            leaderBoard.set(playerIndex, player2);
            leaderBoard.set(playerIndex + 1, player1);
        }
    }

    public boolean isPlayerFavourite(User user, String username) {
        ArrayList<User> favouritePlayers = user.getFavouritePlayer();
        
        for (int i = 0; i < favouritePlayers.size(); i++) {
            User favPlayer = favouritePlayers.get(i);
            if (favPlayer.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    
    public void markFavouriteUser(User user, User favouriteUser) {
        user.addFavourite(favouriteUser);
    }

    public void unmarkFavouriteUser(User user, User favouriteUser) {
        user.removeFavourite(favouriteUser);
    }

    public ArrayList<Player> getLeaderBoard() {
        return leaderBoard;
    }

    public void updateRanks() {
        for (int i = 0; i < leaderBoard.size(); i++) {
            Player player = leaderBoard.get(i);
            player.setRank(i + 1);
        }
    }

    public ArrayList<String> getPlayerUsernameFromLeaderBoard() {
        ArrayList<String> playerUsernames = new ArrayList<>();
        for (Player player : leaderBoard) {
            playerUsernames.add(player.getUsername());
        }
        return playerUsernames;
    }

    public ArrayList<Player> getPlayerFromLeaderBoard() {
        ArrayList<Player> player = new ArrayList<>();
        for (int i = 0; i < leaderBoard.size(); i++) {
            player.get(i);
        }
        return player;
    }
}
