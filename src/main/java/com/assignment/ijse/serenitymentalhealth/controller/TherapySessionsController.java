package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.bo.custom.*;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.*;
import com.assignment.ijse.serenitymentalhealth.dto.*;
import com.assignment.ijse.serenitymentalhealth.dto.tm.TherapySessionTM;
import com.assignment.ijse.serenitymentalhealth.entity.TherapistAvailability;
import com.assignment.ijse.serenitymentalhealth.util.SetBackgroundUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class TherapySessionsController implements Initializable {

    @FXML
    private AnchorPane bodyPane;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<TherapySessionTM, Duration> durationCol;

    @FXML
    private TableColumn<TherapySessionTM, String> patientIdCol;

    @FXML
    private TextField patientIdTxt;

    @FXML
    private TextField patientNameTxt;

    @FXML
    private Button patientSearchButton;

    @FXML
    private Button paymentLoadButton;

    @FXML
    private TableColumn<TherapySessionTM, String> programIdCol;

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
    private TableColumn<TherapySessionTM, LocalDate> sessionDateCol;

    @FXML
    private DatePicker sessionDateTxt;

    @FXML
    private ChoiceBox<String> sessionDurationTxt;

    @FXML
    private TableColumn<TherapySessionTM, String> sessionIdCol;

    @FXML
    private TextField sessionIdTxt;

    @FXML
    private TableColumn<TherapySessionTM, String> sessionTimeCol;

    @FXML
    private TextField sessionTimeTxt;

    @FXML
    private TableColumn<TherapySessionTM, String> statusCol;

    @FXML
    private ChoiceBox<String> statusTxtChoice;

    @FXML
    private TableColumn<TherapySessionTM, String> therapistIdCol;

    @FXML
    private TextField therapistIdTxt;

    @FXML
    private ChoiceBox<String> therapistNameTxt;

    @FXML
    private TableView<TherapySessionTM> therapySessionTable;

    @FXML
    private TableView<?> timeSlotTable;
    @FXML
    private TableColumn<?, ?> timeTSCol;
    @FXML
    private TableColumn<?, ?> date1TSCol;
    @FXML
    private TableColumn<?, ?> date2TSCol;
    @FXML
    private TableColumn<?, ?> date3TSCol;
    @FXML
    private TableColumn<?, ?> date4TSCol;
    @FXML
    private TableColumn<?, ?> date5TSCol;

    @FXML
    private Button updateButton;

    private final PatientBO patientBO = new PatientBOImpl();
    private final PatientProgramBO patientProgramBO = new PatientProgramBOImpl();
    private TherapistBO therapistBO = new TherapistBOImpl();
    private final TherapyProgramBO therapyProgramBO = new TherapyProgramBOImpl();
    private final TherapistProgramBO therapistProgramBO = new TherapistProgramBOImpl();


    private final TherapistAvailabiltyBOImpl therapistAvailabiltyBO = new TherapistAvailabiltyBOImpl();

    public void initialize(URL location, ResourceBundle resources) {
        SetBackgroundUtil setBackground = new SetBackgroundUtil();
        setBackground.setBackgroundImage(bodyPane, 1300, 760);

        sessionDurationTxt.getItems().addAll(
                "30 minutes",
                "1 hour",
                "1 and half hour",
                "2 hours"
        );



    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void loadPaymentPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/payments-page.fxml"));
            Parent root = loader.load();

            PaymentsController controller = loader.getController();
            controller.setFromMainPage(true); // pass the boolean
            controller.configurePage();       // apply changes to the UI

            Stage stage = new Stage();
            stage.setTitle("Manage Payments");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onclickTSTable(MouseEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

//        String therapistId = therapistNameTxt.getValue();
//        LocalDate date = sessionDateTxt.getValue();
//        String durationStr = sessionDurationTxt.getValue();
//        Duration sessionDuration = Duration.ofMinutes(Long.parseLong(durationStr));
//
//        boolean success = therapistAvailabiltyBO.bookTimeSlot(therapistId, date, sessionDuration);
//
//        if (success) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Time slot successfully booked!");
//            alert.showAndWait();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to book the time slot. Please try again.");
//            alert.showAndWait();
//        }
//        boolean success = therapistAvailabiltyBO.bookTimeSlot("therapist001", LocalDate.of(2025, 4, 22), Duration.ofMinutes(60));

    }

    @FXML
    void search(ActionEvent event) {
        List<TherapistAvailabilityDto> availability = therapistAvailabiltyBO.findByTherapistAndDate("T002", LocalDate.of(2025, 4, 22));
                List<String> slots = availability.get(0).getAvailableSlots();

        System.out.println("Slots:");
        for (String slot : slots) {
            System.out.println(slot);
        }

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
        programIdTxt.clear();
        programNameTxt.getItems().addAll(programNames);

        programNameTxt.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                searchProgram();
            }
        });
    }

    void searchProgram() {
        String name = programNameTxt.getValue().trim();
        ArrayList<TherapyProgramDto> programs = therapyProgramBO.findTherapyProgramByName(name);

        if (programs.isEmpty()) {
            showAlert("Not Found", "Program not found", Alert.AlertType.WARNING);
            return;
        }

        TherapyProgramDto program = programs.getFirst();
        programIdTxt.setText(program.getProgramId());


        // Load therapists based on the selected program
        List<TherapistProgramDto> therapistsPrograms = therapistProgramBO.findByProgramName(programNameTxt.getValue().trim());

        List<String> therapistNames = therapistsPrograms.stream()
                .map(dto -> therapistBO.findByTherapistId(dto.getTherapistId()).getName())
                .collect(Collectors.toList());

        therapistNameTxt.getItems().clear();
        therapistIdTxt.clear();
        therapistNameTxt.getItems().addAll(therapistNames);

        therapistNameTxt.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                searchTherapist();
            }
        });
    }

    void searchTherapist() {
        String name = therapistNameTxt.getValue().trim();
        ArrayList<TherapistDto> therapists = therapistBO.findByTherapistName(name);

        if (therapists.isEmpty()) {
            showAlert("Not Found", "Therapist not found", Alert.AlertType.WARNING);
            return;
        }

        TherapistDto therapist = therapists.getFirst();
        therapistIdTxt.setText(therapist.getTherapistId());
    }

    @FXML
    void tableClick(MouseEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }


    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
