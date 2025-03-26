package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.bo.custom.UserBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.UserBOImpl;
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

    UserBO userBO = new UserBOImpl();

    @FXML
    void loginValidate(ActionEvent event) {
        String username = usernameText.getText();
        String password = passwordText.getText();

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

        if (role.equals("admin")) {
            navigate.loginNavigation("/view/home-page.fxml", baseAnchorPane, true);
        } else if (role.equals("receptionist")) {
            navigate.loginNavigation("/view/home-page.fxml", baseAnchorPane, false);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password.");
            alert.showAndWait();
        }
    }



}
