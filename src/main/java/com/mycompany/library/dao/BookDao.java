package com.mycompany.library.dao;

import com.mycompany.library.model.Book;
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
public class BookDao implements BookDaoInterface<Book, Integer> {

    private Session currentSession;

    private Transaction currentTransaction;

    public BookDao() {
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
    public void persist(Book entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Book entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Book findById(Integer id) {
        Book book = (Book) getCurrentSession().get(Book.class, id);
        return book;
    }

    @Override
    public void delete(Book entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = (List<Book>) getCurrentSession().createQuery("from Book").list();
        return books;
    }

    @Override
    public void deleteAll() {
        List<Book> entityList = findAll();
        entityList.forEach(entity -> {
            delete(entity);
        });
    }

}
