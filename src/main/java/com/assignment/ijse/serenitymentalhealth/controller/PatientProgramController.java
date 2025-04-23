package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.bo.BOFactory;
import com.assignment.ijse.serenitymentalhealth.bo.custom.PatientProgramBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.TherapyProgramBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.PatientProgramBOImpl;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.TherapyProgramBOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.PatientDto;
import com.assignment.ijse.serenitymentalhealth.dto.PatientProgramDto;
import com.assignment.ijse.serenitymentalhealth.dto.TherapyProgramDto;
import com.assignment.ijse.serenitymentalhealth.dto.tm.PatientProgramTM;
import com.assignment.ijse.serenitymentalhealth.util.SetBackgroundUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PatientProgramController implements Initializable {

    @FXML
    private AnchorPane bodyPane;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<PatientProgramTM, Double> leftToPayCol;

    @FXML
    private Label  leftToPayTxt;

    @FXML
    private TableColumn<PatientProgramTM, String> patientIdCol;

    @FXML
    private TextField patientIdTxt;

    @FXML
    private TextField patientNameTxt;

    @FXML
    private TableView<PatientProgramTM> patientProgramTable;

    @FXML
    private Button patientSearchButton;

    @FXML
    private TableColumn<PatientProgramTM, String> paymentIdCol;

    @FXML
    private TextField paymentIdTxt;

    @FXML
    private TableColumn<PatientProgramTM, Double> programFeeCol;

    @FXML
    private Label programFeeTxt;

    @FXML
    private TableColumn<PatientProgramTM, String> programIdCol;

    @FXML
    private TextField programIdTxt;

    @FXML
    private TextField programNameTxt;

    @FXML
    private Button programSearchButton;

    @FXML
    private TableColumn<PatientProgramTM, LocalDate> registerDateCol;

    @FXML
    private DatePicker registerDateTxt;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private ToggleButton searchToggleButton;

    @FXML
    private TextField searchTxt;

    @FXML
    private Button updateButton;

    PatientProgramBO patientProgramBO = (PatientProgramBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT_PROGRAM);
    TherapyProgramBO therapyProgramBO = (TherapyProgramBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPY_PROGRAM);



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SetBackgroundUtil setBackground = new SetBackgroundUtil();
        setBackground.setBackgroundImage(bodyPane, 1300, 760);

        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        programIdCol.setCellValueFactory(new PropertyValueFactory<>("programId"));
        registerDateCol.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        paymentIdCol.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        programFeeCol.setCellValueFactory(new PropertyValueFactory<>("programFee"));
        leftToPayCol.setCellValueFactory(new PropertyValueFactory<>("leftToPay"));

        refreshTable();
    }

    private void refreshTable() {
        ArrayList<PatientProgramDto> programList = patientProgramBO.getAllPatientPrograms();
        ObservableList<PatientProgramTM> programTMS = FXCollections.observableArrayList();

        for (PatientProgramDto dto : programList) {
            TherapyProgramDto program = therapyProgramBO.findTherapyProgramByID(dto.getProgramId());
            programTMS.add(new PatientProgramTM(
                    dto.getPatientId(),
                    dto.getPatientName(),
                    dto.getProgramId(),
                    dto.getProgramName(),
                    dto.getRegistrationDate(),
                    dto.getPaymentId(),
                    program.getFee(),
                    dto.getLeftToPay()
            ));
        }

        patientProgramTable.setItems(programTMS);
    }

    private void clearFields() {
        patientIdTxt.clear();
        patientNameTxt.clear();
        programIdTxt.clear();
        programNameTxt.clear();
        paymentIdTxt.clear();
        registerDateTxt.setValue(null);
        programFeeTxt.setText("");
        leftToPayTxt.setText("");
    }

    @FXML
    void tableClick(MouseEvent event) {
        PatientProgramTM selected = patientProgramTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            patientIdTxt.setText(selected.getPatientId());
            patientNameTxt.setText(selected.getPatientName());
            programIdTxt.setText(selected.getProgramId());
            programNameTxt.setText(selected.getProgramName());
            registerDateTxt.setValue(selected.getRegistrationDate());
            paymentIdTxt.setText(selected.getPaymentId());
            programFeeTxt.setText(String.valueOf(selected.getProgramFee()));
            leftToPayTxt.setText(String.valueOf(selected.getLeftToPay()));
        }
    }

    @FXML
    void delete(ActionEvent event) {
//        String paymentId = paymentIdTxt.getText();
        String patientName = patientNameTxt.getText();
        String programName = programNameTxt.getText();

        if (patientProgramBO.deletePatientProgram(patientName, programName)) {
            showAlert("Success", "Program deleted successfully", Alert.AlertType.INFORMATION);
            refreshPage();
        } else {
            showAlert("Error", "Failed to delete program", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void save(ActionEvent event) {
        PatientProgramDto dto = new PatientProgramDto(
                patientIdTxt.getText(),
                patientNameTxt.getText(),
                programIdTxt.getText(),
                programNameTxt.getText(),
                registerDateTxt.getValue(),
                paymentIdTxt.getText(),
                null
        );

        if (patientProgramBO.savePatientProgram(dto)) {
            showAlert("Success", "Program saved successfully", Alert.AlertType.INFORMATION);
            refreshPage();
        } else {
            showAlert("Error", "Failed to save program", Alert.AlertType.ERROR);
        }
    }

    public void refreshPage(){
        clearFields();
        refreshTable();
    }

    @FXML
    void searchToggle(ActionEvent event) {
        if (searchToggleButton.isSelected()) {
            searchToggleButton.setText("Search by Program");
        } else {
            searchToggleButton.setText("Search by Patient");
        }
    }

    @FXML
    void search(ActionEvent event) {
        String query = searchTxt.getText().trim();

        if (query.isEmpty()) {
            showAlert("Input Error", "Please enter a search term", Alert.AlertType.WARNING);
            refreshPage();
            return;
        }

        ObservableList<PatientProgramTM> programTMS = FXCollections.observableArrayList();

        if (searchToggleButton.isSelected()) {
            ArrayList<PatientProgramDto> programList = patientProgramBO.search(query , false);
            for (PatientProgramDto dto : programList) {
                TherapyProgramDto program = therapyProgramBO.findTherapyProgramByID(dto.getProgramId());
                programTMS.add(new PatientProgramTM(
                        dto.getPatientId(),
                        dto.getPatientName(),
                        dto.getProgramId(),
                        dto.getProgramName(),
                        dto.getRegistrationDate(),
                        dto.getPaymentId(),
                        program.getFee(),
                        dto.getLeftToPay()
                ));
            }
        } else {
            ArrayList<PatientProgramDto> programList = patientProgramBO.search(query, true);
            for (PatientProgramDto dto : programList) {
                TherapyProgramDto program = therapyProgramBO.findTherapyProgramByID(dto.getProgramId());
                programTMS.add(new PatientProgramTM(
                        dto.getPatientId(),
                        dto.getPatientName(),
                        dto.getProgramId(),
                        dto.getProgramName(),
                        dto.getRegistrationDate(),
                        dto.getPaymentId(),
                        program.getFee(),
                        dto.getLeftToPay()
                ));
            }
        }

        if (programTMS.isEmpty()) {
            showAlert("Not Found", "No results found for: " + query, Alert.AlertType.INFORMATION);
        }

        patientProgramTable.setItems(programTMS);

    }

    @FXML
    void searchPatient(ActionEvent event) {
        String name = patientNameTxt.getText().trim();
        PatientDto patient = patientProgramBO.findByPatientName(name);
        if (patient == null) {
            showAlert("Not Found", "Patient not found", Alert.AlertType.WARNING);
            return;
        }
        patientIdTxt.setText(patient.getPatientId());
        patientNameTxt.setText(patient.getName());
    }

    @FXML
    void searchProgram(ActionEvent event) {
        String name = programNameTxt.getText().trim();
        TherapyProgramDto program = patientProgramBO.findByProgramName(name);
        if (program == null) {
            showAlert("Not Found", "Program not found", Alert.AlertType.WARNING);
            return;
        }
        programIdTxt.setText(program.getProgramId());
        programNameTxt.setText(program.getName());
    }

    @FXML
    void update(ActionEvent event) {
        try {
            String leftToPayStr = leftToPayTxt.getText().trim();
            if (leftToPayStr.isEmpty()) {
                showAlert("Validation Error", "Left to Pay field cannot be empty.", Alert.AlertType.WARNING);
                return;
            }

            BigDecimal leftToPay = new BigDecimal(leftToPayStr);

            PatientProgramDto dto = new PatientProgramDto(
                    patientIdTxt.getText(),
                    patientNameTxt.getText(),
                    programIdTxt.getText(),
                    programNameTxt.getText(),
                    registerDateTxt.getValue(),
                    paymentIdTxt.getText(),
                    leftToPay
            );

            if (patientProgramBO.updatePatientProgram(dto)) {
                showAlert("Success", "Program updated successfully", Alert.AlertType.INFORMATION);
                refreshPage();
            } else {
                showAlert("Error", "Failed to update program", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter a valid number for 'Left to Pay'.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Unexpected Error", "Something went wrong: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
