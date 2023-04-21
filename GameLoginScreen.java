import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class GameLoginScreen extends Application {
    private Map<String, UserProfile> userDatabase;
    private TextField usernameField;
    private PasswordField passwordField;
    private TextField firstNameField;
    private TextField lastNameField;
    private ImageView profileImageView;

    public GameLoginScreen() {
        userDatabase = new HashMap<>();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game Login");
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setCenter(createLoginPane());
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    private Pane createLoginPane() {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Game Login");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        usernameField = new TextField();
        usernameField.setPromptText("Username");
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> loginUser());
        Button createUserButton = new Button("Create User");
        createUserButton.setOnAction(event -> showCreateUserDialog());
        vbox.getChildren().addAll(titleLabel, usernameField, passwordField, loginButton, createUserButton);

        return vbox;
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (userDatabase.containsKey(username)) {
            UserProfile userProfile = userDatabase.get(username);
            if (userProfile.getPassword().equals(password)) {
                System.out.println("Login successful.");
                // Add logic for successful login
            } else {
                showAlert("Login Error", "Incorrect password. Please try again.");
            }
        } else {
            showAlert("Login Error", "Username not found. Please try again.");
        }
    }

    private void showCreateUserDialog() {
        Dialog<UserProfile> dialog = new Dialog<>();
        dialog.setTitle("Create User");
        dialog.setHeaderText("Please fill in the details for the new user");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        usernameField = new TextField();
        usernameField.setPromptText("Username");
        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        profileImageView = new ImageView();
        profileImageView.setFitWidth(100);
        profileImageView.setFitHeight(100);
        Button uploadImageButton = new Button("Upload Image");
        uploadImageButton.setOnAction(event -> uploadImage());

        gridPane.add(new Label("Username:"), 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(new Label("Password:"), 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(new Label("First Name:"), 0, 2);
        gridPane.add