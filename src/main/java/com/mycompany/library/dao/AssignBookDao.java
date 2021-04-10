package com.mycompany.library.dao;

import com.mycompany.library.model.AssignBook;
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
public class AssignBookDao implements AssignBookDaoInterface<AssignBook, Integer> {
    
    private Session currentSession;

    private Transaction currentTransaction;

    public AssignBookDao() {
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
    public void persist(AssignBook entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(AssignBook entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public AssignBook findById(Integer id) {
        AssignBook assignBook = (AssignBook) getCurrentSession().get(AssignBook.class, id);
        return assignBook;
    }

    @Override
    public void delete(AssignBook entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<AssignBook> findAll() {
        List<AssignBook> assignBooks = (List<AssignBook>) getCurrentSession().createQuery("from Book").list();
        return assignBooks;
    }

    @Override
    public void deleteAll() {
        List<AssignBook> entityList = findAll();
        entityList.forEach(entity -> {
            delete(entity);
        });
    }

}
