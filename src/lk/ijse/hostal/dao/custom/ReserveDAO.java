package lk.ijse.hostal.dao.custom;

import lk.ijse.hostal.dao.CrudDAO;
import lk.ijse.hostal.dao.SuperDAO;
import lk.ijse.hostal.entity.Reserve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ReserveDAO extends CrudDAO<Reserve,String> {
    List<Reserve> searchReserve(String enteredText) throws SQLException, ClassNotFoundException, IOException;
    String generateReservationId() throws Exception;
    List<Reserve> remainKeyMoneyStudents() throws Exception;
}
