package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.util.NavigationUtil;
import javafx.scene.control.Button;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoginController {

    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private Button loginButton;

    @FXML
    private Pane loginPane;

    @FXML
    private JFXTextField passwordText;

    @FXML
    private JFXTextField usernameText;

    NavigationUtil navigate = new NavigationUtil();

    @FXML
    void loginValidate(ActionEvent event) {
        String username = usernameText.getText();
        String password = passwordText.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please Enter your username and Password.");
            alert.showAndWait();
        } else {
            boolean isAdmin = username.equals("admin");
            checkAuthority(isAdmin);

        }

    }

    void checkAuthority(boolean isAdmin) {
        navigate.loginNavigation("/view/home-page.fxml", baseAnchorPane, isAdmin);
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/home-page.fxml"));
//            AnchorPane homePane = loader.load();
//
//            HomeController homeController = loader.getController();
//
//            homeController.setUserRole(isAdmin);
//
//            baseAnchorPane.getChildren().setAll(homePane);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
