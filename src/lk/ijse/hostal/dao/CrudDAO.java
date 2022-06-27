package lk.ijse.hostal.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T,ID> extends SuperDAO{

    boolean save(T dto) throws SQLException, ClassNotFoundException, IOException;
    boolean delete(ID id) throws SQLException, ClassNotFoundException, IOException;
    boolean update(T dto) throws SQLException, ClassNotFoundException, IOException;
    List search(ID id) throws SQLException, ClassNotFoundException, IOException;
    List<T> getAll() throws SQLException, ClassNotFoundException, IOException;
}
