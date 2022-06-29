package lk.ijse.hostal.dao.custom.impl;

import lk.ijse.hostal.dao.custom.ReserveDAO;
import lk.ijse.hostal.entity.Reserve;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReserveDAOImpl implements ReserveDAO {

    @Override
    public boolean save(Reserve dto) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        Reserve reserve = session.load(Reserve.class, s);

        session.delete(reserve);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reserve dto) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List search(String s) throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    @Override
    public List<Reserve> getAll() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Reserve";
        Query query = session.createQuery(hql);
        List<Reserve> reserveList = query.getResultList();

        transaction.commit();
        session.close();

        return reserveList;
    }

    @Override
    public List<Reserve> searchReserve(String enteredText) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("DAOImpl"+enteredText);
        String hql = "FROM Reserve WHERE res_id = :res_Id";
        Query query = session.createQuery(hql);
        query.setParameter("res_Id", enteredText);
//        List list = query.getResultList();
        List<Reserve> r = query.list();

        transaction.commit();
        session.close();

        return r;
    }
}
