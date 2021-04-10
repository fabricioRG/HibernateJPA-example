package com.mycompany.library.service;

import com.mycompany.library.dao.AssignBookDao;
import com.mycompany.library.model.AssignBook;
import java.util.List;

/**
 *
 * @author fabricio
 */
public class AssignBookService {
    
    public static AssignBookDao assignBookDao;

    public AssignBookService() {
        assignBookDao = new AssignBookDao();
    }
    
    public void persist(AssignBook entity) {
        assignBookDao.openCurrentSessionwithTransaction();
        assignBookDao.persist(entity);
        assignBookDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(AssignBook entity) {
        assignBookDao.openCurrentSessionwithTransaction();
        assignBookDao.update(entity);
        assignBookDao.closeCurrentSessionwithTransaction();
    }
    
    public AssignBook findById(Integer id) {
        assignBookDao.openCurrentSession();
        AssignBook assignBook = assignBookDao.findById(id);
        assignBookDao.closeCurrentSession();
        return assignBook;
    }
 
    public void delete(Integer id) {
        assignBookDao.openCurrentSessionwithTransaction();
        AssignBook assignBook = assignBookDao.findById(id);
        assignBookDao.delete(assignBook);
        assignBookDao.closeCurrentSessionwithTransaction();
    }
 
    public List<AssignBook> findAll() {
        assignBookDao.openCurrentSession();
        List<AssignBook> assingBooks = assignBookDao.findAll();
        assignBookDao.closeCurrentSession();
        return assingBooks;
    }
 
    public void deleteAll() {
        assignBookDao.openCurrentSessionwithTransaction();
        assignBookDao.deleteAll();
        assignBookDao.closeCurrentSessionwithTransaction();
    }
 
    public AssignBookDao assignBookDao() {
        return assignBookDao;
    }

}
