package com.assignment.ijse.serenitymentalhealth.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

import java.net.URL;
import java.util.ResourceBundle;

public class TherapistAvailabilityController implements Initializable {

    @FXML
    private TextField availabilityIdTxt;

    @FXML
    private TreeTableColumn<?, ?> availableDateCol;

    @FXML
    private DatePicker availableDateTxt;

    @FXML
    private TreeTableColumn<?, ?> availableIdCol;

    @FXML
    private TreeTableColumn<?, ?> availableStatusCol;

    @FXML
    private TreeTableColumn<?, ?> availableTimeCol;

    @FXML
    private ChoiceBox<String> availableTimeTxt;

    @FXML
    private Button deleteButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTxt;

    @FXML
    private ChoiceBox<?> statusTxt;

    @FXML
    private TreeTableView<?> therapistAvailabilityTable;

    @FXML
    private TreeTableColumn<?, ?> therapistIdCol;

    @FXML
    private TextField therapistIdTxt;

    @FXML
    private TextField therapistNameTxt;

    @FXML
    private Button therapistSearchBtn;

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
    void therapistSearch(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

    public void setTimeToTimePicker() {
        availableTimeTxt.getItems().addAll(
                "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
                "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM"
        );
        availableTimeTxt.setValue("00:00 AM");
    }

}
