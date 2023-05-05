package leaderboard;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The LeaderBoard class responsible for managing the leaderboard of players.
 * It provide functionalities to update and sort the leaderboard based
 * on different criterias.
 */
public class LeaderBoard {

    private ArrayList<Player> leaderBoard = new ArrayList<>();

    private UltilityFunction ultilityFunction;

    /**
     * Constructor for creating a new leaderboard.
     */
    public LeaderBoard(UltilityFunction ultilityFunction) {
        this.ultilityFunction = ultilityFunction;
    }


//    public void updateLeaderBoard(Player player) {
//        //Player playerExist = findIfThePlayerExist(player);
//        int wins = player.getWins();
//        int losses = player.getLosses();
//        ultilityFunction.setPlayerStatistic(wins, losses, player);
//        addNewPlayerToLeaderBoard(player);

//        if (playerExist != null) {
//            int wins = player.getWins();
//            int losses = player.getLosses();
//            ultilityFunction.setPlayerStatistic(wins, losses, playerExist);
//        } else {
//           System.out.println("Player does not exist");
//        }
//    }
       
//    public Player findIfThePlayerExist(Player player) {
//        for (int i = 0; i < leaderBoard.size(); i++) {
//            if (leaderBoard.get(i).getUsername().equals(player.getUsername())) {
//                return leaderBoard.get(i);
//            }
//        }
//        return null;
//    }

    /**
     * Adds a new player to the leaderboard.
     * @param newPlayer The player to be added.
     */
    public void addNewPlayerToLeaderBoard(Player newPlayer) {
        leaderBoard.add(newPlayer);
    }

    /**
     * Adds all players to the leaderboard.
     * @throws SQLException If an error occurs while loading players from the database.
     */
    public void addALLPlayersLeaderBoard() throws SQLException {
        ArrayList<Player> players = null;
        try {
            players = Player.loadAllPlayers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < players.size(); i++) {
            addNewPlayerToLeaderBoard(players.get(i));
        }
    }

    /**
     * Sorts the leaderboard by win percentage.
     */
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

    /**
     * Helper method for comparing two players based on win percentage and swapping them if needed.
     * @param playerIndex The index of the first player in the leaderboard.
     * @param player1 The first player to be compared.
     * @param player2 The second player to be compared.
     */
    private void comparePlayersWinPercent(int playerIndex, Player player1, Player player2) {
        if (player1.getWinPercentage() < player2.getWinPercentage()) {
            // Swap the players if the player1 has a lower win percentage.
            leaderBoard.set(playerIndex, player2);
            leaderBoard.set(playerIndex + 1, player1);
        }
    }

    /**
     * Sorts the leaderboard by number of wins.
     */
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

    /**
     * Compares two players based on their number of wins and swaps them in the leaderboard if necessary.
     * @param playerIndex the index of the player to compare with the adjacent player.
     * @param player1 the first player to compare.
     * @param player2 the second player to compare.
     */
    private void comparePlayersWins(int playerIndex, Player player1, Player player2) {
        if (player1.getWins() < player2.getWins()) {
            // Swap the players if the player1 has lower wins.
            leaderBoard.set(playerIndex, player2);
            leaderBoard.set(playerIndex + 1, player1);
        }
    }

    /**
     * Returns the leaderboard as an ArrayList of Players.
     * @return the leaderboard as an ArrayList of Players.
     */
    public ArrayList<Player> getLeaderBoard() {
        return leaderBoard;
    }

    /**
     * Updates the ranks of the players in the leaderboard.
     * Each player is assigned a rank based on their position in the leaderboard.
     */
    public void updateRanks() {
        for (int i = 0; i < leaderBoard.size(); i++) {
            Player player = leaderBoard.get(i);
            player.setRank(i + 1);
        }
    }

//    public ArrayList<String> getPlayerUsernameFromLeaderBoard() {
//        ArrayList<String> playerUsernames = new ArrayList<>();
//        for (Player player : leaderBoard) {
//            playerUsernames.add(player.getUsername());
//        }
//        return playerUsernames;
//    }
}
