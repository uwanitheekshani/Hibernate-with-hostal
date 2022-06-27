package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostal.bo.BOFactory;
import lk.ijse.hostal.bo.custom.StudentBO;

public class StudentFormController {
    public JFXTextField txtIStudentId;
    public JFXTextField txtContactNo;
    public JFXTextField txtDateOfBirth;
    public JFXTextField txtStudentAddress;
    public Button btnSaveStudent;
    public Button btnDeleteStudent;
    public JFXTextField txtStudentName;
    public JFXComboBox cmbGender;
    public TableView tblStudent;
    public AnchorPane manageStudentContext;


    private final StudentBO customerBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);


    public void initialize() {
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("custId"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("custTitle"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("custName"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        tblCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("city"));
        tblCustomer.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("province"));
        tblCustomer.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        initUI();

        ObservableList<String> obl = FXCollections.observableArrayList();

        obl.add("Mr");
        obl.add("Mrs");
        obl.add("Miss");

        cmbCustomerTitle.setItems(obl);

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteCustomer.setDisable(newValue == null);
            btnsaveCustomer.setText(newValue != null ? "Update" : "Save");
            btnsaveCustomer.setDisable(newValue == null);

            if (newValue != null) {
                txtICustomerId.setText(newValue.getCustId());
                cmbCustomerTitle.setValue(newValue.getCustTitle());
                txtCustomerName.setText(newValue.getCustName());
                txtCustomerAddress.setText(newValue.getCustAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setText(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());

                txtICustomerId.setDisable(false);
                cmbCustomerTitle.setDisable(false);
                txtCustomerName.setDisable(false);
                txtCustomerAddress.setDisable(false);
                txtCity.setDisable(false);
                txtProvince.setDisable(false);
                txtPostalCode.setDisable(false);

            }
        });

        //txtCustomerAddress.setOnAction(event -> btnsaveCustomer.fire());
        loadAllCustomers();
    }


    public void btnNewStudentOnAction(ActionEvent actionEvent) {
    }

    public void saveStudentOnAction(ActionEvent actionEvent) {
    }

    public void deleteStudentOnAction(ActionEvent actionEvent) {
    }
}
