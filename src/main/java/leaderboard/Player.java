package leaderboard;

import javafx.scene.control.Alert;

import java.sql.*;

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

//    public Player(String username) {
//        this.username = username;
//        this.totalGames = 0;
//        this.wins = 0;
//        this.losses = 0;
//    }

    public Player(Player player) {
        this.username = player.username;
        this.totalGames = player.totalGames;
        this.wins = player.wins;
        this.losses = player.losses;
    }

    public String getUsername() {
        return username;
    }

    public double getWinPercentage() {
        if (totalGames == 0) {
            return 0;
        }
        return ((double) wins / totalGames) * 100;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getWins() {
        return wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getLosses(){
        return losses;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public int getRank() {
        return rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
}