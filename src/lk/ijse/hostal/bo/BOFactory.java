package lk.ijse.hostal.bo;

import lk.ijse.hostal.bo.custom.impl.ReserveBOImpl;
import lk.ijse.hostal.bo.custom.impl.RoomBOImpl;
import lk.ijse.hostal.bo.custom.impl.StudentBOImpl;
import lk.ijse.hostal.bo.custom.impl.UserBOImpl;
import lk.ijse.hostal.dao.custom.impl.RoomDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes {
        STUDENT,ROOM,RESERVE,USER
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
           case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RESERVE:
                return new ReserveBOImpl();
            case USER:
                return new UserBOImpl();
//            case LEASTMOVABLEITEM:
//                return new LeastMovableBOImpl();
//            case ORDER:
//                return new OrderBOImpl();
//            case ORDERDETAILS:
//                return new OrderDetailBOImpl();
//            case INCOME:
//               return new IncomeBOImpl();
            default:
                return null;
        }
    }
}
