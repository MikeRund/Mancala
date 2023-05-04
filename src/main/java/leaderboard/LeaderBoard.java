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

    public LeaderBoard(UltilityFunction ultilityFunction) {
        this.ultilityFunction = ultilityFunction;
    }

//    public void updateLeaderBoard(Player player, int wins, int losses) {
//        Player playerExist = findIfThePlayerExist(player);
//
//        if (playerExist != null) {
//            ultilityFunction.setPlayerStatistic(wins, losses, playerExist);
//        } else {
//            Player newPlayer = new Player(player);
//            ultilityFunction.setPlayerStatistic(wins, losses, newPlayer);
//            addNewPlayerToLeaderBoard(newPlayer);
//            updateRanks();
//        }
//    }

    public void updateLeaderBoard(Player player) {
        //Player playerExist = findIfThePlayerExist(player);
        int wins = player.getWins();
        int losses = player.getLosses();
        ultilityFunction.setPlayerStatistic(wins, losses, player);
        addNewPlayerToLeaderBoard(player);

//        if (playerExist != null) {
//            int wins = player.getWins();
//            int losses = player.getLosses();
//            ultilityFunction.setPlayerStatistic(wins, losses, playerExist);
//        } else {
//           System.out.println("Player does not exist");
//        }
    }
       
    public Player findIfThePlayerExist(Player player) {
        for (int i = 0; i < leaderBoard.size(); i++) {
            if (leaderBoard.get(i).getUsername().equals(player.getUsername())) {
                return leaderBoard.get(i);
            }
        }
        return null;
    }
    
    public void addNewPlayerToLeaderBoard(Player newPlayer) {
        leaderBoard.add(newPlayer);
    }

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
}
