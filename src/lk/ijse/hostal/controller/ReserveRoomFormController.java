package lk.ijse.hostal.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostal.bo.BOFactory;
import lk.ijse.hostal.bo.custom.ReserveBO;
import lk.ijse.hostal.bo.custom.RoomBO;
import lk.ijse.hostal.dto.ReserveDTO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.view.tdm.ReserveTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ReserveRoomFormController {

    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVE);
    private final RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    public AnchorPane reserveRoomsContext;
    public Label lblNonAcCount;
    public Label lblNonAcOrFoodCount;
    public Label lblAcCount;
    public Label lblAcOrFoodCount;
    public Label lblResId;
    public JFXComboBox<String> cmbStudentId;
    public JFXTextField txtStudentName;
    public JFXComboBox<String> cmbRoomTypeId;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtStatus;
    public JFXTextField txtResId;
    public TextField txtSearchByOrderIdCustomerId;
    public JFXTextField txtRoomQty;
    public Label lblDate;
    public TableView<ReserveTM> tblReserveRooms;
    public JFXButton btnReserved;
    public Label lblRoomId;
    public Label lblRoomType;
    public Label lblRoomCount;
    public JFXButton btnAdd;
    public TextField txtSearchByResId;
    String reservationId;

    public void initialize() throws Exception {
        tblReserveRooms.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("res_Id"));
        tblReserveRooms.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblReserveRooms.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        tblReserveRooms.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        tblReserveRooms.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));
        tblReserveRooms.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("res_qty"));
        TableColumn<ReserveTM, Button> lastCol = (TableColumn<ReserveTM, Button>) tblReserveRooms.getColumns().get(6);
        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(event -> {
                if (tblReserveRooms.getSelectionModel().getSelectedItem() != null) {
                    try {
                        reservationId = tblReserveRooms.getSelectionModel().getSelectedItem().getRes_Id();
                        if (reserveBO.deleteReservation(reservationId)) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Deleted.....").show();
                            tblReserveRooms.getItems().remove(param.getValue());
                            tblReserveRooms.getSelectionModel().clearSelection();

                        } else {

                            new Alert(Alert.AlertType.ERROR, "Try Again.....").show();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Select Row....").show();
                }
            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        try {
            loadAllReservation();
        } catch (Exception e) {
            e.printStackTrace();
        }

       lblDate.setText(LocalDate.now().toString());
//        btnReserved.setDisable(true);
//        txtResId.setFocusTraversable(false);
//        .setEditable(false);
//        txtDescription.setFocusTraversable(false);
//        txtDescription.setEditable(false);
//        txtUnitPrice.setFocusTraversable(false);
//        txtUnitPrice.setEditable(false);
//        txtQtyOnHand.setFocusTraversable(false);
//        txtQtyOnHand.setEditable(false);
//        txtDiscount.setFocusTraversable(false);
//        txtDiscount.setEditable(false);
//        txtQty.setOnAction(event -> btnAddOrder.fire());
//        txtQty.setEditable(false);
//        btnAddOrder.setDisable(true);

        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {
                try {
                    List<StudentDTO> all = reserveBO.searchStudent(newValue);
                    for (StudentDTO dto : all) {
                        txtStudentName.setText(dto.getStudentName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                txtStudentName.clear();
            }
        });


        cmbRoomTypeId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {

                try {
                    List<RoomDTO> all = reserveBO.searchRoom(newValue);
                    for (RoomDTO dto : all) {
                        lblRoomId.setText(dto.getRoom_type_id());
                        lblRoomType.setText(dto.getType());
                        txtRoomType.setText(dto.getType());
                        txtKeyMoney.setText(dto.getKey_money().setScale(2).toString());
                        Optional<ReserveTM> optOrderDetail = tblReserveRooms.getItems().stream().filter(detail -> detail.getRoom_type_id().equals(newValue)).findFirst();
                        lblRoomCount.setText((optOrderDetail.isPresent() ? dto.getQty() - optOrderDetail.get().getRes_qty() : dto.getQty()) + "");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else {
                txtRoomType.clear();
                txtKeyMoney.clear();
                txtRoomQty.clear();
                lblRoomCount.setDisable(true);
                lblRoomId.setDisable(true);
                lblRoomType.setDisable(true);
            }
        });


        loadAllStudentId();
        loadAllRoomId();
        tblReserveRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                btnReserved.setText("Update");
                txtResId.setText(newValue.getRes_Id());
                cmbStudentId.setValue(newValue.getStudent_id());
                cmbRoomTypeId.setValue(newValue.getRoom_type_id());
                txtStatus.setText(newValue.getStatus());
                txtRoomQty.setText(String.valueOf(newValue.getRes_qty()));
            }
        });
        reservationId = generateNewReservationId();
        txtResId.setText( reservationId);
    }

    private String generateNewReservationId() {
        try {
            return reserveBO.generateReservationId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new Reserve Id").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "RES-001";
    }

    private void loadAllReservation() throws SQLException, IOException, ClassNotFoundException {
        ObservableList<ReserveTM> observableList = FXCollections.observableArrayList();
        List<ReserveDTO> list = reserveBO.getAllReserve();

        for (ReserveDTO r : list) {
            String reserveID = r.getRes_id();
            LocalDate date = r.getDate();
            Room room = r.getRoom_type_id();
            String roomID = room.getRoom_type_id();
            Student student = r.getStudent_id();
            String studentID = student.getStudent_id();
            int qty = r.getRes_qty();
            String status = r.getStatus();


            ReserveTM reservationTM = new ReserveTM(reserveID, String.valueOf(date), studentID, roomID, status, qty);
            observableList.add(reservationTM);
            tblReserveRooms.setItems(observableList);
        }
    }

    private void loadAllRoomId() {
        try {
            List<RoomDTO> all = reserveBO.getAllRoom();
            for (RoomDTO dto : all) {
                cmbRoomTypeId.getItems().add(dto.getRoom_type_id());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllStudentId() throws Exception {
        try {
            List<StudentDTO> all = reserveBO.getAllStudent();
            for (StudentDTO dto : all) {
                cmbStudentId.getItems().add(dto.getStudent_id());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnReservedOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        Student s1 = new Student();
        s1.setStudent_id(cmbStudentId.getValue());

        Room r1 = new Room();
        r1.setRoom_type_id(cmbRoomTypeId.getValue());

        if (!btnReserved.getText().equals("Update")){
            reserveBO.save(new ReserveDTO(txtResId.getText(), LocalDate.now(), s1, r1, txtStatus.getText(), Integer.parseInt(txtRoomQty.getText())));

            cmbStudentId.setValue(null);
            cmbRoomTypeId.setValue(null);
            txtRoomQty.clear();
            txtStatus.clear();

        }else if (btnReserved.getText().equals("Update")){
            btnReserved.setText("Reserved");
            reserveBO.UpdateReservation(new ReserveDTO(txtResId.getText(), LocalDate.now(), s1, r1, txtStatus.getText(), Integer.parseInt(txtRoomQty.getText())));
        }
        loadAllReservation();
        reservationId = generateNewReservationId();
        txtResId.setText( reservationId);
    }




    public void searchResIdOnKeyReleased(KeyEvent keyEvent) throws SQLException, IOException, ClassNotFoundException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (txtSearchByResId.getText().equals("")) {
              loadAllReservation();
            }else {
                System.out.println(txtSearchByResId.getText());
                List<ReserveDTO> reserveDTOS = reserveBO.searchReserve(txtSearchByResId.getText());
                tblReserveRooms.getItems().clear();
                ObservableList<ReserveTM> reserveTMS = FXCollections.observableArrayList();

                for (ReserveDTO reserveDTO : reserveDTOS) {
                    System.out.println("for loop" + reserveDTO.getRes_id());
                    tblReserveRooms.getItems().add(new ReserveTM(reserveDTO.getRes_id(),
                                    reserveDTO.getDate().toString(),
                                    reserveDTO.getStudent_id().getStudent_id(),
                                    reserveDTO.getRoom_type_id().getRoom_type_id(),
                                    reserveDTO.getStatus(),
                                    reserveDTO.getRes_qty())
                            // BigDecimal.valueOf(od.getTotal())
                    );

                }
            }


        }
    }
}
