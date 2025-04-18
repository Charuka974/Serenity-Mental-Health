package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.util.SetBackgroundUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TherapySessionsController implements Initializable {

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> patientIdCol;

    @FXML
    private TextField patientIdTxt;

    @FXML
    private TextField patientNameTxt;

    @FXML
    private Button patientSearchButton;

    @FXML
    private TableColumn<?, ?> programIdCol;

    @FXML
    private TextField programIdTxt;

    @FXML
    private TextField programNameTxt;

    @FXML
    private Button programSearchButton;

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
    private TableColumn<?, ?> sessionIdCol;

    @FXML
    private TextField sessionIdTxt;

    @FXML
    private TableColumn<?, ?> sessionTimeCol;

    @FXML
    private ChoiceBox<String> sessionTimeTxt;

    @FXML
    private TableColumn<?, ?> statusCol;

    @FXML
    private ChoiceBox<?> statusTxtChoice;

    @FXML
    private TableColumn<?, ?> therapistIdCol;

    @FXML
    private TextField therapistIdTxt;

    @FXML
    private TextField therapistNameTxt;

    @FXML
    private Button therapistSearchButton;

    @FXML
    private TableView<?> therapySessionTable;

    @FXML
    private Button updateButton;

    @FXML
    private AnchorPane bodyPane;

    public void initialize(URL location, ResourceBundle resources){
        SetBackgroundUtil setBackground = new SetBackgroundUtil();
        setBackground.setBackgroundImage(bodyPane, 1300, 760);

        setTimeToTimePicker();
    }

    @FXML
    void delete(ActionEvent event) {

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
    void searchProgram(ActionEvent event) {

    }

    @FXML
    void searchTherapist(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

    public void setTimeToTimePicker() {
        sessionTimeTxt.getItems().addAll(
                "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
                "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM"
        );
        sessionTimeTxt.setValue("00:00 AM");
    }

}