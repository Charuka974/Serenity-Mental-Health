package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.bo.BOFactory;
import com.assignment.ijse.serenitymentalhealth.bo.custom.PatientBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.PatientProgramBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.PaymentBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.TherapyProgramBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.PatientBOImpl;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.PatientProgramBOImpl;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.PaymentBOImpl;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.TherapyProgramBOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.PatientDto;
import com.assignment.ijse.serenitymentalhealth.dto.PatientProgramDto;
import com.assignment.ijse.serenitymentalhealth.dto.PaymentDto;
import com.assignment.ijse.serenitymentalhealth.dto.TherapyProgramDto;
import com.assignment.ijse.serenitymentalhealth.dto.tm.PaymentTM;
import com.assignment.ijse.serenitymentalhealth.entity.TherapySession;
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
import javafx.scene.layout.HBox;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PaymentsController implements Initializable {

    @FXML
    private TableColumn<PaymentTM, Double> amountCol;

    @FXML
    private TextField amountTxt;

    @FXML
    private AnchorPane bodyPane;

    @FXML
    private TableColumn<PaymentTM, LocalDate> dateCol;

    @FXML
    private DatePicker dateTxt;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<PaymentTM, String> patientIdCol;

    @FXML
    private TextField patientIdTxt;

    @FXML
    private TextField patientNameTxt;

    @FXML
    private Button patientSearchButton;

    @FXML
    private TableColumn<PaymentTM, String> paymentIdCol;

    @FXML
    private TextField paymentIdTxt;

    @FXML
    private ChoiceBox<String> paymentTypeChoice;

    @FXML
    private TableView<PaymentTM> paymentsTable;

    @FXML
    private TableColumn<PaymentTM, String> programIdCol;

    @FXML
    private TextField programIdTxt;

    @FXML
    private ChoiceBox<String> programNameTxt;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableColumn<PaymentTM, String> sessionIdCol;

    @FXML
    private HBox sessionIdPart;

    @FXML
    private Label paymentHeaderLabel;

    @FXML
    private Label paymentTypeLabel;

    @FXML
    private TextField sessionIdTxt;

    @FXML
    private Button updateButton;

    PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT);
    TherapyProgramBO programBO = (TherapyProgramBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPY_PROGRAM);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    PatientProgramBO patientProgramBO = (PatientProgramBO) BOFactory.getInstance().getBO(BOFactory.BOType.PATIENT_PROGRAM);


    private boolean isSessionPayment = false;

    public void setFromMainPage(boolean paymentType) {
        this.isSessionPayment = paymentType;
    }

    public void initialize(URL location, ResourceBundle resources) {
        SetBackgroundUtil setBackground = new SetBackgroundUtil();
        setBackground.setBackgroundImage(bodyPane, 1300, 760);


        paymentIdCol.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        programIdCol.setCellValueFactory(new PropertyValueFactory<>("therapyProgramId"));
        sessionIdCol.setCellValueFactory(new PropertyValueFactory<>("therapySessionId"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        refreshPage();

//        paymentTypeChoice.setItems(FXCollections.observableArrayList("Program Register", "Session Payment"));
//        paymentTypeChoice.setValue("Program Register");
//        sessionIdPart.setVisible(false);
//        paymentTypeChoice.setOnAction(e -> toggleSessionField());

//        paymentTypeChoice.setVisible(false);
//        if (isSessionPayment) {
//            sessionIdPart.setVisible(true);
//        } else {
//            sessionIdPart.setVisible(false);
//        }

        searchButton.setText("Search / Clear");

        paymentTypeChoice.setVisible(false);
        paymentTypeLabel.setVisible(false);
        sessionIdPart.setVisible(isSessionPayment);
        paymentHeaderLabel.setText(isSessionPayment ? "Session Payment" : "Register Payment");

    }

    public void configurePage() {
        sessionIdPart.setVisible(isSessionPayment);
        paymentHeaderLabel.setText(isSessionPayment ? "Session Payment" : "Register Payment");
    }

//    private void toggleSessionField() {
////        sessionIdPart.setVisible(!"Program Register".equals(paymentTypeChoice.getValue()));
//    }

    private void refreshTable() {
        ArrayList<PaymentDto> paymentList = paymentBO.getAllPayments();
        ObservableList<PaymentTM> payments = FXCollections.observableArrayList();

        for (PaymentDto dto : paymentList) {
            String session = null;
            if (dto.getTherapySession() != null) {
                session = dto.getTherapySession().getSession_id();
            }
            payments.add(new PaymentTM(
                    dto.getPaymentId(),
                    dto.getPatient().getPatient_id(),
                    dto.getTherapyProgram().getProgram_id(),
                    session,
                    dto.getAmount(),
                    dto.getPaymentDate()
            ));
        }

        paymentsTable.setItems(payments);
    }

    private void clearFields() {
        paymentIdTxt.clear();
        patientIdTxt.clear();
        patientNameTxt.clear();
        programIdTxt.clear();
        programNameTxt.setValue("");
        sessionIdTxt.clear();
        amountTxt.clear();
        dateTxt.setValue(null);
    }


    @FXML
    void delete(ActionEvent event) {
        String id = paymentIdTxt.getText();
        if (paymentBO.deletePayment(id)) {
            showAlert("Success", "Payment deleted", Alert.AlertType.INFORMATION);
            refreshPage();
        } else {
            showAlert("Error", "Delete failed", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void save(ActionEvent event) {
        PaymentDto dto = paymentBO.constructPaymentDto(
                paymentIdTxt.getText(),
                patientIdTxt.getText(),
                programIdTxt.getText(),
                sessionIdTxt.getText(),
                new BigDecimal(amountTxt.getText()),
                dateTxt.getValue()
        );

        if (paymentBO.savePayment(dto)) {
            showAlert("Success", "Payment saved successfully", Alert.AlertType.INFORMATION);
            refreshPage();
        } else {
            showAlert("Error", "Failed to save payment", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void search(ActionEvent event) {
        String query = searchTxt.getText();
        if (query.isEmpty()) {
//            showAlert("Error", "Please enter a search term", Alert.AlertType.WARNING);
            refreshPage();
            return;
        }
        ArrayList<PaymentDto> results = paymentBO.searchByPatientName(query);

        ObservableList<PaymentTM> payments = FXCollections.observableArrayList();

        for (PaymentDto dto : results) {
            String sessionId = null;
            if (dto.getTherapySession() != null) {
                sessionId = dto.getTherapySession().getSession_id();
            }

            payments.add(new PaymentTM(
                    true,
                    dto.getPaymentId(),
                    dto.getPatient().getPatient_id(),
                    dto.getTherapyProgram().getProgram_id(),
                    sessionId,
                    dto.getAmount(),
                    dto.getPaymentDate()
            ));
        }

        paymentsTable.setItems(payments);
    }

    private void refreshPage() {
        clearFields();
        refreshTable();
        paymentIdTxt.setText(paymentBO.getNextPaymentPK());
    }

    @FXML
    void searchPatient(ActionEvent event) {
        String name = patientNameTxt.getText().trim();
        ArrayList<PatientDto> patients = patientBO.findByPatientName(name);

        if (patients.isEmpty()) {
            showAlert("Not Found", "Patient not found", Alert.AlertType.WARNING);
            return;
        }

        PatientDto patient = patients.getFirst();
        patientIdTxt.setText(patient.getPatientId());
        patientNameTxt.setText(patient.getName());

        List<PatientProgramDto> programs = patientProgramBO.getProgramsByPatientId(patient.getPatientId());

        List<String> programNames = programs.stream()
                .map(PatientProgramDto::getProgramName)
                .collect(Collectors.toList());

        programNameTxt.getItems().clear(); // optional: clears old data if needed
        programNameTxt.getItems().addAll(programNames);

        programNameTxt.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                searchProgram();
            }
        });


    }

    void searchProgram() {
        String name = programNameTxt.getValue().trim();
        ArrayList<TherapyProgramDto> programs = programBO.findTherapyProgramByName(name);

        if (programs.isEmpty()) {
            showAlert("Not Found", "Program not found", Alert.AlertType.WARNING);
            return;
        }

        TherapyProgramDto program = programs.getFirst();
        programIdTxt.setText(program.getProgramId());

    }

    @FXML
    void update(ActionEvent event) {
        PaymentDto dto = paymentBO.constructPaymentDto(
                paymentIdTxt.getText(),
                patientIdTxt.getText(),
                programIdTxt.getText(),
                sessionIdTxt.getText(),
                new BigDecimal(amountTxt.getText()),
                dateTxt.getValue()
        );

        if (paymentBO.updatePayment(dto)) {
            showAlert("Success", "Payment updated successfully", Alert.AlertType.INFORMATION);
            refreshPage();
        } else {
            showAlert("Error", "Failed to update payment", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void tableClick(MouseEvent event) {
        PaymentTM selected = paymentsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            paymentIdTxt.setText(selected.getPaymentId());
            patientIdTxt.setText(selected.getPatientId());
            patientNameTxt.setText(patientBO.findPatientByID(selected.getPatientId()).getName());
            programIdTxt.setText(selected.getTherapyProgramId());
            programNameTxt.setValue(programBO.findTherapyProgramByID(selected.getTherapyProgramId()).getName());
            sessionIdTxt.setText(selected.getTherapySessionId());
            amountTxt.setText(String.valueOf(selected.getAmount()));
            dateTxt.setValue(selected.getPaymentDate());
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
