package com.mycompany.library.dao;

import com.mycompany.library.model.Book;
import com.mycompany.library.model.Student;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author fabricio
 */
public class StudentDao implements StudentDaoInterface<Student, Integer>{

    
    private Session currentSession;

    private Transaction currentTransaction;

    public StudentDao() {
    }
    
    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = (Transaction) currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
    
    @Override
    public void persist(Student entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Student entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Student findById(Integer id) {
        Student student = (Student) getCurrentSession().get(Student.class, id);
        return student;
    }

    @Override
    public void delete(Student entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = (List<Student>) getCurrentSession().createQuery("from Book").list();
        return students;
    }

    @Override
    public void deleteAll() {
        List<Student> entityList = findAll();
        entityList.forEach(entity -> {
            delete(entity);
        });
    }
    
    

}
