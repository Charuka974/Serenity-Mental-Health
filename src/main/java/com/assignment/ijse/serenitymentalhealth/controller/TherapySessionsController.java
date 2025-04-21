package com.assignment.ijse.serenitymentalhealth.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TherapySessionsController {

    @FXML
    private AnchorPane bodyPane;

    @FXML
    private TableColumn<?, ?> date1TSCol;

    @FXML
    private TableColumn<?, ?> date2TSCol;

    @FXML
    private TableColumn<?, ?> date3TSCol;

    @FXML
    private TableColumn<?, ?> date4TSCol;

    @FXML
    private TableColumn<?, ?> date5TSCol;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> durationCol;

    @FXML
    private TableColumn<?, ?> patientIdCol;

    @FXML
    private TextField patientIdTxt;

    @FXML
    private TextField patientNameTxt;

    @FXML
    private Button patientSearchButton;

    @FXML
    private Button paymentLoadButton;

    @FXML
    private TableColumn<?, ?> programIdCol;

    @FXML
    private TextField programIdTxt;

    @FXML
    private ChoiceBox<?> programNameTxt;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableColumn<?, ?> sessionDateCol;

    @FXML
    private DatePicker sessionDateTxt;

    @FXML
    private ChoiceBox<?> sessionDurationTxt;

    @FXML
    private TableColumn<?, ?> sessionIdCol;

    @FXML
    private TextField sessionIdTxt;

    @FXML
    private TableColumn<?, ?> sessionTimeCol;

    @FXML
    private TextField sessionTimeTxt;

    @FXML
    private TableColumn<?, ?> statusCol;

    @FXML
    private ChoiceBox<?> statusTxtChoice;

    @FXML
    private TableColumn<?, ?> therapistIdCol;

    @FXML
    private TextField therapistIdTxt;

    @FXML
    private ChoiceBox<?> therapistNameTxt;

    @FXML
    private TableView<?> therapySessionTable;

    @FXML
    private TableView<?> timeSlotTable;

    @FXML
    private TableColumn<?, ?> timeTSCol;

    @FXML
    private Button updateButton;

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void loadPaymentPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/payments-page.fxml"));
            Parent root = loader.load();

            PaymentsController controller = loader.getController();
            controller.setFromMainPage(true); // pass the boolean
            controller.configurePage();       // apply changes to the UI

            Stage stage = new Stage();
            stage.setTitle("Manage Payments");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onclickTSTable(MouseEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void searchPatient(ActionEvent event) {

    }

    @FXML
    void tableClick(MouseEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }


}
