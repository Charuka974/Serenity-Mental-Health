package com.assignment.ijse.serenitymentalhealth.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PatientsController {

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> patientAddressCol;

    @FXML
    private TextField patientAddressTxt;

    @FXML
    private TableColumn<?, ?> patientEmailCol;

    @FXML
    private TextField patientEmailTxt;

    @FXML
    private TableColumn<?, ?> patientHistoryCol;

    @FXML
    private TextArea patientHistoryTxt;

    @FXML
    private TableColumn<?, ?> patientIdCol;

    @FXML
    private TextField patientIdTxt;

    @FXML
    private TableColumn<?, ?> patientNameCol;

    @FXML
    private TextField patientNameTxt;

    @FXML
    private TableColumn<?, ?> patientPhoneCol;

    @FXML
    private TextField patientPhoneTxt;

    @FXML
    private TableView<?> patientsTable;

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
    void update(ActionEvent event) {

    }

}
