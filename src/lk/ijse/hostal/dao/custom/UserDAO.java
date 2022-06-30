package lk.ijse.hostal.dao.custom;

import lk.ijse.hostal.dao.CrudDAO;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User,String> {
    User searchUser(String id) throws SQLException, ClassNotFoundException, IOException;
}
