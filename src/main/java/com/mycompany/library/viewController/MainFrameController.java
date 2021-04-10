package com.mycompany.library.viewController;

import com.mycompany.library.model.AssignBook;
import com.mycompany.library.service.BookService;
import com.mycompany.library.model.Book;
import com.mycompany.library.model.Student;
import com.mycompany.library.service.AssignBookService;
import com.mycompany.library.service.StudentService;
import com.mycompany.library.view.MainFrame;

/**
 *
 * @author fabricio
 */
public class MainFrameController {

    private BookService bookService;
    private StudentService studentService;
    private AssignBookService assignBookService;
    MainFrame mainFrameView;

    public MainFrameController(MainFrame mainFrame) {
        this.mainFrameView = mainFrame;
        this.bookService = new BookService();
        this.studentService = new StudentService();
        this.assignBookService = new AssignBookService();
    }

    public void insertBookButtonAction() {
        MainFrame instance = this.mainFrameView;
        try {
            String bookAuthor = instance.getBookAuthorField().getText().trim();
            String bookTitle = instance.getBookTitleField().getText().trim();
            Integer editionNumber = Integer.parseInt(instance.getEditionNumField().getText().trim());
            Integer pagesNumber = Integer.parseInt(instance.getPageNumFields().getText().trim());
            Book newBook = new Book(bookAuthor, bookTitle, editionNumber, pagesNumber);
            bookService.persist(newBook);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public void insertStudentButtonAction() {
        MainFrame instance = this.mainFrameView;
        try {
            Integer idStudent = Integer.parseInt(instance.getIdField().getText().trim());
            String firstName = instance.getFirstNameField().getText().trim();
            String lastName = instance.getLastNameField().getText().trim();
            String email = instance.getEmailField().getText().trim();
            Student newStudent = new Student(idStudent, firstName, lastName, email);
            studentService.persist(newStudent);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
    
    public void assignButtonAction(){
        MainFrame instance = this.mainFrameView;
        try {
            Integer idStudent = Integer.parseInt(instance.getStudentIdField().getText().trim());
            Integer idBook = Integer.parseInt(instance.getBookIdField().getText().trim());
            Student currentStudent = new Student(idStudent);
            Book currentBook = new Book(idBook);
            AssignBook newAssign = new AssignBook(currentBook, currentStudent);
            assignBookService.persist(newAssign);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

}
