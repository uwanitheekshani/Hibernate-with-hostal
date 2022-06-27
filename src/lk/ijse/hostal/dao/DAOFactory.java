package lk.ijse.hostal.dao;

import lk.ijse.hostal.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostal.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    //singleton
    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    //public final static integer values
    public enum DAOTypes {
        STUDENT,ROOM
    }

    //method for hide the object creation logic and return what client wants
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
               return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
//            case ORDER:
//                return new OrderDAOImpl(); //SuperDAO superDAO = new OrderDAOImpl();
//            case ORDERDETAILS:
//                return new OrderDetailsDAOImpl(); //SuperDAO superDAO = new OrderDetailsDAOImpl();
//            case QUERYDAO:
//                return new QueryDAOImpl(); //SuperDAO superDAO = new QueryDAOImpl();
            default:
                return null;
        }
    }
}
