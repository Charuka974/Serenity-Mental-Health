package com.assignment.ijse.serenitymentalhealth.controller;


import com.assignment.ijse.serenitymentalhealth.db.DBConnection;
import com.assignment.ijse.serenitymentalhealth.util.SetBackgroundUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.fill.*;


import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;


public class WelcomeController implements Initializable {

    @FXML
    private AnchorPane bodyPane;

    @FXML
    private Button report1Btn;

    @FXML
    private Button report2Btn;

    @FXML
    private Button report3Btn;

    @FXML
    private Button report4Btn;

    @FXML
    private Button report5Btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SetBackgroundUtil setBackground = new SetBackgroundUtil();
        setBackground.setBackgroundImage(bodyPane, 1300, 760);

        report1Btn.setText("Patient Therapy History");
        report2Btn.setText("Patient Details");
        report3Btn.setText("Report 3");
        report4Btn.setText("Report 4");
        report5Btn.setText("Report 5");

    }



    @FXML
    void loadReport1(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Patient ID Input");
        dialog.setHeaderText("Enter the Patient ID to generate the report");
        dialog.setContentText("Patient ID:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(patientId -> {
            try {
                String path = "/reports/Patient_Therapy_History_Report.jrxml";
                printReport("patientID", patientId, path);
            } catch (JRException | SQLException | ClassNotFoundException e) {
                showAlert("Error", "Failed to load report: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        });

    }


    @FXML
    void loadReport2(ActionEvent event) throws JRException, SQLException, ClassNotFoundException  {
        printReportWithoutParameter("/reports/Serenity_Report_1.jrxml");
    }

    @FXML
    void loadReport3(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {

    }

    @FXML
    void loadReport4(ActionEvent event) throws JRException, SQLException, ClassNotFoundException  {

    }

    @FXML
    void loadReport5(ActionEvent event) throws JRException, SQLException, ClassNotFoundException  {

    }


    public void printReport(String parameterName, String parameterValue, String path) throws JRException, SQLException, ClassNotFoundException {
        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream(path));
        Connection connection = DBConnection.getInstance().getConnection();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(parameterName, parameterValue);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void printReportWithoutParameter(String path) throws JRException, SQLException, ClassNotFoundException {
        JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream(path));
        Connection connection = DBConnection.getInstance().getConnection();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("patientID", "P001");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
        JasperViewer.viewReport(jasperPrint, false);
    }

    public void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




}
