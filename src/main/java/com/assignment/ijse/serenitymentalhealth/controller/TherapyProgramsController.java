package com.assignment.ijse.serenitymentalhealth.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TherapyProgramsController {

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> durationCol;

    @FXML
    private TextField durationTxt;

    @FXML
    private TextField feeTxt;

    @FXML
    private TableColumn<?, ?> patientEmailCol;

    @FXML
    private TableColumn<?, ?> programFeeCol;

    @FXML
    private TableColumn<?, ?> programIdCol;

    @FXML
    private TextField programIdTxt;

    @FXML
    private TextField programNameTxt;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableView<?> therapyProgramsTable;

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
