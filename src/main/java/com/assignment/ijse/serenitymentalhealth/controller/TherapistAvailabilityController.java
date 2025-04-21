package com.assignment.ijse.serenitymentalhealth.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TherapistAvailabilityController {

    @FXML
    private TextField availabilityIdTxt;

    @FXML
    private TableColumn<?, ?> availableDateCol;

    @FXML
    private DatePicker availableDateTxt;

    @FXML
    private TableColumn<?, ?> availableIdCol;

    @FXML
    private TableColumn<?, ?> availableStatusCol;

    @FXML
    private AnchorPane bodyPane;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<?, ?> endTimeCol;

    @FXML
    private TextField endTimeTxt;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableColumn<?, ?> startTimeCol;

    @FXML
    private TextField startTimeTxt;

    @FXML
    private ChoiceBox<?> statusTxt;

    @FXML
    private TableView<?> therapistAvailabilityTable;

    @FXML
    private TableColumn<?, ?> therapistIdCol;

    @FXML
    private TextField therapistIdTxt;

    @FXML
    private TextField therapistNameTxt;

    @FXML
    private Button therapistSearchBtn;

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
    void tableClick(MouseEvent event) {

    }

    @FXML
    void therapistSearch(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

}
