package com.mycompany.library.service;

import com.mycompany.library.model.Book;
import com.mycompany.library.dao.BookDao;
import java.util.List;

/**
 *
 * @author fabricio
 */
public class BookService {
    
    public static BookDao bookDao;

    public BookService() {
        bookDao = new BookDao();
    }
    
    public void persist(Book entity) {
        bookDao.openCurrentSessionwithTransaction();
        bookDao.persist(entity);
        bookDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(Book entity) {
        bookDao.openCurrentSessionwithTransaction();
        bookDao.update(entity);
        bookDao.closeCurrentSessionwithTransaction();
    }
    
    public Book findById(Integer id) {
        bookDao.openCurrentSession();
        Book book = bookDao.findById(id);
        bookDao.closeCurrentSession();
        return book;
    }
 
    public void delete(Integer id) {
        bookDao.openCurrentSessionwithTransaction();
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        bookDao.closeCurrentSessionwithTransaction();
    }
 
    public List<Book> findAll() {
        bookDao.openCurrentSession();
        List<Book> books = bookDao.findAll();
        bookDao.closeCurrentSession();
        return books;
    }
 
    public void deleteAll() {
        bookDao.openCurrentSessionwithTransaction();
        bookDao.deleteAll();
        bookDao.closeCurrentSessionwithTransaction();
    }
 
    public BookDao bookDao() {
        return bookDao;
    }

}
