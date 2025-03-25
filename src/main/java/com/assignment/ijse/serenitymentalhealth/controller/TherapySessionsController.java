package com.assignment.ijse.serenitymentalhealth.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TherapySessionsController implements Initializable {

    @FXML
    private Button deleteButton;

    @FXML
    private ChoiceBox<String> hourChoiceBox;

    @FXML
    private ChoiceBox<String> minuteChoiceBox;

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

    public void initialize(URL location, ResourceBundle resources){
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
        hourChoiceBox.getItems().addAll(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"
        );
        hourChoiceBox.setValue("00");

        minuteChoiceBox.getItems().addAll(
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
                "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35",
                "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
                "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"
        );
        minuteChoiceBox.setValue("00");
    }

}