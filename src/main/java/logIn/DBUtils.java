/**
 * This class provides utility methods for working with a database.
 *
 * @author Mike Rundle
 */
package logIn;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import leaderboard.User;
import mainmenu.UserData;

import java.sql.*;

import java.io.IOException;

public class DBUtils {

    /**
     * Changes the scene to the given FXML file and sets the window title.
     *
     * @param event     the event that triggered the scene change
     * @param fxmlFile  the FXML file to load
     * @param title     the window title to set
     * @param username  the username to pass to the LoggedInController (optional)
     */
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;

        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation(username);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    /**
     * Signs up a user with the given username and password.
     *
     * @param event     the event that triggered the signup
     * @param username  the desired username
     * @param password  the desired password
     */
    public static void signUpUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-video", "root", "rootpassword");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username taken.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?,?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();

                changeScene(event, "logged-in.fxml", "Welcome!", username);
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
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
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
    * Logs in a user with the provided credentials.
    *
    * @param event The ActionEvent that triggered the login.
    * @param username The username of the user.
    * @param password The password of the user.
     */
    public static void logInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-video", "root", "rootpassword");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in database.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                       changeScene(event, "logged-in.fxml", "Welcome!", username);
                    } else {
                        System.out.println("Passwords did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect.");
                        alert.show();
                    }
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
    * Updates the user data for the given username.
    *
    * @param username The username of the user.
     */
    public static void updateUserData(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-video", "root", "rootpassword");
            preparedStatement = connection.prepareStatement("SELECT wins, losses, games FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in database.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("User not found in database");
                alert.show();
            } else {
                while (resultSet.next()) {
                    int wins = resultSet.getInt("wins");
                    int losses = resultSet.getInt("losses");
                    int games = resultSet.getInt("games");

                    UserData.getInstance().setWins(wins);
                    UserData.getInstance().setLosses(losses);
                    UserData.getInstance().setGames(games);
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

    * Updates the user's statistics in the database and the UserData instance with the provided wins and losses.
    *
    * @param wins The number of wins to be added to the user's statistics.
    * @param losses The number of losses to be added to the user's statistics.
     */
    public static void updatePlayerStats(int wins, int losses) {
        int games = wins + losses;
        UserData userData = UserData.getInstance();
        if (userData.getLoggedIn()) {
            String username = userData.getUsername();

            // Create a connection to your database
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx-video", "root", "rootpassword");

                // Update the wins and losses for the user in the database
                PreparedStatement statement = connection.prepareStatement("UPDATE users SET wins = ?, losses = ?, games = ? WHERE username = ?");
                statement.setInt(1, userData.getWins() + wins);
                statement.setInt(2, userData.getLosses() + losses);
                statement.setInt(3, userData.getGames() + games);
                statement.setString(4, username);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated == 0) {
                    System.out.println("No rows were updated. The user " + username + " may not exist in the database.");
                }

                // Update the UserData instance with the new wins and losses
                userData.setWins(userData.getWins() + wins);
                userData.setLosses(userData.getLosses() + losses);
                userData.setGames(userData.getGames() + wins + losses);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
