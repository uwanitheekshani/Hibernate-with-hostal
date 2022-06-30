package lk.ijse.hostal.dao.custom.impl;

import lk.ijse.hostal.dao.custom.UserDAO;
import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.entity.User;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException, IOException {
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
        Transaction transaction = session.beginTransaction();

        User user = session.load(User.class, s);

        session.delete(user);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List search(String s) throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();

        String hql = "FROM User WHERE userId = :user_Id";
        Query query = session.createQuery(hql);
        query.setParameter("user_Id", s);
        List list = query.getResultList();
        return list;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "FROM User";
        Query query = session.createQuery(hql);
        List<User> userList = query.getResultList();

        return userList;
    }
}
