package lk.ijse.hostal.bo.custom;

import lk.ijse.hostal.bo.SuperBO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuperBO {
    boolean saveRoom(RoomDTO dto) throws SQLException, ClassNotFoundException, IOException;

    boolean updateRoom(RoomDTO dto) throws SQLException, ClassNotFoundException, IOException;

    boolean deleteRoom(String id) throws SQLException, ClassNotFoundException, IOException;

    List<RoomDTO> getAll() throws SQLException, ClassNotFoundException, IOException;


}
