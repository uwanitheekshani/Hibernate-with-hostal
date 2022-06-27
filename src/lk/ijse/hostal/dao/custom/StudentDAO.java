package lk.ijse.hostal.dao.custom;

import lk.ijse.hostal.dao.CrudDAO;
import lk.ijse.hostal.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDAO extends CrudDAO<Student,String> {
   // public ArrayList<Student> searchStudents(String enteredText) throws SQLException, ClassNotFoundException;

}
