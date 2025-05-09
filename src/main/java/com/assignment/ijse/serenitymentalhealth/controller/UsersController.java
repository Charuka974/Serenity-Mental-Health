package com.assignment.ijse.serenitymentalhealth.controller;

import com.assignment.ijse.serenitymentalhealth.bo.BOFactory;
import com.assignment.ijse.serenitymentalhealth.bo.custom.UserBO;
import com.assignment.ijse.serenitymentalhealth.bo.custom.impl.UserBOImpl;
import com.assignment.ijse.serenitymentalhealth.dto.UserDto;
import com.assignment.ijse.serenitymentalhealth.dto.tm.UserTM;
import com.assignment.ijse.serenitymentalhealth.util.KeepUserIDUtil;
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

import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements Initializable{


    @FXML
    private AnchorPane bodyPane;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<UserTM, String> passwordCol;

    @FXML
    private TableColumn<UserTM, String> userIdCol;

    @FXML
    private TextField userIdTxt;

    @FXML
    private Button saveButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTxt;

    @FXML
    private Button updateButton;

    @FXML
    private TableColumn<UserTM, String> userEmailCol;

    @FXML
    private TextField userEmailTxt;

    @FXML
    private TextField userPasswordTxt;

    @FXML
    private TableColumn<UserTM, String> userRoleCol;

    @FXML
    private ComboBox<String> userRoleTxt;

    @FXML
    private TableColumn<UserTM, String> usernameCol;

    @FXML
    private TextField usernameTxt;

    @FXML
    private TableView<UserTM> usersTable;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOType.USER);

    private ObservableList<UserTM> userTMList = FXCollections.observableArrayList();

    String loggedInUserId = null;

    public void initialize(URL location, ResourceBundle resources) {
        SetBackgroundUtil setBackground = new SetBackgroundUtil();
        setBackground.setBackgroundImage(bodyPane, 1300, 760);

        userRoleTxt.setItems(FXCollections.observableArrayList("Admin", "Receptionist"));

        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        userEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        userRoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        try {
            clearForm();
            loadAllUsers();
        }catch (Exception e) {
            throw e;
        }

        loadAllUsers();

        loggedInUserId = KeepUserIDUtil.getInstance().getCurrentUserId();
        updateButton.setVisible(false);
        saveButton.setVisible(false);
        searchButton.setText("Search / Clear");



    }

    private void loadAllUsers() {
        userTMList.clear();
        for (UserDto dto : userBO.getAllUsers()) {
            userTMList.add(new UserTM(
                    dto.getUserId(),
                    dto.getUsername(),
                    dto.getPassword(),
                    dto.getEmail(),
                    dto.getRole()

            ));
        }
        usersTable.setItems(userTMList);
    }

    @FXML
    void save(ActionEvent event) {
        String id = userBO.generateNextUserId();
        String username = usernameTxt.getText();
        String email = userEmailTxt.getText();
        String role = userRoleTxt.getValue();
        String password = userPasswordTxt.getText();

        boolean isSaved = userBO.registerUser(new UserDto(id, username, password, email, role));
        if (isSaved) {
            loadAllUsers();
            clearForm();
            new Alert(Alert.AlertType.INFORMATION, "User saved!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save user").show();
        }
    }

    @FXML
    void update(ActionEvent event) {
        if (userIdTxt.getText() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a user from the table").show();
            return;
        }

        boolean isUpdated = userBO.updateUser(new UserDto(
                userIdTxt.getText(),
                usernameTxt.getText(),
                userPasswordTxt.getText(),
                userEmailTxt.getText(),
                userRoleTxt.getValue()
        ));

        if (isUpdated) {
            loadAllUsers();
            clearForm();
            new Alert(Alert.AlertType.INFORMATION, "User updated!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update user").show();
        }
    }

    @FXML
    void delete(ActionEvent event) {
        if (userIdTxt.getText() == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a user to delete").show();
            return;
        }

        boolean isDeleted = userBO.deleteUser(userIdTxt.getText());
        if (isDeleted) {
            loadAllUsers();
            clearForm();
            new Alert(Alert.AlertType.INFORMATION, "User deleted!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to delete user").show();
        }
    }

    @FXML
    void search(ActionEvent event) {
        String name = searchTxt.getText();
        if (name.isEmpty()) {
//            new Alert(Alert.AlertType.WARNING, "Please enter a username to search").show();
            loadAllUsers();
            clearForm();
            return;
        }

        userTMList.clear();

        for (UserDto dto : userBO.searchUser(name)) {
            userTMList.add(new UserTM(
                    dto.getUserId(),
                    dto.getUsername(),
                    dto.getPassword(),
                    dto.getEmail(),
                    dto.getRole()
            ));
        }
        usersTable.setItems(userTMList);

        if (userTMList.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "No users found for that username").show();
        }
    }


    @FXML
    void tableClick(MouseEvent event) {
        UserTM selected = usersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            userIdTxt.setText(selected.getUserId());
            usernameTxt.setText(selected.getUsername());
            userEmailTxt.setText(selected.getEmail());
            userPasswordTxt.setText(selected.getPassword());
            userRoleTxt.setValue(selected.getRole());
        }
    }

    private void setNextUserId() {
        String nextId = userBO.generateNextUserId();
        userIdTxt.setText(nextId);
    }


    private void clearForm() {
        setNextUserId();
        usernameTxt.clear();
        userEmailTxt.clear();
        userPasswordTxt.clear();
        userRoleTxt.getSelectionModel().clearSelection();
    }

}
