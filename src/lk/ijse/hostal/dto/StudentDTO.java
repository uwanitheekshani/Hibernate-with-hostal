package lk.ijse.hostal.dto;

import lk.ijse.hostal.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    String student_id;
    String studentName;
    String studentAddress;
    String contact_no;
    String dob;
    String gender;
}
