package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class ReserveRoomFormController {

    public AnchorPane reserveRoomsContext;
    public Label lblNonAcCount;
    public Label lblNonAcOrFoodCount;
    public Label lblAcCount;
    public Label lblAcOrFoodCount;
    public Label lblResId;
    public JFXComboBox cmbStudentId;
    public JFXTextField txtStudentName;
    public JFXComboBox cmbRoomTypeId;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public TableView tblReserveRoomsOnAction;
    public JFXTextField txtStatus;
    public JFXTextField txtResId;
    public TextField txtSearchByOrderIdCustomerId;
    public JFXTextField txtRoomQty;

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnAddReservationOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void searchOrderIdOnKeyReleased(KeyEvent keyEvent) {
    }
}
