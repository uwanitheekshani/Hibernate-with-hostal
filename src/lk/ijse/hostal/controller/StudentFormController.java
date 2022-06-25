package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

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

    public void btnNewStudentOnAction(ActionEvent actionEvent) {
    }

    public void saveStudentOnAction(ActionEvent actionEvent) {
    }

    public void deleteStudentOnAction(ActionEvent actionEvent) {
    }
}
