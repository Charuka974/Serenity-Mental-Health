package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.util.SetBackgroundUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentsController implements Initializable {

    @FXML
    private TextField amountTxt;

    @FXML
    private DatePicker dateTxt;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField patientIdTxt;

    @FXML
    private TextField patientNameTxt;

    @FXML
    private Button patientSearchButton;

    @FXML
    private TextField paymentIdTxt;

    @FXML
    private ChoiceBox<?> paymentTypeChoice;

    @FXML
    private TableView<?> paymentsTable;

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
    private HBox sessionIdPart;

    @FXML
    private TextField sessionIdTxt;

    @FXML
    private Button updateButton;

    @FXML
    private AnchorPane bodyPane;

    public void initialize(URL location, ResourceBundle resources) {
        SetBackgroundUtil setBackground = new SetBackgroundUtil();
        setBackground.setBackgroundImage(bodyPane, 1300, 760);

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
    void update(ActionEvent event) {

    }

}
