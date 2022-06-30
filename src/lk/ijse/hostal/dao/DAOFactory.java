package lk.ijse.hostal.dao;

import lk.ijse.hostal.dao.custom.impl.ReserveDAOImpl;
import lk.ijse.hostal.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostal.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hostal.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }


    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }


    public enum DAOTypes {
        STUDENT,ROOM,RESERVE,USER
    }


    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
               return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVE:
                return new ReserveDAOImpl();
            case USER:
                return new UserDAOImpl();
//            case QUERYDAO:
//                return new QueryDAOImpl(); //SuperDAO superDAO = new QueryDAOImpl();
            default:
                return null;
        }
    }
}
