package leaderboard;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

/**
* The Player class represents a player in the system, with a username. 
* The Player has their wins, losses, total games, and ranks.
* It provides functionalities to get the username and other statistic of the player
*/
public class Player {
    private String username;
    private int totalGames;
    private int wins;
    private int losses;
    private int rank;

    /**
     * Constructs a new player with the given username and retrieves the player's information from the database.
     * If the user is not found in the database, it sets the total games, wins, and losses to 0 and displays an alert.
     *
     * @param username the username of the player to be constructed
     * @throws SQLException if an error occurs while accessing the database
     */
    public Player(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        this.username = username;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-video", "root", "rootpassword");
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found in database.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Cannot find user");
                alert.show();
                this.username = username;
                this.totalGames = 0;
                this.wins = 0;
                this.losses = 0;
            } else {
                while (resultSet.next()) {
                    System.out.println("Player " + username + " found!");
                    this.totalGames = resultSet.getInt("games");
                    this.wins = resultSet.getInt("wins");
                    this.losses = resultSet.getInt("losses");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Returns an ArrayList of all players in the database.
     *
     * @return an ArrayList of all players in the database
     * @throws SQLException if an error occurs while accessing the database
     */
    public static ArrayList<Player> loadAllPlayers() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Player> players = new ArrayList<>();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-video", "root", "rootpassword");
            preparedStatement = connection.prepareStatement("SELECT * FROM users");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String retrievedUsername = resultSet.getString("username");
                int retrievedGames = resultSet.getInt("games");
                int retrievedWins = resultSet.getInt("wins");
                int retrievedLosses = resultSet.getInt("losses");

                Player player = new Player(retrievedUsername);
                player.setTotalGames(retrievedGames);
                player.setWins(retrievedWins);
                player.setLosses(retrievedLosses);

                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return  players;
    }

//    public Player(String username) {
//        this.username = username;
//        this.totalGames = 0;
//        this.wins = 0;
//        this.losses = 0;
//    }

    /**
     * Creates a new Player object with the same statistics as the given Player.
     * @param player The Player to copy statistics from.
     */
    public Player(Player player) {
        this.username = player.username;
        this.totalGames = player.totalGames;
        this.wins = player.wins;
        this.losses = player.losses;
    }

    /**
     * Returns the username of the player.
     * @return The username of the player.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Calculates and returns the win percentage of the player.
     * @return The win percentage of the player.
     */
    public double getWinPercentage() {
        if (totalGames == 0) {
            return 0;
        }
        return ((double) wins / totalGames) * 100;
    }

    /**
     * Sets the number of wins for the player.
     * @param wins The new number of wins for the player.
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Returns the number of wins for the player.
     * @return The number of wins for the player.
     */
    public int getWins() {
        return wins;
    }

    /**
     * Sets the number of losses for the player.
     * @param losses The new number of losses for the player.
     */
    public void setLosses(int losses) {
        this.losses = losses;
    }

    /**
     * Returns the number of losses for the player.
     * @return The number of losses for the player.
     */
    public int getLosses(){
        return losses;
    }

    /**
     * Sets the total number of games played by the player.
     * @param totalGames The new total number of games played by the player.
     */
    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

//    public int getTotalGames() {
//        return totalGames;
//    }

    /**
     * Returns the rank of the player.
     * @return The rank of the player.
     */
    public int getRank() {
        return rank;
    }

    /**
     * Sets the rank of the player.
     * @param rank The new rank of the player.
     */
    public void setRank(int rank) {
        this.rank = rank;
    }
}