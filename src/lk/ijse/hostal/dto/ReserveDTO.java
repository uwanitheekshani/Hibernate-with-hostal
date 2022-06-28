package lk.ijse.hostal.dto;

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
    String student_id;
    String room_type_id;
    String status;

}
