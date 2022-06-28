package lk.ijse.hostal.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "student")
public class Student {
    @Id
    String student_id;
    @Column(nullable = false)
    String studentName;
    @Column(columnDefinition = "TEXT",nullable = false)
    String studentAddress;
    @Column(nullable = false)
    String contact_no;
    @Column(nullable = false)
    String dob;
    @Column(nullable = false)
    String gender;

    @OneToMany(mappedBy = "student")
    @Cascade(CascadeType.ALL)
    List<Reserve> reserveList;

    public Student(String student_id, String studentName, String studentAddress, String contact_no, String dob, String gender) {
        this.student_id = student_id;
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.contact_no = contact_no;
        this.dob = dob;
        this.gender = gender;
    }
}
