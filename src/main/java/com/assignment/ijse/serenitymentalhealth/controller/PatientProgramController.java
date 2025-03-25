package com.assignment.ijse.serenitymentalhealth.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PatientProgramController {

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> patientIdCol;

    @FXML
    private TextField patientIdTxt;

    @FXML
    private TextField patientNameTxt;

    @FXML
    private TableView<?> patientProgramTable;

    @FXML
    private Button patientSearchButton;

    @FXML
    private TableColumn<?, ?> paymentIdCol;

    @FXML
    private TextField paymentIdTxt;

    @FXML
    private TableColumn<?, ?> programIdCol;

    @FXML
    private TextField programIdTxt;

    @FXML
    private TextField programNameTxt;

    @FXML
    private Button programSearchButton;

    @FXML
    private TableColumn<?, ?> registerDateCol;

    @FXML
    private DatePicker registerDateTxt;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTxt;

    @FXML
    private Button updateButton;

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
    void update(ActionEvent event) {

    }

}
