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
public class Room {

    @Id
    String room_type_id;
    @Column(nullable = false)
    String type;
    @Column(nullable = false)
    String key_money;
    @Column(nullable = false)
    int qty;

    @OneToMany(mappedBy = "Room")
    @Cascade(CascadeType.ALL)
    List<Student> studentList = new ArrayList<>();
}
