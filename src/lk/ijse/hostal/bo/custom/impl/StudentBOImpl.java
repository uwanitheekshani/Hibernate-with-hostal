package lk.ijse.hostal.bo.custom.impl;

import lk.ijse.hostal.bo.custom.StudentBO;
import lk.ijse.hostal.dao.DAOFactory;
import lk.ijse.hostal.dao.custom.StudentDAO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
   private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.save(new Student(dto.getStudent_id(),dto.getStudentName(),dto.getStudentAddress(),dto.getContact_no(),dto.getDob(),dto.getGender()));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.update(new Student(dto.getStudent_id(),dto.getStudentName(),dto.getStudentAddress(), dto.getContact_no(),dto.getDob(),dto.getGender()));
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.delete(id);
    }

    @Override
    public List<StudentDTO> getAll() throws SQLException, ClassNotFoundException, IOException {
        List<Student> all = studentDAO.getAll();
        List<StudentDTO> allDTO = new ArrayList<>();
        for (Student s : all){
            allDTO.add(new StudentDTO(s.getStudent_id(),s.getStudentName(),s.getStudentAddress(),s.getContact_no(),s.getDob(),s.getGender()));
        }
        return allDTO;
    }

}
