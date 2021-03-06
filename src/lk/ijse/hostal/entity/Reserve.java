package lk.ijse.hostal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reserve {

    @Id
    String res_id;
    LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_type_id",referencedColumnName = "room_type_id")
    private Room room;
    String status;
    private int res_qty;

}
