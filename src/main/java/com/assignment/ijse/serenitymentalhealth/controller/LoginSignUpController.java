package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.bo.custom.UserBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.UserBOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.UserDto;
import com.assignment.ijse.serenitymentalhealth.util.NavigationUtil;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class LoginSignUpController {

    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private VBox loginBox;

    @FXML
    private Button loginButton;

    @FXML
    private Pane loginPane;

    @FXML
    private JFXPasswordField loginPasswordText;

    @FXML
    private JFXTextField loginUsernameText;

    @FXML
    private ImageView logoImage;

    @FXML
    private VBox signInBox;

    @FXML
    private Button signInBtn;

    @FXML
    private JFXTextField signInEmailText;

    @FXML
    private JFXTextField signInJobRoleText;

    @FXML
    private JFXPasswordField signInPasswordConfirmText;

    @FXML
    private JFXPasswordField signInPasswordText;

    @FXML
    private JFXTextField signInUserIDText;

    @FXML
    private JFXTextField signInUsernameText;

    @FXML
    private ToggleButton toggleButton;

    @FXML
    private HBox loginVisiblePasswordBox;

    @FXML
    private JFXTextField loginVisiblePasswordText;

    @FXML
    private ImageView seeUnseeEyeImage;



    NavigationUtil navigate = new NavigationUtil();
    UserBO userBO = new UserBOImpl();
    private boolean showingLogin = true;

    @FXML
    public void initialize() {
        setBackgroundImage();
        seeUnseeEyeImage.setImage(new Image(getClass().getResourceAsStream("/images/unseeEye.png")));
        loginVisiblePasswordText.setVisible(false);
        loginPasswordText.setVisible(true);

        signInUserIDText.setText( userBO.generateNextUserId());
    }

    public void setBackgroundImage(){
        Image backgroundImage = new Image(
                getClass().getResource("/images/rm222batch2-mind-03.jpg").toExternalForm()
        );
        BackgroundSize backgroundSize = new BackgroundSize(
                1535, 795, true, true, false, true
        );
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );
        loginPane.setBackground(new Background(bgImage));
    }


    @FXML
    void signIn(ActionEvent event) {
        String userID = signInUserIDText.getText().trim();
        String username = signInUsernameText.getText().trim();
        String email = signInEmailText.getText().trim();
        String password = signInPasswordText.getText().trim();
        String jobRole = signInJobRoleText.getText().trim();
        String passwordConfirm = signInPasswordConfirmText.getText().trim();

        if (userID.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || jobRole.isEmpty() || passwordConfirm.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill in all the fields.");
            alert.showAndWait();
            return;
        }
        if (!password.equals(passwordConfirm)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Passwords do not match.");
            alert.showAndWait();
            return;
        }
        if (userBO.registerUser(new UserDto(userID, username, password, email, jobRole))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Sign Up Complete.");
            alert.showAndWait();

            signInUserIDText.clear();
            signInUsernameText.clear();
            signInEmailText.clear();
            signInPasswordText.clear();
            signInJobRoleText.clear();
            signInPasswordConfirmText.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Username already exists.");
            alert.showAndWait();
        }


    }

    @FXML
    void switchLoginSignIn(ActionEvent event) {
        TranslateTransition imageTransition = new TranslateTransition(Duration.seconds(0.5), logoImage);

        if (showingLogin) {
            imageTransition.setToX(500);
            toggleButton.setText("Switch to Login");
        } else {
            imageTransition.setToX(0);
            toggleButton.setText("Switch to Sign Up");
        }

        imageTransition.play();
        showingLogin = !showingLogin;
    }

    @FXML
    void loginValidate(ActionEvent event) {
        String username = loginUsernameText.getText();
        String password = loginPasswordText.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please Enter your username and Password.");
            alert.showAndWait();
            return;
        }

        String role = userBO.validateUser(username, password);
        System.out.println("Debug: Retrieved Role = " + role);

        checkAuthority(role);
    }


    void checkAuthority(String role) {
        if (role == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password.");
            alert.showAndWait();
            return;
        }

        if (role.equalsIgnoreCase("admin")) {
            navigate.loginNavigation("/view/home-page.fxml", baseAnchorPane, true);
        } else if (role.equalsIgnoreCase("receptionist")) {
            navigate.loginNavigation("/view/home-page.fxml", baseAnchorPane, false);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password.");
            alert.showAndWait();
        }
    }

    @FXML
    void makePasswordInvisible(MouseEvent event) {
        seeUnseeEyeImage.setImage(new Image(getClass().getResourceAsStream("/images/unseeEye.png")));
        loginPasswordText.setVisible(true);
        loginVisiblePasswordText.setVisible(false);
        loginPasswordText.setText(loginVisiblePasswordText.getText());
    }

    @FXML
    void makePasswordVisible(MouseEvent event) {
        seeUnseeEyeImage.setImage(new Image(getClass().getResourceAsStream("/images/seeEye.png")));
        loginVisiblePasswordText.setVisible(true);
        loginPasswordText.setVisible(false);
        loginVisiblePasswordText.setText(loginPasswordText.getText());
    }


}
