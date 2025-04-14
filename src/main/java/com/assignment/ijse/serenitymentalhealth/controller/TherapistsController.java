package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.bo.custom.TherapistBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.TherapistBOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.TherapistDto;
import com.assignment.ijse.serenitymentalhealth.dto.tm.TherapistTM;
import com.assignment.ijse.serenitymentalhealth.util.NavigationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TherapistsController implements Initializable {


    @FXML
    private Button deleteButton;

    @FXML
    private Button therapistAvailabilityBtn;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableColumn<TherapistTM, String> therapistEmailCol;

    @FXML
    private TextField therapistEmailTxt;

    @FXML
    private TableColumn<TherapistTM, String> therapistIdCol;

    @FXML
    private TextField therapistIdTxt;

    @FXML
    private TableColumn<TherapistTM, String> therapistNameCol;

    @FXML
    private TextField therapistNameTxt;

    @FXML
    private TableColumn<TherapistTM, String> therapistPhoneCol;

    @FXML
    private TextField therapistPhoneTxt;

    @FXML
    private TableColumn<TherapistTM, String> therapistSpecsCol;

    @FXML
    private TextField therapistSpecsTxt;

    @FXML
    private TableView<TherapistTM> therapistsTable;

    @FXML
    private Button updateButton;

    NavigationUtil navigate = new NavigationUtil();

    private final TherapistBO therapistBO = new TherapistBOImpl();

    @FXML
    void loadTherapistAvailabilityPage(ActionEvent event) {
        navigate.navigatePopup("/view/therapist-availability-page.fxml", "Manage Therapist Availability");
    }

    public void initialize(URL location, ResourceBundle resources) {

        therapistIdCol.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        therapistNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        therapistEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        therapistPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        therapistSpecsCol.setCellValueFactory(new PropertyValueFactory<>("specialization"));

        try {
            refreshPage();
        } catch (Exception e) {
            throw e;
        }
    }

    public void refreshPage() {
        therapistIdTxt.clear();
        therapistNameTxt.clear();
        therapistEmailTxt.clear();
        therapistPhoneTxt.clear();
        therapistSpecsTxt.clear();
        try {
            therapistIdTxt.setText(therapistBO.getNextTherapistPK());
            refreshTable();
        } catch (Exception e) {
            throw e;
        }
    }

    public void refreshTable() {
        ArrayList<TherapistDto> therapistDtos = therapistBO.getAllTherapists();
        if (therapistDtos != null && !therapistDtos.isEmpty()) {
            ObservableList<TherapistTM> therapistTMS = FXCollections.observableArrayList();
            for (TherapistDto therapistDto : therapistDtos) {
                TherapistTM therapist = new TherapistTM(
                        therapistDto.getTherapistId(),
                        therapistDto.getName(),
                        therapistDto.getEmail(),
                        therapistDto.getPhone(),
                        therapistDto.getSpecialization()
                );
                therapistTMS.add(therapist);
            }
            therapistsTable.setItems(therapistTMS);
        } else {
            therapistsTable.setItems(FXCollections.emptyObservableList());
        }
    }

    @FXML
    void tableClick(MouseEvent event) {
        TherapistTM selectedTherapist = therapistsTable.getSelectionModel().getSelectedItem();
        if (selectedTherapist != null) {
            therapistIdTxt.setText(selectedTherapist.getTherapistId());
            therapistNameTxt.setText(selectedTherapist.getName());
            therapistEmailTxt.setText(selectedTherapist.getEmail());
            therapistPhoneTxt.setText(selectedTherapist.getPhone());
            therapistSpecsTxt.setText(selectedTherapist.getSpecialization());
        }
    }

    @FXML
    void delete(ActionEvent event) {
        String therapistId = therapistIdTxt.getText();
        if (therapistBO.deleteTherapist(therapistId)) {
            showAlert("Success", "Therapist deleted successfully", Alert.AlertType.INFORMATION);
            refreshPage();
        } else {
            showAlert("Error", "Failed to delete therapist", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void save(ActionEvent event) {
        TherapistDto therapistDto = new TherapistDto(
                therapistIdTxt.getText(),
                therapistNameTxt.getText(),
                therapistEmailTxt.getText(),
                therapistPhoneTxt.getText(),
                therapistSpecsTxt.getText()
        );

        if (therapistBO.saveTherapist(therapistDto)) {
            showAlert("Success", "Therapist saved successfully", Alert.AlertType.INFORMATION);
            refreshPage();
        } else {
            showAlert("Error", "Failed to save therapist", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void search(ActionEvent event) {
        String name = searchTxt.getText().trim();

        if (name.isEmpty()) {
            showAlert("Input Error", "Please enter a name to search", Alert.AlertType.WARNING);
            refreshPage();
            return;
        }

        ArrayList<TherapistDto> therapistDtos = therapistBO.findByTherapistName(name);

        if (therapistDtos != null && !therapistDtos.isEmpty()) {
            ObservableList<TherapistTM> therapistTMS = FXCollections.observableArrayList();

            for (TherapistDto therapistDto : therapistDtos) {
                TherapistTM therapistTM = new TherapistTM(
                        therapistDto.getTherapistId(),
                        therapistDto.getName(),
                        therapistDto.getEmail(),
                        therapistDto.getPhone(),
                        therapistDto.getSpecialization()
                );
                therapistTMS.add(therapistTM);
            }

            therapistsTable.setItems(therapistTMS);
        } else {
            showAlert("Not Found", "No therapist found with that name", Alert.AlertType.WARNING);
            therapistsTable.setItems(FXCollections.emptyObservableList());
        }
    }

    @FXML
    void update(ActionEvent event) {
        TherapistDto therapistDto = new TherapistDto(
                therapistIdTxt.getText(),
                therapistNameTxt.getText(),
                therapistEmailTxt.getText(),
                therapistPhoneTxt.getText(),
                therapistSpecsTxt.getText()
        );

        if (therapistBO.updateTherapist(therapistDto)) {
            showAlert("Success", "Therapist updated successfully", Alert.AlertType.INFORMATION);
            refreshPage();
        } else {
            showAlert("Error", "Failed to update therapist", Alert.AlertType.ERROR);
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
