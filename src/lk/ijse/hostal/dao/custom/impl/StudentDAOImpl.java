package lk.ijse.hostal.dao.custom.impl;

import lk.ijse.hostal.dao.custom.StudentDAO;
import lk.ijse.hostal.entity.Student;
import lk.ijse.hostal.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student dto) throws SQLException, ClassNotFoundException, IOException {
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

        Student student = session.load(Student.class, s);

        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student dto) throws SQLException, ClassNotFoundException, IOException {
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

       // String hql = "FROM Student WHERE student_Id = :student_Id";
        Query query = session.createQuery("FROM student WHERE student_id = :student_Id");
        query.setParameter("student_Id", s);
        List list = query.getResultList();
        return list;
    }

    @Override
    public List<Student> getAll() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        String hql = "FROM student";
        Query query = session.createQuery(hql);
        List<Student> studentList = query.getResultList();

        return studentList;
    }

}
