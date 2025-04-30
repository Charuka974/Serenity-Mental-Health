package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.bo.BOFactory;
import com.assignment.ijse.serenitymentalhealth.bo.custom.TherapistAvailabiltyBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.TherapistBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.TherapistAvailabiltyBOImpl;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.TherapistBOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.TherapistAvailabilityDto;
import com.assignment.ijse.serenitymentalhealth.dto.TherapistDto;
import com.assignment.ijse.serenitymentalhealth.dto.tm.TherapistAvailabilityTM;
import com.assignment.ijse.serenitymentalhealth.util.SetBackgroundUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TherapistAvailabilityController implements Initializable {

    @FXML
    private TableColumn<TherapistAvailabilityTM, LocalDate> availableDateCol;

    @FXML
    private DatePicker availableDateTxt;


    @FXML
    private TableColumn<TherapistAvailabilityTM, Boolean> availableStatusCol;

    @FXML
    private AnchorPane bodyPane;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<TherapistAvailabilityTM, LocalTime> endTimeCol;

    @FXML
    private TextField endTimeTxt;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableColumn<TherapistAvailabilityTM, LocalTime> startTimeCol;

    @FXML
    private TextField startTimeTxt;

    @FXML
    private TextField availabilityIdTxt;

    @FXML
    private ChoiceBox<String> statusTxt;

    @FXML
    private TableView<TherapistAvailabilityTM> therapistAvailabilityTable;

    @FXML
    private TableColumn<TherapistAvailabilityTM, String> therapistIdCol;

    @FXML
    private TextField therapistIdTxt;

    @FXML
    private TextField therapistNameTxt;

    @FXML
    private TableColumn<TherapistAvailabilityTM, String> availableIdCol;

    @FXML
    private Button therapistSearchBtn;

    @FXML
    private ToggleButton tableSortToggleButton;

    @FXML
    private Button updateButton;


    TherapistAvailabiltyBO therapistAvailabiltyBO = (TherapistAvailabiltyBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST_AVAILABILITY);
    TherapistBO therapistBO = (TherapistBO) BOFactory.getInstance().getBO(BOFactory.BOType.THERAPIST);

    DateTimeFormatter timeFormatter = new DateTimeFormatterBuilder().appendPattern("hh:mm a").toFormatter().withLocale(Locale.ENGLISH);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SetBackgroundUtil setBackground = new SetBackgroundUtil();
        setBackground.setBackgroundImage(bodyPane, 1300, 760);


        List<String> availabilityIds = List.of("Available", "Not Available");
        statusTxt.getItems().setAll(availabilityIds);
//        statusTxt.getSelectionModel().selectFirst();

        // Bind the TableColumn to the properties of the TherapistAvailabilityTM class
        availableIdCol.setCellValueFactory(new PropertyValueFactory<>("availabilityId"));
        availableDateCol.setCellValueFactory(new PropertyValueFactory<>("availableDate"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        availableStatusCol.setCellValueFactory(new PropertyValueFactory<>("available"));
        therapistIdCol.setCellValueFactory(new PropertyValueFactory<>("therapistId"));

        loadAllAvailability();
        therapistAvailabilityTable.getItems().sort(
                Comparator.comparing(TherapistAvailabilityTM::getAvailableDate)
        );
        tableSortToggleButton.setText("Sorted by Date");

        searchButton.setText("Search / Clear");
    }


    @FXML
    void sortTable(ActionEvent event) {
        if (tableSortToggleButton.isSelected()) {
            therapistAvailabilityTable.getItems().sort(
                    Comparator.comparing(TherapistAvailabilityTM::getAvailableDate)
            );
            tableSortToggleButton.setText("Sorted by Date");
        } else {
            therapistAvailabilityTable.getItems().sort(
                    Comparator.comparing(TherapistAvailabilityTM::getTherapistId)
            );
            tableSortToggleButton.setText("Sorted by Therapist");
        }
    }


    @FXML
    void delete(ActionEvent event) {
        String availabilityId = availabilityIdTxt.getText();

        boolean success = therapistAvailabiltyBO.deleteAvailability(availabilityId);

        if (success) {
            showAlert("Success", "Availability deleted successfully", Alert.AlertType.INFORMATION);
            clearFields();
            loadAllAvailability();
        } else {
            showAlert("Error", "Failed to delete availability", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void save(ActionEvent event) {
        if (!validateFields()) return;

        try {
            String availabilityId = availabilityIdTxt.getText();
            String therapistId = therapistIdTxt.getText();
            LocalDate availableDate = availableDateTxt.getValue();
            LocalTime startTime = LocalTime.parse(startTimeTxt.getText(), timeFormatter);
            LocalTime endTime = LocalTime.parse(endTimeTxt.getText(), timeFormatter);
            boolean status = statusTxt.getValue().equals("Available");

            System.out.println(startTime);
            System.out.println(endTime);


            TherapistAvailabilityDto dto = new TherapistAvailabilityDto(
                    availabilityId, therapistId, availableDate, startTime, endTime, null, status);


            boolean success = therapistAvailabiltyBO.saveTherapistAvailability(dto);

            if (success) {
                showAlert("Success", "Availability saved successfully", Alert.AlertType.INFORMATION);
                loadAllAvailability();
                clearFields();
            } else {
                showAlert("Error", "Failed to save availability", Alert.AlertType.ERROR);
            }
        } catch (DateTimeParseException e) {
            showAlert("Invalid Time Format", "Please use time format: hh:mm AM/PM", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void search(ActionEvent event) {
        String searchTerm = searchTxt.getText();
        if (searchTerm.isEmpty()){
//            showAlert("Error", "Please enter a search term", Alert.AlertType.ERROR);
            clearFields();
            loadAllAvailability();
            return;
        }

        ArrayList<TherapistDto> therapistList = therapistBO.findByTherapistName(searchTerm);

        if (therapistList != null && !therapistList.isEmpty()) {
            TherapistDto therapist = therapistList.get(0);
            List<TherapistAvailabilityDto> results = therapistAvailabiltyBO.findByTherapistId(therapist.getTherapistId());
            List<TherapistAvailabilityTM> tmList = new ArrayList<>();
            for (TherapistAvailabilityDto dto : results) {
                tmList.add(new TherapistAvailabilityTM(
                        dto.getAvailabilityId(),
                        dto.getTherapistId(),
                        dto.getAvailableDate(),
                        dto.getStartTime(),
                        dto.getEndTime(),
                        dto.isAvailable()
                ));
            }
            therapistAvailabilityTable.getItems().clear();
            therapistAvailabilityTable.getItems().addAll(tmList);
        } else {
            showAlert("Error", "Therapist not found", Alert.AlertType.ERROR);
        }
    }


    @FXML
    void therapistSearch(ActionEvent event) {
        String therapistName = therapistNameTxt.getText();

        if (therapistName.isEmpty()) {
            return;
        }

        ArrayList<TherapistDto> therapistList = therapistBO.findByTherapistName(therapistName);

        if (therapistList != null && !therapistList.isEmpty()) {
            TherapistDto therapist = therapistList.get(0);
            therapistIdTxt.setText(therapist.getTherapistId());
            therapistNameTxt.setText(therapist.getName());

        } else {
            showAlert("Error", "Therapist not found", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void update(ActionEvent event) {
        if (!validateFields()) return;

        try {
            String availabilityId = availabilityIdTxt.getText();
            String therapistId = therapistIdTxt.getText();
            LocalDate availableDate = availableDateTxt.getValue();
            LocalTime startTime = LocalTime.parse(startTimeTxt.getText(), timeFormatter);
            LocalTime endTime = LocalTime.parse(endTimeTxt.getText(), timeFormatter);
            boolean status = statusTxt.getValue().equals("Available");

            TherapistAvailabilityDto dto = new TherapistAvailabilityDto(
                    availabilityId, therapistId, availableDate, startTime, endTime, null, status);

            boolean success = therapistAvailabiltyBO.updateTherapistAvailability(dto);

            if (success) {
                showAlert("Success", "Availability updated successfully", Alert.AlertType.INFORMATION);
                loadAllAvailability();
                clearFields();
            } else {
                showAlert("Error", "Failed to update availability", Alert.AlertType.ERROR);
            }
        } catch (DateTimeParseException e) {
            showAlert("Invalid Time Format", "Please use time format: hh:mm AM/PM", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void tableClick(MouseEvent event) {
        TherapistAvailabilityTM selectedAvailability = therapistAvailabilityTable.getSelectionModel().getSelectedItem();

        if (selectedAvailability != null) {
            availabilityIdTxt.setText(selectedAvailability.getAvailabilityId());
            therapistIdTxt.setText(selectedAvailability.getTherapistId());
            availableDateTxt.setValue(selectedAvailability.getAvailableDate());
            startTimeTxt.setText(selectedAvailability.getStartTime().format(timeFormatter));
            endTimeTxt.setText(selectedAvailability.getEndTime().format(timeFormatter));
            statusTxt.setValue(selectedAvailability.isAvailable() ? "Available" : "Not Available");
        }
    }

    private void loadAllAvailability() {
        List<TherapistAvailabilityDto> allAvailability = therapistAvailabiltyBO.getAllAvailability();

        List<TherapistAvailabilityTM> tmList = new ArrayList<>();
        for (TherapistAvailabilityDto dto : allAvailability) {
            tmList.add(new TherapistAvailabilityTM(
                    dto.getAvailabilityId(),
                    dto.getTherapistId(),
                    dto.getAvailableDate(),
                    dto.getStartTime(),
                    dto.getEndTime(),
                    dto.isAvailable()
            ));
        }

        therapistAvailabilityTable.getItems().clear();
        therapistAvailabilityTable.getItems().addAll(tmList);
    }


    private void clearFields() {
        availabilityIdTxt.clear();
        therapistIdTxt.clear();
        therapistNameTxt.clear();
        availableDateTxt.setValue(null);
        startTimeTxt.clear();
        endTimeTxt.clear();
        statusTxt.setValue(null);
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType, message);
        alert.setTitle(title);
        alert.showAndWait();
    }

    private boolean validateFields() {
        if (therapistIdTxt.getText().isEmpty() ||
                availableDateTxt.getValue() == null || startTimeTxt.getText().isEmpty() ||
                endTimeTxt.getText().isEmpty() || statusTxt.getValue() == null) {
            showAlert("Missing Fields", "Please fill in all fields before proceeding.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }


}
