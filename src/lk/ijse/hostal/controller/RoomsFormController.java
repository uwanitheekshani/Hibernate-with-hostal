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
import lk.ijse.hostal.bo.custom.RoomBO;
import lk.ijse.hostal.bo.custom.StudentBO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.view.tdm.RoomTM;
import lk.ijse.hostal.view.tdm.StudentTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class RoomsFormController {
    public AnchorPane roomsContext;
    public JFXTextField txtRoomTypeId;
    public JFXTextField txtRoomsQty;
    public JFXTextField txtKeyMoney;
    public Button btnSaveRoom;
    public Button btnDeleteRoom;
    public TableView<RoomTM> tblRooms;
    public JFXComboBox<String> cmbRoomType;

    private final RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    public void initialize() {
        tblRooms.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        tblRooms.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRooms.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("key_money"));
        tblRooms.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        

        initUI();

        ObservableList<String> obl = FXCollections.observableArrayList();

        obl.add("Non-AC");
        obl.add("Non-AC / Food");
        obl.add("AC ");
        obl.add("AC / Food");

        cmbRoomType.setItems(obl);

        tblRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteRoom.setDisable(newValue == null);
            btnSaveRoom.setText(newValue != null ? "Update" : "Save");
            btnSaveRoom.setDisable(newValue == null);

            if (newValue != null) {
                txtRoomTypeId.setText(newValue.getRoom_type_id());
                cmbRoomType.setValue(newValue.getType());
                txtKeyMoney.setText(newValue.getKey_money().setScale(2).toString());
                txtRoomsQty.setText(newValue.getQty() + "");


                txtRoomTypeId.setDisable(false);
                cmbRoomType.setDisable(false);
                txtKeyMoney.setDisable(false);
                txtRoomsQty.setDisable(false);

            }
        });


        loadAllRooms();
    }

    private void loadAllRooms() {
        tblRooms.getItems().clear();

        try {
            List<RoomDTO> allRooms = roomBO.getAll();
            for (RoomDTO room : allRooms) {
                tblRooms.getItems().add(new RoomTM(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty()));
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

        txtRoomTypeId.clear();
        cmbRoomType.getSelectionModel().clearSelection();
        txtKeyMoney.clear();
        txtRoomsQty.clear();
        txtRoomTypeId.setDisable(true);
        cmbRoomType.setDisable(true);
        txtKeyMoney.setDisable(true);
        txtRoomsQty.setDisable(true);
        btnSaveRoom.setDisable(true);
        btnDeleteRoom.setDisable(true);
    }


    public void btnNewRoomOnAction(ActionEvent actionEvent) {
        txtRoomTypeId.setDisable(false);
        cmbRoomType.setDisable(false);
        txtKeyMoney.setDisable(false);
        txtRoomsQty.setDisable(false);

        txtRoomTypeId.clear();
        cmbRoomType.getSelectionModel().clearSelection();
        txtKeyMoney.clear();
        txtRoomsQty.clear();

        txtRoomTypeId.requestFocus();
        btnSaveRoom.setDisable(false);
        btnSaveRoom.setText("Save");
        tblRooms.getSelectionModel().clearSelection();

    }
    public void saveRoomOnAction(ActionEvent actionEvent) {

        String roomTypeId = txtRoomTypeId.getText();
        String roomType = (String) cmbRoomType.getValue();
        BigDecimal keyMoney = new BigDecimal(txtKeyMoney.getText()).setScale(2);
        int roomsQty = Integer.parseInt(txtRoomsQty.getText());



        if (!roomTypeId.matches("^(RM-)[0-9]{2,4}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Id").show();
            txtRoomTypeId.requestFocus();
            return;
        } else if (!txtKeyMoney.getText().matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid KeyMoney").show();
            txtKeyMoney.requestFocus();
            return;
        }else if (!txtRoomsQty.getText().matches("^\\d+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Qty").show();
            txtRoomsQty.requestFocus();
            return;
        }
        try {
            if (btnSaveRoom.getText().equalsIgnoreCase("save")) {

                if (roomBO.saveRoom(new RoomDTO(roomTypeId, roomType, keyMoney, roomsQty))) {
                    tblRooms.getItems().add(new RoomTM(roomTypeId, roomType, keyMoney, roomsQty));
                    initUI();

                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed!").show();
                }


            } else {

                roomBO.updateRoom(new RoomDTO(roomTypeId, roomType, keyMoney, roomsQty));

                RoomTM selectedRoom = tblRooms.getSelectionModel().getSelectedItem();
                selectedRoom.setRoom_type_id(roomTypeId);
                selectedRoom.setType(roomType);
                selectedRoom.setKey_money(keyMoney);
                selectedRoom.setQty(roomsQty);
                tblRooms.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                initUI();

            }
        }catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }

    }

    public void deleteRoomOnAction(ActionEvent actionEvent) {

        String id = tblRooms.getSelectionModel().getSelectedItem().getRoom_type_id();
        try {
            roomBO.deleteRoom(id);
            tblRooms.getItems().remove(tblRooms.getSelectionModel().getSelectedItem());
            tblRooms.getSelectionModel().clearSelection();
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