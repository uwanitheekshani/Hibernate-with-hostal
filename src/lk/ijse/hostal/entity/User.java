package lk.ijse.hostal.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    String userId;
    @Column(nullable = false)
    String userName;
    @Column(nullable = false)
    String password;
}
