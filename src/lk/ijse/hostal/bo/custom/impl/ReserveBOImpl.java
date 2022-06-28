package lk.ijse.hostal.bo.custom.impl;

import lk.ijse.hostal.bo.custom.ReserveBO;
import lk.ijse.hostal.dao.DAOFactory;
import lk.ijse.hostal.dao.custom.ReserveDAO;
import lk.ijse.hostal.dao.custom.RoomDAO;
import lk.ijse.hostal.dao.custom.StudentDAO;
import lk.ijse.hostal.dto.ReserveDTO;
import lk.ijse.hostal.dto.RoomDTO;
import lk.ijse.hostal.dto.StudentDTO;
import lk.ijse.hostal.entity.Reserve;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReserveBOImpl implements ReserveBO {

    private final ReserveDAO reserveDAO = (ReserveDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVE);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);


    @Override
    public boolean save(ReserveDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return reserveDAO.save(new Reserve(dto.getRes_id(),dto.getDate(), dto.getStudent_id(), dto.getRoom_type_id(), dto.getStatus(), dto.getRes_qty()));
    }

    @Override
    public List<ReserveDTO> getAllReserve() throws SQLException, ClassNotFoundException, IOException {
        List<Reserve> all = reserveDAO.getAll();
        List<ReserveDTO> allDTO = new ArrayList<>();
        for (Reserve r : all){
            allDTO.add(new ReserveDTO(r.getRes_id(),r.getDate(),r.getStudent(),r.getRoom(),r.getStatus(),r.getRes_qty()));
        }
        return allDTO;
    }

    @Override
    public List<ReserveDTO> searchReserve(String enteredText) throws SQLException, ClassNotFoundException, IOException {
        List<Reserve> reserves = reserveDAO.search(enteredText);
        List<ReserveDTO> reserveDTOS = new ArrayList<>();
        for (Reserve rs : reserves){
            reserveDTOS.add(new ReserveDTO(rs.getRes_id(), rs.getDate(), rs.getStudent(), rs.getRoom(), rs.getStatus(), rs.getRes_qty()));
        }
        return reserveDTOS;
    }

    @Override
    public List<StudentDTO> searchStudent(String id) throws ClassNotFoundException, IOException, Exception {
        List<Student> student = studentDAO.search(id);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student st : student){
            studentDTOS.add(new StudentDTO(st.getStudent_id(), st.getStudentName(), st.getStudentAddress(), st.getContact_no(), st.getDob(), st.getGender()));
        }
        return studentDTOS;
    }

    @Override
    public List<RoomDTO> searchRoom(String id) throws ClassNotFoundException, IOException, Exception {
        List<Room> room = roomDAO.search(id);
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room r : room){
            roomDTOS.add(new RoomDTO(r.getRoom_type_id(),r.getType(),r.getKey_money(),r.getQty()));
        }
        return roomDTOS;
    }

    @Override
    public List<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException, Exception {
        List<Student> all = studentDAO.getAll();
        List<StudentDTO> allDTO = new ArrayList<>();
        for (Student s : all){
            allDTO.add(new StudentDTO(s.getStudent_id(),s.getStudentName(),s.getStudentAddress(),s.getContact_no(),s.getDob(),s.getGender()));
        }
        return allDTO;
    }

    @Override
    public List<RoomDTO> getAllRoom() throws SQLException, ClassNotFoundException, Exception {
        List<Room> all = roomDAO.getAll();
        List<RoomDTO> allDTO = new ArrayList<>();
        for (Room r : all){
            allDTO.add(new RoomDTO(r.getRoom_type_id(),r.getType(),r.getKey_money(),r.getQty()));
        }
        return allDTO;
    }
}
