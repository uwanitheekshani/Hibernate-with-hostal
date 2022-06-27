package lk.ijse.hostal.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTM {
    String student_id;
    String studentName;
    String studentAddress;
    String contact_no;
    String dob;
    String gender;
}
