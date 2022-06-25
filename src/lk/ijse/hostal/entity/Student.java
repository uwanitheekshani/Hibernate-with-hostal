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
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student implements SuperEntity{
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

    @OneToMany(mappedBy = "Student")
    @Cascade(CascadeType.ALL)
    List<Room> roomList = new ArrayList<>();
}
