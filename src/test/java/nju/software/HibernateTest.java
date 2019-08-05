package nju.software;

import nju.software.entities.TUserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TUserEntity tUserEntity = new TUserEntity();
        tUserEntity.setUserName("SJ");
        tUserEntity.setPassword("123456");
        tUserEntity.setCredits(1000);
        session.save(tUserEntity);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
