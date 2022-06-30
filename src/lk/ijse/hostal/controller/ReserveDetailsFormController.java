package lk.ijse.hostal.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostal.bo.BOFactory;
import lk.ijse.hostal.bo.custom.ReserveBO;
import lk.ijse.hostal.bo.custom.RoomBO;
import lk.ijse.hostal.dto.ReserveDTO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.view.tdm.RemainTM;
import lk.ijse.hostal.view.tdm.RoomTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReserveDetailsFormController {
    public AnchorPane remainKeyMoneyContext;
    public TableView<RemainTM> tblRemainKeyMoney;

    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVE);
    public void initialize() {
        tblRemainKeyMoney.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("res_Id"));
        tblRemainKeyMoney.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblRemainKeyMoney.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("student_id"));
        tblRemainKeyMoney.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        tblRemainKeyMoney.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));

        loadAllRemainStudents();
    }

    private void loadAllRemainStudents() {
        tblRemainKeyMoney.getItems().clear();

        try {
            List<ReserveDTO> allRemains = reserveBO.remainKeyMoneyStudents();
            for (ReserveDTO reserve : allRemains) {
                Room room = reserve.getRoom_type_id();
                String roomID = room.getRoom_type_id();
                Student student = reserve.getStudent_id();
                String studentID = student.getStudent_id();
                tblRemainKeyMoney.getItems().add(new RemainTM(reserve.getRes_id(), String.valueOf(reserve.getDate()), studentID, roomID, reserve.getStatus()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
