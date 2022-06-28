package lk.ijse.hostal.dto;

import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveDTO {
    String res_id;
    LocalDate date;
    Student student_id;
    Room room_type_id;
    String status;
    private int res_qty;

}
