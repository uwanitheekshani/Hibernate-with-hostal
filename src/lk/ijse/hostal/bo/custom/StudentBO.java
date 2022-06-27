package lk.ijse.hostal.bo.custom;

import lk.ijse.hostal.bo.SuperBO;
import lk.ijse.hostal.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    //boolean saveStudent(StudentDTO dto) throws Exception;

    boolean saveStudent(StudentDTO dto) throws SQLException, ClassNotFoundException, IOException;

    boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException, IOException;

    boolean deleteStudent(String id) throws SQLException, ClassNotFoundException, IOException;

    List<StudentDTO> getAll() throws SQLException, ClassNotFoundException, IOException;

}
