package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.view.tdm.StudentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentFormController {
    public JFXTextField txtIStudentId;
    public JFXTextField txtContactNo;
    public JFXTextField txtDateOfBirth;
    public JFXTextField txtStudentAddress;
    public Button btnSaveStudent;
    public Button btnDeleteStudent;
    public JFXTextField txtStudentName;
    public JFXComboBox<String> cmbGender;
    public TableView<StudentTM> tblStudent;
    public AnchorPane manageStudentContext;


    private final StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);


    public void initialize() {
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentName"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("studentAddress"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        initUI();

        ObservableList<String> obl = FXCollections.observableArrayList();

        obl.add("Male");
        obl.add("Female");

        cmbGender.setItems(obl);

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteStudent.setDisable(newValue == null);
            btnSaveStudent.setText(newValue != null ? "Update" : "Save");
            btnSaveStudent.setDisable(newValue == null);

            if (newValue != null) {
                txtIStudentId.setText(newValue.getStudent_id());
                txtStudentName.setText(newValue.getStudentName());
                txtStudentAddress.setText(newValue.getStudentAddress());
                txtContactNo.setText(newValue.getContact_no());
                txtDateOfBirth.setText(newValue.getDob());
                cmbGender.setValue(newValue.getGender());

                txtIStudentId.setDisable(false);
                txtStudentName.setDisable(false);
                txtStudentAddress.setDisable(false);
                txtContactNo.setDisable(false);
                txtDateOfBirth.setDisable(false);
                cmbGender.setDisable(false);

            }
        });


        loadAllStudents();
    }

    private void loadAllStudents() {
        tblStudent.getItems().clear();

        try {
            List<StudentDTO> allStudents = studentBO.getAll();
            for (StudentDTO student : allStudents) {
                tblStudent.getItems().add(new StudentTM(student.getStudent_id(), student.getStudentName(), student.getStudentAddress(), student.getContact_no(), student.getDob(), student.getGender()));
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
        txtIStudentId.clear();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtContactNo.clear();
        txtDateOfBirth.clear();
        cmbGender.getSelectionModel().clearSelection();
        txtIStudentId.setDisable(true);
        txtStudentName.setDisable(true);
        txtStudentAddress.setDisable(true);
        txtContactNo.setDisable(true);
        txtDateOfBirth.setDisable(true);
        cmbGender.setDisable(true);
        btnSaveStudent.setDisable(true);
        btnDeleteStudent.setDisable(true);
    }


    public void btnNewStudentOnAction(ActionEvent actionEvent) {
        txtIStudentId.setDisable(false);
        txtStudentName.setDisable(false);
        txtStudentAddress.setDisable(false);
        txtContactNo.setDisable(false);
        txtDateOfBirth.setDisable(false);
        cmbGender.setDisable(false);

        txtIStudentId.clear();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtContactNo.clear();
        txtDateOfBirth.clear();
        cmbGender.getSelectionModel().clearSelection();

        txtIStudentId.requestFocus();
        btnSaveStudent.setDisable(false);
        btnSaveStudent.setText("Save");
        tblStudent.getSelectionModel().clearSelection();
    }

    public void saveStudentOnAction(ActionEvent actionEvent) {

        String studentId = txtIStudentId.getText();
        String studentName = txtStudentName.getText();
        String studentAddress = txtStudentAddress.getText();
        String contactNo = txtContactNo.getText();
        String dob = txtDateOfBirth.getText();
        String gender = (String) cmbGender.getValue();


        if (!studentId.matches("^(S00-)[0-9]{3,5}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student Id").show();
            txtStudentName.requestFocus();
            return;
        } else if (!studentName.matches("^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student Name").show();
            txtStudentName.requestFocus();
            return;
        } else if (!studentAddress.matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtStudentAddress.requestFocus();
            return;
        }else if (!contactNo.matches("^07(7|6|8|1|2|5|0|4)-[0-9]{7}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact Number").show();
            txtContactNo.requestFocus();
            return;
        }else if (!dob.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Date Of Birth").show();
            txtDateOfBirth.requestFocus();
            return;
        }
        try {
            if (btnSaveStudent.getText().equalsIgnoreCase("save")) {

                if (studentBO.saveStudent(new StudentDTO(studentId, studentName, studentAddress, contactNo, dob, gender))) {
                    tblStudent.getItems().add(new StudentTM(studentId, studentName, studentAddress, contactNo, dob, gender));
                    initUI();

                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed!").show();
                }


            } else {

                studentBO.updateStudent(new StudentDTO(studentId, studentName, studentAddress, contactNo, dob, gender));

                StudentTM selectedStudent = tblStudent.getSelectionModel().getSelectedItem();
                selectedStudent.setStudent_id(studentId);
                selectedStudent.setStudentName(studentName);
                selectedStudent.setStudentAddress(studentAddress);
                selectedStudent.setContact_no(contactNo);
                selectedStudent.setDob(dob);
                selectedStudent.setGender(gender);
                tblStudent.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                initUI();

            }
        }catch(Exception e){
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }
        }

    public void deleteStudentOnAction(ActionEvent actionEvent) {

        String id = tblStudent.getSelectionModel().getSelectedItem().getStudent_id();
        try {
            studentBO.deleteStudent(id);
            tblStudent.getItems().remove(tblStudent.getSelectionModel().getSelectedItem());
            tblStudent.getSelectionModel().clearSelection();
            initUI();
        } catch (SQLException e) {
        e.printStackTrace();
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
        } catch (Exception e){
        new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }

        }


}
