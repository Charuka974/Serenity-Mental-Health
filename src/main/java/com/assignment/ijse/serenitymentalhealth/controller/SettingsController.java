package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.bo.BOFactory;
import com.assignment.ijse.serenitymentalhealth.bo.custom.UserBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.UserBOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.UserDto;
import com.assignment.ijse.serenitymentalhealth.util.KeepUserIDUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private PasswordField confirmTxtInvisible;

    @FXML
    private TextField confirmTxtVisible;

    @FXML
    private Label oldPasswordLabel;

    @FXML
    private Label oldUsernameLabel;

    @FXML
    private PasswordField passwordTxtInvisible;

    @FXML
    private TextField passwordTxtVisible;

    @FXML
    private Button saveButton;

    @FXML
    private TextField usernameTxt;

    @FXML
    private AnchorPane settingsPane;

    @FXML
    private Button visibleButton;

    private String loggedInUserId = null;


    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    public void initialize(URL location, ResourceBundle resources){
        setBackgroundImage();

        loggedInUserId = KeepUserIDUtil.getInstance().getCurrentUserId();
        if (loggedInUserId == null) {
            System.out.println("User ID is null");
            return;
        }

        UserDto user = userBO.findUserByUserId(loggedInUserId);
        if (user != null) {
            usernameTxt.setText(user.getUsername());
            oldUsernameLabel.setText(user.getUsername());
//            oldPasswordLabel.setText(user.getPassword());
        }
        else {
            System.out.println("User not found");
        }

    }


    @FXML
    void save(ActionEvent event) {
        String newUsername = usernameTxt.getText();
        String newPassword = passwordTxtInvisible.getText();
        String confirmPassword = confirmTxtInvisible.getText();

        if (newUsername.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            System.out.println("Please fill in all fields");
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("Passwords do not match");
            return;
        }

        if (userBO.updateUsernameAndPassword(loggedInUserId, newUsername, newPassword)) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Your username and password have been successfully updated.");
            alert.showAndWait();

            usernameTxt.setText("");
            passwordTxtInvisible.setText("");
            confirmTxtInvisible.setText("");
            passwordTxtVisible.setText("");
            confirmTxtVisible.setText("");
            oldUsernameLabel.setText("");
            oldPasswordLabel.setText("");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to update your username or password. Please try again.");
            alert.showAndWait();
        }


    }

    @FXML
    void setInvisible(MouseEvent event) {
        passwordTxtInvisible.setVisible(true);
        confirmTxtInvisible.setVisible(true);
        passwordTxtVisible.setVisible(false);
        confirmTxtVisible.setVisible(false);
        passwordTxtInvisible.setText(passwordTxtVisible.getText());
        confirmTxtInvisible.setText(confirmTxtVisible.getText());
    }


    @FXML
    void setVisible(MouseEvent event) {
        passwordTxtVisible.setVisible(true);
        confirmTxtVisible.setVisible(true);
        passwordTxtInvisible.setVisible(false);
        confirmTxtInvisible.setVisible(false);
        passwordTxtVisible.setText(passwordTxtInvisible.getText());
        confirmTxtVisible.setText(confirmTxtInvisible.getText());
    }


    public void setBackgroundImage(){
        Image backgroundImage = new Image(
                getClass().getResource("/images/rm222batch2-mind-03.jpg").toExternalForm()
        );
        BackgroundSize backgroundSize = new BackgroundSize(
                600, 400, true, true, false, true
        );
        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );
        settingsPane.setBackground(new Background(bgImage));
    }

}
