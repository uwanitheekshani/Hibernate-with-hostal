package lk.ijse.hostal.bo.custom;

import lk.ijse.hostal.bo.SuperBO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {

    boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException, IOException;

    boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException, IOException;

    boolean deleteUser(String id) throws SQLException, ClassNotFoundException, IOException;

    List<UserDTO> getAll() throws SQLException, ClassNotFoundException, IOException;
}
