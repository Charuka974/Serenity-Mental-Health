package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.bo.custom.TherapistAvailabiltyBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.TherapistBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.TherapistAvailabiltyBOImpl;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.TherapistBOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.PatientDto;
import com.assignment.ijse.serenitymentalhealth.dto.TherapistAvailabilityDto;
import com.assignment.ijse.serenitymentalhealth.dto.TherapistDto;
import com.assignment.ijse.serenitymentalhealth.dto.tm.TherapistAvailabilityTM;
import com.assignment.ijse.serenitymentalhealth.entity.Therapist;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TherapistAvailabilityController implements Initializable {

    @FXML
    private TextField availabilityIdTxt;

    @FXML
    private TableColumn<TherapistAvailabilityTM, LocalDate> availableDateCol;

    @FXML
    private DatePicker availableDateTxt;

    @FXML
    private TableColumn<TherapistAvailabilityTM, String> availableIdCol;

    @FXML
    private TableColumn<TherapistAvailabilityTM, String> availableStatusCol;

    @FXML
    private TableColumn<TherapistAvailabilityTM, LocalTime> availableTimeCol;

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
    private Button therapistSearchBtn;

    @FXML
    private Button updateButton;

    private final TherapistAvailabiltyBO availabilityBO = new TherapistAvailabiltyBOImpl();
    private final TherapistBO therapistBO = new TherapistBOImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusTxt.getItems().addAll("Available", "Not Available");
        statusTxt.setValue("Available");
        setTimeToTimePicker();
        initializeColumns();
        refreshPage();
    }

    private void initializeColumns() {
        availableIdCol.setCellValueFactory(new PropertyValueFactory<>("availabilityId"));
        therapistIdCol.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        availableDateCol.setCellValueFactory(new PropertyValueFactory<>("availableDate"));
        availableTimeCol.setCellValueFactory(new PropertyValueFactory<>("availableTime"));
        availableStatusCol.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));
    }

    private void refreshPage() {
        availabilityIdTxt.clear();
        therapistIdTxt.clear();
        therapistNameTxt.clear();
        availableDateTxt.setValue(null);
        availableTimeTxt.setValue("09:00 AM");
        statusTxt.setValue("Available");

        availabilityIdTxt.setText(availabilityBO.getNextPK());
        refreshTable();
    }

    private void refreshTable() {
        ArrayList<TherapistAvailabilityDto> availabilityDtos = availabilityBO.getAllAvailabilities();
        ObservableList<TherapistAvailabilityTM> items = FXCollections.observableArrayList();

        for (TherapistAvailabilityDto dto : availabilityDtos) {
            items.add(new TherapistAvailabilityTM(
                    dto.getAvailabilityId(),
                    dto.getTherapistId(),
                    dto.getAvailableDate(),
                    dto.getAvailableTime(),
                    dto.isAvailable() ? "Available" : "Not Available"
            ));
        }

        therapistAvailabilityTable.setItems(items);
    }

    @FXML
    void save(ActionEvent event) {
        TherapistAvailabilityDto dto = getDtoFromFields();
        if (dto != null && availabilityBO.saveAvailability(dto)) {
            showAlert("Success", "Availability saved successfully", Alert.AlertType.INFORMATION);
            refreshPage();
        } else {
            showAlert("Error", "Failed to save availability", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void update(ActionEvent event) {
        TherapistAvailabilityDto dto = getDtoFromFields();
        if (dto != null && availabilityBO.updateAvailability(dto)) {
            showAlert("Success", "Availability updated successfully", Alert.AlertType.INFORMATION);
            refreshPage();
        } else {
            showAlert("Error", "Failed to update availability", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void delete(ActionEvent event) {
        String id = availabilityIdTxt.getText();
        if (availabilityBO.deleteAvailability(id)) {
            showAlert("Success", "Availability deleted successfully", Alert.AlertType.INFORMATION);
            refreshPage();
        } else {
            showAlert("Error", "Failed to delete availability", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void search(ActionEvent event) {
        String therapistName = searchTxt.getText().trim();
        if (therapistName.isEmpty()) {
            showAlert("Input Error", "Enter a therapist name to search", Alert.AlertType.WARNING);
            refreshPage();
            return;
        }

        ArrayList<TherapistAvailabilityDto> results = availabilityBO.findByTherapist(therapistName);
        ObservableList<TherapistAvailabilityTM> list = FXCollections.observableArrayList();

        for (TherapistAvailabilityDto dto : results) {
            list.add(new TherapistAvailabilityTM(
                    dto.getAvailabilityId(),
                    dto.getTherapistId(),
                    dto.getAvailableDate(),
                    dto.getAvailableTime(),
                    dto.isAvailable() ? "Available" : "Not Available"
            ));
        }

        if (list.isEmpty()) {
            showAlert("Not Found", "No availability found", Alert.AlertType.WARNING);
            therapistAvailabilityTable.setItems(FXCollections.emptyObservableList());
        } else {
            therapistAvailabilityTable.setItems(list);
        }
    }

    @FXML
    void therapistSearch(ActionEvent event) {
        String name = therapistNameTxt.getText().trim();
        ArrayList<TherapistDto> therapists = therapistBO.findByTherapistName(name);

        if (therapists.isEmpty()) {
            showAlert("Not Found", "Therapist not found", Alert.AlertType.WARNING);
            return;
        }

        TherapistDto therapist = therapists.getFirst();

        therapistIdTxt.setText(therapist.getTherapistId());
        therapistNameTxt.setText(therapist.getName());
    }

    @FXML
    void tableClick(MouseEvent event) {
        TherapistAvailabilityTM selected = therapistAvailabilityTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            availabilityIdTxt.setText(selected.getAvailabilityId());
            therapistIdTxt.setText(selected.getTherapistId());
            therapistNameTxt.setText(therapistBO.findByTherapistId(selected.getTherapistId()).getName());
            availableDateTxt.setValue(selected.getAvailableDate());
            convertTimeBack(selected.getAvailableTime());
            statusTxt.setValue(selected.getIsAvailable());
        }
    }

    private TherapistAvailabilityDto getDtoFromFields() {
        try {
            return new TherapistAvailabilityDto(
                    availabilityIdTxt.getText(),
                    therapistIdTxt.getText(),
                    availableDateTxt.getValue(),
                    convertTime(),
                    statusTxt.getValue().equals("Available")
            );
        } catch (Exception e) {
            showAlert("Input Error", "Please check your inputs.", Alert.AlertType.WARNING);
            return null;
        }
    }


    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void setTimeToTimePicker() {
        availableTimeTxt.getItems().addAll(
                "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
                "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM"
        );
        availableTimeTxt.setValue("09:00 AM");
    }

    public LocalTime convertTime() {
        String time = availableTimeTxt.getValue();
        LocalTime convertedTime = null;

        switch (time) {
            case "09:00 AM" -> convertedTime = LocalTime.of(9, 0);
            case "10:00 AM" -> convertedTime = LocalTime.of(10, 0);
            case "11:00 AM" -> convertedTime = LocalTime.of(11, 0);
            case "12:00 PM" -> convertedTime = LocalTime.of(12, 0);
            case "01:00 PM" -> convertedTime = LocalTime.of(13, 0);
            case "02:00 PM" -> convertedTime = LocalTime.of(14, 0);
            case "03:00 PM" -> convertedTime = LocalTime.of(15, 0);
            case "04:00 PM" -> convertedTime = LocalTime.of(16, 0);
            case "05:00 PM" -> convertedTime = LocalTime.of(17, 0);
        }

        return convertedTime;
    }

    public void convertTimeBack(LocalTime localTime) {
        String convertedTime = null;
        if (localTime.equals(LocalTime.of(9, 0))) {
            convertedTime = "09:00 AM";
        } else if (localTime.equals(LocalTime.of(10, 0))) {
            convertedTime = "10:00 AM";
        } else if (localTime.equals(LocalTime.of(11, 0))) {
            convertedTime = "11:00 AM";
        } else if (localTime.equals(LocalTime.of(12, 0))) {
            convertedTime = "12:00 PM";
        } else if (localTime.equals(LocalTime.of(13, 0))) {
            convertedTime = "01:00 PM";
        } else if (localTime.equals(LocalTime.of(14, 0))) {
            convertedTime = "02:00 PM";
        } else if (localTime.equals(LocalTime.of(15, 0))) {
            convertedTime = "03:00 PM";
        } else if (localTime.equals(LocalTime.of(16, 0))) {
            convertedTime = "04:00 PM";
        } else if (localTime.equals(LocalTime.of(17, 0))) {
            convertedTime = "05:00 PM";
        }
        availableTimeTxt.setValue(convertedTime);
    }



}
