package com.mycompany.library.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author fabricio
 */
@Entity
@Table(name="book")
public class Book implements Serializable{
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "title", length = 100)
    private String title;
    
    @Column(name= "author", length = 100)
    private String author;
    
     
    @Column(name = "edition")
    private Integer editionNumber;
    
    @Column(name = "pages")
    private Integer pagesNumber;
    
    public Book() {
    }

    public Book(Integer id) {
        this.id = id;
    }

    public Book(String author, String title, Integer editionNumber, Integer pagesNumber) {
        this.author = author;
        this.title = title;
        this.editionNumber = editionNumber;
        this.pagesNumber = pagesNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(Integer editionNumber) {
        this.editionNumber = editionNumber;
    }

    public Integer getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(Integer pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", author=" + author + ", editionNumber=" + editionNumber + ", pagesNumber=" + pagesNumber + '}';
    }

}
