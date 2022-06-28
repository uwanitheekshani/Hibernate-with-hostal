package lk.ijse.hostal.bo.custom;

import lk.ijse.hostal.bo.SuperBO;
import lk.ijse.hostal.dto.ReserveDTO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ReserveBO extends SuperBO {

    boolean save(ReserveDTO dto) throws SQLException,ClassNotFoundException, IOException;
    List<ReserveDTO> getAllReserve() throws SQLException,ClassNotFoundException, IOException;
    List<ReserveDTO> searchReserve(String enteredText) throws SQLException, ClassNotFoundException, IOException;
    List<StudentDTO> searchStudent (String id) throws ClassNotFoundException, IOException, Exception;
    List<RoomDTO> searchRoom (String id) throws ClassNotFoundException, IOException, Exception;
    List<StudentDTO> getAllStudent() throws SQLException,ClassNotFoundException, Exception;
    List<RoomDTO> getAllRoom() throws SQLException,ClassNotFoundException, Exception;
}
