package lk.ijse.hostal.util;

import lk.ijse.hostal.entity.Room;
import lk.ijse.hostal.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private  SessionFactory sessionFactory;


    private FactoryConfiguration() throws IOException {
//        Configuration configuration = new Configuration();
//        Properties p = new Properties();
//        p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
//        configuration.setProperties(p);
//
//         configuration.addAnnotatedClass(Student.class);
//         configuration.addAnnotatedClass(Room.class);
//
//        sessionFactory = configuration.buildSessionFactory();

        Properties properties  = new Properties();
        try {
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("lk/ijse/hostal/resourses/hibernte.properties"));
            Configuration configuration = new Configuration().mergeProperties(properties)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Room.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (IOException e) {}

    }



    public static FactoryConfiguration getInstance() throws IOException {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration()
                : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
