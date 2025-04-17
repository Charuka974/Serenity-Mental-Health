package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.bo.custom.UserBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.UserBOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.UserDto;
import com.assignment.ijse.serenitymentalhealth.util.NavigationUtil;
import com.jfoenix.controls.JFXPasswordField;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController {

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
    private Pane loginTogglePane;

    @FXML
    private JFXTextField loginUsernameText;

    @FXML
    private ImageView logoImage;

    @FXML
    private Pane mainContainer;

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
    private Pane signInTogglePane;

    @FXML
    private JFXTextField signInUserIDText;

    @FXML
    private JFXTextField signInUsernameText;

    @FXML
    private ToggleButton toggleButton;

    NavigationUtil navigate = new NavigationUtil();
    UserBO userBO = new UserBOImpl();
    private boolean showingLogin = true;

    @FXML
    public void initialize() {
        signInUserIDText.setText( userBO.generateNextUserId());
    }

    @FXML
    void signIn(ActionEvent event) {
        String userID = signInUserIDText.getText();
        String username = signInUsernameText.getText();
        String email = signInEmailText.getText();
        String password = signInPasswordText.getText();
        String jobRole = signInJobRoleText.getText();
        String passwordConfirm = signInPasswordConfirmText.getText();
        if (!password.equals(passwordConfirm)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Passwords do not match.");
            alert.showAndWait();
            return;
        }
        if (userBO.registerUser(new UserDto(userID, username, password, jobRole, email))){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Sign Up Complete.");
            signInUserIDText.clear();
            signInUsernameText.clear();
            signInEmailText.clear();
            signInPasswordText.clear();
            signInJobRoleText.clear();
            signInPasswordConfirmText.clear();
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



}
