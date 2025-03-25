package com.assignment.ijse.serenitymentalhealth.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TherapistsController {

    @FXML
    private Button deleteButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableColumn<?, ?> therapistEmailCol;

    @FXML
    private TextField therapistEmailTxt;

    @FXML
    private TableColumn<?, ?> therapistIdCol;

    @FXML
    private TextField therapistIdTxt;

    @FXML
    private TableColumn<?, ?> therapistNameCol;

    @FXML
    private TextField therapistNameTxt;

    @FXML
    private TableColumn<?, ?> therapistPhoneCol;

    @FXML
    private TextField therapistPhoneTxt;

    @FXML
    private TableColumn<?, ?> therapistSpecsCol;

    @FXML
    private TextField therapistSpecsTxt;

    @FXML
    private TableView<?> therapistsTable;

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
