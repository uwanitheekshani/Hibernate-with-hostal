package lk.ijse.hostal.bo.custom.impl;

import lk.ijse.hostal.bo.custom.UserBO;
import lk.ijse.hostal.dao.DAOFactory;
import lk.ijse.hostal.dao.custom.RoomDAO;
import lk.ijse.hostal.dao.custom.StudentDAO;
import lk.ijse.hostal.dao.custom.UserDAO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.dto.UserDTO;
import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    private final UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);


    @Override
    public boolean saveUser(UserDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return userDAO.save(new User(dto.getUserId(),dto.getUserName(),dto.getPassword()));

    }

    @Override
    public boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return userDAO.update(new User(dto.getUserId(),dto.getUserName(),dto.getPassword()));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException, IOException {
        return userDAO.delete(id);
    }

    @Override
    public List<UserDTO> getAll() throws SQLException, ClassNotFoundException, IOException {
        List<User> all = userDAO.getAll();
        List<UserDTO> allDTO = new ArrayList<>();
        for (User s : all){
            allDTO.add(new UserDTO(s.getUserId(),s.getUserName(),s.getPassword()));
        }
        return allDTO;
    }
}
