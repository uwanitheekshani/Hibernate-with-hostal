package lk.ijse.hostal.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTM {
    String room_type_id;
    String type;
    BigDecimal key_money;
    int qty;
}
