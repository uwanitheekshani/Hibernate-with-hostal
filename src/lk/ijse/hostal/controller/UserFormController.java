package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostal.bo.BOFactory;
import lk.ijse.hostal.bo.custom.StudentBO;
import lk.ijse.hostal.bo.custom.UserBO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.dto.UserDTO;
import lk.ijse.hostal.view.tdm.StudentTM;
import lk.ijse.hostal.view.tdm.UserTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserFormController {
    public AnchorPane userManageContext;
    public JFXTextField txtIUserId;
    public JFXTextField txtPassword;
    public JFXTextField txtUserName;
    public Button btnSaveUser;
    public Button btnDeleteUser;
    public TableView<UserTM> tblUser;

    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);


    public void initialize() {
        tblUser.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userId"));
        tblUser.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblUser.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("password"));

        initUI();


        tblUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteUser.setDisable(newValue == null);
            btnSaveUser.setText(newValue != null ? "Update" : "Save");
            btnSaveUser.setDisable(newValue == null);

            if (newValue != null) {
                txtIUserId.setText(newValue.getUserId());
                txtUserName.setText(newValue.getUserName());
                txtPassword.setText(newValue.getPassword());

                txtIUserId.setDisable(false);
                txtUserName.setDisable(false);
                txtPassword.setDisable(false);
            }
        });


        loadAllUsers();
    }

    private void loadAllUsers() {
        tblUser.getItems().clear();

        try {
            List<UserDTO> allUsers = userBO.getAll();
            for (UserDTO user : allUsers) {
                tblUser.getItems().add(new UserTM(user.getUserId(), user.getUserName(), user.getPassword()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtIUserId.clear();
        txtUserName.clear();
        txtPassword.clear();
        txtIUserId.setDisable(true);
        txtUserName.setDisable(true);
        txtPassword.setDisable(true);
        btnSaveUser.setDisable(true);
        btnDeleteUser.setDisable(true);
    }

    public void btnNewUserOnAction(ActionEvent actionEvent) {
        txtIUserId.setDisable(false);
        txtUserName.setDisable(false);
        txtPassword.setDisable(false);

        txtIUserId.clear();
        txtUserName.clear();
        txtPassword.clear();

        txtIUserId.requestFocus();
        btnSaveUser.setDisable(false);
        btnSaveUser.setText("Save");
        tblUser.getSelectionModel().clearSelection();
    }

    public void deleteUserOnAction(ActionEvent actionEvent) {
        String id = tblUser.getSelectionModel().getSelectedItem().getUserId();
        try {
            userBO.deleteUser(id);
            tblUser.getItems().remove(tblUser.getSelectionModel().getSelectedItem());
            tblUser.getSelectionModel().clearSelection();
            new Alert(Alert.AlertType.CONFIRMATION,"Delete Successfully").show();
            //initUI();
            btnSaveUser.setDisable(false);
            btnDeleteUser.setDisable(false);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }

    public void saveUserOnAction(ActionEvent actionEvent) {
        String userId = txtIUserId.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

//        if (!name.matches("^[A-Z ][a-z]{1,}$")) {
//            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
//            txtCustomerName.requestFocus();
//            return;
//        } else if (!address.matches(".{3,}")) {
//            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
//            txtCustomerAddress.requestFocus();
//            return;
//        }else if (!city.matches("^[A-Z ][a-z]{1,}$")) {
//            new Alert(Alert.AlertType.ERROR, "Invalid city").show();
//            txtCity.requestFocus();
//            return;
//        }else if (!province.matches("^[A-Z ][a-z]{1,}$")) {
//            new Alert(Alert.AlertType.ERROR, "Invalid province").show();
//            txtProvince.requestFocus();
//            return;
//        }else if (!postalCode.matches("^[A-z0-9 ,/]{4,20}$")) {
//            new Alert(Alert.AlertType.ERROR, "Invalid postal code").show();
//            txtPostalCode.requestFocus();
//            return;
//        }
        try {
            if (btnSaveUser.getText().equalsIgnoreCase("save")) {

                if (userBO.saveUser(new UserDTO(userId, userName, password))) {
                    tblUser.getItems().add(new UserTM(userId, userName, password));
                    initUI();

                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed!").show();
                }


            } else {

                userBO.updateUser(new UserDTO(userId, userName, password));

                UserTM selectedStudent = tblUser.getSelectionModel().getSelectedItem();
                selectedStudent.setUserId(userId);
                selectedStudent.setUserName(userName);
                selectedStudent.setPassword(password);
                tblUser.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                //initUI();

            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }
}
