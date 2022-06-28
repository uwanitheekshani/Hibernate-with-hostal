package lk.ijse.hostal.view.tdm;

import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveTM {
     String res_Id;
     LocalDate date;
     String student_id;
     String room_type_id;
     String status;
     int res_qty;
}
