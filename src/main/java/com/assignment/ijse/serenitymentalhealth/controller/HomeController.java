package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable{

    @FXML
    private VBox adminOnlyButtonBox;

    @FXML
    private Button patientProgramPageBtn;

    @FXML
    private Pane bodyPane;

    @FXML
    private VBox commonButtonBox;

    @FXML
    private AnchorPane homeAnchorPane;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button patientsPageBtn;

    @FXML
    private Button paymentsPageBtn;

    @FXML
    private Button therapistsPageBtn;

    @FXML
    private Button therapyProgramPageBtn;

    @FXML
    private Button therapySessionPageBtn;

    @FXML
    private Button usersPageBtn;

    @FXML
    private Button welcomePageBtn;

    NavigationUtil navigate = new NavigationUtil();

    public void initialize(URL location, ResourceBundle resources){
        navigate.navigateTo(bodyPane, "/view/welcome-page.fxml");
    }

    @FXML
    void loadWelcomePage(ActionEvent event) {
        navigate.navigateTo(bodyPane, "/view/welcome-page.fxml");
    }

    @FXML
    void loadPatientsPage(ActionEvent event) {
        navigate.navigateTo(bodyPane, "/view/patients-page.fxml");
    }

    @FXML
    void loadPaymentsPage(ActionEvent event) {
        navigate.navigateTo(bodyPane, "/view/payments-page.fxml");
    }

    @FXML
    void loadPatientProgramPage(ActionEvent event) {
        navigate.navigateTo(bodyPane, "/view/patient-program-page.fxml");
    }

    @FXML
    void loadTherapistsPage(ActionEvent event) {
        navigate.navigateTo(bodyPane, "/view/therapists-page.fxml");
    }

    @FXML
    void loadTherapyProgramsPage(ActionEvent event) {
        navigate.navigateTo(bodyPane, "/view/therapy-programs-page.fxml");
    }

    @FXML
    void loadTherapySessionsPage(ActionEvent event) {
        navigate.navigateTo(bodyPane, "/view/therapy-sessions-page.fxml");
    }

    @FXML
    void loadUsersPage(ActionEvent event) {
        navigate.navigateTo(bodyPane, "/view/users-page.fxml");
    }

    @FXML
    void logOut(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to log out?",
                ButtonType.YES, ButtonType.NO);
        alert.setTitle("Logout Confirmation");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                navigate.navigateBack(homeAnchorPane, "/view/login-page.fxml");
            }
        });
    }


    public void setUserRole(boolean isAdmin) {
        adminOnlyButtonBox.setVisible(isAdmin);
        commonButtonBox.setVisible(true);
    }

}
